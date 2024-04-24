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
      <el-form-item label="音频名字" prop="audioPath">
        <el-input
          v-model="queryParams.audioPath"
          placeholder="模糊搜索"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="搜索文本" prop="qaSum">
        <el-input
          v-model="queryParams.qaSum"
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
      <el-form-item label="是否标注" prop="isMark">
        <el-select  v-model="queryParams.isMark" placeholder="是否已标注">
          <el-option
            v-for="mark in isMark"
            :key="mark.id"
            :label="mark.name"
            :value="mark.id"
          />
        </el-select>
        
      </el-form-item>

      <el-form-item label="是否删除" prop="isDelete">
        <el-select  v-model="queryParams.isDelete" placeholder="是否已删除">
          <el-option
            v-for="isDelete in isDeletes"
            :key="isDelete.id"
            :label="isDelete.name"
            :value="isDelete.id"
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
      :data="verityQaList"
      @selection-change="handleSelectionChange"
      ref="filterTable"
    >
      <el-table-column
        label="音频路径"
        align="center"
        prop="audioPath"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="标注人"
        align="center"
        prop="taskOwner"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="标注后Q&A"
        align="center"
        prop="qaSum"
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
      title="Q&A标注"
      :visible.sync="open"
      width="100%"
      higth="100%"
      append-to-body
    >
      <!-- 新增播放音频按钮 -->

      <div class="container">
        <div class="audio-container">
          <div class="left">
            <span class="icon" v-if="isPlay == false" @click="play">
              <i class="el-icon-video-play" aria-hidden="true"></i>
              <!-- <i class="fa fa-play play-icon" aria-hidden="true"></i> -->
            </span>
            <span class="icon" v-if="isPlay == true" @click="pause">
              <i class="el-icon-video-pause"></i>
            </span>
          </div>
          <div class="right">
            <div class="words flex-between">
              <div class="name">
                {{ audioName == null ? "未知" : audioName }}
              </div>
              <div class="time">{{ formatCurrentTime }} / {{ totalTime }}</div>
            </div>
            <div class="duration">
              <input
                type="range"
                ref="range"
                @input="onChange"
                @change="onChange"
                min="0"
                max="360"
                value="0"
              />
            </div>
          </div>
        </div>
        <audio
          style="display: none"
          :src="audioURL"
          ref="audio2"
          controls
          @timeupdate="update"
          @canplay="loadingFinish"
        ></audio>
      </div>
      <el-form ref="form" :model="form" :rules="rules" label-width="70px">
        <el-form-item label="识别结果" prop="result">
          <el-input
            v-model="form.result"
            type="textarea"
            :rows="10"
            :readonly="true"
          />
        </el-form-item>

        <el-button type="text" >QA1</el-button>
        <el-form-item prop="qaExtract">
          <el-input
            v-model="form.qa1"
            type="textarea"
            :rows="3"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-button type="text" >QA2</el-button>
        <el-form-item prop="qaExtract">
          <el-input v-model="form.qa2" type="textarea" :rows="3" />
        </el-form-item>
        <el-button type="text" >QA3</el-button>
        <el-form-item prop="qaExtract">
          <el-input v-model="form.qa3" type="textarea" :rows="3" />
        </el-form-item>
        <el-button type="primary" @click="sentenceTrim()">标注整理</el-button>
      </el-form>
      <div slot="footer" class="dialog-footer">

        <el-cascader 
            v-model="sevalue"
            placeholder="选择问题分类"
            :options="formatClazzList(clazzList)"
            @change="handleChange"
        ></el-cascader>

        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import {
  listVerityQa,
  delExtract,
  addExtract,
  updateExtract,
} from "@/api/verity/qa";
import {
  getExtract,
} from "@/api/qa/extract";
import {
  updateVerityQa
}  from "@/api/verity/qa"
import {
  optionsExtract
} from "@/api/qa/extract";

// import {addTag, updateTag } from "@/api/asr/tag";
import markAsr from "@/views/markAsr.vue";
import {allByUser} from "@/api/task/user"
import { setCanvasCreator } from "echarts";

