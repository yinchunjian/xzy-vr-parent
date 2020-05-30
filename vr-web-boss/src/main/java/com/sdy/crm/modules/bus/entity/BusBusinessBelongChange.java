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
 * @Description: 商机归属人员变更
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("bus_business_belong_change")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="bus_business_belong_change对象", description="商机归属人员变更")
public class BusBusinessBelongChange {
    
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
	/**商机id*/
	@Excel(name = "商机id", width = 15)
    @ApiModelProperty(value = "商机id")
	private String businessId;
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
}
