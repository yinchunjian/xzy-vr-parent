package com.sdy.crm.modules.flowable.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdy.crm.common.api.vo.Result;
import com.sdy.crm.common.aspect.annotation.AutoLog;
import com.sdy.crm.common.system.base.controller.JeecgController;
import com.sdy.crm.common.system.query.QueryGenerator;
import com.sdy.crm.modules.flowable.entity.ActReProcdef;
import com.sdy.crm.modules.flowable.model.ProcessModel;
import com.sdy.crm.modules.flowable.service.IActReProcdefService;
import com.sdy.crm.modules.flowable.vo.ProcessVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.CharsetNames;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: procdef
 * @Author: jeecg-boot
 * @Date:   2020-05-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="procdef")
@RestController
@RequestMapping("/flowable/actReProcdef")
public class ActReProcdefController extends JeecgController<ActReProcdef, IActReProcdefService> {
	@Autowired
	private IActReProcdefService actReProcdefService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngine processEngine;

	/**
	 * 分页列表查询
	 *
	 * @param actReProcdef
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "procdef-分页列表查询")
	@ApiOperation(value="procdef-分页列表查询", notes="procdef-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ActReProcdef actReProcdef,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ActReProcdef> queryWrapper = QueryGenerator.initQueryWrapper(actReProcdef, req.getParameterMap());
		Page<ActReProcdef> page = new Page<ActReProcdef>(pageNo, pageSize);
		IPage<ActReProcdef> pageList = actReProcdefService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *  添加流程
	 * @return
	 */
	@ApiOperation(value = "添加流程", notes = "添加流程")
	@PostMapping("/addProcess")
	public Result addProcess(@RequestBody ProcessVO processVO) {
		try {
			Page page=new Page();
			IPage<ProcessModel> pagePro=actReProcdefService.getlcglxxByKey(page,processVO);

			Deployment deployment=repositoryService.createDeployment().key(processVO.getKey()).addString("sdfsdf.bpmn20.xml",processVO.getXmlString()).deploy();
			UpdateWrapper<ActReProcdef> updateWrapper = new UpdateWrapper<>();
			if(pagePro.getRecords().size()!=0){
				updateWrapper.set("SUSPENSION_STATE_","2").eq("DEPLOYMENT_ID_", deployment.getId());//默认为挂起暂停状态
			}else{
				//如果等于0，代表第一个流程,默认是主版本，并且是激活状态
				updateWrapper.set("DERIVED_VERSION_","1").eq("DEPLOYMENT_ID_", deployment.getId());//默认为挂起暂停状态
			}

			actReProcdefService.update(updateWrapper);
			return  Result.ok("添加流程成功!");
		}catch (Exception e){
			log.info("================添加流程失败,失"+e);
			return  Result.error("添加流程失败,失败原因:"+e);
		}
	}

	/**
	 * 查询版本 管理
	 * */
	@ApiOperation(value = "查询版本 管理", notes = "查询版本管理")
	@PostMapping("/getProcess")
	public Result<?> getProcess(ProcessVO processVO,
										   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
		Page<ProcessModel> page = new Page<ProcessModel>(pageNo, pageSize);
		IPage<ProcessModel> data = actReProcdefService.getProcess(page,processVO);
		for (ProcessModel processModel : data.getRecords()) {
			try {
				processModel.setXmlString(new String(processModel.getXmlString().getBytes(CharsetNames.ISO_8859_1), CharsetNames.UTF_8));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return Result.ok(data);
	}

	/**
	 * 查询版本 管理
	 * */
	@ApiOperation(value = "根据key查询所有版本", notes = "根据key查询所有版本")
	@PostMapping("/getProcessByKey")
	public R<IPage<ProcessModel>> getProcessByKey(ProcessVO processVO,
												  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		Page<ProcessModel> page = new Page<ProcessModel>(pageNo, pageSize);
		return new R<>(actReProcdefService.getlcglxxByKey(page,processVO));
	}
	/**
	 *  更新现有的流程(根据deploymentId)
	 * */
	@ApiOperation(value = "更新现有的流程(根据deploymentId)", notes = "更新现有的流程(根据deploymentId)")
	@PostMapping("/updateProcessXmlString")
	public R updateProcessXmlString(@RequestBody @Validated ProcessVO processVO) {
		return new R<>(actReProcdefService.updateProcessXmlString(processVO));
	}


	/**
	 * 挂起流程
	 * */
	@GetMapping(value = "/suspendProcess")
	public R suspend(String processId) {
		try {
			repositoryService.suspendProcessDefinitionById(processId);
		}catch (Exception e){
			return   R.error(e.getMessage());
		}
		return   R.ok();
	}

	/**
	 * 激活流程
	 * */
	@GetMapping(value = "/activateProcess")
	public R  activateProcessDefinition(String processId) {
		try {
			repositoryService.activateProcessDefinitionById(processId);
		}catch (Exception e){
			return   R.error(e.getMessage());
		}
		return   R.ok();
	}

	/**
	 * 生成流程图
	 *
	 * @param processId 任务ID
	 */
	@RequestMapping(value = "processDiagram", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
		//流程走完的不显示图
		if (pi == null) {
			return null;
		}
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		//使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		String InstanceId = task.getProcessInstanceId();
		List<Execution> executions = runtimeService
				.createExecutionQuery()
				.processInstanceId(InstanceId)
				.list();
		//得到正在执行的Activity的Id
		List<String> activityIds = new ArrayList<>();
		List<String> flows = new ArrayList<>();
		for (Execution exe : executions) {
			List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
			activityIds.addAll(ids);
		}
		//获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
		ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
		ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
		InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);
		byte[] bytes = new byte[in.available()];
		in.read(bytes, 0, in.available());
		return bytes;
	}

	
	/**
	 * 编辑
	 *
	 * @param actReProcdef
	 * @return
	 */
	@AutoLog(value = "procdef-编辑")
	@ApiOperation(value="procdef-编辑", notes="procdef-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ActReProcdef actReProcdef) {
		actReProcdefService.updateById(actReProcdef);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "procdef-通过id删除")
	@ApiOperation(value="procdef-通过id删除", notes="procdef-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		actReProcdefService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "procdef-批量删除")
	@ApiOperation(value="procdef-批量删除", notes="procdef-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.actReProcdefService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "procdef-通过id查询")
	@ApiOperation(value="procdef-通过id查询", notes="procdef-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ActReProcdef actReProcdef = actReProcdefService.getById(id);
		return Result.ok(actReProcdef);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param actReProcdef
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ActReProcdef actReProcdef) {
      return super.exportXls(request, actReProcdef, ActReProcdef.class, "procdef");
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
      return super.importExcel(request, response, ActReProcdef.class);
  }

}
