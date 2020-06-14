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
import java.util.List;

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
	  *
	  * @return
	  */
	@AutoLog(value = "疗法方案分类列表")
	@ApiOperation(value="疗法方案分类列表", notes="疗法方案分类列表")
	@GetMapping(value = "/queryList")
	public Result<?> queryList() {
		QueryWrapper<SceTreatmentScemeCategory> queryWrapper = new QueryWrapper<SceTreatmentScemeCategory>();
		List<SceTreatmentScemeCategory> list = sceTreatmentScemeCategoryService.list(queryWrapper);
		return Result.ok(list);
	}
	


}
