import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/login/login'
import NotFound from '@/pages/errorPage/404'
import Forbidden from '@/pages/errorPage/403'
import Layout from '@/pages/layout/index'
import Home from '@/pages/home/index'
import changePassword from '@/pages/change-password/index'
Vue.use(Router)

/* 初始路由 */
export default new Router({
    routes: [
        {
            path: '/login',
            component: Login
        }
    ]
})

// TODOS:添加changepassword的路由

/* 准备动态添加的路由 */
export const DynamicRoutes = [
    {
        path: '',
        component: Layout,
        name: 'container',
        redirect: 'home',
        meta: {
            requiresAuth: true,
            name: '首页'
        },
        children: [
            {
                id: 1,
                path: 'home',
                component: Home,
                name: 'home',
                meta: {
                    name: 'home',
                    icon: 'tree'
                }
            }
        ]
    },
    {
        path: '/change-password',
        component: changePassword
    },
    {
        path: '/403',
        component: Forbidden
    },
    {
        path: '*',
        component: NotFound
    }
]
