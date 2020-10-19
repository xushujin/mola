package com.mola.authx.filter;

import com.google.code.kaptcha.Constants;
import com.mola.authx.handler.MolaAuthFailureHandler;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码校验拦截器
 *
 * @author hatim
 */
public class CaptchaFilter extends AbstractAuthenticationProcessingFilter {
    public static final String POST = "POST";
    public static final String CAPTCHA = "captcha";
    public static final String ERROR_MSG = "验证码错误";

    private String servletPath;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (POST.equalsIgnoreCase(request.getMethod()) && servletPath.equals(request.getServletPath())) {
            // 从session获取验证码校验
            String expect = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (expect != null && !expect.equalsIgnoreCase(req.getParameter(CAPTCHA))) {
                unsuccessfulAuthentication(request, response, new InsufficientAuthenticationException(ERROR_MSG));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public CaptchaFilter(String servletPath) {
        super(servletPath);
        this.servletPath = servletPath;
        setAuthenticationFailureHandler(new MolaAuthFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
