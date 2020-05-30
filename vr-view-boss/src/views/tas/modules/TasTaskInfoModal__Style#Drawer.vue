<template>
  <a-drawer
      :title="title"
      :width="800"
      placement="right"
      :closable="false"
      @close="close"
      :visible="visible"
  >

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
          label="任务类型 1、普通任务 2、跟进任务">
          <a-input placeholder="请输入任务类型 1、普通任务 2、跟进任务" v-decorator="['taskType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="任务名称">
          <a-input placeholder="请输入任务名称" v-decorator="['taskName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开始时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'beginTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="结束时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'endTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="紧要程度 1、重要 2、 紧急 3、 普通  4、重要且紧急">
          <a-input placeholder="请输入紧要程度 1、重要 2、 紧急 3、 普通  4、重要且紧急" v-decorator="['importanceLevel', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="关联业务 1、客户 2、联系人3、商机4、订单5、回款6、退款">
          <a-input placeholder="请输入关联业务 1、客户 2、联系人3、商机4、订单5、回款6、退款" v-decorator="['relatedObjectType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="对象id">
          <a-input placeholder="请输入对象id" v-decorator="['objectId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="对象名称">
          <a-input placeholder="请输入对象名称" v-decorator="['objectName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="任务描述">
          <a-input placeholder="请输入任务描述" v-decorator="['taskDesc', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="跟进/跟进人员id">
          <a-input placeholder="请输入跟进/跟进人员id" v-decorator="['chargeUserId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="跟进/负责人员姓名">
          <a-input placeholder="请输入跟进/负责人员姓名" v-decorator="['chargeUserName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="抄送/参与人员id 多个都好分隔">
          <a-input placeholder="请输入抄送/参与人员id 多个都好分隔" v-decorator="['participantUserId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="参与人员名称多个都好分隔">
          <a-input placeholder="请输入参与人员名称多个都好分隔" v-decorator="['participantUserName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="跟进时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'followTime', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="跟进内容">
          <a-input placeholder="请输入跟进内容" v-decorator="['followContent', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="签到地址">
          <a-input placeholder="请输入签到地址" v-decorator="['signAddr', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="签到经度">
          <a-input-number v-decorator="[ 'signLongitude', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="签到维度">
          <a-input-number v-decorator="[ 'signLatitude', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="签到范围 单位(米)">
          <a-input-number v-decorator="[ 'signScope', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="任务提醒 1、准时提醒 2、提前5分钟 3、提前15分钟4、提前30分钟
            5、提前1小时6、提前2小时">
          <a-input placeholder="请输入任务提醒 1、准时提醒 2、提前5分钟 3、提前15分钟4、提前30分钟
            5、提前1小时6、提前2小时" v-decorator="['taskReminder', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="提醒方式 1、系统消息 2、短信 3、邮件">
          <a-input placeholder="请输入提醒方式 1、系统消息 2、短信 3、邮件" v-decorator="['remindWay', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="任务标记">
          <a-input placeholder="请输入任务标记" v-decorator="['taskTag', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="任务状态 0、未处理 1、已处理">
          <a-input placeholder="请输入任务状态 0、未处理 1、已处理" v-decorator="['taskStatus', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "TasTaskInfoModal",
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
          add: "/tas/tasTaskInfo/add",
          edit: "/tas/tasTaskInfo/edit",
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
          this.form.setFieldsValue(pick(this.model,'remark','delFlag','taskType','taskName','importanceLevel','relatedObjectType','objectId','objectName','taskDesc','chargeUserId','chargeUserName','participantUserId','participantUserName','followContent','signAddr','signLongitude','signLatitude','signScope','taskReminder','remindWay','taskTag','taskStatus'))
		  //时间格式化
          this.form.setFieldsValue({beginTime:this.model.beginTime?moment(this.model.beginTime):null})
          this.form.setFieldsValue({endTime:this.model.endTime?moment(this.model.endTime):null})
          this.form.setFieldsValue({followTime:this.model.followTime?moment(this.model.followTime):null})
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
            formData.beginTime = formData.beginTime?formData.beginTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.endTime = formData.endTime?formData.endTime.format('YYYY-MM-DD HH:mm:ss'):null;
            formData.followTime = formData.followTime?formData.followTime.format('YYYY-MM-DD HH:mm:ss'):null;
            
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
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>