package ltd.leaves.mall.controller.common;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import ltd.leaves.mall.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    @GetMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/png");

        // The three parameters are width, height and number respectively
        SpecCaptcha captcha = new SpecCaptcha(150, 40, 4);

        // Sets type numbers and letters mixed
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //Set the font
        captcha.setCharType(Captcha.FONT_9);

        // The verification code is stored in the session
        httpServletRequest.getSession().setAttribute("verifyCode", captcha.text().toLowerCase());

        // Output picture stream
        captcha.out(httpServletResponse.getOutputStream());
    }

    @GetMapping("/common/mall/kaptcha")
    public void mallKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/png");

        // The three parameters are width, height and number respectively
        SpecCaptcha captcha = new SpecCaptcha(110, 40, 4);

        // Sets type numbers and letters mixed
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //Set the font
        captcha.setCharType(Captcha.FONT_9);

        // The verification code is stored in the session
        httpServletRequest.getSession().setAttribute(Constants.MALL_VERIFY_CODE_KEY, captcha.text().toLowerCase());

        // Output picture stream
        captcha.out(httpServletResponse.getOutputStream());
    }
}
