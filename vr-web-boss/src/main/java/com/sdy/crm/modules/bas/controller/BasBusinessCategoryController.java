package com.sdy.crm.modules.bas.controller;

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
import com.sdy.crm.modules.bas.entity.BasBusinessCategory;
import com.sdy.crm.modules.bas.service.IBasBusinessCategoryService;
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
 * @Description: 业务分类
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="业务分类")
@RestController
@RequestMapping("/bas/basBusinessCategory")
public class BasBusinessCategoryController extends JeecgController<BasBusinessCategory, IBasBusinessCategoryService> {
	@Autowired
	private IBasBusinessCategoryService basBusinessCategoryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param basBusinessCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "业务分类-分页列表查询")
	@ApiOperation(value="业务分类-分页列表查询", notes="业务分类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BasBusinessCategory basBusinessCategory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BasBusinessCategory> queryWrapper = QueryGenerator.initQueryWrapper(basBusinessCategory, req.getParameterMap());
		Page<BasBusinessCategory> page = new Page<BasBusinessCategory>(pageNo, pageSize);
		IPage<BasBusinessCategory> pageList = basBusinessCategoryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param basBusinessCategory
	 * @return
	 */
	@AutoLog(value = "业务分类-添加")
	@ApiOperation(value="业务分类-添加", notes="业务分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BasBusinessCategory basBusinessCategory) {
		basBusinessCategoryService.save(basBusinessCategory);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param basBusinessCategory
	 * @return
	 */
	@AutoLog(value = "业务分类-编辑")
	@ApiOperation(value="业务分类-编辑", notes="业务分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BasBusinessCategory basBusinessCategory) {
		basBusinessCategoryService.updateById(basBusinessCategory);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务分类-通过id删除")
	@ApiOperation(value="业务分类-通过id删除", notes="业务分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		basBusinessCategoryService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "业务分类-批量删除")
	@ApiOperation(value="业务分类-批量删除", notes="业务分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.basBusinessCategoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "业务分类-通过id查询")
	@ApiOperation(value="业务分类-通过id查询", notes="业务分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BasBusinessCategory basBusinessCategory = basBusinessCategoryService.getById(id);
		return Result.ok(basBusinessCategory);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param basBusinessCategory
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BasBusinessCategory basBusinessCategory) {
      return super.exportXls(request, basBusinessCategory, BasBusinessCategory.class, "业务分类");
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
      return super.importExcel(request, response, BasBusinessCategory.class);
  }

}
