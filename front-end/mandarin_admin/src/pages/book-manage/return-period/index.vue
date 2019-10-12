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
                        @click="postForm(numberValidateForm,'numberValidateForm')"
                    >submit</el-button>
                    <el-button @click="resetForm('numberValidateForm')">restore</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="time-line-bar">
            <el-divider>The last three modify records</el-divider>
            <el-timeline>
                <el-timeline-item
                    v-for="(activity, index) in activities"
                    :key="index"
                    :timestamp="activity.timestamp"
                >{{activity.book_period}}</el-timeline-item>
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
                period: ''
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
            var chunck = await getBookPeriod()
            this.activities = chunck
        },
        postForm(formName, objectName) {
            if (this.submitForm(objectName)) {
                var formData = new FormData()
                var data = formName.period
                var date = getDate()
                formData.append('book_period', data)
                formData.append('timestamp', date)
                modifyBookPeriod(formData).then(chunck => {
                    Message.success('Modify success')
                })
            }
        },
        async submitForm(formName) {
            var result = await this.$refs[formName].validate()
            return result
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>
