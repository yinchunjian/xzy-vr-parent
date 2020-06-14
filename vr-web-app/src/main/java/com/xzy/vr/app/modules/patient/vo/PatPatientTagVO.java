package com.xzy.vr.app.modules.patient.vo;

import com.xzy.vr.app.modules.system.entity.SysDictItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.List;

@Data
public class PatPatientTagVO {

    /**患者id*/
    @NonNull
    @ApiModelProperty(value = "患者id")
    private java.lang.String userId;
    /**标签id*/
    @NonNull
    @ApiModelProperty(value = "标签id")
    private java.lang.String tagId;
    /**标签名称*/
    @NonNull
    @ApiModelProperty(value = "标签名称")
    private java.lang.String tagName;

}
