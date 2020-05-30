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
 * @Description: 商机跟进
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("bus_business_follow")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="bus_business_follow对象", description="商机跟进")
public class BusBusinessFollow {
    
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
	/**商机名称*/
	@Excel(name = "商机名称", width = 15)
    @ApiModelProperty(value = "商机名称")
	private String businessName;
	/**跟进方式 1、到访2、电话3、微信4、短信5、邮件6、QQ7、其它*/
	@Excel(name = "跟进方式 1、到访2、电话3、微信4、短信5、邮件6、QQ7、其它", width = 15)
    @ApiModelProperty(value = "跟进方式 1、到访2、电话3、微信4、短信5、邮件6、QQ7、其它")
	private Integer followWay;
	/**跟进时间*/
	@Excel(name = "跟进时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "跟进时间")
	private Date followTime;
	/**跟进描述*/
	@Excel(name = "跟进描述", width = 15)
    @ApiModelProperty(value = "跟进描述")
	private Object followDesc;
	/**客户id*/
	@Excel(name = "客户id", width = 15)
    @ApiModelProperty(value = "客户id")
	private String customerId;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String customerName;
	/**商机阶段1、初步洽淡 2、深入沟通 3、流失商机*/
	@Excel(name = "商机阶段1、初步洽淡 2、深入沟通 3、流失商机", width = 15)
    @ApiModelProperty(value = "商机阶段1、初步洽淡 2、深入沟通 3、流失商机")
	private Integer salesStage;
	/**客户联系人*/
	@Excel(name = "客户联系人", width = 15)
    @ApiModelProperty(value = "客户联系人")
	private String contactId;
	/**客户联系人姓名*/
	@Excel(name = "客户联系人姓名", width = 15)
    @ApiModelProperty(value = "客户联系人姓名")
	private String contactName;
	/**跟进人员id*/
	@Excel(name = "跟进人员id", width = 15)
    @ApiModelProperty(value = "跟进人员id")
	private String followUserId;
	/**跟进人员姓名*/
	@Excel(name = "跟进人员姓名", width = 15)
    @ApiModelProperty(value = "跟进人员姓名")
	private String followUserName;
	/**抄送人员id 多个都好分隔*/
	@Excel(name = "抄送人员id 多个都好分隔", width = 15)
    @ApiModelProperty(value = "抄送人员id 多个都好分隔")
	private String copyUserId;
	/**抄送人员名称多个都好分隔*/
	@Excel(name = "抄送人员名称多个都好分隔", width = 15)
    @ApiModelProperty(value = "抄送人员名称多个都好分隔")
	private String copyUserName;
}
