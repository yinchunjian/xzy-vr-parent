package com.xzy.vr.app.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy.vr.app.modules.system.entity.SysVersionInfo;
import com.xzy.vr.app.modules.system.service.ISysVersionInfoService;
import com.xzy.vr.common.api.vo.Result;
import com.xzy.vr.common.aspect.annotation.AutoLog;
import com.xzy.vr.common.system.base.controller.JeecgController;
import com.xzy.vr.common.system.query.QueryGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 版本管理
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="版本管理")
@RestController
@RequestMapping("/system/sysVersionInfo")
public class SysVersionInfoController extends JeecgController<SysVersionInfo, ISysVersionInfoService> {
	@Autowired
	private ISysVersionInfoService sysVersionInfoService;


	 /**
	  *
	  * @param systemId
	  * @return
	  */
	 @AutoLog(value = "获取最新的版本信息")
	 @ApiOperation(value="获取最新的版本信息", notes="获取最新的版本信息")
	 @GetMapping(value = "/getLastVersion")
	 public Result<?> getLastVersion(@RequestParam(name="systemId") String systemId) {
		 SysVersionInfo param = new SysVersionInfo();
		 param.setSystemId(systemId);
		 QueryWrapper<SysVersionInfo> queryWrapper = new QueryWrapper<SysVersionInfo>(param).orderByDesc("version_no");
		 List<SysVersionInfo> versionInfoList = sysVersionInfoService.list(queryWrapper);
		 if(null != versionInfoList && versionInfoList.size() > 0){
			 return Result.ok(versionInfoList.get(0));
		 }else{
			 return Result.ok(null);
		 }
	 }

}
