<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String ctx=getServletContext().getContextPath(); %>
<c:import url="/top" />
<head>
<style>
.background{
	background-color: pink;
	padding-top: 30px;
	height:auto;
}
.container {
	background-color:white;
	padding-top: 30px;
	width: 650px;
}

.profileImage {
	height: 60px;
	width: 60px;
	border-top-left-radius: 50%;
	border-top-right-radius: 50%;
	border-bottom-left-radius: 50%;
	border-bottom-right-radius: 50%;
	margin-left: 15px;
	
}
.profile {
	font-size:11pt;
	margin-top:10px;
}
#content{
	width:100%;
	margin-left: 30px;
	margin-top: 30px;
	font-size:20pt;
}
#likeCount{
	width:2em;
	height:2em;
}
#replyCount{
	width:2em;
	height:2em;
}
.count{
	padding-left:20px;
	margin-top:30px;
}
#replyBox{
	height:150px;
	border-style:solid;
	margin:20px;
	border-width: 1px;
	border-color:#ffcccc;
	padding-left:0;
	padding-right:0;
}
#replyContent{
	width:100%;
	height:120px;
	border-style:none;
	padding-left:10px;
	padding-top:10px;
	text-overflow: auto;	
}
.form-check-label{
	margin-left:20px;
}
#replyList >*{
	font-size:10pt;
}
</style>
</head>
<body>
<div class="background">
	<div class="container">
		<div class="row">
			<div class="col-2">
				<c:if test="${pe.MImage eq null }">
					<img src="images/noprofile.jpg" alt="" class="profileImage img-rounded">
				</c:if>
				<c:if test="${pe.MImage ne null }">
				<img src="images/${pe.MImage}" alt="" class="profileImage img-rounded">
				</c:if>
					
			</div>
			<div class="profile col-10">
				<span><strong>${pe.name }</strong><br> 
					<%-- <fmt:formatDate value="작성날짜" pattern="yyyy-MM-dd"/> --%>
					<fmt:formatDate value="${postContent.pdate }" pattern="yyyy-MM-dd"/> | ${pState }
				</span>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p id="content" style="border-style: none;">${postContent.content }</p>
					<c:if test="${postImage ne null}">
					<c:forEach items="${postImage}" var="img">
						<c:if test="${img.imgName ne 'noImage'}">
						<img src="post_images/${img.imgName}" width="620px">
						</c:if>  
					</c:forEach>
				</c:if> 
			</div>
		</div>
		<div class="row">
			<div class="col count">
				<hr>
				<img src="<%=ctx %>/images/beforeLike.png" id="likeCount" name="likeCount" onclick="like()" >
				<span >&nbsp ${likeCount} &nbsp&nbsp</span>
				<img src="<%=ctx %>/images/replyCount.png" id="replyCount" name="replyCount" >
				<span>&nbsp ${replyCount}</span>
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col" style="font-size:10pt;padding-left:1.2em;color: gray;">
				<p>태그정보</p>
			</div>
		</div>
		<div class="row">
			<div class="col" style="font-size:10pt;padding-left:1.2em;">
				<p>댓글</p>
			</div>
		</div>
		<c:forEach items="${postReply}" var="re">
			<div class="row" style="margin-top:10px; margin-bottom:10px">
				<div class="col-2" style="padding-top:0;">
					<c:if test="${re.mvo[0].MImage eq null }">
						<img src="images/noprofile.jpg" alt="" 
							class="profileImage img-rounded">
					 </c:if>
					<c:if test="${re.mvo[0].MImage ne null}">
						<img src="images/'${re.mvo[0].MImage}'" alt="" 
							class="profileImage img-rounded">
					</c:if> 
				</div>
				<div class="col-10" id="replyList">
					<p><strong>${re.mvo[0].name}</strong></p>
					<p>${re.RContent}</p>
					<span><fmt:formatDate value="${re.RDate}" pattern="yyyy-MM-dd hh:mm"/></span>
					<span onclick="replyLevel()">답글쓰기</span>
				</div>
			</div>
		</c:forEach>
		<div class="row" style="padding-bottom:50px;padding-top:20px;">
			<div class="col " id="replyBox">
				<textarea id="replyContent" name="replyContent" placeholder="댓글을 입력해 주세요"  onkeyup="subReply()"></textarea><br>
				<div class="form-check">
					<label class="form-check-label">
						<input class="form-check-input" id="secretReply" name="secretReply" type="checkbox"  >비밀글
					</label>
				</div>
			</div><!-- col -->
		</div><!-- row -->
		<form action="replySub" id="replySub" method="get">
			<input type="hidden" id="pidxForReply" name="pidxForReply" value="${postContent.PIdx }">
			<input type="hidden" id="replyContentF" name="replyContentF"><!-- 댓글내용 -->
			<input type="hidden" id="secretReplyF" name="secretReplyF"><!-- 댓글비밀글여부 -->
		</form>
		<form action="likeUp" id="likeUp" method="get">
			<input type="hidden" id="likePidxF" name="likePidxF" value="${pidx}">
			<input type="hidden" id="likeMyPost" name="likeMyPost" value="${pe.midx }">
		</form>
	</div><!-- container -->
</div><!-- background -->
</body>
<script type="text/javascript">
	$(function(){
		
		
	});
	var like=function(){
		//회원번호랑 글번호를 받아와서 회원이 이미 누른 회원이면 카운트가 안 올라가게끔
		$('#likeUp').submit();
	}
 	/* 리플작성 */
	var subReply=function(){
		if(event.keyCode==13){
			//alert("클릭");
			if($('#secretReply').is(':checked')){
				$('#secretReplyF').val("1");
			}else {
				$('#secretReplyF').val("0");
			}
			$('#replyContentF').val($('#replyContent').val());
			$('#replySub').submit();
			
		}
		
	}

</script>