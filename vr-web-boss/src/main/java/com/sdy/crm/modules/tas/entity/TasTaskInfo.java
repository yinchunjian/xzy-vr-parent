package com.sdy.crm.modules.tas.entity;

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
 * @Description: 任务信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Data
@TableName("tas_task_info")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tas_task_info对象", description="任务信息")
public class TasTaskInfo {
    
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
	/**任务类型 1、普通任务 2、跟进任务*/
	@Excel(name = "任务类型 1、普通任务 2、跟进任务", width = 15)
    @ApiModelProperty(value = "任务类型 1、普通任务 2、跟进任务")
	private Integer taskType;
	/**任务名称*/
	@Excel(name = "任务名称", width = 15)
    @ApiModelProperty(value = "任务名称")
	private String taskName;
	/**开始时间*/
	@Excel(name = "开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
	private Date beginTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
	private Date endTime;
	/**紧要程度 1、重要 2、 紧急 3、 普通  4、重要且紧急*/
	@Excel(name = "紧要程度 1、重要 2、 紧急 3、 普通  4、重要且紧急", width = 15)
    @ApiModelProperty(value = "紧要程度 1、重要 2、 紧急 3、 普通  4、重要且紧急")
	private Integer importanceLevel;
	/**关联业务 1、客户 2、联系人3、商机4、订单5、回款6、退款*/
	@Excel(name = "关联业务 1、客户 2、联系人3、商机4、订单5、回款6、退款", width = 15)
    @ApiModelProperty(value = "关联业务 1、客户 2、联系人3、商机4、订单5、回款6、退款")
	private Integer relatedObjectType;
	/**对象id*/
	@Excel(name = "对象id", width = 15)
    @ApiModelProperty(value = "对象id")
	private String objectId;
	/**对象名称*/
	@Excel(name = "对象名称", width = 15)
    @ApiModelProperty(value = "对象名称")
	private String objectName;
	/**任务描述*/
	@Excel(name = "任务描述", width = 15)
    @ApiModelProperty(value = "任务描述")
	private String taskDesc;
	/**跟进/跟进人员id*/
	@Excel(name = "跟进/跟进人员id", width = 15)
    @ApiModelProperty(value = "跟进/跟进人员id")
	private String chargeUserId;
	/**跟进/负责人员姓名*/
	@Excel(name = "跟进/负责人员姓名", width = 15)
    @ApiModelProperty(value = "跟进/负责人员姓名")
	private String chargeUserName;
	/**抄送/参与人员id 多个都好分隔*/
	@Excel(name = "抄送/参与人员id 多个都好分隔", width = 15)
    @ApiModelProperty(value = "抄送/参与人员id 多个都好分隔")
	private String participantUserId;
	/**参与人员名称多个都好分隔*/
	@Excel(name = "参与人员名称多个都好分隔", width = 15)
    @ApiModelProperty(value = "参与人员名称多个都好分隔")
	private String participantUserName;
	/**跟进时间*/
	@Excel(name = "跟进时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "跟进时间")
	private Date followTime;
	/**跟进内容*/
	@Excel(name = "跟进内容", width = 15)
    @ApiModelProperty(value = "跟进内容")
	private String followContent;
	/**签到地址*/
	@Excel(name = "签到地址", width = 15)
    @ApiModelProperty(value = "签到地址")
	private String signAddr;
	/**签到经度*/
	@Excel(name = "签到经度", width = 15)
    @ApiModelProperty(value = "签到经度")
	private java.math.BigDecimal signLongitude;
	/**签到维度*/
	@Excel(name = "签到维度", width = 15)
    @ApiModelProperty(value = "签到维度")
	private java.math.BigDecimal signLatitude;
	/**签到范围 单位(米)*/
	@Excel(name = "签到范围 单位(米)", width = 15)
    @ApiModelProperty(value = "签到范围 单位(米)")
	private Integer signScope;
	/**任务提醒 1、准时提醒 2、提前5分钟 3、提前15分钟4、提前30分钟
            5、提前1小时6、提前2小时*/
	@Excel(name = "任务提醒 1、准时提醒 2、提前5分钟 3、提前15分钟4、提前30分钟
            5、提前1小时6、提前2小时", width = 15)
    @ApiModelProperty(value = "任务提醒 1、准时提醒 2、提前5分钟 3、提前15分钟4、提前30分钟
            5、提前1小时6、提前2小时")
	private java.lang.String taskReminder;
	/**提醒方式 1、系统消息 2、短信 3、邮件*/
	@Excel(name = "提醒方式 1、系统消息 2、短信 3、邮件", width = 15)
    @ApiModelProperty(value = "提醒方式 1、系统消息 2、短信 3、邮件")
	private java.lang.Integer remindWay;
	/**任务标记*/
	@Excel(name = "任务标记", width = 15)
    @ApiModelProperty(value = "任务标记")
	private java.lang.String taskTag;
	/**任务状态 0、未处理 1、已处理*/
	@Excel(name = "任务状态 0、未处理 1、已处理", width = 15)
    @ApiModelProperty(value = "任务状态 0、未处理 1、已处理")
	private java.lang.Integer taskStatus;
}
