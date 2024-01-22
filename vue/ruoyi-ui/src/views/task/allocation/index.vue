<template>
  <div class="app-container home">
    <el-row :gutter="40" style="margin-bottom: 100px">
      <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
        <el-card
          style="margin: -10px; background-color: #fafafa"
          shadow="never"
        >
          <h3 style="font-weight: bold">任务统计</h3>
          <el-row :gutter="40" class="panel-group">
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-blue">
                  <svg-icon icon-class="device" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">ASR总任务</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.asrCountSum"
                    :duration="3000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-red">
                  <svg-icon
                    icon-class="monitor-a"
                    class-name="card-panel-icon"
                  />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">QA总任务</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.qacountSum"
                    :duration="3000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-blue">
                  <svg-icon icon-class="model" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">ASR已标注</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.asrMarkedSum"
                    :duration="1000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-red">
                  <svg-icon icon-class="alert" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">QA已标注</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.qamarkedSum"
                    :duration="1000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-blue">
                  <svg-icon icon-class="log-a" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">ASR未标注</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.asrNotMarkedSum"
                    :duration="2000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-red">
                  <svg-icon icon-class="event-a" class-name="card-panel-icon" />
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">QA未标注</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.qanotMarkedSum"
                    :duration="2000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>


      <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
        <el-card
          style="margin: -10px; background-color: #fafafa"
          shadow="never"
        >
          <h3 style="font-weight: bold">任务分配</h3>
          <el-row :gutter="40" class="panel-group">
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-blue">
                  <el-button type="primary" icon="el-icon-edit" @click="handleAdd(0)" circle></el-button>
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">ASR分配</div>
                </div>
              </div>
            </el-col>
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-red">
                  <el-button type="success" icon="el-icon-check"  @click="handleAdd(1)" circle></el-button>
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">QA分配</div>
                  
                </div>
              </div>
            </el-col>
            <!-- ASR已分配 -->
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-blue">
                  <el-upload
                    ref="uploadASR"
                    class="upload-component"
                    action="/audioUpload/asr"
                    :show-file-list="false"
                    :before-upload="file => validateFile(file, '/audioUpload/asr')"
                  >
                    <el-button type="info" icon="el-icon-message" circle @click="uploadASR">上传ASR文件</el-button>
                  </el-upload>
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">ASR已分配</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.asrAllocationSum"
                    :duration="2000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>

            <!-- QA已分配 -->
            <el-col :span="12" class="card-panel-col">
              <div class="card-panel">
                <div class="card-panel-icon-wrapper icon-red">
                  <el-upload
                    ref="uploadQA"
                    class="upload-component"
                    action="/audioUpload/qa"
                    :show-file-list="false"
                    :before-upload="file => validateFile(file, '/audioUpload/qa')"
                  >
                    <el-button type="warning" icon="el-icon-star-off" circle @click="uploadQA">上传QR文件</el-button>
                  </el-upload>
                </div>
                <div class="card-panel-description">
                  <div class="card-panel-text">QA已分配</div>
                  <count-to
                    :start-val="0"
                    :end-val="countData.qallocationSum"
                    :duration="2000"
                    class="card-panel-num"
                  />
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

    

    <el-row :gutter="40">
      
      <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="4">
        <el-card
          style="
            margin: -10px;
            height: 218px;
            margin-bottom: 30px;
            background-color: #fafafa;
          "
          shadow="hover"
        >
          <div ref="pieCpu" style="height: 200px; margin-bottom: -20px"></div>
        </el-card>
      </el-col>


      <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="4">
        <el-card
          style="
            margin: -10px;
            height: 218px;
            margin-bottom: 30px;
            background-color: #fafafa;
          "
          shadow="hover"
        >
          <div
            ref="pieMemery"
            style="height: 200px; margin-bottom: -20px"
          ></div>
        </el-card>
      </el-col>


      <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="4">
        <el-card
          style="
            margin: -10px;
            height: 218px;
            margin-bottom: 30px;
            background-color: #fafafa;
          "
          shadow="hover"
        >
          <div ref="pieDisk" style="height: 200px; margin-bottom: -20px"></div>
        </el-card>
      </el-col>


      <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="4">
        <el-card
          style="
            margin: -10px;
            height: 218px;
            margin-bottom: 30px;
            background-color: #fafafa;
          "
          shadow="hover"
        >
          <div ref="pieDisk2" style="height: 200px; margin-bottom: -20px"></div>
        </el-card>
      </el-col>


      <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="4">
        <el-card
          style="
            margin: -10px;
            height: 218px;
            margin-bottom: 30px;
            background-color: #fafafa;
          "
          shadow="hover"
        >
          <div ref="pieCpu2" style="height: 200px; margin-bottom: -20px"></div>
        </el-card>
      </el-col>

      
      <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="4">
        <el-card
          style="
            margin: -10px;
            height: 218px;
            margin-bottom: 30px;
            background-color: #fafafa;
          "
          shadow="hover"
        >
          <div
            ref="pieMemery2"
            style="height: 200px; margin-bottom: -20px"
          ></div>
        </el-card>
      </el-col>
      
    </el-row>

  <!-- 任务分配对话框 -->
  <el-dialog :title="task_title" :visible.sync="open" width="80%"  append-to-body>
    <!-- Task Form -->
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="任务名" prop="name">
        <el-input v-model="form.name" placeholder="请输入任务名" />
      </el-form-item>
      <el-form-item label="任务描述" prop="desc">
        <el-input v-model="form.desc" type="textarea" placeholder="请输入内容" />
      </el-form-item>
      <el-form-item label="任务类型" prop="clazz">
        <el-input v-model="form.clazz" placeholder="请输入任务类型" readonly />
      </el-form-item>
    </el-form>

    <!-- User and Task Assignment Section -->
    <el-button @click="addUser">添加用户</el-button>
    <el-row :gutter="20" v-for="(row, index) in userTaskRows" :key="index">
      <el-col :span="10">
        <!-- User List -->
        <h4>用户列表</h4>
        
        <el-select v-model="row.selectedUsers" multiple placeholder="请选择用户">
          <el-option
            v-for="user in userList"
            :key="user.id"
            :label="user.nickname"
            :value="user.id"
          />
        </el-select>
        <el-button @click="removeUser(index)">删除用户</el-button>
      </el-col>

      <el-col :span="7">
        <!-- Task Assignment -->
        <h4>问题分类</h4>
        <el-cascader
          v-model="row.sevalue"
          placeholder="选择问题分类"
          :options="options"
          :props="props"
          @change="handleChange(index, $event)"
        ></el-cascader>
        </el-col>
        <el-col :span="5">
          <h4>任务数量</h4>
          <el-input v-model="row.taskQuantity" placeholder="请输入任务数量" />

        <!-- <el-button @click="addTask">添加任务</el-button> -->
      </el-col>
    </el-row>

    <!-- Assigned Tasks List -->
    <h4>已分配任务列表</h4>
    <ul>
      <li v-for="assignment in userTaskAssignments" :key="assignment.id">
        {{ assignment.user.name }} - {{ assignment.task.name }} - {{ assignment.taskType.name }} - {{ assignment.taskQuantity }}
      </li>
    </ul>

    <!-- Dialog Footer -->
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>

  </div>
  
