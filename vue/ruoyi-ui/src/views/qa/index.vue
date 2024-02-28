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
      <el-form-item label="模糊分类" prop="audioPath">
        <el-input
          v-model="queryParams.result"
          placeholder="请输入关键字（如：预约）"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="问题分类">
        <el-cascader
          v-model="getvalue"
          placeholder="选择分类"
          :options="options"
          :props="props"
          @change="handleChange2"
        ></el-cascader>
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
          @click="handleExport"
          v-hasPermi="['qa:extract:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="extractList"
      @selection-change="handleSelectionChange"
      ref="filterTable"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column
        label="音频路径"
        align="center"
        prop="audioPath"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="执行结果"
        align="center"
        prop="result"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="标注前Q&A"
        align="center"
        prop="qaExtract"
        :show-overflow-tooltip="true"
      />

      <el-table-column
        label="标注后Q&A"
        align="center"
        prop="qaSum"
        :show-overflow-tooltip="true"
      />
      <!-- <el-table-column label="音频长度" align="center" prop="audioTime" /> -->
      <!-- <el-table-column label="类别" align="center" prop="cuda" /> -->
      <el-table-column label="类别" align="center" prop="cuda">
        <template label="类别" align="center" slot-scope="scope">
          {{ selectClazzLabel(options,scope.row.cuda) }}
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
      <!-- 
      <el-table-column
        label="是否标注"
        align="center"
        prop="isMark"
        :show-overflow-tooltip="true"
      /> -->
      <el-table-column
        label="修改时间"
        align="center"
        prop="updateTime"
        width="180"
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

    <!-- 添加或修改extract对话框 -->
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

        <!-- <el-cascader
          v-model="sevalue"
          placeholder="选择问题分类"
          
          :options="options"
          :props="props"
          @change="handleChange"
        ></el-cascader> -->

        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- <el-dialog
      title="提示"
      :visible.sync="dialogVisible2"
      width="30%"
      :before-close="handleClose2"
    >
      <span>是否关联QA1?</span>

      <span slot="footer" class="dialog-footer">
        <div>
          <p class="floating-text">已关联数据：</p>
        </div>
        <el-table :data="gridData">
          <el-table-column
            property="qa1"
            label="QA1"
            align="center"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            property="qa2"
            label="QA2"
            align="center"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDeleteRel(scope.row)"
                v-hasPermi="['qa:relation:remove']"
                >删除</el-button
              >
            </template>
          </el-table-column>
          
        </el-table>
        <el-button @click="dialogVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="handleRelUpdate()">确 定</el-button>
      </span>
    </el-dialog> -->
    <!-- <el-dialog
      title="提示"
      :visible.sync="dialogVisible3"
      width="20%"
      :before-close="handleClose3"
    >
      <span>是否关联QA2?</span>

      <span slot="footer" class="dialog-footer">
        <div>
          <p class="floating-text">已关联数据：</p>
        </div>
        <el-table :data="gridData">
          <el-table-column
            property="qa1"
            align="center"
            label="QA1"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            property="qa2"
            align="center"
            label="QA2"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            property="qa3"
            align="center"
            label="QA3"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            label="操作"
            align="center"
            :show-overflow-tooltip="true"
          >
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              >删除</el-button
            >
          </el-table-column>
        </el-table>
        <el-button @click="dialogVisible3 = false">取 消</el-button>
        <el-button type="primary" @click="handleRelUpdate2()">确 定</el-button>
      </span>
    </el-dialog> -->
  </div>
</template>

