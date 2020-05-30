<template>
	<a-modal :width="900" title="新建任务" v-model="visible" @ok="handleOk" :footer="null">
		<div class="Public" v-if="visible">
			<a-row class="RIGHT" style="width: 100%;height: calc(100% - 110px);">
				<a-row>
					<a-form-model ref="ruleForm" :model="ruleForm" :rules="rules" v-bind="layout">
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="任务标题" prop="pass">
									<a-input placeholder="请输入任务标题" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="密59995599码" prop="pass2">
									<a-input placeholder="请输入密码" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="开始时间" prop="time">
									<a-date-picker style="width:100%;" v-model="ruleForm.time" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="结束时间" prop="time">
									<a-date-picker style="width:100%;" v-model="ruleForm.time" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="紧要程度" prop="time">
									<a-select allowClear placeholder="请选择客户类型" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
										  {{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="关联业务" prop="time">
									<a-select allowClear placeholder="请选择客户类型" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
										  {{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="任务描述" prop="texts">
									<a-textarea placeholder="请输入任务描述" :rows="4" v-model="ruleForm.texts" />
								</a-form-model-item>
							</a-col>

						</a-row>
						<a-row>
							<a-col :md="12">
							</a-col>
							<a-col :md="12" style="text-align: right;">

								<a-button class="butSearch" type="primary" @click="submitForm('ruleForm')">
									提交
								</a-button>
								<a-button class="butReset" type="primary" @click="resetForm('ruleForm')" style="margin-right: 0;">
									重置
								</a-button>

							</a-col>
						</a-row>
					</a-form-model>
				</a-row>

			</a-row>


		</div>
	</a-modal>
</template>

<script>
	export default {
		data() {
			return {
				visible: false,
				lists:[{name:"重要",val:1}],
				ruleForm: {
					pass: '',
				},
				rules: {
					time: [{
						required:true,
						message:"必填！",
						trigger: 'change'
					}],
					pass: [{
						required:true,
						message:"必填！",
						trigger: 'change'
					}],
					pass2: [{
						validator: (rule, value, callback) => {
							callback();
						},
						trigger: 'change'
					}],
				},
				layout: {
					labelCol: {
						span: 8
					},
					wrapperCol: {
						span: 16
					},
				},
			}
		},
		watch: {

		},
		created() {


		},
		mounted() {


		},
		methods: {
			onChange() {
				console.log(arguments)
			},
			handleOk() {

			},
			submitForm(formName) {
				
				let ups = JSON.parse(JSON.stringify(this.ruleForm))
				if (ups.time) {
					ups.time = ups.time.split("T")[0];
				}
				
				this.$refs[formName].validate(valid => {
					console.log(valid,ups)
					if (valid) {
						alert('submit!');
					} else {
						console.log('error submit!!');
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
		},
	}
</script>

<style>
	.pubInput {
		margin-right: 25px;
	}
</style>
