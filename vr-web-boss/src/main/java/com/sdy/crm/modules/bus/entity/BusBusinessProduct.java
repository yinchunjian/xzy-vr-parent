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
 * @Description: 商机产品报价
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("bus_business_product")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="bus_business_product对象", description="商机产品报价")
public class BusBusinessProduct {
    
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
	/**商机id*/
	@Excel(name = "商机id", width = 15)
    @ApiModelProperty(value = "商机id")
	private String businessId;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String productName;
	/**产品编号*/
	@Excel(name = "产品编号", width = 15)
    @ApiModelProperty(value = "产品编号")
	private String productNo;
	/**标准单价 */
	@Excel(name = "标准单价 ", width = 15)
    @ApiModelProperty(value = "标准单价 ")
	private Integer unitPrice;
	/**销售单价*/
	@Excel(name = "销售单价", width = 15)
    @ApiModelProperty(value = "销售单价")
	private Integer salePrice;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
	private Integer saleNum;
	/**折扣*/
	@Excel(name = "折扣", width = 15)
    @ApiModelProperty(value = "折扣")
	private Integer discount;
	/**总价*/
	@Excel(name = "总价", width = 15)
    @ApiModelProperty(value = "总价")
	private java.math.BigDecimal totalPrice;
	/**产品规格*/
	@Excel(name = "产品规格", width = 15)
    @ApiModelProperty(value = "产品规格")
	private String productStandard;
	/**礼品*/
	@Excel(name = "礼品", width = 15)
    @ApiModelProperty(value = "礼品")
	private String gift;
}
