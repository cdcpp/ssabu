     var x=new Array(10);
        var y=new Array(10);
        $(function(){
            for(var i=0;i<x.length;i++){
               x[i]=-50;
               y[i]=-50;
            }
            w=setInterval("addxy()",100);
            w1=setInterval("moveFlower()",100);
            
        })
        function addxy(){
           for(var i=0;i<x.length;i++){ 
               x[i]=x[i]+Math.random()*10;
           }
           for(var i=0;i<y.length;i++){
               if(y[i]>700){
                    y[i]=-50;
                    x[i]=-50;
               } 
               y[i]=y[i]+Math.random()*15;
           }
        }
        function moveFlower(){
                $('#flower1').css('transform','translate('+x[0]+'px,'+y[0]+'px)');
                $('#flower2').css('transform','translate('+(x[1]+100)+'px,'+(y[1]+80)+'px)');
                $('#flower3').css('transform','translate('+(x[2]+170)+'px,'+(y[2]+200)+'px)');
                $('#flower4').css('transform','translate('+(x[3]+110)+'px,'+(y[3]+300)+'px)');
                $('#flower5').css('transform','translate('+(x[4]+200)+'px,'+(y[4]+300)+'px)');
                $('#flower6').css('transform','translate('+(x[5]+30)+'px,'+(y[5]+300)+'px)');
                $('#flower7').css('transform','translate('+(x[6]+486)+'px,'+(y[6]+300)+'px)');
                $('#flower8').css('transform','translate('+(x[7]+324)+'px,'+(y[7]+300)+'px)');
                $('#flower9').css('transform','translate('+(x[8]+256)+'px,'+(y[8]+300)+'px)');
                $('#flower10').css('transform','translate('+(x[9]+128)+'px,'+(y[9]+300)+'px)');
        }