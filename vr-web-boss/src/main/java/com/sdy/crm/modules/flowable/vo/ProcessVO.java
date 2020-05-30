package com.sdy.crm.modules.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("添加流程管理")
public class ProcessVO {

    @ApiModelProperty("流程key")
    private  String key;

    @ApiModelProperty("流程名称")
    private  String name;

    @ApiModelProperty("流程xmlString")
    private  String xmlString;


    @ApiModelProperty("流程状态")
    private  String status;

    @ApiModelProperty("流程部署的id")
    private  String deploymentId;

}
