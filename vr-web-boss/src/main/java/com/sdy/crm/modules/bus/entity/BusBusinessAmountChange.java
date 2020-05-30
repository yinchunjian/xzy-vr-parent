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
 * @Description: 商机金额变更记录
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("bus_business_amount_change")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="bus_business_amount_change对象", description="商机金额变更记录")
public class BusBusinessAmountChange {
    
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
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
	/**操作类型 1、新增  2、编辑*/
	@Excel(name = "操作类型 1、新增  2、编辑", width = 15)
    @ApiModelProperty(value = "操作类型 1、新增  2、编辑")
	private Integer operationType;
	/**商机id*/
	@Excel(name = "商机id", width = 15)
    @ApiModelProperty(value = "商机id")
	private String businessId;
	/**商机名称*/
	@Excel(name = "商机名称", width = 15)
    @ApiModelProperty(value = "商机名称")
	private String businessName;
	/**字段名称*/
	@Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
	private String fieldName;
	/**旧字段值*/
	@Excel(name = "旧字段值", width = 15)
    @ApiModelProperty(value = "旧字段值")
	private String oldValue;
	/**新字段值*/
	@Excel(name = "新字段值", width = 15)
    @ApiModelProperty(value = "新字段值")
	private String newValue;
}
