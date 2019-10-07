import axios from '@/config/httpConfig'

export function login(data) {
    console.log(`the loging data is ${data}`)
    return axios.post('/mandarin/admin/login')
}

export function fetchPermission() {
    return axios.get('/mandarin/admin/info')
}

export function getAllLib() {
    return axios.get('/mandarin/admin/getAllLib')
}

export function getReaderDeposit() {
    return axios.get('/mandarin/admin/getReaderDeposit')
}

export function getBookFine() {
    return axios.get('/mandarin/admin/getBookFine')
}

export function getBookPeriod() {
    return axios.get('/mandarin/admin/getBookPeriod')
}

export function modifyReaderDeposit(data) {
    return axios.post('/mandarin/admin/modifyReaderDeposit', data)
}

export function modifyBookFine(data) {
    return axios.post('/mandarin/admin/modifyBookFine', data)
}

export function modifyBookPeriod(data) {
    return axios.post('/mandarin/admin/modifyBookPeriod', data)
}
