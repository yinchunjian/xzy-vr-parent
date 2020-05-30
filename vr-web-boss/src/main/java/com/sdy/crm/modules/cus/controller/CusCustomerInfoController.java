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
import com.sdy.crm.modules.cus.entity.CusCustomerInfo;
import com.sdy.crm.modules.cus.service.ICusCustomerInfoService;
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
 * @Description: 客户信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户信息")
@RestController
@RequestMapping("/cus/cusCustomerInfo")
public class CusCustomerInfoController extends JeecgController<CusCustomerInfo, ICusCustomerInfoService> {
	@Autowired
	private ICusCustomerInfoService cusCustomerInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cusCustomerInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户信息-分页列表查询")
	@ApiOperation(value="客户信息-分页列表查询", notes="客户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CusCustomerInfo cusCustomerInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CusCustomerInfo> queryWrapper = QueryGenerator.initQueryWrapper(cusCustomerInfo, req.getParameterMap());
		Page<CusCustomerInfo> page = new Page<CusCustomerInfo>(pageNo, pageSize);
		IPage<CusCustomerInfo> pageList = cusCustomerInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cusCustomerInfo
	 * @return
	 */
	@AutoLog(value = "客户信息-添加")
	@ApiOperation(value="客户信息-添加", notes="客户信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CusCustomerInfo cusCustomerInfo) {
		cusCustomerInfoService.save(cusCustomerInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cusCustomerInfo
	 * @return
	 */
	@AutoLog(value = "客户信息-编辑")
	@ApiOperation(value="客户信息-编辑", notes="客户信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CusCustomerInfo cusCustomerInfo) {
		cusCustomerInfoService.updateById(cusCustomerInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息-通过id删除")
	@ApiOperation(value="客户信息-通过id删除", notes="客户信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cusCustomerInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户信息-批量删除")
	@ApiOperation(value="客户信息-批量删除", notes="客户信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cusCustomerInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息-通过id查询")
	@ApiOperation(value="客户信息-通过id查询", notes="客户信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CusCustomerInfo cusCustomerInfo = cusCustomerInfoService.getById(id);
		return Result.ok(cusCustomerInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cusCustomerInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CusCustomerInfo cusCustomerInfo) {
      return super.exportXls(request, cusCustomerInfo, CusCustomerInfo.class, "客户信息");
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
      return super.importExcel(request, response, CusCustomerInfo.class);
  }

}
