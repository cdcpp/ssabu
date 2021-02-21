package com.member.service;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberVO;
import com.member.domain.NotUserException;

public interface MemberService {

	/* 회원가입 관련 메소드 */
	int emailCheck(String email1); // 이메일중복확인

	int insertMember(MemberVO member); // 회원가입 처리

	int hpCheck(String hp); // 핸드폰번호 중복확인
	/* 로그인 처리 및 아이디,비밀번호 찾기 관련 메소드 */

	MemberVO userLogin(String email, String pwd) throws NotUserException; // 로그인처리

	MemberVO findUser(MemberVO mvo) throws NotUserException; // 아이디 찾기

	   MemberVO selectMember(String email);//회원가입시 기본 게시판 부여 위해서 -----
	String searchId(Map<String, String> map);

	String searchPwd(Map<String, String> map);
	   int updatePwd(Map<String, String> map); 
	   
	   MemberVO findUserByMidx(String mIdx);
	   
	   ////////////////////////////////////////////
	   int countFollow(int mIdx); //팔로우가 있는지 없는지
	   int insertFollow(Map<String, String> map);
	   int updateFollow(Map<String, String> map);
	   String selectFollow(int loginMidx);
	   List<Integer> selectFwer(int loginMidx);
	   int deleteFollow(int deleteF);
	   String compareFollow(int midx,int loginMidx);
	   
	   
	   //게시글수/팔로워수
	   int selectPostCount(int mIdx);
	   
	   ///////////////
	   int editMember(MemberVO member); //회원수정
		int dropMember(int midx);
	   /////////
}