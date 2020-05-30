package com.sdy.crm.modules.bus.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 商机信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("bus_business_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="bus_business_info对象", description="商机信息")
public class BusBusinessInfo {
    
	/**主键ID 跟租户的设备id一致*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键ID 跟租户的设备id一致")
	private String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
	/**商机编号*/
	@Excel(name = "商机编号", width = 15)
    @ApiModelProperty(value = "商机编号")
	private String businessNo;
	/**商机标题*/
	@Excel(name = "商机标题", width = 15)
    @ApiModelProperty(value = "商机标题")
	private String businessTitle;
	/**客户id*/
	@Excel(name = "客户id", width = 15)
    @ApiModelProperty(value = "客户id")
	private String customerId;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String customerName;
	/**客户联系人*/
	@Excel(name = "客户联系人", width = 15)
    @ApiModelProperty(value = "客户联系人")
	private String contactId;
	/**客户联系人姓名*/
	@Excel(name = "客户联系人姓名", width = 15)
    @ApiModelProperty(value = "客户联系人姓名")
	private String contactName;
	/**预计销售金额*/
	@Excel(name = "预计销售金额", width = 15)
    @ApiModelProperty(value = "预计销售金额")
	private Integer salesAmount;
	/**预计签单日期*/
	@Excel(name = "预计签单日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "预计签单日期")
	private Date signatureDate;
	/**销售阶段1、初步洽淡 2、深入沟通 3、流失商机*/
	@Excel(name = "销售阶段1、初步洽淡 2、深入沟通 3、流失商机", width = 15)
    @ApiModelProperty(value = "销售阶段1、初步洽淡 2、深入沟通 3、流失商机")
	private Integer salesStage;
	/**商机类型 1、短缺商机*/
	@Excel(name = "商机类型 1、短缺商机", width = 15)
    @ApiModelProperty(value = "商机类型 1、短缺商机")
	private Integer businessType;
	/**成交机率 10%20%30%40%50%60%70%80%90%100%*/
	@Excel(name = "成交机率 10%20%30%40%50%60%70%80%90%100%", width = 15)
    @ApiModelProperty(value = "成交机率 10%20%30%40%50%60%70%80%90%100%")
	private Integer dealProbability;
	/**商机来源  1、电话营销2、主动来电3、客户介绍4、朋友介绍5、独立开发6、网络搜索*/
	@Excel(name = "商机来源  1、电话营销2、主动来电3、客户介绍4、朋友介绍5、独立开发6、网络搜索", width = 15)
    @ApiModelProperty(value = "商机来源  1、电话营销2、主动来电3、客户介绍4、朋友介绍5、独立开发6、网络搜索")
	private Integer businessSource;
	/**获取日期*/
	@Excel(name = "获取日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "获取日期")
	private Date getDate;
	/**归属人员id*/
	@Excel(name = "归属人员id", width = 15)
    @ApiModelProperty(value = "归属人员id")
	private String belongUserId;
	/**归属人员姓名*/
	@Excel(name = "归属人员姓名", width = 15)
    @ApiModelProperty(value = "归属人员姓名")
	private String belongUserName;
	/**归属部门id*/
	@Excel(name = "归属部门id", width = 15)
    @ApiModelProperty(value = "归属部门id")
	private String belongDepartId;
	/**归属部门名称*/
	@Excel(name = "归属部门名称", width = 15)
    @ApiModelProperty(value = "归属部门名称")
	private String belongDepartName;
	/**协作人员*/
	@Excel(name = "协作人员", width = 15)
    @ApiModelProperty(value = "协作人员")
	private String assistPersonIds;
	/**协作人员姓名*/
	@Excel(name = "协作人员姓名", width = 15)
    @ApiModelProperty(value = "协作人员姓名")
	private String assistPersonNames;
}
