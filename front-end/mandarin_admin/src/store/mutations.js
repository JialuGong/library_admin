
export default {
    LOGIN_IN(state, value) {
        state.UserToken = value.token
        state.AdminName = value.admin_name
    },
    LOGIN_OUT(state) {
        state.UserToken = ''
        state.crumbList = ''
        state.AdminName = ''
    },
    toggleNavCollapse(state) {
        state.isSidebarNavCollapse = !state.isSidebarNavCollapse
    },
    setCrumbList(state, list) {
        state.crumbList = list
    }
}
