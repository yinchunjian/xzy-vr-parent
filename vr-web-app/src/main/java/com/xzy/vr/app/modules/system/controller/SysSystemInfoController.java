package com.xzy.vr.app.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzy.vr.app.modules.system.entity.SysSystemInfo;
import com.xzy.vr.app.modules.system.service.ISysSystemInfoService;
import com.xzy.vr.common.api.vo.Result;
import com.xzy.vr.common.aspect.annotation.AutoLog;
import com.xzy.vr.common.system.base.controller.JeecgController;
import com.xzy.vr.common.system.query.QueryGenerator;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: sys_system_info
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="sys_system_info")
@RestController
@RequestMapping("/system/sysSystemInfo")
public class SysSystemInfoController extends JeecgController<SysSystemInfo, ISysSystemInfoService> {
	@Autowired
	private ISysSystemInfoService sysSystemInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysSystemInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "sys_system_info-分页列表查询")
	@ApiOperation(value="sys_system_info-分页列表查询", notes="sys_system_info-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysSystemInfo sysSystemInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysSystemInfo> queryWrapper = QueryGenerator.initQueryWrapper(sysSystemInfo, req.getParameterMap());
		Page<SysSystemInfo> page = new Page<SysSystemInfo>(pageNo, pageSize);
		IPage<SysSystemInfo> pageList = sysSystemInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysSystemInfo
	 * @return
	 */
	@AutoLog(value = "sys_system_info-添加")
	@ApiOperation(value="sys_system_info-添加", notes="sys_system_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysSystemInfo sysSystemInfo) {
		sysSystemInfoService.save(sysSystemInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysSystemInfo
	 * @return
	 */
	@AutoLog(value = "sys_system_info-编辑")
	@ApiOperation(value="sys_system_info-编辑", notes="sys_system_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysSystemInfo sysSystemInfo) {
		sysSystemInfoService.updateById(sysSystemInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "sys_system_info-通过id删除")
	@ApiOperation(value="sys_system_info-通过id删除", notes="sys_system_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysSystemInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "sys_system_info-批量删除")
	@ApiOperation(value="sys_system_info-批量删除", notes="sys_system_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysSystemInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "sys_system_info-通过id查询")
	@ApiOperation(value="sys_system_info-通过id查询", notes="sys_system_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysSystemInfo sysSystemInfo = sysSystemInfoService.getById(id);
		return Result.ok(sysSystemInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysSystemInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysSystemInfo sysSystemInfo) {
      return super.exportXls(request, sysSystemInfo, SysSystemInfo.class, "sys_system_info");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, SysSystemInfo.class);
  }

}
