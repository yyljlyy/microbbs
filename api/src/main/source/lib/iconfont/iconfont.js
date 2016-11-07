;(function(window) {

var svgSprite = '<svg>' +
  ''+
    '<symbol id="icon-youxuan" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M967.27 302.985c-7.782-17.203-27.921-24.781-45.193-17.067-17.203 7.782-24.781 27.989-17.067 45.193 25.19 55.637 37.956 115.098 37.956 176.606 0 236.476-192.307 428.783-428.783 428.783-236.407 0-428.783-192.307-428.783-428.783-0.068-236.407 192.307-428.783 428.715-428.783 78.029 0 154.283 21.094 220.638 61.030 16.179 9.762 37.205 4.574 46.899-11.605 9.694-16.179 4.437-37.137-11.674-46.831-77.005-46.353-165.478-70.861-255.863-70.861-274.022 0-497.050 223.027-497.050 497.050s223.027 497.050 497.050 497.050 497.050-223.027 497.050-497.050c0-71.27-14.746-140.151-43.895-204.732z"  ></path>'+
      ''+
      '<path d="M804.25 171.708c0 20.623 16.719 37.342 37.342 37.342s37.342-16.719 37.342-37.342c0-20.623-16.719-37.342-37.342-37.342-20.623 0-37.342 16.719-37.342 37.342z"  ></path>'+
      ''+
      '<path d="M587.571 461.841v274.432c0 51.61 13.517 66.56 63.829 66.56h81.51c47.514 0 59.051-27.17 63.147-126.976-11.537-3.413-29.901-11.537-40.073-18.978-1.98 88.269-6.076 103.834-26.487 103.834h-73.318c-21.094 0-24.439-4.096-24.439-24.371v-274.432h-44.169z"  ></path>'+
      ''+
      '<path d="M752.572 320.598c-19.661-27.17-60.416-69.973-93.662-99.806l-31.266 22.391c33.314 31.198 73.387 74.684 91.682 103.219l33.246-25.805z"  ></path>'+
      ''+
      '<path d="M796.058 364.084h-244.463c2.731-50.927 3.413-103.219 3.413-153.532h-44.851c0 50.244-0.683 102.537-2.731 153.532h-155.511v42.803h152.781c-10.172 154.146-44.851 297.438-160.973 378.948 11.537 7.441 25.805 21.094 33.246 31.949 122.948-89.634 160.29-244.463 172.51-410.897h246.511v-42.803z"  ></path>'+
      ''+
      '<path d="M313.207 821.129v-448.239c27.853-49.562 52.292-102.537 71.339-156.194l-41.438-12.22c-36.727 103.902-96.461 205.824-160.29 272.316 8.192 10.172 21.709 33.246 26.487 42.803 21.026-23.142 41.438-48.947 61.099-78.165v379.699h42.803z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
    '<symbol id="icon-zuixin" viewBox="0 0 1036 1024">'+
      ''+
      '<path d="M946.688 123.258435l-801.391304 0c-12.288 0-22.26087 9.46087-22.26087 21.147826 0 11.664696 9.97287 21.147826 22.26087 21.147826l801.391304 0c24.576 0 44.521739 18.944 44.521739 42.295652l0 676.730435c0 23.351652-19.945739 42.295652-44.521739 42.295652l-845.913043 0c-24.576 0-44.521739-18.944-44.521739-42.295652l0-634.434783c0-11.686957-9.97287-21.147826-22.26087-21.147826s-22.26087 9.46087-22.26087 21.147826l0 634.434783c0 46.703304 39.869217 84.591304 89.043478 84.591304l845.913043 0c49.174261 0 89.043478-37.888 89.043478-84.591304l0-676.730435C1035.731478 161.124174 995.862261 123.258435 946.688 123.258435zM590.514087 355.884522c12.288 0 22.26087-9.48313 22.26087-21.147826s-9.97287-21.147826-22.26087-21.147826l-155.826087 0c-12.288 0-22.26087 9.48313-22.26087 21.147826l0 422.956522c0 11.664696 9.97287 21.147826 22.26087 21.147826l155.826087 0c12.288 0 22.26087-9.48313 22.26087-21.147826s-9.97287-21.147826-22.26087-21.147826l-133.565217 0 0-169.182609 133.565217 0c12.288 0 22.26087-9.48313 22.26087-21.147826s-9.97287-21.147826-22.26087-21.147826l-133.565217 0 0-169.182609L590.514087 355.884522zM189.818435 757.693217l0-310.984348 133.565217 310.984348c4.87513 10.573913 9.97287 21.147826 22.26087 21.147826s22.26087-9.48313 22.26087-21.147826l0-422.956522c0-11.664696-9.97287-21.147826-22.26087-21.147826s-22.26087 9.48313-22.26087 21.147826l0 310.984348-133.565217-310.984348c-5.565217-11.575652-9.97287-21.147826-22.26087-21.147826s-22.26087 9.48313-22.26087 21.147826l0 422.956522c0 11.664696 9.97287 21.147826 22.26087 21.147826S189.818435 769.357913 189.818435 757.693217zM679.557565 313.566609c-12.288 0-22.26087 9.48313-22.26087 21.147826l0 422.956522c0 11.664696 9.97287 21.147826 22.26087 21.147826s17.051826-10.24 22.26087-21.147826l77.490087-170.963478 78.336 170.963478c5.565217 11.241739 9.97287 21.147826 22.26087 21.147826s22.26087-9.48313 22.26087-21.147826l0-422.956522c0-11.664696-9.97287-21.147826-22.26087-21.147826s-22.26087 9.48313-22.26087 21.147826l0 316.059826-57.766957-127.421217c-4.697043-10.796522-17.719652-15.916522-29.094957-11.464348-4.919652 1.936696-8.43687 5.520696-10.796522 9.638957l-58.167652 128.422957L701.818435 334.736696C701.818435 323.049739 691.867826 313.566609 679.557565 313.566609z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
    '<symbol id="icon-zhiding" viewBox="0 0 1024 1024">'+
      ''+
      '<path d="M227.476 296.172h92.166v380.162c0 17.54-9.154 26.334-27.46 26.334-18.712 0-36.84-2.12-54.38-6.358 4.194 16.414 7.26 33.412 9.154 50.998 11.814 0.766 27.46 1.128 46.94 1.128 46.94 0 70.432-22.726 70.432-68.178v-384.084h76.114v-41.8h-212.964v41.8zM589.286 492.499c0 69.486-13.166 121.34-39.5 155.698-29.76 36.658-80.712 65.246-152.858 85.854 11.092 14.88 20.2 28.632 27.46 41.214 77.872-24.394 132.612-58.392 164.312-101.906 4.6-6.088 8.748-12.942 12.58-20.606 56.094 37.786 109.752 77.872 160.84 120.212l32.060-38.328c-53.072-39.726-111.78-80.126-176.306-121.34 10.326-33.592 15.466-74.22 15.466-121.97v-62.946h-44.098v64.12zM457.62 635.616h44.098v-235.284h220.946v232.984h44.098v-274.198h-157.998l18.306-77.286h165.438v-41.214h-341.202v41.214h129.366l-16.008 77.286h-107.046v276.498z"  ></path>'+
      ''+
      '<path d="M512 57.055c248.632 0 450.91 202.278 450.91 450.91s-202.278 450.91-450.91 450.91-450.91-202.278-450.91-450.91c0-248.632 202.278-450.91 450.91-450.91M512 11.964c-273.928 0-496 222.028-496 496 0 273.928 222.028 496 496 496s496-222.072 496-496c0-273.972-222.072-496-496-496v0z"  ></path>'+
      ''+
    '</symbol>'+
  ''+
'</svg>'
var script = function() {
    var scripts = document.getElementsByTagName('script')
    return scripts[scripts.length - 1]
  }()
var shouldInjectCss = script.getAttribute("data-injectcss")

/**
 * document ready
 */
var ready = function(fn){
  if(document.addEventListener){
      document.addEventListener("DOMContentLoaded",function(){
          document.removeEventListener("DOMContentLoaded",arguments.callee,false)
          fn()
      },false)
  }else if(document.attachEvent){
     IEContentLoaded (window, fn)
  }

  function IEContentLoaded (w, fn) {
      var d = w.document, done = false,
      // only fire once
      init = function () {
          if (!done) {
              done = true
              fn()
          }
      }
      // polling for no errors
      ;(function () {
          try {
              // throws errors until after ondocumentready
              d.documentElement.doScroll('left')
          } catch (e) {
              setTimeout(arguments.callee, 50)
              return
          }
          // no errors, fire

          init()
      })()
      // trying to always fire before onload
      d.onreadystatechange = function() {
          if (d.readyState == 'complete') {
              d.onreadystatechange = null
              init()
          }
      }
  }
}

/**
 * Insert el before target
 *
 * @param {Element} el
 * @param {Element} target
 */

var before = function (el, target) {
  target.parentNode.insertBefore(el, target)
}

/**
 * Prepend el to target
 *
 * @param {Element} el
 * @param {Element} target
 */

var prepend = function (el, target) {
  if (target.firstChild) {
    before(el, target.firstChild)
  } else {
    target.appendChild(el)
  }
}

function appendSvg(){
  var div,svg

  div = document.createElement('div')
  div.innerHTML = svgSprite
  svg = div.getElementsByTagName('svg')[0]
  if (svg) {
    svg.setAttribute('aria-hidden', 'true')
    svg.style.position = 'absolute'
    svg.style.width = 0
    svg.style.height = 0
    svg.style.overflow = 'hidden'
    prepend(svg,document.body)
  }
}

if(shouldInjectCss && !window.__iconfont__svg__cssinject__){
  window.__iconfont__svg__cssinject__ = true
  try{
    document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>");
  }catch(e){
    console && console.log(e)
  }
}

ready(appendSvg)


})(window)