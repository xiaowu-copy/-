/**
readme：
本文件为腾讯云对象存储相关工具类方法
方法一：getCOSList 查询腾讯云对象存储指定桶中的文件
方法二：uploadObject 上传文件到腾讯云对象存储的指定桶
方法三：removeObject 删除腾讯云对象存储指定桶中的文件

注意：桶的访问权限需要设置指定域名（不然会出现跨域问题），现在设置允许访问的域名是：
http://localhost:8080 https://xxx.com.cn/
所以本地调试时，需要用http://localhost:8080，不可用其他端口。
跨域配置：
桶：指定域名 + 指定子账号能上传；外部不能访问，统一通过cdn访问；
CDN：设置为无跨域限制
*/
// https://cloud.tencent.com/document/product/436/11459
import COS from 'cos-js-sdk-v5'
import { Message } from 'element-ui'
// import { getCOSSecretKey } from '@/api/common/index'
const config = {
  videoBucket: 'bishe-1317249791',
  imageBucket: 'xxx',
  Region: 'ap-beijing'
}

/**
文件上传到文件夹规则：商城文章素材上传到 art 文件夹
图片上传到图片桶（imageBucket）：
生产环境文件夹：imageBucket + '/art/'
测试环境文件夹：imageBucket + '/test/art/'
视频上传到视频桶（videoBucket）：
生产环境文件夹：videoBucket + '/art/'
测试环境文件夹：videoBucket + '/test/art/'
*/

export function getCOSList (callback) {
  let type = 'video'
//   getCOSSecretKey({ bucket: type === 'video' ? config.videoBucket : config.imageBucket }).then(response => { // 后台接口返回 密钥相关信息
//     // 后台接口返回 密钥相关信息
//     const data = response.result
//     var credentials = data && data.credentials
//     if (!data || !credentials) return window.console.error('credentials invalid')
//     // 初始化
//     var cos = new COS({
//       getAuthorization: (options, callback) => {
//         callback({
//           TmpSecretId: credentials.tmpSecretId,
//           TmpSecretKey: credentials.tmpSecretKey,
//           XCosSecurityToken: credentials.sessionToken,
//           StartTime: data.startTime,
//           ExpiredTime: data.expiredTime,
//           expiration: data.expiration,
//           requestId: data.requestId,
//         })
//       },
//     })
//     cos.getBucket(
//       {
//         Bucket: type === 'video' ? config.videoBucket : config.imageBucket, // 存储桶名称
//         Region: config.Region, // 存储桶所在地域，必须字段
//         // Prefix: 'a/',           /* 列出目录 a 的所有文件，非必须 */
//         onHashProgress: (progressData) => {
//         },
//         onProgress: (progressData) => {
//           const percent = parseInt(progressData.percent * 10000) / 100;
//           const speed = parseInt((progressData.speed / 1024 / 1024) * 100) / 100;
//           // console.log('进度：' + percent + '%; 速度：' + speed + 'Mb/s;');
//         },
//       },
//       (err, data) => {
//         window.console.log('err', err)
//         if (err) {
//           Message({ message: '列表获取失败！', type: 'error' })
//         } else {
//             window.console.log('列表data', data)
//         }
//       }
//     )
//   })
}
// 上传到腾讯云cos
export function uploadObject (file, callback) {
    window.console.log('file, flag', file)
  // return
  /* 1.获取临时秘钥data
   2.初始化
   3.判断上传文件的类型
   4.判断文件大小 是否需要分片上传*/
  const type = file.type.substring(0, 5)
  let fileName = file.name || ""
  const origin_file_name = fileName.split(".").slice(0, fileName.split(".").length - 1).join('.') // 获取文件名称
  // console.log('origin_file_name', origin_file_name)
  // 获取当前时间戳 与文件类型拼接 为cos.putObject里的参数Key
  const upload_file_name = new Date().getTime() + '.' + fileName.split(".")[fileName.split(".").length - 1] // 文件上传名称定义为当前时间戳
  // console.log('upload_file_name', upload_file_name)
  // 获取密钥
  getCOSSecretKey({ bucket: type === 'video' ? config.videoBucket : config.imageBucket })
    .then(response => { // 后台接口返回 密钥相关信息
      const data = response.result
      var credentials = data && data.credentials
      if (!data || !credentials) return window.console.error('credentials invalid')
      // 初始化
      var cos = new COS({
        getAuthorization: (options, callback) => {
          callback({
            TmpSecretId: credentials.tmpSecretId,
            TmpSecretKey: credentials.tmpSecretKey,
            XCosSecurityToken: credentials.sessionToken,
            StartTime: data.startTime,
            ExpiredTime: data.expiredTime,
            expiration: data.expiration,
            requestId: data.requestId,
          })
        },
      })
      // 获取上传文件大小
      let size = file.size
      const isProd = process.env.VUE_APP_ENV === 'sit' || process.env.VUE_APP_ENV === 'dev' || process.env.VUE_APP_ENV === 'uat' ? false : true
      window.console.log('process.env.VUE_APP_ENV', process.env.VUE_APP_ENV)
      let key = isProd ? '/art/' + upload_file_name : '/test/art/' + upload_file_name
      window.console.log('key', key)
      if (size / (1024 * 2024) < 50) {
        window.console.log('普通上传')
        cos.putObject(
          {
            Bucket: type === 'video' ? config.videoBucket : config.imageBucket, // 存储桶名称
            Region: config.Region, // 存储桶所在地域，必须字段
            Key: 'video/', // 名称
            StorageClass: 'STANDARD',
            Body: file, // 上传文件对象
            onHashProgress: (progressData) => {
              // console.log('校验中', JSON.stringify(progressData))
            },
            onProgress: (progressData) => {
              const percent = parseInt(progressData.percent * 10000) / 100;
              const speed = parseInt((progressData.speed / 1024 / 1024) * 100) / 100;
              window.console.log('进度：' + percent + '%; 速度：' + speed + 'Mb/s;');
            },
          },
          (err, data) => {
            if (err) {
                window.console.log('err', err)
              Message({ message: '文件上传失败,请重新上传', type: 'error' })
            } else {
              if (type === 'video') {
                let fileUrl = 'https://' + config.videoBucket + '.file.myqcloud.com' + key
                callback(fileUrl, origin_file_name) // 返回视频链接地址和视频的原始名称
              } else {
                let fileUrl = 'https://' + config.imageBucket + '.file.myqcloud.com' + key
                callback(fileUrl) // 返回图片链接地址和视频的原始名称
              }
            }
          }
        )
      } else {
        window.console.log('视频分块上传')
        // 上传分块
        cos.sliceUploadFile({
          Bucket: config.videoBucket, // 存储桶名称
          Region: config.Region, // 存储桶所在地域，必须字段
          Key: key /* 必须 */,
          Body: file,
          onTaskReady: (taskId) => {
            /* 非必须 */
            // console.log(taskId)
          },
          onHashProgress: (progressData) => {
            /* 非必须 */
            // console.log(JSON.stringify(progressData))
          },
          onProgress: function (progressData) {
            const percent = parseInt(progressData.percent * 10000) / 100;
            const speed = parseInt((progressData.speed / 1024 / 1024) * 100) / 100;
            window.console.log('进度：' + percent + '%; 速度：' + speed + 'Mb/s;');
          },
        },
          (err, data) => {
            if (err) {
                window.console.log(err)
              Message({ message: '文件上传失败,请重新上传', type: 'error' })
            } else {
              let fileUrl = 'https://' + config.videoBucket + '.file.myqcloud.com' + key
              callback(fileUrl, origin_file_name) // 返回视频链接地址和视频的原始名称
            }
          }
        )
      }
    })
}

