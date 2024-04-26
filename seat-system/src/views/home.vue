<template>
  <div id="home">
     <Name-NavName></Name-NavName>
     <div class="content">
       
        <div class="left">
          <div class="block" style="background: lightskyblue;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);margin-bottom: 5px; ">
           <span class="demonstration" style="color:#fff ;font-weight: 600;">&emsp;课室：</span>
            <el-cascader
             v-model="value"
             :options="options"
             :props="{ expandTrigger: 'hover' }"
             @change="handleChange"></el-cascader>

             <span  style="width:200px;text-align: right;color:#fff;font-weight: 600;">
               &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
               当前课室:  {{value[1]}}
               &emsp;&emsp;&emsp;&emsp;&emsp;
               课室空位: {{emtryseat}}
             </span>
          </div>

          <div class="tablebox">
              <el-table
                ref="singleTable"
                :data="tableDataRow"
                highlight-current-row
                @current-change="handleCurrentChange"
                style="width: 90%; margin:0 auto;margin-bottom: 20px"
                max-height="600px"
                min-height="600px">

               <el-table-column
                  type="index">
               </el-table-column>
                <template v-for='(col) in tableDataCol'>
                <el-table-column
                  :label="col"
                  :key="col"
                 >
                  <div class="seatempty" ref="seatBox" name="seatempty"></div>
                 <!-- <div :class="{'seatempty':'true'=='true',class2:seatuse=='status'}" ref="seatbox"></div> -->
                </el-table-column>
                </template>

              </el-table>
              
          </div>

        </div>
        <div class="right">
           <!--<h3 style="font-weight:normal">座位信息</h3> 座位信息-->
           
           <div class="message">
            &emsp;<i class="el-icon-menu"></i> &ensp;楼层信息
           </div>

            <el-table
              :data="tableDataFloor"
              style="width: 90%; margin:0 auto;margin-bottom:10px">
              <el-table-column
               width="15%">
              </el-table-column>
           <!--   <el-table-column
                prop="floor"
                label=" 楼 层 ">
              </el-table-column> -->
             
             <el-table-column
              prop="one">
             </el-table-column>
             <el-table-column
               prop="two"
               label="课室/空位">
             </el-table-column>
             <el-table-column
               prop="three">
             </el-table-column>
           </el-table>

           <div class="message">
            &emsp;<i class="el-icon-s-operation"></i> &ensp;违规座位
           </div>
           
           <el-table
             :data="tableDataViolation"
             style="width: 100%; margin:0 auto;"
             max-height="240px"
             min-height="240px"
             >
             <el-table-column
               width="15%">
              </el-table-column>
             <el-table-column
               prop="roomid"
               label="课室"
               >
             </el-table-column>
             <el-table-column
               prop="seatid"
               label="座位号"
              >
             </el-table-column>
              
             <!--<el-table-column
               label="存放号">
              <template slot-scope="scope">
              <el-input v-model="scope.row.deposit" placeholder="请输入"></el-input>
              </template>
             </el-table-column>-->
              <el-table-column
               prop="username"
               label="用户"
              >
             </el-table-column>
             <el-table-column
               width="15%">
              </el-table-column>
             <el-table-column
               label="操作">
             <template slot-scope="scope">
             <el-button
               @click="deleteRow(scope.$index)"
               type="text">
               确认
             </el-button>
             </template>
             </el-table-column>
           </el-table>
           <!--<button @click="changeseatBox()">  test</button>-->
           <div class="message">
             &emsp;<i class="el-icon-s-operation"></i> &ensp;添加违规
           </div>
           <div style="margin-top:25px">
             <el-form :inline="true" :model="formInline" class="demo-form-inline">
                 <el-form-item >
                    <el-input v-model="formInline.roomid" placeholder="课室号" style="width:200px"></el-input>
                 </el-form-item>
                 <el-form-item >
                    <el-input v-model="formInline.seatid" placeholder="座位号" style="width:200px"></el-input>
                 </el-form-item>
                 <el-form-item>
                   <el-button type="primary" @click="onSubmit()">确认</el-button>
                 </el-form-item>
             </el-form>
           </div>
        </div>
     </div>
  </div>
</template>




<script type="text/javascript">

import NavName from '@/components/NavName'

