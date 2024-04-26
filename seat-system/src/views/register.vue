<template>
  <div id="register">
    <Name-NavName></Name-NavName>
     <div class="content" >

    <div class="center">

     <div class="block" style="background:lightskyblue;" >
        <span class="demonstration" style="color:#fff;font-weight:600;">&emsp;&emsp;&emsp;&emsp;查找用户:
          
          <el-input v-model="input" placeholder="请输入内容" style="width:200px"></el-input>
        </span>
         
       <!-- <span style="width:200px;text-align:right;color:#fff;font-weight:600;">
          
          &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
          课室:{{room}}

          &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
          需领回座位：{{reclamiseat}}

        </span> -->
     </div>

        <el-table
             :data="tableuserData"
             style="width: 100%; margin:0 auto;">
             <el-table-column
               width="75%">
              </el-table-column>
              <el-table-column
               prop="username"
               label="用户"
               >
             </el-table-column>
             <el-table-column
               prop="roomid"
               label="课室"
               >
             </el-table-column>
             <el-table-column
               prop="seatid"
               label="位置"
              >
             </el-table-column>
             <el-table-column
               width="15%">
              </el-table-column>
             <el-table-column
               prop="use_state"
               label="状态">
             </el-table-column>
             <el-table-column
               width="15%">
              </el-table-column>
              <el-table-column
               prop="use_credit"
               label="信用">
             </el-table-column>
             <el-table-column
               width="15%">
              </el-table-column>
           <!--  <el-table-column
               label="操作">
             <template slot-scope="scope">
             <el-button
               @click.native.prevent="deleteRow(scope.$index, tableDataViolation)"
               type="text">
               领回
             </el-button>
             </template>
             </el-table-column> -->
           </el-table>


     </div>
     </div>
  </div>
</template>



<script>

import NavName from '@/components/NavName'

export default {
  name: 'register',
  components:{
      "Name-NavName": NavName
  },
  data () {
    return {
       input: '',
       tableuserData:[],
   
    }
  },
 created(){
    this.getdata();
},
  methods:{
        getdata(){
           this.$axios
          .get('/userdataAll')
         .then(resp => {
         this. tableuserData=resp.data;
      
        })
        }
  }
}
</script>

<style>
#register{
  min-width: 1400px;
}
.content{
  margin-left: 20px ;
 
}
.center{
  width: 80%;
  float: left;
  margin-left: 120px;
  margin-right: 20px;
  margin-top: 20px;
  text-align: left;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
</style>