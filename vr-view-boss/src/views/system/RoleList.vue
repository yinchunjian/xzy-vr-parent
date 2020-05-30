<template>
	<a-card :bordered="false" class="LRAll" style="height: calc(100% - 0px);">

		<!-- 查询区域 -->
		<div class="table-page-search-wrapper" style="height: 50px;">
			<!-- 搜索区域 -->
			<a-form layout="inline" @keyup.enter.native="searchQuery">
				<a-row :gutter="24">
					<a-col :md="4">
						<a-form-item label="">
							<a-input placeholder="请输入名称查询" v-model="queryParam.roleName"></a-input>
						</a-form-item>
					</a-col>
					<a-col :md="8">
						<a-form-item label="">
							<j-date v-model="queryParam.createTime_begin" :showTime="true" date-format="YYYY-MM-DD HH:mm:ss" style="width:45%"
							 placeholder="请选择开始时间"></j-date>
							<span style="width: 10px;"> ~ </span>
							<j-date v-model="queryParam.createTime_end" :showTime="true" date-format="YYYY-MM-DD HH:mm:ss" style="width:45%"
							 placeholder="请选择结束时间"></j-date>
						</a-form-item>
					</a-col>
					<span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
						<a-col :md="4">
							<a-button class="butSearch" type="primary" @click="searchQuery">查询</a-button>
							<a-button class="butReset" @click="searchReset">重置</a-button>
						</a-col>
					</span>
				</a-row>
			</a-form>
		</div>

		<!-- 操作按钮区域 -->

		<div class="tableTitle">

			<span class="elName">
				<a-icon type="book" /> 列表</span>

			<div class="buts">

				<a-button class="butAdd" @click="handleAdd" type="primary" icon="plus">新增</a-button>
				<a-dropdown v-if="selectedRowKeys.length > 0">
					<a-menu slot="overlay">
						<a-menu-item key="1" @click="batchDel">
							<a-icon type="delete" />删除</a-menu-item>
					</a-menu>
					<a-button class="butReset">
						批量操作
						<a-icon type="down" />
					</a-button>
				</a-dropdown>

			</div>
		</div>



		<!-- table区域-begin -->
		<div>
			<!-- <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择&nbsp;<a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项&nbsp;&nbsp;
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div> -->

			<a-table ref="table" size="middle" bordered rowKey="id" :columns="columns" :dataSource="dataSource" :pagination="ipagination"
			 :loading="loading" :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}" @change="handleTableChange">

				<span slot="action" slot-scope="text, record">
					<a @click="handleEdit(record)" style="margin-right: 10px;">编辑</a>


					<a-dropdown>
						<a class="ant-dropdown-link">
							更多
							<a-icon type="down" />
						</a>
						<a-menu slot="overlay">
							<a-menu-item>
								<a @click="handlePerssion(record.id)">授权</a>
							</a-menu-item>
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
		<role-modal ref="modalForm" @ok="modalFormOk"></role-modal>
		<user-role-modal ref="modalUserRole"></user-role-modal>
	</a-card>
</template>

<script>
	import RoleModal from './modules/RoleModal'
	import UserRoleModal from './modules/UserRoleModal'
	import {
		JeecgListMixin
	} from '@/mixins/JeecgListMixin'
	import JDate from '@/components/jeecg/JDate'

	export default {
		name: "RoleList",
		mixins: [JeecgListMixin],
		components: {
			RoleModal,
			UserRoleModal,
			JDate
		},
		data() {
			return {

				description: '角色管理页面',
				// 查询条件
				queryParam: {
					roleName: '',
				},
				// 表头
				columns: [{
						title: '序号',
						dataIndex: '',
						key: 'rowIndex',
						width: 80,
						align: "center",
						customRender: function(t, r, index) {
							return parseInt(index) + 1;
						}
					},
					{
						title: '角色名称',
						align: "center",
						dataIndex: 'roleName',
						ellipsis: true,
					},
					{
						title: '角色编码',
						align: "center",
						dataIndex: 'roleCode',
						ellipsis: true,
					},
					{
						title: '备注',
						align: "center",
						dataIndex: 'description',
						ellipsis: true,
					},
					{
						title: '创建时间',
						dataIndex: 'createTime',
						align: "center",
						sorter: true,
						ellipsis: true,
					},
					{
						title: '更新时间',
						dataIndex: 'updateTime',
						align: "center",
						sorter: true,
						ellipsis: true,
					},
					{
						title: '操作',
						dataIndex: 'action',
						align: "center",
						width: 140,
						scopedSlots: {
							customRender: 'action'
						},
					}
				],
				url: {
					list: "/sys/role/list",
					delete: "/sys/role/delete",
					deleteBatch: "/sys/role/deleteBatch",
					exportXlsUrl: "/sys/role/exportXls",
					importExcelUrl: "sys/role/importExcel",
				},
			}
		},
		computed: {
			importExcelUrl: function() {
				return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
			}
		},
		methods: {
			handlePerssion: function(roleId) {
				// alert(roleId);
				this.$refs.modalUserRole.show(roleId);
			},
			onChangeDate(date, dateString) {
				console.log(date, dateString);
			},
		}
	}
</script>
<style scoped>
	@import '~@assets/less/common.less'
</style>
