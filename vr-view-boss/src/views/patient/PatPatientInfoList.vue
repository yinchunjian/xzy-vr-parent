<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="主键ID">
              <a-input placeholder="请输入主键ID" v-model="queryParam.userId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="创建人">
              <a-input placeholder="请输入创建人" v-model="queryParam.creater"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="修改人">
              <a-input placeholder="请输入修改人" v-model="queryParam.editor"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="修改时间">
              <a-input placeholder="请输入修改时间" v-model="queryParam.editTime"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="删除标识0-正常,1-已删除">
              <a-input placeholder="请输入删除标识0-正常,1-已删除" v-model="queryParam.delFlag"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('患者信息')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <patPatientInfo-modal ref="modalForm" @ok="modalFormOk"></patPatientInfo-modal>
  </a-card>
</template>

<script>
  import PatPatientInfoModal from './modules/PatPatientInfoModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "PatPatientInfoList",
    mixins:[JeecgListMixin],
    components: {
      PatPatientInfoModal
    },
    data () {
      return {
        description: '患者信息管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '主键ID',
            align:"center",
            dataIndex: 'userId'
           },
		   {
            title: '创建人',
            align:"center",
            dataIndex: 'creater'
           },
		   {
            title: '修改人',
            align:"center",
            dataIndex: 'editor'
           },
		   {
            title: '修改时间',
            align:"center",
            dataIndex: 'editTime'
           },
		   {
            title: '删除标识0-正常,1-已删除',
            align:"center",
            dataIndex: 'delFlag'
           },
		   {
            title: '备注',
            align:"center",
            dataIndex: 'remark'
           },
		   {
            title: '患者编号',
            align:"center",
            dataIndex: 'patientNo'
           },
		   {
            title: '患者姓名',
            align:"center",
            dataIndex: 'patientName'
           },
		   {
            title: '医保卡号',
            align:"center",
            dataIndex: 'hisCardNo'
           },
		   {
            title: '身份证号',
            align:"center",
            dataIndex: 'idCardNo'
           },
		   {
            title: '医院id',
            align:"center",
            dataIndex: 'hospitalId'
           },
		   {
            title: '医院名称',
            align:"center",
            dataIndex: 'hospitalName'
           },
		   {
            title: '婚姻状况 1、已婚 0、未婚',
            align:"center",
            dataIndex: 'maritalStatus'
           },
		   {
            title: '手机号',
            align:"center",
            dataIndex: 'mobileNo'
           },
		   {
            title: '文化程度  1、高中以下2、大专3、本科 4、硕士5、博士6、博士后7、教授',
            align:"center",
            dataIndex: 'cultureStandard'
           },
		   {
            title: '性别',
            align:"center",
            dataIndex: 'sex'
           },
		   {
            title: '行业',
            align:"center",
            dataIndex: 'industry'
           },
		   {
            title: '职业',
            align:"center",
            dataIndex: 'profession'
           },
		   {
            title: '失眠持续时间 1、3个月内',
            align:"center",
            dataIndex: 'insomniaTime'
           },
		   {
            title: '药物服用',
            align:"center",
            dataIndex: 'takDrug'
           },
		   {
            title: '省份ID',
            align:"center",
            dataIndex: 'provinceId'
           },
		   {
            title: '城市ID',
            align:"center",
            dataIndex: 'cityId'
           },
		   {
            title: '区县标识ID',
            align:"center",
            dataIndex: 'countyId'
           },
		   {
            title: '联系地址',
            align:"center",
            dataIndex: 'address'
           },
		   {
            title: '出生年月',
            align:"center",
            dataIndex: 'birthday'
           },
		   {
            title: '紧急联系人',
            align:"center",
            dataIndex: 'contactName'
           },
		   {
            title: '紧急联系人电话',
            align:"center",
            dataIndex: 'contactPhone'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/patient/patPatientInfo/list",
          delete: "/patient/patPatientInfo/delete",
          deleteBatch: "/patient/patPatientInfo/deleteBatch",
          exportXlsUrl: "patient/patPatientInfo/exportXls",
          importExcelUrl: "patient/patPatientInfo/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>