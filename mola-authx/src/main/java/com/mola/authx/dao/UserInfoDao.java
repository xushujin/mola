package com.mola.authx.dao;


import com.mola.authx.enyity.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 登陆用户DAO
 *
 * @author hatim
 */
@Repository
public interface UserInfoDao extends Mapper<UserInfo>, InsertListMapper<UserInfo> {
}
