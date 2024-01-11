<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名" prop="taskName">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务类型" prop="taskClazz">
        <el-input
          v-model="queryParams.wordAccuracy"
          placeholder="请输入字符准确率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="创建时间"  prop="beginTime,endTime">
        <el-date-picker clearable="clearTime"
          v-model="queryParams.queryStartTime"
          type="datetime"
          value-format="yyyy-MM-dd"
          placeholder="开始日期"
          >
        </el-date-picker>
        -
        <el-date-picker clearable="clearTime"
          v-model="queryParams.queryEndTime"
          type="datetime"
          value-format="yyyy-MM-dd"
          placeholder="结束日期"
          >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:allocation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="allocationList" @selection-change="handleSelectionChange">
      <el-table-column label="id" align="center" width="55" prop="id" />
      <el-table-column label="任务名" align="center" prop="taskName" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="任务类型" align="center" prop="taskClazz" />
      <el-table-column label="个数准确率" align="center" prop="numberAccuracy" />
      <el-table-column label="字符准确率" align="center" prop="wordAccuracy" />
      <el-table-column label="召回率" align="center" prop="recallRate" />
      <el-table-column label="任务创建时间" align="center" prop="createTime" />
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser } from "@/api/task/user";

export default {
  name: "TaskUserTaskAllocation",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 【请填写功能名称】表格数据
      allocationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: null,
        userId: null,
        wordAccuracy: null,
        numberAccuracy: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskId: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "$comment不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        console.log(response);
        this.allocationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        taskId: null,
        userId: null,
        wordAccuracy: null,
        numberAccuracy: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('task/user/export', {
        ...this.queryParams
      }, `allocation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
