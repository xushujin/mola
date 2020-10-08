package com.mola.auth.dao;


import com.mola.auth.enyity.LoginUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 登陆用户DAO
 *
 * @author hatim
 */
@Repository
public interface LoginUserDao extends Mapper<LoginUser>, InsertListMapper<LoginUser> {
}
