<template>
     <div id="message">
         
         <div class="projectname" style="width: 100%;
             float: left;
             color:gray;
             background-color:white;
             font-size: 20px;
             font-weight:normal;
             line-height: 30px;
             text-align: left;
             font-family:Microsoft YaHei;
             margin:0 auto;margin-top:5px" 
            @click="back()">
          <img :src='imglogo' style="width:30px;height:30px;float:left;margin:0px 10px;border-radius:20px">
          <div style="float:left">自习室座位系统</div>
          <div style="float:right;margin-right:15px;font-size:25px" @click="history()"><i class="el-icon-user-solid"></i>{{user}}</div>
        </div> 
           <!--  <i class="el-icon-document-copy"></i>&ensp;用户信息 -->
          <!-- <div>
              <div class="time">
                 <span>座位计时器</span>
                      
              </div>
              <br/>
              <el-row>
                    <el-button type="success" plain @click="back()">暂时离开</el-button>
                    <el-button type="warning" plain>释放座位</el-button>
              </el-row>
          </div> -->
            <div class="review">
                <span>历史记录&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;信用:{{credit}}</span> 
                <el-table
              :data="tableDataHistory"
              style="width:100%; margin:0 auto;margin-bottom:0px;font-size:12px;"
              min-height="540px" 
              max-height="540px">
              
              <el-table-column
                label="日期"
                width="85px"
                prop="date">
              </el-table-column>
              <el-table-column
               label="课室"
                width="45px"
               align= "center"
               prop="roomid">
             </el-table-column>
             <el-table-column
               label="座位"
                width="45px"
               prop="seatid">
             </el-table-column>
              <el-table-column
               label="开始"
                width="100px"
               prop="start_time">
             </el-table-column>
             <el-table-column
               label="使用"
                width="45px"
               prop="use_time">
             </el-table-column>
             <el-table-column
               label="状态"
                width="45px"
               prop="use_state">
             </el-table-column>
              <el-table-column
               label="信用"
                width="45px"
               prop="use_credit">
             </el-table-column>
                
           </el-table>
                 
            </div>   
            <div> 
                  <el-button type="primary" plain style="width:100%;margin:15px 0px" @click="loginout()">退出登录</el-button>
            </div> 
  
    </div>
</template>

<script>
  export default {
    name:'Message',
    data() {
      return {
        imglogo:require("../assets/img/logo.jpg"),
        user:this.$store.state.user.username,
        credit:"",
        tabPosition: 'left',
        tableDataHistory:[ ],
      };
    },
    created(){
        this.getdata();
    },
    methods:{
       back(){
         this.$router.replace({path: '/'})
       },
       getdata(){
          this.$axios
          .post('/history',{
            username:this.$store.state.user.username,
           })
         .then(resp => {
         this. tableDataHistory=resp.data.reverse();
        })
        this.$axios
           .post('/userdata',{
            username:this.$store.state.user.username,
           })
           .then(resp => {
              //alert(resp.data);
             this.credit=resp.data.use_credit;
             if(this.credit<80){
                this.$message({
                       message: '信用分低于80,请规范使用',
                       type: 'warning'
                     });
             }
             if(this.credit<60){
               this.$message.error('信用分低于80,请联系管理员');
             }
        })
       },
       loginout(){
         this.$router.replace({path: '/mlogin'})
       }
    }
  };
</script>

<style >
#message{
  margin: 0 auto;
}
.time{
  width: 100%;
  text-align: center;
  font-weight: 600;
  line-height: 40px;
  color:#fff;
  background: lightskyblue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-top: 30px;
 
}
.review{
  width: 100%;
  text-align: center;
  font-weight: 600;
  line-height: 40px;
  color:#fff;
  background: lightskyblue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-top: 30px;
  float: left;
}
.messagebox{
  width: 100%;
  text-align: center;
  background: skyblue;
  color: #fff;
}
.el-table .el-table_cell{
  text-align: center;
  
}
.cell{
  padding: 0px 0px;
  margin: 0px 0px;
}
.el-table .el-table__cell{
  padding: 10px 0px;
}
</style>