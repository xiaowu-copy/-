<template>
   <div class="login">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>课程思政示范平台用户注册</span>
      </div>
      <div>
        

        <el-form
          class="demo-ruleForm"
          ref="lform"
          :model="form"
          :rules="rules"
          label-width="80px"
        >
          <el-form-item label="用户名" prop="name">
            <el-input name="name" v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input name="password" type="password" v-model="form.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input name="phone"  v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="所属组织" prop="orgnazation" >
            <el-input name="orgnazation"  v-model="form.orgnazation"></el-input>
          </el-form-item>
          <el-form-item label="所教课程">
          
                <el-checkbox-group v-model="form.checkList">
                    <el-checkbox v-for="course in courses" :label="course.className" :key="course.classid"></el-checkbox>
                </el-checkbox-group>
           
          </el-form-item>
          <el-form-item label="验证码">
            <img style="float:left" :src="captchaUrl" alt="captcha">
            <el-input style="width:170px;margin-left:20px" v-model="yanzhengm" name="验证码" ></el-input>
          </el-form-item>
          <el-form-item label="上传头像" prop="avtar">
            <upload :register="is" v-model="form.avtar" @getImgurl="GetUrl"> </upload>
          </el-form-item>
          
          <el-form-item>
            <el-button type="primary" @click="submit()">注册</el-button>
               
          </el-form-item>
        </el-form>
      </div>
    </el-card>
 
  </div>
</template>

<script>
import upload from "../../api/upload.vue";
export default {
    components: {
        upload
  },
    
  data() {
    
    return {
      yanzhengm:'',
      
      is:'register',
      captchaUrl:'',     
      labelPosition: "right",
      selectedIds:[],  //选中的选项的编号列表,用于验证码输入和验证码匹配的检查,
      form: {
        checkList:[],
        Createtime:''
     
      },
      courses: [],  //array of course names
      checkboxGroup1: ['上海'],
      radio: '1',//身份选择
      rules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        pass: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 5,
            max: 20,
            message: "密码长度在 5 到 20 个字符",
            trigger: "blur"
          }
        ],
       phone:[{ required: true, message: "请输入手机号码", trigger: "blur" },
              {
                    min: 11,
                    max: 11,
                    message: "手机号应为11个字符",
                    trigger: "blur"
              }],
      orgnazation: [{ required: true, message: "请输入所属组织", trigger: "blur" }],

    
      }
    };
  },
  mounted() {
    //验证码方法
   this.refreshCaptcha()
   this.loaddate()
  },
  watch: {
    'form.checkList': function(newVal, oldVal) {
      this.selectedIds = newVal.map(label => {
        let course = this.courses.find(course => course.className === label)
        return course.classid
      })
      window.console.log(this.selectedIds)
    }
  },
  methods: {
    submit(){
      window.console.log(this.form)
      // 先进行验证码的校验
      var hh = this.yanzhengm
      window.console.log(hh)
      this.$http.get('/api/checkCaptcha?code='+hh).then(
        response => {
          window.console.log(response)
          if(response.data ==false){
            this.$alert('验证码错误')
          }else{
            let jjj = new FormData()
            jjj.append('LoginName',this.form.name)
            jjj.append('Password',this.form.pass)
            jjj.append('phone',this.form.phone)
            jjj.append('createTime',this.form.Createtime)
            jjj.append('Avatar',this.form.Avatar)
            jjj.append('orgnazation',this.form.orgnazation)
            jjj.append('checkList',this.selectedIds)
            window.console.log(jjj)
            // 先进行用户名是否重复的操作
            this.$http.get("/api/user/check-username?username="+this.form.name).then(response=>{
              window.console.log(response)
              if(response.data.exists==true)
                this.$alert('用户名重复,请重新输入')
              else{
                this.$http({      
                method:"post",
                url: "/api/user/Register",
                data:jjj,
                
                  }).then((res)=>{
                    window.console.log(res)
                    window.console.log("6666666")
                      
                      if(res.data==true){
                        this.$alert('注册成功')
                        this.$router.push("/login")
                      }
              
            })
              }
            })
           
          }
      } )  //response.data is the data from server, and this is the data to check if the entered code is correct.
    } ,    //submit the form data to the server and resets the form.
    GetUrl(url){
      //得到了图片的url 并且设置时间的格式
      this.form.Avatar = url
      let year = new Date().getFullYear();
      //月份是从0月开始获取的，所以要+1;
      let month = new Date().getMonth() +1;
      //日
      let day = new Date().getDate();

      let time = year + '-' + month + '-' + day
      // window.console.log(time+"ffff")
      this.form.Createtime = time
    },

    refreshCaptcha() {
      // make API call to get captcha image URL 
      fetch('/api/getCaptchaImg')
        .then(response => {
          // convert image data to URL
          return response.blob();
        })
        .then(blob => {
          const imgUrl = URL.createObjectURL(blob);
          // set captchaUrl data variable to display the captcha image
          this.captchaUrl = imgUrl;
          window.console.log(imgUrl);
        })
        .catch(error => {
          window.console.error(error);
        });
    },
    //加载数据，比如课程什么的
    loaddate(){
        this.$http.get("/api/course/selectAll").then(res=>
        {
            this.courses = res.data
        })
    }
  },
    
    
  
};
</script>

<style lang="scss" scoped>
a {
  text-decoration: none;
  color: #409eff;
}

.login {
  //display: flex;
  padding: 200px 0;
  width: 100%;
  height: 100%;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
  margin: 0 auto;
}
.el-form-item__content {
  text-align: start;
}
</style>