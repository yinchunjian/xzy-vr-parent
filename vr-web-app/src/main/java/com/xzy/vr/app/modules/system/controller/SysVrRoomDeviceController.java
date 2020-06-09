package com.xzy.vr.app.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy.vr.app.modules.system.entity.SysVrRoomDevice;
import com.xzy.vr.app.modules.system.service.ISysVrRoomDeviceService;
import com.xzy.vr.common.api.vo.Result;
import com.xzy.vr.common.aspect.annotation.AutoLog;
import com.xzy.vr.common.system.base.controller.JeecgController;
import com.xzy.vr.common.system.query.QueryGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: VR室设备
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="VR室设备")
@RestController
@RequestMapping("/system/sysVrRoomDevice")
public class SysVrRoomDeviceController extends JeecgController<SysVrRoomDevice, ISysVrRoomDeviceService> {
	@Autowired
	private ISysVrRoomDeviceService sysVrRoomDeviceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sysVrRoomDevice
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "VR室设备-分页列表查询")
	@ApiOperation(value="VR室设备-分页列表查询", notes="VR室设备-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysVrRoomDevice sysVrRoomDevice,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SysVrRoomDevice> queryWrapper = QueryGenerator.initQueryWrapper(sysVrRoomDevice, req.getParameterMap());
		Page<SysVrRoomDevice> page = new Page<SysVrRoomDevice>(pageNo, pageSize);
		IPage<SysVrRoomDevice> pageList = sysVrRoomDeviceService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sysVrRoomDevice
	 * @return
	 */
	@AutoLog(value = "VR室设备-添加")
	@ApiOperation(value="VR室设备-添加", notes="VR室设备-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SysVrRoomDevice sysVrRoomDevice) {
		sysVrRoomDeviceService.save(sysVrRoomDevice);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sysVrRoomDevice
	 * @return
	 */
	@AutoLog(value = "VR室设备-编辑")
	@ApiOperation(value="VR室设备-编辑", notes="VR室设备-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SysVrRoomDevice sysVrRoomDevice) {
		sysVrRoomDeviceService.updateById(sysVrRoomDevice);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "VR室设备-通过id删除")
	@ApiOperation(value="VR室设备-通过id删除", notes="VR室设备-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sysVrRoomDeviceService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "VR室设备-批量删除")
	@ApiOperation(value="VR室设备-批量删除", notes="VR室设备-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sysVrRoomDeviceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "VR室设备-通过id查询")
	@ApiOperation(value="VR室设备-通过id查询", notes="VR室设备-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SysVrRoomDevice sysVrRoomDevice = sysVrRoomDeviceService.getById(id);
		return Result.ok(sysVrRoomDevice);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sysVrRoomDevice
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SysVrRoomDevice sysVrRoomDevice) {
      return super.exportXls(request, sysVrRoomDevice, SysVrRoomDevice.class, "VR室设备");
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
      return super.importExcel(request, response, SysVrRoomDevice.class);
  }

}
