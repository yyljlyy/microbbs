package me.micro.bbs.reply.support;

import me.micro.bbs.enums.PostStatus;
import me.micro.bbs.post.Post;
import me.micro.bbs.reply.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * ReplyService
 *
 * Created by microacup on 2016/11/8.
 */
@Service
public class ReplyService {
    public static final String CACHES_NAME = "cache.replies";
    public static final String CACHE_NAME = "cache.reply";
    public static final Class<?> CACHE_TYPE = Reply.class;

    @Autowired
    private ReplyRepository replyRepository;

    // 按照帖子找回复
    public Page<Reply> findReplies(Post post, int page, int pageSize) {
        Page<Reply> replies = replyRepository.findByPostAndStatus(post,
                PostStatus.actived,
                new PageRequest(page, pageSize, Sort.Direction.ASC, "floor"));
        return replies;
    }

    // 回复的回复
    public Page<Reply> findRepliesByReplyAndStatus(Long replyId, int page, int pageSize) {
        Reply reply = new Reply();
        reply.setId(replyId);
        Page<Reply> replies = replyRepository.findByReplyAndStatus(reply, PostStatus.actived, new PageRequest(page, pageSize, Sort.Direction.ASC, "floor"));
        return replies;
    }


}
