<template>
<div class="header" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
  <div class="projectname" style="width: 75%;
    float: left;
    color:lightskyblue;
    font-size: 20px;
    font-weight:normal;
    line-height: 59px;
    text-align: left;
    font-family:Microsoft YaHei;
    border-bottom:1px solid rgb(230, 228, 228)" >
     <img :src='imglogo' style="width:40px;height:40px;float:left;margin:10px 20px;border-radius:20px">
     <div style="float:left">图书馆座位系统</div>
     <!--<img :src='imgnamelogo' style="width:260px;height:60px;float:left;margin-left:20px">-->
  </div>

 <div class="nav" style="width: 25%;float: left;">     
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" router>
  <el-menu-item index="/">管理首页</el-menu-item>
  <el-menu-item index="/register">用户管理</el-menu-item>
  <!--<el-menu-item index="/manage">后台控制</el-menu-item>-->
  <el-menu-item index="/monitor">监控视角</el-menu-item>
  
  </el-menu>
 </div>


</div>
</template>


<script>
  export default {
    name:'NavName',
    data() {
      return {
         imglogo:require("../assets/img/logo.jpg"),
         imgnamelogo:require("../assets/img/namelogo.jpg"),
         activeIndex: this.$route.path,
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

