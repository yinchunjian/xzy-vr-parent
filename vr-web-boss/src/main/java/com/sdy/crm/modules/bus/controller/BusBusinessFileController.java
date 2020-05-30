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
import com.sdy.crm.modules.bus.entity.BusBusinessFile;
import com.sdy.crm.modules.bus.service.IBusBusinessFileService;
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
 * @Description: 商机附件
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商机附件")
@RestController
@RequestMapping("/bus/busBusinessFile")
public class BusBusinessFileController extends JeecgController<BusBusinessFile, IBusBusinessFileService> {
	@Autowired
	private IBusBusinessFileService busBusinessFileService;
	
	/**
	 * 分页列表查询
	 *
	 * @param busBusinessFile
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商机附件-分页列表查询")
	@ApiOperation(value="商机附件-分页列表查询", notes="商机附件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BusBusinessFile busBusinessFile,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BusBusinessFile> queryWrapper = QueryGenerator.initQueryWrapper(busBusinessFile, req.getParameterMap());
		Page<BusBusinessFile> page = new Page<BusBusinessFile>(pageNo, pageSize);
		IPage<BusBusinessFile> pageList = busBusinessFileService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param busBusinessFile
	 * @return
	 */
	@AutoLog(value = "商机附件-添加")
	@ApiOperation(value="商机附件-添加", notes="商机附件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BusBusinessFile busBusinessFile) {
		busBusinessFileService.save(busBusinessFile);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param busBusinessFile
	 * @return
	 */
	@AutoLog(value = "商机附件-编辑")
	@ApiOperation(value="商机附件-编辑", notes="商机附件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BusBusinessFile busBusinessFile) {
		busBusinessFileService.updateById(busBusinessFile);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商机附件-通过id删除")
	@ApiOperation(value="商机附件-通过id删除", notes="商机附件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		busBusinessFileService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商机附件-批量删除")
	@ApiOperation(value="商机附件-批量删除", notes="商机附件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.busBusinessFileService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商机附件-通过id查询")
	@ApiOperation(value="商机附件-通过id查询", notes="商机附件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BusBusinessFile busBusinessFile = busBusinessFileService.getById(id);
		return Result.ok(busBusinessFile);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param busBusinessFile
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BusBusinessFile busBusinessFile) {
      return super.exportXls(request, busBusinessFile, BusBusinessFile.class, "商机附件");
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
      return super.importExcel(request, response, BusBusinessFile.class);
  }

}
