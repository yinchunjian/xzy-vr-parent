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
import com.xzy.vr.app.modules.scene.service.ISceTreatmentScemeVideoService;
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
	 @Autowired
	 private ISceTreatmentScemeVideoService sceTreatmentScemeVideoService;

	 /**
	  * 疗法方案-分页列表查询
	  * @param categoryId
	  * @param pageNo
	  * @param pageSize
	  * @return
	  */
	@AutoLog(value = "疗法方案-分页列表查询")
	@ApiOperation(value="疗法方案-分页列表查询", notes="疗法方案-分页列表查询")
	@GetMapping(value = "/queryPageList")
	public Result<?> queryPageList(@RequestParam(name="categoryId",required=true) String categoryId,@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		QueryWrapper<SceTreatmentScemeInfo> queryWrapper = new QueryWrapper<SceTreatmentScemeInfo>();
		queryWrapper.eq("category_id",categoryId);
		queryWrapper.eq("del_flag",0);
		Page<SceTreatmentScemeInfo> page = new Page<SceTreatmentScemeInfo>(pageNo, pageSize);
		IPage<SceTreatmentScemeInfo> pageList = sceTreatmentScemeInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
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


}
