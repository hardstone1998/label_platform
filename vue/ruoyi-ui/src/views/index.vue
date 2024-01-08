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

      <el-form-item>
        <el-dropdown @command="handleCommandUse">
          <span class="el-dropdown-link">
            是否使用<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">是</el-dropdown-item>
            <el-dropdown-item command="b">否</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-form-item>

      <el-form-item>
        <el-dropdown @command="handleCommandMark">
          <span class="el-dropdown-link">
            是否已标注<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">是</el-dropdown-item>
            <el-dropdown-item command="b">否</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
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
          v-hasPermi="['asr:tag:export']"
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
      <el-table-column prop="isUse" label="是否使用" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.isUse === '是' ? 'success' : 'danger'"
            disable-transitions
            >{{ scope.row.isUse }}</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column prop="isMask" label="是否已标注" width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.isMask === '是' ? 'primary' : 'info'"
            disable-transitions
            >{{ scope.row.isMask }}</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column
        label="修改时间"
        align="center"
        prop="updateTime"
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
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
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
            :rows="4"
            v-model="computedValue"
            placeholder="标注内容"
          />
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
        <el-cascader
          v-model="sevalue"
          placeholder="选择问题分类"
          
          :options="options"
          :props="props"
          @change="handleChange"
        ></el-cascader>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <!-- <el-button size="mini" type="text" icon="el-icon-plus"  @click="addTags()">添加标签</el-button> -->
        <!-- <el-button type="primary" @click="addTags">添加标签</el-button> -->
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="title" :visible.sync="opentagadd" width="500px" append-to-body>
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
    </el-dialog>
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

import {addTag, updateTag } from "@/api/asr/tag";
import markAsr from "./markAsr.vue";

export default {
  //
  name: "Annotation",

  components: { markAsr },
  data() {
    return {
      checkAll: false,
      checkedCities: [],
      sevalue: [], //标签选择值
      options: [],
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
        isUse: null,
        isMask: null,
        taskOwner: null,
      },
      // 表单参数
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
    this.getOptions();
  },

  methods: {
    handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.cities.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
      },
    /** 查询标注列表 */
    getList() {
      this.loading = true;
      this.queryParams.taskOwner = this.$store.state.user.name;
      listAnnotation(this.queryParams).then((response) => {
        this.annotationList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getOptions() {
      optionsExtract().then((response) => {
       // this.extractList = response.rows;
       this.options =response.data.options;
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
    submitFormtagadd(){
    
      this.$refs["formadd"].validate(valid => {
        if (valid) {
            addTag(this.formadd).then(response => {
              this.$modal.msgSuccess("添加成功，请重新进入该界面");
              this.opentagadd = false;
              this.formadd.tagName=null;
              this.formadd.createUser=null;
             // this.getList();
            });
          
        }
      });
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
        isMask: null,
        createTime: null,
        // dynamicTags: null,
        updateTime: null,
        sevalue:null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    handleCommandUse(command) {
      if (command === "b") {
        this.queryParams.isUse = "否";
      } else {
        this.queryParams.isUse = "是";
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },

    handleCommandMark(command) {
      if (command === "b") {
        this.queryParams.isMask = "否";
      } else {
        this.queryParams.isMask = "是";
      }
      this.queryParams.pageNum = 1;
      this.getList();
    },
    handleClickMark() {},
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.isUse = null;
      this.queryParams.isMask = null;
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
      this.audio_name_1 = row.audioName;
      //发送一个请求，根据id查对应的标签名字
      this.sevalue = []
      // this.dynamicTags = [];
      this.reset();
      this.isPopupVisible = true;
      this.stopAudio = false;
      const id = row.id || this.ids;
      getAnnotation(id).then((response) => {
        this.form = response.data;
        this.cities = response.data.dynamicTags;
        this.checkedCities=response.data.selectTags;
      
        this.open = true;
        this.title = "添加标签";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.sevalue = this.sevalue;
      // this.form.dynamicTags = this.dynamicTags;
      this.form.selectTags=this.checkedCities;
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateAnnotation(this.form).then((response) => {
              this.$modal.msgSuccess("标注成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnnotation(this.form).then((response) => {
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
          return delAnnotation(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("设置成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "asr/annotation/export",
        {
          ...this.queryParams,
        },
        `annotation_${new Date().getTime()}.json`
      );
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
