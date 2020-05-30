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
							<a-form-item label="客户编号">
								<a-input placeholder="请输入客户编号" v-model="queryParam.customerNo"></a-input>
							</a-form-item>
						</a-col>
						<a-col :xl="6" :lg="7" :md="8" :sm="24">
							<a-form-item label="客户名称">
								<a-input placeholder="请输入客户名称" v-model="queryParam.customerName"></a-input>
							</a-form-item>
						</a-col>
						<a-col :xl="6" :lg="7" :md="8" :sm="24">
							<a-form-item label="助记名称">
								<a-input placeholder="请输入助记名称" v-model="queryParam.mnemonicName"></a-input>
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
			<a-button type="primary" icon="download" @click="handleExportXls('客户信息')">导出</a-button>
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
		<cusCustomerInfo-modal ref="modalForm" @ok="modalFormOk"></cusCustomerInfo-modal>
	</a-card>
</template>

<script>
	import '@/assets/less/TableExpand.less'
	import CusCustomerInfoModal from './modules/CusCustomerInfoModal'
	import {
		JeecgListMixin
	} from '@/mixins/JeecgListMixin'

	export default {
		name: "CusCustomerInfoList",
		mixins: [JeecgListMixin],
		components: {
			CusCustomerInfoModal
		},
		data() {
			return {
				description: '客户信息管理页面',
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
						title: '客户编号',
						align: "center",
						dataIndex: 'customerNo'
					},
					{
						title: '客户名称',
						align: "center",
						dataIndex: 'customerName'
					},
					{
						title: '助记名称',
						align: "center",
						dataIndex: 'mnemonicName'
					},
					{
						title: '所属行业id',
						align: "center",
						dataIndex: 'industryId'
					},
					{
						title: '所属行业名称',
						align: "center",
						dataIndex: 'industryName'
					},
					{
						title: '客户类型 1、企业 2、个人  3、代理商',
						align: "center",
						dataIndex: 'customerType'
					},
					{
						title: '客户星级 1-5星级',
						align: "center",
						dataIndex: 'customerStar'
					},
					{
						title: '客户来源1、 电话营销 2、主动来电3、客户介绍4、朋友介绍 5、独立开发6、 网络搜索 ',
						align: "center",
						dataIndex: 'customerSource'
					},
					{
						title: '归属人员id',
						align: "center",
						dataIndex: 'belongUserId'
					},
					{
						title: '归属人员姓名',
						align: "center",
						dataIndex: 'belongUserName'
					},
					{
						title: '省份ID',
						align: "center",
						dataIndex: 'provinceId'
					},
					{
						title: '城市ID',
						align: "center",
						dataIndex: 'cityId'
					},
					{
						title: '区县标识ID',
						align: "center",
						dataIndex: 'countyId'
					},
					{
						title: '详细地址',
						align: "center",
						dataIndex: 'detailAddress'
					},
					{
						title: '企业规模 1、10人以内2、10-20人 3、21人-50人4、51人-200人5、201人-500人6、500人以上',
						align: "center",
						dataIndex: 'customerScale'
					},
					{
						title: '上级客户',
						align: "center",
						dataIndex: 'parentId'
					},
					{
						title: '上级客户名称',
						align: "center",
						dataIndex: 'parentName'
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
					list: "/cus/cusCustomerInfo/list",
					delete: "/cus/cusCustomerInfo/delete",
					deleteBatch: "/cus/cusCustomerInfo/deleteBatch",
					exportXlsUrl: "cus/cusCustomerInfo/exportXls",
					importExcelUrl: "cus/cusCustomerInfo/importExcel",
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
