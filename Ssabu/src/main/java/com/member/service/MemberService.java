package com.member.service;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberVO;
import com.member.domain.NotUserException;

public interface MemberService {

	/* ȸ������ ���� �޼ҵ� */
	int emailCheck(String email1); // �̸����ߺ�Ȯ��

	int insertMember(MemberVO member); // ȸ������ ó��

	int hpCheck(String hp); // �ڵ�����ȣ �ߺ�Ȯ��
	/* �α��� ó�� �� ���̵�,��й�ȣ ã�� ���� �޼ҵ� */

	MemberVO userLogin(String email, String pwd) throws NotUserException; // �α���ó��

	MemberVO findUser(MemberVO mvo) throws NotUserException; // ���̵� ã��

	   MemberVO selectMember(String email);//ȸ�����Խ� �⺻ �Խ��� �ο� ���ؼ� -----
	String searchId(Map<String, String> map);

	String searchPwd(Map<String, String> map);
	   int updatePwd(Map<String, String> map); 
	   
	   MemberVO findUserByMidx(String mIdx);
	   
	   ////////////////////////////////////////////
	   int countFollow(int mIdx); //�ȷο찡 �ִ��� ������
	   int insertFollow(Map<String, String> map);
	   int updateFollow(Map<String, String> map);
	   String selectFollow(int loginMidx);
	   List<Integer> selectFwer(int loginMidx);
	   int deleteFollow(int deleteF);
	   String compareFollow(int midx,int loginMidx);
	   
	   
	   //�Խñۼ�/�ȷο���
	   int selectPostCount(int mIdx);
	   
	   ///////////////
	   int editMember(MemberVO member); //ȸ������
		int dropMember(int midx);
	   /////////
}