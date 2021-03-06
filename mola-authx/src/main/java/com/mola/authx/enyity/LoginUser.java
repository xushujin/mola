package com.mola.authx.enyity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 登陆用户表
 *
 * @author hatim
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Table("t_auth_login_user")
public class LoginUser {
    @Id
    private Long id;

    /**
     * 登陆用户
     */
    @Length(min = 0, max = 32, message = "登陆用户 字段长度不正确")
    private String username;

    /**
     * 登陆密码
     */
    @Length(min = 0, max = 128, message = "登陆密码 字段长度不正确")
    @NotNull(message = "登陆密码 不能为空")
    private String password;

    /**
     * 标签（0:正常,1:删除,9:异常数据）
     */
    @Length(min = 0, max = 2, message = "标签 字段长度不正确")
    @NotNull(message = "标签 不能为空")
    private Integer tag;

    /**
     * 备注
     */
    @Length(min = 0, max = 256, message = "备注 字段长度不正确")
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    /**
     * 创建人
     */
    @Length(min = 0, max = 32, message = "创建人 字段长度不正确")
    private String createdBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;

    /**
     * 更新人
     */
    @Length(min = 0, max = 32, message = "更新人 字段长度不正确")
    private String updatedBy;
}
