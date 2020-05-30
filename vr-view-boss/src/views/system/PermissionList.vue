<template>
	<a-card :bordered="false" class="LRAll" style="padding: 20px 20px 20px;height: calc(100% - 0px);">

		<!-- 操作按钮区域 -->
		<div class="table-operator">
			<div class="tableTitle">
				
				<span class="elName"><a-icon type="book" /> 菜单列表</span>

				<div class="buts">
					<a-button class="butAdd" @click="handleAdd" type="primary" icon="plus">新增</a-button>
					<a-button class="butDelete" @click="batchDel" v-if="selectedRowKeys.length > 0" type="primary" icon="delete">批量删除
					</a-button>
				</div>
			</div>

		</div>

		<!-- table区域-begin -->
		<div>

			<!-- <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>已选择&nbsp;<a style="font-weight: 600">{{
        selectedRowKeys.length }}</a>项&nbsp;&nbsp;
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div> -->

			<a-table bordered :columns="columns" size="middle" :pagination="false" :dataSource="dataSource" :loading="loading"
			 :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}">

				<span slot="action" slot-scope="text, record">
					
					<a type="link" @click="handleEdit(record)">编辑</a>
					
					<a-dropdown>
						<a class="ant-dropdown-link">
							更多
							<a-icon type="down" />
						</a>
						<a-menu slot="overlay">
							<a-menu-item>
								<a href="javascript:;" @click="handleDetail(record)">详情</a>
							</a-menu-item>
							<a-menu-item>
								<a href="javascript:;" @click="handleAddSub(record)">添加子菜单</a>
							</a-menu-item>
							<a-menu-item>
								<a href="javascript:;" @click="handleDataRule(record)">数据规则</a>
							</a-menu-item>

							<a-menu-item>
								<a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
									<a>删除</a>
								</a-popconfirm>
							</a-menu-item>
						</a-menu>
					</a-dropdown>
				</span>
				<!-- 字符串超长截取省略号显示 -->
				<span slot="url" slot-scope="text">
					<j-ellipsis :value="text" :length="25" />
				</span>
				<!-- 字符串超长截取省略号显示-->
				<span slot="component" slot-scope="text">
					<j-ellipsis :value="text" />
				</span>
			</a-table>

		</div>
		<!-- table区域-end -->

		<permission-modal ref="modalForm" @ok="modalFormOk"></permission-modal>
		<permission-data-rule-list ref="PermissionDataRuleList" @ok="modalFormOk"></permission-data-rule-list>

	</a-card>
</template>

<script>
	import PermissionModal from './modules/PermissionModal'
	import {
		getPermissionList
	} from '@/api/api'
	import {
		JeecgListMixin
	} from '@/mixins/JeecgListMixin'
	import PermissionDataRuleList from './PermissionDataRuleList'
	import JEllipsis from '@/components/jeecg/JEllipsis'

	const columns = [{
			title: '菜单名称',
			dataIndex: 'name',
			key: 'name',
			width: 320,
			ellipsis: true,
		}, {
			title: '菜单类型',
			dataIndex: 'menuType',
			key: 'menuType',
			ellipsis: true,
			customRender: function(text) {
				if (text == 0) {
					return '菜单'
				} else if (text == 1) {
					return '菜单'
				} else if (text == 2) {
					return '按钮/权限'
				} else {
					return text
				}
			}
		},
		/*{
		      title: '权限编码',
		      dataIndex: 'perms',
		      key: 'permissionCode',
		    },*/
		{
			title: 'icon',
			dataIndex: 'icon',
			key: 'icon'
		},
		{
			title: '组件',
			dataIndex: 'component',
			key: 'component',
			ellipsis: true,
			scopedSlots: {
				customRender: 'component'
			}
		},
		{
			title: '路径',
			dataIndex: 'url',
			key: 'url',
			ellipsis: true,
			scopedSlots: {
				customRender: 'url'
			}
		},
		{
			title: '排序',
			dataIndex: 'sortNo',
			key: 'sortNo',
			ellipsis: true,
			width: 80
		},
		{
			title: '操作',
			dataIndex: 'action',
			scopedSlots: {
				customRender: 'action'
			},
			align: 'center',
			width: 140
		}
	]

	export default {
		name: 'PermissionList',
		mixins: [JeecgListMixin],
		components: {
			PermissionDataRuleList,
			PermissionModal,
			JEllipsis
		},
		data() {
			return {
				description: '这是菜单管理页面',
				// 表头
				columns: columns,
				loading: false,
				url: {
					list: '/sys/permission/list',
					delete: '/sys/permission/delete',
					deleteBatch: '/sys/permission/deleteBatch'
				}
			}
		},
		methods: {
			loadData() {
				this.dataSource = []
				getPermissionList().then((res) => {
					if (res.success) {
						console.log(res.result)
						this.dataSource = res.result
					}
				})
			},
			// 打开数据规则编辑
			handleDataRule(record) {
				this.$refs.PermissionDataRuleList.edit(record)
			},
			handleAddSub(record) {
				this.$refs.modalForm.title = "添加子菜单";
				this.$refs.modalForm.localMenuType = 1;
				this.$refs.modalForm.disableSubmit = false;
				this.$refs.modalForm.edit({
					status: '1',
					permsType: '1',
					route: true,
					'parentId': record.id
				});
			}
		}
	}
</script>
<style scoped>
	@import '~@assets/less/common.less'
</style>
