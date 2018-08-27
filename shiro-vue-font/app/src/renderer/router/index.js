import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../components/layout/Layout'
// import MyFrame from '../views/layout/MyFrame'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    role: ['admin','editor']     will control the page role (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if fasle ,the page will no be cached(default is false)
  }
 **/
export const constantRouterMap = [
    { path: '/login', component: _import('login/index'), hidden: true },
    { path: '/404', component: _import('errorPage/404'), hidden: true },
    { path: '/401', component: _import('errorPage/401'), hidden: true },
    {
        path: '',
        component: Layout,
        redirect: 'dashboard',
        special: '1',
        children: [{
            path: 'dashboard',
            component: _import('dashboard/test'),
            name: 'dashboard',
            meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
        }]
    },
    {
        path: '/market',
        component: Layout,
        redirect: 'noredirect',
        name: 'market',
        meta: {
            title: 'market',
            icon: 'table'
        },
        children: [
            { path: 'market', component: _import('dashboard/test2'), name: 'marketData', meta: { title: 'marketData', noCache: true }},
            { path: 'bench', component: _import('dashboard/test2'), name: 'benchData', meta: { title: 'benchData', noCache: true }}
        ]
    },
]

export default new Router({
    // mode: 'history', //后端支持可开
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRouterMap
})

export const asyncRouterMap = [
    { path: '*', redirect: '/404', hidden: true }
]
// export const asyncRouterMap = [
//     {
//         path: '/market',
//         component: Layout,
//         redirect: 'noredirect',
//         name: 'market',
//         meta: {
//             title: 'market',
//             icon: 'table'
//         },
//         children: [
//             { path: 'market', component: _import('market/marketTable'), name: 'marketData', meta: { title: 'marketData', noCache: true }},
//             { path: 'bench', component: _import('market/benchTable'), name: 'benchData', meta: { title: 'benchData', noCache: true }}
//         ]
//     },
//
//     {
//         path: '/record',
//         component: Layout,
//         redirect: 'noredirect',
//         name: 'record',
//         meta: {
//             title: 'record',
//             icon: 'chart'
//         },
//         children: [
//             { path: 'pricing', component: _import('record/pricingTable'), name: 'pricingDtls', meta: { title: 'pricingDtls', noCache: true }},
//             //  { path: 'market', component: _import('charts/line'), name: 'marketDtls', meta: { title: 'marketDtls', noCache: true }},
//             { path: 'chart', component: _import('record/pricingChart'), name: 'chartDtls', meta: { title: 'chartDtls', noCache: true }}
//         ]
//     },
//
//     {
//         path: '/setting',
//         component: Layout,
//         redirect: 'noredirect',
//         name: 'setting',
//         meta: {
//             title: 'setting',
//             icon: 'form'
//         },
//         children: [
//             { path: 'calendar', component: _import('setting/holidays'), name: 'calendar', meta: { title: 'calendar', noCache: true }},
//             { path: 'global.sass', component: _import('setting/holidays'), name: 'global.sass', meta: { title: 'global.sass', noCache: true }}
//         ]
//     },
//
//     {
//         path: '/monitor',
//         component: Layout,
//         redirect: 'noredirect',
//         name: 'monitor',
//         meta: {
//             title: 'monitor',
//             icon: 'star'
//         },
//         children: [
//             { path: 'apServer', component: _import('monitor/sysLogs'), name: 'apServer', meta: { title: 'apServer', noCache: true }},
//             { path: 'cpServer', component: _import('monitor/sysLogs'), name: 'cpServer', meta: { title: 'cpServer', noCache: true }},
//             { path: 'syslog', component: _import('monitor/sysLogs'), name: 'syslog', meta: { title: 'syslog', noCache: true }    }
//         ]
//     },
//
//     {
//         path: '/system',
//         component: Layout,
//         redirect: 'noredirect',
//         name: 'system',
//         meta: {
//             title: 'system',
//             icon: 'bug'
//         },
//         children: [
//             { path: 'sysUser', component: _import('system/sysUser'), name: 'sysUser', meta: { title: 'sysUser', noCache: true }},
//             { path: 'sysRole', component: _import('system/sysRole'), name: 'sysRole', meta: { title: 'sysRole', noCache: true }},
//             { path: 'sysMenu', component: _import('system/sysMenu'), name: 'sysMenu', meta: { title: 'sysMenu', noCache: true } }
//         ]
//     },
//     { path: '*', redirect: '/404', hidden: true }
// ]