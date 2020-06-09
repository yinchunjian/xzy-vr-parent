package com.xzy.vr.app.modules.scene.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy.vr.app.modules.scene.entity.SceTreatmentScemeCategory;
import com.xzy.vr.app.modules.scene.service.ISceTreatmentScemeCategoryService;
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
 * @Description: 疗法方案分类
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="疗法方案分类")
@RestController
@RequestMapping("/scene/sceTreatmentScemeCategory")
public class SceTreatmentScemeCategoryController extends JeecgController<SceTreatmentScemeCategory, ISceTreatmentScemeCategoryService> {
	@Autowired
	private ISceTreatmentScemeCategoryService sceTreatmentScemeCategoryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sceTreatmentScemeCategory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "疗法方案分类-分页列表查询")
	@ApiOperation(value="疗法方案分类-分页列表查询", notes="疗法方案分类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SceTreatmentScemeCategory sceTreatmentScemeCategory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SceTreatmentScemeCategory> queryWrapper = QueryGenerator.initQueryWrapper(sceTreatmentScemeCategory, req.getParameterMap());
		Page<SceTreatmentScemeCategory> page = new Page<SceTreatmentScemeCategory>(pageNo, pageSize);
		IPage<SceTreatmentScemeCategory> pageList = sceTreatmentScemeCategoryService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sceTreatmentScemeCategory
	 * @return
	 */
	@AutoLog(value = "疗法方案分类-添加")
	@ApiOperation(value="疗法方案分类-添加", notes="疗法方案分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SceTreatmentScemeCategory sceTreatmentScemeCategory) {
		sceTreatmentScemeCategoryService.save(sceTreatmentScemeCategory);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sceTreatmentScemeCategory
	 * @return
	 */
	@AutoLog(value = "疗法方案分类-编辑")
	@ApiOperation(value="疗法方案分类-编辑", notes="疗法方案分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SceTreatmentScemeCategory sceTreatmentScemeCategory) {
		sceTreatmentScemeCategoryService.updateById(sceTreatmentScemeCategory);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "疗法方案分类-通过id删除")
	@ApiOperation(value="疗法方案分类-通过id删除", notes="疗法方案分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sceTreatmentScemeCategoryService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "疗法方案分类-批量删除")
	@ApiOperation(value="疗法方案分类-批量删除", notes="疗法方案分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sceTreatmentScemeCategoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "疗法方案分类-通过id查询")
	@ApiOperation(value="疗法方案分类-通过id查询", notes="疗法方案分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SceTreatmentScemeCategory sceTreatmentScemeCategory = sceTreatmentScemeCategoryService.getById(id);
		return Result.ok(sceTreatmentScemeCategory);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sceTreatmentScemeCategory
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SceTreatmentScemeCategory sceTreatmentScemeCategory) {
      return super.exportXls(request, sceTreatmentScemeCategory, SceTreatmentScemeCategory.class, "疗法方案分类");
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
      return super.importExcel(request, response, SceTreatmentScemeCategory.class);
  }

}
