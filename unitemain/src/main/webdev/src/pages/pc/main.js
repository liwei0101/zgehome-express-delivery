import  './config';
import '@babel/polyfill';
import Vue from 'vue';
import App from './App.vue';
import router from './router';
import '@/common/reset.css';
import '@/common/pc.css';
import store from './store';

import { getQueryString, sessionId} from '@/libs/util.js';

import { setStore, getStore, removeStore } from '@/libs/storage';
import { getRequest, postRequest, $http} from '@/libs/http/$http.js';


// 组件库未使用请删除

//iview
import iView from 'iview';
import 'iview/dist/styles/iview.css';
Vue.use(iView);
// 富文本编辑器
import VueQuillEditor from 'vue-quill-editor';
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
Vue.use(VueQuillEditor);

// 公用组件库: 对资源包大小、性能有影响(建议：手动引入项目所需组件)
import '@/components/index.js';

//Vecharts 图表
import VCharts from 'v-charts';
Vue.use(VCharts);

//vue-echarts 图表
import ECharts from 'vue-echarts';
Vue.component('v-chart', ECharts);
window.echarts = ECharts;

// 挂载全局使用的方法
Vue.prototype.$http = $http; 
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;

Vue.prototype.setStore = setStore;
Vue.prototype.getStore = getStore;
Vue.prototype.removeStore = removeStore;

Vue.config.productionTip = false;


function sessionPast(response){  //sessionId是否失效
  if (response.meta && response.meta.code != 0) {
    window.alert('登录超时,请重新登录');
    console.log('sessionStorage情况;', sessionStorage);
    // 清空   sessionStorage
    sessionId('');
    setTimeout(function () {
      if (window.sessionStorage.getItem('portal')) {
        window.top.location.href = window.sessionStorage.getItem('portal')
      }else{
        window.top.location.href = 'http://orgt800.systoon.com/workbench-portal/#/login'
      }
    }, 1000);
    return false
  }else{
    return true
  }
}

let init = ()=>{
  new Vue({
    store,
    router,
    render: h => h(App),
  }).$mount('#app')
}

function getSession (code) {
  // 请求--session
  let param = {
    "code": encodeURIComponent(code)
  }
  $http.get(`/pc/pcValidateAuthCode`, {params: param}).then((res)=> {
    // if(!sessionPast(res)) return false;
    if (res.meta.code == 0) {
      sessionStorage.setItem("sessionId",res.data.sessionId);
      init();
    }else{
      console.log(res.meta.message)
      // Toast(res.meta.message);
    }
  }).catch( (e) => {
    return Promise.reject(e)
  })
}

$http.post("/pc/pcProjectInit", {}).then((res)=> {
  window.sessionStorage.setItem('base', res.data.domainUrl)
  // window.sessionStorage.setItem('portal', res.data.portal);
  window.sessionStorage.setItem('uploadMuiltipart', res.data.uploadMuiltipart);
  // window.sessionStorage.setItem('imgUrl', res.data.imgUrl);
  // window.sessionStorage.setItem('getMapInfoUrl', res.data.getMapInfoUrl);
  // window.sessionStorage.setItem('dictList', JSON.stringify(res.data.commonProjectInitDictListVOList));

  if(!sessionPast(res)) return false;
  if(sessionStorage.getItem("sessionId")){
    init();
  }else{
    let code = getQueryString("code");
    console.log(code)
    if(code){
      getSession(code);
    }else{
      // window.location.href = window.sessionStorage.getItem('portal');
      // console.log("code不存在");
    }
  }
}).catch( (e) => {
  return Promise.reject(e)
})
