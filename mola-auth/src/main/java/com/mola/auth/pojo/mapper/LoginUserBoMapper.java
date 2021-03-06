package com.mola.auth.pojo.mapper;

import com.mola.auth.enyity.LoginUser;
import com.mola.auth.enyity.UserInfo;
import com.mola.auth.pojo.bo.LoginUserBo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * LoginUserBo 转换器
 *
 * @author hatim
 */
@Mapper(builder = @Builder(disableBuilder = true))
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
