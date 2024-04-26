<template>

<div id="mobile">
  <div class="projectname" style="width: 100%;
    float: left;
    color:gray;
    background-color:white;
    font-size: 20px;
    font-weight:normal;
    line-height: 30px;
    text-align: left;
    font-family:Microsoft YaHei;
    margin:0 auto;margin-top:5px" >
     <img :src='imglogo' style="width:30px;height:30px;float:left;margin:0px 10px;border-radius:20px">
     <div style="float:left">自习室座位系统</div>
     <div style="float:right;margin-right:15px;font-size:25px" @click="history()"><i class="el-icon-user-solid"></i>{{user}}</div>
  </div> 
  <div> 
    <el-button type="primary" plain style="width:100%;margin:15px 0px" @click="useqrcore()">扫一扫使用座位</el-button>
  </div> 
  <div id="core">
  <!--<vue-qrcode></vue-qrcode>-->
  </div>
    <div class="up"  v-show="upshow">
          <div class="block" style="background: lightskyblue;
          box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
          margin-bottom: 5px; font-size:15px">
           <span class="demonstration" style="color:#fff ;font-weight: 600;">课室:</span>
            <el-cascader
             v-model="value"
             :options="options"
             :props="{ expandTrigger: 'hover' }"
             size="small"
             @change="handleChange"></el-cascader>

             <span  style="width:200px;text-align: right;color:#fff;font-weight: 600;">
              
               当前:{{value[1]}}
               &ensp; 
               空位:{{emtryseat}}
             </span>
          </div>

          <div class="tablebox">
              <el-table
                class="mtable"
                ref="singleTable"
                :data="tableDataRow"
                highlight-current-row
                @current-change="handleCurrentChange"
                style="width: 100%; margin:0 auto;margin-bottom: 10px;font-size:10px; max-width:400px;text-align: center"
               
                max-height="3000px"
                >

               <el-table-column
                  type="index" width="35px" height="40px">
               </el-table-column>
                <template v-for='(col) in tableDataCol' style="width:10%">
                <el-table-column
                  :label="col"
                  :key="col"
                  width="40px"  
                  height="40px"
                 >
                  <div class="mseatempty" ref="seatBox" name="mseatempty"></div>
                 
                 <!-- <div :class="{'seatempty':'true'=='true',class2:seatuse=='status'}" ref="seatbox"></div> -->
                </el-table-column>
                </template>

              </el-table>
              
          </div>

        
  </div>
  <div id=down  v-show="downshow">
    <div class="message">
            &emsp;<i class="el-icon-menu"></i> &ensp;楼层信息
           </div>
          <div class="tablebox">
            <el-table
              :data="tableDataFloor"
              style="width: 90%; margin:0 auto;margin-bottom:10px;font-size:13px"
              max-height="2000px">
              <el-table-column
               width="8%">
              </el-table-column>
             <!-- <el-table-column
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
          </div>
  </div>
  
</div>
</template>



