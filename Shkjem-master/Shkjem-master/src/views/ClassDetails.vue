<template>
  <div class="case">
    <banner img="../assets/img/bgtop.jpg" />
    <div class="summer_right">
      
    </div>
    <el-row style="float:left">
           
            <!-- 左边的一栏-->
            <div class="left">
                <!-- 标题 -->
                <div class="title-video">{{ videoform.viedioTitile }}</div>
                <!-- 这是视频 -->
                <video width="1025px" height="600px" controls :src="videoform.address">
                </video>
                <!-- <div class="title">{{ videoform.viedioTitile }}</div> -->
                <!-- 这里是作者的头像那里 一横排 -->
                <div style="float:left">
                    <div class="block" style="padding-top:5%;float:left"> <el-avatar :size="50" :src="videoform.avatar"></el-avatar></div>
                    <div class="author_name" style="">{{ videoform.loginName }}</div>
                </div>
                <div class="View" style="float:left;margin-left:60px"> 
                    <div class="View_time" style="">浏览次数：</div>
                    <div class="View_time" style="">{{ videoform.numberofViews }} 次</div>
                </div>
                <div style="margin-left:370px">
                    <div class="Reference_time" style="">引用次数：</div>
                    <div class="Reference_time" style="">{{ videoform.numberofReferences }} 次</div>
                    <div class="Reference">
                        <el-button type="primary" round>引用资源</el-button>

                    </div>
                </div>
                <!-- //这里是视频标签的那块 -->
                <div v-for="(item,index) in res" class="label">
                  <div class="label1">
                    <el-tag type="success">{{label[item]}}</el-tag>
                  </div>
              </div>
                <!-- //引用按钮 -->
            </div>
            <div class="right">
              <div class="title_right">
                <p style="text-align:center;padding-top:22px">视频相关内容介绍</p>

              </div>
              <div class="information">
                <p>{{ videoform.intro }}</p>
              </div>
            </div>

            


                
           
            
    </el-row>
    <div class="summer_right1">

    </div>
   
  </div>
</template>

<script>
import Banner from "../components/Banner";
import Json from "../assets/label.json"
export default {
  components: {
    Banner
  },
  data() {
    return {
      label: Json,
      videoform: '',
      id_label: [],
      res:''
      
    };
  },
  created() {
    //获取到相应的参数喽 就是跳到这个页面来携带的视频的id
    this.pid = this.$route.params.id;
  },
  mounted() {
    this.loadData();
  },
  methods: {
    
    loadData() {
      var type = localStorage.getItem("video-type");
      window.console.log(type)
      if(type == 1){
        this.$http
        .get(`api/class/getOne?id=`+this.pid)
        .then(response => {
          // 增加浏览次数的方法接口
          this.$http.get(`api/class/View?id=`+this.pid)
          this.videoform = response.data
          //该图片的对应标签          
          window.console.log(this.videoform)
          let res = response.data.label.split(",");
          this.res = res
        })
        .catch(function(error) {
          window.console.log(error);
        });
      }else if (type == 0){
      this.$http
        .get(`api/Cases/GetSwiperOne?id=`+this.pid)
        .then(response => {   
          this.videoform = response.data
          //该图片的对应标签          
          window.console.log(this.videoform)
          let res = response.data.label.split(",");
          this.res = res
        })
        .catch(function(error) {
          window.console.log(error);
        });
    }
    }}

};
</script>

<style lang="scss" scoped>
.title-video{
  font-size: 30px;
  font-family:"Cambria, Cochin, Georgia, Times, 'Times New Roman', serif" ;
  font-weight: 700;
  line-height: 46px;
  
  margin-bottom: 18px;
  -webkit-font-smoothing: antialiased;
}
//左边的视频相关信息栏目
.information{
  width: 205px;
  padding: 10px;
  line-height: 30px;
  font-size: large;
  font-weight: bold;
  margin-top: 12px;
  border: 2px solid #1d42b9;
  border-radius: 10px;
  height: 500px;
} 
//右边的栏目
.right{
  margin-top: 60px;
  float: left;
}
.title_right{
  margin-top: 15px;
  background-color: rgb(241,242,243);
  width: 220px;
  height: 70px;
  padding-left: -50px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
}
.label{
  display: inline-block;
  margin: 10px;
}
.Reference{
    display: inline-block;
    margin-left: 500px;
    
   
}
//按钮
.button1{
    background-image: url(../assets/img/引用链接.png);
    background-size: cover; 
    background-position: center;
    width: 80px;
    height: 40px;

}
//引用次数
.Reference_time{
    
    display: inline;
    line-height: 4.9rem;

}
//观看次数
.View_time{
    display: inline;
    line-height: 4.9rem;
}
//作者名字
.author_name{
    font-size: 1rem;
    line-height: 4.9rem;
    font-weight: 600;
    padding-left: 70px;
}
//视频下方的视频名称
.title{
    font-family: "YouTube Sans","Roboto",sans-serif;
    font-size: 2rem;
    line-height: 2.8rem;
    font-weight: 600;
    white-space: normal;
}
.left{
   
    margin-top: 15px;
    width: 1040px;
    float: left;
}
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
.summer_right{
  float: left;
  width: 170px;
  height: 900px;
  background-color: rgb(194,218,235);
}

.summer_right1{
  float: left;
  width: 175px;
  height: 900px;
  background-color: rgb(194,218,235);
}

</style>