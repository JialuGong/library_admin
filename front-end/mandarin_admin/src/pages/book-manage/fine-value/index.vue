<template>
    <el-card>
        <div class="time-line-bar">
            <el-divider>The last three modify records</el-divider>
            <el-timeline>
                <el-timeline-item
                    v-for="(activity, index) in activities"
                    :key="index"
                    :timestamp="activity.timestamp"
                >{{activity.book_fine_value}}</el-timeline-item>
            </el-timeline>
        </div>
        <div class="modify-value-bar">
            <el-divider>Modify the penalty</el-divider>
            <el-form
                :model="numberValidateForm"
                ref="numberValidateForm"
                label-width="100px"
                class="demo-ruleForm"
            >
                <el-form-item
                    label="new value"
                    prop="value"
                    :rules="[
                    {validator:checkFine,tigger:'blur'}
     //{ required: false, message: 'value can not be null'},
      //{ type: 'number', message: 'value must be number'}
    ]"
                >
                    <el-input
                        suffix-icon="iconfont icon-sousuo"
                        type="value"
                        v-model.number="numberValidateForm.value"
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
    </el-card>
</template>

<script>
import { getBookFine, modifyBookFine } from '@/api/data'
export default {
    data() {
        return {
            activities: [],
            numberValidateForm: {
                value: ''
            }
        }
    },
    created() {
        this.initlist()
    },
    methods: {
        checkFine(rule, value, callback) {
            const pattern = /^[0-9]+(.[0-9]{1,2})?$/
            if (!value) {
                return callback(new Error('Fine value can not be null'))
            } else {
                if (!pattern.test(value)) {
                    return callback(
                        new Error(
                            'Please enter the correct value.For example 1 1.2 4.23'
                        )
                    )
                } else {
                    callback()
                }
            }
        },
        async initlist() {
            var chunck = await getBookFine()
            this.activities = chunck
        },
        async postForm(formName, objectName) {
            if (this.submitForm(objectName)) {
                var formData = new FormData()
                var data = formName.value
                var date = this.getDate()
                formData.append('reader_fine_value', data)
                formData.append('timestamp', date)
                var chunck = await modifyBookFine(formData)
                if (chunck) {
                    alert('modify success')
                    this.activities = chunck
                } else {
                    alert('modify failed')
                }
            }
        },

        // 获取当前时间
        getDate() {
            var myDate = new Date()
            var year = myDate.getFullYear()
            var month = myDate.getMonth()
            var day = myDate.getDate()
            return year + '-' + month + '-' + day
        },

        // 检查input内的值是否可以被传输
        async submitForm(objectName) {
            var result = await this.$refs[objectName].validate()
            return result
        },

        // 光标重新设置
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>
