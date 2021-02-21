<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String ctx=getServletContext().getContextPath();%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    
       <link rel="stylesheet" type="text/css" href="<%=ctx%>/css/top.css">
</head>

<script type="text/javascript">

/*  */
$(function(){
	$('#user_del_btn').click(function(){
		if($('#bpwd').val()!=$('#bpwd_chk').val()){
			 alert('비밀번호가 일치하지 않습니다');
			 return false;
		}
		alert('탈퇴처리 되었습니다. 감사합니다');
		$('#user_del_form').submit();
	});
});

/*  */
var loginMidx=${loginUser.midx};
function submitFing(mIdx) {
	$('#mIdx').val(mIdx);
	fmf.submit();
}
function showfingList(){
	//alert("팔로잉목록~~");
	
	$.ajax({
		type:'POST',
		url:'showfingList',
		data:'mIdx='+loginMidx,
		dataType:'json',
		cache:false,
		success:function(res){
			//alert(JSON.stringify(res));
			 var str="<ul class='list-group'>";
			str+="<li class='list-group-item active'>";
			
			str+="::팔로잉 목록::</li>";
			$.each(res, function(i,item){
				str+="<li class='list-group-item' id='up"+item.midx+"'>";
				str+="<img height='50px' width='50px' src='images/noProfile.jpg'>"
				str+="</img>"
				
				str+="<a href='#' onclick='submitFing("+item.midx+")' name='mIdx'>";
				str+=item.name;
				str+="</a>"
				
				str+="</li>";
			});
			
			str+="</ul>";
			$('#fing').html(str); 
		},
		error:function(e){
			//alert("error: "+e.status);
			alert("팔로우가 없거나 서버오류입니다.");
		}
	});
}//------------------------------------
function showfwerList(){
	$.ajax({
		type:'GET',
		url:'showfwerList?mIdx='+loginMidx,
		dataType:'json',
		cache:false,
		success:function(res){
			//alert(JSON.stringify(res));
			var str="<ul class='list-group'>";
			str+="<li class='list-group-item active'>";
			str+="::팔로워 목록::</li>";
			$.each(res, function(i,item){
				str+="<li class='list-group-item' id='up"+item.midx+"'>";
				str+="<img height='50px' width='50px' src='images/noProfile.jpg'>"
				str+="</img>"
				str+="<a href='#' onclick='submitFing("+item.midx+")' name='mIdx'>";
				str+=item.name;
				str+="</a>"
				str+="</li>";
			});
			str+="</ul>";
			$('#fing').html(str);
		},
		error:function(e){
			//alert("error: "+e.status);
			alert("팔로워가 없거나 서버오류입니다.");
		}
	});
}//------------------------------------
</script>
<!-- 회원탈퇴 Modal -->
		<div class="modal" id="dropout" var="member">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">SSABU 회원탈퇴 신청</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<form id="user_del_form" action="updatecheckpwd" method="GET" >
				<div class="modal-body">
					<table class="table table-borderless">
						<li>본인여부를 확인하기 위해서 등록된 비밀번호를 입력해주세요.</li>
						<tr>
									<th scope="row"><span>이름</span></th>
									<td colspan="2"><strong>${loginUser.name }</strong></td>
						</tr>					
						<tr><td colspan="2"><input type="password" id="bpwd" name="bpwd" class="form-control" placeholder="비밀번호를 입력하세요"></td></tr>					
					</table>
				</div>
                <input type="hidden" id="bpwd_chk" value="${loginUser.pwd}">
                <input type="hidden" id="" name="midx" value="${loginUser.midx}">
				<!-- Modal footer -->
				<div class="modal-footer">
					<button id="user_del_btn" type="button" class="btn btn-success">확인</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div>
<body>
<div class="row" id="topLow">
        <div id="banner">
            <a href="<%=ctx %>/mainboard" id="bannerA">SSABU</a>
        </div>
        <div id="timeLine">
            <a href="#" id="timeLineA">타임라인</a>
        </div>
       <div id="search">
            <form class="input-group mb-3" id="searchF" action="searchF">
               <input type="text" class="form-control" placeholder="Search"  id="searchText" name="searchText">
               <div class="input-group-append">
                   <button class="btn" id="searchButton">
                      <img src="images/magnifier.png" width="16">
                   </button> 
               </div>
            </form>
        </div>

    <div id="profile1">
                <img class="dropdown-toggle" data-toggle="dropdown" src="profile_images/${loginUser.MImage}"/>

                  <ul class="dropdown-menu">
                   <li><a href="#" onclick="showfingList()">팔로잉목록</a></li>
                   <li><a href="#" onclick="showfwerList()">팔로워목록</a></li>
                    <li><a href="<%=ctx %>/userEdit">회원정보수정</a></li>
                    <li><a data-toggle="modal" href="#dropout">회원탈퇴</a></li>
                    <li><a href="<%=ctx %>/logout">로그아웃</a></li>
                  </ul>
       </div>

              	
             	
    </div>
    <div id="fing"></div>  
    <form action="mainboard" name="fmf" method="post">
    	<input type="hidden" id="mIdx" name="mIdx"/>
    </form>
