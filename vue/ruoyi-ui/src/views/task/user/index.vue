<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入任务名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务类型" prop="taskClazz">
        <el-select v-model="queryParams.taskClazz" placeholder="任务类型">
          <el-option
            v-for="task in taskClazz"
            :key="task.id"
            :label="task.name"
            :value="task.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="创建时间"  prop="beginTime,endTime">
        <el-date-picker clearable=clearTime
          v-model="queryParams.queryStartTime"
          type="datetime"
          value-format="yyyy-MM-dd"
          placeholder="开始日期"
          >
        </el-date-picker>
        -
        <el-date-picker clearable=clearTime
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
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" width="55" prop="id" />
      <el-table-column label="任务名" align="center" prop="taskName" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="审核人" align="center" prop="verityUser" />
      <el-table-column label="任务类型" align="center" prop="taskClazz" />
      <el-table-column label="标注数量" align="center" prop="labelNum" />
      <el-table-column label="标注完成数量" align="center" prop="labeledNum" />
      <el-table-column label="召回数量" align="center" prop="recallNum" />
      <el-table-column label="已审核数量" align="center" prop="verityNum" />
      <el-table-column label="个数准确率" align="center" prop="numberAccuracy" />
      <el-table-column label="字符准确率" align="center" prop="wordAccuracy" />
      <el-table-column label="任务创建时间" align="center" prop="createTime" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="allocationVerity(scope.row)"
            >分配审核</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 分配审核任务对话框 -->
    <el-dialog
      :title="form.taskName"
      :visible.sync="open"
      width="1000px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
    >
      <el-form ref="form" :model="form"  label-width="100px">
        <el-form-item  label="标注人" prop="form.labelUser">
          <el-input
            :rows="6"
            v-model="form.labelUser"
            placeholder="标注人"
            readonly
          />
        </el-form-item>
        <el-form-item  label="审核人" prop="form.verityUserId">
          <el-select v-model="form.verityUserId" placeholder="审核人">
          <el-option
            v-for="user in userList"
            :key="user.id"
            :label="user.nickname"
            :value="user.id"
          />
        </el-select>
        </el-form-item>
        <el-form-item  label="抽取审核数量" prop="form.verityNum">
          <el-input
            :rows="6"
            v-model="form.verityNum"
            placeholder="请设置审核数量"
          />
        </el-form-item>
        <el-form-item  label="抽取审核百分比" prop="form.verityPercentage">
          <el-input
            :rows="6"
            v-model="form.verityPercentage"
            placeholder="请设置抽取审核百分比"
            
          />
        </el-form-item>
        
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser,verityAnnotation } from "@/api/task/user";
import { allTask } from "@/api/task/allocation";
import {
  totalUser
} from "@/api/system/user"

export default {
  name: "TaskUserTaskAllocation",
  data() {
    return {
      taskClazz: [
        {
          id: 0,
          name: "ASR"
      },
      {
          id: 1,
          name: "QA"
      }
      ],
      userList: [],
      clearable:false,
      clearTime: false,
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
        taskName: null,
        userName: null,
        clazz: null,
        responsiblePersonId: null,
        beginTime:null,
        endTime:null,
        responsiblePersonName: null,
        clearable:false,
        clearTime: false,
      },
      // 表单参数
      form: {},
      // 表单校验
      // rules: {
      //   taskName: [
      //     { required: true, message: "$comment不能为空", trigger: "blur" }
      //   ],
      //   userId: [
      //     { required: true, message: "$comment不能为空", trigger: "blur" }
      //   ],
      // }
    };
  },
  created() {
    this.getTask();
    this.getList();
    this.getUsers();
  },
  methods: {
    getTask(){
      allTask(this.$store.state.user.name).then(response => {
        console.log(response);
      })
    },
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        taskName: null,
        userName: null,
        taskClazz: null,
        verityUserId:null,
        responsiblePersonId: null,
        beginTime:null,
        endTime:null,
        responsiblePersonName: null,
      };
      this.resetForm("queryForm");
      this.handleQuery();
    },
    getUsers() {
      totalUser().then((response) => {
       this.userList = response.rows.map(row => ({ id: row.userId, nickname: row.nickName }));
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        taskId:null,
        taskName:null,
        labelUser: null,
        verityUserId: null,
        verityNum: null,
        verityPercentage: null,
        reqUser:null,
        taskClazz:null
      };
    },
    /** 分配审核操作 */
    allocationVerity(row) {
      this.reset();
      this.isPopupVisible = true;
      const id = row.id || this.ids;
      this.form.id = id;
      this.form.labelUserId = row.userId;
      this.form.taskId = row.taskId;
      this.form.taskName = row.taskName;
      this.form.labelUser = row.userName;
      this.form.taskClazz = row.taskClazz;
      this.open = true;
      this.title = "分配审核";
    },
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.queryParams.responsiblePersonName = this.$store.state.user.name;
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();
      const id = row.id || this.ids
      getUser(id).then(response => {
        console.log(response.data);
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
    },
     // 取消按钮
     cancel() {
      this.open = false;
      this.reset();
    },
    /** 提交按钮 */
    submitForm() {
      this.form.reqUser = this.$store.state.user.name;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log(this.form);
          verityAnnotation(this.form).then((response) => {
            this.$modal.msgSuccess("分配成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
  }
};
</script>
