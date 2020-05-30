package com.sdy.crm.modules.flowable.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdy.crm.modules.flowable.entity.ActReProcdef;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sdy.crm.modules.flowable.model.ProcessModel;
import com.sdy.crm.modules.flowable.vo.ProcessVO;

import java.util.List;

/**
 * @Description: procdef
 * @Author: jeecg-boot
 * @Date:   2020-05-26
 * @Version: V1.0
 */
public interface IActReProcdefService extends IService<ActReProcdef> {

    IPage<ProcessModel> getProcess(Page<ProcessModel> page, ProcessVO processVO);

    IPage<ProcessModel> getlcglxxByKey(Page<ProcessModel> page, ProcessVO processVO);

    boolean updateProcessXmlString(ProcessVO processVO);

    boolean updateActReProcdef(ActReProcdef actReProcdef);
}
