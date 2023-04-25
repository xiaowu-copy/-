<template>
  
<div class="news">
    <banner title="新闻资讯" />
    <div class="news" style="margin-left:150px">
    <div class="news-item" v-for="(item, index) in newsList" :key="index" @click="toInfor1(index)">
      <div class="news-title">{{ item.title }}</div>
      <div class="news-date">{{ item.createTime}}</div>
    </div>
  </div>

    <el-pagination style="margin-left:600px;margin-bottom:60px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.params.page"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="this.params.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total"
      >
      </el-pagination>
</div>
    
</template>

<script>
import Banner from "../components/Banner";
export default {
  name: "news",
  components: {
    Banner
  },
  data() {
    return {
      total:'',
      params:{
        page: 1,
        size: 6,
      },
      newsList: [],
      activeIndex: -1
    };
  },
  methods: {
    //增加浏览次数
    toInfor1(index){
      const index1 = parseInt(index) + 1 
      window.console.log(index+"55")
      this.$http.get("/api/news/View?id="+index1)
      this.toInfor(index)
      
    },
    // 跳转到详情的方法
    toInfor(index){
      window.console.log(`每页 ${index} 条`);
      const index1 = parseInt(index) + 1 
      this.$router.push("/newsdetails/"+index1)
    
        
    },
    highlight(index) {
      this.activeIndex = index
    },
    unhighlight(index) {
      if (this.activeIndex === index) {
        this.activeIndex = -1
      }
    },
    showDetail(index) {
      this.activeIndex = index
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
      .get("/api/news/" + this.params.page + "/" + this.params.size)
      .then((res) => {
        window.console.log(res.data);
        this.newsList = res.data.list
        this.datas = res.data.list;
        this.total = res.data.total;
      });
    }
  },

  mounted() { 
    this.pagehelper()
  },
  watch: {
    
  }
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}

.news {
  width: 100%;
  height: 100%;
  
  position: relative;
  overflow: hidden;

  
  }
  .text-decoration {
    text-decoration: none;
  }
  .news {
  display: flex;
  flex-wrap: wrap;
}

.news-item {
  width: 50%;
  padding: 20px;
  box-sizing: border-box;
}

.news-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.news-date {
  font-size: 14px;
  color: #999;
}

</style>