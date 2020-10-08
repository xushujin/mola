package com.mola.auth.handler;

import com.mola.core.helper.JsonHelper;
import com.mola.core.response.success.SuccessVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功事件
 *
 * @author hatim
 */
@Slf4j
@Component
public class MolaLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("登出成功,Authentication:{},requestSessionId:{}", authentication, httpServletRequest.getSession().getId());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            httpServletResponse.getWriter().write(JsonHelper.toJson(SuccessVo.builder().data("登出成功").build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
