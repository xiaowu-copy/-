<template>
    <div class="video-upload-com">
    
        <el-form  ref="form" :model="form" label-width="80px" >
        <el-form-item label="图片名称">
          <el-col :span="10">
            <el-input  v-model="form.Title"></el-input>
          </el-col>          
        </el-form-item>

        <el-form-item label="图片" >
          <el-col :span="15">
            <!-- //父组件绑定值向子组件传值 同样子组件向父组件传值要在子组件中定义这个相应的方法 -->
            <upload :picture="is" @getImgurl="GetUrl"></upload>
          </el-col>          
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
    name: 'PictureUpload',
    data() {
        return {
        is:"picture",
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
          var id = sessionStorage.getItem("token")
          window.console.log("作者的id"+id)
          let jjj = new FormData()
          jjj.append('name',this.form.Title)
          jjj.append('time',this.form.Createtime)
          jjj.append('address',this.form.img)
          jjj.append('author_id',id)
     
        //向子组件传递参数 让子组件传递过去
        this.$http({      
          method:"post",
          url: "/api/picture/insert",
          data:jjj,
          
        }).then((res)=>{
          window.console.log(res)

          if(res.status==200)//成功操作 回到页面上展示页面上的页面名称 或者显示
          {
            this.$alert('上传成功，请等待审核通过')
            setTimeout(() => {
                location.reload();// 这里编写需要延迟执行的代码
              }, 2000);
            
          }
          
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
 