package com.mola.authx.enyity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * 员工表 实体类
 *
 * @author wisdom
 * @date 2019-02-27 16:57:57
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Table(name = "t_auth_employee")
@ApiModel(value = "员工表实体")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 人员ID
     */
    @Id
    @ApiModelProperty(hidden = true)
    private String id;


    /**
     * 所属机构ID
     */
    @Length(min = 0, max = 32, message = "所属机构ID 字段长度不正确")
    @NotNull(message = "所属机构ID 不能为空")
    @ApiModelProperty(value = "所属机构ID")
    private String organId;

    /**
     * 岗位ID
     */
    @Length(min = 0, max = 32, message = "岗位ID 字段长度不正确")
    @NotNull(message = "岗位ID 不能为空")
    @ApiModelProperty(value = "岗位ID")
    private String positionId;

    /**
     * 姓名
     */
    @Length(min = 0, max = 32, message = "姓名 字段长度不正确")
    @NotNull(message = "姓名 不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别(male:男,female:女)
     */
    @Length(min = 0, max = 10, message = "性别(male:男,female:女) 字段长度不正确")
    @ApiModelProperty(value = "性别(male:男,female:女)")
    private String sex;

    /**
     * 生日
     */
    @Length(min = 0, max = 10, message = "生日 字段长度不正确")
    @ApiModelProperty(value = "生日")
    private String birthday;

    /**
     * 手机号
     */
    @Length(min = 0, max = 32, message = "手机号 字段长度不正确")
    @NotNull(message = "手机号 不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 登陆账号
     */
    @Length(min = 0, max = 32, message = "登陆账号 字段长度不正确")
    @ApiModelProperty(value = "登陆账号")
    private String account;

    /**
     * 登陆密码
     */
    @Length(min = 0, max = 80, message = "登陆密码 字段长度不正确")
    @NotNull(message = "登陆密码 不能为空")
    @ApiModelProperty(value = "登陆密码")
    private String password;

    /**
     * 身份证号
     */
    @Length(min = 0, max = 32, message = "身份证号 字段长度不正确")
    @ApiModelProperty(value = "身份证号")
    private String identityCard;

    /**
     * 工号
     */
    @Length(min = 0, max = 32, message = "工号 字段长度不正确")
    @ApiModelProperty(value = "工号")
    private String employeeNumber;

    /**
     * 证件照
     */
    @Length(min = 0, max = 1024, message = "证件照 字段长度不正确")
    @ApiModelProperty(value = "证件照")
    private String imgUrl;

    /**
     * 在职状态(JobStatusEnum)
     */
    @NotNull(message = "在职状态(JobStatusEnum) 不能为空")
    @ApiModelProperty(value = "在职状态(JobStatusEnum)")
    private String onJob;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间")
    private Date hireDate;

    /**
     * 离职时间
     */
    @ApiModelProperty(value = "离职时间")
    private Date leaveDate;

    /**
     * 是否允许登陆（0：不允许，1：允许）
     */
    @NotNull(message = "是否允许登陆（0：不允许，1：允许） 不能为空")
    @ApiModelProperty(value = "是否允许登陆（0：不允许，1：允许）")
    private Integer loginPermit;

    /**
     * 备注
     */
    @Length(min = 0, max = 1024, message = "备注 字段长度不正确")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 登陆次数
     */
    @ApiModelProperty(value = "登陆次数")
    private Integer loginCount;

    /**
     * 最近登陆时间
     */
    @ApiModelProperty(value = "最近登陆时间")
    private Date loginTime;

    /**
     * 是否绑定微信(1=是，0=否)
     */
    @ApiModelProperty(value = "是否绑定微信(1=是，0=否)")
    private Integer wechatBind;

    /**
     * 企业号
     */
    @Length(min = 0, max = 10, message = "企业号 字段长度不正确")
    @ApiModelProperty(value = "企业号")
    private String entCode;

    /**
     * 企业名称
     */
    @Length(min = 0, max = 100, message = "企业名称 字段长度不正确")
    @ApiModelProperty(value = "企业名称")
    private String entName;

    /**
     * 所属公司ID
     */
    @Length(min = 0, max = 10, message = "所属公司ID 字段长度不正确")
    @ApiModelProperty(value = "所属公司ID")
    private String companyId;

    /**
     * 删除标记(1=已删除，0=正常)
     */
    @ApiModelProperty(value = "删除标记(1=已删除，0=正常)")
    private Integer deleted;

    /**
     * 是否公开(false不公开)
     */
    @ApiModelProperty(value = "是否公开(false不公开)")
    private Boolean open;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 创建人
     */
    @Length(min = 0, max = 32, message = "创建人 字段长度不正确")
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 更新人
     */
    @Length(min = 0, max = 32, message = "更新人 字段长度不正确")
    @ApiModelProperty(value = "更新人")
    private String updatedBy;


}