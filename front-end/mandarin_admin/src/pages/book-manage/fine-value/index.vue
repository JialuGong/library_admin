<!--TODOS  1、添加滚动条 2、思考如何完成双向绑定-->
<template>
    <el-card>
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
        <div class="time-line-bar">
            <el-divider>Modify records</el-divider>
            <el-timeline>
                <el-timeline-item
                    v-for="(activity, index) in activities"
                    :key="index"
                    :timestamp="activity.timestamp"
                >{{activity.book_fine_value}}</el-timeline-item>
            </el-timeline>
        </div>
    </el-card>
</template>

<script>
import { getBookFine, modifyBookFine } from '@/api/data'
import { Message } from 'element-ui'
import { getDate } from '@/utils/time-stamp'
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
            let chunck = await getBookFine()
            this.activities = chunck
        },
        // getNewList() {
        //     getBookFine(chunck => {
        //         console.log(`this chunck is ${chunck}`)
        //         this.activities = chunck
        //     })
        // },
        postForm(formName, objectName) {
            if (
                this.submitForm(objectName) &&
                this.varifyRule(formName, objectName)
            ) {
                let formData = new FormData()
                let data = formName.value
                let date = getDate()
                formData.append('book_fine_value', data)
                formData.append('timestamp', date)
                modifyBookFine(formData).then(chunck => {
                    getBookFine().then(chunck => {
                        this.activities = chunck
                        Message.success('Modify success')
                    })
                })
            }
        },
        varifyRule(formName, objectName) {
            let data = formName.value
            let pattern = /^[0-9]+(.[0-9]{1,2})?$/
            console.log(`data is ${data} and rule is ${pattern.test(data)}`)
            if (pattern.test(data)) {
                return true
            } else {
                this.$refs[objectName].resetFields()
                Message.warning('Format is not correct')
                return false
            }
        },
        // 检查input内的值是否可以被传输
        async submitForm(objectName) {
            let result = await this.$refs[objectName].validate()
            console.log(`submit result is ${result}`)
            return result
        },

        // 光标重新设置
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>
