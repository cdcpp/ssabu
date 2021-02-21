	function allCheckPolicy(){
		if($('#allCheck').is(":checked")){
		
			$('#policy1Check').attr('checked',true);
			$('#policy2Check').attr('checked',true);
		} 
		else {
			
			$('#policy1Check').attr('checked',false);
			$('#policy2Check').attr('checked',false);
	} 
	}
	
	
	function policyCheck(){
		if($('#policy1Check').is(":checked")&&$('#policy2Check').is(":checked")){
		checkPolicy.submit();
	    return true;
		}
		else{
			alert('약관에 동의하셔야 합니다');
			return false;
		}
	}