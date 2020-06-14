//package com.xzy.vr.modules.scene.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.xzy.vr.common.api.vo.Result;
//import com.xzy.vr.common.aspect.annotation.AutoLog;
//import com.xzy.vr.common.system.base.controller.JeecgController;
//import com.xzy.vr.common.system.query.QueryGenerator;
//import com.xzy.vr.modules.scene.entity.SceTreatmentScemeVideo;
//import com.xzy.vr.modules.scene.service.ISceTreatmentScemeVideoService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//
// /**
// * @Description: 疗法方案视频
// * @Author: jeecg-boot
// * @Date:   2020-06-03
// * @Version: V1.0
// */
//@Slf4j
//@Api(tags="疗法方案视频")
//@RestController
//@RequestMapping("/scene/sceTreatmentScemeVideo")
//public class SceTreatmentScemeVideoController extends JeecgController<SceTreatmentScemeVideo, ISceTreatmentScemeVideoService> {
//	@Autowired
//	private ISceTreatmentScemeVideoService sceTreatmentScemeVideoService;
//
//	/**
//	 * 分页列表查询
//	 *
//	 * @param sceTreatmentScemeVideo
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "疗法方案视频-分页列表查询")
//	@ApiOperation(value="疗法方案视频-分页列表查询", notes="疗法方案视频-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(SceTreatmentScemeVideo sceTreatmentScemeVideo,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<SceTreatmentScemeVideo> queryWrapper = QueryGenerator.initQueryWrapper(sceTreatmentScemeVideo, req.getParameterMap());
//		Page<SceTreatmentScemeVideo> page = new Page<SceTreatmentScemeVideo>(pageNo, pageSize);
//		IPage<SceTreatmentScemeVideo> pageList = sceTreatmentScemeVideoService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 * 添加
//	 *
//	 * @param sceTreatmentScemeVideo
//	 * @return
//	 */
//	@AutoLog(value = "疗法方案视频-添加")
//	@ApiOperation(value="疗法方案视频-添加", notes="疗法方案视频-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody SceTreatmentScemeVideo sceTreatmentScemeVideo) {
//		sceTreatmentScemeVideoService.save(sceTreatmentScemeVideo);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 * 编辑
//	 *
//	 * @param sceTreatmentScemeVideo
//	 * @return
//	 */
//	@AutoLog(value = "疗法方案视频-编辑")
//	@ApiOperation(value="疗法方案视频-编辑", notes="疗法方案视频-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody SceTreatmentScemeVideo sceTreatmentScemeVideo) {
//		sceTreatmentScemeVideoService.updateById(sceTreatmentScemeVideo);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 * 通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "疗法方案视频-通过id删除")
//	@ApiOperation(value="疗法方案视频-通过id删除", notes="疗法方案视频-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		sceTreatmentScemeVideoService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 * 批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "疗法方案视频-批量删除")
//	@ApiOperation(value="疗法方案视频-批量删除", notes="疗法方案视频-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.sceTreatmentScemeVideoService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功！");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "疗法方案视频-通过id查询")
//	@ApiOperation(value="疗法方案视频-通过id查询", notes="疗法方案视频-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		SceTreatmentScemeVideo sceTreatmentScemeVideo = sceTreatmentScemeVideoService.getById(id);
//		return Result.ok(sceTreatmentScemeVideo);
//	}
//
//  /**
//   * 导出excel
//   *
//   * @param request
//   * @param sceTreatmentScemeVideo
//   */
//  @RequestMapping(value = "/exportXls")
//  public ModelAndView exportXls(HttpServletRequest request, SceTreatmentScemeVideo sceTreatmentScemeVideo) {
//      return super.exportXls(request, sceTreatmentScemeVideo, SceTreatmentScemeVideo.class, "疗法方案视频");
//  }
//
//  /**
//   * 通过excel导入数据
//   *
//   * @param request
//   * @param response
//   * @return
//   */
//  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
//  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
//      return super.importExcel(request, response, SceTreatmentScemeVideo.class);
//  }
//
//}
