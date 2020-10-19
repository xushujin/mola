package com.mola.authx.impl;

import com.mola.authx.dao.LoginUserDao;
import com.mola.authx.dao.UserInfoDao;
import com.mola.authx.enyity.LoginUser;
import com.mola.authx.enyity.UserInfo;
import com.mola.authx.pojo.bo.LoginUserBo;
import com.mola.authx.pojo.mapper.LoginUserBoMapper;
import com.mola.common.consts.enu.TagEnum;
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
        LoginUser loginUser = loginUserDao.selectOne(LoginUser.builder().username(username).tag(TagEnum.normal.getCode()).build());
        UserInfo userInfo = userInfoDao.selectOne(UserInfo.builder().loginUserId(loginUser.getId()).build());
        LoginUserBo loginUserBo = LoginUserBoMapper.MAPPER.loginUserBo(loginUser, userInfo);
        return loginUserBo;
    }
}
