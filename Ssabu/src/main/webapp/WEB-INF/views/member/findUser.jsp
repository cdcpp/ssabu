<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<%
	String ctx = getServletContext().getContextPath();
%>
<link href="<%=ctx%>/css/findUser.css" type="text/css" rel="stylesheet">
<script>
	
	$(function(){
		// 비밀번호 정규식
		
	});
	
	
	
	
	
	function searchId1(){
		if(!sif.name.value){
			alert('이름을 입력해주세요'); 
			sif.name.focus();
			return false;
		}
		if(!sif.hp2.value||!sif.hp3.value){
			alert('전화번호를 입력해주세요');
			return false;
		}
		var hp=sif.hp1.value+sif.hp2.value+sif.hp3.value;
		var name= sif.name.value;
		$.ajax({
		type:'POST',
		url:'searchId',
		data:'hp='+hp+'&name='+name,
		dataType:'JSON',
		cache:false,
		success:function(res){
			//alert(JSON.stringify(res));
			if(res.email!=null){
				var str="<p>"
				str+=res.name+" 회원님의 이메일은 "+res.email+" 입니다."
				str+="</p>"
			}else{
				var str="<p>"
					str+="존재하지않는 회원정보입니다.";
					str+="</p>"
			}
			$('#idSearchResult').html(str);
			$('input').val("");
		},
		error:function(e){
			alert('error : '+e.status);
		}
		});
		
	}
	
	function searchPwd1(){
		if(!spf.email.value){
			alert('이메일을 입력해주세요');
			spf.email.focus();
			return false;
		}
		if(!spf.pname.value){
			alert('이름을 입력해주세요');
			sif.pname.focus();
			return false;
		}
		if(!spf.php2.value||!spf.php3.value){
			alert('전화번호를 입력해주세요');
			return false;
		}
		
		var email=spf.email.value;
		var hp=spf.php1.value+spf.php2.value+spf.php3.value;
		var name= spf.pname.value;
		$.ajax({
		type:'POST',
		url:'searchPwd',
		data:'hp='+hp+'&name='+name+'&email='+email,
		dataType:'JSON',
		cache:false,
		success:function(res){
			//alert(JSON.stringify(res));
			if(res.pwdChange==1){
				var str='<form id="changePwdf">';
				str+='<table id="updatePwdTable">';
				str+='<tr><td><h3>비밀번호 변경</h3></td></tr>'
				str+='<tr><td>새 비밀번호</td><td colspan="3"><input type="password" id="changePwd" name="changePwd" class="name"></td></tr>';
				str+='<tr><td colspan="6"><div id="changePwd_check"></div></td></tr>';
				str+='<tr><td>비밀번호 확인</td><td colspan="3"><input type="password" id="conPwd" name="conPwd" class="name"></td></tr>';
				str+='<tr><td><button id="updatePwd" type="button" onclick="changePwd1(\''+email+'\')">비밀번호 변경</button></td></tr>';
				str+='</table>';
				str+='</form>';
				
			
			}
			else{
				var str="<p>"
					str+="존재하지않는 회원정보입니다.";
					str+="</p>"
			}
			$('#idSearchResult').html(str);
			str="";
			$('input').val("");
		},
		error:function(e){
			alert('error : '+e.status);
		}
		});
	
		
		
	}
	
	function changePwd1(email){
		var changePwd=changePwdf.changePwd.value;
		var conPwd=changePwdf.conPwd.value;	
		var pwJ = /^[A-Za-z0-9]{6,12}$/; 
		
		
			if(pwJ.test($('#changePwd').val())){
				console.log('true');
				$('#changePwd_check').text('');
			}else{
			
				$('#changePwd').val("");
				$('#conPwd').val("");
				console.log('false');
				$('#changePwd_check').text('6~12자만 입력할수있습니다.');
				$('#changePwd_check').css('color','red');
				return false;
			}
		
		
		
		if(changePwd!=conPwd){
			alert('비밀번호가 서로 일치 하지 않습니다.');
			return false;
		}
		$.ajax({
		type:'POST',
		url:'changePwd',
		data:'changePwd='+changePwd+'&conPwd='+conPwd+'&email='+email,
		dataType:'JSON',
		cache:false,
		success:function(res){
			//alert(JSON.stringify(res));
			if(res.result==1){
				alert('비밀번호가 변경되었습니다.');
				location.href="<%=ctx%>/main"; 
			}
			else{
				alert('비밀번호 변경이 실패하였습니다.')
			}
			
		},
		error:function(e){
			alert('error : '+e.status);
		}
		});
	}
</script>

<div class="container" style="text-align: center">
	<div class="row">
		<div class="col-sm" id="idSearchResult">
		</div>
	</div>
	<div class="row">
		<div class="col-md-6" >
			<form id="sif">
				<table id="searchIdTable" >
					<tr>
						<td colspan="2" id="searchIdTitle">아이디 찾기</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" class="name" name="name"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><select class="tel1" name="hp1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
						</select>- <input type="text" class="tel2" name="hp2">- <input type="text"
							class="tel3" name="hp3"></td>
					</tr>
					<tr>
						<td colspan="2" class="search"><button id="searchId"  onclick="searchId1()" type="button">아이디찾기</button></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="col-md-6">
			<form id="spf">
				<table id="searchPwdTable">
					<tr>
						<td colspan="2" id="searchIdTitle">비밀번호 찾기</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" class="id" name="email"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" class="name" name="pname"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><select class="tel1" name="php1">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
						</select>- <input type="text" class="tel2" name="php2">- <input type="text"
							class="tel3" name="php3"></td>
					</tr>
					<tr>
						<td colspan="2" class="search"><button id="searchPwd" type="button" onclick="searchPwd1()">비밀번호
								찾기</button></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- col -->
	</div>
	<!-- row -->
</div>
<!-- container -->