<template>
    <el-card>
        <div class="modify-period-bar">
            <el-divider>Modify the return period</el-divider>
            <el-form
                :model="numberValidateForm"
                ref="numberValidateForm"
                label-width="100px"
                class="demo-ruleForm"
            >
                <el-form-item
                    label="new period"
                    prop="period"
                    :rules="[
                    {validator:checkPeriod,trigger:'blur'}
      //{ required: false, message: 'value can not be null'},
      //{ type: 'number', message: 'value must be number'}
    ]"
                >
                    <el-input
                        suffix-icon="iconfont icon-sousuo"
                        type="number"
                        v-model.number="numberValidateForm.period"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="postForm('numberValidateForm',numberValidateForm)"
                    >submit</el-button>
                    <el-button @click="resetForm('numberValidateForm')">restore</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="time-line-bar">
            <el-divider>The modify records</el-divider>
            <el-timeline>
                <el-timeline-item
                    v-for="(activity, index) in activities"
                    :key="index"
                    :timestamp="activity.timestamp"
                >{{activity.book_period}} days</el-timeline-item>
            </el-timeline>
        </div>
    </el-card>
</template>

<script>
import { getBookPeriod, modifyBookPeriod } from '@/api/data'
import { Message } from 'element-ui'
import { getDate } from '@/utils/time-stamp'
export default {
    data() {
        return {
            activities: [],
            numberValidateForm: {
                period: 30
            }
        }
    },
    created() {
        this.initlist()
    },
    methods: {
        checkPeriod(rule, value, callback) {
            const pattern = /^[0-9]*$/
            if (!value) {
                return callback(new Error('Return-period can not be null'))
            } else {
                if (!pattern.test(value)) {
                    return callback(
                        new Error(
                            'Please enter the correct value.For exmample 24'
                        )
                    )
                } else {
                    callback()
                }
            }
        },
        async initlist() {
            let chunck = await getBookPeriod()
            this.activities = chunck
        },
        postForm(formName, objectName) {
            this.checkForm(formName).then(chunck => {
                let formData = new FormData()
                let data = objectName.period
                let date = getDate()
                formData.append('book_period', data)
                formData.append('timestamp', date)
                modifyBookPeriod(formData).then(chunck => {
                    getBookPeriod().then(chunck => {
                        this.activities = chunck
                        Message.success('Modify success')
                    })
                })
            })
        },
        // varifyRule(formName, objectName) {
        //     let data = formName.period
        //     let pattern = /^[0-9]*$/
        //     if (pattern.test(data)) {
        //         return true
        //     } else {
        //         this.$refs[objectName].resetFields()
        //         Message.warning('Format is not correct')
        //         return false
        //     }
        // },
        async submitForm(formName) {
            let result = await this.$refs[formName].validate()
            return result
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        },
        checkForm(formName) {
            console.log(`chechForm function excute`)
            return new Promise((resolve, reject) => {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        resolve(true)
                    } else {
                        Message.warning('Format is not correct')
                        this.resetForm(formName)
                    }
                })
            })
        }
    }
}
</script>
