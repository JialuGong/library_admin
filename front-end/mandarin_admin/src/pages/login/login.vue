<template>
    <div class="login-container">
        <el-form
            class="login-form"
            autocomplete="on"
            :model="loginForm"
            :rules="loginRules"
            ref="loginForm"
            label-position="left"
        >
            <h3 class="title">Mandarin Library System For Admin</h3>
            <el-form-item prop="username">
                <span class="svg-container svg-container_login">
                    <svg-icon icon-class="user" />
                </span>
                <el-input
                    name="username"
                    type="text"
                    v-model="loginForm.admin_name"
                    autocomplete="on"
                    placeholder="admin_name"
                />
            </el-form-item>
            <el-form-item prop="admin_password">
                <span class="svg-container">
                    <svg-icon icon-class="password"></svg-icon>
                </span>
                <el-input
                    name="password"
                    :type="pwdType"
                    @keyup.enter.native="login"
                    v-model="loginForm.admin_password"
                    autocomplete="on"
                    placeholder="password"
                ></el-input>
                <span class="show-pwd" @click="showPwd">
                    <svg-icon icon-class="eye" />
                </span>
            </el-form-item>
            <el-form-item>
                <el-button
                    type="primary"
                    style="width:100%;"
                    :loading="loading"
                    @click.native.prevent="login"
                >Login in</el-button>
            </el-form-item>
        </el-form>
        <div class="footerBox">
        </div>
    </div>
</template>

<script>
import { login } from '@/api/data'
export default {
    data() {
        const validateUsername = (rule, value, callback) => {
            if (value.length < 5) {
                callback(new Error('Please enter user name'))
            } else {
                callback()
            }
        }
        const validatePass = (rule, value, callback) => {
            if (value.length < 5) {
                callback(new Error("The length of password can't less than 5"))
            } else {
                callback()
            }
        }
        return {
            loginForm: {
                admin_name: 'admin',
                admin_password: 'admin'
            },
            loginRules: {
                admin_name: [
                    {
                        required: true,
                        trigger: 'blur',
                        validator: validateUsername
                    }
                ],
                admin_password: [
                    { required: true, trigger: 'blur', validator: validatePass }
                ]
            },
            loading: false,
            pwdType: 'password'
        }
    },
    methods: {
        showPwd() {
            if (this.pwdType === 'password') {
                this.pwdType = ''
            } else {
                this.pwdType = 'password'
            }
        },
        async login() {
            try {
                let formData = new FormData()
                let name = this.loginForm.admin_name
                let password = this.loginForm.admin_password
                formData.append('admin_name', name)
                formData.append('admin_password', password)
                let data = await login(formData)
                let token = data.token
                let value = {
                    token: token,
                    admin_name: name
                }
                this.$store.commit('LOGIN_IN', value)
                this.$router.replace('/')
            } catch (e) {
                console.log(e)
            }
        }
    }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg: #2d3a4b;
$light_gray: #eee;

/* reset element-ui css */
.login-container {
    .el-input {
        display: inline-block;
        height: 47px;
        width: 90%;
        input {
            background: transparent;
            border: 0px;
            -webkit-appearance: none;
            border-radius: 0px;
            padding: 12px 5px 12px 15px;
            color: $light_gray;
            height: 47px;
            &:-webkit-autofill {
                -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
                -webkit-text-fill-color: #fff !important;
            }
        }
    }
    .el-form-item {
        border: 1px solid rgba(255, 255, 255, 0.1);
        background: rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        color: #454545;
    }
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;
.footer {
    font-size: 26px;
    font-weight: 400;
    color: $light_gray;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
}
.login-container {
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: $bg;
    .login-form {
        position: absolute;
        left: 0;
        right: 0;
        width: 520px;
        padding: 35px 35px 15px 35px;
        margin: 120px auto;
    }
    .tips {
        font-size: 14px;
        color: #fff;
        margin-bottom: 10px;
        span {
            &:first-of-type {
                margin-right: 16px;
            }
        }
    }
    .svg-container {
        padding: 6px 5px 6px 15px;
        color: $dark_gray;
        vertical-align: middle;
        width: 30px;
        display: inline-block;
        &_login {
            font-size: 20px;
        }
    }
    .title {
        font-size: 26px;
        font-weight: 400;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
    }
    .show-pwd {
        position: absolute;
        right: 10px;
        top: 7px;
        font-size: 16px;
        color: $dark_gray;
        cursor: pointer;
        user-select: none;
    }
    .fontcontainer {
        color: #889aa4;
        padding-left: 10px;
    }
}
</style>
