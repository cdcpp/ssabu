package com.post.service;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberVO;
import com.post.domain.ImageFileVO;
import com.post.domain.LikeVO;
import com.post.domain.PostVO;
import com.post.domain.ReplyVO;
import com.post.domain.TagVO;

public interface PostService {
	/*List<PostVO> getPost(int pIdx);*/
	PostVO getPost(int pIdx);
	List<LikeVO> getLike(int pIdx);
	List<ReplyVO> getReply(int pIdx);
	List<ImageFileVO> getImageFile(int pIdx);
	List<TagVO> getTag(int pIdx);
	MemberVO getPostEditer(int pIdx);
	
		int uploadImage(ImageFileVO imageVO); // 이미지 업로드하기

	int insertPost(PostVO post); // 글작성하기

	int selectPidx(); // 글번호 가져오기

	String selectImgName(String filename); // 이미시 삭제시 파일명 가져오기

	int deleteImage(String imgName); //업로드시 이미지 삭제
	
	int deleteImages(); //글쓰기 화면 진입시 임시 저장해놨던 이미지 모두 삭제
	
	int updateImgPidx(int pidx); // 글작성시 이미지파일에 해당 글번호 부여

	List<String> findPidxZero(); //0번 글 가져오기
	
	int insertTag(TagVO tag); //태그 저장하기

	List<PostVO> selectAllPost(Map<String,String> map); // 로그인한 유저의 모든 게시물 가져오기

	List<String> getYearMonth();//작성한 글들의 년도-월을 가져오기

	int selectImgCount();//게시글 작성시 이미지 여부를 확인
	
	List<Integer> selectPidxBymidx(int midx); //로그인한 회원의 글번호 가져오기

	List<String> selectTname(int pidx); // 회원의 글번호로 태그명 가져오기
	
	/*리플의 갯수를세는 메소드*/
	int replyCount(int pIdx);
	/*좋아요 메소드*/
	int likeCount(int pIdx);
	/*댓글단사람의 정보를 가져오는 메소드*/
	MemberVO getReplyEditer(int rmIdx);
	/*댓글 작성하기*/
	int subReply(Map<String, String> replyInfo);
	
	/*게시글 좋아요처리*/
	int likeUp(Map<String, String> likeUserMap);
	Integer alreadyLikeChk(Map<String, String> likeUserMap);
	int likeDown(Map<String, String> likeUserMap);
	int uplikeCount(Map<String, String> likeUserMap);
	int downlikeCount(Map<String, String> likeUserMap);
	
	
	
}
