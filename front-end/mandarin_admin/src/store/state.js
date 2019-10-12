export default {
    get UserToken() {
        return localStorage.getItem('token')
    },
    set UserToken(value) {
        localStorage.setItem('token', value)
    },
    set UserName(value) {
        localStorage.setItem('name', value)
    },
    set UserPassword(value) {
        localStorage.setItem('password', value)
    },
    get UserName() {
        return localStorage.getItem('name')
    },
    get UserPassword() {
        return localStorage.getItem('password')
    },
    /* 导航菜单是否折叠 */
    isSidebarNavCollapse: false,
    /* 面包屑导航列表 */
    crumbList: []
}
