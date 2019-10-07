<template lang="html">
  <el-card class="box-card">
    <div class="search-bar">
      <el-form :inline="true" :model="searchData" class="fl">
        <el-input style="display: none;"></el-input>
        <el-form-item label="search librarian">
          <el-input v-model="searchData.librarian_name" placeholder="name" @keyup.enter.native="onSearch()"></el-input>
        </el-form-item>
      </el-form>
      <div class="fl">
        <!--el-button type="text" @click="handleReset">重置</el-button-->
        <el-button type="primary" icon="el-icon-search" @click="onSearch">search</el-button>
      </div>
    </div>
    <div  class="tools-bar">
      <el-button type="success" icon="el-icon-plus" size="small" @click="dialogVisible = true;dialogTitle='register librarian'">register librarian</el-button>
    </div>
    <div>
      <el-table
        v-loading.body="tableLoading"
        ref="singleTable"
        :data="tableData"
        border
        highlight-current-row
        style="width: 100%">
        <el-table-column
          type="index"
          width="60">
        </el-table-column>
        <el-table-column
        prop="librarian_id"
        label="id"
        width="200">
        </el-table-column>
        <el-table-column
          prop="librarian_name"
          label="name"
          width="200">
        </el-table-column>
        <el-table-column
          prop="librarian_phone"
          label="phone"
          width="200">
        </el-table-column>
        <el-table-column
        prop="auth_admin_name"
        label="authrization admin"
        width="200">
        </el-table-column>
        <el-table-column
          prop="librarian_email"
          label="E-mail"
          min-width="250">
        </el-table-column>
        <el-table-column
          label="operation"
          fixed="right"
          width="200">
          <template slot-scope="scope">
    <div>
        <el-button type="text" size="small" @click="handleEdit(scope.$index, scope.row)">edit</el-button>
        <el-divider direction="vertical"></el-divider>
        <el-button
            type="text"
            size="small"
            @click="handleResetPwd(scope.$index, scope.row)"
        >reset password</el-button>
    </div>
</template>
        </el-table-column>
      </el-table>
      <div class="pagination-bar">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="[10, 25, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalRecord">
        </el-pagination>
      </div>
    </div>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" @close="onDialogClose()">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" label-width="80px">
        <el-form-item label="Name" prop="librarian_name">
          <template v-if="dialogTitle=='edit librarian information'">{{ dataForm.librarian_name }}</template>
          <el-input v-else v-model="dataForm.lirarian_name" placeholder="Name"></el-input>
        </el-form-item>
            <el-form-item label="Phone" prop="mobile">
              <el-input v-model="dataForm.librarian_phone" placeholder="Contact Number"></el-input>
            </el-form-item>
            <el-form-item label="E-mail" prop="email">
              <el-input v-model="dataForm.librarian_email" placeholder="E-mail"></el-input>
            </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">cancel</el-button>
        <el-button type="primary" @click="onDialogSubmit()" v-if="dialogTitle=='edit librarian information'">submit</el-button>
        <el-button type="primary" @click="onDialogSubmit()" v-else>create</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
import { getAllLib } from '@/api/data'
import moment from 'moment'
export default {
    data() {
        return {
            totalRecord: 0,
            pageSize: 10,
            tableLoading: false,
            dialogVisible: false,
            dialogTitle: 'register librarian',
            rules: {
                loginName: [
                    {
                        required: true,
                        message: 'Name can not be null',
                        trigger: 'blur'
                    },
                    {
                        min: 1,
                        max: 50,
                        message: 'The length of name must be 1-50',
                        trigger: 'blur'
                    }
                ],
                mobile: [
                    {
                        required: true,
                        message: 'Contact number can not be null',
                        trigger: 'blur'
                    },
                    {
                        pattern: /^(13|15|18|14|17)[0-9]{9}$/,
                        message: 'The format of contact number is not correct',
                        trigger: 'blur'
                    }
                ],
                email: [
                    {
                        required: true,
                        message: 'Please enter the E-mail address',
                        trigger: 'blur'
                    },
                    {
                        type: 'email',
                        message: 'The format of E-mail is not correct',
                        trigger: 'blur, change'
                    }
                ]
            },
            searchData: {
                librarian_name: ''
            },
            dataForm: {
                librarian_id: '',
                librarian_name: '',
                auth_admin_name: '',
                librarian_phone: '',
                librarian_email: ''
            },
            tableData: []
        }
    },
    created() {
        this.initList()
    },
    methods: {
        async initList() {
            const data = await getAllLib()
            console.log(data)
            this.tableData = data
        },
        handleStatus(row) {},
        // statusFormat(row, column, cellValue) {
        //     return { '0': '停用', '1': '启用' }[cellValue] || ''
        // },
        onDialogClose() {
            this.dataForm.tempRoleIds = []
            this.$refs.dataForm.resetFields()
        },
        handleSizeChange(val) {
            this.pageSize = val
            this.onSearch()
        },
        handleCurrentChange(val) {
            this.onSearch({ pageNumber: val })
        },
        handleReset() {
            this.searchData = {
                librarian_name: ''
            }
            this.onSearch()
        },
        onSearch({ pageNumber = 1 } = {}) {},
        toDateTime(row, column, cellValue) {
            return cellValue
                ? moment(cellValue).format('YYYY-MM-DD HH:mm:ss')
                : ''
        },
        roleFormatter(row, column, cellValue) {
            let result = []
            if (
                typeof row.erpMemberRoles === 'object' &&
                row.erpMemberRoles.length > 0
            ) {
                for (let item of row.erpMemberRoles) {
                    result.push(item.roleName)
                }
            }
            return result.join('，')
        },
        handleChangeStatus(index, row) {},
        handleResetPwd(index, row) {
            this.$confirm('Are you sure to reset the password？', 'warning', {
                confirmButtonText: 'confirm',
                cancelButtonText: 'cancel',
                type: 'warning'
            }).then(() => {})
        },
        handleEdit(index, row) {
            this.dialogVisible = true
            this.dialogTitle = 'edit librarian information'
            this.dataForm.tempRoleIds = []
            for (let x of Object.keys(this.dataForm)) {
                if (
                    x === 'tempRoleIds' &&
                    typeof row.roleList === 'object' &&
                    row.roleList.length > 0
                ) {
                    for (let item of row.roleList) {
                        this.dataForm.tempRoleIds.push(item.id)
                    }
                } else {
                    this.dataForm[x] = row[x]
                }
            }
        },
        onDialogSubmit() {}
    }
}
</script>

<style lang="scss">
.fr {
    float: right;
}
.fl {
    float: left;
}
.search-bar {
    overflow: hidden;
}
</style>

<style>
.tools-bar {
    margin-bottom: 20px;
}
</style>
