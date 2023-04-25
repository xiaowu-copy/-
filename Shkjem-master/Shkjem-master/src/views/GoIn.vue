<template>
  <div class="go-in">
    <banner img="../assets/img/bgtop.jpg" title="资源引用排行" />
    <div class="section" v-loading="loading">
      <div class="section-content">
        <div class="content-summary">
          <div class="summary-left">
            <p class="title">课程资源引用排行</p>
            <p class="eTitle">Course ranking</p>
            <div style="width:582px; height: calc(120vh - 402px);overflow-y: auto;overflow-x: hidden;margin:20px;">
    <div>
          <div class="rank-title" > 排名情况</div>  <div class="rank-title1" > 上传作者 </div>
          <div>
            <div v-show="Cumulative_Rank.length>0" style="width: 382px;height:386px;">
              <div v-for="(item,index) in Cumulative_Rank"
                :key="index"
                style="width:550px;padding-left:50px;margin-top:30px">
                <div @click="Toinfor(item.id) ">
                  <el-row style="font-size:24px;color:#606266;margin-bottom:30px" >{{index + 1 + "  "+item.viedioTitile}}</el-row>
                </div>
                
                <el-row style="height:18px;">
                  <el-col :span="16">
                    <el-progress :percentage="item.numberofReferences" :stroke-width="20" color="#50c097"></el-progress>
                  </el-col>
                  <el-col :span="4">
                    <span style="color:#50c097;font-weight:bold;font-size:34px  margin-left:-5px;">{{item.numberofReferences?item.numberofReferences+'次':''}}</span>
                  </el-col>
                  <el-col :span="3">
                    <span style="color:#50c097;font-weight:bold;font-size:34px  margin-left:-5px;">{{item.loginName}}</span>
                  </el-col>
                </el-row>
              </div>
            </div>
            <div v-show="Cumulative_Rank == null || Cumulative_Rank.length == 0" style="width: 382px;height:260px;line-height:260px;text-align:center;color:#909399;">
              暂无数据
            </div>
          </div>
        </div>
  </div>


          </div>
          <div class="summary-right">
            <img src="../assets/img/jianjietopmin.jpg" alt />
          </div>
        </div>
        <el-divider class="el-divider-active">
          <i class="el-icon-arrow-down el-icon-arrow-down-active"></i>
        </el-divider>
        <!-- 发展历程 -->
        <div class="content-course">
          <div class="top">
            <h3>年度最佳</h3>
            <p>Best of the year</p>
            <div class="border"></div>
            <div class="timeline"></div>
          </div>
          <div class="course-time">
            <swiper :options="swiperOption" ref="mySwiper">
              <swiper-slide v-for="(item,index) in courseList" :key="index">
                <div class="time-show">
                  <div class="time-show-item" v-for="(courseOne,one) in item" :key="one">
                    <div class="item-top" :class="{'order-top' : one%2===1}"></div>
                    <el-divider>
                      <i class="el-icon-mobile-phone"></i>
                    </el-divider>
                    <div class="item-bottom" :class="{'order' : one%2===1}">
                      <div class="item-bottom-content">
                        <p>{{courseOne.Content}}</p>
                        <p>{{courseOne.Year}}</p>
                      </div>
                    </div>
                  </div>
                </div>
              </swiper-slide>
              <div class="swiper-button-prev" slot="button-prev"></div>
              <div class="swiper-button-next" slot="button-next"></div>
            </swiper>
          </div>
        </div>
        
        <div class="content-culture">
          <h3>课程思政</h3>
          <h3>CULTURE</h3>
          <p>不忘初心牢记使命</p>
          <span>持之以恒地推进习近平新时代中国特色社会主义思想的“三进”工作</span>
          <span>坚持不懈用习近平新时代中国特色社会主义思想铸魂育人</span>
          <span>坚定中国特色社会主义的“四个自信”；</span>
          <span>将社会主义核心价值观内化为精神追求</span>
        </div>
        <!-- 公司荣誉 -->
        <div class="content-honor">
          <div class="honor-big-img">
          </div>
          <div class="top">
            <h3>教师排行</h3>
            <p>Teacher rank</p>
            <div class="border"></div>
              <div class="summary-left1">
                  <img src="../assets/img/left.jpg" alt />
              </div>

              <div class="teacher_ranking">
                <div  v-for="(item,index) in tableData" :key="index">
                  <div class="techer" style="margin-left:600px">
                    <div>
                    <span class="xuhao" style="float:left;">{{index+1}}</span>

                    <el-avatar :size="100" :src="item.avatar" style="float:left"></el-avatar>
                    <div style="margin-top:50px;">
                        <div style="padding-top:20px;float:left">
                          <div class="title">
                            {{ item.loginName}}
                          </div>
                          <div class="orgnization">
                            {{ item.orgnazation}}
                          </div>
                        </div>

                        <div style="padding-left:420px;line-height:106px;font-size:30px;color:yellowgreen">
                          {{ item.active}}
                        </div>
                  </div>
                  </div>
            
                  </div>
                </div>
              </div>


          </div>
          
        </div>
        
        <div class="content-team">
          <div class="top">
            <h3>团队风采</h3>
            <p>TEAM</p>
          </div>
          <el-carousel :interval="4000" type="card">
            <el-carousel-item v-for="(team,index) in teamItem" :key="index">
              <div class="swiper-img" v-lazy:background-image="imgserver + team.Img"></div>
            </el-carousel-item>
          </el-carousel>
        </div>
         合作伙伴
        <div class="content-partner">
          <div class="top">
            <h3>合作伙伴</h3>
            <p>RARTNERS</p>
            <ul class="partner-img">
              <li v-for="(partner,i) in partnerImg" :key="i">
                <img v-lazy="imgserver+partner.Img" alt />
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Banner from "../components/Banner";
import { swiper, swiperSlide } from "vue-awesome-swiper";
export default {
  components: {
    Banner,
    swiper,
    swiperSlide
  },
  data() {
    return {
      Cumulative_Rank: [
       
      ],
      tableData: [],

      honorList: [],
      partnerImg: [],
      courseList: [],
      teamItem: [],
      swiperOption: {
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        }
      },
      dialogTableVisible: false,
      dialogUrl: "",
      dialogTitle: ""
    };
  },
  mounted() {
    this.$http
      .all([
        this.$http.get('/api/class/Ranking'),
        this.$http.get('/api/user/GetTeacherAll'),
        
      ])
      .then(
        this.$http.spread(
          (res,res1)=>{
              window.console.log(res1.data)
              this.Cumulative_Rank = res.data
              this.tableData = res1.data
            }
       
      ));
  },
  methods: {
    Toinfor(id){
      
      this.$router.push("/ClassDails/"+id)
    }
  },
};
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
}
.xuhao{
  float: left;
  font-size: 35px;
  line-height:100px;
  padding-right: 25px;
  color: brown;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
.orgnization{
  
  height: 100px;
  padding-left:20px;
  margin-top:30px;
 
  line-height: 50px;
  font-size: 15px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
.title{
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  float: left;
  padding-left:50px;
  font-weight: bolder;
  font-size: 25px;
}
.rank-title{
  float: left;
  font-size: 18px;
  color: #2D8CF0;
  font-weight: bold;
  margin-top: 10px;
  margin-bottom: 10px;
  padding-left:40px;
}
.rank-title1{
  padding-top: 15px;
  margin-left: 470px;
  font-size: 18px;
  color: #2D8CF0;
  font-weight: bold;
 
  margin-bottom: 10px;
  padding-left:40px;
}


::v-deep .el-progress__text{
  display:none;
}
::v-deep .el-progress-bar__outer{
  background-color: rgba(235,238,245,0);
}

.progress-txt{
  .progress-title{
    width: 88px;
    display: inline-block;
    position: relative;
  }
  .progress-percen{
    display: inline-block;
    position: relative;
    left: 0px;
  }
}

.progress-custom{
  position: relative;
  // width: 364px;
  width: 100%;
  height: 20px;
  .progress-content{
    position: absolute;
    width: 100%;
    height: 12px;
    background-color: #ebeef5;
    margin: 1px;
    border-radius: 6px;
  }
  .progress-value{
    position: absolute;
    width: 40%;
    height: 12px;
    background-color: #43bef1;
    margin: 1px;
    border-radius: 6px;
    left: 0px; // 经测试，left 最多到70% 就已经最右边了
  }
}
// 教师排行的实现
.summary-left1{
  float: left;
  width: 400px;
          height: 410px;
          border: 2px solid #1d42b9;
          //animation: imgboxkey 4s infinite;
          border-radius: 10px;
          margin-top: 160px;
          margin-left: 75px;
          
          text-align: center;

          img {
            width: 360px;
            height: 370px;
            margin-top: 20px;
            //animation: imgbo 4s infinite;
          }
}
  


.go-in {
  width: 100%;
  height: 100%;
  background-color: #14679f;
  position: relative;
  overflow: hidden;
  .section {
    width: 100%;
    &-content {
      width: 1240px;
      margin: 0 auto;
      background-color: #fff;

      .content-summary {
        //height: 500px;
        display: flex;
        justify-content: space-around;
        padding: 50px 0;
        .summary-left {
          width: 600px;
          .title {
            font-size: 25px;
            color: #e13834;
          }
          .eTitle {
            font-size: 17px;
            color: #e13834;
            padding: 20px 0;
          }
          .content {
            color: #14679f;
            font-size: 14px;
            text-indent: 25px;
            line-height: 30px;
          }
        }

        .summary-right {
          width: 400px;
          height: 310px;
          border: 2px solid #1d42b9;
          //animation: imgboxkey 4s infinite;
          border-radius: 10px;
          margin-top: 160px;
          
          text-align: center;

          img {
            width: 360px;
            height: 270px;
            margin-top: 20px;
            //animation: imgbo 4s infinite;
          }
        }
      }

      //发展历程
      .content-course {
        padding: 50px 0;
        .course-time {
          width: 1000px;
          height: 400px;
          margin: 20px auto;
          .swiper-container {
            height: 100%;
          }
          .time-show {
            width: 700px;
            height: 100%;
            margin: 0 auto;
            display: flex;
            .time-show-item {
              width: 350px;
              height: 100%;
              overflow: hidden;
              display: flex;
              flex-direction: column;

              .item-top,
              .item-bottom {
                height: 190px;
              }
              .item-bottom {
                // display: flex;
                // align-content: center;
                .item-bottom-content {
                  background-color: #1667a0;
                  margin: 20px 0;
                  p {
                    color: #fff;
                    text-align: center;
                    padding: 15px;
                  }
                }
              }
            }
          }
        }
      }

      //企业文化
      .content-culture {
        height: 450px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        padding-left: 100px;
        background-color: #c2daeb;
        h3 {
          color: #3c6088;
          font-size: 26px;
        }
        p {
          color: #3c6088;
          font-size: 26px;
          padding: 5px 0;
        }
        span {
          font-weight: 400;
          line-height: 36px;
          font-size: 18px;
          padding: 5px 0;
        }
      }

      //公司荣誉
      .content-honor {
        padding: 50px 0;
        .honor-show {
          width: 1000px;
          margin: 30px auto;
          display: flex;
          flex-wrap: wrap;
          justify-content: flex-start;
          align-content: flex-start;

          li {
            width: 220px;
            height: 320px;
            margin-left: 15px;
            list-style: none;
            border: 1px solid palegoldenrod;

            img {
              width: 100%;
              height: 100%;
            }
          }
        }
        p {
          text-align: center;
          color: #3c6088;
        }
      }

      //团队风采
      .content-team {
        padding: 50px 100px;
        .swiper-img {
          height: 400px;
          background: no-repeat center;
          background-size: cover;
        }
      }

      //合作伙伴
      .content-partner {
        padding: 50px 0;
        .partner-img {
          width: 950px;
          margin: 20px auto;
          display: flex;
          justify-content: flex-start;
          flex-wrap: wrap;
          li {
            width: 107px;
            height: 107px;
            list-style: none;
            margin: 10px 25px;

            img {
              width: 100%;
              height: 100%;
              border: 1px solid rgb(194, 218, 235);
            }
          }
        }
      }
    }
  }
}

@keyframes imgboxkey {
  0% {
    border: solid rgb(29, 66, 185) 2px;
  }
  40% {
    border: solid rgb(255, 255, 255) 2px;
  }
  60% {
    border: solid rgb(255, 255, 255) 2px;
  }
  100% {
    border: solid rgb(29, 66, 185) 2px;
  }
}

@keyframes imgbo {
  0% {
    transform: scale(1);
    box-shadow: 0px 0px 0px 0px #ababab;
  }
  50% {
    transform: scale(1.1);
    box-shadow: 0px 0px 10px 5px #ababab;
  }
  100% {
    transform: scale(1);
    box-shadow: 0px 0px 0px 0px #ababab;
  }
}
.el-divider--horizontal {
  margin: 1px 0;
}

.top {
  h3,
  p {
    text-align: center;
    color: #3c6088;
    font-weight: 400;
    padding: 10px 0;
  }
  h3 {
    font-size: 30px;
  }
  p {
    font-size: 20px;
  }
  .border {
    border-bottom: 1px solid #3c6088;
    width: 15%;
    margin: 0 auto;
  }
}
// .swiper-button-disabled {
//   display: none;
// }
.order {
  order: -1;
}
.order-top {
  order: 1;
}
.el-divider {
  background-color: red;
  height: 3px;
  .el-divider__text {
    width: 20px;
    height: 20px;
    border-radius: 50%;
    padding: 0px;
    color: #fff;
    border: 3px solid red;
  }
}
.el-divider-active {
  background-color: #3c6088;
}
.honor-big-img {
  width: 100%;
  height: 100%;
  z-index: 10;
  text-align: center;
  padding-bottom: 20px;
  padding: 5%;
  //background-color: #14679f;
  .el-dialog__wrapper {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    overflow: auto;
    margin: 0;

    .el-dialog__body {
      overflow: hidden;
      img {
        width: 100%;
      }
    }
  }
}
</style>