<template>
  <div class="login">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>课程思政示范平台登录入口</span>
      </div>
      <div>
        

        <el-form
          class="demo-ruleForm"
          ref="lform"
          :model="loginform"
          :rules="rules"
          label-width="80px"
        >
          <el-form-item label="用户名" prop="name">
            <el-input name="name" v-model="loginform.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input name="password" type="password" v-model="loginform.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身份" prop="shenfen">
            <el-radio v-model="radio" label="0">管理员</el-radio>
            <el-radio v-model="radio" label="1">教师</el-radio>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="text">
              <router-link to="/register">去注册</router-link>
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
      labelPosition: "right",
      loginform: {
        name: "",
        pass: ""
      },
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
        ]
      }
    };
  },
  mounted() {
    window.addEventListener('keydown',this.keyDown);
  },
  methods: {
    login() {
      this.$refs.lform.validate(valid => {
        if (valid) {
          this.$http
            .get(
              '/api/user/login?LoginName='+this.loginform.name+"&Password="+this.loginform.pass+"&Radio="+this.radio
            )
            .then(response => {
              // window.console.log(response.data);
              var arr = response.data.split(",")
              if (arr[0]==='succuss') {
                this.$message({
                  message: "登录成功了呢",
                  type: "success"
                });
                
                sessionStorage.setItem("token", arr[1]);
                window.console.log(arr[1]);
                // 如果是教师
                if(this.radio == 1){
                  sessionStorage.setItem("status",'teacher');
                  this.$router.push("/")
                }
                // 如果是管理员
                else {
                  sessionStorage.setItem("status",'admin');
                  this.$router.push("/admin")
                }
                
                
              } else {
                this.$message({
                  message: "账号或密码错误",
                  type: "error"
                });
              }
            })
            .catch(e => {
              this.$message({
                message: "账号密码错误",
                type: "error"
              });
            });
        } else {
          this.$message({
            message: "请输入合法的值",
            type: "error"
          });
          return false;
        }
      });
    },
    keyDown(e){
        //如果是回车则执行登录方法
      if(e.keyCode == 13){
        this.login();
      }
    }
  }
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