<template>
    <div class="video-upload-com">
    
        <el-form  ref="form" :model="form" label-width="80px" >
        <el-form-item label="视频名称">
          <el-col :span="10">
            <el-input  v-model="form.Title"></el-input>
          </el-col>          
        </el-form-item>

        <el-form-item label="视频简介" style="margin-top:70px" >
          <el-col :span="15">
            <el-input type="textarea" style="height:102px;"  v-model="form.Content"></el-input>
          </el-col>          
        </el-form-item>

        <el-form-item label="视频内容" >
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
        <el-form-item label="视频类型" style="margin-top:70px">
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
    
    </div>
</template>
  
<script>
import { uploadObject } from '../../api/upload'
import upload from "../../api/upload.vue";
export default {
    // 上传视频到腾讯云对象存储
    name: 'VideoUpload',
    data() {
        return {
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
    props: {
       
    },
    components: {
        upload
    },
    created() {
        
    },
    watch: {
       
    },
    mounted() {},
    methods: {
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
}
</script>
  
<style lang="scss" scoped>
.video-upload-com {
    .video .el-upload-list {
        width: 326px;
        padding-left: 157px;
    }
    .el-upload-list__item {
        margin: 0 8px 0 0;
    }
    .el-upload-list__item-status-label {
        right: -24px;
        line-height: 0px; // text-align: left;
        i {
            position: relative;
            top: 0px;
            left: -6px;
        }
    }
}
</style>
 