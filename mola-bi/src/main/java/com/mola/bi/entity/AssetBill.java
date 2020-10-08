package com.mola.bi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.math.BigDecimal;
import java.util.Date;


/**
 * 资产账单表 实体类
 *
 * @author wisdom
 * @date 2019-03-26 16:08:34
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Table(name = "t_bill_asset_bill")
@ApiModel(value = "资产账单表实体")
public class AssetBill implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @ApiModelProperty(hidden = true)
    private String id;


    /**
     * 业主名称，多个业主以逗号分隔
     */
    @Length(min = 0, max = 128, message = "业主名称，多个业主以逗号分隔 字段长度不正确")
    @ApiModelProperty(value = "业主名称，多个业主以逗号分隔")
    private String residentName;

    /**
     * 电话，多个电话以逗号分隔
     */
    @Length(min = 0, max = 128, message = "电话，多个电话以逗号分隔 字段长度不正确")
    @ApiModelProperty(value = "电话，多个电话以逗号分隔")
    private String mobile;

    /**
     * 业主ID，多个业主以逗号分隔
     */
    @Length(min = 0, max = 32, message = "业主ID，多个业主以逗号分隔 字段长度不正确")
    @ApiModelProperty(value = "业主ID，多个业主以逗号分隔")
    private String residentId;

    /**
     * 房屋ID
     */
    @Length(min = 0, max = 32, message = "房屋ID 字段长度不正确")
    @ApiModelProperty(value = "房屋ID")
    private String houseId;

    /**
     * 房屋名称
     */
    @Length(min = 0, max = 32, message = "房屋名称 字段长度不正确")
    @ApiModelProperty(value = "房屋名称")
    private String houseName;

    /**
     * 单元ID
     */
    @Length(min = 0, max = 32, message = "单元ID 字段长度不正确")
    @ApiModelProperty(value = "单元ID")
    private String unitId;

    /**
     * 单元名称
     */
    @Length(min = 0, max = 32, message = "单元名称 字段长度不正确")
    @ApiModelProperty(value = "单元名称")
    private String unitName;

    /**
     * 楼栋ID
     */
    @Length(min = 0, max = 32, message = "楼栋ID 字段长度不正确")
    @ApiModelProperty(value = "楼栋ID")
    private String buildId;

    /**
     * 楼栋名称
     */
    @Length(min = 0, max = 32, message = "楼栋名称 字段长度不正确")
    @ApiModelProperty(value = "楼栋名称")
    private String buildName;

    /**
     * 小区ID
     */
    @Length(min = 0, max = 32, message = "小区ID 字段长度不正确")
    @ApiModelProperty(value = "小区ID")
    private String communityId;

    /**
     * 小区名称
     */
    @Length(min = 0, max = 32, message = "小区名称 字段长度不正确")
    @ApiModelProperty(value = "小区名称")
    private String communityName;

    /**
     * 车场ID
     */
    @Length(min = 0, max = 32, message = "车场ID 字段长度不正确")
    @ApiModelProperty(value = "车场ID")
    private String parkingLotId;

    /**
     * 车场名称
     */
    @Length(min = 0, max = 32, message = "车场名称 字段长度不正确")
    @ApiModelProperty(value = "车场名称")
    private String parkingLotName;

    /**
     * 资产ID (房屋id 或车辆id 或车位id 等)
     */
    @Length(min = 0, max = 32, message = "资产ID (房屋id 或车辆id 或车位id 等) 字段长度不正确")
    @NotNull(message = "资产ID (房屋id 或车辆id 或车位id 等) 不能为空")
    @ApiModelProperty(value = "资产ID (房屋id 或车辆id 或车位id 等)")
    private String assetId;

    /**
     * 资产名称 (房屋 或车辆 或车位 等)
     */
    @Length(min = 0, max = 32, message = "资产名称 (房屋 或车辆 或车位 等) 字段长度不正确")
    @NotNull(message = "资产名称 (房屋 或车辆 或车位 等) 不能为空")
    @ApiModelProperty(value = "资产名称 (房屋 或车辆 或车位 等)")
    private String assetName;

    /**
     * 账单类型: assetBill 资产账单 | tempPay 临时缴费账单
     */
    @Length(min = 0, max = 32, message = "账单类型: assetBill 资产账单 | tempPay 临时缴费账单  字段长度不正确")
    @NotNull(message = "账单类型: assetBill 资产账单 | tempPay 临时缴费账单  不能为空")
    @ApiModelProperty(value = "账单类型: assetBill 资产账单 | tempPay 临时缴费账单 ")
    private String billType;

    /**
     * 费用类型(物业服务费 - propertyServiceFee、车位费 - parkingSpaceFee、水费 - waterFee、电费 - electricFee)
     */
    @Length(min = 0, max = 32, message = "费用类型(物业服务费 - propertyServiceFee、车位费 - parkingSpaceFee、水费 - waterFee、电费 - electricFee) 字段长度不正确")
    @NotNull(message = "费用类型(物业服务费 - propertyServiceFee、车位费 - parkingSpaceFee、水费 - waterFee、电费 - electricFee) 不能为空")
    @ApiModelProperty(value = "费用类型(物业服务费 - propertyServiceFee、车位费 - parkingSpaceFee、水费 - waterFee、电费 - electricFee)")
    private String feeType;

    /**
     * 收费主体(房屋 - house、车辆 - car、车位 - parkingSpace、水表 - waterMeter、电表 - electricMeter)
     */
    @Length(min = 0, max = 32, message = "收费主体(房屋 - house、车辆 - car、车位 - parkingSpace、水表 - waterMeter、电表 - electricMeter) 字段长度不正确")
    @NotNull(message = "收费主体(房屋 - house、车辆 - car、车位 - parkingSpace、水表 - waterMeter、电表 - electricMeter) 不能为空")
    @ApiModelProperty(value = "收费主体(房屋 - house、车辆 - car、车位 - parkingSpace、水表 - waterMeter、电表 - electricMeter)")
    private String chargeSubject;

    /**
     * 收费标准id
     */
    @Length(min = 0, max = 32, message = "收费标准id 字段长度不正确")
    @NotNull(message = "收费标准id 不能为空")
    @ApiModelProperty(value = "收费标准id")
    private String chargeStandardId;

    /**
     * 唯一标识位
     */
    @Length(min = 0, max = 32, message = "唯一标识位 字段长度不正确")
    @NotNull(message = "唯一标识位 不能为空")
    @ApiModelProperty(value = "唯一标识位")
    private String identification;

    /**
     * 费用名称
     */
    @Length(min = 0, max = 64, message = "费用名称 字段长度不正确")
    @NotNull(message = "费用名称 不能为空")
    @ApiModelProperty(value = "费用名称")
    private String feeName;

    /**
     * 数量或读数
     */
    @ApiModelProperty(value = "数量或读数")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    /**
     * 单位
     */
    @Length(min = 0, max = 32, message = "单位 字段长度不正确")
    @ApiModelProperty(value = "单位")
    private String unit;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal receivableFee;

    /**
     * 减免金额
     */
    @ApiModelProperty(value = "减免金额")
    private BigDecimal discountFee;

    /**
     * 已收金额
     */
    @ApiModelProperty(value = "已收金额")
    private BigDecimal receivedFee;

    /**
     * 欠费金额
     */
    @ApiModelProperty(value = "欠费金额")
    private BigDecimal arrearsFee;

    /**
     * 欠费最后通知时间
     */
    @ApiModelProperty(value = "欠费最后通知时间")
    private Date arrearsNoticeAt;

    /**
     * 欠费通知次数
     */
    @ApiModelProperty(value = "欠费通知次数")
    private Integer arrearsNoticeNum;

    /**
     * 账期
     */
    @Length(min = 0, max = 10, message = "账期 字段长度不正确")
    @NotNull(message = "账期 不能为空")
    @ApiModelProperty(value = "账期")
    private String chargeYearMonth;

    /**
     * 公式
     */
    @Length(min = 0, max = 64, message = "公式")
    @ApiModelProperty(value = "公式")
    private String formula;

    /**
     * 转换后公式
     */
    @Length(min = 0, max = 64, message = "转换后公式")
    @ApiModelProperty(value = "转换后公式")
    private String convertFormula;

    /**
     * 审核状态：audited 已审核 | wait_audit 待审核
     */
    @Length(min = 0, max = 32, message = "审核状态：audited 已审核 | wait_audit 待审核")
    @ApiModelProperty(value = "审核状态：audited 已审核 | wait_audit 待审核")
    private String auditStatus;

    /**
     * 冻结状态 0正常 1冻结
     */
    @ApiModelProperty(value = "冻结状态 0正常 1冻结")
    private Boolean freeze;

    /**
     * 是否是预付账单
     */
    @ApiModelProperty(value = "是否是预付账单（默认为0，即非预付账单 1预付账单）")
    private Boolean advance;

    /**
     * 删除标记（默认为0，即正常状态 1删除）
     */
    @ApiModelProperty(value = "删除标记（默认为0，即正常状态 1删除）")
    private Boolean deleted;

    /**
     * 备注
     */
    @Length(min = 0, max = 64, message = "备注 字段长度不正确")
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    /**
     * 创建人
     */
    @Length(min = 0, max = 32, message = "创建人 字段长度不正确")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateAt;

    /**
     * 更新人
     */
    @Length(min = 0, max = 32, message = "更新人 字段长度不正确")
    @ApiModelProperty(value = "更新人")
    private String updateBy;


}