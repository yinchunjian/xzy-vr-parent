<template>
	<div class="Public">
		<a-row class="TOP">
			<div class="table-page-search-wrapper">
				<a-form layout="inline" @keyup.enter.native="searchQuery">
					<a-row :gutter="24">
						<a-col :md="4">
							<a-form-item label="">
								<a-input placeholder="请输入客户编号" v-model="queryParam.userNo"></a-input>
							</a-form-item>
						</a-col>
						<a-col :md="4">
							<a-form-item label="">
								<a-select allowClear placeholder="请选择客户星级" v-model="queryParam.valveStatus">
									<a-select-option value="0">已关阀</a-select-option>
									<a-select-option value="1">已开阀</a-select-option>
								</a-select>
							</a-form-item>
						</a-col>
						<a-col :md="4">
							<a-form-item label="">
								<a-select allowClear placeholder="请选择客户类型" v-model="queryParam.valveStatus">
									<a-select-option value="0">已关阀</a-select-option>
									<a-select-option value="1">已开阀</a-select-option>
								</a-select>
							</a-form-item>
						</a-col>
						<a-col :md="4">
							<a-form-item label="">
								<a-select allowClear placeholder="请选择客户状态" v-model="queryParam.valveStatus">
									<a-select-option value="0">已关阀</a-select-option>
									<a-select-option value="1">已开阀</a-select-option>
								</a-select>
							</a-form-item>
						</a-col>
						<a-col :md="4">
							<a-form-item label="">
								<a-date-picker style="width:100%;" v-model="times" @change="onChange" />
							</a-form-item>
						</a-col>
						<a-col :md="2">
							<span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
								<a-button class="butSearch" type="primary" @click="searchQuery" icon="search">查询</a-button>
								<a-button class="butReset" type="primary" @click="searchReset" icon="reload">重置</a-button>
							</span>
						</a-col>
					</a-row>
				</a-form>
			</div>
		</a-row>

		<a-row class="LRAll">
			<a-row>

				<div class="tableTitle tableTitleRight">
					<span class="elName">
						<a-icon type="book" /> 欠费户表</span>
					<div class="buts">
						<a-button class="butAdd" type="primary" icon="plus" @click="handleAdd">创建客户</a-button>
						<a-button class="butAdd" type="primary" icon="plus" @click="add">新建任务</a-button>
						<a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl"
						 @change="handleImportExcel">
							<a-button class="butReset" type="primary" icon="import">导入</a-button>
						</a-upload>
						<a-button class="butReset" type="primary" icon="export" @click="handleExportXls('欠费用户')">导出</a-button>

					</div>
				</div>


				<a-table ref="table" size="middle" bordered :columns="columns" :dataSource="dataSource" :pagination="ipagination"
				 :loading="loading" @change="handleTableChange">

					<span slot="userName" slot-scope="text, record">
						<a v-if="record.roleName" @click="toUser(record, index)">{{record.roleName}}</a>
					</span>
					<span slot="star" slot-scope="text, record">
						<a-rate :default-value="Number(record.star)" allow-half />
					</span>
					<span slot="action" slot-scope="text, record">
						<a @click="handleEdit(record)">编辑</a>
						<a @click="handleTableChange(record, index)">删除</a>
					</span>

				</a-table>

			</a-row>

		</a-row>
		
		<role-modal ref="modalForm" @ok="modalFormOk"></role-modal>
		<look-diglog ref="lookDia"></look-diglog>
	</div>
</template>

<script>
	// import {
	// 	arrearsUserPage,
	// 	closeValveControl,
	// 	sendSmsMessage
	// } from '@/api/sdy/cxtj/qfhbcz';
	
	import RoleModal from './RoleModal'
	import {
		JeecgListMixin
	} from '@/mixins/JeecgListMixin'

	import Vue from 'vue'
	import {
		ACCESS_TOKEN
	} from "@/store/mutation-types"

	import lookDialog from './lookDialog';

	export default {
		mixins: [JeecgListMixin],
		components: {
			RoleModal,
			"look-diglog": lookDialog
		},
		data() {
			return {
				times: "",
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
						title: '客户名称',
						align: "center",
						dataIndex: 'roleName',
						ellipsis: true,
						scopedSlots: {
							customRender: 'userName'
						},
					},
					{
						title: '客户类型',
						align: "center",
						dataIndex: 'itemName',
						ellipsis: true,
					},
					{
						title: '客户星级',
						align: "center",
						dataIndex: 'star',
						ellipsis: true,
						scopedSlots: {
							customRender: 'star'
						},
					},
					{
						title: '首联系人',
						dataIndex: 'installAddress',
						align: "center",
						ellipsis: true,
					},
					{
						title: '手机号码',
						dataIndex: 'currentTotal',
						align: "center",
						ellipsis: true,
					},
					{
						title: '归宿人员',
						dataIndex: 'meterTime',
						align: "center",
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
					exportXlsUrl: "/sys/role/exportXls",
					importExcelUrl: "sys/role/importExcel",
				},
			}
		},
		watch: {
			queryParam(val){
				console.log(val.time)
				if(!val.time){
					this.times = "";
				}
				
			}
		},
		computed: {
			importExcelUrl: function() {
				return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
			}
		},
		created() {
			this.$nextTick(() => {
				//this.searchQuery();
			})

		},
		mounted() {


		},
		methods: {
			delChange(row) {
				console.log(row)
			},
			toUser(row) {
				console.log(row);
				this.$router.push({
					path: "/khgl/khxq",
					query: {

					}
				});
			},
			add() {
				this.$refs.lookDia.visible = true;

			},
			onChange(date, dateString) {
				console.log(date, dateString);
				this.queryParam.time = dateString;
			},
		},
	}
</script>

<style>

</style>