</template>

<script>
 import * as echarts from 'echarts';
 import CountTo from 'vue-count-to'
 require('echarts/theme/macarons') // echarts theme
  require('echarts/extension/bmap/bmap')
  import {
      getServer
  } from "@/api/monitor/server";

  import {
    getData
  } from "@/api/asr/annotation";
  import {
    uploadASRFile,uploadQAFile,addTask,updateTask
  } from "@/api/task/allocation";
  import {
  optionsExtract
} from "@/api/qa/extract";
import {
  totalUser
} from "@/api/system/user"

export default {
   components: {
        CountTo,
        
    },
  data() {
    return {
      countData: {
        asrAllocationSum: 0,
        qallocationSum: 0,
      },
      userTaskRows: [
        // Add more rows as needed
      ],
      userTaskAssignments: [],
      taskTypeList: [
      { id: 1, name: 'Type 1' },
      { id: 2, name: 'Type 2' },
      ],
      userList: [],
      taskList: [
        { id: 101, name: 'Task 1' },
        { id: 102, name: 'Task 2' },
        // Add more tasks as needed
      ],
      sevalue: [],
       //标签选择值
      options: [],
      selectedUser: null,
      selectedTask: null,
      userTaskAssignments: [],
      // 弹出层标题
      task_title: "任务分配",
      // 表单参数
      form: {},
      // 遮罩层
      loading: true,
      // 是否显示弹出层
      open: false,
      // 是否为管理员
      isAdmin: false,
      test1: {},
      deviceStatistic:{},
      countData:{},
      // 服务器信息
      server: {
        jvm: {
          name: "",
          version: "",
          startTime: "",
          runTime: "",
          used: "",
          total: 100,
        },
        sys: {
          computerName: "",
          osName: "",
          computerIp: "",
          osArch: "",
        },
        cpu: {
          cpuNum: 1,
        },
        mem: {
          total: 2,
        },
      },
    };
  },
  created() {
   
    this.init();
  },
  
  methods: {
    init() {
      if (
        this.$store.state.user.roles.indexOf("tenant") === -1 &&
        this.$store.state.user.roles.indexOf("general") === -1
      ) {
        this.isAdmin = true;
        this.getServer();
        this.getData();
        this.getOptions();
        this.getUsers();
      }RFile
    },

    handleChange(index, value) {
     // Check if value is an array and not empty
     if (Array.isArray(value) && value.length > 0) {
        // Assign the last string to row.sevalue
        this.userTaskRows[index].sevalue = value[value.length - 1];
      } else {
        // Reset row.sevalue if the array is empty or not an array
        this.userTaskRows[index].sevalue = "";
      }
      },
    validateFile(file,url) {
      const allowedFormats = ['mp3', 'wav', 'zip'];
      const fileNameArr = file.name.split('.');
      const fileFormat = fileNameArr[fileNameArr.length - 1].toLowerCase();
      if (!allowedFormats.includes(fileFormat)) {
        this.$message.error('只能上传mp3、wav音频文件或zip压缩包');
        return false; // 阻止上传
      }
      this.handleFileUpload(file, url);
    },
    async uploadASR() {
      // Trigger file selection
      const fileInput = this.$refs.uploadASR.$refs.input;
      fileInput.click();
    },
    async uploadQA() {
      // Trigger file selection
      const fileInput = this.$refs.uploadQA.$refs.input;
      fileInput.click();
    },
    async handleFileUpload(file, url) {
      try {
        if (url === '/audioUpload/asr') {
          await uploadASRFile(file);
        } else if (url === '/audioUpload/qa') {
          await uploadQAFile(file);
        }
        // File upload successful logic, you can update UI or perform other operations
        console.log('File uploaded successfully');
      } catch (error) {
        // Handle the case when the upload fails
        console.error('File upload failed:', error);
      }
    },

    removeUser(index) {
    this.userTaskRows.splice(index, 1);
  },
  addUser() {
    this.userTaskRows.push({ selectedUsers: [], sevalue: null, taskQuantity: null });
  },
  addTask() {
    const taskId = this.sevalue.length + 1;
    const newTask = { id: taskId, name: `Task ${taskId}` };

    // Ensure that each sevalue property refers to a new array
    this.userTaskRows.forEach(row => {
      row.sevalue = [...row.sevalue];
      row.sevalue.push(newTask);
    });
  },
    assignTasks() {
      for (const row of this.userTaskRows) {
        if (row.selectedUsers.length > 0 && row.selectedTaskType && row.taskQuantity) {
          for (const userId of row.selectedUsers) {
            const user = this.userList.find((u) => u.id === userId);

            // Assuming each assignment has a unique ID
            const assignmentId = this.userTaskAssignments.length + 1;

            this.userTaskAssignments.push({
              id: assignmentId,
              user,
              task: { name: row.taskQuantity }, // Assuming task name is the quantity
              taskType: this.sevalue.find((type) => type.id === row.selectedTaskType),
              taskQuantity: row.taskQuantity,
            });
          }
        }
      }

      // Optionally, clear the selected users, task type, and task quantity after assignment
      this.userTaskRows.forEach((row) => {
        row.selectedUsers = [];
        row.selectedTaskType = null;
        row.taskQuantity = null;
      });
    },

    getData() {
      getData().then((response) => {
        this.countData=response.data;
       console.log(response)
      });
    },

    
    getServer() {
      getServer().then((response) => {
        this.server = response.data;
        this.$nextTick(() => {
          this.drawPieCpu();
          this.drawPieMemery();
          this.drawPieDisk();

          this.drawPieCpu2();
          this.drawPieMemery2();
          this.drawPieDisk2();
        });
      });
    },

    // 取消按钮
    cancel() {
      this.sevalue = [];
      this.open = false;
      this.reset();
    },

    reset() {
      this.form = {
        name: null,
        desc: null,
        clazz: null,
        userTaskRows: null
      };
      this.resetForm("form");
    },

     /** 任务分配操作 */
     handleAdd(clazz) {
      this.reset();
      this.open = true;
      this.task_title = "任务分配";
      this.form.clazz = clazz;
    },
    getUsers() {
      totalUser().then((response) => {
       this.userList = response.rows.map(row => ({ id: row.userId, nickname: row.nickName }));
      });
    },

    getOptions() {
      optionsExtract().then((response) => {
       this.options =response.data.options;
      //  console.log(this.options)
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.taskAllocationUser = this.userTaskRows;
      console.log(this.form);
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            console.log(this.form)
            addTask(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },

    drawPieCpu() {
              // 基于准备好的dom，初始化echarts实例
              let myChart = echarts.init(this.$refs.pieCpu);
              var option;
              option = {
                  title: {
                      text: '用户1',
                      left: 'left'
                  },
                  tooltip: {
                      trigger: 'item',
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'right'
                  },
                  color: ['#E6A23C', '#F56C6C', '#DDD'],
                  series: [{
                      name: '用户1',
                      type: 'pie',
                      radius: '55%',
                      label: {
                          show: false
                      },
                      labelLine: {
                          normal: {
                              position: "inner",
                              show: false
                          }
                      },
                      data: [{
                              value: this.server.cpu.used,
                              name: 'QA'
                          },
                          {
                              value: this.server.cpu.sys,
                              name: 'ASR'
                          },
                          {
                              value: this.server.cpu.free,
                              name: '剩余'
                          }
                      ],
                  }]
              };
              option && myChart.setOption(option);
          },

          drawPieMemery() {
              // 基于准备好的dom，初始化echarts实例
              let myChart = echarts.init(this.$refs.pieMemery);
              var option;
              option = {
                  title: {
                      text: '用户2',
                      left: 'left'
                  },
                  tooltip: {
                      trigger: 'item'
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'right'
                  },
                  color: ['#F56C6C', '#DDD'],
                  series: [{
                      name: '用户2',
                      type: 'pie',
                      radius: '55%',
                      label: {
                          show: false
                      },
                      labelLine: {
                          normal: {
                              position: "inner",
                              show: false
                          }
                      },
                      data: [{
                              value: this.server.mem.used,
                              name: 'QA'
                          },
                          {
                              value: this.server.mem.free,
                              name: '剩余'
                          }
                      ]
                  }]
              };
              option && myChart.setOption(option);
          },
          drawPieDisk() {
              // 基于准备好的dom，初始化echarts实例
              let myChart = echarts.init(this.$refs.pieDisk);
              var option;
              let one = this.server.sysFiles[0].used.replace("GB", "");
              let two = this.server.sysFiles[0].free.replace("GB", "");;
              option = {
                  title: {
                      text: '用户3',
                      left: 'left'
                  },
                  tooltip: {
                      trigger: 'item'
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'right'
                  },
                  color: ['#F56C6C', '#DDD'],
                  series: [{
                      name: '用户3',
                      type: 'pie',
                      radius: '55%',
                      label: {
                          show: false
                      },
                      labelLine: {
                          normal: {
                              position: "inner",
                              show: false
                          }
                      },
                      data: [{
                              value: one,
                              name: 'ASR'
                          },
                          {
                              value: two,
                              name: '剩余'
                          }
                      ]
                  }]
              };
              option && myChart.setOption(option);
          },
      
          drawPieCpu2() {
              // 基于准备好的dom，初始化echarts实例
              let myChart = echarts.init(this.$refs.pieCpu2);
              var option;
              option = {
                  title: {
                      text: '用户4',
                      left: 'left'
                  },
                  tooltip: {
                      trigger: 'item',
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'right'
                  },
                  color: ['#E6A23C', '#F56C6C', '#DDD'],
                  series: [{
                      name: '用户4',
                      type: 'pie',
                      radius: '55%',
                      label: {
                          show: false
                      },
                      labelLine: {
                          normal: {
                              position: "inner",
                              show: false
                          }
                      },
                      data: [{
                              value: this.server.cpu.used,
                              name: 'QA'
                          },
                          {
                              value: this.server.cpu.sys,
                              name: 'ASR'
                          },
                          {
                              value: this.server.cpu.free,
                              name: '剩余'
                          }
                      ],
                  }]
              };
              option && myChart.setOption(option);
          },

          drawPieMemery2() {
              // 基于准备好的dom，初始化echarts实例
              let myChart = echarts.init(this.$refs.pieMemery2);
              var option;
              option = {
                  title: {
                      text: '用户5',
                      left: 'left'
                  },
                  tooltip: {
                      trigger: 'item'
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'right'
                  },
                  color: ['#F56C6C', '#DDD'],
                  series: [{
                      name: '用户5',
                      type: 'pie',
                      radius: '55%',
                      label: {
                          show: false
                      },
                      labelLine: {
                          normal: {
                              position: "inner",
                              show: false
                          }
                      },
                      data: [{
                              value: this.server.mem.used,
                              name: 'QA'
                          },
                          {
                              value: this.server.mem.free,
                              name: '剩余'
                          }
                      ]
                  }]
              };
              option && myChart.setOption(option);
          },
          drawPieDisk2() {
              // 基于准备好的dom，初始化echarts实例
              let myChart = echarts.init(this.$refs.pieDisk2);
              var option;
              let one = this.server.sysFiles[0].used.replace("GB", "");
              let two = this.server.sysFiles[0].free.replace("GB", "");;
              option = {
                  title: {
                      text: '用户6',
                      left: 'left'
                  },
                  tooltip: {
                      trigger: 'item'
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'right'
                  },
                  color: ['#F56C6C', '#DDD'],
                  series: [{
                      name: '用户6',
                      type: 'pie',
                      radius: '55%',
                      label: {
                          show: false
                      },
                      labelLine: {
                          normal: {
                              position: "inner",
                              show: false
                          }
                      },
                      data: [{
                              value: one,
                              name: 'ASR'
                          },
                          {
                              value: two,
                              name: '剩余'
                          }
                      ]
                  }]
              };
              option && myChart.setOption(option);
          }



  },
};
</script>


<style lang="scss" scoped>
.content {
  line-height: 24px;
  padding: 10px;
  border: 1px solid #eee;
  border-radius: 10px;
}

.description {
  font-size: 14px;

  tr {
    line-height: 26px;
  }
}

.panel-group {
  .card-panel-col {
    margin-bottom: 10px;
  }

  .card-panel {
    height: 68px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    color: #666;
    border: 1px solid #eee;
    border-radius: 5px;
    //box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: #fff;

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-blue {
        background: #36a3f7;
      }

      .icon-green {
        background: #34bfa3;
      }

      .icon-red {
        background: #f56c6c;
      }

      .icon-orange {
        background: #e6a23c;
      }
    }

    .icon-blue {
      color: #36a3f7;
    }

    .icon-green {
      color: #34bfa3;
    }

    .icon-red {
      color: #f56c6c;
    }

    .icon-orange {
      color: #e6a23c;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 10px;
      padding: 10px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 30px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 15px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 14px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 14px;
        margin-bottom: 12px;
        text-align: right;
      }


      .card-panel-text2 {
        line-height: 20px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 20px;
        margin-bottom: 12px;
        text-align: right;
      }
      .card-panel-num {
        font-size: 18px;
      }
    }
  }
}
</style>
