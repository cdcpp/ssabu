<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%
   String ctx = getServletContext().getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<c:import url="/top"/>
<head>
<meta charset="UTF-8">
<title>Document</title>
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

jQuery library
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

Popper JS
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

Latest compiled JavaScript
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> -->

<link rel="stylesheet" type="text/css" href="<%=ctx%>/css/top.css">
<link rel="stylesheet" type="text/css"
   href="<%=ctx%>/css/mainBoard.css">


<script>

$(function() {
   str='${mInfo.name}님 홈페이지'
   str+=' <button type="button" class="ok" onclick="pado()">파도타기</button>';
   if(${state==2}){ 
       
   }
   else if(${state==0}){
      str+='<button id="doFollow"class="ok" onclick="doFollow()">팔로잉</button>';
   }else{
      str+='<button id="unFollow"class="ok" onclick="unFollow()">팔로잉 해제</button>';
   }  
   $('#dof').append(str);
});

function pado() {
   var fff = 1;
   var ff=0;
   $('#flag').val(fff);
   pf.submit();
   $('#flag').val(ff);
   
}
function doFollow(){
   var mIdx = $('#fmIdx').val();
   //alert(mIdx);
     $.ajax({
      type:"GET",
      url:"doFollow?mIdx="+mIdx,
      dataType:"JSON",
      cache:false,
      success:function(res){
         //alert(res);
         /* $('#doFollow').text("팔로우 해제");
         $('#doFollow').attr("onclick","unFollow()"); */
         str='${mInfo.name}님 홈페이지';
         str+=' <button type="button" class="ok" onclick="pado()">파도타기</button>';
         str+='<button id="unFollow"class="ok" onclick="unFollow()">팔로잉 해제</button>';
         $('#dof').html(str);
      },
      erroer:function(e){
         alert("error: "+e.status);
      }
   });  
   
}
function unFollow(){
   //alert("바뀜~");
   var mIdx = $('#fmIdx').val();
   //alert(mIdx);
     $.ajax({
         type:"GET",
         url:"unFollow?mIdx="+mIdx,
         dataType:"JSON",
         cache:false,
         success:function(res){
            //alert(res);
            /* $('#doFollow').text("팔로우");
            $('#doFollow').attr("onclick","doFollow()"); */
            str='${mInfo.name}님 홈페이지'
            str+=' <button type="button" class="ok" onclick="pado()">파도타기</button>'
            str+='<button id="doFollow"class="ok" onclick="doFollow()">팔로잉</button>'
            
            $('#dof').html(str);
         },
         error:function(e){
            alert("error: "+e.status);
         }
      });  
   
}
function check(){
   
    if(!cbf.bname.value){
      alert('게시판명을 입력하세요');
      cbf.bname.focus();
      return false;
   } 
    return true;
}
     
     var cnt = 1;
    var beforeid;
    var sw=0;
    function showContents(id) {
       if (beforeid == id&&sw==0) {
          sw=1;
          $('#' + id).hide();
          return;
       }
       beforeid = id;
      sw=0;
       init();
       $('#' + id).show();

    }
    
    function init() {
       $('#content1').hide();
       $('#content2').hide();
       $('#content3').hide();
    }

    $(function() {
       init();
       $("#bt2").on("click", function() {
          $("#2").slideToggle();
       });
    });
    
   function tagClick(tname){
      tmf.tname.value=tname;
      tmf.submit();
   }
   
   
    </script>
