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
import com.sdy.crm.modules.tas.entity.TasTaskFile;
import com.sdy.crm.modules.tas.service.ITasTaskFileService;
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
 * @Description: 任务附件
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="任务附件")
@RestController
@RequestMapping("/tas/tasTaskFile")
public class TasTaskFileController extends JeecgController<TasTaskFile, ITasTaskFileService> {
	@Autowired
	private ITasTaskFileService tasTaskFileService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tasTaskFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "任务附件-分页列表查询")
	@ApiOperation(value="任务附件-分页列表查询", notes="任务附件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TasTaskFile tasTaskFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TasTaskFile> queryWrapper = QueryGenerator.initQueryWrapper(tasTaskFile, req.getParameterMap());
		Page<TasTaskFile> page = new Page<TasTaskFile>(pageNo, pageSize);
		IPage<TasTaskFile> pageList = tasTaskFileService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param tasTaskFile
	 * @return
	 */
	@AutoLog(value = "任务附件-添加")
	@ApiOperation(value="任务附件-添加", notes="任务附件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TasTaskFile tasTaskFile) {
		tasTaskFileService.save(tasTaskFile);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param tasTaskFile
	 * @return
	 */
	@AutoLog(value = "任务附件-编辑")
	@ApiOperation(value="任务附件-编辑", notes="任务附件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TasTaskFile tasTaskFile) {
		tasTaskFileService.updateById(tasTaskFile);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务附件-通过id删除")
	@ApiOperation(value="任务附件-通过id删除", notes="任务附件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tasTaskFileService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务附件-批量删除")
	@ApiOperation(value="任务附件-批量删除", notes="任务附件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tasTaskFileService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务附件-通过id查询")
	@ApiOperation(value="任务附件-通过id查询", notes="任务附件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TasTaskFile tasTaskFile = tasTaskFileService.getById(id);
		return Result.ok(tasTaskFile);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tasTaskFile
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TasTaskFile tasTaskFile) {
      return super.exportXls(request, tasTaskFile, TasTaskFile.class, "任务附件");
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
      return super.importExcel(request, response, TasTaskFile.class);
  }

}