<script>
import {
  listExtract,
  getExtract,
  delExtract,
  addExtract,
  optionsExtract,
  updateExtract,
} from "@/api/qa/extract";
import {listBatch } from "@/api/total/batch";
import {
  listRelation,
  getRelation,
  delRelation,
  addRelation,
  updateRelation,
} from "@/api/qa/relation";
import {allByUser} from "@/api/task/user";
export default {
  name: "Extract",
  data() {
    return {
      insertBatch: [],
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
      taskList: [],
      props:{
        value:"id"
      },
      getvalue:"",
      audioURL: "",
      isPlay: false, // 控制icon切换
      totalTime: 0, // 播放总时间--秒
      currentTime: 0, // 当前播放时间--秒
      sevalue: [], //标签选择值
      options: [],
      selectvalue: "",
      gridData: [],
      res_id: 0,
      dialogVisible2: false,
      dialogVisible3: false,
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
      // extract表格数据
      extractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      audioName: "",
      formatCurrentTime: 0,
      formatTotalTime: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        audioPath: null,
        result: null,
        qaExtract: null,
        taskOwner: null,
        isDelete: "否",
        cuda:"",
        insertBatchId :null
      },
      // 表单参数
      form: {},
      rel_form: {},
      // 表单校验
      rules: {},
      isPlaying: false,
      audioElement: null,
    };
  },
  created() {
    this.getList();
    this.getOptions();
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

    selectClazzLabel(array,targetId) {
    if(targetId ==undefined ||targetId == null){
      return null;
    }
   targetId = parseInt(targetId)
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

    sentenceTrim(){
      this.form.qa1 = this.form.qa1.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，").replaceAll(":","：").replaceAll("客户：","客户问：").replaceAll("客服：","客服答：").replaceAll("客户询问：","客户问：").replaceAll("客服回答：","客服答：");
      this.form.qa2 = this.form.qa2.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，").replaceAll(":","：").replaceAll("客户：","客户问：").replaceAll("客服：","客服答：").replaceAll("客户询问：","客户问：").replaceAll("客服回答：","客服答：");
      this.form.qa3 = this.form.qa3.replaceAll(" ","").replaceAll(",","，").replaceAll("?","？").replaceAll("/n","").replaceAll("，，","，").replaceAll("。。","。").replaceAll("？？","？").replaceAll("，。","，").replaceAll(":","：").replaceAll("客户：","客户问：").replaceAll("客服：","客服答：").replaceAll("客户询问：","客户问：").replaceAll("客服回答：","客服答：");
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

    getTaskList(){
      var userName = this.$store.state.user.name;
      var qa = 1;
      allByUser(userName,qa).then((response) => {
        this.taskList = response.rows.map((row) => { return { taskId: row.taskId, taskName: row.taskName }; }); 
        console.log(this.taskList);
      });
      
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



    // 控制音乐播放
    play() {
      const audio = this.$refs.audio2;
      audio.play();
      this.isPlay = true;
    },
    // 控制音乐暂停
    pause() {
      const audio = this.$refs.audio2;
      audio.pause();
      this.isPlay = false;
    },
    // 音乐缓存完毕，获取时间
    loadingFinish() {
      const totalTime = this.$refs.audio2.duration;
      this.totalTime = totalTime;
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
    // 通过url获取filename
    getFilename() {
     // console.log(this.form.audioPath);
      const parts = this.form.audioPath.split("/");
      const audioName = parts[parts.length - 1];
    
      this.$refs.audio.src = "audio/" + audioName;
      return audioName;
    },

    /** 删除按钮操作 */
    handleDeleteRel(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除编号为"' + ids + '"的数据项？')
        .then(function () {
          return delRelation(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
          this.dialogVisible = false;
          this.dialogVisible2 = false;
          this.open = false;
        })
        .catch(() => {});
    },

    handleRelUpdate() {
      this.rel_form.asrResultId = this.res_id;
      this.rel_form.qa1 = this.form.qa1;
      this.rel_form.qa2 = this.form.qa2;
      // this.rel_form.qa3=this.form.qa3
      // this.rel_form.qa4=this.form.qa4
      // this.rel_form.qa5=this.form.qa5

      updateRelation(this.rel_form).then((response) => {
        this.$modal.msgSuccess("添加成功");
        this.rel_form.id == null;
        this.dialogVisible2 = false;
        this.getRelList();
        this.gridData = [];
        this.open = false;
      });
    },

    handleRelUpdate2() {
      this.rel_form.asrResultId = this.res_id;
      this.rel_form.qa1 = this.form.qa1;
      this.rel_form.qa2 = this.form.qa2;
      this.rel_form.qa3 = this.form.qa3;
      // this.rel_form.qa4=this.form.qa4
      // this.rel_form.qa5=this.form.qa5
      this.rel_form.id = this.res_id;

      updateRelation(this.rel_form).then((response) => {
        this.$modal.msgSuccess("添加成功");
        this.rel_form.id == null;
        this.dialogVisible3 = false;
        this.getRelList();
        this.gridData = [];
        this.open = false;
      });
    },

    handleClose2(done) {
      this.$confirm("确认关闭管理2？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },

    handleClose3(done) {
      this.$confirm("确认关闭管理3？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },

    getRelList() {
      listRelation(this.queryParams).then((response) => {
        this.relationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询extract列表 */
    getList() {
      this.loading = true;
      this.queryParams.taskOwner = this.$store.state.user.name;
      console.log(this.queryParams);
      listExtract(this.queryParams).then((response) => {
        this.extractList = response.rows;
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
       this.options =response.data.options;
        //this.total = response.total;
        //this.loading = false;
      });
    },

    // getOptions() {
    //   optionsExtract(this.queryParams).then((response) => {
    //    // this.extractList = response.rows;
    //    this.options =response.data.options;
    //     //this.total = response.total;
    //     //this.loading = false;
    //   });
    // },


    // 取消按钮
    cancel() {
      this.sevalue = [];
      this.open = false;
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
      (this.gridData = []), this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        audioPath: null,
        result: null,
        qaExtract: null,
        taskOwner: null,
        isDelete: "否",
        cuda:"",
        insertBatchId :null
      };
      
      this.queryParams.cuda="";
      this.queryParams.result="";
      this.getvalue="";
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
      this.title = "添加extract";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.res_id = row.id;
      const id = row.id || this.ids;
      getExtract(id).then((response) => {

        this.form = response.data;
        this.sevalue = response.data.clazzId;
      //  console.log(response.data)
      //   console.log(this.sevalue)
       // this.options=response.data.options;
        const parts = this.form.audioPath.split("/");
        this.audioName = parts[parts.length - 1];
        this.formatCurrentTime = this.formatTime(this.currentTime);
        this.formatTotalTime = this.formatTime(this.totalTime);
        this.audioURL = "audio/" +this.form.folder+"/"+ this.audioName;
        console.log( this.audioURL);
        this.isPlay = false;

        setTimeout(() => {
          this.$refs.range.value = 0;
        }, 1);
        this.gridData.push(this.form.qaRelation);
        this.open = true;
        this.title = "修改extract";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            this.form.labelUserName = this.$store.state.user.name;
            this.form.cuda=this.selectvalue.toString();
            // this.form.clazzId=this.sevalue;
            console.log(this.form);
            updateExtract(this.form).then((response) => {
           
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
        .confirm('是否确认删除extract编号为"' + ids + '"的数据项？')
        .then(function () {
          return delExtract(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "qa/extract/export",
        {
          ...this.queryParams,
        },
        `qa_${new Date().getTime()}.json`
      );
    },
  },
};
</script>
<style scoped>
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
.el-tooltip__popper {
  max-width: 30%;
}
.floating-text {
  float: left; /* 让文本左浮动 */
  margin-right: 10px; /* 可选：增加一些右边距以分隔文本和其他内容 */
}

.audio-container {
  padding: 8px 16px;
  width: 100%;
  background: #f5f6f8;
  border-radius: 2px;
  display: flex;
}
.left {
  margin-right: 16px;
}

.icon {
  display: inline-block;
  width: 28px;
  height: 28px;
  border: 2px solid #10a9ff;
  border-radius: 50%;

  text-align: center;
  font-size: 16px;
  line-height: 28px;

}
.icon:hover {
  cursor: pointer;
}
.play-icon {
  position: relative;
  left: 2px;
}
.flex-between {
  display: flex;
  justify-content: space-between;
  align-content: center;
}
.right {
  flex: 1;
}
.words {
  margin-bottom: -1px;
}
.name {
  font-size: 14px;
  color: #333333;
  line-height: 14px;
}
.time {
  font-size: 14px;
  color: #666666;
  line-height: 14px;
}

input[type="range"] {
  outline: none;

  width: 100% !important;
  background: -webkit-linear-gradient(#10a9ff, #10a9ff) no-repeat, #dddddd; /*背景颜色，俩个颜色分别对应上下*/
  background-size: 0% 100%; /*设置左右宽度比例，这里可以设置为拖动条属性*/
  height: 2px; /*横条的高度，细的真的比较好看嗯*/
}
/*拖动块的样式*/
input[type="range"]::-webkit-slider-thumb {
  -webkit-appearance: none; /*清除系统默认样式*/
  height: 10px; /*拖动块高度*/
  width: 3px; /*拖动块宽度*/
  background: #10a9ff; /*拖动块背景*/
}
</style>
