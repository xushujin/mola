package com.mola.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mola.auth.enyity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆授权提交方式拦截处理
 *
 * @author hatim
 */
@Slf4j
public class MolaAuthFilter extends UsernamePasswordAuthenticationFilter {
    public static final String POST = "POST";
    public static final String ERROR_MSG = "请求体解析错误";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 判断是否是json提交 且post请求
        log.info("Method:{},ContentType:{}", request.getMethod(), request.getContentType());
        if (!POST.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else if (StringUtils.equalsIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE) ||
                StringUtils.equalsIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            log.info("登陆 header：{}", request.getHeader("entCode"));
            // Jackson序列化
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try {
                LoginUser user = mapper.readValue(request.getInputStream(), LoginUser.class);
                log.info("LoginUser:{}", user);
                authRequest = new UsernamePasswordAuthenticationToken(
                        user.getUsername().trim(), user.getPassword());
            } catch (IOException e) {
                log.error(ERROR_MSG, e);
                throw new AuthenticationServiceException(ERROR_MSG);
            }
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            // 非json提交走原来的form提交方式
            return super.attemptAuthentication(request, response);
        }
    }
}