// 删除腾讯云cos文件
export function removeObject (key, callback) {
  const typeFlag = ['png', 'jpeg', 'jpg', 'gif', 'webp'].indexOf(key.split('.')[1]) !== -1 ? true : false
  getCOSSecretKey({ bucket: typeFlag ? config.imageBucket : config.videoBucket })
    .then((response) => {
      // 后台接口返回 密钥相关信息
      const data = response.result
      var credentials = data && data.credentials
      var cos = new COS({
        getAuthorization: function (options, callback) {
          callback({
            TmpSecretId: credentials.tmpSecretId,
            TmpSecretKey: credentials.tmpSecretKey,
            XCosSecurityToken: credentials.sessionToken,
            StartTime: data.startTime,
            ExpiredTime: data.expiredTime,
            expiration: data.expiration,
            requestId: data.requestId,
          })
        },
      })
      cos.deleteObject(
        {
          Bucket: typeFlag ? config.imageBucket : config.videoBucket, // 存储桶名称
          Region: config.Region, // 存储桶所在地域，必须字段
          Key: key /* 必须 */,
        },
        function (err, data) {
          if (data) {
            window.console.log('文件删除成功')
          } else {
            Message({ message: '文件删除失败！', type: 'error' })
          }
        }
      )
    })
}

// 查询媒体文件的信息
export function getCOSVideoInfo (key, callback) {
  getCOSSecretKey({ bucket: config.videoBucket })
    .then((response) => {
      // 后台接口返回 密钥相关信息
      const data = response.result
      var credentials = data && data.credentials
      var cos = new COS({
        getAuthorization: function (options, callback) {
          callback({
            TmpSecretId: credentials.tmpSecretId,
            TmpSecretKey: credentials.tmpSecretKey,
            XCosSecurityToken: credentials.sessionToken,
            StartTime: data.startTime,
            ExpiredTime: data.expiredTime,
            expiration: data.expiration,
            requestId: data.requestId,
          })
        },
      })
      cos.request({
        Bucket: config.videoBucket,
        Region: config.Region,
        Method: 'GET',
        Key: key,  /* 存储桶内的媒体文件，必须字段 */
        Query: {
          'ci-process': 'videoinfo' /** 固定值，必须 */
        }
      },
        function (err, data) {
          if (err) {
            window.console.log(err)
            Message({ message: '查询媒体文件信息失败！', type: 'error' })
          } else {
            callback(data.Response)
          }
        }
      )
    })
}

export default {
   getCOSList,
  uploadObject,
  removeObject,
  getCOSVideoInfo
}
