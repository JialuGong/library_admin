export default {
    LOGIN_IN(state, token) {
        state.UserToken = token
    },
    LOGIN_OUT(state) {
        state.UserToken = ''
    },
    toggleNavCollapse(state) {
        state.isSidebarNavCollapse = !state.isSidebarNavCollapse
    },
    setCrumbList(state, list) {
        state.crumbList = list
    },
    SET_USERINFO(state, name, password) {
        state.UserName = name
        state.UserPassword = password
    },
    REALLY_OUT(state) {
        state.UserName = ''
        state.UserPassword = ''
        state.UserToken = ''
    }
}
