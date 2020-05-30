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
import com.sdy.crm.modules.cus.entity.CusCustomerContact;
import com.sdy.crm.modules.cus.service.ICusCustomerContactService;
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
 * @Description: 客户联系人
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户联系人")
@RestController
@RequestMapping("/cus/cusCustomerContact")
public class CusCustomerContactController extends JeecgController<CusCustomerContact, ICusCustomerContactService> {
	@Autowired
	private ICusCustomerContactService cusCustomerContactService;
	
	/**
	 * 分页列表查询
	 *
	 * @param cusCustomerContact
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户联系人-分页列表查询")
	@ApiOperation(value="客户联系人-分页列表查询", notes="客户联系人-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CusCustomerContact cusCustomerContact,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CusCustomerContact> queryWrapper = QueryGenerator.initQueryWrapper(cusCustomerContact, req.getParameterMap());
		Page<CusCustomerContact> page = new Page<CusCustomerContact>(pageNo, pageSize);
		IPage<CusCustomerContact> pageList = cusCustomerContactService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param cusCustomerContact
	 * @return
	 */
	@AutoLog(value = "客户联系人-添加")
	@ApiOperation(value="客户联系人-添加", notes="客户联系人-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CusCustomerContact cusCustomerContact) {
		cusCustomerContactService.save(cusCustomerContact);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param cusCustomerContact
	 * @return
	 */
	@AutoLog(value = "客户联系人-编辑")
	@ApiOperation(value="客户联系人-编辑", notes="客户联系人-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CusCustomerContact cusCustomerContact) {
		cusCustomerContactService.updateById(cusCustomerContact);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户联系人-通过id删除")
	@ApiOperation(value="客户联系人-通过id删除", notes="客户联系人-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cusCustomerContactService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户联系人-批量删除")
	@ApiOperation(value="客户联系人-批量删除", notes="客户联系人-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cusCustomerContactService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户联系人-通过id查询")
	@ApiOperation(value="客户联系人-通过id查询", notes="客户联系人-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CusCustomerContact cusCustomerContact = cusCustomerContactService.getById(id);
		return Result.ok(cusCustomerContact);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param cusCustomerContact
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CusCustomerContact cusCustomerContact) {
      return super.exportXls(request, cusCustomerContact, CusCustomerContact.class, "客户联系人");
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
      return super.importExcel(request, response, CusCustomerContact.class);
  }

}
