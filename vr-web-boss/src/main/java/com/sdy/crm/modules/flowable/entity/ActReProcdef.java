package com.sdy.crm.modules.flowable.entity;

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
 * @Description: procdef
 * @Author: jeecg-boot
 * @Date:   2020-05-26
 * @Version: V1.0
 */
@Data
@TableName("ACT_RE_PROCDEF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ACT_RE_PROCDEF对象", description="procdef")
public class ActReProcdef {
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
	private String id;
	/**rev*/
	@Excel(name = "rev", width = 15)
    @ApiModelProperty(value = "rev")
	private Integer rev;
	/**category*/
	@Excel(name = "category", width = 15)
    @ApiModelProperty(value = "category")
	private String category;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
	private String name;
	/**key*/
	@Excel(name = "key", width = 15)
    @ApiModelProperty(value = "key")
	private String key;
	/**version*/
	@Excel(name = "version", width = 15)
    @ApiModelProperty(value = "version")
	private Integer version;
	/**deploymentId*/
	@Excel(name = "deploymentId", width = 15)
    @ApiModelProperty(value = "deploymentId")
	private String deploymentId;
	/**resourceName*/
	@Excel(name = "resourceName", width = 15)
    @ApiModelProperty(value = "resourceName")
	private String resourceName;
	/**dgrmResourceName*/
	@Excel(name = "dgrmResourceName", width = 15)
    @ApiModelProperty(value = "dgrmResourceName")
	private String dgrmResourceName;
	/**description*/
	@Excel(name = "description", width = 15)
    @ApiModelProperty(value = "description")
	private String description;
	/**hasStartFormKey*/
	@Excel(name = "hasStartFormKey", width = 15)
    @ApiModelProperty(value = "hasStartFormKey")
	private Integer hasStartFormKey;
	/**hasGraphicalNotation*/
	@Excel(name = "hasGraphicalNotation", width = 15)
    @ApiModelProperty(value = "hasGraphicalNotation")
	private Integer hasGraphicalNotation;
	/**suspensionState*/
	@Excel(name = "suspensionState", width = 15)
    @ApiModelProperty(value = "suspensionState")
	private Integer suspensionState;
	/**tenantId*/
	@Excel(name = "tenantId", width = 15)
    @ApiModelProperty(value = "tenantId")
	private String tenantId;
	/**engineVersion*/
	@Excel(name = "engineVersion", width = 15)
    @ApiModelProperty(value = "engineVersion")
	private String engineVersion;
	/**derivedFrom*/
	@Excel(name = "derivedFrom", width = 15)
    @ApiModelProperty(value = "derivedFrom")
	private String derivedFrom;
	/**derivedFromRoot*/
	@Excel(name = "derivedFromRoot", width = 15)
    @ApiModelProperty(value = "derivedFromRoot")
	private String derivedFromRoot;
	/**derivedVersion*/
	@Excel(name = "derivedVersion", width = 15)
    @ApiModelProperty(value = "derivedVersion")
	private Integer derivedVersion;
}
