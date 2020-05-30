package com.sdy.crm.modules.bus.controller;

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
import com.sdy.crm.modules.bus.entity.BusCustomerFile;
import com.sdy.crm.modules.bus.service.IBusCustomerFileService;
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
 * @Description: 客户附件
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="客户附件")
@RestController
@RequestMapping("/bus/busCustomerFile")
public class BusCustomerFileController extends JeecgController<BusCustomerFile, IBusCustomerFileService> {
	@Autowired
	private IBusCustomerFileService busCustomerFileService;
	
	/**
	 * 分页列表查询
	 *
	 * @param busCustomerFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "客户附件-分页列表查询")
	@ApiOperation(value="客户附件-分页列表查询", notes="客户附件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BusCustomerFile busCustomerFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BusCustomerFile> queryWrapper = QueryGenerator.initQueryWrapper(busCustomerFile, req.getParameterMap());
		Page<BusCustomerFile> page = new Page<BusCustomerFile>(pageNo, pageSize);
		IPage<BusCustomerFile> pageList = busCustomerFileService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param busCustomerFile
	 * @return
	 */
	@AutoLog(value = "客户附件-添加")
	@ApiOperation(value="客户附件-添加", notes="客户附件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BusCustomerFile busCustomerFile) {
		busCustomerFileService.save(busCustomerFile);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param busCustomerFile
	 * @return
	 */
	@AutoLog(value = "客户附件-编辑")
	@ApiOperation(value="客户附件-编辑", notes="客户附件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BusCustomerFile busCustomerFile) {
		busCustomerFileService.updateById(busCustomerFile);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户附件-通过id删除")
	@ApiOperation(value="客户附件-通过id删除", notes="客户附件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		busCustomerFileService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户附件-批量删除")
	@ApiOperation(value="客户附件-批量删除", notes="客户附件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.busCustomerFileService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户附件-通过id查询")
	@ApiOperation(value="客户附件-通过id查询", notes="客户附件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BusCustomerFile busCustomerFile = busCustomerFileService.getById(id);
		return Result.ok(busCustomerFile);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param busCustomerFile
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BusCustomerFile busCustomerFile) {
      return super.exportXls(request, busCustomerFile, BusCustomerFile.class, "客户附件");
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
      return super.importExcel(request, response, BusCustomerFile.class);
  }

}
