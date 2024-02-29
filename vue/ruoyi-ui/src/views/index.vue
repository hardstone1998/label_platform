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

      <el-form-item label="搜索文本" prop="preText">
        <el-input
          v-model="queryParams.preText"
          placeholder="文本搜索"
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


      <el-form-item label="是否使用" prop="isUse">
        <!-- <el-dropdown @command="handleCommandUse">
          <span class="el-dropdown-link">
            是否使用<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">是</el-dropdown-item>
            <el-dropdown-item command="b">否</el-dropdown-item>
          </el-dropdown-menu> -->

          <el-select v-model="queryParams.isUse"  placeholder="是否使用">
          <el-option
            
            v-for="use in isUse"
            :key="use.id"
            :label="use.name"
            :value="use.id"
          />
        </el-select>
        <!-- </el-dropdown> -->
        
      </el-form-item>

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

      <el-form-item label="插入批次" prop="insertBatch" >
        <el-select v-model="queryParams.insertBatchId" placeholder="请选择插入批次">
          <el-option
            v-for="batch in insertBatch"
            :key="batch.id"
            :label="batch.name"
            :value="batch.id"
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
        v-hasPermi="['asr:tag:export']"
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
      :data="annotationList"
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
        label="标注前文本"
        align="center"
        prop="preText"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="标注后文本"
        align="center"
        prop="afterText"
        :show-overflow-tooltip="true"
      />
      <!-- <el-table-column prop="isUse" label="是否使用" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.isUse === '是' ? 'success' : 'danger'"
            disable-transitions
            >{{ scope.row.isUse }}</el-tag
          >
        </template> -->
      <!-- </el-table-column> -->

      <!-- <el-table-column
        label="类别"
        align="center"
        @change="selectClazzLabel(clazzId)"
        :show-overflow-tooltip="true"
      /> -->
      <el-table-column label="类别" align="center" prop="clazzId">
        <template label="类别" align="center" slot-scope="scope">
          {{ selectClazzLabel(clazzList,scope.row.clazzId) }}
        </template>
      </el-table-column>

      <el-table-column prop="isMark" label="是否已标注" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.isMark === '是' ? 'primary' : 'info'"
            disable-transitions
            >{{ scope.row.isMark }}</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column
        label="标注时间"
        align="center"
        prop="labelTime"
        width="150"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.updateTime, "{y}-{m}-{d}   {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="showPopup"
            v-hasPermi="['asr:annotation:edit']"
            >标注</el-button
          > -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            >标注</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >舍弃</el-button
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

    <!-- 添加或修改标注对话框 -->
    <el-dialog
      :title="form.audioName"
      :visible.sync="open"
      width="1000px"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      append-to-body
      @close="stopAudioPlayback"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
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
            placeholder="欢迎使用标注工具，请使用中文标点符号进行标注，如果该音频没有有效内容或者音频内容不清晰，请返回上一级舍弃该音频。"
            :disabled="true"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="标注：" prop="preText">
          <el-input
            type="textarea"
            :rows="6"
            v-model="computedValue"
            placeholder="标注内容"
          />
          <el-button type="primary" @click="sentenceTrim(computedValue)">标注整理</el-button>
        </el-form-item>
        <!-- <el-form-item label="标签：" prop="dynamicTags">
            <el-tooltip placement="top">
              <div slot="content">Tips: 添加标签（可选），输入内容后回车添加，最多输入5个。</div>
              <i class="el-icon-question" />
            </el-tooltip>
         
          <el-tag
            :key="tag"
            v-for="tag in dynamicTags"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)"
          >
            {{ tag }}
          </el-tag>
          <el-input
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          >
          </el-input>
          <el-button
            v-else
            class="button-new-tag"
            size="small"
            @click="showInput"
            >+ New Tag</el-button
          >
        </el-form-item> -->
        <!-- <el-form-item label="标签：" prop="dynamicTags">
          <div style="margin: 15px 0"></div>
          <el-checkbox-group
            v-model="checkedCities"
            @change="handleCheckedCitiesChange"
          >
            <el-checkbox v-for="city in cities" :label="city" :key="city">{{
              city
            }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item> -->
        <el-form-item  label="问题分类：" prop="verity">
          <el-cascader 
            v-model="sevalue"
            placeholder="选择问题分类"
            :options="formatClazzList(clazzList)"
            @change="handleChange"
        ></el-cascader>
        </el-form-item>
        
      </el-form>

      <div slot="footer" class="dialog-footer">
        <!-- <el-button size="mini" type="text" icon="el-icon-plus"  @click="addTags()">添加标签</el-button> -->
        <!-- <el-button type="primary" @click="addTags">添加标签</el-button> -->
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- <el-dialog :title="title" :visible.sync="opentagadd" width="500px" append-to-body>
      <el-form ref="formadd" :model="formadd" :rules="rulesadd" label-width="80px">
       
        <el-form-item label="标签名字" prop="tagName">
          <el-input v-model="formadd.tagName" placeholder="请输入标签名字" />
        </el-form-item>

        <el-form-item label="标签描述" prop="createUser">
          <el-input v-model="formadd.createUser" placeholder="请输入标签描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormtagadd()">确 定</el-button>
        <el-button @click="canceltagadd">取 消</el-button>
      </div>
    </el-dialog> -->
    <!-- 引入组件 -->
    <!-- <markAsr v-if="isPopupVisible" @close="closePopup"> </markAsr> -->
  </div>
</template>

<script>

import {
  listAnnotation,
  getAnnotation,
  delAnnotation,
  addAnnotation,
  updateAnnotation,
} from "@/api/asr/annotation";
import {
  optionsExtract
} from "@/api/qa/extract";

import {listBatch } from "@/api/total/batch";
import {addTag, updateTag } from "@/api/asr/tag";
import markAsr from "./markAsr.vue";
// import {allTask} from "@/api/task/allocation"
import {allByUser} from "@/api/task/user"
import { setCanvasCreator } from "echarts";

export default {
  name: "Annotation",

  components: { markAsr },
  data() {
    return {
      insertBatch: [],
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
      audioName:null,
      // 标注表格数据
      annotationList: [],
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
        preText : null,
        isUse: null,
        isMark: null,
        taskOwner: null,
        clazzId: null,
        selectedTask: null,
        isPass: null,
        taskId :null,
        insertBatchId :null
      },
      // 表单参数
      form: {},
      // formadd:{},
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
          return this.form.preText;
        } else {
          return this.form.afterText;
        }
        // return this.form.afterText === null
        //   ? this.form.preText
        //   : this.form.afterText;
      },
      set(newValue) {
        if (this.form.afterText === "") {
          this.form.preText = newValue;
        } else {
          this.form.afterText = newValue;
        }
      },
    },
  },
  created() {
    this.getList();
    this.getClazz();
    this.getTaskList();
    this.searchAllBatches();
  },

  methods: {
    //查询全部批次
    searchAllBatches(){
      listBatch().then((response) => {
        this.insertBatch = response.rows;
      });
    },

    // 查询类别名称
    // 递归函数，根据 id 查找 label
  selectClazzLabel(array,targetId) {
    if(targetId ==undefined ||targetId == null){
      return null;
    }
    for (const item of array) {
      if (item.id === targetId) {
        return item.label;
      }

      if (item.children && item.children.length > 0) {
        const label = this.selectClazzLabel(item.children,targetId);
        if (label) {
          return label;
        }
      }
    }
    // 如果没有找到匹配的 id，可以返回 null 或其他适当的值
    return null;
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
    
    this.form.clazzId = value[value.length-1]
    //  this.sevalue=value;

    //  this.selectvalue=this.sevalue[this.sevalue.length-1]
     //console.log(this.sevalue);
     },

    getTaskList(){
      var userName = this.$store.state.user.name;
      console.log(userName);
      allByUser(userName).then((response) => {
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
      listAnnotation(this.queryParams).then((response) => {
        this.annotationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getClazz() {
      optionsExtract().then((response) => {
       // this.extractList = response.rows;
      //  console.log(response.data);
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
        preText: null,
        afterText: null,
        isUse: null,
        taskOwner: null,
        isMark: null,
        createTime: null,
        // dynamicTags: null,
        updateTime: null,
        clazzId:null,
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
        preText : null,
        isUse: null,
        isMark: null,
        taskOwner: null,
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
      this.audio_name_1 ="audio/" +row.folder+"/"+  row.audioName;
      //发送一个请求，根据id查对应的标签名字
      // this.sevalue = []
      // this.dynamicTags = [];
      this.reset();
      this.isPopupVisible = true;
      this.stopAudio = false;
      // this.form.clazzId = this.queryParams.clazzId;
      const id = row.id || this.ids;
      // console.log("---------------------")
      // console.log( this.audio_name_1)
      getAnnotation(id).then((response) => {
        this.form = response.data;
        // this.audio_name_1 ="audio/" + this.form.folder+"/" + this.form.audioName;
        this.form.audioName  = "audio/" +this.form.folder+"/"+ this.form.audioName;
        
        
        // this.cities = response.data.dynamicTags;
        // this.checkedCities=response.data.selectTags;
        this.audioName = this.form.audioName;
        // console.log(response);
        this.open = true;
        this.title = "添加标签";
        console.log( this.audioName);
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.labelUserName = this.$store.state.user.name;
      this.form.audioName = null;
      this.form.audioPath = null;
      this.form.dynamicTags = null;
      this.form.selectTags = null;
      // this.form.sevalue = this.sevalue;
      // this.form.dynamicTags = this.dynamicTags;
      // this.form.selectTags=this.checkedCities;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log(this.form);
          updateAnnotation(this.form).then((response) => {
            this.$modal.msgSuccess("标注成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认舍弃编号为"' + ids + '"的音频数据？')
        .then(function () {
          return delAnnotation(ids);
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
        this.computedValue = value.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll(".","。").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，");
    },

    handleExport() {
      // Validate exportForm.exportFilePath if needed
      if (this.exportForm.exportFilePath.trim() === "") {
        this.$message.error("请输入文件路径");
        return;
      }

      

      // Perform the export with the user-input file path
      this.download(
        "asr/annotation/export",
        {
          ...this.queryParams,
          exportFilePath: this.exportForm.exportFilePath,
        },
        `annotation_${new Date().getTime()}.json`
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
