package com.sdy.crm.modules.cus.entity;

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
 * @Description: 客户信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("cus_customer_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cus_customer_info对象", description="客户信息")
public class CusCustomerInfo {
    
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
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String customerNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String customerName;
	/**助记名称*/
	@Excel(name = "助记名称", width = 15)
    @ApiModelProperty(value = "助记名称")
	private String mnemonicName;
	/**所属行业id*/
	@Excel(name = "所属行业id", width = 15)
    @ApiModelProperty(value = "所属行业id")
	private String industryId;
	/**所属行业名称*/
	@Excel(name = "所属行业名称", width = 15)
    @ApiModelProperty(value = "所属行业名称")
	private String industryName;
	/**客户类型 1、企业 2、个人  3、代理商*/
	@Excel(name = "客户类型 1、企业 2、个人  3、代理商", width = 15)
    @ApiModelProperty(value = "客户类型 1、企业 2、个人  3、代理商")
	private Integer customerType;
	/**客户星级 1-5星级*/
	@Excel(name = "客户星级 1-5星级", width = 15)
    @ApiModelProperty(value = "客户星级 1-5星级")
	private Integer customerStar;
	/**客户来源1、 电话营销 2、主动来电3、客户介绍4、朋友介绍 5、独立开发
            6、网络搜索*/
	@Excel(name = "客户来源1、 电话营销 2、主动来电3、客户介绍4、朋友介绍 5、独立开发
            6、网络搜索", width = 15)
    @ApiModelProperty(value = "客户来源1、 电话营销 2、主动来电3、客户介绍4、朋友介绍 5、独立开发
            6、网络搜索")
	private java.lang.Integer customerSource;
	/**归属人员id*/
	@Excel(name = "归属人员id", width = 15)
    @ApiModelProperty(value = "归属人员id")
	private java.lang.String belongUserId;
	/**归属人员姓名*/
	@Excel(name = "归属人员姓名", width = 15)
    @ApiModelProperty(value = "归属人员姓名")
	private java.lang.String belongUserName;
	/**省份ID*/
	@Excel(name = "省份ID", width = 15)
    @ApiModelProperty(value = "省份ID")
	private java.lang.Integer provinceId;
	/**城市ID*/
	@Excel(name = "城市ID", width = 15)
    @ApiModelProperty(value = "城市ID")
	private java.lang.Integer cityId;
	/**区县标识ID*/
	@Excel(name = "区县标识ID", width = 15)
    @ApiModelProperty(value = "区县标识ID")
	private java.lang.Integer countyId;
	/**详细地址*/
	@Excel(name = "详细地址", width = 15)
    @ApiModelProperty(value = "详细地址")
	private java.lang.String detailAddress;
	/**企业规模 1、10人以内2、10-20人 3、21人-50人4、51人-200人5、201人-500人6、500人以上*/
	@Excel(name = "企业规模 1、10人以内2、10-20人 3、21人-50人4、51人-200人5、201人-500人6、500人以上", width = 15)
    @ApiModelProperty(value = "企业规模 1、10人以内2、10-20人 3、21人-50人4、51人-200人5、201人-500人6、500人以上")
	private java.lang.Integer customerScale;
	/**上级客户*/
	@Excel(name = "上级客户", width = 15)
    @ApiModelProperty(value = "上级客户")
	private java.lang.String parentId;
	/**上级客户名称*/
	@Excel(name = "上级客户名称", width = 15)
    @ApiModelProperty(value = "上级客户名称")
	private java.lang.String parentName;
}
