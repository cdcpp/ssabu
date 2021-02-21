<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String myctx = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<c:import url="/top"/>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<script type="text/javascript" src="<%=myctx%>/js/edit.js"></script>
<link rel="stylesheet" type="text/css" href="<%=myctx%>/css/top.css">

	<div id="contents">
		
	</div>
	<div class="section">
		<div class="container">
		<h2>회원정보 수정</h2>
		<hr style="color: gray">
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<form class="form-horizontal" id="mf" name="mf"
						action="memberEdit"  method="POST" onsubmit="return check()" enctype="multipart/form-data"> 
						
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputFile" class="control-label">프로필 사진</label>
							</div>
							<div class="col-sm-10">
								<input type="file" class="form-control" id="mImage" name="multiImage">
							</div> 
						</div>
						
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputInfo" class="control-label">인사말</label>
							</div>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="pInfo" name="pInfo" value="${loginUser.PInfo}">
								<!-- <div class="check_font" id="name_check"></div> -->
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputPassword3" class="control-label">이름</label>
							</div>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name" name="name" value="${loginUser.name}">
								<div class="check_font" id="name_check"></div>
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputPassword3" class="control-label">비밀번호</label>
							</div>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="pwd" name="pwd"
									placeholder="비밀번호를 입력하세요">
								<div class="check_font" id="pwd_check"></div>
							</div>

						</div>
						
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputEmail3" class="control-label">비밀번호 확인</label>
							</div>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="checkPwd"
									name="checkPwd" placeholder="비밀번호를 입력하세요">
							</div>
						</div>
						

						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputEmail3" class="control-label">핸드폰</label>
							</div>
							<div class="col-sm-10">
								<table>
									<tbody>
										<tr>
											<td><select id="hp1" name="hp1">
													<option value="010">010</option>
													<option value="011">011</option>
													<option value="016">016</option>
													<option value="019">019</option>
											</select></td>
											<td><span>-</span></td>
											<td><input type="text" class="form-control" id="hp2"
												name="hp2"></td>

											<td><span>-</span></td>
											<td><input type="text" class="form-control" id="hp3"
												name="hp3"></td>
										</tr>
										<tr>
											<td colspan="4"><div class="check_font" id="hp_check"></div></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputPassword3" class="control-label">이메일수신</label>
							</div>
							<div class="col-sm-10">
								<table>
									<tbody>
										<tr>
											<td><input type="radio" name="emailYN" id="emailY"
												value="y"> <span>수신함</span></td>
											<td><input type="radio" name="emailYN" id="emailN"
												value="n"> <span>수신안함</span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>

					
						<div class="form-group">
							<div class="col-sm-2">
								<label for="inputPassword3" class="control-label">생년월일</label>
							</div>
							<div class="col-sm-10">
								<table>
									<tbody>
										<tr>
											<td><input type="text" class="form-control" id="birth1">
											</td>
											<td><span>년</span></td>
											<td><input type="text" class="form-control" id="birth2">
											</td>
											<td><span>월</span></td>
											<td><input type="text" class="form-control" id="birth3">
											</td>
											<td><span>일</span></td>
										</tr>
										<tr>
											<td colspan="6"><div id="birth_check"></div></td>
										</tr>
									</tbody>
								</table>
								<input type="hidden" id="midx" name="midx" value="${loginUser.midx}">
								<input type="hidden" id="hp" name="hp">
								<input type="hidden" id="birth" name="birth">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="reset" class="btn btn-danger">다시작성</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn btn-success">수정하기</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>