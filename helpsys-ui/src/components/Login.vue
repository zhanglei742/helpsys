<template>
  <div class="container">
    <el-form ref="Loginform" :model="Loginform" :rules="rules" class="login-container">
      <el-form-item  label-width="80px" class="login-page">
      <h2>社交互助系统</h2>
        <el-form-item label="账  号:" prop="username">
            <el-input v-model="Loginform.username" placeholder="请输入手机号">
              <el-button slot="prepend" icon="el-icon-user"></el-button>
            </el-input>
            </el-form-item>
        <el-form-item label="密 码:" prop="password">
            <el-input v-model="Loginform.password" type="password" placeholder="请输入密码" @keyup.enter.native="submit">
              <el-button slot="prepend" icon="el-icon-key"></el-button>
            </el-input>
            </el-form-item>
          <el-checkbox v-model="checked" checked  class="remember">记住用户名</el-checkbox>
        <el-form-item style="margin-bottom:-15px">
            <el-button type="primary" icon="el-icon-user-solid" @click.native.prevent="submit">登 录</el-button>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <el-button type="primary" icon="el-icon-user-solid" @click.native.prevent="openFindPassword">注 册</el-button>
          </el-form-item>
        <br>
        <el-button type="text" @click="openFindMobile">&nbsp;&nbsp;&nbsp;手机验证码登录</el-button>
      </el-form-item>
    </el-form>

    <!-- 注册dialog   -->
    <el-dialog  :visible.sync="findPasswordDialogVisible"
               :close-on-click-modal='false' :show-close="false">
      <el-form :model="RegisterForm" :rules="rulesRegister" ref="RegisterForm">
        <el-form-item label="手机号:" :label-width="formLabelWidth" prop="mobile" >
          <el-input v-model.trim="RegisterForm.mobile" style="width:20vw;" placeholder="手机号" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="验证码:"prop="verifyCode" :label-width="formLabelWidth" >
          <el-input v-model.trim="RegisterForm.verifyCode" style="width:20vw;" autocomplete="off" placeholder="验证码"></el-input>
          <el-button type="button" @click="sendcode" :disabled="disabled" v-if="disabled==false">发送验证码
          </el-button>
          <el-button type="button" @click="sendcode" :disabled="disabled" v-if="disabled==true">{{btntxt}}
          </el-button>
        </el-form-item>
        <el-form-item label="密码:" :label-width="formLabelWidth" prop="password" >
          <el-input v-model.trim="RegisterForm.password" show-password type="password" style="width:20vw;"
                    placeholder="重置密码" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="请重输密码:" :label-width="formLabelWidth" prop="passwordTwo" >
          <el-input v-model.trim="RegisterForm.passwordTwo" show-password type="password" style="width:20vw;"
                    placeholder="确认密码"  autocomplete="off" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="findPasswordDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitRegister">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 手机号登录   -->
    <el-dialog  :visible.sync="findMobileDialogVisible"
                :close-on-click-modal='false' :show-close="false">
      <el-form :model="MobileForm" :rules="rulesMobile" ref="MobileForm">
        <el-form-item label="手机号:" :label-width="formLabelWidth" prop="mobile" >
          <el-input v-model.trim="MobileForm.mobile" style="width:20vw;" placeholder="手机号" autocomplete="off" clearable></el-input>
        </el-form-item>
        <el-form-item label="验证码:"prop="verifyCode" :label-width="formLabelWidth" >
          <el-input v-model.trim="MobileForm.verifyCode" style="width:20vw;" autocomplete="off" placeholder="验证码"></el-input>
          <el-button type="button" @click="sendcode" :disabled="disabled" v-if="disabled==false">发送验证码
          </el-button>
          <el-button type="button" @click="sendcode" :disabled="disabled" v-if="disabled==true">{{btntxt}}
          </el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="findMobileDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitMobile">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  export default {
    data() {
      //密码格式
      var checkPassword = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空'));
        } else if (value.length < 6 || value.length > 16) {
          return callback(new Error('密码长度6-16位,必须包含大写字母、小写字母、特殊字符、数字其中三类'));
        } else if (!/^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]/.test(value)) {
          return callback(new Error('密码长度6-16位,必须包含大写字母、小写字母、特殊字符、数字其中三类'));
        } else {
          callback();
        }
      };
      //密码一致性
      var validatePasswordTwo = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.resetPwdForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        //账号密码
        Loginform: {
          username: "",
          password: ""
        },
        //注册和手机号登陆dialog
        findPasswordDialogVisible:false,
        findMobileDialogVisible:false,
        //注册
        RegisterForm:{
          mobile:"",
          verifyCode:"",
          password:"",
          passwordTwo:""
        },
        //手机号登陆
        MobileForm:{
          mobile:"",
          verifyCode:"",
        },
        disabled: false,
        //账号密码登录 规则
        rules:{
          username: [{ required: true, pattern: /^[0-9]{11}$/, message: "请输入11位手机号", trigger: "blur" }],
          password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        },
        //注册 规则
        rulesRegister:{
          password: [
            {required: true, validator: checkPassword, trigger: 'change'}
          ],
          verifyCode:[{required:true, message: "请输入验证码",trigger: 'change'}],
          passwordTwo:[
            {required: true, validator: validatePasswordTwo, trigger: 'change'}
          ],
          mobile: [
            {required: true,
              pattern: /^[0-9]{11}$/, message: "请输入11位手机号",
              trigger: "blur"
            }
          ],
        },
        rulesMobile:{
          mobile: [{required: true, pattern: /^[0-9]{11}$/, message: "请输入11位手机号", trigger: "blur"}],

        },
        checked: false
      };
    },
    methods: {
      submit: function () {
        if(this.Loginform.username == ''||this.Loginform.password == ''){
          this.$alert('请填写账号和密码！', '提示', {
            dangerouslyUseHTMLString: true
          });
        }else {
          let para = {
            "userAccount": this.Loginform.username,
            "userPass": this.Loginform.password,};
          


        }
      },
      //注册dialog
      openFindPassword(){
        this.findPasswordDialogVisible = true;
        this.$nextTick(()=>{
          this.$refs.RegisterForm.resetFields();
        })
      },
      //手机号登录
      openFindMobile(){
        this.findMobileDialogVisible = true;
        this.$nextTick(()=>{
          this.$refs.MobileForm.resetFields();
        })
      }
    }
};
</script>
<style scoped>
  .container {
    margin-top: 100px;
    width: 100%;
    height: 100%;

  }
  .remember {
    margin: 0px 0px 35px 0px;
    color: #1d8ce0;
  }

  .login-page {
    border-radius: 5px;
    margin: auto;
    width: 350px;
    padding: 10px 55px 45px;
    background: #fff;
    border: 3px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
</style>
