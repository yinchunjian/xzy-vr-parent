package com.sdy.crm.modules.message.service;

import java.util.List;

import com.sdy.crm.common.system.base.service.JeecgService;
import com.sdy.crm.modules.message.entity.SysMessageTemplate;

/**
 * @Description: 消息模板
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageTemplateService extends JeecgService<SysMessageTemplate> {
    List<SysMessageTemplate> selectByCode(String code);
}
