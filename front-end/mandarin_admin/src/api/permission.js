import axios from '@/config/httpConfig'
const hostname = 'localhost'
const port = '8000'

var options = {
    hostname: hostname,
    port: port
}

export function fetchPermission() {
    return axios.get('/user/info', options)
}

// 获取用户列表
export function getUserList() {
    return axios.get('/user/list')
}
// 获取角色列表
export function getRoleList() {
    return axios.get('/role/list')
}
// 获取所有权限
export function getAllPermissiion() {
    return axios.get('/permission/all')
}
// 获取一级权限列表
export function getFirstLevel() {
    return axios.get('/permission/resource')
}
// 获取次级权限列表
export function getNextLevel(id) {
    return axios.get(`/permission/level?id=${id}`)
}
export function login(data) {
    console.log(`the loginForm is ${data}`)
    return axios.post('/user/login', data)
}
