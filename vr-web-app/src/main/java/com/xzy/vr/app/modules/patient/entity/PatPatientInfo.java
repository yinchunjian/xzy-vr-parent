package com.xzy.vr.app.modules.patient.entity;

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
 * @Description: 患者信息
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Data
@TableName("pat_patient_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="pat_patient_info对象", description="患者信息")
public class PatPatientInfo {
    
	/**主键ID*/
	@Excel(name = "主键ID", width = 15)
    @ApiModelProperty(value = "主键ID")
	private String userId;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String creater;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String editor;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date editTime;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15)
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
	private Integer delFlag;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**患者编号*/
	@Excel(name = "患者编号", width = 15)
    @ApiModelProperty(value = "患者编号")
	private String patientNo;
	/**患者姓名*/
	@Excel(name = "患者姓名", width = 15)
    @ApiModelProperty(value = "患者姓名")
	private String patientName;
	/**医保卡号*/
	@Excel(name = "医保卡号", width = 15)
    @ApiModelProperty(value = "医保卡号")
	private String hisCardNo;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
	private String idCardNo;
	/**医院id*/
	@Excel(name = "医院id", width = 15)
    @ApiModelProperty(value = "医院id")
	private String hospitalId;
	/**医院名称*/
	@Excel(name = "医院名称", width = 15)
    @ApiModelProperty(value = "医院名称")
	private String hospitalName;
	/**婚姻状况 1、已婚 0、未婚*/
	@Excel(name = "婚姻状况 1、已婚 0、未婚", width = 15)
    @ApiModelProperty(value = "婚姻状况 1、已婚 0、未婚")
	private Integer maritalStatus;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
	private String mobileNo;
	/**文化程度  1、高中以下2、大专3、本科 4、硕士5、博士6、博士后7、教授*/
	@Excel(name = "文化程度  1、高中以下2、大专3、本科 4、硕士5、博士6、博士后7、教授", width = 15)
    @ApiModelProperty(value = "文化程度  1、高中以下2、大专3、本科 4、硕士5、博士6、博士后7、教授")
	private Integer cultureStandard;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
	private Integer sex;
	/**行业*/
	@Excel(name = "行业", width = 15)
    @ApiModelProperty(value = "行业")
	private Integer industry;
	/**职业*/
	@Excel(name = "职业", width = 15)
    @ApiModelProperty(value = "职业")
	private Integer profession;
	/**失眠持续时间 1、3个月内*/
	@Excel(name = "失眠持续时间 1、3个月内", width = 15)
    @ApiModelProperty(value = "失眠持续时间 1、3个月内")
	private Integer insomniaTime;
	/**药物服用*/
	@Excel(name = "药物服用", width = 15)
    @ApiModelProperty(value = "药物服用")
	private Integer takDrug;
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
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
	private String address;
	/**出生年月*/
	@Excel(name = "出生年月", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生年月")
	private Date birthday;
	/**紧急联系人*/
	@Excel(name = "紧急联系人", width = 15)
    @ApiModelProperty(value = "紧急联系人")
	private String contactName;
	/**紧急联系人电话*/
	@Excel(name = "紧急联系人电话", width = 15)
    @ApiModelProperty(value = "紧急联系人电话")
	private String contactPhone;
}
