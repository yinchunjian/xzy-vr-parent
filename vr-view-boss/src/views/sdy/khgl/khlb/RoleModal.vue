<template>
	<a-modal :width="1000" :title="title" v-model="visible" @ok="handleOk" :footer="null">
		
		<div class="Public" v-if="visible">
			<a-row class="RIGHT" style="width: 100%;height: calc(100% - 110px);">
				<a-row>
					<a-form-model ref="ruleForm" :model="ruleForm" :rules="rules" v-bind="layout">
						<div class="tableTitleRight">
							<span class="elName"><a-icon type="book" /> 基本信息</span>
						</div>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="客户名称" prop="pass">
									<a-input placeholder="请输入" v-model="ruleForm.roleName" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="助记名称" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="客户类型" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="客户状态" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="客户类型" prop="star">
									<a-rate v-model="ruleForm.star" allow-half />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="客户来源" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="归属人员" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
						</a-row>
						
						<div class="tableTitleRight">
							<span class="elName"><a-icon type="book" /> 附加信息</span>
						</div>
						
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="所在地区" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="详细地址" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="企业规模" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="上级客户" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="备注信息" prop="texts">
									<a-textarea placeholder="请输入" :rows="4" v-model="ruleForm.texts" />
								</a-form-model-item>
							</a-col>						
						</a-row>
						
						<div class="tableTitleRight">
							<span class="elName"><a-icon type="book" /> 首要联系人</span>
						</div>
						
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="联系人姓名" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
								
							</a-col>
							<a-col :md="12">
								
								<a-form-model-item has-feedback label="尊称" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
							
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="角色" prop="time">
									<a-select allowClear placeholder="请选择" v-model="ruleForm.valveStatus">
										<a-select-option v-for="i in lists" :key="i.val">
											{{i.name}}
										</a-select-option>
									</a-select>
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="生日" prop="time">
									<a-date-picker style="width:100%;" v-model="ruleForm.time" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="部门职务" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="手机号码" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="固定电话" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="传真号码" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="电子邮箱" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="微信账号" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="QQ号码" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="所在地区" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
						</a-row>
						<a-row>
							<a-col :md="12">
								<a-form-model-item has-feedback label="详细地址" prop="pass2">
									<a-input placeholder="请输入" v-model="ruleForm.pass" type="text" />
								</a-form-model-item>
							</a-col>
							<a-col :md="12">
								<a-form-model-item has-feedback label="备注信息" prop="texts">
									<a-textarea placeholder="请输入" :rows="4" v-model="ruleForm.texts" />
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
				title:"新建客户",
				visible: false,
				lists: [{
					name: "重要",
					val: 1
				}],
				ruleForm: {
					pass: '',
				},
				rules: {
					time: [{
						required: true,
						message: "必填！",
						trigger: 'change'
					}],
					pass: [{
						required: true,
						message: "必填！",
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
			add() {
				
				this.edit({});
			},
			edit(record) {
				console.log(record)
				
				this.ruleForm = Object.assign({}, record);
				this.visible = true;
			},
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
					console.log(valid, ups)
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
