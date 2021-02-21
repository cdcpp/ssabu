/* db에서 hp에 unique 제약조건 추가
 * 핸드폰번호 중복확인 구현 예정
 * */



/**
 * 정규식 메소드.
 * 정규식과 일치하지 않았을 경우 해당 input의 value를 공백으로 만든다.
 * */
$(function() {
			var empJ = /\s/g;
			//아이디 정규식
			var idJ = /^[a-z0-9]{4,18}$/;
			// 비밀번호 정규식
			var pwJ = /^[A-Za-z0-9]{6,12}$/; 
			// 이름 정규식
			var nameJ = /^[가-힣]{2,6}$/;
			// 이메일 검사 정규식
			var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
			var mailJ1 = /^[a-z0-9A-Z]{4,30}$/;
			var mailJ2 = /^([a-zA-z]+\.)+[a-z]+$/;
			// 휴대폰 번호 정규식
			var phoneJ1 =/^[0-9]{3,4}$/;
			var phoneJ2 =/^[0-9]{4}$/;
			// 생년월일 정규식
			// 생일 유효성 검사
			var birthJ = /^[0-9]{4}$/;
			var birthJ2 = /^[0-9]{2}$/;
			flag = 1;
			
			$('#emailY').attr('checked',true);
			$('#m').attr('checked',true);

			
			  $('#email1').blur(function(){
						if(mailJ1.test($('#email1').val())){
							console.log('true');
							$('#email_check').text('');
							
						}else{
							console.log('false');
							$('#email1').val("");
							$('#email_check').text('영어와 숫자,4~30글자 이내로 입력해야 합니다.');
							$('#email_check').css('color','red');
							
							return false;
						}
					 });  
			 $('#email2').blur(function(){
					if(mailJ2.test($('#email2').val())){
						console.log('true');
						$('#email_check').text('');
						
					}else{
						console.log('false');
						$('#email2').val("");
						$('#email_check').text('이메일 형식을 입력해야 합니다.');
						$('#email_check').css('color','red');
						
						return false;
					}
				 }); 
			
			
			 $('#pwd').blur(function(){
					if(pwJ.test($('#pwd').val())){
						console.log('true');
						$('#pwd_check').text('');
						
					}else{
						$('#pwd').val("");
						console.log('false');
						$('#pwd_check').text('6~12자만 입력할수있습니다.');
						$('#pwd_check').css('color','red');
						
						return false;
					}
				 }); 
			 
			 $('#name').blur(function(){
				if(nameJ.test($('#name').val())){
					console.log('true');
					$('#name_check').text('');
					
				}else{
					$('#name').val("");
					console.log('false');
					$('#name_check').text('한글만 입력할수 있습니다.');
					$('#name_check').css('color','red');
				
					return false;
				}
			 }); 
			 $('#hp2').blur(function(){
					if(phoneJ1.test($('#hp2').val())){
						console.log('true');
						$('#hp_check').text('');
					
					}else{
						$('#hp2').val("");
						console.log('false');
						$('#hp_check').text('3~4자 숫자만 입력할수 있습니다.');
						$('#hp_check').css('color','red');
						
						return false;
					}
				 }); 
			 $('#hp3').blur(function(){
					if(phoneJ2.test($('#hp3').val())){
						hpCheck();
						console.log('true');
						$('#hp_check').text('');
						
					}else{
						$('#hp3').val("");
						console.log('false');
						$('#hp_check').text('4자리 숫자만 입력할수 있습니다.');
						$('#hp_check').css('color','red');
					
						return false;
					}
				 });
			 $('#birth1').blur(function(){
					var today = new Date();
				    var year = $('#birth1').val();
					var yearNow = today.getFullYear();
					if(!birthJ.test(year)){
						$('#birth1').val("");
						$('#birth_check').text('년도를 4글자로 입력');
						$('#birth_check').css('color', 'red');
					
						return false;
					}
					if(birthJ.test(year)){
						if(1900 >year || year > yearNow){
							$('#birth1').val("");
							$('#birth_check').html('년도를 확인해주세요');
							$('#birth_check').css('color','red');
							
							return false;
						}
						$('#birth_check').html('');
						
					}
					
					
					
				});
				
				$('#birth2').blur(function(){
					
					var month = $('#birth2').val();
					//alert(month+"///"+birthJ2.test(month));
					if(!birthJ2.test(month)){
						$('#birth2').val("");
						$('#birth_check').text('2자리의 월을 입력해라');
						$('#birth_check').css('color', 'red');
						return false;
					}
					if(birthJ2.test(month)){
						if (month < 1 || month > 12) {
							$('#birth2').val("");
					    	$('#birth_check').text('생년월일을 확인해주세요');
							$('#birth_check').css('color', 'red');
							return false;
					    
					    }
						
						$('#birth_check').text('');
					
					}
				});
			
				$('#birth3').blur(function(){
					var year = $('#birth1').val();
					var month = $('#birth2').val();
					var day = $('#birth3').val();
					if(!birthJ2.test(day)){
						$('#birth3').val("");
						$('#birth_check').text('2자리의 일을 입력해라');
						$('#birth_check').css('color', 'red');
						return false;
					}
					if(birthJ2.test(day)){
						 if (day < 1 || day > 31) {
							 $('#birth3').val("");
							 $('#birth_check').html('생년월일을 확인해주세요');
							$('#birth_check').css('color', 'red'); 
							return false;
					    	
					    }else if ((month==4 || month==6 || month==9 || month==11) && day==31) {
					    	$('#birth3').val("");
					    	$('#birth_check').html('생년월일을 확인해주세요');
							$('#birth_check').css('color', 'red'); 
							return false;
					    	 
					    }else if (month == 2) {
					    	 
					       	var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					       	
					     	if (day>29 || (day==29 && !isleap)) {
					     		$('#birth3').val("");
					     		$('#birth_check').html('생년월일을 확인해주세요');
								$('#birth_check').css('color', 'red'); 
								return false;
							}
						
					}
						$('#birth_check').html('');
				}
				});
				
			 
		});//end
		
		function hpCheck(){
			var hp=mf.hp1.value+mf.hp2.value+mf.hp3.value;
			$.ajax({
			type:'GET',
			url:'hpCheck?hp='+hp,
			dataType:'JSON',
			cache:false,
			success:function(res){
				//alert(JSON.stringify(res));	
				var hpok=res.hpcheck;
				if(parseInt(hpok)>0){
					$('#hp2').val("");
					$('#hp3').val("");
					$('#hp_check').text('이미 가입된 번호입니다.');
					$('#hp_check').css('color','red');
				}else{
					$('#hp_check').text("사용가능한 번호입니다.");
					$('#hp_check').css('color','green');
				}
			},
			error:function(e){
				alert('error : '+e.status);
			}
			});
		}//
		
			/**이메일(아이디) 중복확인 메소드
			 * 중복확인 버튼을 누르지 않았을때, 중복된 아이디일때 flag=0; (회원가입x)
			 * */
		  function emailCheck(email){
		      
		      $.ajax({
		         type:'GET',
		         url:'emailCheck?email='+email,
		         dataType:'JSON',
		         cache:false,
		         success:function(res){
		            //alert(JSON.stringify(res));
		            var ok=res.check;
		            if(parseInt(ok)>0){
		               flag=1;
		              
		               $('#email_check').html('중복된 아이디 입니다.');
		               $('#email_check').css('color','red');
		            }
		            else{
		            flag=0;
		               $('#email_check').html('사용가능한 아이디 입니다.'); 
		               $('#email_check').css('color','green');
		               
		            }
		         },
		         error:function(e){
		            alert('error : '+e.status);
		         }
		      });
		   } //이메일체크
	
		 /**
		  * 회원가입 칸에 빈칸,비밀번호 일치여부,중복확인 체크여부를 확인하는 메소드
		  * email,hp,birth 를 합쳐서 hidden type input에 value로 return함.
		  * */
		function check(){
			
			 if(!mf.email1.value){
				alert('이메일를 입력하세요');
				mf.email1.focus();
				return false;
			} 
			 
			if(!mf.pwd.value){
				alert('비밀번호를 입력하세요');
				mf.pwd.focus();
				return false;
			}
			
			if(mf.pwd.value != mf.checkPwd.value){
				alert('비밀번호가 달라요');
				mf.pwd.select();
				return false;
			}
			 
			if(!mf.name.value){
				alert('이름을 입력하세요');
				mf.name.focus();
				return false;
			}
			
			if(!mf.hp2.value||!mf.hp3.value){
				alert("연락처를 모두 입력하세요");
				mf.hp2.focus();
				return false;
			}
			if(!mf.birth1.value||!mf.birth2.value||!mf.birth3.value){
				alert("생년월일을 모두 입력하세요");
				mf.birth1.focus();
				return false;
			}
			
				var email=mf.email1.value+"@"+mf.email2.value;
				$('#email').val(email);
				
			
				var hp=mf.hp1.value+mf.hp2.value+mf.hp3.value;
				$('#hp').val(hp);
			
				
				var birth=mf.birth1.value+mf.birth2.value+mf.birth3.value;
				$('#birth').val(birth);
				
					
			    
			
			if(flag==0){
		
			alert("회원가입 성공");
			return true;
			}else{
				alert("입력정보,중복체크를 확인해주세요");
				return false;
			}
		}//
		