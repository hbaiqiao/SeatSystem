<template>
  <div id="userdata">
    <div class="projectname" style="width: 100%;
             float: left;
             color:gray;
             font-size: 20px;
             font-weight:normal;
             line-height: 30px;
             text-align: left;
             font-family:Microsoft YaHei;
             margin:0 auto;margin-top:5px
             opacity: 0.3;" 
            >
          <img :src='imglogo' style="width:30px;height:30px;float:left;margin:0px 10px;border-radius:20px">
          <div style="float:left">自习室座位系统</div>
          <div style="float:right;margin-right:15px;font-size:25px" @click="history()"><i class="el-icon-user-solid"></i>{{user}}</div>
        </div>
     <div class="usedata">课室:{{roomid}}&emsp;座位:{{seatid}}</div>
     <div class="timer">
    <div ref="startTimer"></div>
    
    </div>
    <div class="state"><div ref="startTimerStop">进行中</div></div>
    <br/>
        <div style="float:left;margin-top:200px;margin-left:30px">
        <el-row>
                    <el-button type="success" plain @click="stop()">暂时离开</el-button>
                    <el-button type="primary" plain @click="cont()">回到座位</el-button>
                    <el-button type="warning" plain @click="leave()">释放座位</el-button>
        </el-row>
        </div>
        
  </div>
  
</template>



<script>
// 