export default {
  name: "VerityAsr",

  components: { markAsr },
  data() {
    return {
      selectvalue: "",
      sevalue: [], //标签选择值
      getvalue:"",
      gridData: [],
      audioName: "",
      formatCurrentTime: 0,
      formatTotalTime: 0,
      isPlay: false, // 控制icon切换
      totalTime: 0, // 播放总时间--秒
      audioURL: "",
      options: [],
      props:{
        value:"id"
      },
      verityQaList: [],
      
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
      isDeletes: [
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
        audioPath: null,
        qaSum : null,
        isMark: "是",
        isDelete: "否",
        clazzId: null,
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
    this.getTask();
  },

  methods: {
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
    // 控制音乐暂停
    pause() {
      const audio = this.$refs.audio2;
      audio.pause();
      this.isPlay = false;
    },
    sentenceTrim(){
      this.form.qa1 = this.form.qa1.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll(".","。").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，").replaceAll(":","：").replaceAll("客户：","客户问：").replaceAll("客服：","客服答：").replaceAll("客户询问：","客户问：").replaceAll("客服回答：","客服答：");
      this.form.qa2 = this.form.qa2.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll(".","。").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，").replaceAll(":","：").replaceAll("客户：","客户问：").replaceAll("客服：","客服答：").replaceAll("客户询问：","客户问：").replaceAll("客服回答：","客服答：");
      this.form.qa3 = this.form.qa3.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll(".","。").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，").replaceAll(":","：").replaceAll("客户：","客户问：").replaceAll("客服：","客服答：").replaceAll("客户询问：","客户问：").replaceAll("客服回答：","客服答：");
      if(this.form.qa1==null||this.form.qa1==""){
        this.form.qa1 = this.form.qa2;
        this.form.qa2 = this.form.qa3;
        this.form.qa3 = "";
      }
      if(this.form.qa2==null||this.form.qa2==""){
        this.form.qa2 = this.form.qa3;
        this.form.qa3 = "";
      }
    },
    // 控制音乐播放
    play() {
      const audio = this.$refs.audio2;
      audio.play();
      this.isPlay = true;
    },
    // range--拖动进度条得到的回调
    onChange() {
      let value = this.$refs.range.value;
      const persentage = ((value / 360) * 100).toFixed(1) + "%";
      this.$refs.range.style.backgroundSize = `${persentage} 100%`;
      // 控制音频播放
      const timeToGo = (value / 360) * this.totalTime;
      const audio = this.$refs.audio2;
      audio.currentTime = timeToGo;
    },
    // audio--进度变化的时候的回调--改变文字
    update() {
      const audio = this.$refs.audio2;
      const currentTime = audio.currentTime; // 当前播放时间
      this.currentTime = currentTime;
      // 改变进度条的值
      const range = this.$refs.range;
      range.value = ((this.currentTime / this.totalTime) * 360).toFixed(1);
      // 进度条的值改变的时候，颜色也跟着变化
      const persentage =
        ((this.currentTime / this.totalTime) * 100).toFixed(1) + "%";
      this.$refs.range.style.backgroundSize = `${persentage} 100%`;
    },
    // 音乐缓存完毕，获取时间
    loadingFinish() {
      const totalTime = this.$refs.audio2.duration;
      this.totalTime = totalTime;
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
      // this.getvalue=value[value.length-1]
      // this.queryParams.cuda=this.getvalue
     // console.log("查询的分类id：",this.getvalue);
      },

    handleChange(value) {
      this.form.clazzId = value[value.length-1]
      // this.sevalue=value;

      // this.selectvalue=this.sevalue[this.sevalue.length-1]
      //console.log(this.sevalue);
      },

    getTask(){
      var userName = this.$store.state.user.name;
      var qa = 1;
      allByUser(userName,qa).then((response) => {
        this.taskList = response.rows.map((row) => { return { taskId: row.taskId, taskName: row.taskName }; }); 
        console.log(this.taskList);
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
      console.log(this.queryParams);
      listVerityQa(this.queryParams).then((response) => {
        this.verityQaList = response.rows;
       // console.log("返回结果",response.rows)
        this.total = response.total;
        this.loading = false;
      });
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

    // loadNewAudio() {
    //   //console.log("监听到tag关闭了",this.form.audioName)
    //   //console.log(this.stopAudio)
    //   if (this.stopAudio === true) {
    //     this.$nextTick(() => {
    //       this.$refs.getChild.reloadAudio();
    //     });
    //   } else {
    //     // console.log("子组件-----》",this.audio_name_1)
    //     this.$nextTick(() => {
    //       this.$refs.getChild.reloadAudio2(this.audio_name_1);
    //     });
    //   }
    // },
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
        result: null,
        cuda: "",
        audioTime: null,
        execTime: null,
        createTime: null,
        qaExtract: null,
        qaMark: null,
        updateTime: null,
        taskOwner: null,
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
        isMark: null,
        isDeletes: null,
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
      this.reset();
      this.res_id = row.id;
      const id = row.id || this.ids;
      getExtract(id).then((response) => {
        
        this.form = response.data;
        this.sevalue=response.data.classPath;
        this.form.qaSum = null;
        const parts = this.form.audioPath.split("/");
        this.audioName = parts[parts.length - 1];
        this.formatCurrentTime = this.formatTime(this.currentTime);
        this.formatTotalTime = this.formatTime(this.totalTime);
        this.audioURL = "../audio/" +this.form.folder+"/"+ this.audioName;
        console.log(this.audioURL);
        this.isPlay = false;

        setTimeout(() => {
          this.$refs.range.value = 0;
        }, 1);
        this.gridData.push(this.form.qaRelation);
        this.open = true;
        this.title = "QA审核";
      });
    },
    //辅助函数，将秒变成分秒的形式--用在计算属性中
    formatTime(value) {
      let second = 0;
      let minute = 0;
      minute = parseInt(value / 60);
      second = parseInt(value % 60);
      // 补0
      minute = minute < 10 ? "0" + minute : minute;
      second = second < 10 ? "0" + second : second;
      return minute + ":" + second;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            this.form.result = null;
            this.form.cuda=this.selectvalue.toString();
            this.form.reqUser = this.$store.state.user.name;
            updateVerityQa(this.form).then((response) => {
             // console.log("提交数据",this.form)
              this.$modal.msgSuccess("修改成功");
              this.open = false;

              this.getList();
            });
          } else {
            addExtract(this.form).then((response) => {
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
  // watch: {
    //当关闭时，强制重新加载子组件
    // stopAudio() {
      // console.log("this.stopAudio,watch",this.stopAudio)
      // this.loadNewAudio();
  //   },
  // },
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
