<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card" style="text-align: left">
        <div slot="header" class="clearfix title">
          <span>{{audioName}}</span>
        </div>
        <div id="waveform" ref="waveform"></div>
        <div id="wave-timeline" ref="wave-timeline">
          <!--时间轴 -->
        </div>
        <div>
          <el-button type="primary" @click="playMusic" size="small">
            <i class="el-icon-video-play"></i>
            播放 /
            <i class="el-icon-video-pausee"></i>
            暂停
          </el-button>
        </div>
        
      </el-card>
    </el-row>
  </div>
</template>
  <script>
import WaveSurfer from "wavesurfer.js";
import Timeline from "wavesurfer.js/dist/plugin/wavesurfer.timeline.js";
export default {
  name: "Details",
  props: ['audioName','stopAudio'],
  data() {
    return {
      wavesurfer: null,
    };
  },
  mounted() {
      this.initializeWaveSurfer();
  },
  methods: {
    initializeWaveSurfer() {
      this.wavesurfer = WaveSurfer.create({
        container: this.$refs.waveform,
        waveColor: "#409EFF",
        progressColor: "blue",
        backend: "MediaElement",
        mediaControls: false,
        audioRate: "1",
        plugins: [
          Timeline.create({
            container: "#wave-timeline",
          }),
        ],
      });
      this.loadAudio();
    },

    initializeWaveSurfer2(audio_name_1) {
      this.wavesurfer = WaveSurfer.create({
        container: this.$refs.waveform,
        waveColor: "#409EFF",
        progressColor: "blue",
        backend: "MediaElement",
        mediaControls: false,
        audioRate: "1",
        plugins: [
          Timeline.create({
            container: "#wave-timeline",
          }),
        ],
      });
      this.loadAudio2(audio_name_1);
    },

    loadAudio(){
      if(this.audioName==null){
        //console.log("取消按钮")
      }else{        
        // var audioPath = "/mnt/vdc/mydata/nginx/html/audio/" + this.audioName;
        // this.wavesurfer.load(require("../assets/audio/" + this.audioName));
        // this.wavesurfer.load(audioPath);
        // this.wavesurfer.load("audio/"+ this.audioName);
        this.wavesurfer.load(this.audioName);
      }
      
    },
    loadAudio2(audio_name_1){
      // var audioPath = "/mnt/vdc/mydata/nginx/html/audio/" +audio_name_1;
      // this.wavesurfer.load(require("../assets/audio/" + audio_name_1));
      // this.wavesurfer.load(audioPath);
      // this.wavesurfer.load("audio/" +audio_name_1);
      console.log("audio_name_1:");
      console.log(audio_name_1);
      this.wavesurfer.load(audio_name_1);
    },

    playMusic() {
      //console.log("---->",this.audioName)
      //"播放/暂停"按钮的单击触发事件，暂停的话单击则播放，正在播放的话单击则暂停播放
      this.wavesurfer.playPause();
    },
    reloadAudio() {
      this.wavesurfer.destroy();
      this.initializeWaveSurfer();
    },

    reloadAudio2(audio_name_1) {
      this.wavesurfer.destroy();
      this.initializeWaveSurfer2(audio_name_1);
    },
  },
};
</script>
  <style scoped>
.mixin-components-container {
  background-color: #f0f2f5;
  padding: 30px;
  min-height: 200px;
}
</style>