<template>
  
  <div class="product">
    <banner img="../assets/img/bgtop.jpg" title="经典课程" />
    
    <div class="product-content">
    </div>
    <div class="Search">
      <el-input v-model="input" style="width:400px" placeholder="请输入内容"></el-input>
      <el-button type="primary" @click="search()">搜索一下</el-button>
    </div>
    
      <!-- 视频播放页面 -->
      
    <div v-if="this.videos.length>0" class="videos-container">
        <div class="video-item" v-for="(video,index ) in videos" :key="video.id">
         
          <video class="v-item" :src="videoAdress[index]" controls>
            
          </video>
          <div class="video-info">
            <h3 class="video-title" @click="toinfo(video.viedioId)">{{ video.viedioTitile }}</h3>
        </div>
        </div>
     </div>
     <div v-if="this.videos.length==0">
        <p class="none">暂时没有数据</p>

     </div>
     
     <el-pagination style="margin-left:620px;margin-top:50px;padding-bottom:50px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.params.page"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="this.params.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total"
      >
      </el-pagination>
     <!-- <video
      ref="veo"     
      :src=src>

      </video> -->
 


      
  </div>
</template>

<script>
import Banner from "../components/Banner";
// import axios from "axios"
export default {
  data() {
    return {
      input:'',
      videos: [],
      videoAdress:[],
      total:'',
      params:{
        page: 1,
        size: 6,
      },
      newsList: [],
      activeIndex: -1
   
      
    };
  },
  components: {
    Banner
  },
  methods: {
    toinfo(id){
      this.$router.push("/ClassDails/"+id)
      localStorage.setItem("video-type","1")
    },
    //搜索方法
    search(){
      var key = this.input;
      this.$http.get('/api/class/Search?key='+key).
        then((res)=>{
           window.console.log(res)
           this.videos=res.data
            for (let i = 0; i < this.videos.length; i++) {
              this.videoAdress[i] = this.videos[i].address
              // window.console.log(this.videoAdress[i])

            }

      })
    },
    highlight(index) {
      this.activeIndex = index
    },
    unhighlight(index) {
      if (this.activeIndex === index) {
        this.activeIndex = -1
      }
    },
    handleSizeChange(val) {
      window.console.log(`每页 ${val} 条`);
      this.params.size = val;
      this.pagehelper();
    },
    handleCurrentChange(val) {
      window.console.log(`当前页: ${val}`);
      this.params.page = val;
      this.pagehelper();
    },
    pagehelper:function(){
           this.$http
      .get("/api/class/" + this.params.page + "/" + this.params.size)
      .then((res) => {
        window.console.log(res.data);
        this.videos = res.data.list
        this.datas = res.data.list;
        this.total = res.data.total;
      });
    }
  },
  created() {

    this.$http.get('/api/class/find').then(response =>{
      // window.console.log(response)
      this.videos=response.data
      for (let i = 0; i < this.videos.length; i++) {
        this.videoAdress[i] = this.videos[i].address
        // window.console.log(this.videoAdress[i])

      }
    }),
    window.console.log(this.videos)
  },
  mounted() {
    this.pagehelper()
  },
  
};
</script>

<style lang="scss" scoped>
.none{
  font-size: 20px;
  color:#fff;
  margin-left: 900px;
  margin-top: 200px;
  margin-bottom: 300px;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  line-height: 36px;


}
.Search{
  margin-left: 700px;
  margin-top: 40px;
}
.videos-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-top: 40px;
  margin-left: 50px;
  margin-right: 50px;
}

.video-item {
  width: calc(33.33% - 20px);
  position: relative;
}
.v-item{
  width: 100%;
}

.video-cover {
  width: 100%;
  height: 0;
  padding-bottom: 56.25%; /* 16:9 */
  object-fit: cover;
}

.video-info {
  // position: absolute; 
  left: 0;
  width: 100%;

  background-color: rgba(0, 0, 0, 0.7);
  color: #fff;
}

.video-title {
  margin: 0;
  font-size: 20px;
}

.video-description {
  margin: 10px 0;
  font-size: 14px;
}

.video-player {
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  object-fit: cover;
}
.product {
  width: 100%;
  height: 100%;
  background-color: #14679f;
}

.product-content {
  width: 1240px;
  margin: 0 auto;
  background-color: #fff;
}

.p-video {
  width: 1000px;
  margin: 0 auto;
  padding: 60px 0;
}
//对分页器的样式修改
::v-deep .el-pagination {
.el-pagination__total {
font-size: 1.0vw ;
color: #fff;
}

}



//公共样式
.title,
.eTitle {
  font-size: 34px;
  font-weight: 400;
  color: #333;
  text-align: center;
}
.eTitle {
  font-size: 30px;
  padding: 10px 0;
}
</style>