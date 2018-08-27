<template>
  <div class="login-container">
    <el-form class="card-box login-form" autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
      <h3 class="title">系统登录</h3>

      <el-form-item prop="username">
        <span class="svg-container svg-container_login">
          <svg-icon icon-class="user" />
        </span>
        <el-input name="username" type="text" v-model="loginForm.username" autoComplete="on" placeholder="用户名" />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input name="password" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on"
                  placeholder="密码" />
        <span class="show-pwd" @click="showPwd"><svg-icon icon-class="eye" /></span>
      </el-form-item>

      <div class="login-checkbox">
        <el-checkbox v-model="checked" style="margin-bottom: 13px; margin-left: 6px;">记住密码</el-checkbox>
      </div>
      <el-button type="primary" style="width:100%;margin-bottom:16px;" :loading="loading" @click.native.prevent="handleLogin">登录</el-button>

    </el-form>

  </div>
</template>

<script>
    import {  validatAlphabetsAndMunber,validatePassword  } from '@/utils/validate'
    import login from '@/api/login'

    export default {
        name: 'login',
        data() {
            const validateUsername = (rule, value, callback) => {
                callback()
            };
            const validateRealPassword = validatePassword;
            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                loginRules: {
                    username: [{ required: true, trigger: 'blur', validator: validateUsername }],
                    password: [{ required: true, trigger: 'blur', validator: validateRealPassword }]
                },
                pwdType: 'password',
                loading: false,
                showDialog: false,
                checked: false
            }
        },
        created(){
          if (localStorage.hasOwnProperty('username') || localStorage.hasOwnProperty('password')) {
            this.loginForm.username = localStorage.getItem('username');
            this.loginForm.password = localStorage.getItem('password');
          }
          if (localStorage.hasOwnProperty('checked')) {
              this.checked = localStorage.getItem('checked') == '0' ? false : true;
          }
        },
        methods: {
            showPwd() {
                if (this.pwdType === 'password') {
                    this.pwdType = ''
                } else {
                    this.pwdType = 'password'
                }
            },
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        login.login(this.loginForm).then((data) => {
                            let a = data;
                            debugger
                        })
                        // this.$router.push({ path: '/dashboard' })

                        login


                        // this.loading = true
                        // this.$store.dispatch('LoginByUsername', this.loginForm).then((response) => {
                        //     if (response.data.status) {
                        //         this.loading = false
                        //         this.$router.push({ path: '/' })
                        //     } else {
                        //         this.$message.warning("用户名或密码不正确或用户被禁用！");
                        //         this.loading = false
                        //     }
                        //     // this.showDialog = true
                        // }).catch(() => {
                        //     this.loading = false
                        // })

                        // 实现记住密码
                        if (this.checked) {
                            localStorage.setItem('username', this.loginForm.username);
                            localStorage.setItem('password', this.loginForm.password);
                        } else {
                            localStorage.removeItem('username');
                            localStorage.removeItem('password');
                        }
                        localStorage.setItem('checked', this.checked ? "1" : "0");
                    } else {
                        console.log('error submit!!');
                        return false
                    }
                })
            }
        }
    }
</script>

<style rel="stylesheet/scss" lang="scss">
  @import "../../styles/mixin.scss";
  // $bg:#2d3a4b;
  $bg:#2D2D30;
  $dark_gray:#889aa4;
  $light_gray:#eee;

  .login-container {
    @include relative;
    height: 94.4vh;
    background-color: $bg;
    input:-webkit-autofill {
      -webkit-box-shadow: 0 0 0px 1000px #293444 inset !important;
      -webkit-text-fill-color: #fff !important;
    }
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
    }
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
    }
    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;
    }
    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
      &_login {
        font-size: 20px;
      }
    }
    .title {
      font-size: 26px;
      font-weight: 400;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 400px;
      /*padding: 0px 35px 15px 35px;*/
      margin: 120px auto;
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select:none;
    }
    .thirdparty-button{
      position: absolute;
      right: 35px;
      bottom: 28px;
    }
    .login-checkbox .el-checkbox{
      color: white;
    }
    .login-checkbox .el-checkbox__label{
      font-size: 12px;
    }
  }
</style>