<script>
// 
import qrcode from '@/components/qrcode.vue';
export default {
// 
components: {
        'vue-qrcode': qrcode,
    },
data () {
    return {
      imglogo:require("../assets/img/logo.jpg"),
      user:this.$store.state.user.username,
      
//固定数据
     upshow:true,
     downshow:true,
     imgnamelogo:require("../assets/img/namelogo.jpg"),
    //选择教室/摄像头组，不需要后台数据 options
     value: [],
        options: [{
          value: 'first',
          label: '一层',
          children: [{
            value: 101,
            label: '101',
            
          }, {
           value: 102,
            label: '102',
            
          }, {
           value: '103',
            label: '103',
            
          }]
        }, 
        {
          value: 'second',
          label: '二层',
          children: [{
            value: '201',
            label: '201',
           
          }, {
           value: '202',
            label: '202',
            
          }, {
           value: '203',
            label: '203',
            
          }]
        },{
          value: 'third',
          label: '三层',
          children: [{
            value: '301',
            label: '301',
           
          }, {
           value: '302',
            label: '302',
            
          }, {
           value: '303',
            label: '303',
            
          }]
        },
        ],
        
//后台数据
    seatclass:"seatempty",
    //切换教室座位
    //room: this.value,
    //id:value[1],
    room:101,
    emtryseat:20,
    //表格行增  
    //tableDataRow: [1,2,3,4,5,6,7,8,9,10],
    //表格列增
    //tableDataCol:[1,2,3,4,5,6,7,8,],
    //表格行增
    tableDataRow: [],
    //表格列增
    tableDataCol:[],
    //无法使用的座位    数值为座位号
    //empty:[71,72,79,80],
    empty:[],
    //使用中座位信息
    //use:[1,3,6,5,6,7,8,24,16,30,22,36,37,49,41,60,50,55,56,66,51,53,54,58,39,73,74,76],
    use:this.use,
    //违规座位信息
    violation:[],
    //暂离座位信息
    leave:[],
    //currentRow: null
    //value:["first","101"],
    //value:'',
    //options:["first","101"],
    //楼层信息
    tableDataFloor:[
      
    ],
  }
  },
  created(){
     
  },
  mounted() {
    this.$nextTick(() => {
       this.findmaxroom();
       this.handleChange(this.value);
       this.seatlayout();
       this.changeseatBox();
       this.getdata();
       /*window.setInterval(() => {
           setTimeout(this.getdata(), 0);
          //setTimeout(this.seatlayout(), 0);
           //setTimeout(this.changeseatBox(), 0);
      }, 5000*6);*/
    });
  },
  methods:{
    //座位布局
    seatlayout(){
      this.$nextTick(() => {
          var arr = document.getElementsByName('mseatempty');
          var num = arr.length;
          var empty= this.empty;
          var sum =0;
            //初始化
            for(var i=0;i<num;++i)
            { 
                 arr[i].className="mseatempty";
            }
          //alert(this.empty.length)
          if(this.empty!=null)
          {  
            for(var i=0;i<num;++i)          
            { 
                for(var n=0;n<empty.length;++n)
                {
                 arr[empty[n]+(Number(this.tableDataCol.length)-1)].className = "mseatno";
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
          var arr = document.getElementsByName('mseatempty');
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
                 arr[use[j]+Number(this.tableDataCol.length)-1].className="mseatuse";
                }
                for(var l=0;l<leave.length;++l)
                {
                 arr[leave[l]+Number(this.tableDataCol.length)-1].className = "mseatleave";
                }
                for(var k=0;k<violation.length;++k)
                {
                 arr[violation[k]+Number(this.tableDataCol.length)-1].className = "mseatviolation";
                }
                
                
            }
             //this.$forceUpdate()
      });     
    },
    
    //发送课室选择框的value 到后端,返回不同的 tableDataRow[],tableDataCol[], empty 数据 来更改课室座位布局   //var id=value[1];
    handleChange(value) {
        
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
            
          }
        })
    },
    getdata(){
      this.$axios
      .get('/rooms')
      .then(resp => {
       var tableDataFloor=resp.data;
       for(var i=0;i<3;i++){
         this.tableDataFloor[i]='{';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'one'+' : '+'"'+ tableDataFloor[3*i].id+':'+tableDataFloor[3*i].empty_seat+'"'+',';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'two'+' : '+'"'+tableDataFloor[3*i+1].id+':'+tableDataFloor[3*i+1].empty_seat+'"'+',';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'three'+' : '+'"'+tableDataFloor[3*i+2].id+':'+tableDataFloor[3*i+2].empty_seat+'"';
         this.tableDataFloor[i]=this.tableDataFloor[i]+'}'
       } 
       this.tableDataFloor='['+this.tableDataFloor+']';
       this.tableDataFloor=eval('(' + this.tableDataFloor + ')'); //将字符串转换为josn数据
       //alert(this.tableDataFloor); 
      })
      
    },
    findmaxroom(){
      this.$axios
      .get('/rooms')
      .then(resp => {
       var tableFloor=resp.data;
      
      var maxid=0;var maxroom="";
      for(var i=0;i<9;i++){
        
           if(maxid<tableFloor[i].empty_seat){
             maxid=tableFloor[i].empty_seat;
             maxroom=tableFloor[i].id;
           }
        
      }
      
      this.value=""+maxroom;
      maxroom='" ",'+maxroom;
      //alert(this.value);
      this.value=maxroom.split(",");
      //var minroomid=minroom.split(",");
      this.handleChange(this.value);
      
      //alert(this.value[1]);
      })
    },
    useqrcore(){
       //this.$router.replace('useqrcode');
       this.$router.replace({path: '/useqrcode'})
    },
    history(){
       this.$router.replace({path: '/message'})
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
#mobile{
  /*max-width: 1000px;
  min-width: 800px;
  max-height: 1000px;
  min-height: 1000px;*/
  text-align: center;

}
.up{
  width: 100%;
  /*float: left;*/
  /*margin-left: 50px;
  margin-right: 20px;
  margin-top: 10px;*/
  text-align: left;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
.dowm{
  width: 100%;
  /*float: left;
  margin-top: 10px;*/
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
table label{
  text-align: center;
}
.tablebox{
 font-size:10px;
 text-align: center;
}
.mseatempty{
   width: 20px;
   height: 20px;
   background: url('../assets/img/seatempty.png');
   background-size:20px 20px;
   opacity: 0.5;
}
.mseatuse{
   width: 20px;
   height: 20px;
   background: url('../assets/img/seatuse.png');
   background-size:20px 20px;
   opacity: 0.6;
}
.mseatviolation{
   width: 20px;
   height: 20px;
   background: url('../assets/img/seatviolation.png');
   background-size:20px 20px;
   opacity: 0.6;
}
.mseatleave{
   width: 20px;
   height: 20px;
   background: url('../assets/img/seatleave.png');
   background-size:20px 20px;
   opacity: 0.6;
}
.mseatno{
   width: 30px;
   height: 30px;
   opacity: 0;
}
.message{
  text-align: center;
  line-height: 30px;
  font-weight: 600;
 font-size: 15px;
  color:#fff;
  background: lightskyblue;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  margin-bottom: 5px;
}
.cell{
  padding: 0px 0px;
  margin: 0px 0px;
}
.mtable.el-table .el-table__cell {
    padding: 6px 0;
}
</style>