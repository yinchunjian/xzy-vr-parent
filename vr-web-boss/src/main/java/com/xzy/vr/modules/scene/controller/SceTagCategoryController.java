package com.xzy.vr.modules.scene.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xzy.vr.common.api.vo.Result;
import com.xzy.vr.common.aspect.annotation.AutoLog;
import com.xzy.vr.common.system.base.controller.JeecgController;
import com.xzy.vr.modules.scene.entity.SceTagCategory;
import com.xzy.vr.modules.scene.service.ISceTagCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

   /**
    * 分页列表查询
    *
    * @param sceTagCategory
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "标签分类-分页列表查询")
   @ApiOperation(value="标签分类-分页列表查询", notes="标签分类-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(SceTagCategory sceTagCategory,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<SceTagCategory> queryWrapper = new QueryWrapper<SceTagCategory>();
       Page<SceTagCategory> page = new Page<SceTagCategory>(pageNo, pageSize);
       IPage<SceTagCategory> pageList = sceTagCategoryService.page(page, queryWrapper);
       return Result.ok(pageList);
   }
}
