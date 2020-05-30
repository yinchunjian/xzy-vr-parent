package com.sdy.crm.modules.tas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Result;
import QueryGenerator;
import AutoLog;
import oConvertUtils;
import com.sdy.crm.modules.tas.entity.TasTaskInfo;
import com.sdy.crm.modules.tas.service.ITasTaskInfoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import JeecgController;
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
 * @Description: 任务信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="任务信息")
@RestController
@RequestMapping("/tas/tasTaskInfo")
public class TasTaskInfoController extends JeecgController<TasTaskInfo, ITasTaskInfoService> {
	@Autowired
	private ITasTaskInfoService tasTaskInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tasTaskInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "任务信息-分页列表查询")
	@ApiOperation(value="任务信息-分页列表查询", notes="任务信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TasTaskInfo tasTaskInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TasTaskInfo> queryWrapper = QueryGenerator.initQueryWrapper(tasTaskInfo, req.getParameterMap());
		Page<TasTaskInfo> page = new Page<TasTaskInfo>(pageNo, pageSize);
		IPage<TasTaskInfo> pageList = tasTaskInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tasTaskInfo
	 * @return
	 */
	@AutoLog(value = "任务信息-添加")
	@ApiOperation(value="任务信息-添加", notes="任务信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TasTaskInfo tasTaskInfo) {
		tasTaskInfoService.save(tasTaskInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tasTaskInfo
	 * @return
	 */
	@AutoLog(value = "任务信息-编辑")
	@ApiOperation(value="任务信息-编辑", notes="任务信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TasTaskInfo tasTaskInfo) {
		tasTaskInfoService.updateById(tasTaskInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务信息-通过id删除")
	@ApiOperation(value="任务信息-通过id删除", notes="任务信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tasTaskInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务信息-批量删除")
	@ApiOperation(value="任务信息-批量删除", notes="任务信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tasTaskInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务信息-通过id查询")
	@ApiOperation(value="任务信息-通过id查询", notes="任务信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TasTaskInfo tasTaskInfo = tasTaskInfoService.getById(id);
		return Result.ok(tasTaskInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tasTaskInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TasTaskInfo tasTaskInfo) {
      return super.exportXls(request, tasTaskInfo, TasTaskInfo.class, "任务信息");
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
      return super.importExcel(request, response, TasTaskInfo.class);
  }

}
