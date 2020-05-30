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
 * @Description: 客户联系人
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("cus_customer_contact")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cus_customer_contact对象", description="客户联系人")
public class CusCustomerContact {
    
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
	/**客户id*/
	@Excel(name = "客户id", width = 15)
    @ApiModelProperty(value = "客户id")
	private String customerId;
	/**联系人姓名*/
	@Excel(name = "联系人姓名", width = 15)
    @ApiModelProperty(value = "联系人姓名")
	private String contactName;
	/**性别 1、男 0、女 2、未知*/
	@Excel(name = "性别 1、男 0、女 2、未知", width = 15)
    @ApiModelProperty(value = "性别 1、男 0、女 2、未知")
	private Integer sex;
	/**角色 1、普通人2、决策人3、分项决策人4、商务决策5、财务决策6、使用人7、意见影响人*/
	@Excel(name = "角色 1、普通人2、决策人3、分项决策人4、商务决策5、财务决策6、使用人7、意见影响人", width = 15)
    @ApiModelProperty(value = "角色 1、普通人2、决策人3、分项决策人4、商务决策5、财务决策6、使用人7、意见影响人")
	private Integer contactRole;
	/**生日*/
	@Excel(name = "生日", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
	private Date birthday;
	/**部门职务*/
	@Excel(name = "部门职务", width = 15)
    @ApiModelProperty(value = "部门职务")
	private String position;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String mobileNo;
	/**固定电话*/
	@Excel(name = "固定电话", width = 15)
    @ApiModelProperty(value = "固定电话")
	private String telephone;
	/**传真号码*/
	@Excel(name = "传真号码", width = 15)
    @ApiModelProperty(value = "传真号码")
	private String faxNumber;
	/**电子邮箱*/
	@Excel(name = "电子邮箱", width = 15)
    @ApiModelProperty(value = "电子邮箱")
	private String emailAddress;
	/**微信账号*/
	@Excel(name = "微信账号", width = 15)
    @ApiModelProperty(value = "微信账号")
	private String wechatAccount;
	/**QQ号码*/
	@Excel(name = "QQ号码", width = 15)
    @ApiModelProperty(value = "QQ号码")
	private String qqNumber;
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
	/**详细地址*/
	@Excel(name = "详细地址", width = 15)
    @ApiModelProperty(value = "详细地址")
	private String detailAddress;
	/**是否首要联系人*/
	@Excel(name = "是否首要联系人", width = 15)
    @ApiModelProperty(value = "是否首要联系人")
	private Integer isPrimary;
}
