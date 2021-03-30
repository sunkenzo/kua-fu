package com.mini.kuafu.api.auth;

import com.mini.kuafu.util.ValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author kenzo
 * @create 2021-03-24 18:12
 */
@RestController
public class GetValidCodeApi extends AuthCommon {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GetValidCodeApi(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @GetMapping("/validCodeImg")
    private void process() {
        ValidateCodeUtil.ValidCode validCode = ValidateCodeUtil.createImage();
        logger.debug("code {}", validCode.getCode());

        HttpSession session = this.getSession();
        session.setAttribute(AuthCommon.VALID_CODE_KEY, validCode.getCode());

        try (OutputStream os = this.getResponse().getOutputStream()) {
            this.getResponse().setContentType("image/png");
            BufferedImage image = validCode.getImage();
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
