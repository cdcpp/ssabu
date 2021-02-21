<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String ctx=getServletContext().getContextPath();%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    
       <link rel="stylesheet" type="text/css" href="<%=ctx %>/css/top.css">


    <style>
          td{
       padding: 0px 0px 0px 0px;
       border : 1px solid white;
        
    }
    tr{
        padding-top: 0px;
        margin-top : 0px;
    }
    .intext{
        left:0px;
        width:0px;
        font-size:17px;
        font-weight: bold;
        position: absolute;
        color: antiquewhite;
    }
    .text1{
        left:50px;
        width:120px;
        top:100px;
        position: absolute;
    }
    .rep{
        font-weight: bold;
        left:0px;
        bottom:0px;
        position: absolute;
        color: antiquewhite;
    }
        
        .profile {
            position: relative;
            padding-left: 140px;
            min-height: 118px;
        }

        .profile>.profileImage {
            border-radius: 50%;
            position: absolute;
            left: 0;
            top: 50%;
            margin-top: -59px;
            width: 100px;
    		height: 100px;
        }

        .profile>span {
            display: block;
            max-width: 600px;
            font-size: 20px;
            line-height: 1.4;
            overflow: hidden;
            max-height: 2.7em;
            word-wrap: break-word;
        }

        .ok {
            font-size: 11pt;
            height: 40px;
            width: 100px;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
            border-bottom-left-radius: 20px;
            border-bottom-right-radius: 20px;
            box-shadow: none;
            background-color: pink;
            color: white;
            /*margin-left: 500px;*/
            margin-top:5px;
        }
        #visit{
            font-size: 15px;
            margin-top: 20px;
        }
        
         #content1,#content2,#content3 {
            border: 1px solid brown;
        }
        .radio{
            margin: 20px;
        }
         
    </style>
     <script>
        var cnt=1;
        var beforeid;
        var sw;
        function showContents(id) {
            if(beforeid==id){
                $('#'+id).hide();
                return;
            }
            beforeid = id;
            init();
          
                $('#'+id).show();
            
        
        }
    
         
        $(function(){
            init();
              $("#bt2").on("click",function(){
          $("#2").slideToggle();  
       });
        });
        function init(){
            $('#content1').hide();
            $('#content2').hide();
            $('#content3').hide();
        }

    </script>
</head>

<body>
    <div class="row" id="topLow">
        <div id="banner">
            <a href="#" id="bannerA">SSABU</a>
        </div>
        <div id="timeLine">
            <a href="#" id="timeLineA">타임라인</a>
        </div>
        <div id="search">
            <form class="input-group mb-3" >
            <input type="text" class="form-control" placeholder="Search"  id="searchText">
            <div class="input-group-append">
                <button class="btn" type="submit" id="searchButton">
                <img src="images/magnifier.png" width="16">
                </button> 
            </div>
            </form>
        </div>
        <div id="profile">
            <img src="images/noprofile.jpg">
        </div>
    </div>
    <div class="container" style="width:800px;">
   <div >
    <p class="profile">
        <img src="images/noprofile.jpg" alt="" class="profileImage">
        <span>프로필 자기소개</span><br>
        <span>홍길동님 홈페이지 <button class="ok">팔로잉</button></span>
        <span id="visit">오늘 방문자수 | 총 방문자수</span>
    </p>
    </div>
    <div >
        <div class="row">
            <div class="col">
                <a href="#" onclick="showContents('content1')">정렬순서</a>
            </div>
            <div class="col">
                <a href="#" onclick="showContents('content2')">태그</a>
            </div>
            <div class="col">
                <a href="#" onclick="showContents('content3')">게시판</a>
            </div>
        </div>
       <!-- <div class="row">
            <div class="col">
                <div id="contents">
                </div>
            </div>
        </div>-->
        <!--정렬순서----------------->
        <div id="content1" class="row">
            <div class="col">
                <input type="radio" name="sort" class="radio">
                <label for="radio">최신순</label>
            </div>
            <div class="col">
                <input type="radio" name="sort" class="radio">
                <label for="radio">공감순</label>
            </div>
            <div class="col">
                <input type="radio" name="sort" class="radio">
                <label for="radio">댓글순</label>
            </div>
            <div class="col">
                <button class="ok">확인</button>
            </div>
        </div>
        <!------------------------->
        
        <!--태그-------------------->
        <div id="content2" class="row">
            <div class="row">
                <div class="col">#das</div>
            </div>
            
        </div>
        <!------------------------->
        <div id="content3" class="row">
            <div class="row">
                <div class="col">게시판1</div>
                 <div class="col">게시판2</div>
            </div>
            
        </div>
    </div>
     <div id="postlist_area" >
      <a class="button" id="bt2" style="font-size:15px;font-weight:bold" >2019.02</a>
  
        
        <table id="2">
          <tr>
              <td style="position:relative"><a><img src="images/img02.png" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img04.png" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">오늘은 이걸 먹어볼까요~~
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img05.jpg" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td>   
          </tr>
          <tr>
              <td style="position:relative"><a><img src="images/img06.jpg" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img07.jpg" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img08.jpg" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
          </tr>
           <tr>
            <td style="position:relative"><a><img src="images/img09.jpg" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img10.jpg" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img04.png" style="width:239px; height:239px"></a><span class="intext"
              >02.15</span><span class="text1">오늘 본 영화?????
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
          </tr>
      </table>
      
       <a style="font-size:15px;font-weight:bold">2019.03</a>
  
        
        <table id="3">
          <tr>
              <td style="position:relative"><a><img src="images/img02.png" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img04.png" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">오늘은 이걸 먹어볼까요~~
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img05.jpg" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td>   
          </tr>
          <tr>
              <td style="position:relative"><a><img src="images/img06.jpg" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img07.jpg" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img08.jpg" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
          </tr>
           <tr>
            <td style="position:relative"><a><img src="images/img09.jpg" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img10.jpg" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
              <td style="position:relative"><a><img src="images/img04.png" style="width:239px; height:239px"></a><span class="intext"
              >03.15</span><span class="text1">오늘 본 영화?????
              </span><span class="rep">♡13&nbsp;&nbsp;〓17</span></td> 
          </tr>
      </table>
    </div>
    </div>
</body>
</html>