</head>
<body>
   
   <div class="container" style="width: 800px;">
   
   
      <div>
         <p class="profile">
            
            <img src="profile_images/${mInfo.MImage}" alt="" class="profileImage"> 
            <span>${mInfo.PInfo}</span><br> 
            <span id="dof">
               
               <%-- <c:if test="${state==2}">
               </c:if>
               <c:if test="${state==0}">
               <button id="doFollow"class="ok" onclick="doFollow()">팔로잉</button>
               </c:if>
               <c:if test="${state==1}">
               <button id="unFollow"class="ok" onclick="unFollow()">팔로잉 해제</button>
               </c:if> --%>
               
               
            </span><span id="visit">총 게시글 수:${pCount} | 총 팔로워 수:${fwer}</span>
            <input type="hidden" id="fmIdx" name="fmIdx" value="${mInfo.midx}">
         </p>
         
      </div>
      <div>
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
            <c:if test="${loginUser.midx eq mInfo.midx}">
            <div class="col">
               <a href="createPost?bidx=${bidx}">글쓰기</a>
            </div>
            </c:if>
            <c:if test="${loginUser.midx ne mInfo.midx}">
            
            </c:if>
         </div>
         
         <!-- <div class="row">
            <div class="col">
                <div id="contents">
                </div>
            </div>
        </div>-->
         <!--정렬순서----------------->
            <form action="mainboard" method="post">
         <div id="content1" class="row">
            <div class="col">
               <input type="radio" name="sort" value="date" checked class="radio"> <label
                  for="radio">최신순</label>
            </div>
            <div class="col">
               <input type="radio" name="sort"  value="like" class="radio"> <label
                  for="radio">공감순</label>
            </div>
            <div class="col">
               <input type="radio" name="sort"  value="hit" class="radio"> <label
                  for="radio">조회순</label>
            </div>
            <div class="col">
               <button class="ok" type="submit">확인</button>
            </div>
         </div>
            </form>
            
         <!------------------------->

         <!--태그-------------------->
         <form action="mainboard" method="post" id="tmf" name="tmf">
         <div id="content2" class="row" style="width:740px">
            <div class="row">
               <c:forEach items="${tname1}" var="t">
                  <div>
                     &nbsp;&nbsp;&nbsp;&nbsp;<a href="#"  style="color:red;"onclick="tagClick('${t.key}')">${t.key}</a>
                  </div>
               </c:forEach>
               <input type="hidden" id="tname" name="tname">
            </div>

         </div>
         </form>
         <!------------------------->
         <div id="content3" class="row">
            <div class="row">
               <c:forEach var="board" items="${boards}">
                  <div class="col">
                     <a href="mainboard?bidx=${board.bidx}" onclick="saveidx(${board.bidx})">${board.bname}</a>
                     <input type="hidden" name="bidx" id="bidx">
                  </div>
               </c:forEach>
               <div class="row">
                  <div class="col">
                  <c:if test="${loginUser.midx eq mInfo.midx}">
                     <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#myModal">게시판 만들기</button>
                  </c:if>
                  <c:if test="${loginUser.midx ne mInfo.midx}">
                  </c:if>
                  </div>
                  
               </div>
            </div>

         </div>
      </div>
      <div id="postlist_area">

<!-- -->
         <c:forEach items="${post}" var="i">
            <a class="button" id="bt2"
               style="font-size: 15px; font-weight: bold">${i.key}</a>
            <table id="2">
               <c:forEach items="${i.value}" var="k" varStatus="status">
                  <c:if
                     test="${status.index==0||status.index%3==0&&status.index!=1}">
                     <tr>
                  </c:if>
                  <c:if test="${k.imageFile.imgName=='noImage'}">
                     <td style="position: relative"><a href="showpost?pIdx=${k.getPIdx()}"><img
                           src="images/noImage.png" style="width: 239px; height: 239px"></a><span
                        class="intext"><fmt:formatDate value="${k.pdate}"
                              pattern="MM.dd" /></span><span class="text1">${k.subject} </span>
                              <span class="rep" style="font-size: 20px">♡${k.likeCount}</span> 
                        </td>
                  </c:if>
                  <c:if test="${k.imageFile.imgName!='noImage'}"> 
                     <td style="position: relative"><a href="showpost?pIdx=${k.PIdx}"><img
                           src="post_images/${k.imageFile.imgName}"
                           style="width: 239px; height: 239px"></a><span class="intext"><fmt:formatDate
                              value="${k.pdate}" pattern="MM.dd" /></span><span class="text1">
                     </span> <span class="rep" style="font-size: 20px">♡${k.likeCount}</span></td>
                  </c:if>
                  <c:if test="${status.index!=0&&status.index%3==2}">
                     </tr>
                  </c:if>
               </c:forEach>

            </table>
         </c:forEach>

      </div>
   </div>

   <!-- The Modal -->
   
   <div class="modal" id="myModal">
      <div class="modal-dialog">
         <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
               <h4 class="modal-title">게시판 만들기</h4>
               <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <form id="cbf" action="createBoard" method="POST" onsubmit="return check()">
            <div class="modal-body">
               <table class="table table-borderless">

                  <tr><td colspan="2"><input type="text" id="bname" name="bname" class="form-control" placeholder="게시판명을 입력하세요"></td></tr>

                  <tr>
                     <td width="20%">공개권한</td>
                     <td width="80%"><select class="form-control" id="pstate"
                        name="bstate" style="width: 20em">
                           <option value="0">전체공개</option>
                           <option value="1">팔로우공개</option>
                           <option value="2">비공개</option>
                     </select></td>
                  </tr>
               </table>
            </div>



            <!-- Modal footer -->
            <div class="modal-footer">
               <button type="submit" class="btn btn-success">Save</button>
               <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
            </form>
         </div>
      </div>
   </div>
   <form action="mainboard" name="pf" method="post">
      <input type="hidden" id="flag" name="flag">
   </form>
</body>
</html>