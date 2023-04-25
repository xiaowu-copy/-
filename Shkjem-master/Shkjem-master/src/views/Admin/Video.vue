<template>
  <div class="Team">
    <el-button type="primary" @click="openDialog()">新增数据</el-button>

    <el-table border :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column prop="id" label="序号" width="100"></el-table-column>
      <el-table-column prop="viedioTitile" label="资源名称"></el-table-column>
      <el-table-column  label="思政资源" width="400">
        <template slot-scope="scope">
          <img style="width:100%" :src="imgserver+scope.row.Img" alt />
          <video style="width:80%;height:80%;padding-left:10px" :src="scope.row.address"  controls></video>
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="资源简介"></el-table-column>
      <el-table-column prop="intro" label="资源类型" width="100">
        <template slot-scope="scope">
          <div v-for="(item,index) in scope.row.label " :key = index>
            {{ label[item] }}
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="loginName" label="发布人" width="100"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
          ></el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="团队风采编辑" :visible.sync="dialogFormVisible">
      <el-form :model="formData">
        <el-form-item label="风采图片" :label-width="formLabelWidth">
          <el-upload
            class="avatar-uploader"
            action="http://shkjgw.shkjem.com/api/UpLoad/UploadImage"
            :headers="headers"
            :show-file-list="false"
            :on-success="handleSuccess"
          >
            <img v-if="formData.Img" :src="imgserver+formData.Img" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input v-model="formData.Remark" autocomplete="off"></el-input>
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
export default {
  data() {
    return {
      label: JSON,
      loading: true,
      dialogFormVisible: false,
      formLabelWidth: "120px",
      tableData: [],
      formData: {
        Id: 0,
        Img: "",
        Remark: "",
        CreateTime: new Date()
      },
      options: {},
      headers: {}
    };
  },
  mounted() {
    let token = "Browser " + sessionStorage.getItem("token");
    //window.console.log(token);
    this.options = {
      headers: {
        Authorization: token
      }
    };
    this.headers = {
      Authorization: token
    };

    this.loadData();
  },
  methods: {
    handleSuccess(response, file, fileList) {
      window.console.log(response, file, fileList);
      this.formData.Img = response;
    },
    loadData() {
      this.loading = true;
      this.$http.get("/api/class/Cheack").then(
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
    openDialog() {
      // 清除数据
      this.formData.Id = 0;
      this.formData.Img = "";
      this.formData.Remark = "";
      this.formData.CreateTime = new Date();

      this.dialogFormVisible = true;
    },
    // 新增
    handleCreateOrModify(row) {
      window.console.log(row.id);
      this.$confirm("此操作将确认条数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 已确认删除
          // 调接口删除
          this.loading = true;
          this.$http
            .get("class/DeleteTeam?id="+row.id)
            .then(response => {
              this.loading = false;
              window.console.log(response);
              this.$message({
                message: "审核通过！",
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
    },
    handleEdit(index, row) {
      window.console.log(index, row);
      this.formData = row;
      this.dialogFormVisible = true;
    },
    handleDelete(index, row) {
      window.console.log(index, row);
      this.$confirm("此操作将永久此条数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // 已确认删除
          // 调接口删除
          this.loading = true;
          this.$http
            .post(`Team/DeleteTeam?id=${row.Id}`, null, this.options)
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
    },
    //时间格式化
    dateFormat: function(row) {
      //row 表示一行数据, CreateTime 表示要格式化的字段名称
      let t = new Date(row.CreateTime);
      return t.getFullYear() + "-" + (t.getMonth() + 1) + "-" + t.getDate();
    }
  }
};
</script>

<style scoped>
.el-table {
  margin-top: 20px;
}
</style>