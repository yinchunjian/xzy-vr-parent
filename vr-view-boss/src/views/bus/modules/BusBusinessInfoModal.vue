<template>
  <j-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-input placeholder="请输入备注" v-decorator="['remark', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="删除标识0-正常,1-已删除">
          <a-input placeholder="请输入删除标识0-正常,1-已删除" v-decorator="['delFlag', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商机编号">
          <a-input placeholder="请输入商机编号" v-decorator="['businessNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商机标题">
          <a-input placeholder="请输入商机标题" v-decorator="['businessTitle', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户id">
          <a-input placeholder="请输入客户id" v-decorator="['customerId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户名称">
          <a-input placeholder="请输入客户名称" v-decorator="['customerName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户联系人">
          <a-input placeholder="请输入客户联系人" v-decorator="['contactId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户联系人姓名">
          <a-input placeholder="请输入客户联系人姓名" v-decorator="['contactName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预计销售金额">
          <a-input-number v-decorator="[ 'salesAmount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="预计签单日期">
          <a-date-picker v-decorator="[ 'signatureDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="销售阶段1、初步洽淡 2、深入沟通 3、流失商机">
          <a-input placeholder="请输入销售阶段1、初步洽淡 2、深入沟通 3、流失商机" v-decorator="['salesStage', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商机类型 1、短缺商机">
          <a-input placeholder="请输入商机类型 1、短缺商机" v-decorator="['businessType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="成交机率 10%20%30%40%50%60%70%80%90%100%">
          <a-input placeholder="请输入成交机率 10%20%30%40%50%60%70%80%90%100%" v-decorator="['dealProbability', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="商机来源  1、电话营销2、主动来电3、客户介绍4、朋友介绍5、独立开发6、网络搜索">
          <a-input placeholder="请输入商机来源  1、电话营销2、主动来电3、客户介绍4、朋友介绍5、独立开发6、网络搜索" v-decorator="['businessSource', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="获取日期">
          <a-date-picker v-decorator="[ 'getDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属人员id">
          <a-input placeholder="请输入归属人员id" v-decorator="['belongUserId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属人员姓名">
          <a-input placeholder="请输入归属人员姓名" v-decorator="['belongUserName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属部门id">
          <a-input placeholder="请输入归属部门id" v-decorator="['belongDepartId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属部门名称">
          <a-input placeholder="请输入归属部门名称" v-decorator="['belongDepartName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="协作人员">
          <a-input placeholder="请输入协作人员" v-decorator="['assistPersonIds', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="协作人员姓名">
          <a-input placeholder="请输入协作人员姓名" v-decorator="['assistPersonNames', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </j-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "BusBusinessInfoModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        },
        url: {
          add: "/bus/busBusinessInfo/add",
          edit: "/bus/busBusinessInfo/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'remark','delFlag','businessNo','businessTitle','customerId','customerName','contactId','contactName','salesAmount','salesStage','businessType','dealProbability','businessSource','belongUserId','belongUserName','belongDepartId','belongDepartName','assistPersonIds','assistPersonNames'))
		  //时间格式化
          this.form.setFieldsValue({signatureDate:this.model.signatureDate?moment(this.model.signatureDate):null})
          this.form.setFieldsValue({getDate:this.model.getDate?moment(this.model.getDate):null})
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.signatureDate = formData.signatureDate?formData.signatureDate.format():null;
            formData.getDate = formData.getDate?formData.getDate.format():null;
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>