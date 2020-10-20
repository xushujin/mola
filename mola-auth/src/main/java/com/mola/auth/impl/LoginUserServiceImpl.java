package com.mola.auth.impl;

import com.mola.auth.dao.LoginUserDao;
import com.mola.auth.dao.UserInfoDao;
import com.mola.auth.enyity.LoginUser;
import com.mola.auth.enyity.UserInfo;
import com.mola.auth.pojo.bo.LoginUserBo;
import com.mola.auth.pojo.mapper.LoginUserBoMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 登陆用户 service
 *
 * @author hatim
 */
@Service
public class LoginUserServiceImpl implements UserDetailsService {
    private final LoginUserDao loginUserDao;
    private final UserInfoDao userInfoDao;


    public LoginUserServiceImpl(LoginUserDao loginUserDao, UserInfoDao userInfoDao) {
        this.loginUserDao = loginUserDao;
        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserDao.selectOne(LoginUser.builder().username(username).build());
        UserInfo userInfo = userInfoDao.selectOne(UserInfo.builder().loginUserId(loginUser.getId()).build());
        LoginUserBo loginUserBo = LoginUserBoMapper.MAPPER.loginUserBo(loginUser, userInfo);
        return loginUserBo;
    }
}
