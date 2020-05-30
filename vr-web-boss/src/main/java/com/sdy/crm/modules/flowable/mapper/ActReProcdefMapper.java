package com.sdy.crm.modules.flowable.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdy.crm.modules.flowable.entity.ActReProcdef;
import com.sdy.crm.modules.flowable.model.ProcessModel;
import com.sdy.crm.modules.flowable.vo.ProcessVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: procdef
 * @Author: jeecg-boot
 * @Date:   2020-05-26
 * @Version: V1.0
 */
public interface ActReProcdefMapper extends BaseMapper<ActReProcdef> {

    IPage<ProcessModel> getProcess(Page<ProcessModel> page, @Param("processVO") ProcessVO processVO);

    IPage<ProcessModel> getlcglxxByKey(Page<ProcessModel> page,@Param("processVO")  ProcessVO processVO);

    boolean updateProcessXmlString(@Param("processVO") ProcessVO processVO);

    boolean  updateActReProcdef(@Param("actReProcdef") ActReProcdef actReProcdef);
}
