<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/top"/>
<head>
	<style>
			.container{
				width:900px;
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
				<form id="articleSub" method="GET">
				<div class="postTitle">
					<input class="form-control" id="title" name="title" type="text" placeholder="당신의 이야기를 들려주세요" 
					style="height:50px;width:100%;padding-left:20px;">
				</div>
				<hr>
				<div class="postContents">
					<input class="form-control postContent" id="content" name="content" type="text" maxlength="70" placeholder="소중한 일상을 기록해보세요"
						style="height:150px;width:100%;padding-left:20px;"/>
					<input type="hidden" id="userIdx" name="userIdx" value=""/>
					<input type="hidden" id="boardIdx" name="boardIdx" value=""/>
				</div>
				<div class="postExtraSet" style="font-size:12pt;">
				<table class="table" style="margin-top:20px;">
					<tbody>
						<tr>
							<td width="20%">사진</td>
							<td width="80%">
								<div class="btnWrap">
									<span title="사진추가" class="ce-toolbar-btn fa-camera"
										data-action="image"> 
										<input name="files" id="imageupload" 
											onclick="gtagFn()" type="file" multiple=""/>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td width="20%">공개권한</td>
							<td width="80%">
								<select class="form-control" id="publicContSelect" name="publicContSelect" style="width:20em">
									<option accesskey="0" >전체공개</option>
									<option accesskey="1" >팔로우공개</option>
									<option accesskey="2" >비공개</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="20%">태그</td>
							<td width="80%">
								<input class="form-control" type="text" id="tagArr" width="100%"
									placeholder="공백으로 구분 문자, 숫자, _(언더바)만 허용" />
								<input type="hidden" value="separateTag()" name="tagArr"/><!-- 분류된 태그를 제출한다. -->
							</td> 
						</tr>
						<tr>
							<td width="20%">댓글 작성권한</td>
							<td width="80%">
								<select class="form-control" id="replyAccessSelect" name="replyAccessSelect" style="width:20em">
									<option accesskey="0" >모두에게허용</option>
									<option accesskey="1" >팔로우에게만 허용</option>
									<option accesskey="2" >비허용</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="20%">검색허용</td>
							<td width="80%">
								<select class="form-control" id="searchAccessSelect" name="searchAccessSelect" style="width:20em">
									<option accesskey="0">허용</option>
									<option accesskey="1">비허용</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button class="btn btn-info" type="button" onclick="save()">임시저장</button>
								<button class="btn btn-primary" id="sub">작성</button>
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
	$(function(){
		
		$('#tagArr').keyup(function(e){
			if(e.keyCode==13){
				//엔터키를 누르면 공백을 기준으로 태그를 분리하여 아래 label로 띄워준다.
				var allTag=this.val();
				
			}
		});
		
		var save=function(){
			//게시글을 임시저장하는 함수(구현보류)
			
		}
		
	
	});
</script>