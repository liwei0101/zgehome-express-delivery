import  './config';
import '@babel/polyfill';
import Vue from 'vue';
import App from './App.vue';
import router from './router';
import '@/common/reset.css';
import '@/common/m.css';
import store from './store';

import { getQueryString} from '@/libs/util.js';

import { setStore, getStore, removeStore } from '@/libs/storage';
import { getRequest, postRequest, $http} from '@/libs/http/$http.js';


// 组件库未使用请删除
// 有赞
import vant from 'vant';
import 'vant/lib/index.css';
Vue.use(vant);


// 公用组件库: 对资源包大小、性能有影响(建议：手动引入项目所需组件)
import '@/components/index.js';

//Vecharts 图表
import VCharts from 'v-charts';
Vue.use(VCharts);

//vue-echarts 图表
import ECharts from 'vue-echarts';
Vue.component('v-chart', ECharts);
window.echarts = ECharts;

import { Toast } from 'vant';
// 挂载全局使用的方法
Vue.prototype.$Toast = Toast;
Vue.prototype.$http = $http; 
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;

Vue.prototype.setStore = setStore;
Vue.prototype.getStore = getStore;
Vue.prototype.removeStore = removeStore;

Vue.config.productionTip = false;





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
    "code":code
  }
  $http.post("/v1/open/getSessionId", param).then((res)=> {
    if (res.meta.code == 0) {
      sessionStorage.setItem("sessionId",res.data);
      init();
    }else{
      Toast(res.meta.message);
    }
  }).catch( (e) => {
    return Promise.reject(e)
  })
}

$http.post("/app/appProjectInit", {}).then((res)=> {
  window.sessionStorage.setItem('base', res.data.domainUrl)
  // window.sessionStorage.setItem('portal', res.data.portal);
  window.sessionStorage.setItem('uploadMuiltipart', res.data.uploadMuiltipart);
  // window.sessionStorage.setItem('dictList', JSON.stringify(res.data.commonProjectInitDictListVOList));
  window.sessionStorage.setItem('thirdPartyCompanyJson', res.data.thirdPartyCompanyJson);
  window.sessionStorage.setItem('postalCodeUrl', res.data.postalCodeUrl);
  window.sessionStorage.setItem('postageUrl', res.data.postageUrl);

  if(sessionStorage.getItem("sessionId")){
    init();
  }else{
    let code = getQueryString("code");
    console.log(code)
    if(code){
      getSession(code);
    }
  }
}).catch( (e) => {
  return Promise.reject(e)
})
