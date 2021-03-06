package com.mola.authx.pojo.mapper;

import com.mola.authx.enyity.LoginUser;
import com.mola.authx.enyity.UserInfo;
import com.mola.authx.pojo.bo.LoginUserBo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * LoginUserBo 转换器
 *
 * @author hatim
 */
@Mapper
public interface LoginUserBoMapper {
    LoginUserBoMapper MAPPER = Mappers.getMapper(LoginUserBoMapper.class);

    /**
     * loginUserBo 构建
     *
     * @param loginUser
     * @param userInfo
     * @return
     */
    @Mapping(source = "loginUser.id", target = "id")
    LoginUserBo loginUserBo(LoginUser loginUser, UserInfo userInfo);
}
