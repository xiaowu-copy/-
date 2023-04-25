import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

let kejianrouter = new Router({
  routes: [
    //默认首页的方法
    {
      path: '/',
      redirect:'/home'
    },  
    {
      path: '/home',
      name: 'shouye',
      component: () => import('./views/Home.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('./views/Register.vue')
    },
    
    {
      path: '/news',
      name: 'news',
      component: () => import('./views/News.vue'),
    },
    {
      path: '/picture',
      name: 'picture',
      component: () => import('./views/Picture.vue'),
    },
    {
      path: '/newsdetails/:id',
      name: 'newsdetails',
      component: () => import('./views/NewsDetails.vue'),
    },
    {
      path: '/class',
      name: 'product',
      component: () => import('./views/ClassicsClass.vue'),
    },
    {
      path: '/case',
      name: 'case',
      component: () => import('./views/Case.vue')
    },
    {
      path: '/casedetails/:id',
      name: 'casedetails',
      component: () => import('./views/CaseDetails.vue')
    },
    {
      path: '/ClassDails/:id',
      name: 'ClassDails',
      component: () => import('./views/ClassDetails.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/goin',
      name: 'goin',
      component: () => import('./views/GoIn.vue')
    },
    {
      path: '/download',
      name: 'download',
      component: () => import('./views/Personal.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      meta: {
        requireAuth: true
      },
      component: () => import('./views/Admin.vue'),
      children: [{
          path: '/admin/user',
          name: 'user',
          component: () => import('./views/Admin/User.vue')
        },
        {
          path: '/admin/news',
          name: 'new',
          component: () => import('./views/Admin/News.vue')
        },
        {
          path: '/admin/cases',
          name: 'cases',
          component: () => import('./views/Admin/Cases.vue')
        },
        {
          path: '/admin/team',
          name: 'team',
          component: () => import('./views/Admin/Video.vue')
        },
        {
          path: '/admin/course',
          name: 'course',
          component: () => import('./views/Admin/Course.vue')
        },
        {
          path: '/admin/enterprise',
          name: 'enterprise',
          component: () => import('./views/Admin/Enterprise.vue')
        },
        {
          path: '/admin/honor',
          name: 'honor',
          component: () => import('./views/Admin/Honor.vue')
        },
        {
          path: '/admin/dictionary',
          name: 'dictionary',
          component: () => import('./views/Admin/Dictionary.vue')
        },
        {
          path: '/admin/page',
          name: 'page',
          component: () => import('./views/Admin/Page.vue')
        },
      
      ]
    }
  ]
})


export default kejianrouter
//判断是否需要登录权限 以及是否登录
kejianrouter.beforeEach((to, from, next) => {
  // 1.如果访问的是登录页面（无需权限），直接放行
  if (to.path === '/login' || to.path === '/register') return next()
  // 2.如果访问的是有登录权限的页面，先要获取token
  const tokenStr = window.sessionStorage.getItem('token')
  // 2.1如果token为空，强制跳转到登录页面；否则，直接放行
  if (!tokenStr) {
    alert("请登录")
    return next('/login')
  }
  next()
})
