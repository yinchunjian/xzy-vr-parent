package com.xzy.vr.app.modules.device.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzy.vr.app.modules.device.entity.DevDeviceCategory;
import com.xzy.vr.app.modules.device.service.IDevDeviceCategoryService;
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
 * @Description: 设备分类
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="设备分类")
@RestController
@RequestMapping("/device/devDeviceCategory")
public class DevDeviceCategoryController extends JeecgController<DevDeviceCategory, IDevDeviceCategoryService> {
	@Autowired
	private IDevDeviceCategoryService devDeviceCategoryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param devDeviceCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备分类-分页列表查询")
	@ApiOperation(value="设备分类-分页列表查询", notes="设备分类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DevDeviceCategory devDeviceCategory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DevDeviceCategory> queryWrapper = QueryGenerator.initQueryWrapper(devDeviceCategory, req.getParameterMap());
		Page<DevDeviceCategory> page = new Page<DevDeviceCategory>(pageNo, pageSize);
		IPage<DevDeviceCategory> pageList = devDeviceCategoryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param devDeviceCategory
	 * @return
	 */
	@AutoLog(value = "设备分类-添加")
	@ApiOperation(value="设备分类-添加", notes="设备分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DevDeviceCategory devDeviceCategory) {
		devDeviceCategoryService.save(devDeviceCategory);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param devDeviceCategory
	 * @return
	 */
	@AutoLog(value = "设备分类-编辑")
	@ApiOperation(value="设备分类-编辑", notes="设备分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DevDeviceCategory devDeviceCategory) {
		devDeviceCategoryService.updateById(devDeviceCategory);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备分类-通过id删除")
	@ApiOperation(value="设备分类-通过id删除", notes="设备分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		devDeviceCategoryService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备分类-批量删除")
	@ApiOperation(value="设备分类-批量删除", notes="设备分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.devDeviceCategoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备分类-通过id查询")
	@ApiOperation(value="设备分类-通过id查询", notes="设备分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DevDeviceCategory devDeviceCategory = devDeviceCategoryService.getById(id);
		return Result.ok(devDeviceCategory);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param devDeviceCategory
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DevDeviceCategory devDeviceCategory) {
      return super.exportXls(request, devDeviceCategory, DevDeviceCategory.class, "设备分类");
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
      return super.importExcel(request, response, DevDeviceCategory.class);
  }

}
