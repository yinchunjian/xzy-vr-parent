<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="备注">
              <a-input placeholder="请输入备注" v-model="queryParam.remark"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="删除标识0-正常,1-已删除">
              <a-input placeholder="请输入删除标识0-正常,1-已删除" v-model="queryParam.delFlag"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="商机编号">
              <a-input placeholder="请输入商机编号" v-model="queryParam.businessNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="商机标题">
              <a-input placeholder="请输入商机标题" v-model="queryParam.businessTitle"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="客户id">
              <a-input placeholder="请输入客户id" v-model="queryParam.customerId"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
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
      <a-button type="primary" icon="download" @click="handleExportXls('商机信息')">导出</a-button>
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
        class="j-table-force-nowrap"
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
    <busBusinessInfo-modal ref="modalForm" @ok="modalFormOk"></busBusinessInfo-modal>
  </a-card>
</template>

<script>
  import '@/assets/less/TableExpand.less'
  import BusBusinessInfoModal from './modules/BusBusinessInfoModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "BusBusinessInfoList",
    mixins:[JeecgListMixin],
    components: {
      BusBusinessInfoModal
    },
    data () {
      return {
        description: '商机信息管理页面',
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
            title: '备注',
            align:"center",
            dataIndex: 'remark'
           },
		   {
            title: '删除标识0-正常,1-已删除',
            align:"center",
            dataIndex: 'delFlag'
           },
		   {
            title: '商机编号',
            align:"center",
            dataIndex: 'businessNo'
           },
		   {
            title: '商机标题',
            align:"center",
            dataIndex: 'businessTitle'
           },
		   {
            title: '客户id',
            align:"center",
            dataIndex: 'customerId'
           },
		   {
            title: '客户名称',
            align:"center",
            dataIndex: 'customerName'
           },
		   {
            title: '客户联系人',
            align:"center",
            dataIndex: 'contactId'
           },
		   {
            title: '客户联系人姓名',
            align:"center",
            dataIndex: 'contactName'
           },
		   {
            title: '预计销售金额',
            align:"center",
            dataIndex: 'salesAmount'
           },
		   {
            title: '预计签单日期',
            align:"center",
            dataIndex: 'signatureDate'
           },
		   {
            title: '销售阶段1、初步洽淡 2、深入沟通 3、流失商机',
            align:"center",
            dataIndex: 'salesStage'
           },
		   {
            title: '商机类型 1、短缺商机',
            align:"center",
            dataIndex: 'businessType'
           },
		   {
            title: '成交机率 10%20%30%40%50%60%70%80%90%100%',
            align:"center",
            dataIndex: 'dealProbability'
           },
		   {
            title: '商机来源  1、电话营销2、主动来电3、客户介绍4、朋友介绍5、独立开发6、网络搜索',
            align:"center",
            dataIndex: 'businessSource'
           },
		   {
            title: '获取日期',
            align:"center",
            dataIndex: 'getDate'
           },
		   {
            title: '归属人员id',
            align:"center",
            dataIndex: 'belongUserId'
           },
		   {
            title: '归属人员姓名',
            align:"center",
            dataIndex: 'belongUserName'
           },
		   {
            title: '归属部门id',
            align:"center",
            dataIndex: 'belongDepartId'
           },
		   {
            title: '归属部门名称',
            align:"center",
            dataIndex: 'belongDepartName'
           },
		   {
            title: '协作人员',
            align:"center",
            dataIndex: 'assistPersonIds'
           },
		   {
            title: '协作人员姓名',
            align:"center",
            dataIndex: 'assistPersonNames'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/bus/busBusinessInfo/list",
          delete: "/bus/busBusinessInfo/delete",
          deleteBatch: "/bus/busBusinessInfo/deleteBatch",
          exportXlsUrl: "bus/busBusinessInfo/exportXls",
          importExcelUrl: "bus/busBusinessInfo/importExcel",
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
  @import '~@assets/less/common.less';
</style>