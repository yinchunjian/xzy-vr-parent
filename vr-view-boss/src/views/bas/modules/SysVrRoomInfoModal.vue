<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">


        <a-form-item label="所属机构/医院">
          <a-select v-decorator="['hospitalId', {}]" style="width: 120px" @change="changeHospital">
            <a-select-option v-for="item in hospitalList" :value="item.hospitalId">{{item.hospitalName}}</a-select-option>
          </a-select>
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="科室id">
          <a-select v-decorator="['departmentId', {}]" style="width: 120px" @change="changeHospital">
            <a-select-option v-for="item in departList" :value="item.departmentId">{{item.departmentName}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="VR室名称">
          <a-input placeholder="请输入VR室名称" v-decorator="['roomName', {}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="设备id">
          <a-input placeholder="请输入设备id" v-decorator="['deviceId', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="设备地址">
          <a-input placeholder="请输入设备地址" v-decorator="['deviceAddr', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否登录验证">
          <a-select placeholder="请选择是否登录验证" v-decorator="['loginCheck', {}]" style="width: 120px" @change="changeHospital">
            <a-select-option  value="1">是</a-select-option>
            <a-select-option  value="0">否</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-input placeholder="请输入备注" v-decorator="['remark', {}]" />
        </a-form-item>
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction ,getAction} from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "SysVrRoomInfoModal",
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
          add: "/system/sysVrRoomInfo/add",
          edit: "/system/sysVrRoomInfo/edit",
          hospitalList:"/system/sysHospitalInfo/list",
          departList:"/system/sysDepartMentInfo/list"
        },
        hospitalList:[],
        departList:[]
      }
    },
    created () {
      this.$nextTick(() =>{
        this.loadHospitalList();
        this.loadDepartList();
      })
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
          this.form.setFieldsValue(pick(this.model,'remark','delFlag','hospitalId','hospitalName','departmentId','departmentName','roomName','deviceId','deviceAddr','loginCheck'))
		  //时间格式化
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
      changeHospital(value){

      },
      loadHospitalList(){
        var that = this;
        getAction(this.url.hospitalList,{}).then((res)=>{
          if(res.success){
            that.hospitalList = res.result;

          }
        })
      },
      loadDepartList(){
        var that = this;
        getAction(this.url.departList,{}).then((res)=>{
          if(res.success){
            that.departList = res.result;

          }
        })
      }

    }
  }
</script>

<style lang="less" scoped>

</style>