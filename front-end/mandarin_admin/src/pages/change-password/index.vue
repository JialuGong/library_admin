<!--TODOS add change password functions and change the GUI-->
<template>
    <div class="change-password-container">
        <el-container>
            <el-header></el-header>
            <el-main>
                <el-row>
                    <el-col :span="8">
                        <div>&nbsp</div>
                    </el-col>
                    <el-col :span="8">
                        <div class="grid-content bg-purple-light"></div>
                        <div class="change-form">
                            <el-card>
                                <div class="card-box">
                                    <el-form
                                        :model="ruleForm"
                                        status-icon
                                        :rules="rules"
                                        ref="ruleForm"
                                        label-width="150px"
                                        class="demo-ruleForm"
                                    >
                                        <el-form-item>
                                            <img class="user-img" :src="avatar" alt />
                                            <a href>
                                                <span class="user-name">{{account}}</span>
                                            </a>
                                        </el-form-item>
                                        <el-divider></el-divider>
                                        <el-form-item
                                            label="origin password"
                                            prop="age"
                                            class="input-form"
                                        >
                                            <el-input type="password" v-model.number="ruleForm.age"></el-input>
                                        </el-form-item>
                                        <el-form-item label="new password" prop="pass">
                                            <el-input
                                                type="password"
                                                v-model="ruleForm.pass"
                                                autocomplete="off"
                                            ></el-input>
                                        </el-form-item>
                                        <el-form-item label="check password" prop="checkPass">
                                            <el-input
                                                type="password"
                                                v-model="ruleForm.checkPass"
                                                autocomplete="off"
                                            ></el-input>
                                        </el-form-item>
                                    </el-form>
                                    <div class="btn">
                                        <el-button
                                            type="primary"
                                            @click="submitForm('ruleForm',ruleForm)"
                                        >submit</el-button>
                                        <el-button @click="resetForm('ruleForm')">cancel</el-button>
                                    </div>
                                </div>
                            </el-card>
                        </div>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>
<script>
import { changePassword } from '@/api/data'
import { Message } from 'element-ui'
import { mapState } from 'vuex'
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
            },
            account: this.$store.state.AdminName
        }
    },
    computed: {
        ...mapState('permission', ['avatar'])
    },
    methods: {
        submitForm(formName, objectName) {
            this.checkForm(formName).then(chunck => {
                let formData = new FormData()
                formData.append('admin_name', this.$store.state.AdminName)
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
.user-img {
    width: 100px;
    height: 100 px;
    border-radius: 50%;
    vertical-align: middle;
    margin: 30px;
}
.user-name {
    color: #758eb5;
    padding: 0 8px;
    font-size: 30px;
    margin: 20px;
    text-decoration: underline;
}
.input-form {
    margin-top: 50px;
}
.card-box {
    .btn {
        align-content: center;
        margin-left:200px;
        margin-bottom: 100px;
    }
}
</style>
