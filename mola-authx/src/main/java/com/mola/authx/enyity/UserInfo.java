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
 * 用户信息表
 *
 * @author hatim
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Table("t_auth_user_info")
public class UserInfo {
    @Id
    private Long id;

    /**
     * 登陆用户ID
     */
    @Length(min = 0, max = 32, message = "登陆用户ID 字段长度不正确")
    @NotNull(message = "登陆用户ID 不能为空")
    private Long loginUserId;

    /**
     * 用户姓名
     */
    @Length(min = 0, max = 25, message = "用户姓名 字段长度不正确")
    private String name;

    /**
     * 用户性别
     */
    @Length(min = 0, max = 2, message = "用户性别 字段长度不正确")
    private String sex;

    /**
     * 用户年龄
     */
    @Length(min = 0, max = 3, message = "用户年龄 字段长度不正确")
    private Integer age;

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
