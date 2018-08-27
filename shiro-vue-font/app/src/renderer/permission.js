import router from './router'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式

const whiteList = ['/login', '/authredirect']// 不重定向白名单

let bool = true;

router.afterEach(() => {
    NProgress.done() // 结束Progress
})
