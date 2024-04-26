<template>
  <div id="monitor">
     <Name-NavName></Name-NavName>
     <div class="content">
      <div class="centers">
  <el-container style=" border: 1px solid #eee">
  <el-aside width="250px" >

  <div class="mannage">
     <i class="el-icon-view"></i> &emsp;监控管理
  </div>

  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="vertical" @select="handleSelect">
  
  <el-submenu index="1" >
    <template slot="title" > 
      <div style="font-size:16px">&emsp;&emsp;一层</div>
      </template>
    <el-menu-item index="1-1">101</el-menu-item>
    <el-menu-item index="1-2">102</el-menu-item>
    <el-menu-item index="1-3">103</el-menu-item>
  </el-submenu>
  <el-submenu index="1" >
    <template slot="title" > 
      <div style="font-size:16px">&emsp;&emsp;二层</div>
      </template>
    <el-menu-item index="1-1">201</el-menu-item>
    <el-menu-item index="1-2">202</el-menu-item>
    <el-menu-item index="1-3">203</el-menu-item>
  </el-submenu>
  <el-submenu index="1" >
    <template slot="title" > 
      <div style="font-size:16px">&emsp;&emsp;三层</div>
      </template>
    <el-menu-item index="1-1">301</el-menu-item>
    <el-menu-item index="1-2">302</el-menu-item>
    <el-menu-item index="1-3">303</el-menu-item>
  </el-submenu>
</el-menu>
  </el-aside>
  <el-main style="background-color:rgb(238, 241, 246)">
    <video controls="controls" width="100%" >
        <source :src='getVideo' type="video/mp4"/>
    </video>
  </el-main>
 
    
</el-container>
        
      </div>
     </div>
  </div>
</template>



<script>

import NavName from '@/components/NavName'

export default {
  name: 'monitor',

  components:{
      "Name-NavName": NavName
  },
  data () {
    return {
      getVideo:require("../assets/img/demo.mp4"),
    
    }
  }
}
</script>

<style>
#monitor{
  min-width:1400px;
}
.content{
  margin-left: 20px ;
 
}
.centers{
  width: 92%;
  float: left;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 10px;
  text-align: left;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}

.mannage{
  text-align: center;
  font-weight: 600;
  line-height: 40px;
  color:#fff;
  background: lightskyblue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-bottom: 5px;
}
</style>