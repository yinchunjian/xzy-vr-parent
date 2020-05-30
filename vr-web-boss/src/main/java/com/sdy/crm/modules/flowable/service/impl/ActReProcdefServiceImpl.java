package com.sdy.crm.modules.flowable.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdy.crm.modules.flowable.entity.ActReProcdef;
import com.sdy.crm.modules.flowable.mapper.ActReProcdefMapper;
import com.sdy.crm.modules.flowable.model.ProcessModel;
import com.sdy.crm.modules.flowable.service.IActReProcdefService;
import com.sdy.crm.modules.flowable.vo.ProcessVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: procdef
 * @Author: jeecg-boot
 * @Date:   2020-05-26
 * @Version: V1.0
 */
@Service
public class ActReProcdefServiceImpl extends ServiceImpl<ActReProcdefMapper, ActReProcdef> implements IActReProcdefService {
    @Override
    public IPage<ProcessModel> getProcess(Page<ProcessModel> page, ProcessVO processVO) {
        return baseMapper.getProcess(page,processVO);
    }
    @Override
    public IPage<ProcessModel> getlcglxxByKey(Page<ProcessModel> page, ProcessVO processVO) {
        return baseMapper.getlcglxxByKey(page,processVO);
    }

    @Override
    public boolean updateProcessXmlString(ProcessVO processVO) {
        return baseMapper.updateProcessXmlString(processVO);
    }

    @Override
    public boolean updateActReProcdef(ActReProcdef actReProcdef) {
        return baseMapper.updateActReProcdef(actReProcdef);
    }
}
