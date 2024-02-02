<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="音频名字" prop="audioName">
        <el-input
          v-model="queryParams.audioName"
          placeholder="模糊搜索"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="搜索文本" prop="audioName">
        <el-input
          v-model="queryParams.afterText"
          placeholder="文本搜索搜索"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="问题分类" prop="clazzList" >
        <el-cascader
          v-model="queryParams.clazzId"
          placeholder="选择分类"
          :options="formatClazzList(clazzList)"
          @change="handleChange2"
          :style="{width: '300px'}"
        ></el-cascader>
      </el-form-item>


      <!-- <el-form-item label="是否使用" prop="isUse">
          <el-select v-model="queryParams.isUse"  placeholder="是否使用">
          <el-option
            v-for="use in isUse"
            :key="use.id"
            :label="use.name"
            :value="use.id"
          />
        </el-select>     
      </el-form-item> -->

      <el-form-item label="是否标注" prop="isMark">
        <!-- <el-dropdown @command="handleCommandMark">
          <span class="el-dropdown-link">
            是否已标注<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">是</el-dropdown-item>
            <el-dropdown-item command="b">否</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown> -->
        <el-select  v-model="queryParams.isMark" placeholder="是否已标注">
          <el-option
            v-for="mark in isMark"
            :key="mark.id"
            :label="mark.name"
            :value="mark.id"
          />
        </el-select>
        
      </el-form-item>

      <el-form-item label="任务查询" prop="taskList">
        <el-select v-model="queryParams.taskId" placeholder="请选择任务">
          <el-option
            v-for="task in taskList"
            :key="task.taskId"
            :label="task.taskName"
            :value="task.taskId"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="审核结果" prop="isPass">
        <el-select v-model="queryParams.isPass" placeholder="请选择审核结果">
          <el-option
            v-for="pass in isPass"
            :key="pass.id"
            :label="pass.name"
            :value="pass.id"
          />
        </el-select>
      </el-form-item>


      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
    <el-col :span="1.5">
      <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="showExportDialog"
        v-hasPermi="['verity:asr:export']"
      >
        导出
      </el-button>
    </el-col>
    <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
      <el-dialog title="导出设置" :visible.sync="exportDialogVisible">
        <el-form :model="exportForm" label-width="80px">
          <el-form-item label="文件路径">
            <el-input v-model="exportForm.exportFilePath" placeholder="请输入文件路径"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="exportDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleExport">确定</el-button>
        </div>
      </el-dialog>

    <el-table
      v-loading="loading"
      :data="verityAsrList"
      @selection-change="handleSelectionChange"
      ref="filterTable"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->

      <!-- <el-table-column
        label="音频所在路径"
        align="center"
        prop="audioPath"
        :show-overflow-tooltip="true"
      /> -->
      <el-table-column
        label="音频名字"
        align="center"
        prop="audioName"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="标注人"
        align="center"
        prop="taskOwner"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="标注后文本"
        align="center"
        prop="afterText"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="审核后文本"
        align="center"
        prop="verityText"
        :show-overflow-tooltip="true"
      />
      
      <el-table-column prop="isMark" label="是否标注" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.isMark === '是' ? 'primary' : 'info'"
            disable-transitions
            >{{ scope.row.isMark }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="isPass" label="是否通过" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.isPass === '是' ? 'primary' : 'info'"
            disable-transitions
            >{{ scope.row.isPass }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column
        label="字符错误率"
        align="center"
        prop="accuracy"
        :show-overflow-tooltip="true"
      />
      

      <el-table-column
        label="标注时间"
        align="center"
        prop="labelTime"
        width="150"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.labelTime, "{y}-{m}-{d}   {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="审核时间"
        align="center"
        prop="verityTime"
        width="150"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.verityTime, "{y}-{m}-{d}   {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>
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
            @click="handleUpdate(scope.row)"
            >审核</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 审核界面-->
    <el-dialog
      :title="form.audioName"
      :visible.sync="open"
      width="1000px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
      @close="stopAudioPlayback"
    >
      <el-form ref="form" :model="verityForm" :rules="rules" label-width="100px">
        <markAsr
          v-if="isPopupVisible"
          @close="closePopup"
          :audioName="form.audioName"
          :closeTag="this.closeTag"
          :stopAudio="this.stopAudio"
          ref="getChild"
        >
        </markAsr>
        <el-form-item label="提示：">
          <el-input
            placeholder="欢迎使用审核工具，请使用中文标点符号进行标注。"
            :disabled="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="是否通过：" prop="verityText">
          <el-button type="primary" 
        @click="pass"
        >通 过</el-button>
        <el-button @click="noPass">不通过</el-button>
        </el-form-item>
        <el-form-item label="标注：" prop="verityText">
          <el-input
            type="textarea"
            :rows="4"
            v-model="computedValue"
            placeholder="审核内容"
            :readonly = canChange
          />
          <el-button type="primary" @click="sentenceTrim(computedValue)">标注整理</el-button>
        </el-form-item>
        <el-form-item  label="问题分类：" prop="sevalue">
          <el-cascader 
            v-model="sevalue"
            placeholder="选择问题分类"
            :options="formatClazzList(clazzList)"
            @change="handleChange"
            :disabled = canChange
        ></el-cascader>
        </el-form-item>

        <el-form-item label="审核意见：" prop="verity" :hidden=canChange>
          <el-input
            v-model="verity"
            type="textarea"
            :rows="1"
            placeholder="审核意见"
          />
        </el-form-item>
        
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" 
        @click="submitForm"
        >确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import {
  listVerityAsr,
  getVerityAsr,
  delVerityAsr,
  addVerityAsr,
  updateVerityAsr,
} from "@/api/verity/asr";
import {
  optionsExtract
} from "@/api/qa/extract";

import {addTag, updateTag } from "@/api/asr/tag";
import markAsr from "@/views/markAsr.vue";
import {allByUser} from "@/api/task/user"
import { setCanvasCreator } from "echarts";

export default {
  name: "VerityAsr",

  components: { markAsr },
  data() {
    return {
      canChange: true,//是否能更改
      isMark: [
        {
          id:"否",
          name: "否"
      },
      {
          id:"是",
          name: "是"
      }
      ],
      isUse: [
        {
          id:"否",
          name: "否"
      },
      {
          id:"是",
          name: "是"
      }
      ],
      clazzList: [],
      isPass: [
        {
          id : 0,
          name: "不通过"
        },
        {
          id : 1,
          name: "通过"
        }
      ],
      taskList: [],
      // ... your existing data properties ...
      exportDialogVisible: false,
      exportForm: {
        exportFilePath: "", // user-input file path
      },
      checkAll: false,
      checkedCities: [],
      sevalue: [], //标签选择值
      cities: [],
      isIndeterminate: true,
      // 遮罩层
      isPopupVisible: false,
      dynamicTags: [],
      inputVisible: false,
      inputValue: "",
      audio_name_1: "",
      stopAudio: false,
      markText: "",
      loading: true,
      closeTag: null,
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
      // 标注表格数据
      verityAsrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      opentagadd:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        audioName: null,
        afterText : null,
        isUse: null,
        isMark: null,
        clazzId: null,
        selectedTask: null,
        isPass: null,
        taskId :null,
      },
      verity: null,
      // 表单参数
      verityForm:{},
      form: {},
      formadd:{},
      rules:{},

      // 表单校验
      rulesadd: {
        tagName: [
           { required: true, message: "标签名字不能为空", trigger: "blur" }
         ],
      }
    };
  },
  computed: {
    computedValue: {
      get() {
        if (this.form.afterText === null || this.form.afterText === "") {
          return this.form.afterText;
        } else {
          return this.form.afterText;
        }
        // return this.form.afterText === null
        //   ? this.form.preText
        //   : this.form.afterText;
      },
      set(newValue) {
        if (this.form.afterText === "") {
          this.form.afterText = newValue;
        } else {
          this.form.afterText = newValue;
        }
      },
    },
  },
  created() {
    this.getList();
    this.getOptions();
    this.getClazz();
  },

  methods: {

    pass(){
      this.submitForm();
    },

    noPass() {
      this.canChange = false;
    },
    noPass() {
      this.canChange = false;
    },
  formatClazzList(clazzList) { 
  // 将 clazzList 格式化为适用于 el-cascader 的 options 数据 
  return clazzList.map(clazz => ({ 
      value: clazz.id, 
      label: clazz.label, // 可根据实际情况调整 label 属性 
      children: formatChildren(clazz.children)
  }));
  
  function formatChildren(children) { 
    if (!children || children.length === 0) {
        return undefined;  // 如果子类为空，返回 undefined 或者可以根据实际需求返回其他合适的值
      }
      return children.map(child => ({
          value: child.id, 
          label: child.label, 
          children: formatChildren(child.children)
      })); 
    }
  },
    handleChange2(value) {
     this.queryParams.clazzId=value[value.length-1]
     },

   handleChange(value) {
      this.verityForm.verityClazzId = value[value.length-1]
     },

    getClazz(){
      var userName = this.$store.state.user.name;
      var asr = 0
      allByUser(userName,asr).then((response) => {
        this.taskList = response.rows.map((row) => { return { taskId: row.taskId, taskName: row.taskName }; }); 
      });
      
    },

    handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.cities.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
      },
    /** 查询标注列表 */
    getList() {
      this.loading = true;
      this.queryParams.taskOwner = this.$store.state.user.name;
      console.log(this.queryParams)
      listVerityAsr(this.queryParams).then((response) => {
        this.verityAsrList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      console.log(this.verityAsrList);
    },
    getOptions() {
      optionsExtract().then((response) => {
       // this.extractList = response.rows;
       console.log(response.data);
       this.clazzList =response.data.options;
        //this.total = response.total;
        //this.loading = false;
      });
    },
    addTags(){
      this.opentagadd=true;
    },
    canceltagadd(){
      this.opentagadd = false;

    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = "";
    },

    loadNewAudio() {
      //console.log("监听到tag关闭了",this.form.audioName)
      //console.log(this.stopAudio)
      if (this.stopAudio === true) {
        this.$nextTick(() => {
          this.$refs.getChild.reloadAudio();
        });
      } else {
        // console.log("子组件-----》",this.audio_name_1)
        this.$nextTick(() => {
          this.$refs.getChild.reloadAudio2(this.audio_name_1);
        });
      }
    },
    stopAudioPlayback() {
      this.stopAudio = true;
    },

    showPopup() {
      this.isPopupVisible = true;
    },
    closePopup() {
      this.isPopupVisible = false;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.stopAudio = true;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        audioPath: null,
        audioName: null,
        afterText: null,
        isUse: null,
        isMark: null,
        createTime: null,
        audioName: null,
        // dynamicTags: null,
        updateTime: null,
        sevalue: null,
        // verity: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // handleCommandUse(command) {
    //   if (command === "b") {
    //     this.queryParams.isUse = "否";
    //   } else {
    //     this.queryParams.isUse = "是";
    //   }
    //   this.queryParams.pageNum = 1;
    //   this.getList();
    // },

    // handleCommandMark(command) {
    //   if (command === "b") {
    //     this.queryParams.isMask = "否";
    //   } else {
    //     this.queryParams.isMask = "是";
    //   }
    //   this.queryParams.pageNum = 1;
    //   this.getList();
    // },
    handleClickMark() {},
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        audioName: null,
        afterText : null,
        isUse: null,
        isMark: null,
        clazzId: null,
        selectedTask: null,
        isPass: null,
        taskId: null,
      };
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加标注";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.canChange = true;
      this.audio_name_1 ="../audio/"+ row.audioName;
      //发送一个请求，根据id查对应的标签名字
      this.sevalue = []
      // this.dynamicTags = [];
      this.reset();
      this.isPopupVisible = true;
      this.stopAudio = false;
      const id = row.id || this.ids;
      getVerityAsr(id).then((response) => {
        this.form = response.data;
        this.form.audioName ="../audio/" +this.form.audioName;
        console.log(response.data);
        // this.checkedCities=response.data.selectTags;
        this.verityForm.afterText = this.form.afterText;
        this.sevalue = response.data.clazzId;
        this.open = true;
        this.title = "添加标签";
        
      });
    },
    /** 提交按钮 */
    submitForm() {
      // this.verityForm.sevalue = this.sevalue;
      
      this.verityForm.id = this.form.id;
      this.verityForm.labelUserName = this.$store.state.user.name;
      this.verityForm.verityText = this.computedValue;
      this.verityForm.verity = this.verity;
      console.log("------------------")
      console.log(this.verityForm)
        if (this.verityForm != null) {
          updateVerityAsr(this.verityForm).then((response) => {
            this.$modal.msgSuccess("标注成功");
            this.open = false;
            this.getList();
          });
        }
      // });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认舍弃编号为"' + ids + '"的音频数据？')
        .then(function () {
          return delVerityAsr(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("设置成功");
        })
        .catch(() => {});
    },
    showExportDialog() {
      this.exportDialogVisible = true;
    },
    sentenceTrim(value){
        console.log(value)
        this.computedValue = value.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll(".","。").replaceAll("/n","");
        
    },

    handleExport() {
      // Validate exportForm.exportFilePath if needed
      if (this.exportForm.exportFilePath.trim() === "") {
        this.$message.error("请输入文件路径");
        return;
      }

      

      // Perform the export with the user-input file path
      this.download(
        "verity/asr/export",
        {
          ...this.queryParams,
          exportFilePath: this.exportForm.exportFilePath,
        },
        `verity_asr_${new Date().getTime()}.json`
      );

      // Close the export dialog
      this.exportDialogVisible = false;
    },
    
  },
  watch: {
    //当关闭时，强制重新加载子组件
    stopAudio() {
      // console.log("this.stopAudio,watch",this.stopAudio)
      this.loadNewAudio();
    },
  },
};
</script>
<style>
.el-tooltip__popper {
  max-width: 30%;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
