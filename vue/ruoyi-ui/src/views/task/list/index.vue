
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入任务名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务类型"  prop="clazz">
        <el-select v-model="formData.clazz" placeholder="请选择任务类型" clearable label-width="68px">
          <el-option v-for="(item, index) in clazz" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:allocation:add']"
        >新增</el-button>
      </el-col>
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
      <el-table-column label="任务名" align="center" prop="name" />
      <el-table-column label="任务描述" align="center" prop="desc" />
      <el-table-column label="任务类型" align="center" prop="clazz" />
      <el-table-column label="正确率" align="center" prop="accuracy" />
      <el-table-column label="字符准确率" align="center" prop="wordAccuracy" />
      <el-table-column label="召回率" align="center" prop="recallRate" />
      <el-table-column label="创建时间" align="center" prop="createTime" />
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 任务分配对话框 -->
    <el-dialog :title="task_title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务名" prop="name">
          <el-input v-model="form.name" placeholder="请输入任务名" />
        </el-form-item>
        <el-form-item label="任务描述" prop="desc">
          <el-input v-model="form.desc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="任务类型" prop="clazz">
          <el-input v-model="form.clazz" placeholder="请输入任务类型" />
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
import { listAllocation, getAllocation, delAllocation, addAllocation, updateAllocation } from "@/api/task/allocation";

export default {
  name: "Allocation",
  data() {
    return {
      formData: {
        clazz: undefined,
      },
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
        name: null,
        desc: null,
        clazz: null,
        accuracy: null,
        recallRate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "任务名不能为空", trigger: "blur" }
        ],
        clazz: [{
          required: true,
          message: '请选择下拉选择',
          trigger: 'change'
        }],
      },
      clazz: [{
        "label": "ASR标注",
        "value": 0
      }, {
        "label": "QA标注",
        "value": 1
      }],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listAllocation(this.queryParams).then(response => {
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
        name: null,
        desc: null,
        clazz: null,
        accuracy: null,
        recallRate: null,
        bak1: null,
        bak2: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAllocation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAllocation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAllocation(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项？').then(function() {
        return delAllocation(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/allocation/export', {
        ...this.queryParams
      }, `allocation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
