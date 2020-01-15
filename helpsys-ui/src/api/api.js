import axios from 'axios';
import Vue from 'vue';

//本地
let base = 'http://localhost:8080/helpsys';
axios.defaults.withCredentials = true;

// 登录
export const login = params => {
  return axios.post(`${base}/user/login`, params).then(res => res.data);
};






