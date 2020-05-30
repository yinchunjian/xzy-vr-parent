<template>
	<div class="tableOneInfo">

		<div v-for="(item,index) in User" :key="index">
			<div class="tableTitleRight" :style="index=='0'?'':'margin-top: 20px;'">
				<span class="elName">
					<a-icon type="book" /> {{item.title}}
				</span>
			</div>
			<a-row :gutter="24" v-for="(rows,index) in item.childs" :key="index" :style="index=='0'?'margin: 0;border-top: solid 1px rgb(221, 221, 221);':''">
				<a-col :md="24/rows.childs.length" style="padding: 0;" v-for="(cols,index) in rows.childs" :key="index">
					<span class="title">{{cols.name}}：</span>
					<span v-if="cols.slotName" class="val"><slot :name="cols.slotName" :data="cols"></slot></span>
					<span v-else class="val">{{cols.val}}</span>
				</a-col>

			</a-row>
		</div>

	</div>

</template>

<script>
	export default {
		props: {
			User: {
				type: Array,
				default: ()=>{
					return []
				}
			}
		},
		data() {
			return {
				// User: [{
				// 	title: "基本信息",
				// 	childs: [{
				// 		childs: [{
				// 			name: "用户名称",
				// 			val: "xxx"
				// 		}, {
				// 			name: "用户编号",
				// 			val: "xxx"
				// 		}]
				// 	}, {
				// 		childs: [{
				// 			name: "用户名称",
				// 			val: "xxx"
				// 		}, {
				// 			name: "用户编号",
				// 			val: "xxx"
				// 		}]
				// 	}]
				// }],
			}
		},
		watch: {

		},
		created() {

		},
		mounted() {

		},
		methods: {

		},
	}
</script>

<style>

</style>
