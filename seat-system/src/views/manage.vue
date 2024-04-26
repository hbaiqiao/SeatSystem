<template>
  <div id="manage">
    <Name-NavName></Name-NavName>
    <div class="main">
      <div class="left_box">

       <canvas id="myCanvas"></canvas>
       <img :src='imgmanage' id="imgs" style="width:500px;height:660px" >
       <!--<span style='display: inline-block;width:100px;height: 100px;' id ='span'>12</span>
       <span style='display: inline-block;width:100px;height: 100px;' id ='span1'>112</span> -->
      </div>
      <div class="right_box">
          <div class="message">
            &emsp;<i class="el-icon-s-data"></i> &ensp;HSV调节
           </div>
           <div id="rgb_box">
            <div class="rgb_info">主题色:</div>
            <div  id ='span'></div>
            <div class="rgb_info">RGB:  {{rgb}}</div>
            <div class="rgb_info">HSV:  {{hsv}}</div>
         </div>

          <p>H 色调调节</p>
          <template>
            <div class="block" style="width:90%;margin-left:30px">
              <el-slider
                v-model="newR"
                max="255"
                show-input>
              </el-slider>
            </div>
         </template>

         <p>S 饱和度调节</p>
          <template>
            <div class="block" style="width:90%;margin-left:30px">
              <el-slider
                v-model="newG"
                max="255"
                show-input>
              </el-slider>
            </div>
         </template>

         <p>V 明度调节</p>
          <template>
            <div class="block" style="width:90%;margin-left:30px">
              <el-slider
                v-model="newB"
                max="255"
                show-input>
              </el-slider>
            </div>
         </template>
         
         <!-- <p>A 颜色阈值</p>
          <template>
            <div class="block" style="width:90%;margin-left:30px">
              <el-slider
                v-model="newA"
                max="255"
                show-input>
              </el-slider>
            </div>
         </template> -->

         <div style="margin-top:150px;margin-bottom:50px;">
         <el-row>
           <el-button @click="getrgb()">获取</el-button>
           <el-button type="primary" @click="setrgb(newR,newG,newB)">确认</el-button>
  
         </el-row>
         </div>
      </div>
    </div>
  </div>
</template>



<script>

import NavName from '@/components/NavName'

