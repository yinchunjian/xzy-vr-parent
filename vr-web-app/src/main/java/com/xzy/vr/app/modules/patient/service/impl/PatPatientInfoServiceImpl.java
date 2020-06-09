package com.xzy.vr.app.modules.patient.service.impl;

import com.xzy.vr.app.modules.patient.entity.PatPatientInfo;
import com.xzy.vr.app.modules.patient.mapper.PatPatientInfoMapper;
import com.xzy.vr.app.modules.patient.service.IPatPatientInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 患者信息
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Service
public class PatPatientInfoServiceImpl extends ServiceImpl<PatPatientInfoMapper, PatPatientInfo> implements IPatPatientInfoService {

}
