package com.mola.auth.handler;

import com.mola.auth.filter.MolaAuthFilter;
import com.mola.auth.filter.CaptchaFilter;
import com.mola.core.helper.JsonHelper;
import com.mola.core.response.failure.FailureVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 授权失败处理
 *
 * @author hatim
 */
@Slf4j
@Component
public class MolaAuthFailureHandler implements AuthenticationFailureHandler {
    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败 exception:{}", exception.getMessage());
        String msg = Optional.ofNullable(exception.getMessage()).orElse("账号或密码错误");
        switch (msg) {
            case CaptchaFilter.ERROR_MSG:
                msg = CaptchaFilter.ERROR_MSG;
                break;
            case MolaAuthFilter.ERROR_MSG:
                msg = MolaAuthFilter.ERROR_MSG;
                break;
            default:
                break;
        }
        if (CaptchaFilter.ERROR_MSG.equals(exception.getMessage())) {
            msg = exception.getMessage();
        }
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.getWriter().write(JsonHelper.toJson(FailureVo.builder().msg(msg).build()));
    }
}
