<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String ctx = getServletContext().getContextPath();
%>
<c:import url="/top" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- <script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script> -->
<script type="text/javascript" src="<%=ctx%>/js/dropzone.js"></script>
<link rel="stylesheet" type="text/css" href="css/dropzone.css">
<!-- <link rel="stylesheet"
	href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css">  -->
<script>

$(function(){
	var tagJ = /^#[가-힣a-zA-Z0-9_]{1,200}$/;
	var tag;
	$('#tname').blur(function(){
		

		if($('#tname').val()==""){
			$('#tname_check').text('');
			return false;
		}	
		
		tag = $('#tname').val().split(' ');
		for (var i in tag){
			if(tagJ.test(tag[i])){
				console.log('true');
				$('#tname_check').text('');
				
			}
			else{
				console.log('false');
				$('#tname').val("");
				$('#tname_check').text('태그 형식을 입력해야 합니다.');
				$('#tname_check').css('color','red');
				
				return false;
			}
		}
		articleSub.tagArr.value = tag;
		
		
	 }); 
});


function check(){
	
	 if(!articleSub.subject.value){
		alert('제목을 입력하세요');
		articleSub.subject.focus();
		return false;
	} 
	 
	return true;
}//
</script>
<head>
<style>
.container {
	width: 900px;
	margin-top: 20px
}
</style>

<title>글쓰기</title>




</head>
<body>



	<div id="cyWrap" class="container">
		<div id="editorWrap">
			<div class="title" style="text-align: center">
				<h3>글쓰기</h3>
				<hr>
			</div>



			
			<div id="postData" class="form-group">
				<form action="${pageContext.request.contextPath}/imageUpload"
					class="dropzone" method="post" enctype="multipart/form-data"
					>
					  <div class="fallback">
						<input id="file" name="file" type="file" multiple />
					</div>  
					</form>
					<form id="articleSub" method="POST" action="postInsert" onsubmit="return check()">
						<div class="postTitle">
							<input class="form-control" id="subject" name="subject" type="text"
								placeholder="당신의 이야기를 들려주세요"
								style="height: 50px; width: 100%; padding-left: 20px;">
						</div>
						<hr>
						<div class="postContents">
							<input class="form-control postContent" id="content"
								name="content" type="text" maxlength="70"
								placeholder="소중한 일상을 기록해보세요"
								style="height: 150px; width: 100%; padding-left: 20px;" /> <input
								type="hidden" id="userIdx" name="userIdx" value="" /> <input
								type="hidden" id="bidx" name="bidx" value="${bidx}" />
						</div>
						<div class="postExtraSet" style="font-size: 12pt;">
							<table class="table" style="margin-top: 20px;">
								<tbody>

									<tr>
										<td width="20%">공개권한</td>
										<td width="80%"><select class="form-control"
											id="pstate" name="pstate"
											style="width: 20em">
												<option value="0">전체공개</option>
												<option value="1">팔로우공개</option>
												<option value="2">비공개</option>
										</select></td>
									</tr>
									<tr>
										<td width="20%">태그</td>
										<td width="80%"><input class="form-control" type="text"
											id="tname" name="tname" width="100%"
											placeholder="공백으로 구분 문자, 숫자, _(언더바)만 허용" /> <input
											type="hidden" value="" name="tagArr" /> <!-- 분류된 태그를 제출한다. -->
											<div id="tname_check"><div></td>
											
									</tr>
									<tr>
										<td width="20%">댓글 작성권한</td>
										<td width="80%"><select class="form-control"
											id="replyAccessSelect" name="replyAccessSelect"
											style="width: 20em">
												<option accesskey="0">모두에게허용</option>
												<option accesskey="1">팔로우에게만 허용</option>
												<option accesskey="2">비허용</option>
										</select></td>
									</tr>
									<tr>
										<td width="20%">검색허용</td>
										<td width="80%"><select class="form-control"
											id="searchAccessSelect" name="searchAccessSelect"
											style="width: 20em">
												<option accesskey="0">허용</option>
												<option accesskey="1">비허용</option>
										</select></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											
											<button class="btn btn-primary" type="submit" id="sub">작성</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	$(function() {

		$('#tagArr').keyup(function(e) {
			if (e.keyCode == 13) {
				//엔터키를 누르면 공백을 기준으로 태그를 분리하여 아래 label로 띄워준다.
				var allTag = this.val();

			}
		});

		var save = function() {
			//게시글을 임시저장하는 함수(구현보류)

		}

	});
</script>