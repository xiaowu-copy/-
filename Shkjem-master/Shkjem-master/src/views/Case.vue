<template>
  <div class="case">
          <!-- drag： 是否有拖拽框
              action：前后端交互的接口
              data：上传所带的参数
              on-success：文件上传成功时的钩子
              before-upload：上传文件之前的钩子，参数为上传的文件
              用:http-request来代替 action方法
              on-progress：文件上传时的钩子 -->
    <banner img="../assets/img/bgtop.jpg" title="资源上传" />
    <!-- //用户进行资源上传的部分 -->
    <!-- //外面的大边框 -->
    <div style=" border:1px solid #9bdf70;background:#f0fbeb" >
      <!-- 选择框 -->
    <div style="margin-left:500px">
      <div class="SelectType"><p>请选择想要上传的资源类型:</p></div>
      <div style="padding-top:30px">
          <el-select @change="select" v-model="value" placeholder="请选择想要上传的资源类型">
          <el-option
            v-for="item in species"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            <span style="float: left">{{ item.label }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
          </el-option>
        </el-select>
     </div> 
    </div>
    <!-- 下面是各个上传的部分 -->
    <div  style="margin-top:50px;margin-left:500px">
      <el-form v-if="isNews" ref="form" :model="form" label-width="80px" >
        <el-form-item label="新闻名称">
          <el-col :span="10">
            <el-input  v-model="form.Title"></el-input>
          </el-col>          
        </el-form-item>

        <el-form-item label="新闻内容" style="margin-top:70px" >
          <el-col :span="15">
            <el-input type="textarea" style="height:102px;"  v-model="form.Content"></el-input>
          </el-col>          
        </el-form-item>

        <el-form-item label="新闻插图" >
          <el-col :span="15">
            <!-- //父组件绑定值向子组件传值 同样子组件向父组件传值要在子组件中定义这个相应的方法 -->
            <upload :auto="isnow" @getImgurl="GetUrl"></upload>
          </el-col>          
        </el-form-item>
        <!-- 上传资源的来源 -->
        <el-form-item label="资源来源">
          <el-col :span="10">
            <el-input  v-model="form.source"></el-input>
          </el-col>          
        </el-form-item>

        <!-- //活动类型选择 -->
        <el-form-item label="新闻类型" style="margin-top:70px">
          <el-checkbox-group v-model="checkList">
        <!-- label绑定的值是要传的值 -->
                <el-checkbox :label="item.value" v-for="(item,index) in type" :key="index">
                  <!-- 视图上显示给用户看的值 -->
                  {{ item.label }}
                </el-checkbox>
      </el-checkbox-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit" >立即上传</el-button>
          <el-button>取消</el-button>
        </el-form-item>
       
        

      </el-form>
      <VideoUpload v-if="isVideo"></VideoUpload>
      <PictureUpload v-if="isImg"></PictureUpload>
    </div>
    </div>

  </div>
</template>
<script>
import upload from "../../api/upload.vue";
import VideoUpload from '../components/video-upload.vue'
import Banner from "../components/Banner";
import PictureUpload from "../components/picture-upload.vue"
export default {
  components: {
    Banner,
    upload,
    VideoUpload,
    PictureUpload

  },
  data() {
    return {
      isNews: 1,
      isVideo: 0,
      isAudio: 0,
      isImg: 0,
      species: [{
          value: 'news',
          label: '新闻'
        }, {
          value: 'audio',
          label: '音频'
        }, {
          value: 'video',
          label: '视频'
        }, {
          value: 'img',
          label: '图像'
        }
      ],
      value: '',
      item: {},
      type: [{label: '文化自信', value: '1'} ,
             {label: '科技强国', value: '2'} ,
             {label: '家国情怀', value: '3'} ,
             {label: '科技思维', value: '4'} ,
             {label: '学生作品', value: '5'} ,
             {label: '微电影', value: '6'} ,],
      checkList:['a'], //他必须是普通的数组. 会默认选中 ”复选框 A“
      videoForm: {
        videoId: '',
        videoUrl: ''
      },
      //这个是向子组件传值的一个参数
      isnow: false,
      videoFlag: false,
      Plus: true,
      videoUploadPercent: 0,
      form: {
          Title: '',
          Content: '',
          // img是图像的地址
          img: '',
          Createtime : '',
          IsAdmited: 0,
          source: '',
          label:''
          
          
        }
    }
  },

  mounted() {
    
  },
  methods: {
    //选择框更改类型
    select(value) {
      if(value==='news'){this.isNews=1,this.isAudio=0,this.isImg=0,this.isVideo=0}
      else if(value==='audio'){this.isNews=0,this.isAudio=1,this.isImg=0,this.isVideo=0}
      if(value==='video'){this.isNews=0,this.isAudio=0,this.isImg=0,this.isVideo=1}
      if(value==='img'){this.isNews=0,this.isAudio=0,this.isImg=1,this.isVideo=0}
    },
    // 立即上传
    onSubmit() {
      //这里是获取多选框选中的内容
      for(var i=1;i<this.checkList.length;i++){
        this.form.label = this.form.label.concat(i+",")
      }
      this.form.label = this.form.label.substring(0, this.form.label.length - 1);
      //获取当前登录教师的id
      var id = sessionStorage.getItem("token")
      window.console.log("作者的id"+id)
      let jjj = new FormData()
      jjj.append('Title',this.form.Title)
      jjj.append('Content',this.form.Content)
      jjj.append('Img',this.form.img)
      jjj.append('CreateTime',this.form.Createtime)
      jjj.append('source',this.form.source)
      jjj.append('label',this.form.label)
      jjj.append('AuthorId',id)
     
        //向子组件传递参数 让子组件传递过去
        this.$http({      
          method:"post",
          url: "/api/news/Upload",
          data:jjj,
          
        }).then((res)=>{
          window.console.log(res.data)
          
        })
      },
    //调用子组件之后通过子组件返回给父组件的一个图片的地址
    GetUrl(url){
      //得到了图片的url 并且设置时间的格式
      this.form.img = url
      window.console.log(url)
      //年
      let year = new Date().getFullYear();
      //月份是从0月开始获取的，所以要+1;
      let month = new Date().getMonth() +1;
      //日
      let day = new Date().getDate();

      let time = year + '-' + month + '-' + day
      // window.console.log(time+"ffff")
      this.form.Createtime = time
      
      // window.console.log(this.form.img+"ffff")
        
    }
    
    
    
  }


};
</script>

 <style lang="scss" scoped>
 .SelectType{
  float:left;
  padding: 40px;
  font-family: 'Courier New', Courier, monospace;
  font-size: larger;
  font-weight:bolder;
 }
.case {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
  

  &-section {
    width: 100%;
    &-content {
      width: 1240px;
      min-height: 1000px;
      margin: 0 auto;
      background-color: #fff;

      &-list {
        width: 100%;
        height: 500px;
        display: flex;
        justify-content: center;
        align-items: center;
        border: 1px solid pink;

        img {
          width: 612px;
          height: 314px;
        }
        .content-list-abstract {
          width: 290px;
          height: 350px;
          padding: 0 20px;
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          .abstract-title {
            line-height: 30px;
            font-size: 22px;
            color: #e13834;
          }
          .abstract-content {
            height: 150px;
            color: #484848;
            font-size: 15px;

            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 7;
            -webkit-box-orient: vertical;
            white-space: normal !important;
            word-wrap: break-word;
            //border: 1px solid pink;
          }
          .more {
            display: flex;
            justify-content: flex-start;
            .text-decoration {
              text-decoration: none;

              span {
                color: #000;
              }
              img {
                width: 12px;
                height: 12px;
              }
            }
          }
        }
        .abstract-active {
          order: -1;
        }
      }
    }
  }
}
</style>