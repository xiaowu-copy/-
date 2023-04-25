<template>
  <div class="download">
    <banner img="../assets/img/appDown.jpg" title="个人信息" />
   
    <el-row :gutter="15" style="margin-top:10px;">
        <el-col :span="14"  :offset="5">
            <div class="grid-content bg-purple">
                 <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>个人中心</span>
        </div>
          <div class="name-role">
          
        </div>
        <!-- 头像标签 -->
        <div class="block" style="margin-left: 295px"> <el-avatar :size="200" :src="avatar"></el-avatar></div>
       
        
        <el-divider></el-divider>
        <div class="personal-relation">
      <div class="relation-item">用户昵称:  <div style="float: right; padding-right:20px;">
        {{dataForm.loginName}}</div></div>      
    </div>
        <div class="personal-relation">
        <div class="relation-item">手机号:  <div style="float: right; padding-right:20px;">
          {{dataForm.phone}}</div></div>
    </div>
    <div class="personal-relation">
      <div class="relation-item">所属企业:  <div style="float: right; padding-right:20px;">
        {{dataForm.orgnazation}}</div></div> 
    </div>
    <!-- 所教课程 -->
    <div class="personal-relation">
      <div class="relation-item">所教课程:  <div style="float: right; padding-right:20px;">
        {{dataForm.teach_Class}}</div></div> 
    </div>

    <div class="personal-relation">
      <div class="relation-item">用户活跃度:  <div style="float: right; padding-right:20px;">
        {{dataForm. active}}</div></div> 
    </div>

    <div class="personal-relation">
      <div class="relation-item">注册时间:  <div style="float: right; padding-right:20px;">
        {{dataForm.creatTime}}</div></div> 
    </div>
    <!-- 退出登录的部分 -->
    <div class="" style="margin-left:400px" @click="Logout()" >
        <div style="float:left">
          <img style="width:30px;height:30px" src="../assets/img/退出.png" alt="">
        </div>
      
        <div style="text-align:center;float:left;line-height: 31px;color: inherit;font-size:26px;font-style:Tahoma;margin-left:7px">
          退出登录
        </div>
      
    </div>
      
      </el-card>
        </div>
        </el-col>
    <!-- <el-col :span="16">
        <div class="grid-content bg-purple">
       <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>基本资料</span>
        </div>
        <div> -->
          <!-- 头像组件 -->
          

          <!-- <el-form label-width="80px" v-model="dataFrom" size="small" label-position="right">
          <el-form-item label="用户昵称" prop="nickName">
          <el-input  auto-complete="off" v-model="dataForm.nickName"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input auto-complete="off" v-model="dataForm.phone"></el-input>
        </el-form-item>
          <el-form-item label="首页链接" prop="homeUrl">
          <el-input  maxlength="18" v-model="dataForm.homeUrl"></el-input>
        </el-form-item>
            </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button size="mini" type="primary">提交</el-button>
            <el-button size="mini" type="warning" >关闭</el-button>
          </div> -->
        <!-- </div> -->
      <!-- </el-card>
        </div>
        </el-col> -->
      </el-row>

      <!-- 下面是个人上传的资源的展示页面 -->
      <div class="title"> 用户资源上传情况</div>
      
      <div class="title" style="margin-top:100px"> 新闻</div>
      <!-- <el-button @click="clearFilter">清除所有过滤器</el-button> -->
      <div style="margin-left:200px;margin-top:100px">
        <el-table
          ref="filterTable"
          :data="newsForm"
          style="width: 90%">
          <el-table-column
            prop="createTime"
            label="日期"
            sortable
            width="120"
            column-key="date"
            :filter-method="filterHandler"
          >
          </el-table-column>
          <el-table-column
            prop="title"
            label="题目"
            width="180">
          </el-table-column>
          <el-table-column
            prop="content"
            label="内容"
            width="180"
            :formatter="formatter">
          </el-table-column>
          <el-table-column prop="img" label="图片" width="280" >
              <template slot-scope="scope"  >
                <el-image :preview-src-list="srcList" @click="xianshi(scope.row.img)"    style="width:100%"  :src="scope.row.img" alt />
              </template>
            </el-table-column>

            <el-table-column prop="label" label="新闻类别">
              <template slot-scope="scope">
                <div v-for="(item,index) in scope.row.label " :key = index>
                  {{ label[item] }}

                </div>
              </template>
            </el-table-column>

          <el-table-column
            prop="isAdmited"
            label="状态"
            width="100"
            :filters="[{ text: '审核通过', value: 0 }, { text: '待审核', value: 1 }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.isAdmited === 1 ? 'primary' : 'success'"
                disable-transitions>{{ (scope.row.isAdmited === 0) ? '审核通过' : '待审核' }}</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </div>  


      <div class="title" style="margin-top:100px"> 课程</div>
      <!-- <el-button @click="clearFilter">清除所有过滤器</el-button> -->
      <div style="margin-left:200px;margin-top:100px">
        <el-table
          ref="filterTable"
          :data="videoForm"
          style="width: 90%">
          <el-table-column
            prop="creatTime"
            label="日期"
            sortable
            width="120"
            column-key="date"
            :filter-method="filterHandler"
          >
          </el-table-column>
          <el-table-column
            prop="viedioTitile"
            label="题目"
            width="180">
          </el-table-column>
          <el-table-column
            prop="content"
            label="内容"
            width="380"
            :formatter="formatter">
              <template slot-scope="scope">
                  <img style="width:100%" :src="imgserver+scope.row.Img" alt />
                  <video style="width:80%;height:80%;padding-left:10px" :src="scope.row.address"  controls></video>
              </template>
          </el-table-column>
          <el-table-column
            prop="intro"
            label="介绍"
            width="180"
            :formatter="formatter">
            </el-table-column>

            <el-table-column prop="label" label="新闻类别">
              <template slot-scope="scope">
                <div v-for="(item,index) in scope.row.label " :key = index>
                  {{ label[item] }}

                </div>
              </template>
            </el-table-column>

          <el-table-column
            prop="isAdmited"
            label="状态"
            width="100"
            :filters="[{ text: '审核通过', value: 0 }, { text: '待审核', value: 1 }]"
            :filter-method="filterTag1"
            filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.isAdmit === 0 ? 'primary' : 'success'"
                disable-transitions>{{ (scope.row.isAdmit === 0) ? '审核通过' : '待审核' }}</el-tag>
            </template>
          </el-table-column>
        </el-table>
      </div>  
  </div>
