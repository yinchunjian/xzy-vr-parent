<template>
	<a-card :bordered="false">

		<!-- 查询区域 -->
		<div class="table-page-search-wrapper">
			<a-form layout="inline" @keyup.enter.native="searchQuery">
				<a-row :gutter="24">

					<a-col :xl="6" :lg="7" :md="8" :sm="24">
						<a-form-item label="备注">
							<a-input placeholder="请输入备注" v-model="queryParam.remark"></a-input>
						</a-form-item>
					</a-col>
					<a-col :xl="6" :lg="7" :md="8" :sm="24">
						<a-form-item label="删除标识0-正常,1-已删除">
							<a-input placeholder="请输入删除标识0-正常,1-已删除" v-model="queryParam.delFlag"></a-input>
						</a-form-item>
					</a-col>
					<template v-if="toggleSearchStatus">
						<a-col :xl="6" :lg="7" :md="8" :sm="24">
							<a-form-item label="任务类型 1、普通任务 2、跟进任务">
								<a-input placeholder="请输入任务类型 1、普通任务 2、跟进任务" v-model="queryParam.taskType"></a-input>
							</a-form-item>
						</a-col>
						<a-col :xl="6" :lg="7" :md="8" :sm="24">
							<a-form-item label="任务名称">
								<a-input placeholder="请输入任务名称" v-model="queryParam.taskName"></a-input>
							</a-form-item>
						</a-col>
						<a-col :xl="6" :lg="7" :md="8" :sm="24">
							<a-form-item label="开始时间">
								<a-input placeholder="请输入开始时间" v-model="queryParam.beginTime"></a-input>
							</a-form-item>
						</a-col>
					</template>
					<a-col :xl="6" :lg="7" :md="8" :sm="24">
						<span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
							<a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
							<a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
							<a @click="handleToggleSearch" style="margin-left: 8px">
								{{ toggleSearchStatus ? '收起' : '展开' }}
								<a-icon :type="toggleSearchStatus ? 'up' : 'down'" />
							</a>
						</span>
					</a-col>

				</a-row>
			</a-form>
		</div>

		<!-- 操作按钮区域 -->
		<div class="table-operator">
			<a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
			<a-button type="primary" icon="download" @click="handleExportXls('任务信息')">导出</a-button>
			<a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl"
			 @change="handleImportExcel">
				<a-button type="primary" icon="import">导入</a-button>
			</a-upload>
			<a-dropdown v-if="selectedRowKeys.length > 0">
				<a-menu slot="overlay">
					<a-menu-item key="1" @click="batchDel">
						<a-icon type="delete" />删除</a-menu-item>
				</a-menu>
				<a-button style="margin-left: 8px"> 批量操作
					<a-icon type="down" />
				</a-button>
			</a-dropdown>
		</div>

		<!-- table区域-begin -->
		<div>
			<div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
				<i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
				<a style="margin-left: 24px" @click="onClearSelected">清空</a>
			</div>

			<a-table ref="table" size="middle" bordered rowKey="id" :columns="columns" :dataSource="dataSource" :pagination="ipagination"
			 :loading="loading" class="j-table-force-nowrap" :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
			 @change="handleTableChange">

				<span slot="action" slot-scope="text, record">
					<a @click="handleEdit(record)">编辑</a>

					<a-divider type="vertical" />
					<a-dropdown>
						<a class="ant-dropdown-link">更多
							<a-icon type="down" /></a>
						<a-menu slot="overlay">
							<a-menu-item>
								<a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
									<a>删除</a>
								</a-popconfirm>
							</a-menu-item>
						</a-menu>
					</a-dropdown>
				</span>

			</a-table>
		</div>
		<!-- table区域-end -->

		<!-- 表单区域 -->
		<tasTaskInfo-modal ref="modalForm" @ok="modalFormOk"></tasTaskInfo-modal>
	</a-card>
</template>

