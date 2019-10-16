<template>
    <el-card>
        <div class="modify-period-bar">
            <el-divider>Modify the deposit</el-divider>
            <el-form
                :model="numberValidateForm"
                ref="numberValidateForm"
                label-width="100px"
                class="demo-ruleForm"
            >
                <el-form-item
                    label="new deposit"
                    prop="deposit"
                    :rules="[
                {validator:checkDeposit,trigger:'blur'}
      //{ required: false, message: 'Deposit can not be null'},
      //{ type: 'number', message: 'Deposit must be number'}
    ]"
                >
                    <el-input
                        suffix-icon="iconfont icon-sousuo"
                        placeholder="Please enter the new deposit"
                        type="number"
                        v-model.number="numberValidateForm.deposit"
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
            <el-divider>The modify records</el-divider>
            <el-timeline>
                <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
                    <el-timeline-item
                        v-loading.body="actLoading"
                        :data="activities"
                        v-for="(activity, index) in activities"
                        :key="index"
                        :timestamp="activity.timestamp"
                    >￥ {{activity.reader_deposit}}</el-timeline-item>
                </ul>
            </el-timeline>
        </div>
    </el-card>
</template>

<script>
import { getReaderDeposit, modifyReaderDeposit } from '@/api/data'
import { getDate } from '@/utils/time-stamp'
import { Message } from 'element-ui'
export default {
    data() {
        return {
            actLoading: false,
            activities: [],
            numberValidateForm: {
                deposit: '300'
            }
        }
    },
    created() {
        console.log('function begin to create')
        this.initList()
    },
    methods: {
        async initList() {
            let data = await getReaderDeposit()
            this.activities = data
            console.log(this.activities.length)
        },
        async getList() {
            let data = await getReaderDeposit()
            return data
        },
        checkDeposit(rule, value, callback) {
            const pattern = /^[0-9]+(.[0-9]{1,2})?$/
            if (!value) {
                return callback(new Error('Deposit can not be null'))
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
        postForm(formName, objectName) {
            if (
                this.submitForm(objectName) &&
                this.varifyRule(formName, objectName)
            ) {
                let formaData = new FormData()
                let data = formName.deposit
                let date = getDate()
                formaData.append('reader_deposit', data)
                formaData.append('timestamp', date)
                modifyReaderDeposit(formaData).then(chunck => {
                    getReaderDeposit().then(chunck => {
                        this.activities = chunck
                        Message.success('Modify success')
                    })
                })
            }
        },
        varifyRule(formName, objectName) {
            let data = formName.deposit
            let pattern = /^[0-9]+(.[0-9]{1,2})?$/
            if (pattern.test(data)) {
                return true
            } else {
                this.$refs[objectName].resetFields()
                Message.warning('Format is not correct')
                return false
            }
        },
        async submitForm(objectName) {
            var result = await this.$refs[objectName].validate()
            return result
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>
<style>
.el-scrollbar__wrap {
    overflow-x: hidden;
}
</style>
