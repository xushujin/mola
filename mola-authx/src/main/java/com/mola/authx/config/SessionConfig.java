package com.mola.authx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * session配置
 *
 * @author hatim
 */
@EnableWebSecurity
@EnableRedisHttpSession(
        redisNamespace = "MOLA",
        flushMode = FlushMode.ON_SAVE,
        maxInactiveIntervalInSeconds = 7 * 24 * 60 * 60)
public class SessionConfig {

    /**
     * 自定义CookieSerializer
     *
     * @return
     */
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("MOLA_SESSION_ID");
        serializer.setUseBase64Encoding(false);
        serializer.setCookiePath("/");

        /**
         * CookiePath设置为根路径且配置了相关的正则表达式，可以达到同父域下的单点登录的效果，
         * 在未涉及跨域的单点登录系统中，这是一个非常优雅的解决方案。
         * 如果我们的当前域名是moe.cnkirito.moe，该正则会将Cookie设置在父域cnkirito.moe中，
         * 如果有另一个相同父域的子域名blog.cnkirito.moe也会识别这个Cookie，便可以很方便的实现同父域下的单点登录
         */
        /** TODO druid.auth-test.wisdomcity.com.cn域名长度不一致时候好像不行，有时间得研究下
         serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
         **/
        return serializer;
    }
}
