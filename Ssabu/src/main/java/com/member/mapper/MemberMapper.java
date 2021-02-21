package com.member.mapper;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberVO;
import com.member.domain.NotUserException;

public interface MemberMapper {

	/* �̸��� �ߺ�üũ�ϴ� �޼ҵ� */
	int emailCheck(String email1);

	int insertMember(MemberVO member);

	

	int hpCheck(String hp);
	
		/* �α��� ó�� �޼ҵ� */
	MemberVO userLogin(String email, String pwd) throws NotUserException; // �α���ó��

	MemberVO findUser(MemberVO mvo) throws NotUserException; // ���̵�ã��
	String searchId(Map<String, String> map);

	String searchPwd(Map<String, String> map);
	int updatePwd(Map<String, String> map); 
	
	
	MemberVO selectMember(String email);
	
	MemberVO findUserByMidx(String mIdx);
	
	///////////////////////////////////�ȷο� ó��
	int countFollow(int mIdx);
	int insertFollow(Map<String, String> map);
	int updateFollow(Map<String, String> map);
	String selectFollow(int loginMidx);
	List<Integer> selectFwer(int loginMidx);
	int deleteFollow(int deleteF);
	
	// �Խñ� �� / �ȷο� ��
	int selectPostCount(int mIdx);
	
	
	///ȸ�� Ż��/����
	int editMember(MemberVO member); //ȸ������
	int dropMember(int midx);
	
	
}