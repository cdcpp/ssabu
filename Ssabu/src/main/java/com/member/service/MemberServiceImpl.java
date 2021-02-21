package com.member.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.member.domain.MemberVO;
import com.member.domain.NotUserException;
import com.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
   
   @Inject
   private MemberMapper mMapper;
   
   @Inject
   MemberService memberService;
   
   @Override
	public int hpCheck(String hp) {
		return mMapper.hpCheck(hp);
	}

	@Override
	   public int emailCheck(String email1) {
	      return mMapper.emailCheck(email1);
	   }
	
	@Override
	   public int insertMember(MemberVO member) {
	      return mMapper.insertMember(member);
	   }

   /**유저정보를 받아서 DB와 비교해 맞다면 유저정보 보내주기 1_2*/
	@Override
	public MemberVO userLogin(String email, String pwd) throws NotUserException {
		MemberVO mvo=new MemberVO();
		mvo.setEmail(email);
		mvo.setPwd(pwd);
		MemberVO dbUser=findUser(mvo);
		if(dbUser!=null) {
			if(!dbUser.getPwd().equals(pwd)) {
				throw new NotUserException("비밀번호가 일치하지 않습니다.");
			}//if
		}
		return dbUser;
	}

	/**로그인중 아이디체크 1_1*/
	@Override
	public MemberVO findUser(MemberVO mvo) throws NotUserException {
		MemberVO user=mMapper.findUser(mvo);
		if(user==null) {
			throw new NotUserException("존재하지 않는 아이디입니다.");
		}
		return user;
	}
	@Override
	public String searchId(Map<String, String> map) {
		
		return mMapper.searchId(map);
	}

	@Override
	public String searchPwd(Map<String, String> map) {
		return mMapper.searchPwd(map);
	}

	@Override
	public int updatePwd(Map<String, String> map) {
		return mMapper.updatePwd(map);
	}

	@Override
	public MemberVO selectMember(String email) {
		return mMapper.selectMember(email);
	}

	@Override
	public MemberVO findUserByMidx(String mIdx) {
		// TODO Auto-generated method stub
		return mMapper.findUserByMidx(mIdx);
	}
	//////////////////////////
	@Override
	public int countFollow(int mIdx) {
		// TODO Auto-generated method stub
		return mMapper.countFollow(mIdx);
	}

	@Override
	public int insertFollow(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mMapper.insertFollow(map);
	}

	@Override
	public int updateFollow(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mMapper.updateFollow(map);
	}

	@Override
	public String selectFollow(int loginMidx) {
		// TODO Auto-generated method stub
		return mMapper.selectFollow(loginMidx);
	}

	@Override
	public List<Integer> selectFwer(int loginMidx) {
		// TODO Auto-generated method stub
		return mMapper.selectFwer(loginMidx);
	}

	@Override
	public int editMember(MemberVO member) {
		// TODO Auto-generated method stub
		return mMapper.editMember(member);
	}

	@Override
	public int dropMember(int midx) {
		// TODO Auto-generated method stub
		return mMapper.dropMember(midx);
	}

	@Override
	public int deleteFollow(int deleteF) {
		// TODO Auto-generated method stub
		return mMapper.deleteFollow(deleteF);
	}

	@Override
	public String compareFollow(int midx,int loginMidx) {
		String state="";
		String follower = memberService.selectFollow(loginMidx);
		System.out.println("folloooooooo=="+follower);
		if(follower!=null) {
			String[] followArr = follower.split(",");
			
			if(midx==loginMidx){ //자기 자신일때
				state= "2";
			}
			else {
				for(int i=0;i<followArr.length;i++) {
					System.out.println("배열="+followArr[i]);
					if(String.valueOf(midx).equals(followArr[i])) {
						state= "1";
						break;
					}else {
						state= "0";
					}
					
				}
			}
		}else {
			if(midx==loginMidx){ //자기 자신일때
				state= "2";
			}
			else {
				state= "0";
			}
		}
		
		
		
		return state;
	}

	@Override
	public int selectPostCount(int mIdx) {
		// TODO Auto-generated method stub
		return mMapper.selectPostCount(mIdx);
	}

	

}