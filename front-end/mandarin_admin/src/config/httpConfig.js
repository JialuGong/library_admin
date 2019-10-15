
// TODOS token失效验证
import axios from 'axios'
import store from '@/store/index.js'
import baseURL from './baseUrl'
import { Message } from 'element-ui'

const http = {}

// 创建axios实例
var instance = axios.create({
    timeout: 1000,
    baseURL,
    validateStatus(status) {
        switch (status) {
        case 400:
            Message.error('request error')
            break
        case 401:
            Message.warning({
                message: 'Authourization error.Please login again'
            })
            store.commit('LOGIN_OUT')
            setTimeout(() => {
                window.location.reload()
            }, 1000)
            return
        case 403:
            Message.warning({
                message: 'Access Denied'
            })
            break
        case 404:
            Message.warning({
                message: 'Request error.Not found'
            })
            break
        case 500:
            Message.warning({
                message: 'Server error'
            })
            break
        }
        return status >= 200 && status < 300
    }
})

// 添加请求拦截器
instance.interceptors.request.use(
    function (config) {
        // 请求头添加token
        if (store.state.UserToken) {
            config.headers.Authorization = `${store.state.UserToken}`
        }
        return config
    },
    function (error) {
        return Promise.reject(error)
    }
)

// 响应拦截器即异常处理
instance.interceptors.response.use(
    response => {
        return response.data
    },
    err => {
        if (err && err.response) {
        } else {
            err.message = 'Server Connection Failed'
        }
        // Message.error({
        //     message: err.message
        // })
        return Promise.reject(err.message)
        // return Promise.reject(err.response)
    }
)

http.get = function (url, options) {
    let loading
    if (!options || options.isShowLoading !== false) {
        loading = document.getElementById('ajaxLoading')
        loading.style.display = 'block'
    }
    return new Promise((resolve, reject) => {
        instance
            .get(url, options)
            .then(response => {
                if (!options || options.isShowLoading !== false) {
                    loading = document.getElementById('ajaxLoading')
                    loading.style.display = 'none'
                }
                if (response.code === 1) {
                    resolve(response.data)
// 对于token失效，如果本地存储有用户名和密码，重新进行login操作，获取token（post操作同)
                    store.commit('LOGIN_OUT')
                    Message.error({
                        message: response.message
                    })
                    reject(response.message)
                }
            })
            .catch(e => {
                console.log(e)
            })
    })
}

http.post = function (url, data, options) {
    let loading
    if (!options || options.isShowLoading !== false) {
        loading = document.getElementById('ajaxLoading')
        loading.style.display = 'block'
    }
    return new Promise((resolve, reject) => {
        instance
            .post(url, data, options)
            .then(response => {
                if (!options || options.isShowLoading !== false) {
                    loading = document.getElementById('ajaxLoading')
                    loading.style.display = 'none'
                }
                if (response.code === 1) {
                    resolve(response.data)
                } else {

                    store.commit('LOGIN_OUT')

                    Message.error({
                        message: response.message
                    })
                    reject(response.message)
                }
            })
            .catch(e => {
                store.commit('LOGIN_OUT')
                console.log(e)
            })
    })
}

export default http
