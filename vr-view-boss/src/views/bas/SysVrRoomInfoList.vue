<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="科室id">
              <a-input placeholder="请输入科室id" v-model="queryParam.roomName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="所属机构/医院">
              <a-select v-model="queryParam.hospitalId" style="width: 120px">
                <a-select-option v-for="item in hospitalList" :value="item.hospitalId">{{item.hospitalName}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>

            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('VR室信息')">导出</a-button>
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
    <sysVrRoomInfo-modal ref="modalForm" @ok="modalFormOk"></sysVrRoomInfo-modal>
  </a-card>
</template>

<script>
  import SysVrRoomInfoModal from './modules/SysVrRoomInfoModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import { httpAction ,getAction} from '@/api/manage'

  export default {
    name: "SysVrRoomInfoList",
    mixins:[JeecgListMixin],
    components: {
      SysVrRoomInfoModal
    },
    data () {
      return {
        description: 'VR室信息管理页面',
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
            title: '科室id',
            align:"center",
            dataIndex: 'departmentId'
           },
		   {
            title: '科室名称',
            align:"center",
            dataIndex: 'departmentName'
           },
		   {
            title: 'VR室名称',
            align:"center",
            dataIndex: 'roomName'
           },
		   {
            title: '设备id',
            align:"center",
            dataIndex: 'deviceId'
           },
		   {
            title: '设备地址',
            align:"center",
            dataIndex: 'deviceAddr'
           },
		   {
            title: '是否登录验证 1、是 0、否',
            align:"center",
            dataIndex: 'loginCheck'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/system/sysVrRoomInfo/list",
          delete: "/system/sysVrRoomInfo/delete",
          deleteBatch: "/system/sysVrRoomInfo/deleteBatch",
          exportXlsUrl: "system/sysVrRoomInfo/exportXls",
          importExcelUrl: "system/sysVrRoomInfo/importExcel",
          hospital:"/system/sysHospitalInfo/list"
       },
        hospitalList:[]
    }
  },
    created() {
      this.$nextTick(() =>{
        this.loadHospitalList()
      });
    },
    computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
      loadHospitalList(){
        var that = this;
        getAction(this.url.hospital,{}).then((res)=>{
          if(res.success){
            that.hospitalList = res.result;

          }
        })
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>