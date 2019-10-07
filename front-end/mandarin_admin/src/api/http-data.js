/**
 * @description 此选项为备用方案解决跨域问题，优先使用axios
 * @exports 导出两个函数
 * @param path
 * @param data
 * @version 0.0
 * @author GHL
 */

// 导入所需的npm
const http = require('http')
const queryString = require('querystring')

// 全局变量定义
let hostname = 'localhost'
let port = 8000

/**
 *
 * @description 发送get请求
 * @param {String} path
 * @param {String} data
 * @returns Promise(response_data)
 *
 */
export function getData(path, data) {
    var content = queryString.stringify(data)
    var options = {
        hostname: hostname,
        port: port,
        path: path + '?' + content,
        method: 'GET'
    }
    return new Promise((resolve, reject) => {
        var req = http.request(options, res => {
            res.setEncoding('utf-8')
            res.on('data', chunck => {
                resolve(chunck)
            })
        })
        req.on('error', e => {
            reject(e)
        })
        req.end()
    })
}

/**
 *
 * @description  发送POST请求
 * @param {String} path
 * @param {String} data
 * @return Promise(response_data)
 */
export function postData(path, data) {
    var options = {
        host: hostname,
        port: port,
        path: path,
        method: 'POST',
        headers: { 'Content-Type': 'charset=UTF-8' }
    }
    var content = queryString.stringify(data)
    return new Promise((resolve, reject) => {
        var req = http.request(options, res => {
            res.on('data', chunck => {
                resolve(chunck.toString())
            })
        })
        req.on('error', e => {
            reject(e)
        })
        req.write(content)
        req.end()
    })
}

// TEST

// var formdata=new FormData()
// formdata.append('name','jialu')
// formdata.append('password','123')

// postData('/mandarin/login', formdata).then(res => {
//     console.log(res)
// }).catch(err => {
//     console.log(err)
// })
