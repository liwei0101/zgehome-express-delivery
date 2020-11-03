import Vue from 'vue'
import Router from 'vue-router'
import {setTit} from '@/libs/util'

Vue.use(Router)
const router = new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
    {
      "meta": {title: '快递速寄'},
      "path": "/",
      "name": "home",
      "component": () => import('./views/index'),
      redirect: '/express',
      children:[
        {
          "meta": {title: '快递速寄'},
          "path": "/express",
          "name": "express",
          "component": () => import('./views/express'),
        },
        {
          "meta": {title: ''},
          "path": "/edit",
          "name": "edit",
          "component": () => import('./views/edit'),
        },
        {
          "meta": {title: '详情'},
          "path": "/detail",
          "name": "detail",
          "component": () => import('./views/detail'),
        },
      ]
    }
  ]
})

router.afterEach(route => {
  //设置标题
  if (route.meta && route.meta.title) {
    setTit(route.meta.title)
  } else {
    //如果页面中标题为动态标题，路由表内不填写，由页面内部设置
  }
})

export default router