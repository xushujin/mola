package com.mola.authx.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 登陆用户详情
 *
 * @author hatim
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserBo implements UserDetails {

    /**
     * 登陆用户ID
     */
    private String id;
    /**
     * 登陆用户名
     */
    private String username;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户年龄
     */
    private Integer age;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
