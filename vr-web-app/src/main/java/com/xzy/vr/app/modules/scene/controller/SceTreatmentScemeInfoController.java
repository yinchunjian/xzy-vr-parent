package com.xzy.vr.app.modules.scene.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzy.vr.app.modules.scene.entity.SceTreatmentScemeInfo;
import com.xzy.vr.app.modules.scene.service.ISceTreatmentScemeInfoService;
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
 * @Description: 疗法方案
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="疗法方案")
@RestController
@RequestMapping("/scene/sceTreatmentScemeInfo")
public class SceTreatmentScemeInfoController extends JeecgController<SceTreatmentScemeInfo, ISceTreatmentScemeInfoService> {
	@Autowired
	private ISceTreatmentScemeInfoService sceTreatmentScemeInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sceTreatmentScemeInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "疗法方案-分页列表查询")
	@ApiOperation(value="疗法方案-分页列表查询", notes="疗法方案-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SceTreatmentScemeInfo sceTreatmentScemeInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SceTreatmentScemeInfo> queryWrapper = QueryGenerator.initQueryWrapper(sceTreatmentScemeInfo, req.getParameterMap());
		Page<SceTreatmentScemeInfo> page = new Page<SceTreatmentScemeInfo>(pageNo, pageSize);
		IPage<SceTreatmentScemeInfo> pageList = sceTreatmentScemeInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sceTreatmentScemeInfo
	 * @return
	 */
	@AutoLog(value = "疗法方案-添加")
	@ApiOperation(value="疗法方案-添加", notes="疗法方案-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SceTreatmentScemeInfo sceTreatmentScemeInfo) {
		sceTreatmentScemeInfoService.save(sceTreatmentScemeInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sceTreatmentScemeInfo
	 * @return
	 */
	@AutoLog(value = "疗法方案-编辑")
	@ApiOperation(value="疗法方案-编辑", notes="疗法方案-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SceTreatmentScemeInfo sceTreatmentScemeInfo) {
		sceTreatmentScemeInfoService.updateById(sceTreatmentScemeInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "疗法方案-通过id删除")
	@ApiOperation(value="疗法方案-通过id删除", notes="疗法方案-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sceTreatmentScemeInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "疗法方案-批量删除")
	@ApiOperation(value="疗法方案-批量删除", notes="疗法方案-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sceTreatmentScemeInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "疗法方案-通过id查询")
	@ApiOperation(value="疗法方案-通过id查询", notes="疗法方案-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SceTreatmentScemeInfo sceTreatmentScemeInfo = sceTreatmentScemeInfoService.getById(id);
		return Result.ok(sceTreatmentScemeInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sceTreatmentScemeInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SceTreatmentScemeInfo sceTreatmentScemeInfo) {
      return super.exportXls(request, sceTreatmentScemeInfo, SceTreatmentScemeInfo.class, "疗法方案");
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
      return super.importExcel(request, response, SceTreatmentScemeInfo.class);
  }

}