<script>
	import '@/assets/less/TableExpand.less'
	import TasTaskInfoModal from './modules/TasTaskInfoModal'
	import {
		JeecgListMixin
	} from '@/mixins/JeecgListMixin'

	export default {
		name: "TasTaskInfoList",
		mixins: [JeecgListMixin],
		components: {
			TasTaskInfoModal
		},
		data() {
			return {
				description: '任务信息管理页面',
				// 表头
				columns: [{
						title: '#',
						dataIndex: '',
						key: 'rowIndex',
						width: 60,
						align: "center",
						customRender: function(t, r, index) {
							return parseInt(index) + 1;
						}
					},
					{
						title: '备注',
						align: "center",
						dataIndex: 'remark'
					},
					{
						title: '删除标识0-正常,1-已删除',
						align: "center",
						dataIndex: 'delFlag'
					},
					{
						title: '任务类型 1、普通任务 2、跟进任务',
						align: "center",
						dataIndex: 'taskType'
					},
					{
						title: '任务名称',
						align: "center",
						dataIndex: 'taskName'
					},
					{
						title: '开始时间',
						align: "center",
						dataIndex: 'beginTime'
					},
					{
						title: '结束时间',
						align: "center",
						dataIndex: 'endTime'
					},
					{
						title: '紧要程度 1、重要 2、 紧急 3、 普通  4、重要且紧急',
						align: "center",
						dataIndex: 'importanceLevel'
					},
					{
						title: '关联业务 1、客户 2、联系人3、商机4、订单5、回款6、退款',
						align: "center",
						dataIndex: 'relatedObjectType'
					},
					{
						title: '对象id',
						align: "center",
						dataIndex: 'objectId'
					},
					{
						title: '对象名称',
						align: "center",
						dataIndex: 'objectName'
					},
					{
						title: '任务描述',
						align: "center",
						dataIndex: 'taskDesc'
					},
					{
						title: '跟进/跟进人员id',
						align: "center",
						dataIndex: 'chargeUserId'
					},
					{
						title: '跟进/负责人员姓名',
						align: "center",
						dataIndex: 'chargeUserName'
					},
					{
						title: '抄送/参与人员id 多个都好分隔',
						align: "center",
						dataIndex: 'participantUserId'
					},
					{
						title: '参与人员名称多个都好分隔',
						align: "center",
						dataIndex: 'participantUserName'
					},
					{
						title: '跟进时间',
						align: "center",
						dataIndex: 'followTime'
					},
					{
						title: '跟进内容',
						align: "center",
						dataIndex: 'followContent'
					},
					{
						title: '签到地址',
						align: "center",
						dataIndex: 'signAddr'
					},
					{
						title: '签到经度',
						align: "center",
						dataIndex: 'signLongitude'
					},
					{
						title: '签到维度',
						align: "center",
						dataIndex: 'signLatitude'
					},
					{
						title: '签到范围 单位(米)',
						align: "center",
						dataIndex: 'signScope'
					},
					{
						title: '任务提醒 1、准时提醒 2、提前5分钟 3、提前15分钟4、提前30分钟5、 提前1小时6、 提前2小时 ',
						align: "center",
						dataIndex: 'taskReminder'
					},
					{
						title: '提醒方式 1、系统消息 2、短信 3、邮件',
						align: "center",
						dataIndex: 'remindWay'
					},
					{
						title: '任务标记',
						align: "center",
						dataIndex: 'taskTag'
					},
					{
						title: '任务状态 0、未处理 1、已处理',
						align: "center",
						dataIndex: 'taskStatus'
					},
					{
						title: '操作',
						dataIndex: 'action',
						align: "center",
						scopedSlots: {
							customRender: 'action'
						},
					}
				],
				url: {
					list: "/tas/tasTaskInfo/list",
					delete: "/tas/tasTaskInfo/delete",
					deleteBatch: "/tas/tasTaskInfo/deleteBatch",
					exportXlsUrl: "tas/tasTaskInfo/exportXls",
					importExcelUrl: "tas/tasTaskInfo/importExcel",
				},
			}
		},
		computed: {
			importExcelUrl: function() {
				return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
			}
		},
		methods: {

		}
	}
</script>
<style scoped>
	@import '~@assets/less/common.less';
</style>
