<template>
  <div class="picture">
    <banner img="../assets/img/bgtop.jpg" title="精美图片" />
    <div class="gallery">
        <div class="Search">
      <el-input v-model="input" style="width:400px" placeholder="请输入内容"></el-input>
      <el-button style="width:77px" >搜索</el-button>
    </div>
    <div class="thumbnails">
      <div v-for="image in images" :key="image.id" class="thumbnail" >
        <img :src="image.address" :alt="image.alt" @click="showImage(image)">
        
        <el-avatar style="float:left" :size="50" :src="image.avatar"></el-avatar>
        <p>{{ image.loginName}}</p>
      </div>
    </div>
    <div v-if="selectedImage" class="lightbox" @click="closeLightbox">
      <img :src="selectedImage.address" :alt="selectedImage.alt">
      <p class="intro">{{selectedImage.name}}</p>
    </div>

    <el-pagination style="margin-left:140px;margin-top:100px;"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="this.params.page"
        :page-sizes="[3, 4, 5, 6]"
        :page-size="this.params.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total"
      >
      </el-pagination>
  </div>    
  </div>
</template>

<script>
import Banner from "../components/Banner";

  export default {
    name:'Picture',
    props:[''],
    data() {
    return {
      images: [
        
      ],
      selectedImage: null,
      total:'',
      params:{
        page: 1,
        size: 6,
      },
      activeIndex: -1
    };
  },

    components: {
        Banner
    },

    computed: {},

    beforeMount() {},

    mounted() {
       
        this.$http.get("/api/picture/seletAll").then(response => {
          //console.log(response);
          this.images = response.data;
          window.console.log(this.images);
        })
        this.pagehelper()
    },

    methods: {
        showImage(image) {
            this.selectedImage = image;
    },
        closeLightbox() {
                this.selectedImage = null;
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
      .get("/api/picture/" + this.params.page + "/" + this.params.size)
      .then((res) => {
        window.console.log(res.data);
        this.images = res.data.list
        this.datas = res.data.list;
        this.total = res.data.total;
      });
    }
    },

    watch: {}

  }

</script>
<style lang='scss' scoped>
.picture {
  width: 100%;
  height: 100%;
  background-color: #14679f;
}
.gallery {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px;
}
h2 {
  text-align: center;
}
.thumbnails {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin: 20px;
}
.thumbnail {
  position: relative;
  width: 320px;
  height: 300px;
  
  margin-left: 35px;
  margin-top: 60px;
  cursor: pointer;
}
.thumbnail img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform .2s;
  border-radius: 10%;
}
.thumbnail img:hover {
  transform: scale(1.1);
}
.thumbnail p{
    line-height: 50px;
    color: aliceblue;
    font-size: 24px;
    font-weight: 300;
}
.lightbox {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.8);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
}
.lightbox img {
  max-width: 90%;
  max-height: 90%;
//   object-fit: contain;
float: left;
}
.intro{
    font-size: 50px;
    color: brown;
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
</style>