package com.member.mapper;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberVO;
import com.member.domain.NotUserException;

public interface MemberMapper {

	/* 이메일 중복체크하는 메소드 */
	int emailCheck(String email1);

	int insertMember(MemberVO member);

	

	int hpCheck(String hp);
	
		/* 로그인 처리 메소드 */
	MemberVO userLogin(String email, String pwd) throws NotUserException; // 로그인처리

	MemberVO findUser(MemberVO mvo) throws NotUserException; // 아이디찾기
	String searchId(Map<String, String> map);

	String searchPwd(Map<String, String> map);
	int updatePwd(Map<String, String> map); 
	
	
	MemberVO selectMember(String email);
	
	MemberVO findUserByMidx(String mIdx);
	
	///////////////////////////////////팔로우 처리
	int countFollow(int mIdx);
	int insertFollow(Map<String, String> map);
	int updateFollow(Map<String, String> map);
	String selectFollow(int loginMidx);
	List<Integer> selectFwer(int loginMidx);
	int deleteFollow(int deleteF);
	
	// 게시글 수 / 팔로워 수
	int selectPostCount(int mIdx);
	
	
	///회원 탈퇴/수정
	int editMember(MemberVO member); //회원수정
	int dropMember(int midx);
	
	
}