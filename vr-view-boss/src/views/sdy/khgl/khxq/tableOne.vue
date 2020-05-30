<template>

	<grid :User="User">
		<template v-slot:star="row">
			<a-rate style="margin-top: -6px;" allow-half disabled :value="Number(row.data.val)" />
		</template>
	</grid>

</template>

<script>
	import grid from '@/components/elements/grid.vue';

	export default {
		components: {
			grid: grid
		},
		data() {
			return {
				list30List: [],
				userInfo: {},
				url: {
					queryTreeList: "/sys/waterCompanyArea/queryTreeList",
					list: "/arc/deviceInfo/userDevicePage",
					edit: "/arc/deviceInfo/changeStatus",
					queryById: "/arc/deviceInfo/queryDetails"
				},
				queryParam: {
					pageNo: 1,
					pageSize: 10,
				},
				User: []
			}
		},
		watch: {

		},
		created() {

		},
		mounted() {
			setTimeout(() => {
				this.User = [{
					title: "基本信息",
					childs: [{
						childs: [{
							name: "客户名称",
							val: "xxx"
						}, {
							name: "系统编号",
							val: "xxfdkjafkjdjkfdjajflkdajflajflkdaafljdajaljflx"
						}]
					}, {
						childs: [{
							name: "客户类型",
							val: "xxx"
						}, {
							name: "所属行业",
							val: "xxx"
						}]
					}, {
						childs: [{
							name: "客户星级",
							val: "1",
							slotName: "star"
						}, {
							name: "客户来源",
							val: "xxx"
						}]
					}, {
						childs: [{
							name: "客户状态",
							val: "xxx"
						}, {
							name: "客户归属",
							val: "xxx"
						}]
					}]
				},{
					title: "附加信息",
					childs: [{
						childs: [{
							name: "所在地区",
							val: "xxx"
						}, {
							name: "详细地址",
							val: "xxfdkjafkjdjkfdjajflkdajflajflkdaafljdajaljflx"
						}]
					}, {
						childs: [{
							name: "企业规模",
							val: "xxx"
						}, {
							name: "上级客户",
							val: "xxx"
						}]
					}, {
						childs: [{
							name: "下级客户",
							val: "1",
							
						}, {
							name: "备注信息",
							val: "xxx"
						}]
					}]
				},{
					title: "首联系人",
					childs: [{
						childs: [{
							name: "联系人姓名",
							val: "xxx"
						}, {
							name: "手机号码",
							val: "xxfdkjafkjdjkfdjajflkdajflajflkdaafljdajaljflx"
						}]
					}, {
						childs: [{
							name: "企业规模",
							val: "xxx"
						}, {
							name: "上级客户",
							val: "xxx"
						}]
					}, {
						childs: [{
							name: "下级客户",
							val: "1",
							
						}, {
							name: "备注信息",
							val: "xxx"
						}]
					}, {
						childs: [{
							name: "下级客户",
							val: "1",
							
						}, {
							name: "备注信息",
							val: "xxx"
						}]
					}]
				}]
			}, 1000)
		},
		methods: {

		},
	}
</script>

<style>

</style>
