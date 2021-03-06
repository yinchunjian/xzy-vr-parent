package com.xzy.vr.app.modules.patient.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy.vr.app.modules.patient.entity.PatPatientInfo;
import com.xzy.vr.app.modules.patient.service.IPatPatientInfoService;
import com.xzy.vr.common.api.vo.Result;
import com.xzy.vr.common.aspect.annotation.AutoLog;
import com.xzy.vr.common.system.base.controller.JeecgController;
import com.xzy.vr.common.system.query.QueryGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 患者信息
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="患者信息")
@RestController
@RequestMapping("/patient/patPatientInfo")
public class PatPatientInfoController extends JeecgController<PatPatientInfo, IPatPatientInfoService> {
	@Autowired
	private IPatPatientInfoService patPatientInfoService;

	 /**
	  * 分页列表查询
	  * @param userNoName
	  * @param pageNo
	  * @param pageSize
	  * @return
	  */
	@AutoLog(value = "患者信息-分页列表查询")
	@ApiOperation(value="患者信息-分页列表查询", notes="患者信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(String userNoName,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		QueryWrapper<PatPatientInfo> queryWrapper = new QueryWrapper<PatPatientInfo>()
				.like(StringUtils.isNotEmpty(userNoName),"patient_name",userNoName)
				.like(StringUtils.isNotEmpty(userNoName),"patient_no",userNoName);
		Page<PatPatientInfo> page = new Page<PatPatientInfo>(pageNo, pageSize);
		IPage<PatPatientInfo> pageList = patPatientInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param patPatientInfo
	 * @return
	 */
	@AutoLog(value = "患者信息-添加")
	@ApiOperation(value="患者信息-添加", notes="患者信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PatPatientInfo patPatientInfo) {
		patPatientInfoService.save(patPatientInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param patPatientInfo
	 * @return
	 */
	@AutoLog(value = "患者信息-编辑")
	@ApiOperation(value="患者信息-编辑", notes="患者信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PatPatientInfo patPatientInfo) {
		patPatientInfoService.updateById(patPatientInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "患者信息-通过id删除")
	@ApiOperation(value="患者信息-通过id删除", notes="患者信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		patPatientInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "患者信息-批量删除")
	@ApiOperation(value="患者信息-批量删除", notes="患者信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.patPatientInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "患者信息-通过id查询")
	@ApiOperation(value="患者信息-通过id查询", notes="患者信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PatPatientInfo patPatientInfo = patPatientInfoService.getById(id);
		return Result.ok(patPatientInfo);
	}

}
