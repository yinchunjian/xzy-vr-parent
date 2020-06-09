package com.xzy.vr.app.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy.vr.app.modules.system.entity.SysLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-26
 */
public interface ISysLogService extends IService<SysLog> {

	/**
	 * @功能：清空所有日志记录
	 */
	public void removeAll();

	/**
	 * 记录日志
	 * @param LogContent
	 * @param logType
	 * @param operatetype
	 */
	public void addLog(String LogContent, Integer logType, Integer operatetype);

}
