package com.sdy.crm.modules.cus.controller;

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
import com.sdy.crm.modules.cus.entity.CusIndustryInfo;
import com.sdy.crm.modules.cus.service.ICusIndustryInfoService;
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
 * @Description: 行业信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="行业信息")
@RestController
@RequestMapping("/cus/cusIndustryInfo")
public class CusIndustryInfoController extends JeecgController<CusIndustryInfo, ICusIndustryInfoService> {
	@Autowired
	private ICusIndustryInfoService cusIndustryInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cusIndustryInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "行业信息-分页列表查询")
	@ApiOperation(value="行业信息-分页列表查询", notes="行业信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CusIndustryInfo cusIndustryInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CusIndustryInfo> queryWrapper = QueryGenerator.initQueryWrapper(cusIndustryInfo, req.getParameterMap());
		Page<CusIndustryInfo> page = new Page<CusIndustryInfo>(pageNo, pageSize);
		IPage<CusIndustryInfo> pageList = cusIndustryInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cusIndustryInfo
	 * @return
	 */
	@AutoLog(value = "行业信息-添加")
	@ApiOperation(value="行业信息-添加", notes="行业信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CusIndustryInfo cusIndustryInfo) {
		cusIndustryInfoService.save(cusIndustryInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cusIndustryInfo
	 * @return
	 */
	@AutoLog(value = "行业信息-编辑")
	@ApiOperation(value="行业信息-编辑", notes="行业信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CusIndustryInfo cusIndustryInfo) {
		cusIndustryInfoService.updateById(cusIndustryInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "行业信息-通过id删除")
	@ApiOperation(value="行业信息-通过id删除", notes="行业信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cusIndustryInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "行业信息-批量删除")
	@ApiOperation(value="行业信息-批量删除", notes="行业信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cusIndustryInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "行业信息-通过id查询")
	@ApiOperation(value="行业信息-通过id查询", notes="行业信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CusIndustryInfo cusIndustryInfo = cusIndustryInfoService.getById(id);
		return Result.ok(cusIndustryInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cusIndustryInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CusIndustryInfo cusIndustryInfo) {
      return super.exportXls(request, cusIndustryInfo, CusIndustryInfo.class, "行业信息");
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
      return super.importExcel(request, response, CusIndustryInfo.class);
  }

}
