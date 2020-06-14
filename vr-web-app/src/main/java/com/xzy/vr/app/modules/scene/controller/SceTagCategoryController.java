package com.xzy.vr.app.modules.scene.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzy.vr.app.modules.scene.entity.SceTagCategory;
import com.xzy.vr.app.modules.scene.entity.SceTagInfo;
import com.xzy.vr.app.modules.scene.service.ISceTagCategoryService;
import com.xzy.vr.app.modules.scene.service.ISceTagInfoService;
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
 * @Description: 标签分类
 * @Author: jeecg-boot
 * @Date:   2020-06-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="标签分类")
@RestController
@RequestMapping("/scene/sceTagCategory")
public class SceTagCategoryController extends JeecgController<SceTagCategory, ISceTagCategoryService> {
	@Autowired
	private ISceTagCategoryService sceTagCategoryService;
	 @Autowired
	 private ISceTagInfoService sceTagInfoService;

	 /**
	  * 标签分类及标签列表查询
	  * @return
	  */
	@AutoLog(value = "标签分类及标签列表查询")
	@ApiOperation(value="标签分类及标签列表查询", notes="标签分类及标签列表查询")
	@GetMapping(value = "/queryCategoryTagList")
	public Result<?> queryCategoryTagList() {
		QueryWrapper<SceTagCategory> queryWrapper = new QueryWrapper<SceTagCategory>().eq("del_flag",0);
		List<SceTagCategory> tagCategoryList = sceTagCategoryService.list(queryWrapper);
		if(null != tagCategoryList && tagCategoryList.size() > 0){
			for(SceTagCategory tagCategory : tagCategoryList){
				SceTagInfo tagParam = new SceTagInfo();
				tagParam.setCategoryId(tagCategory.getCategoryId());
				tagParam.setDelFlag(0);
				QueryWrapper<SceTagInfo> tagInfoWrapper = new QueryWrapper<SceTagInfo>(tagParam);
				tagCategory.setTagInfoList(sceTagInfoService.list(tagInfoWrapper));
			}
		}
		return Result.ok(tagCategoryList);
	}
}
