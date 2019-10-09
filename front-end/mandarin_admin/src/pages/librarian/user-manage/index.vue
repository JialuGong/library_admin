<template lang="html">
  <el-card class="box-card">
    <div class="search-bar">
      <el-form :inline="true" :model="searchData" class="fl">
        <el-input style="display: none;"></el-input>
        <el-form-item label="search librarian">
          <el-input v-model="searchData.librarian_name" placeholder="name" @keyup.enter.native="onSearch(searchData.librarian_name,'searchData')"></el-input>
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
            @click="handleDelete(scope.$index, scope.row)"
        >delete</el-button>
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
        <!-- <el-form-item label="Name" prop="librarian_name">
          <template v-if="dialogTitle=='edit librarian information'">{{ tableData.librarian_name }}</template>
          <el-input v-else v-model="dataForm.lirarian_name" placeholder="Name"></el-input>
        </el-form-item> -->
            <el-form-item label="Name" prop="tabName">
              <el-input v-model="dataForm.tabName" placeholder="librarian name"></el-input>
            </el-form-item>
            <el-form-item label="Password" prop="tabPassword">
              <el-input  v-model="dataForm.tabPassword" placeholder="password"></el-input>
            </el-form-item>
            <el-form-item label="Phone" prop="tabPhone">
              <el-input v-model="dataForm.tabPhone" placeholder="Contact Number"></el-input>
            </el-form-item>
            <el-form-item label="E-mail" prop="tabEmail">
              <el-input v-model="dataForm.tabEmail" placeholder="E-mail"></el-input>
            </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">cancel</el-button>
        <el-button type="primary" @click="onDialogSubmit()" v-if="dialogTitle=='edit librarian information'">submit</el-button>
        <el-button type="primary" @click="onDialogRegisterSubmit()" v-else>create</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
import {
    getAllLib,
    searchLib,
    deleteLib,
    editLib,
    registerLib
} from '@/api/data'
// import moment from 'moment'
export default {
    data() {
        return {
            totalRecord: 0,
            pageSize: 10,
            tableLoading: false,
            dialogVisible: false,
            dialogTitle: 'register librarian',
            rules: {
                tabName: [
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
                tabPassword: [
                    {
                        required: true,
                        message: 'password can not be null',
                        trigger: 'blur'
                    },
                    {
                        min: 1,
                        max: 50,
                        message: 'The length of password must be 1-50',
                        trigger: 'blur'
                    },
                    {
                        pattern: /^[a-zA-Z]\w{5,17}$/,
                        message: 'The format of password is not correct',
                        trigger: 'blur'
                    }
                ],
                tabPhone: [
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
                tabEmail: [
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
                tabID: '',
                tabName: '',
                tabPassword: '',
                tabPhone: '',
                tabEmail: ''
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
            this.tableData = data
        },
        showPwd() {
            if (this.pwdType === 'password') {
                this.pwdType = ''
            } else {
                this.pwdType = 'password'
            }
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
        // onSearch({ pageNumber = 1 } = {}) {},
        // toDateTime(row, column, cellValue) {
        //     return cellValue
        //         ? moment(cellValue).format('YYYY-MM-DD HH:mm:ss')
        //         : ''
        // },

        // for search function
        async onSearch(data, objectName) {
            var formData = new FormData()
            var name = data
            formData.append('libririan_name', name)
            if (this.getName(objectName)) {
                var chunck = await searchLib(formData)

                if (chunck) {
                    this.tableData = chunck
                } else {
                    alert('Name not found')
                }
            }
        },
        async getName(obejectName) {
            // var chunck = await this.$refs[obejectName].validate()
            // return chunck
            return true
        },

        // for role
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

        // for delete function
        handleDelete(index, row) {
            this.$confirm('Are you sure to delte the librarian ？', 'warning', {
                confirmButtonText: 'confirm',
                cancelButtonText: 'cancel',
                type: 'warning'
            }).then(async () => {
                var id = row.id
                var submitData = new FormData()
                submitData.append('librarian_id', id)
                var chunck = await deleteLib(submitData)
                if (chunck) {
                    alert('Delete success')
                    this.tableData = this.initList()
                } else {
                    alert('Delete failed')
                }
            })
        },
        handleEdit(index, row) {
            this.dialogVisible = true
            this.dialogTitle = 'edit librarian information'
            var value = {
                tabID: row.librarian_id,
                tabName: row.librarian_name,
                tabPassword: row.librarian_password,
                tabPhone: row.librarian_phone,
                tabEmail: row.librarian_email
            }
            this.dataForm = value
            // console.log(row.librarian_name)
            // this.dataForm.tempRoleIds = []
            // for (let x of Object.keys(this.dataForm)) {
            //     if (
            //         x === 'tempRoleIds' &&
            //         typeof row.roleList === 'object' &&
            //         row.roleList.length > 0
            //     ) {
            //         for (let item of row.roleList) {
            //             this.dataForm.tempRoleIds.push(item.id)
            //         }
            //     } else {
            //         this.dataForm[x] = row[x]
            //     }
            // }
        },
        async onDialogSubmit() {
            var submitData = new FormData()
            submitData.append('librarian_id', this.dataForm.tabID)
            submitData.append('librarian_name', this.dataForm.tabName)
            submitData.append('librarian_password', this.dataForm.tabPassword)
            submitData.append('librarian_phone', this.dataForm.tabPhone)
            submitData.append('librarian_email', this.dataForm.tabEmail)
            var chunck = await editLib(submitData)
            if (chunck) {
                alert('edit success')
                this.tableData = this.initList()
            } else {
                alert('edit failed')
            }
        },
        async onDialogRegisterSubmit() {
            var submitData = new FormData()
            submitData.append('librarian_id', this.dataForm.tabID)
            submitData.append('librarian_name', this.dataForm.tabName)
            submitData.append('librarian_password', this.dataForm.tabPassword)
            submitData.append('librarian_phone', this.dataForm.tabPhone)
            submitData.append('librarian_email', this.dataForm.tabEmail)
            var chunck = await registerLib(submitData)
            if (chunck) {
                alert('add  success')
                this.tableData = this.initList()
            } else {
                alert('add failed')
            }
        }
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
