<template>
    <div>
        <el-menu class="navbar" mode="horizontal">
            <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>

            <breadcrumb class="breadcrumb-container"></breadcrumb>

            <div class="right-menu">
                <error-log v-if="errorLogs.length>0" class="errLog-container right-menu-item" :logsList="errorLogs"></error-log>

                <el-dropdown class="avatar-container right-menu-item" trigger="click">
                    <div class="avatar-wrapper">
                        <!--img class="user-avatar" :src="avatar+'?imageView2/1/w/80/h/80'"-->
                        <img class="user-avatar" src="static/default.jpg?imageView2/1/w/80/h/80">
                        <i class="el-icon-caret-bottom"></i>
                    </div>
                    <el-dropdown-menu slot="dropdown">
                        <router-link to="/">
                            <el-dropdown-item>
                                首页
                            </el-dropdown-item>
                        </router-link>

                        <el-dropdown-item >
                            <span @click="dialogFormVisible = true">修改密码</span>
                        </el-dropdown-item>

                        <el-dropdown-item divided>
                            <span @click="logout" style="display:block;">退出登录</span>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>

                <el-dropdown class="avatar-container right-menu-item" trigger="click">
                    <div class="i-wrapper">
                        <i class="fa fa-gears"></i>
                    </div>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                            <el-row>
                                <el-col :span="6">主题</el-col>
                                <el-col :span="18">
                                    <el-select v-model="themeType" placeholder="请选择" size="mini">
                                        <el-option
                                                v-for="item in themes"
                                                :key="item.headerTheme"
                                                :label="item.label"
                                                :value="item.headerTheme"
                                                :disabled="item.disabled">
                                        </el-option>
                                    </el-select>
                                    <!--<el-radio v-model="radio" v-for="item in themes" label="item.value">{{item.label}}</el-radio>-->
                                </el-col>
                            </el-row>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-menu>

        <!-- 修改密码弹窗 -->
        <el-dialog title="密码修改" :visible.sync="dialogFormVisible">
            <el-form class="small-space" :model="passwordForm"  :rules="passwordFormRules" ref="passwordForm" label-position="right" label-width="100px" style='width: 400px; margin-left:50px;'>

                <el-form-item label="原密码" prop="oldPassword" >
                    <el-input type="password" v-model="passwordForm.oldPassword" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="新密码" prop="newPassword">
                    <el-input type="password" v-model="passwordForm.newPassword" auto-complete="off"></el-input>
                </el-form-item>

                <el-form-item label="重复新密码" prop="newPassword2">
                    <el-input type="password" v-model="passwordForm.newPassword2" auto-complete="off"></el-input>
                </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>

                <el-button type="primary" @click="handlePwdModifySubmit(passwordForm)">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { mapGetters} from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import ErrorLog from '@/components/ErrorLog'
export default {
    name: "nav-bar",
    components: {
        Breadcrumb,
        Hamburger,
        ErrorLog
    },
    data() {
        // 验证用户密码是否正确
        const validateOldPassword = (rule, value, callback) => {
            var user = {
                username: this.$store.getters.name,
                password: value
            }
        };
        const validateNewPassword2 = (rule, value, callback) => {
            if (value !== this.passwordForm.newPassword) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        // 输入的密码 跟登入密码验证方式一致
        const validateRealPassword = validatePassword;
        return {
            dialogFormVisible:false,
            themeType: {}, // 用户选择使用的颜色
            themes,
            passwordForm: {
                "oldPassword":'',
                "newPassword":'',
                "newPassword2":'',
            },
            passwordFormRules: {
                oldPassword: [
                    { required: true, trigger: 'blur', message: '旧密码不能为空！'},
                    // { required: true, trigger: 'blur' , validator: validateOldPassword}

                ],
                newPassword: [
                    { required: true, trigger: 'blur', message: '新密码不能为空！'},
                    // { required: true, trigger: 'blur' , validator: validateRealPassword}
                ],
                newPassword2: [
                    { required: true, trigger: 'blur', message: '重复密码不能为空！'},
                    // { required: true, trigger: 'blur' , validator: validateNewPassword2}
                ]
            },
        }
    },
    computed: {
        ...mapGetters([
            'sidebar',
            'name',
            'avatar',
            'language',
            'errorLogs'
        ])
    },
    watch: {
        // 监视themeType属性
        themeType(val) {
            let theme = this.themes.find(e => e.headerTheme === val) || {}
            this.$emit("change-theme", theme);
            localStorage.setItem('theme', val);
        }
    },
    methods: {
        toggleSideBar() {
            this.$store.dispatch('toggleSideBar')
        },
        /**
         * 修改密码保存
         * @param formName
         */
        handlePwdModifySubmit(formName){
            this.$refs.passwordForm.validate(valid => {
                if (valid) {
                    var user = {
                        username: this.$store.getters.name,
                        password: this.passwordForm.newPassword
                    }
                    // modifyPassword(user).then(response => {
                    //    if (response.data.status) {
                    //        this.$message.success('密码设置成功');
                    //    } else {
                    //        this.$message.success('密码设置失败');
                    //    }
                    //    this.dialogFormVisible = false;
                    // });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        logout() {
            this.$store.dispatch('LogOut').then(() => {
                this.$router.push({ path: '/login' })
                // location.reload()// 为了重新实例化vue-router对象 避免bug

            })
        }
    }
}
</script>

<style scoped>

</style>