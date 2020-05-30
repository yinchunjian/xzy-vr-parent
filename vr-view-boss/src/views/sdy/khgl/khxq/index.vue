<template>
	<div class="Public">

		<a-row class="TOP" v-show="!id">
			<div class="table-page-search-wrapper">
				<a-form layout="inline" @keyup.enter.native="searchQuery">
					<a-row :gutter="24">
						<a-col :md="4">
							<a-form-item label="">
								<a-input placeholder="请输入用户编号" v-model="queryParam.userNo"></a-input>
							</a-form-item>
						</a-col>
						<a-col :md="4">
							<a-form-item label="">
								<a-input placeholder="请输入用户名称" v-model="queryParam.userName"></a-input>
							</a-form-item>
						</a-col>
						<a-col :md="4">
							<a-form-item label="">
								<a-input placeholder="请输入水表编号" v-model="queryParam.deviceCode"></a-input>
							</a-form-item>
						</a-col>

						<a-col :md="4">
							<a-form-item label="">
								<a-input placeholder="请输入电话号码" v-model="queryParam.mobileNo"></a-input>
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
		<a-row class="LRAll" :style="!id?'':'height: calc(100% - 0px);'">

			<a-tabs defaultActiveKey="1" @change="callback" style="margin-top: -20px;">
				<a-tab-pane tab="概况信息" key="1">
				</a-tab-pane>
				<a-tab-pane tab="联系人" key="2">
				</a-tab-pane>
				<a-tab-pane tab="下级客户" key="3">
				</a-tab-pane>
				<a-tab-pane tab="跟进记录" key="4">
				</a-tab-pane>
				<a-tab-pane tab="任务记录" key="5">
				</a-tab-pane>
				<a-tab-pane tab="工单记录" key="6">
				</a-tab-pane>
				<a-tab-pane tab="商机记录" key="7">
				</a-tab-pane>
				<a-tab-pane tab="订单记录" key="8">
				</a-tab-pane>
				<a-tab-pane tab="发票记录" key="9">
				</a-tab-pane>
				<a-tab-pane tab="退款记录" key="10">
				</a-tab-pane>
				<a-tab-pane tab="费用记录" key="11">
				</a-tab-pane>
				<a-tab-pane tab="相关附件" key="12">
				</a-tab-pane>				
			</a-tabs>

			<a-row v-show="tabsV=='1'">
				<tableOne ref="tableOne"></tableOne>
			</a-row>
			
		</a-row>

		<!-- <look-diglog ref="lookDia"></look-diglog> -->

	</div>
</template>

<script>
	import tableOne from './tableOne';
	// import tableTow from './tableTow';
	// import tableThree from './tableThree';
	// import tableFour from './tableFour';
	// import tableSix from './tableSix';
	// import tableSeven from './tableSeven'
	// import tableFive from '@/views/modules/dossier/userProfile/modules/detail/deviceChangeRecordList';
	// import lookDialog from './lookDialog';


	export default {
		components: {
			"tableOne": tableOne,
			// "tableTow": tableTow,
			// "tableThree": tableThree,
			// "tableFour": tableFour,
			// "tableSix": tableSix,
			// "tableFive": tableFive,
			// "tableSeven": tableSeven,
			// "look-diglog": lookDialog
		},
		data() {
			return {
				id: "",
				userInfo: {},
				tabsV: "1",
				pagination: {
					current: 1,
					pageSize: 10,
					pageSizeOptions: ['10', '20', '30'],
					showTotal: (total, range) => {
						return range[0] + "-" + range[1] + " 共" + total + "条"
					},
					showQuickJumper: true,
					showSizeChanger: true,
					total: 0
				},
				queryParam: {
					pageNo: 1,
					pageSize: 10,
				},
				tableData: [],
				valveStatusList: [{
					itemCode: 0,
					itemValue: "关"
				}, {
					itemCode: 1,
					itemValue: "开"
				}],
				loading: false
			}
		},
		watch: {
			'$route'(to, from) { // 监听路由是否变化
				console.log("路由监听")
				console.log(to);
				console.log(from)
				if (this.$route.query.id) {
					this.id = this.$route.query.id
					this.setUser({
						id: this.id
					});
				} else {
					this.id = "";
				}
			}
		},
		created() {


		},
		mounted() {
			if (this.$route.query.id) {
				this.id = this.$route.query.id
				this.setUser({
					id: this.id
				});
			} else {
				this.id = "";
			}


		},
		methods: {
			init(id) {
				this.id = id;

				this.setUser({
					id: this.id
				});
			},
			callback(key) {
				this.tabsV = key;
				console.log(key, this.userInfo);
				if (this.userInfo.id) {
					this.setUser(this.userInfo)
				}
			},
			searchQuery() {

				this.$refs.lookDia.queryParam = this.queryParam;
				this.$refs.lookDia.searchQuery();
				this.$refs.lookDia.visible = true;

				console.log(this.tabsV)

			},
			setUser(record) {
				this.userInfo = record;
				console.log(this.userInfo)
				if (this.tabsV == "1") {
					this.$refs.tableOne.searchQuery({
						userId: this.userInfo.id
					});
				} else if (this.tabsV == "2") {
					this.$refs.tableTow.searchQuery({
						userId: this.userInfo.id
					});
				} else if (this.tabsV == "3") {
					this.$refs.tableThree.searchQuery({
						userId: this.userInfo.id
					});
				} else if (this.tabsV == "4") {
					this.$refs.tableFour.searchQuery({
						userId: this.userInfo.id
					});
				} else if (this.tabsV == "5") {
					this.$refs.tableFive.init(this.userInfo.id);

				} else if (this.tabsV == '6') {
					this.$refs.tableSix.searchQuery({
						userId: this.userInfo.id
					});
				} else if (this.tabsV == '7') {
					this.$refs.tableSeven.searchQuery({
						userId: this.userInfo.id
					});
				}
			},
			searchReset() {
				this.queryParam = {
					pageNo: 1,
					pageSize: 10,
				}
			}
		},
	}
</script>

<style>
	.pubInput {
		margin-right: 25px;
	}
</style>
