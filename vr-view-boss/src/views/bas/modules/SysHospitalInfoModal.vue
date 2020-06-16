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

        <a-form-item :labelCol="labelCol"
               :wrapperCol="wrapperCol"
               label="所属系统">
            <a-checkbox-group
              v-model="groupList"
              name="checkboxgroup"
              :options="plainOptions"
              @change="onChange"
            />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="医院编码">
          <a-input placeholder="请输入医院编码" v-decorator="['hospitalNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="医院名称">
          <a-input placeholder="请输入医院名称" v-decorator="['hospitalName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="医院照片">
          <a-upload
            name="avatar"
            list-type="picture-card"
            class="avatar-uploader"
            :show-upload-list="false"
            action="url.uploadUrl"
            :before-upload="beforeUpload"
            @change="handleChange"
          >
            <img v-if="imageUrl" :src="imageUrl" alt="avatar" />
            <div v-else>
              <a-icon :type="loading ? 'loading' : 'plus'" />
              <div class="ant-upload-text">
                Upload
              </div>
            </div>
          </a-upload>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="所属区域">
          <a-cascader :options="areaOptions" @change="onChange" v-model="areaList" placeholder="Please select" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="医院地址">
          <a-input placeholder="请输入医院地址" v-decorator="['hospitalAddress', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系人">
          <a-input placeholder="请输入联系人" v-decorator="['contactPerson', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系人手机号">
          <a-input placeholder="请输入联系人手机号" v-decorator="['contactMobile', {}]" />
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

  function getBase64(img, callback) {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
  }

  export default {
    name: "SysHospitalInfoModal",
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
          add: "/system/sysHospitalInfo/add",
          edit: "/system/sysHospitalInfo/edit",
          uploadUrl:window._CONFIG['domianURL'] + "/sys/common/upload",
          groupList:"/system/sysSystemInfo/list"
        },
        areaOptions: require('@/assets/json/area_mini.json'),
        areaList: null,
        loading: false,
        imageUrl: '',
        groupList:[],
        plainOptions:[]
      }
    },
    created () {
      this.$nextTick(() =>{
        this.loadGroupList();
      })
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        var that = this;
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        that.areaList = [];
        console.log(this.model)
        if (that.model && that.model.provinceId) {
          that.areaList.push(that.model.provinceId + "")
          that.areaList.push(that.model.cityId + "")
          that.areaList.push(that.model.countyId + "")
        }
        console.log(this.areaList)

        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'remark','delFlag','hospitalNo','hospitalName','hospitalLogo','provinceId','cityId','countyId','hospitalAddress','contactPerson','contactMobile'))
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

            // 区域数据转换
            var areaList = this.areaList;
            if (areaList && areaList.length == 3) {
              formData.provinceId = areaList[0];
              formData.cityId = areaList[1];
              formData.countyId = areaList[2];
            }
            // 获取系统id
            formData.groupList = this.groupList;

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
      onChange(value, selectedOptions) {
        console.log(value, selectedOptions);
        this.areaList = value;
      },
      handleChange(info) {
        if (info.file.status === 'uploading') {
          this.loading = true;
          return;
        }
        if (info.file.status === 'done') {
          // Get this url from response in real world.
          getBase64(info.file.originFileObj, imageUrl => {
            this.imageUrl = imageUrl;
            this.loading = false;
          });
        }
      },
      beforeUpload(file) {
        const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJpgOrPng) {
          this.$message.error('You can only upload JPG file!');
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('Image must smaller than 2MB!');
        }
        return isJpgOrPng && isLt2M;
      },
      loadGroupList(){
        var that = this;
        getAction(this.url.groupList,{}).then((res)=>{
          if(res.success){
            var groupList = res.result;
            groupList.forEach((item,index) =>{
              var item = {
                label:item.systemName,
                value:item.id
              }

              that.plainOptions.push(item);
            })
          }
        })
      }

    }
  }
</script>

<style lang="less" scoped>
  .avatar-uploader > .ant-upload {
    width: 128px;
    height: 128px;
  }
  .ant-upload-select-picture-card i {
    font-size: 32px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }
</style>