//setInterval("handleChange(value)","5000");
//setInterval("getdata()","5000");
//window.setInterval( "getdata()" ,"6000");
export default {
  name: 'home',

  components:{
      "Name-NavName": NavName
  },
  
  data () {
    return {
//固定数据
     imgnamelogo:require("../assets/img/namelogo.jpg"),
     //imgseatempty:require("../assets/seatempty.png"),
     /*backgroundDiv: {
            backgroundImage: 'url(' + require("../assets/img/seatempty.png") + ')'
        },*/

    //选择教室/摄像头组，不需要后台数据 options
     value: [],
        options: [{
          //value: 'first',
          label: '一层',
          children: [{
            value: '101',
            label: '101',
          }, {
           value: '102',
            label: '102',
          },{
           value: '103',
            label: '103',
          },
          ]
        }, 
        {
          //value: 'second',
          label: '二层',
          children: [{
            value: '201',
            label: '201',
          }, {
           value: '202',
            label: '202',
          },{
           value: '203',
            label: '203',
          },
          ]
        },{
         // value: 'third',
          label: '三层',
          children: [{
            value: '301',
            label: '301',
          }, {
           value: '302',
            label: '302',
          },{
           value: '303',
            label: '303',
          },
          ]
        },
        ],
    formInline: {
          roomid: '',
          seatid: ''
    },
//后台数据
    seatclass:"seatempty",
    //切换教室座位
    room:101,
    emtryseat:20,
    //表格行增
    tableDataRow: [],
    //表格列增
    tableDataCol:[],
    //无法使用的座位    数值为座位号
    empty:[],
    //使用中座位信息
    use:[],
    //违规座位信息
    violation:[],
    //暂离座位信息
    leave:[],
    //currentRow: null
    //value:["first","101"],
    //楼层信息
    tableDataFloor:[],
    //违规列表
    tableDataViolation:[],
    violationname:"",
    //someone:[8,24,16],
    //violation:[5,18]
    }
  },
  //加载前自动执行
   created() {
    // 实现轮询
   /* window.setInterval(() => {
      setTimeout(this.getdata(), 0);
    }, 5000*2);*/
  },
   
  //加载完成自动执行
  mounted() {
    //this.getdata();
    //this.handleChange(value);
    this.$nextTick(() => {
      clearInterval(this.timer);
       this.findminroom();
       this.handleChange(this.value);
       this.seatlayout();
       this.changeseatBox();
       this.getdata();
       this.regetdata();
       // 实现轮询
      this.timer=setInterval(() => {
      setTimeout(this.getdata(), 0);
      setTimeout(this.handleChange(this.value), 0);
      //setTimeout(this.seatlayout(), 0);
      //setTimeout(this.changeseatBox(), 0);
      }, 5000*4);
    });
  },
  destroyed(){
      clearInterval(this.timer);
  },
   
  methods:{
    //定时刷新
    regetdata(){
      /*window.setInterval(() => {
       setTimeout(getdata,6000)
      }, 6000)*/
    },
    //座位布局
    seatlayout(){
      this.$nextTick(() => {
          var arr = document.getElementsByName('seatempty');
          var num = arr.length;
          var empty= this.empty;
          var sum =0;
            //初始化
            for(var i=0;i<num;++i)
            { 
                 arr[i].className="seatempty";
            }
          //alert(this.empty.length)
          if(this.empty!=null)
          {  
            for(var i=0;i<num;++i)          
            { 
                for(var n=0;n<empty.length;++n)
                {
                 arr[empty[n]+(Number(this.tableDataCol.length)-1)].className = "seatno";
                 //arr[empty[n]+7].removeAttribute("name");
                
                }
            }
          }
           //this.$forceUpdate();
      });
    },
    
    //更改座位状态
    changeseatBox(){
       this.$nextTick(() => { 
          var arr = document.getElementsByName('seatempty');
          var num = arr.length;
          var use=this.use;
          var violation=this.violation;
          var leave=this.leave;
          var empty= this.empty;

          //经过无法使用的座位座位号+1
          if(this.empty!=null)
          {
          for(var s=0;s<empty.length;++s)
           {
             for(var i=0;i<use.length;++i){
               if(use[i] >= empty[s]){
                 use[i]=use[i]+1;
               }
             }
             for(var i=0;i<violation.length;++i){
               if(violation[i] >= empty[s]){
                 violation[i]=violation[i]+1;
               }
             }
             for(var i=0;i<leave.length;++i){
               if(leave[i] >= empty[s]){
                 leave[i]=leave[i]+1;
               }
             }
           }
          }
           //更换座位状态
            for(var i=0;i<num;++i)
            
            { 
                for(var j=0;j<use.length;++j)
                {
                 arr[use[j]+Number(this.tableDataCol.length)-1].className="seatuse";
                }
                for(var l=0;l<leave.length;++l)
                {
                 arr[leave[l]+Number(this.tableDataCol.length)-1].className = "seatleave";
                }
                for(var k=0;k<violation.length;++k)
                {
                 arr[violation[k]+Number(this.tableDataCol.length)-1].className = "seatviolation";
                }
                
                
            }
             //this.$forceUpdate()
      });     
    },
    
    //发送课室选择框的value 到后端,返回不同的 tableDataRow[],tableDataCol[], empty 数据 来更改课室座位布局
    /*handleChange(value) {
        var id=value[1];
        console.log(value);
         this.$axios
           .post('/searchroom',{
            id
           })
           .then(successResponse=> {

           })
           .catch(failResponse => {

           })
    },*/
     handleChange(value) {
        //this.value=value;
        //clearInterval(this.timer);
        console.log(value);
         this.$axios
           .post('/find',{
            id:this.value[1]
           })
           .then(resp => {

          if (resp && resp.status === 200) {
            this.tableDataRow=resp.data.room_rows.split(",");
            //alert(this.tableDataRow);
            this.tableDataCol=resp.data.room_cols.split(",");

            this.emtryseat=resp.data.empty_seat;

            var usearr=resp.data.seat_used
            var usedata=usearr.split(",");
            for(var i=0;i<usedata.length;++i)
            {
              usedata[i]=Number(usedata[i]);
            }
            this.use=usedata;

            var emptyarr=resp.data.empty_area;
            var emptydata=emptyarr.split(",");
            for(var i=0;i<emptydata.length;++i)
            {
              emptydata[i]=Number(emptydata[i]);
            }
            this.empty=emptydata;

            var violationarr=resp.data.seat_violation
            var violationdata=violationarr.split(",");
            for(var i=0;i<violationdata.length;++i)
            {
              violationdata[i]=Number(violationdata[i]);
            }
            this.violation=violationdata;

            var leavearr=resp.data.seat_leave
            var leavedata=leavearr.split(",");
            for(var i=0;i<leavedata.length;++i)
            {
              leavedata[i]=Number(leavedata[i]);
            }
            this.leave=leavedata;
            
            this.seatlayout();
            this.changeseatBox();
            
            //clearInterval(this.timer);
          }
        })
    },
    getdata(){
      this.$axios
      .get('/rooms')
      .then(resp => {
       var tableDataFloor=resp.data;
       var tableFloor=resp.data;
       for(var i=0;i<3;i++){
         this.tableDataFloor[i]='{';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'one'+' : '+'"'+ tableDataFloor[3*i].id+':'+tableDataFloor[3*i].empty_seat+'"'+',';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'two'+' : '+'"'+tableDataFloor[3*i+1].id+':'+tableDataFloor[3*i+1].empty_seat+'"'+',';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'three'+' : '+'"'+tableDataFloor[3*i+2].id+':'+tableDataFloor[3*i+2].empty_seat+'"';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'}'
       } 
       this.tableDataFloor='['+this.tableDataFloor+']';
       this.tableDataFloor=eval('(' + this.tableDataFloor + ')'); //将字符串转换为josn数据

       var message="";
       for(var i=0;i<9;i++){
        if(tableFloor[i].empty_seat<20){
           //alert(tableFloor[i].id);
           message=message+tableFloor[i].id+" ";
        }
      }
      if(message){
        const h = this.$createElement;
        this.$notify({
          title: '系统提示',
          message: h('i', { style: 'color: teal'}, message+"座位不足！")
          });
        }
      })
      this.$axios
      .get('/violationAll')
      .then(resp => {
        this.tableDataViolation=resp.data;
      })
      //var data=sessionStorage.getItem(key);
      //alert(data);
      /*this.$axios
           .post('/userdata',{
            username:this.$store.state.user.username,
          })
      this.$axios
           .post('/history',{
            username:this.$store.state.user.username,
          })
      this.$axios
           .post('/find',{
            id:this.value[1]
           })*/
    },
    findminroom(){
      this.$axios
      .get('/rooms')
      .then(resp => {
       var tableFloor=resp.data;
      
      var minid=999;var minroom="";
      for(var i=0;i<9;i++){
           if(minid>tableFloor[i].empty_seat){
             minid=tableFloor[i].empty_seat;
             minroom=tableFloor[i].id;
           }  
      }
      
      this.value=""+minroom;
      minroom='" ",'+minroom;
      this.value=minroom.split(",");
      this.handleChange(this.value);
      
      //alert(this.value[1]);
      })
    },
    deleteRow(index){
          //alert(index);
          this.$axios
           .post('/violation/delete',{
              username:this.tableDataViolation[index].username,
            })
           //this.tableDataViolation.splice(index, 1);
           var i=index;
           this.$axios
           .post('/find',{
            id:this.tableDataViolation[index].roomid,
           })
           .then(resp => {
             //alert(i);
             //alert(JSON.stringify(resp.data));
            var json=resp.data;
            var violationarr=resp.data.seat_violation;
            var violationdata=violationarr.split(",");
            //alert(violationdata);
            var violation=this.tableDataViolation[i].seatid;
            //alert(violation);
            var violationnow=violation.split(",");
            var index=violationdata.indexOf(violationnow.toString());
            violationdata.splice(index,1);
            var violationed=violationdata;
            this.violationed=violationed.toString();
            //alert(this.violationed);
            json["seat_violation"]=this.violationed;
            
            
            var usearr=resp.data.seat_used;
            var usedata=usearr.split(",");
            var use=this.tableDataViolation[i].seatid;;
            var usenow=use.split(",");
            var index=usedata.indexOf(usenow.toString());
            usedata.splice(index,1);
            var used=usedata;
            this.used=used.toString();
            
            json["seat_used"]=this.used;
            //alert(json);
           this.$axios.post('/rooms',{
              id:json.id,
              seat_used:json.seat_used,
              seat_violation:json.seat_violation,
              seat_leave:json.seat_leave,
              empty_area:json.empty_area,
              empty_seat:json.empty_seat+1,
              room_rows:json.room_rows,
              room_cols:json.room_cols,
              seat_num:json.seat_num,     
            })
            //alert(i);
            this.$axios
           .post('/userdataState',{
              roomid:this.tableDataViolation[i].roomid,
              seatid:this.tableDataViolation[i].seatid
            })
            .then(resp => {
                var json=resp.data;
                //alert(json);
              this.$axios
                 .post('/userdataAll',{
                 username:json.username,
                 roomid:json.roomid,
                 seatid:json.seatid,
                 use_time:json.use_time,
                 use_state:"正常",
                 use_credit:json.use_credit,
                 }) 
             })

            this.tableDataViolation.splice(index, 1);
            this.$message({
                message: '违规删除成功',
                type: 'success'
               });
           })
          
           
    },
    onSubmit(){
      var json="";
       //this.getdata(); 
       this.$axios
           .post('/userdataState',{
              roomid:this.formInline.roomid,
              seatid:this.formInline.seatid
            })
            .then(resp => {
                  json=resp.data;
                  this.violationname=json.username;
              this.$axios
                 .post('/userdataAll',{
                 username:json.username,
                 roomid:json.roomid,
                 seatid:json.seatid,
                 use_time:json.use_time,
                 use_state:"违规",
                 use_credit:json.use_credit-6,
                 })
                .catch(error=>{
                       console.log(error);
                      this.$message.error('查找错误');
                 }) 
              this.$axios
                 .post('/violationAll',{
                    roomid:this.formInline.roomid,
                    seatid:this.formInline.seatid,
                    username:this.violationname,
                 })
                 .then(resp => {
                      this.$message({
                        message: '添加成功',
                       type: 'success'
                     });  
               })
                this.getdata(); 
             })
              this.$axios
                 .post('/find',{
                    id:this.formInline.roomid
                  })
                 .then(resp => {
                    var json=resp.data;
             
                    var violationarr=resp.data.seat_violation;
                    var violationdata=violationarr.split(",");
                    var violationnow=this.formInline.seatid.split(",");
                    var violation=[...violationdata,...violationnow];
                    this.violation=violation.toString();
                   //alert(this.violation);
                   json["seat_violation"]=this.violation;
                   this.$axios.post('/rooms',{
                         id:json.id,
                         seat_used:json.seat_used,
                         seat_violation:json.seat_violation,
                         seat_leave:json.seat_leave,
                         empty_area:json.empty_area,
                         empty_seat:json.empty_seat,
                         room_rows:json.room_rows,
                         room_cols:json.room_cols,
                         seat_num:json.seat_num,
                
                         })
                 })
            
    },
    postdata(){
      this.$axios
      .post('/',{
        //index:this.
      })
    },

    
  },
}


