<template>
<div class="mlogin">
<div class="main">
    <!--<img :src='logo' style="width:70px; height:70px;margin-right:220px;margin-top:10px;">
    <p style="margin-left:40px;margin-top:-35px;color:white;">欢迎使用图书管理系统</p>--> 
    <div class="head">
       <img :src='logo' style="width:50px; height:50px;margin-right:15px;margin-left:30px;float:left">
       <div style="float:left">自习室座位系统</div>
    </div>
     <div class="loginbox">
           <br>
           <!--<img :src='usericon' style="width:30px; height:30px;">    -->
           <input type="text" class="textbox" v-model="loginForm.username" placeholder="请输入用户名"/>
           <br><br>
           <!--<img :src='passwordicon' width="30px" height="30px" alt="">   -->
           <input type="password" class="textbox" v-model="loginForm.password" placeholder="请输入密码"/>
           <br><br>
           <button v-on:click="login" style="border-radius:5px;width:200px;height:40px;border-color:white;font-size:16px">登&emsp;录</button>
       </div>
  </div>
</div>
  
</template>

<script>

  export default {
    name: 'Login',
    data () {
      return {
        loginForm: {
          username: '',
          password: ''
        },
        responseResult: [],
        logo:require("../assets/img/logoicon.png"),
        usericon:require("../assets/img/user.png"),
        passwordicon:require("../assets/img/password.png"),
      }
    },
    methods: {
login () {
  var _this = this
  console.log(this.$store.state)
  this.$axios
    .post('/login', {
      username: this.loginForm.username,
      password: this.loginForm.password
    })
    .then(successResponse => {
      if (successResponse.data.code === 200) {
        // var data = this.loginForm
        _this.$store.commit('login', _this.loginForm)
        var path = this.$route.query.redirect
        this.$router.replace({path: path === 'mlogin' || path === undefined ? '/' : path})
         this.$message({
                       message: '登录成功!',
                       type: 'success'
                  });
      }
      else{
        this.$message.error('账号密码不正确!');
      }
    })
    .catch(failResponse => {
       
    })
}   
     /* login () {
        this.$axios
          .post('/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({path: '/mobile'})
            }
          })
          .catch(failResponse => {
          })
      }*/
    }
  }
</script>

<style>
.mlogin{
  width: 100%;
  height: 100%;
  background: url('../assets/img/mbackground.jpg');
  background-size:400px 800px;
  opacity: 0.8;
  z-index: 1;
  position: absolute;
  max-width: 400px;
}
.head{
  z-index: 1;
  position: absolute;
  width: 98%;
  color: #fff;
  font-size: 22px;
  line-height: 50px;
  margin-top: 20px;
}
.textbox{
  width: 90%;
  height: 35px;
  border-radius:5px ;
  border: solid 1px gray;
  font-size: 15px;
}
.main{
  background:rgb(191, 211, 238);
  border-radius:10px;
   z-index: -1;
   width: 98%;
   height: 320px;
   position: absolute;
   margin-top: 100px;
   /*margin-left: 450px;*/
}
.loginbox{
  background: rgba(255, 251, 251, 0.5);
  border: 2px solid rgb(249, 249, 252);
  width: 90%;
  height: 200px;
  z-index: 1;
  position: absolute;
  margin-left: 5%;
  margin-top: 80px;
}
</style>