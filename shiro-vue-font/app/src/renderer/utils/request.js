import axios from 'axios'
import { Message } from 'element-ui'
import router from './../router'

axios.defaults.withCredentials=true;

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  // timeout: 5000 // 请求超时时间
})

// request拦截器  发请求的拦截器
service.interceptors.request.use(config => {
  return config
}, error => {
  // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

// respone拦截器 接受请求的拦截器
service.interceptors.response.use(
  response => {
      return response;
  }, (error) => {
    if (error && error.response) {
      switch (error.response.status) {
          case 401:
            error.message = '未授权，请登录';
            // 跳转路由
            router.push('/401');
            break;
          case 400:
              error.message = '请求错误';
            break;
          case 403:
              error.message = '拒绝访问';
            break;
          case 404:
              error.message = '请求地址出错：${error.response,config.url}';
              break
          case 408:
              error.message = '请求超时';
              break
          case 500:
              error.message = '服务器内部错误';
              break
          case 501:
              error.message = '服务未实现'
              break
          case 502:
              error.message = '网关错误'
              break
          case 503:
              error.message = '服务不可用'
              break
          case 504:
              error.message = '网关超时'
              break
          case 505:
              error.message = 'HTTP版本不受支持'
              break
      }
    } else {
        Message({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        })
    }
    return Promise.reject(error)
  })

export default service