export default {
  name: 'manage',
  components:{
      "Name-NavName": NavName
  },
  data () {
    return {
     imgmanage:require("../assets/img/test.jpg"),
     rgb:"(255,255,255)",
     hsv:"(255,0,100)",
     //newa:100,
    }
  },

  methods:{
   getrgb(){
     //alert("ok");
     //alert(this.test());
     var canvas=document.getElementById('myCanvas');
     var img=document.getElementById('imgs');
     canvas.style.display="none";
     var span=document.getElementById('span');
     //alert("获取成功");
     this.opensuccess();
     //this.getImageColor(canvas,img)
     this.getImageColor(canvas,img)
     
  },
  setrgb(newR,newG,newB){
    var canvas=document.getElementById('myCanvas');
     var img=document.getElementById('imgs');
     canvas.style.display="block";
     canvas.style.marginLeft="160px";
     var span=document.getElementById('span');
     //alert(newR + newG + newB);
     //this.getImageColor(canvas,img)
     this.setImageColor(canvas,img,newR,newG,newB) 
  },
   test(){
     return "666";
   },
   opensuccess() {
        this.$message({
          message: '获取成功！',
          type: 'success'
        });
  },

   getImageColor(canvas, img) {
        canvas.width = img.width;
        canvas.height = img.height;

        var context = canvas.getContext("2d");

        context.drawImage(img, 0, 0,canvas.width,canvas.height);

        // 获取像素数据
        var data = context.getImageData(0, 0, img.width, img.height).data;
        console.log(data)
        var r=1,g=1,b=1,a=1;
        // 取所有像素的平均值
        for (var row = 0; row < img.height; row++) {
            for (var col = 0; col < img.width; col++) {
        // console.log(data[((img.width * row) + col) * 4])
                if(row==0){
                    r += data[((img.width * row) + col)];
                    g += data[((img.width * row) + col) + 1];
                    b += data[((img.width * row) + col) + 2];
                    //a += data[((img.width * row) + col) + 3];
                }else{
                    r += data[((img.width * row) + col) * 4];
                    g += data[((img.width * row) + col) * 4 + 1];
                    b += data[((img.width * row) + col) * 4 + 2];
                   // a += data[((img.width * row) + col) * 4 + 3];
                }
            }
        }

        console.log(r,g,b)
        // 求取平均值
        r /= (img.width * img.height);
        g /= (img.width * img.height);
        b /= (img.width * img.height);
        //a /= (img.width * img.height);

        // 将最终的值取整
        r = Math.round(r);
        g = Math.round(g);
        b = Math.round(b);
        //a = Math.round(b);

        console.log(r,g,b)
        span.style.background = "rgb(" + r + "," + g + "," + b + ")"
        this.rgb = "(" + r + "," + g + "," + b + ")";
        this.newR=r;
        this.newG=g;
        this.newB=b;
        //this.newA=a;
        //计算hsv
        var rr=r/255;
        //alert("666");
        var gg = g / 255;
        var bb = b / 255;
       
        rr = rr.toFixed(2);
        gg = gg.toFixed(2);
        bb = bb.toFixed(2);
        //alert(rr);
        var Cmax = Math.max(rr,gg,bb);
        //alert(Cmax);
        var Cmin = Math.min(rr,gg,bb);
        var c = Cmax - Cmin
        c = c.toFixed(2);
        //alert(c);
        //H计算
        if(c == 0) { h = 0;} 
        if(Cmax == rr) { var h = 60 * ((gg - bb) / c + 0)} 
        if(Cmax == gg) { var h = 60 * ((bb - rr) / c + 2)}
        if(Cmax == bb) { var h = 60 * ((rr - gg) / c + 4)}
        //S计算
        if(Cmax == 0) { var s = 0}
        if(Cmax != 0) { var s = c / Cmax}
        //V计算
        var v = Cmax;
        
        h = Math.round(h);
        s = Math.round(s);
        v = Math.round(v);
        this.hsv = "(" + h + "," + s + "," + v + ")";

    },
    setImageColor(canvas, img,newR,newG,newB){
        canvas.width = img.width;
        canvas.height = img.height;
        let threshold = 160; //默认颜色阀值 为 114 －>和默认图相关
        var ctx = canvas.getContext("2d");
        
        ctx.drawImage(img, 0, 0, img.width, img.height);
          // 获取画布的像素信息
          let imageData = ctx.getImageData(0, 0, img.width, img.height),data = imageData.data;
          // 对像素集合中的单个像素进行循环，每个像素是由4个通道组成，所以要注意
          
          let i = 0;
          while (i < data.length) {
            let r = data[i++],
                g = data[i++],
                b = data[i++],
                a = data[i++];
            //计算透明度
            let alp = (255 - r) / (255 - threshold);
            //判断是否透明
            let isTransparent = r == 255 && g == 255 && b == 255 && a == 255;
            if (isTransparent) {
              data[i - 1] = 0;
            } else {
              data[i - 4] = newR;
              data[i - 3] = newG;
              data[i - 2] = newB;
              data[i - 1] = a !== 255 ? 255 - a : alp * 255; //处理透明的图片和不透明的图片
            }
          }
          //alert(111);
          // 将修改后的代码复制回画布中
          ctx.putImageData(imageData, 0, 0);
          //alert("修改成功！");
          //canvas.style.display="ruby";


    }

  }
}
</script>

<style scoped>
#manage{
  min-width: 1400px;
}
.left_box{
 width: 54%;
  float: left;
  margin-left: 50px;
  margin-right: 20px;
  margin-top: 10px;
  text-align: center;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
.right_box{
  width: 36%;
  height: 650px;
  float: left;
  margin-top: 10px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}
.right_box p{
  text-align: left;
  text-indent: 20px;
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
#imgs{
  z-index: 1;
}
#myCanvas{
  z-index: 2;
  /*display:none;*/
  display:none;
}
#span{
  float: left;
  width:40px;
  height: 20px;
  border-radius: 2px;
  border:1px solid gray;
  margin-top: 4px;
  margin-right: 10px;
}
#rgb_box{
  height: 30px;
  line-height: 30px;
  text-align: left;
  margin-top: 30px;
  margin-left: 30px;
  margin-bottom: 40px;
}
.rgb_info{
  float: left;
  line-height: 30px;
  margin-left: 30px;
  margin-right: 10px;
}
</style>