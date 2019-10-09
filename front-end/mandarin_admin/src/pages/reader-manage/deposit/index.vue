<template>
    <el-card>
        <div class="time-line-bar">
            <el-divider>The last three modify records</el-divider>
            <el-timeline>
                <el-timeline-item
                    v-loading.body="actLoading"
                    :data="activities"
                    v-for="(activity, index) in activities"
                    :key="index"
                    :timestamp="activity.timestamp"
                >{{activity.reader_deposit}}</el-timeline-item>
            </el-timeline>
        </div>
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
    </el-card>
</template>

<script>
import { getReaderDeposit, modifyReaderDeposit } from '@/api/data'
export default {
    data() {
        return {
            actLoading: false,
            activities: [],
            numberValidateForm: {
                deposit: ''
            }
        }
    },
    created() {
        console.log('function begin to create')
        this.initList()
    },
    methods: {
        async initList() {
            var data = await getReaderDeposit()
            this.activities = data
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
            if (this.submitForm(objectName)) {
                var formaData = new FormData()
                var data = formName.deposit
                var date = this.getDate()
                formaData.append('reader_deposit', data)
                formaData.append('timestamp', date)
                modifyReaderDeposit(formaData).then(chunck => {
                    console.log(chunck)
                    console.log(chunck.data)
                    if (chunck) {
                        this.activities = chunck
                        alert('modify success')
                    } else {
                        alert('modify failed')
                    }
                })
            }
        },
        getDate() {
            var myDate = new Date()
            var year = myDate.getFullYear()
            var month = myDate.getMonth()
            var day = myDate.getDate()
            return year + '-' + month + '-' + day
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