</template>

<script>
import JSON from '../assets/label.json'
import Banner from "../components/Banner";
export default {

  data() {
    return {
      dataForm:{
        loginName: '超级管理员',
        phone: '173567777777',
        homeUrl: 'http://www.baidu.com',
        orgnazation: '山东',
        creatTime: '2019.1.10',
        teach_Class: ''},
        newsForm:[],
        videoForm:[],
        srcList:[],
        label:JSON

    }
  },
  components: {
    Banner,

  },
 
  methods: {
      resetDateFilter() {
        this.$refs.filterTable.clearFilter('date');
        
      },
      clearFilter() {
        this.$refs.filterTable.clearFilter();
      },
      formatter(row, column) {
        return row.content;
      },
      filterTag(value, row) {
        window.console.log(value+"11"+row.isAdmited)
        return row.isAdmited === value;
      },
      filterTag1(value, row) {
        window.console.log(value+"11"+row.isAdmited)
        return row.isAdmit === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      xianshi(src){
        this.srcList.push(src)
      },
    
    Logout(){
      sessionStorage.clear();
      this.$message({
                  message: "退出登录成功",
                  type: "success"
      })
      this.$router.push("/login")
    }
    
  },
  created() {
      var id = sessionStorage.getItem("token")
      this.$http.get('/api/user/details?id='+id)
      .then(response =>{
          this.dataForm.nickName = response.data.loginName
          this.avatar = response.data.avatar
          this.dataForm.orgnazation = response.data.orgnazation
          this.dataForm.phone = response.data.phone
          this.dataForm.loginName = response.data.loginName
          this.dataForm.creatTime = response.data.creatTime
          this.dataForm.teach_Class = response.data.teach_Class
          this.dataForm.active = response.data.active

    })
    //获取新闻数据
    this.$http.get('/api/news/gVideo_user?id='+id).
      then(response=>{        
        this.newsForm = response.data
        for (var i = 0; i < this.newsForm.length; i++) {
            let res = this.newsForm[i].label.split(",");
            this.newsForm[i].label = res;
        }
       
      })

    //获取课程数据
    this.$http.get('/api/class/GetVideo_user?id='+id).
      then(response=>{        
        window.console.log(response)
        this.videoForm = response.data
        for (var i = 0; i < this.videoForm.length; i++) {
            let res = this.newsForm[i].label.split(",");
            this.videoForm[i].label = res;
        }
        window.console.log( this.videoForm)
      })
     
    
    
  },
};
</script>

<style lang="scss" scoped>
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
    clear: both
  }
  .box-card {
    width: 100%;
  }
//文本样式区
  .name-role {
    font-size: 16px;
    padding: 5px;
   text-align:center;
  }
   .sender{
      text-align:center;
    }
.registe-info{
  text-align: center;
  padding-top:10px;
}
.personal-relation {
  font-size: 16px;
  padding: 0px 5px 15px;
  margin-right: 1px;
    width: 100%
}
.relation-item {
  padding: 12px;
}
.dialog-footer{
  padding-top:10px ;
  padding-left: 10%;
}
//布局样式区
   .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
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
.title{
  color: #333333;
  font-size: 28px;
  font-weight: bold;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 30px;
}
</style>