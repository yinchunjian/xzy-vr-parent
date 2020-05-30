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
import com.sdy.crm.modules.bus.entity.BusBusinessFollow;
import com.sdy.crm.modules.bus.service.IBusBusinessFollowService;
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
 * @Description: 商机跟进
 * @Author: jeecg-boot
 * @Date:   2020-05-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商机跟进")
@RestController
@RequestMapping("/bus/busBusinessFollow")
public class BusBusinessFollowController extends JeecgController<BusBusinessFollow, IBusBusinessFollowService> {
	@Autowired
	private IBusBusinessFollowService busBusinessFollowService;
	
	/**
	 * 分页列表查询
	 *
	 * @param busBusinessFollow
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商机跟进-分页列表查询")
	@ApiOperation(value="商机跟进-分页列表查询", notes="商机跟进-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BusBusinessFollow busBusinessFollow,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BusBusinessFollow> queryWrapper = QueryGenerator.initQueryWrapper(busBusinessFollow, req.getParameterMap());
		Page<BusBusinessFollow> page = new Page<BusBusinessFollow>(pageNo, pageSize);
		IPage<BusBusinessFollow> pageList = busBusinessFollowService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param busBusinessFollow
	 * @return
	 */
	@AutoLog(value = "商机跟进-添加")
	@ApiOperation(value="商机跟进-添加", notes="商机跟进-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BusBusinessFollow busBusinessFollow) {
		busBusinessFollowService.save(busBusinessFollow);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param busBusinessFollow
	 * @return
	 */
	@AutoLog(value = "商机跟进-编辑")
	@ApiOperation(value="商机跟进-编辑", notes="商机跟进-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BusBusinessFollow busBusinessFollow) {
		busBusinessFollowService.updateById(busBusinessFollow);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商机跟进-通过id删除")
	@ApiOperation(value="商机跟进-通过id删除", notes="商机跟进-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		busBusinessFollowService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商机跟进-批量删除")
	@ApiOperation(value="商机跟进-批量删除", notes="商机跟进-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.busBusinessFollowService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商机跟进-通过id查询")
	@ApiOperation(value="商机跟进-通过id查询", notes="商机跟进-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BusBusinessFollow busBusinessFollow = busBusinessFollowService.getById(id);
		return Result.ok(busBusinessFollow);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param busBusinessFollow
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BusBusinessFollow busBusinessFollow) {
      return super.exportXls(request, busBusinessFollow, BusBusinessFollow.class, "商机跟进");
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
      return super.importExcel(request, response, BusBusinessFollow.class);
  }

}
