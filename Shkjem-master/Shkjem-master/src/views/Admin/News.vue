<template>
  <div class="news">
    <el-button type="primary" @click="openDialog()">新增</el-button>

    <el-table :data="tableData" border style="width: 100%" v-loading="loading">
      <el-table-column prop="id" label="序号" width="180"></el-table-column> 
      <el-table-column prop="title" label="新闻标题" width="180"></el-table-column>
      <el-table-column prop="img" label="图片">
        <template slot-scope="scope"  >
          <el-image :preview-src-list="srcList" @click="xianshi(scope.row.img)"    style="width:100%"  :src="scope.row.img" alt />
        </template>
      </el-table-column>
      <el-table-column   label="新闻内容">
        <template slot-scope="scope">
          <p ref="infoItem" class="ellipsis" :class="{'more':showMore}">{{ scope.row.content }}
            <i class="switch-arrow" :class="showMore?'arrow-up':'arrow-down'" 
              v-if="hasMoreArrow" 
              @click="showMore=!showMore"/>
          </p>
        </template>
      </el-table-column>
      <el-table-column prop="label" label="新闻类别">
        <template slot-scope="scope">
          <div v-for="(item,index) in scope.row.label " :key = index>
            {{ label[item] }}

          </div>
        </template>
      </el-table-column>
      <!-- 发布人 -->
      <el-table-column prop="author_Name" label="发布人" width="100">
        <template slot-scope="scope"  >
          <p>{{ scope.row.author_Name }}</p>
        
        </template>
      </el-table-column>
      <!-- 发布时间 -->
      <el-table-column prop="createTime" label="发布时间" width="150">
        <template slot-scope="scope"  >
          <p>{{ scope.row.createTime }}</p>
        
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
          >通过</el-button>
          <el-button

            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >失败</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--  -->
    <el-dialog title="新闻编辑" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="新闻名称" :label-width="formLabelWidth">
          <el-input v-model="formData.Title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新闻图片" :label-width="formLabelWidth">
          <el-upload
            class="avatar-uploader"
           
            :headers="headers"
            :show-file-list="false"
            :on-success="handleSuccess"
          >
            <img v-if="formData.Img" :src="formData.img" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="新闻内容" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="10" v-model="formData.content" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新闻类别" :label-width="formLabelWidth">
          <el-radio v-model="formData.label" :label="1">公司新闻</el-radio>
          <el-radio v-model="formData.label" :label="2">行业动态</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleCreateOrModify()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import JSON from '../../assets/label.json'
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'

export default {
  name: "loginNews",
  data() {
    return {
      label: JSON,
      hasMoreArrow:true,
      showMore:false,
      srcList:[],
      options: {},
      headers: {},
      tableData: [],
      formData: {
        Id: 0,
        title: "",
        img: "",
        label: "",
        content: "",
        createTime: "",
        author_Name:"",

      },
      dialogFormVisible: false,
      formLabelWidth: "120px",
      loading: true
    };
  },
  mounted() {
   
    //获取表格中的数据
    let token = "Browser " + sessionStorage.getItem("token");
    window.console.log(token);
    this.options = {
      headers: {
        Authorization: token
      }
    };

    this.loadData();

  },
  created() {
    let cellChild=this.$refs.infoItem
    this.hasMoreArrow=cellChild.scrollHeight > cellChild.offsetHeight
  },
  methods: {
    xianshi(src){
      this.srcList.push(src)
    },
    handleSuccess(response, file, fileList) {
      window.console.log(response, file, fileList);
      this.formData.Img = response;
      window.console.log()
     
    },
    loadData() {
      this.loading = true;
      this.$http.get("/api/news/Cheack").then(
      (res)=>{
      this.tableData = res.data
      window.console.log(res.data)
      this.loading = false;
      //将标签转化为数组 方便后面的遍历
      for (var i = 0; i < this.tableData.length; i++) {
        let res = this.tableData[i].label.split(",");
        this.tableData[i].label = res;
        


    }
    })
    },
    //图片放大方法
    

    openDialog() {
      // 清除数据
      this.formData.Id = 0;
      this.formData.Title = "";
      this.formData.Img = "";
      this.formData.Type = 1;
      this.formData.Content = "";
      this.formData.CreateTime = new Date();

      this.dialogFormVisible = true;
    },

    // handleCreateOrModify() {
    //   if (!this.formData.Id) {
    //     this.loading = true;
    //     this.$http
    //       .post("News/CreateNews", this.formData, this.options)
    //       .then(response => {
    //         window.console.log(response);
    //         this.loading = false;
    //         this.$message({
    //           message: "创建成功！",
    //           type: "success"
    //         });
    //         this.dialogFormVisible = false;
    //         this.loadData();
    //       })
    //       .catch(e => {
    //         this.$message({
    //           message: "网络或程序异常！" + e,
    //           type: "error"
    //         });
    //       });
    //   } else {
    //     this.loading = true;
    //     this.$http
    //       .post("News/ModifiedNews", this.formData, this.options)
    //       .then(response => {
    //         this.loading = false;
    //         window.console.log(response);
    //         this.$message({
    //           message: "修改成功！",
    //           type: "success"
    //         });
    //         this.dialogFormVisible = false;
    //         this.loadData();
    //       })
    //       .catch(e => {
    //         this.$message({
    //           message: "网络或程序异常！" + e,
    //           type: "error"
    //         });
    //       });
    //   }
    // },
    //编辑
    handleEdit(row) {
      this.$message({
          message: '审核成功',
          type: 'success'
        });
      
      this.$http.get("/api/news/Approved?id="+row.id)
      this.loadData();
    },
    handleDelete(row) {
      this.$confirm("此操作将永久删除该资源, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 已确认删除
          // 调接口删除
          this.loading = true;
          this.$http
            .get("/api/news/Approved?id="+row.id)
            .then(response => {
              this.loading = false;
              window.console.log(response);
              this.$message({
                message: "删除成功！",
                type: "success"
              });
              this.loadData();
            })
            .catch(e => {
              this.$message({
                message: "网络或程序异常！" + e,
                type: "error"
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
     }
  }
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 20px;
}
.ellipsis{
   position: relative;
   padding-right:3px;
   overflow:hidden; 
   word-break: break-all;
   text-overflow:ellipsis;
   display:-webkit-box; 
   -webkit-box-orient:vertical;
   -webkit-line-clamp:2; 
      &.more{
        display:block;
        text-overflow:initial;
    }
    .switch-arrow{
        position:absolute;
        right:-2px;
        bottom:-2px;
        color:#000;
        border: 6px solid transparent;
        border-left-width: 24px;
        border-right-width: 0;
        border-color:transparent;
        &:before{
          background-color:#fff;
        }
     }
}
</style>