package com.xzy.vr.app.modules.scene.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy.vr.app.modules.scene.entity.SceTagInfo;
import com.xzy.vr.app.modules.scene.service.ISceTagInfoService;
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
 * @Description: 标签信息
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="标签信息")
@RestController
@RequestMapping("/scene/sceTagInfo")
public class SceTagInfoController extends JeecgController<SceTagInfo, ISceTagInfoService> {
	@Autowired
	private ISceTagInfoService sceTagInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sceTagInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "标签信息-分页列表查询")
	@ApiOperation(value="标签信息-分页列表查询", notes="标签信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SceTagInfo sceTagInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SceTagInfo> queryWrapper = QueryGenerator.initQueryWrapper(sceTagInfo, req.getParameterMap());
		Page<SceTagInfo> page = new Page<SceTagInfo>(pageNo, pageSize);
		IPage<SceTagInfo> pageList = sceTagInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param sceTagInfo
	 * @return
	 */
	@AutoLog(value = "标签信息-添加")
	@ApiOperation(value="标签信息-添加", notes="标签信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SceTagInfo sceTagInfo) {
		sceTagInfoService.save(sceTagInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param sceTagInfo
	 * @return
	 */
	@AutoLog(value = "标签信息-编辑")
	@ApiOperation(value="标签信息-编辑", notes="标签信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SceTagInfo sceTagInfo) {
		sceTagInfoService.updateById(sceTagInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "标签信息-通过id删除")
	@ApiOperation(value="标签信息-通过id删除", notes="标签信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		sceTagInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "标签信息-批量删除")
	@ApiOperation(value="标签信息-批量删除", notes="标签信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sceTagInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "标签信息-通过id查询")
	@ApiOperation(value="标签信息-通过id查询", notes="标签信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SceTagInfo sceTagInfo = sceTagInfoService.getById(id);
		return Result.ok(sceTagInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param sceTagInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, SceTagInfo sceTagInfo) {
      return super.exportXls(request, sceTagInfo, SceTagInfo.class, "标签信息");
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
      return super.importExcel(request, response, SceTagInfo.class);
  }

}
