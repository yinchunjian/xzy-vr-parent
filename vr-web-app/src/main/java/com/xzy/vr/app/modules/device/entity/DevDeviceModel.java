package com.xzy.vr.app.modules.device.entity;

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
 * @Description: 设备型号
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Data
@TableName("dev_device_model")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="dev_device_model对象", description="设备型号")
public class DevDeviceModel {
    
	/**主键ID*/
	@Excel(name = "主键ID", width = 15)
    @ApiModelProperty(value = "主键ID")
	private String invoiceId;
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
	/**设备品牌id*/
	@Excel(name = "设备品牌id", width = 15)
    @ApiModelProperty(value = "设备品牌id")
	private String brandId;
	/**设备品牌名称*/
	@Excel(name = "设备品牌名称", width = 15)
    @ApiModelProperty(value = "设备品牌名称")
	private String brandName;
	/**型号编码*/
	@Excel(name = "型号编码", width = 15)
    @ApiModelProperty(value = "型号编码")
	private String modelNo;
	/**型号名称*/
	@Excel(name = "型号名称", width = 15)
    @ApiModelProperty(value = "型号名称")
	private String modelName;
}
