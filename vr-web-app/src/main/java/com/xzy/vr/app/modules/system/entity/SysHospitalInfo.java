package com.xzy.vr.app.modules.system.entity;

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
 * @Description: sys_hospital_info
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Data
@TableName("sys_hospital_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="sys_hospital_info对象", description="sys_hospital_info")
public class SysHospitalInfo {
    
	/**主键ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键ID")
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
	/**医院编码*/
	@Excel(name = "医院编码", width = 15)
    @ApiModelProperty(value = "医院编码")
	private String hospitalNo;
	/**医院名称*/
	@Excel(name = "医院名称", width = 15)
    @ApiModelProperty(value = "医院名称")
	private String hospitalName;
	/**医院照片*/
	@Excel(name = "医院照片", width = 15)
    @ApiModelProperty(value = "医院照片")
	private String hospitalLogo;
	/**省份ID*/
	@Excel(name = "省份ID", width = 15)
    @ApiModelProperty(value = "省份ID")
	private Integer provinceId;
	/**城市ID*/
	@Excel(name = "城市ID", width = 15)
    @ApiModelProperty(value = "城市ID")
	private Integer cityId;
	/**区县标识ID*/
	@Excel(name = "区县标识ID", width = 15)
    @ApiModelProperty(value = "区县标识ID")
	private Integer countyId;
	/**医院地址*/
	@Excel(name = "医院地址", width = 15)
    @ApiModelProperty(value = "医院地址")
	private String hospitalAddress;
	/**联系人*/
	@Excel(name = "联系人", width = 15)
    @ApiModelProperty(value = "联系人")
	private String contactPerson;
	/**联系人手机号*/
	@Excel(name = "联系人手机号", width = 15)
    @ApiModelProperty(value = "联系人手机号")
	private String contactMobile;
}
