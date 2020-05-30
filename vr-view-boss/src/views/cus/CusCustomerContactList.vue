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
            <a-form-item label="客户id">
              <a-input placeholder="请输入客户id" v-model="queryParam.customerId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="联系人姓名">
              <a-input placeholder="请输入联系人姓名" v-model="queryParam.contactName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="性别 1、男 0、女 2、未知">
              <a-input placeholder="请输入性别 1、男 0、女 2、未知" v-model="queryParam.sex"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('客户联系人')">导出</a-button>
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
    <cusCustomerContact-modal ref="modalForm" @ok="modalFormOk"></cusCustomerContact-modal>
  </a-card>
</template>

<script>
  import '@/assets/less/TableExpand.less'
  import CusCustomerContactModal from './modules/CusCustomerContactModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "CusCustomerContactList",
    mixins:[JeecgListMixin],
    components: {
      CusCustomerContactModal
    },
    data () {
      return {
        description: '客户联系人管理页面',
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
            title: '客户id',
            align:"center",
            dataIndex: 'customerId'
           },
		   {
            title: '联系人姓名',
            align:"center",
            dataIndex: 'contactName'
           },
		   {
            title: '性别 1、男 0、女 2、未知',
            align:"center",
            dataIndex: 'sex'
           },
		   {
            title: '角色 1、普通人2、决策人3、分项决策人4、商务决策5、财务决策6、使用人7、意见影响人',
            align:"center",
            dataIndex: 'contactRole'
           },
		   {
            title: '生日',
            align:"center",
            dataIndex: 'birthday'
           },
		   {
            title: '部门职务',
            align:"center",
            dataIndex: 'position'
           },
		   {
            title: '手机号码',
            align:"center",
            dataIndex: 'mobileNo'
           },
		   {
            title: '固定电话',
            align:"center",
            dataIndex: 'telephone'
           },
		   {
            title: '传真号码',
            align:"center",
            dataIndex: 'faxNumber'
           },
		   {
            title: '电子邮箱',
            align:"center",
            dataIndex: 'emailAddress'
           },
		   {
            title: '微信账号',
            align:"center",
            dataIndex: 'wechatAccount'
           },
		   {
            title: 'QQ号码',
            align:"center",
            dataIndex: 'qqNumber'
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
            title: '详细地址',
            align:"center",
            dataIndex: 'detailAddress'
           },
		   {
            title: '是否首要联系人',
            align:"center",
            dataIndex: 'isPrimary'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/cus/cusCustomerContact/list",
          delete: "/cus/cusCustomerContact/delete",
          deleteBatch: "/cus/cusCustomerContact/deleteBatch",
          exportXlsUrl: "cus/cusCustomerContact/exportXls",
          importExcelUrl: "cus/cusCustomerContact/importExcel",
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