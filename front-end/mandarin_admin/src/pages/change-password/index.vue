<!--TODOS add change password functions and change the GUI-->
<template>
    <div class="change-password-container">
        <el-container>
            <el-header>
                <el-card>
                    <el-page-header @back="goBack" content="back to home"></el-page-header>
                </el-card>
            </el-header>
            <el-main>
                <div class="change-form">
                    <el-card>
                        <el-form
                            :model="ruleForm"
                            status-icon
                            :rules="rules"
                            ref="ruleForm"
                            label-width="100px"
                            class="demo-ruleForm"
                        >
                            <el-form-item label="origin password" prop="age">
                                <el-input type="password" v-model.number="ruleForm.age"></el-input>
                            </el-form-item>
                            <el-form-item label="new password" prop="pass">
                                <el-input
                                    type="password"
                                    v-model="ruleForm.pass"
                                    autocomplete="off"
                                ></el-input>
                            </el-form-item>
                            <el-form-item label="check new password" prop="checkPass">
                                <el-input
                                    type="password"
                                    v-model="ruleForm.checkPass"
                                    autocomplete="off"
                                ></el-input>
                            </el-form-item>

                            <el-form-item>
                                <el-button
                                    type="primary"
                                    @click="submitForm('ruleForm',ruleForm)"
                                >submit</el-button>
                                <el-button @click="resetForm('ruleForm')">cancle</el-button>
                            </el-form-item>
                        </el-form>
                    </el-card>
                </div>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import { changePassword } from '@/api/data'
import { Message } from 'element-ui'
export default {
    data() {
        var checkAge = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('Password can not be null'))
            }
            callback()
        }
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('Please enter the new password'))
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass')
                }
                callback()
            }
        }
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('Please enter the new password again'))
            } else if (value !== this.ruleForm.pass) {
                callback(new Error('Two passwords are incosistent'))
            } else {
                callback()
            }
        }
        return {
            ruleForm: {
                age: '',
                pass: '',
                checkPass: ''
            },
            rules: {
                pass: [{ validator: validatePass, trigger: 'blur' }],
                checkPass: [{ validator: validatePass2, trigger: 'blur' }],
                age: [{ validator: checkAge, trigger: 'blur' }]
            }
        }
    },
    methods: {
        submitForm(formName, objectName) {
            this.checkForm(formName).then(chunck => {
                let formData = new FormData()
                formData.append('admin_name', this.$store.state.UserName)
                formData.append('admin_old_password', objectName.age)
                formData.append('admin_new_password', objectName.pass)
                changePassword(formData)
                    .then(chunck => {
                        Message.success('Change password success')
                    })
                    .catch(() => {
                        Message.error('Change password failed')
                    })
            })
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        },
        checkForm(formName) {
            return new Promise((resolve, reject) => {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        resolve(true)
                    } else {
                        Message.error('Format is not correct')
                        this.resetForm(formName)
                    }
                })
            })
        },
        goBack() {
            this.$router.replace('/')
        }
    }
}
</script>
<style rel="stylesheet/scss" lang="scss">
$bg: #2d3a4b;
$light_gray: #eee;

/* reset element-ui css */
.change-password-container {
    .el-input {
        height: 47px;
        width: 90%;
    }
}
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;
.change-password-container {
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: $bg;
}
</style>
