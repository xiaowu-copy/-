<template>
  <div>
    <el-upload
      ref="upload"
      list-type="picture-card"
      :limit="4"
      action="#"
      :file-list="fileList"
      :on-preview="preview"
      :http-request="upload"
      :on-change="change"
      :before-upload="beforeUpload"
      :accept="typeList"
      :on-remove="handleRemove"
      
    >
    <i slot="default" class="el-icon-plus"></i>
    </el-upload>
    <el-progress
      v-if="showPercent"
      style="width: 180px"
      :percentage="percent"
    />
    <el-dialog title="图片" :visible.sync="showDialog">
      <img :src="imgUrl" style="width: 100%" alt="" />
    </el-dialog>
    
  </div>
</template>
<script>
import COS from 'cos-js-sdk-v5'
const cos = new COS({
  SecretId: 'AKIDoTumUzGMXeZGnALJ6kEWdXJvNdicJq37', // 密钥id
  SecretKey: 'tXyBCyXu44tY2MUP8ew0rX3LPGo9II6h'  // 密钥 key
}) // 实例化的包 已经具有了上传的能力 可以上传到该账号里面的存储桶了
export default {
  data() {
    return {
      fileList: [], // 图片地址设置为数组
      showDialog: false, // 控制显示弹层
      imgUrl: '',
      currentImageUid: null,
      typeList: 'image/*,video/*',
      showPercent: false, // 是否显示进度条
      percent: 0 ,// 上传进度,
      url: '',
      key: ''
      
      
    }
  },
  //来获取子组件的值
  props: ['auto','register','picture'],
  methods: {
    preview(file) {
      this.imgUrl = file.url
     
      this.showDialog = true
    },
    beforeUpload(file) {
     
      // 允许上传的文件类型
      const types = ['image/jpeg', 'image/gif', 'image/bmp', 'image/png','video/mp4']
      if (!types.includes(file.type)) {
        this.$message.error('上传图片只能是 JPG、GIF、BMP、PNG 格式!')
        return false // return false 会阻止图片的上传操作
      }
      const maxSize = 1024 * 1024*512
      if (file.size > maxSize) {
        this.$message.error('图片大小最大不能超过1M')
        return false
      }
      this.currentImageUid = file.uid
      this.showPercent = true
      return true
    },
    upload(params) {
      window.console.log(params.file)
      if (params.file) {
        //得到是图还是视频
        if(params.file.type == "video/mp4"){
           this.key = 'video/'
          }else{
            if(this.register == 'register'){
                this.key = '头像文件夹/'
                window.console.log('头像文件夹/')
              }
            else if(this.picture=="picture"){
              this.key = 'picture/'
              window.console.log('picture/')
            }
            else
                this.key = 'news/'
          }
        // 执行上传操作
        cos.putObject({
          Bucket: 'bishe-1317249791', // 存储桶
          
          Region: 'ap-beijing', // 地域
          Key: this.key+params.file.name, // 文件名
          Body: params.file, // 要上传的文件对象
          StorageClass: 'STANDARD', // 上传的模式类型 直接默认 标准模式即可
          onProgress: (progressData) => {
            this.percent = progressData.percent * 100
          }
        }, (err, data) => {
          // data返回数据之后 应该如何处理
          if (err) return
          this.fileList = this.fileList.map(item => {
            if (item.uid === this.currentImageUid) {
              window.console.log(this.fileList[0].url+"6666")
              return { url: 'http://' + data.Location, name: item.name }
            }
            return item
          })
        //获取图片的地址
         this.url = this.fileList[0].url
         window.console.log(this.url)
         //上传给父组件相关参数

         this.$emit("getImgurl", this.url);
         

        })
      }
    },
    handleRemove(file, fileList) {
      this.fileList = this.fileList.filter(item => item.uid !== file.uid)
      // console.log(file)
      cos.deleteObject({
        Bucket: 'xxx', /* 必须 */
        Region: 'ap-nanjing', /* 存储桶所在地域，必须字段 */
        Key: file.name /* 必须 */
      }, (err, data) => {
        // console.log(err || data)
      })
    },
    change(file, fileList) {
      this.fileList = fileList
    },

  },
  //用来监听是否进行上传
  watch:{
      
    }
    
  }

</script>

