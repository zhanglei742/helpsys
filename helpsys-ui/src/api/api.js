import axios from 'axios';
import Vue from 'vue';

//本地
let base = 'http://localhost:8080/system';
export var url = "http://localhost:8080/system";

axios.defaults.withCredentials = true;

//login
export const baseUrl = () => {
  return base;

};
// 登录
export const login = params => {
  return axios.post(`${base}/user/login`, params).then(res => res.data);
};

//respon拦截器
axios.interceptors.response.use(
  response=>{
    console.log(response);
    if(response.data.code=='401' || response.status=="401" ){
      Vue.prototype.vm.$message({
        message:'未授权',
        type:'error'
      })
    }
    if(response.data.code=='405'|| response.status=="405"){
      Vue.prototype.vm.$message({
        message:'系统繁忙',
        type:'error'
      })
    }
    if(response.data.code=='404'|| response.status=="404"){
      Vue.prototype.vm.$message({
        message:'网页未找到',
        type:'error'
      })
    }

    return response
  },
  error=>{
    Vue.prototype.vm.$message({
      message:"系统错误",
      type:'error'
    })
    console.log(Vue.prototype.vm.$message)
    return Promise.reject(error);
  }
)











