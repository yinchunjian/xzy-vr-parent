package com.xzy.vr.app.modules.device.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzy.vr.app.modules.device.entity.DevDeviceInfo;
import com.xzy.vr.app.modules.device.service.IDevDeviceInfoService;
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
 * @Description: 设备信息
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="设备信息")
@RestController
@RequestMapping("/device/devDeviceInfo")
public class DevDeviceInfoController extends JeecgController<DevDeviceInfo, IDevDeviceInfoService> {
	@Autowired
	private IDevDeviceInfoService devDeviceInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param devDeviceInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备信息-分页列表查询")
	@ApiOperation(value="设备信息-分页列表查询", notes="设备信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DevDeviceInfo devDeviceInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DevDeviceInfo> queryWrapper = QueryGenerator.initQueryWrapper(devDeviceInfo, req.getParameterMap());
		Page<DevDeviceInfo> page = new Page<DevDeviceInfo>(pageNo, pageSize);
		IPage<DevDeviceInfo> pageList = devDeviceInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param devDeviceInfo
	 * @return
	 */
	@AutoLog(value = "设备信息-添加")
	@ApiOperation(value="设备信息-添加", notes="设备信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DevDeviceInfo devDeviceInfo) {
		devDeviceInfoService.save(devDeviceInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param devDeviceInfo
	 * @return
	 */
	@AutoLog(value = "设备信息-编辑")
	@ApiOperation(value="设备信息-编辑", notes="设备信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DevDeviceInfo devDeviceInfo) {
		devDeviceInfoService.updateById(devDeviceInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备信息-通过id删除")
	@ApiOperation(value="设备信息-通过id删除", notes="设备信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		devDeviceInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备信息-批量删除")
	@ApiOperation(value="设备信息-批量删除", notes="设备信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.devDeviceInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备信息-通过id查询")
	@ApiOperation(value="设备信息-通过id查询", notes="设备信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DevDeviceInfo devDeviceInfo = devDeviceInfoService.getById(id);
		return Result.ok(devDeviceInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param devDeviceInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, DevDeviceInfo devDeviceInfo) {
      return super.exportXls(request, devDeviceInfo, DevDeviceInfo.class, "设备信息");
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
      return super.importExcel(request, response, DevDeviceInfo.class);
  }

}
