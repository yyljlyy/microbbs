package me.micro.bbs.security.oauth.client;

import me.micro.bbs.enums.Channel;
import me.micro.bbs.enums.ClientType;
import me.micro.bbs.security.social.SocialToken;
import me.micro.bbs.user.User;
import me.micro.bbs.user.UserForm;
import me.micro.bbs.user.UserSocial;
import me.micro.bbs.user.support.UserService;
import me.micro.bbs.user.support.UserSocialRepository;
import me.micro.bbs.util.ShortUUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 *
 *
 * Created by microacup on 2016/11/29.
 */
@Controller
@RequestMapping("/oauth/lms")
public class LmsOAuthClient {
    private static final String OAUTH_CALLBACK = "/oauth/lms/callback";
    private static final String authorize_url = "/oauth2/authorize";
    private static final String access_token_url =  "/oauth2/access_token";
    private static final String user_show = "/api/v2/users/%s";

    @Value("${oauth2.lms.server}")
    private String lms;

    @Value("${oauth2.lms.clientId}")
    private String clientId;

    @Value("${oauth2.lms.clientSecret}")
    private String clientSecret;

    @Value("${site.server}")
    private String server;

    @Autowired
    private UserSocialRepository userSocialRepository;

    @Autowired
    private UserService userService;

    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 集成LMS
     *
     * @param forward 授权成功后转发地址
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam("forward") String forward) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getDetails() instanceof User && authentication.isAuthenticated()) {
            HttpSession session = request.getSession();
            session.setAttribute("simple-category", null);
            session.setAttribute("simple-tag", null);
            return "redirect:" + forward;
        }

        try {
            OAuthClientRequest oauthResponse = OAuthClientRequest
                    .authorizationLocation(lms + authorize_url)
                    .setResponseType(OAuth.OAUTH_CODE)
                    .setClientId(clientId)
                    .setRedirectURI(server + OAUTH_CALLBACK)
                    .setState(forward)
                    .buildQueryMessage();
            WebUtils.setSessionAttribute(request, "state", forward);
            return "redirect:"+oauthResponse.getLocationUri();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }

        return "redirect:/500";
    }

    @GetMapping("/callback")
    public String callback(HttpServletRequest request, Model model) {
        OAuthAuthzResponse oauthAuthzResponse = null;
        try {
            oauthAuthzResponse = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
            String forward = oauthAuthzResponse.getState();
            String code = oauthAuthzResponse.getCode();

            if (illegal(forward, request)) {
                model.addAttribute("message", "非法入口");
                return "site/500";
            }

            OAuthClientRequest oauthClientRequest = OAuthClientRequest
                    .tokenLocation(lms + access_token_url)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRedirectURI(server + OAUTH_CALLBACK)
                    .setCode(code)
                    .buildQueryMessage();
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            //获取access token
            OAuthJSONAccessTokenResponse oAuthResponse = oAuthClient.accessToken(oauthClientRequest, OAuth.HttpMethod.POST);
            String accessToken = oAuthResponse.getAccessToken();
            // String refreshToken= oAuthResponse.getRefreshToken();
            // Long expiresIn = oAuthResponse.getExpiresIn();
            String body = oAuthResponse.getBody();
            JSONObject jsonObject = new JSONObject(body);
            String openId = jsonObject.getString("openId");

            // 根据UID查找是否已经登录过
            UserSocial userSocial = userSocialRepository.findBySourceAndOpenid(getName(), openId);
            if (userSocial != null) {
                User user = userSocial.getUser();
                return login(request, user, forward);
            } else {
                //获得资源服务
                OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(String.format(lms + user_show, openId))
                        .setAccessToken(accessToken)
                        .buildQueryMessage();
                OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
                String resBody = resourceResponse.getBody();
                // 解析这个用户
                JSONObject userInfo = new JSONObject(resBody);
                if (userInfo.getInt("code") == 200) {
                    UserForm userForm = toUserForm(userInfo.getJSONObject("data"));
                    User user = userService.newUserAndUserSocial(userForm);
                    return login(request, user, forward);
                } else {
                    model.addAttribute("message", userInfo.getString("msg"));
                }
            }
        } catch (OAuthSystemException ex) {
            ex.printStackTrace();
            model.addAttribute("message", ex.getMessage());
        } catch (OAuthProblemException e) {
            e.printStackTrace();
            model.addAttribute("message", e.getDescription());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            model.addAttribute("message", "无法解码URL");
        }

        return "site/500";
    }

    private UserForm toUserForm(JSONObject userInfo) {
        UserForm userForm = new UserForm();
        userForm.setOpenid(userInfo.getString("openId"));
        userForm.setClient(ClientType.WEB);
        userForm.setGender(userInfo.getString("gender").equals("男") ? 1: 2);
        userForm.setChannel(Channel.OPEN);
        userForm.setInfo(userInfo.getString("info"));
        userForm.setName(userInfo.getString("name"));
        userForm.setUsername(ShortUUID.uuid());
        userForm.setNick(userInfo.getString("nickname"));
        userForm.setOpenNickname(userInfo.getString("nickname"));
        userForm.setSource(getName());

        return userForm;
    }


    // state是否非法
    private boolean illegal(String state, HttpServletRequest request) {
        return StringUtils.isBlank(state) || !state.equals(WebUtils.getSessionAttribute(request, "state"));
    }

    private String login(HttpServletRequest request, User user, String forward) throws UnsupportedEncodingException {
        // 第三方登录
        SocialToken socialToken = new SocialToken(user);
        socialToken.setDetails(authenticationDetailsSource.buildDetails(request));
        Authentication authenticate = authenticationManager.authenticate(socialToken);
        if (authenticate  != null && authenticate.isAuthenticated()) {
            return "redirect:" + forward;
        }

        return "redirect:/login";
    }


    String getName() {
        return "lm";
    }

}
