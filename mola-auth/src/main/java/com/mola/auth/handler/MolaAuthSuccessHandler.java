package com.mola.auth.handler;

import com.mola.core.helper.JsonHelper;
import com.mola.core.response.success.SuccessVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权成功处理
 *
 * @author hatim
 */
@Slf4j
@Component
public class MolaAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功,userDetail:{},sessionId:{}", authentication.getPrincipal(), request.getSession().getId());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            response.getWriter().write(JsonHelper.toJson(SuccessVo.builder().data("成功").build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
