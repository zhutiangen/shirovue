import { asyncRouterMap, constantRouterMap } from '@/router'
import Layout from '../../components/layout/Layout'
const _import = require('../../router/_import_' + process.env.NODE_ENV)


const asyncRouter = {
    state: {
        allRouters: constantRouterMap, // 所有
        addRouters: []  // 后台提供路由
    },
    mutation: {
        SET_ROUTERS: (state, routers) => {
            state.addRouters = routers
            state.allRouters = constantRouterMap.concat(routers)
        }
    },
    actions: {
        GenerateRoutes({ commit }, data) {

        }
    }
}

export default asyncRouter

/**
 * 支持后台加载路由
 * 形成路由
 */
function formRoute(menus) {
    let data = [];
    for(let menu of menus) {
        if (menu.parentId === '0') {
            let map = buildTreeChildsMap(menu, menus);
            data.push(map);
        }
    }
    return data;
}

/**
 * 递归实现 路由的分级
 * @param node
 * @param menus
 * @returns {{path: string, component: {name, components, computed}, redirect: string, name: *, meta: {title: *, icon: *}}}
 */
function buildTreeChildsMap(node, menus) {
    let map = {
        'path': (isLeaf(node, menus) ? '' : '/') + node.menuUrl.substring(node.menuUrl.lastIndexOf('/') + 1, node.menuUrl.length),
        'component': isLeaf(node, menus) ? _import(node.menuUrl) : Layout ,
        'redirect': isLeaf(node, menus) ?  '' : 'noredirect',
        'name': node.menuName,
        'meta': {
            'title': node.menuName,
            icon: node.menuIcon,
        }
    }

    let childs = buildTreeChilds(node, menus);
    if (childs.length !== 0) {
        map['children'] = childs;
    }
    return map;
}


function buildTreeChilds(node, menus) {
    let data = [];
    let datas = getChilds(node, menus)
    for (let node of datas) {
        let retdata = buildTreeChildsMap(node, menus);
        data.push(retdata);
    }
    return data;
}

function getChilds(node, menus) {
    let childs = [];
    for (let menu of menus) {
        if (menu.parentId === node.id) {
            childs.push(menu);
        }
    }
    return childs;
}

function isLeaf(node, menus) {
    if (node.parentId == 0) {
        return false;
    }
    for (let menu of menus) {
        if (menu.parentId === node.id) {
            return false;
        }
    }
    return true;
}

/**
 * 通过menu判断是否与当前用户权限匹配
 * @param menu
 * @param route
 */
function hasPermissionByMenu(menus, route) {
    if (route.menu) {
        /*
        * 如果这个路由有menu属性,就需要判断用户是否拥有此menu权限
        */
        return menus.indexOf(route.menu) > -1;
    } else {
        return true
    }
}

/**
 * 递归过滤异步路由表，返回符合用户菜单权限的路由表
 * @param asyncRouterMap
 * @param menus
 */
function filterAsyncRouterByMenu(asyncRouterMap, menus) {
    let a = 0;
    const accessedRouters = asyncRouterMap.filter(route => {
        if (hasPermissionByMenu(menus, route)) {
            if (route.children && route.children.length) {
                route.children = filterAsyncRouterByMenu(route.children, menus)
                //如果过滤一圈后,没有子元素了,这个父级菜单就也不显示了
                return (route.children && route.children.length)
            }
            return true
        }
        return false
    })
    return accessedRouters
}