export default {
    name: 'Timer',
    data () {
    return {
      imglogo:require("../assets/img/logoicon.png"),
      user:this.$store.state.user.username,
      dialogVisible: false,
      flag:0,
      flagstop:1,
      tag:0,
      roomid:"",
      seatid: "",
      use_state:"正常",
      use_credit:"",
      use_time:0,
      start_time:"",
      date:"",

      state:"进行中",
      timer: "",
      content: "",
      hour: 0,
      minutes: 59,
      seconds: 55,
      min:29,
      sec:50,
    }
    },
    created(){
      this.getdata();
    },
    mounted () {
        //this.getdata();
        var time=new Date();
        this.date=time.toLocaleDateString();
        this.start_time=time.toLocaleTimeString();
        this.timer = setInterval(this.startTimer, 1000);
        //setTimeout (this.getdata(),1000);
        //this.getdata();
       
    },
    beforeDestroy(){
      //alert("sure?");
    },
    destroyed () {
        clearInterval(this.timer);
        //this.useover();
    },
    
    methods: {
    startTimer () {
    this.seconds += 1;
    if (this.seconds >= 60) {
    this.seconds = 0;
    this.minutes = this.minutes + 1;
    }
    
    /*if (this.minutes >= 60) {
    this.minutes = 0;
    this.hour = this.hour + 1;
    }*/
    this.$refs.startTimer.innerHTML = (this.minutes < 10 ? '0' + this.minutes : this.minutes) + ':' + (this.seconds < 10 ? '0' + this.seconds : this.seconds);
    },

    startTimerStop () {
    this.sec += 1;
    if (this.sec >= 60) {
    this.sec = 0;
    this.min = this.min + 1;
    } 
    this.$refs.startTimerStop.innerHTML = "暂停中 "+(this.min< 10 ? '0' + this.min : this.min) + ':' + (this.sec < 10 ? '0' + this.sec : this.sec);
       if(this.min==30&&this.sec==0){
          this.tag=1;
          //this.use_state="违规";
          this.$axios.post('/violationAll',{
              
              roomid:this.roomid,
              seatid:this.seatid,
              username:this.$store.state.user.username,
           })
          this.$axios
            .post('/userdata',{
                username:this.$store.state.user.username,
             })
            .then(resp => {
                var json=resp.data;
                alert(json);
                //this.use_credit=this.use_credit-6;
                var credit=json.use_credit;
                credit=credit-6;
                alert(credit);
              this.$axios
                 .post('/userdataAll',{
                 username:json.username,
                 roomid:json.roomid,
                 seatid:json.seatid,
                 use_time:json.use_time,
                 use_state:"违规",
                 use_credit:credit,
                 }) 
             })
           this.$axios
           .post('/find',{
            id:this.roomid,
           })
           .then(resp => {
             var json=resp.data;
             
            var violationarr=resp.data.seat_violation;
            var violationdata=violationarr.split(",");
            var violationnow=this.seatid.split(",");
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
              //room_rows:json.room_rows,   
            })
            /*this.$axios
           .post('/userdata/all',{
            username:this.$store.state.user.username,
            roomid:roomid,
            seatid:seatid,
            use_time:30,
            use_state:"正常",
            use_credit:100,

           })*/
            
        })


       }
    },

    stop(){
      clearInterval(this.timer);
      this.state="暂停中";
      if(this.flagstop==1){
             this.timerStop = setInterval(this.startTimerStop, 1000);
               this.$axios
                 .post('/find',{
                    id:this.roomid
                  })
                 .then(resp => {
                    var json=resp.data;
             
                    var leavearr=resp.data.seat_leave;
                    var leavedata=leavearr.split(",");
                    var leavenow=this.seatid.split(",");
                    var leaves=[...leavedata,...leavenow];
                    this.leaves=leaves.toString();
                   //alert(this.violation);
                   json["seat_leave"]=this.leaves;
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

      }else{
        this.$message({
          message: '已经暂时离开座位',
          type: 'warning'
        });
      }
      this.flag=1;
      this.flagstop=0;
    },
    cont(){
      if(this.flag==1){
       this.timer = setInterval(this.startTimer, 1000);
        clearInterval(this.timerStop);
        this.min=29;this.sec=50;
        this.$refs.startTimerStop.innerHTML="进行中";
        this.flag=0;
        this.flagstop=1;

        this.$axios
           .post('/find',{
            id:this.roomid,
           })
           .then(resp => {
             //alert(JSON.stringify(resp.data));
            var json=resp.data;
            var leavearr=resp.data.seat_leave;
            var leavedata=leavearr.split(",");
            var leaves=this.seatid;
            var leavenow=leaves.split(",");
            var index=leavedata.indexOf(leavenow.toString());
            leavedata.splice(index,1);
            var leaves=leavedata;
            this.leaves=leaves.toString();
            
            json["seat_leave"]=this.leaves;
            
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
         })

      }else{
         this.$message({
          message: '座位使用中',
          type: 'warning'
        });
      }
    },
   /*leave(){ 
     /* this.$alert('<strong>确认离开此座位？</strong>', '提示信息', {
          dangerouslyUseHTMLString: true,  
        });
         this.dialogVisible = false;
        this.$router.replace({path: '/mobile'});  
    },*/
    leave() {
        this.use_time=this.minutes;
        this.$confirm('离开当前座位？', '提示信息', {
          distinguishCancelAndClose: true,
          confirmButtonText: '确认',
          cancelButtonText: '取消'
        })
          .then(() => {
            this.useover();
            //this.$router.replace({path: '/mobile'});
          })
          .catch(action => {
            this.$message({
              type: 'info',
              message: action === 'cancel'
                ? '已取消'
                : '已取消'
            })
          });
      },
    getdata(){
      //this.$refs.startTimerStop.innerHTML="进行中";
      this.$axios
           .post('/userdata',{
            username:this.$store.state.user.username,
          })
         .then(resp => {
            //alert(JSON.stringify(resp.data));
            this.roomid=resp.data.roomid;
            this.seatid=resp.data.seatid;
            this.use_state=resp.data.use_state;
            this.use_credit=resp.data.use_credit;
            //alert(this.use_credit);
         }) 
    },
    useover(){
         this.$axios
           .post('/find',{
            id:this.roomid,
           })
           .then(resp => {
             //alert(JSON.stringify(resp.data));
            var json=resp.data;
            var usearr=resp.data.seat_used;
            var usedata=usearr.split(",");
            var use=this.seatid;
            var usenow=use.split(",");
            var index=usedata.indexOf(usenow.toString());
            //alert(index);
            if(index!=-1){
               //alert("del");
               usedata.splice(index,1); 
            }
            else{
                this.$message({
                   message: '已违规,请联系管理员',
                   type: 'warning'
                });
            }
            //usedata.splice(index,1);
            var used=usedata;
            this.used=used.toString();
            
            json["seat_used"]=this.used;
            var empty_seat=json.empty_seat;
            empty_seat=empty_seat+1;
           this.$axios.post('/rooms',{
              id:json.id,
              seat_used:json.seat_used,
              seat_violation:json.seat_violation,
              seat_leave:json.seat_leave,
              empty_area:json.empty_area,
              empty_seat:empty_seat,
              room_rows:json.room_rows,
              room_cols:json.room_cols,
              seat_num:json.seat_num,  

            })
            
            //alert("ok");
            if(this.tag==0){
                if(this.use_credit<100&&this.minutes>=60){
                   this.use_credit=this.use_credit+2;
                }
                 //this.use_credit=this.use_credit+2;
                 this.use_state="正常";
                 //alert(this.$store.state.user.username);
                 this.$axios
                   .post('/userdata',{
                      username:this.$store.state.user.username,
                    })
                   .then(resp => {
                          var json=resp.data;
                          //alert(json);
                          //alert(JSON.stringify(resp.data));
                          var credit=json.use_credit;
                          //alert(this.use_credit);
                          //alert(credit);
                          if(credit<100&&this.minutes>=60){
                             credit=credit+2;
                          } 
                          //credit=credit+2;
                          //alert(credit);
                          //var credit=this.use_credit+2;
                           this.$axios
                           .post('/userdataAll',{
                            username:json.username,
                            roomid:json.roomid,
                            seatid:json.seatid,
                            use_time:json.use_time,
                            use_state:"正常",
                            use_credit:credit,
                            }) 
                        })
            }
            if(this.tag==1){
                 this.use_state="违规";
                 this.use_credit=this.use_credit-6;
            }

            this.$axios.post('/historyAll',{
             
             username:this.$store.state.user.username,
             roomid:this.roomid,
             seatid:this.seatid,
             use_state:this.use_state,
             use_credit:this.use_credit,
             use_time:this.use_time,
             date:this.date,
             start_time:this.start_time,
            })
            
           })
           this.$router.replace({path: '/'});
    }
  }
}
</script>

<style>
#userdata{
  width: 100%;
  height: 720px;
  background: url('../assets/img/mainbackground.jpg');
  background-size:420px 800px;
  opacity: 0.8;
  /*z-index: 1;
  position: absolute;*/
  float: left;
  max-width: 400px;
}
.timer{
  background-color:grey ;
  color:#fff;
  font-size: 70px;
  width: 60%;
  height: 90px;
  border-radius: 20px;
  margin-left: 75px;
  margin-top: 70px;
  float: left;
}
.usedata{
  width: 60%;
  float: left;
  background-color: gray;
  color: #fff;
  border-radius: 10px;
  font-size: 26px;
  margin-left: 75px;
  margin-top: 130px;
  opacity: 0.5;
}
.state{
  width: 40%;
  height: 30px;
  background-color:grey ;
  color:#fff;
  font-size: 18px;
  margin-left: 120px;
  margin-top: 20px;
  float: left;
  text-align: center;
  border-radius: 10px;
  padding-top: 5px;
}
.el-message-box{
  width: 360px;
}
</style>