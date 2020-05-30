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
import com.sdy.crm.modules.bus.entity.BusBusinessInfo;
import com.sdy.crm.modules.bus.service.IBusBusinessInfoService;
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
 * @Description: 商机信息
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商机信息")
@RestController
@RequestMapping("/bus/busBusinessInfo")
public class BusBusinessInfoController extends JeecgController<BusBusinessInfo, IBusBusinessInfoService> {
	@Autowired
	private IBusBusinessInfoService busBusinessInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param busBusinessInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商机信息-分页列表查询")
	@ApiOperation(value="商机信息-分页列表查询", notes="商机信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BusBusinessInfo busBusinessInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BusBusinessInfo> queryWrapper = QueryGenerator.initQueryWrapper(busBusinessInfo, req.getParameterMap());
		Page<BusBusinessInfo> page = new Page<BusBusinessInfo>(pageNo, pageSize);
		IPage<BusBusinessInfo> pageList = busBusinessInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param busBusinessInfo
	 * @return
	 */
	@AutoLog(value = "商机信息-添加")
	@ApiOperation(value="商机信息-添加", notes="商机信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BusBusinessInfo busBusinessInfo) {
		busBusinessInfoService.save(busBusinessInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param busBusinessInfo
	 * @return
	 */
	@AutoLog(value = "商机信息-编辑")
	@ApiOperation(value="商机信息-编辑", notes="商机信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BusBusinessInfo busBusinessInfo) {
		busBusinessInfoService.updateById(busBusinessInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商机信息-通过id删除")
	@ApiOperation(value="商机信息-通过id删除", notes="商机信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		busBusinessInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商机信息-批量删除")
	@ApiOperation(value="商机信息-批量删除", notes="商机信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.busBusinessInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商机信息-通过id查询")
	@ApiOperation(value="商机信息-通过id查询", notes="商机信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BusBusinessInfo busBusinessInfo = busBusinessInfoService.getById(id);
		return Result.ok(busBusinessInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param busBusinessInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BusBusinessInfo busBusinessInfo) {
      return super.exportXls(request, busBusinessInfo, BusBusinessInfo.class, "商机信息");
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
      return super.importExcel(request, response, BusBusinessInfo.class);
  }

}
