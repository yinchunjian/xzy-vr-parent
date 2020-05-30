package com.sdy.crm.modules.flowable.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查看流程信息返回")
public class ProcessModel {

    @ApiModelProperty("流程id")
    private String id;
    @ApiModelProperty("发布状态（1发布 2未发布）")
    private String status;
    @ApiModelProperty("流程名称")
    private String name;
    @ApiModelProperty("流程key")
    private String key;
    @ApiModelProperty("版本号")
    private String version;
    @ApiModelProperty("xml信息")
    private String xmlString;
    @ApiModelProperty("发布id")
    private String deploymentId;
    @ApiModelProperty("是否是主版本（0非 1是）")
    private int derivedVersion;
    @ApiModelProperty("流程描述")
    private String description;

}