</script>

<style>
#home{
  min-width: 1400px;
}
.content{
  margin-left: 20px ;
 
}
.left{
  width: 56%;
  float: left;
  margin-left: 50px;
  margin-right: 20px;
  margin-top: 10px;
  text-align: left;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
.right{
  width: 36%;
  min-width: 520px;
  float: left;
  margin-top: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
table label{
  text-align: center;
}
.tablebox{
 text-align: center;
}
.seatempty{
   width: 30px;
   height: 30px;
   background: url('../assets/img/seatempty.png');
   background-size:30px 30px;
   opacity: 0.5;
}
.seatuse{
   width: 30px;
   height: 30px;
   background: url('../assets/img/seatuse.png');
   background-size:30px 30px;
   opacity: 0.6;
}
.seatviolation{
   width: 30px;
   height: 30px;
   background: url('../assets/img/seatviolation.png');
   background-size:30px 30px;
   opacity: 0.6;
}
.seatleave{
   width: 30px;
   height: 30px;
   background: url('../assets/img/seatleave.png');
   background-size:30px 30px;
   opacity: 0.6;
}
.seatno{
   width: 30px;
   height: 30px;
   opacity: 0;
}
.message{
  text-align: center;
  font-weight: 600;
  line-height: 40px;
  color:#fff;
  background: lightskyblue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-bottom: 5px;
}
</style>