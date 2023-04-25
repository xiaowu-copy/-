<template>
<!-- 这里是新闻的详情界面 -->
  <div class="NewsDetails">
    <banner img="../assets/img/bgtop.jpg" />
    <div>
    <div style="text-align:center;margin:50px;font-size: 30px;font-weight: bold; line-height: 2em;color: #027DCB; font-family:楷体;">
      <h1>{{ newsIdList.title }}</h1>
    </div>
    <!-- 这里是分割线 -->
    <div class="tableTitle">
        <span class="midText"></span>
    
    </div>

    <div class="font" style="text-align:center">
      <p v-if="this.model === 'swiper'"  style="display:inline;margin-right:20px">发布人: {{ newsIdList.author }} </p> 
      <p v-if="this.model !== 'swiper'"  style="display:inline;margin-right:20px">发布人: {{ newsIdList.author_Name }} </p>
      <p  v-if="this.model === 'swiper'" style="display:inline;margin-right:20px">发布时间: {{ newsIdList.time }}</p>   
      <p v-if="this.model !== 'swiper'" style="display:inline;margin-right:20px">发布时间: {{ newsIdList.createTime }}</p>  
      <p style="display:inline;">浏览次数： {{ newsIdList.numberofViews }}</p> 
    </div> 
    
    <div class="tableTitle">
        <span class="midText"></span>
    </div>
    <div class="uimg1">
      <img :src="newsIdList.img" alt="news image" />
    </div>
    <div class="content">
      <p>{{ newsIdList.content }}</p>
    </div>

  
    <div class="tableTitle1">
        <span class="midText"  ></span>
    </div>

    <div class="footer1">
        <p>高校思政教育平台   版权所有</p>    
    </div>
  
   
  </div>

  </div>

</template>

<script>
import Banner from "../components/Banner";
export default {
  name: "NewsDetails",
  components: {
    Banner
  },
  data() {
    return {
      id:1,
      pid: 0,
      model: '',
      newsIdList: [],    
      imageSrc: '' ,
      views:4,

    };
  },
  computed: {
  },
 
  created() {
    //获取地址中的参数 	
    this.id = this.$route.params.id
    window.console.log(this.id)
    this.model = this.$route.params.model
    window.console.log(this.model)
  },
  mounted() {
    this.loadData();
    //测试加载图片的方法

    
    
  },
  methods: {
     loadData() {
      if(this.model === 'swiper'){
        this.$http.get("/api/Cases/getOne/"+this.id).
        then((res)=>{
          window.console.log(res);
          this.newsIdList = res.data
        })
      }
      else{
       this.$http.get("/api/news/find/"+this.id)
      .then((res) => {
        window.console.log(res);
        this.newsIdList = res.data
        this.src = this.newsIdList.img
      })
        .catch(function(error) {
          window.console.log(error);
        });
      }
    },
    
    
  }
};
</script>

<style lang="scss" scoped>
.news-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}
//第一张显示的图片
.uimg1 img{
  text-align: center;
  margin-left: 260px;
  margin-top: 50px;
  max-width: 940px!important;
  height: auto!important;

}
.content p{
    margin: 0;
    padding: 0;
    font-family: "SimSun", Arial;
    vertical-align: baseline;
    

}
.content{
  margin-top: 50px;
  margin-left: 250px;
  margin-right: 260px;
  max-width: 1200px;
}
.news-title {
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 20px;
}

.news-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  color: #888;
  margin-bottom: 20px;
}

.news-image {
  width: 100%;
  max-width: 800px;
  margin-bottom: 20px;
}

.news-source {
  font-size: 14px;
  color: #888;
}

.tableTitle {
    position: relative;
    margin-left: 250px;
    width: 1000px;
    height: 2px;
    background-color: #d4d4d4;
    text-align: center;
    font-size: 16px;
    color: rgba(101, 101, 101, 1);
  }
.tableTitle1 {
    position: relative;
    margin-left: 250px;
    margin-top: 40px;
    width: 1000px;
    height: 2px;
    background-color: #8A771F;
    text-align: center;
    font-size: 16px;
    color: rgba(101, 101, 101, 1);
  }
 .midText {
    position: absolute;
    left: 50%;
    background-color: #ffffff;
    padding: 0 15px;
    transform: translateX(-50%) translateY(-50%);
  }
  // 标题下面的时间作者的字体样式
  .font p{
    font-size: 20px;
  }
.footer1{
  text-align: center;
  margin: 20px;
}

</style>