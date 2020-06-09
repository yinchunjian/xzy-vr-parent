package com.xzy.vr.app.modules.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzy.vr.app.modules.system.entity.SysDepartmentInfo;
import com.xzy.vr.app.modules.system.service.ISysDepartmentInfoService;
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
 * @Description: 科室信息
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="科室信息")
@RestController
@RequestMapping("/system/sysDepartmentInfo")
public class SysDepartmentInfoController extends JeecgController<SysDepartmentInfo, ISysDepartmentInfoService> {
	@Autowired
	private ISysDepartmentInfoService sysDepartmentInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysDepartmentInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "科室信息-分页列表查询")
	@ApiOperation(value="科室信息-分页列表查询", notes="科室信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysDepartmentInfo sysDepartmentInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysDepartmentInfo> queryWrapper = QueryGenerator.initQueryWrapper(sysDepartmentInfo, req.getParameterMap());
		Page<SysDepartmentInfo> page = new Page<SysDepartmentInfo>(pageNo, pageSize);
		IPage<SysDepartmentInfo> pageList = sysDepartmentInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysDepartmentInfo
	 * @return
	 */
	@AutoLog(value = "科室信息-添加")
	@ApiOperation(value="科室信息-添加", notes="科室信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysDepartmentInfo sysDepartmentInfo) {
		sysDepartmentInfoService.save(sysDepartmentInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysDepartmentInfo
	 * @return
	 */
	@AutoLog(value = "科室信息-编辑")
	@ApiOperation(value="科室信息-编辑", notes="科室信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysDepartmentInfo sysDepartmentInfo) {
		sysDepartmentInfoService.updateById(sysDepartmentInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "科室信息-通过id删除")
	@ApiOperation(value="科室信息-通过id删除", notes="科室信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysDepartmentInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "科室信息-批量删除")
	@ApiOperation(value="科室信息-批量删除", notes="科室信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysDepartmentInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "科室信息-通过id查询")
	@ApiOperation(value="科室信息-通过id查询", notes="科室信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysDepartmentInfo sysDepartmentInfo = sysDepartmentInfoService.getById(id);
		return Result.ok(sysDepartmentInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysDepartmentInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysDepartmentInfo sysDepartmentInfo) {
      return super.exportXls(request, sysDepartmentInfo, SysDepartmentInfo.class, "科室信息");
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
      return super.importExcel(request, response, SysDepartmentInfo.class);
  }

}
