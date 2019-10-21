<template>
    <!-- TODOS 添加change password的页面-->
    <el-card>
        <div class="passwordBox">
            <el-input
                placeholder="Please input the origin password"
                v-model="oldPassword"
                show-password
            ></el-input>
            <el-input
                placeholder="Please input the new password"
                v-model="newPassword_1"
                show-password
            ></el-input>
            <el-input
                placeholder="Please input the new password again"
                v-model="newPassword_2"
                show-password
            ></el-input>
        </div>
    </el-card>
</template>
<script>
import { changePassword } from '@/api/data'
export default {
    data() {
        return {
            oldPassword: '',
            newPassword_1: '',
            newPassword_2: ''
        }
    },
    methods: {}
}
</script>
