package com.post.mapper;

import java.util.List;
import java.util.Map;

import com.member.domain.MemberVO;
import com.post.domain.ImageFileVO;
import com.post.domain.LikeVO;
import com.post.domain.PostVO;
import com.post.domain.ReplyVO;
import com.post.domain.TagVO;

public interface PostMapper {
	int uploadImage(ImageFileVO imageVO);
	/*List<PostVO> getPost(Integer pIdx);*/
	int insertPost(PostVO post);
	PostVO getPost(int pIdx);
	List<LikeVO> getLike(int pIdx);
	List<ReplyVO> getReply(int pIdx);
	List<ImageFileVO> getImageFile(int pIdx);
	List<TagVO> getTag(int pIdx);
	MemberVO getPostEditer(int pIdx);
	
	
	int selectPidx();
	String selectImgName(String filename);
	int deleteImage(String imgName);
	int deleteImages();
	int updateImgPidx(int pidx);
	List<String> findPidxZero();
	int insertTag(TagVO tag);
	List<PostVO> selectAllPost(Map<String,String> midx);
	List<String> getYearMonth();
	int selectImgCount();
	List<Integer> selectPidxBymidx(int midx);
	List<String> selectTname(int pidx);
	/*���ü� ����*/
	int replyCount(int pIdx);
	/*���ƿ� ����*/
	int likeCount(int pIdx);
	/*��� �ۼ��� ���� ��������*/
	MemberVO getReplyEditer(int rmIdx);
	/*��� �ۼ��ϱ�*/
	int subReply(Map<String, String> replyInfo);
	
	/*�Խñ� ���ƿ�ó��*/
	int likeUp(Map<String, String> likeUserMap);
	Integer alreadyLikeChk(Map<String, String> likeUserMap);
	int likeDown(Map<String, String> likeUserMap);
	//�Խñ� ���ƿ� ���� ������Ʈ
	int uplikeCount(Map<String, String> likeUserMap);
	int downlikeCount(Map<String, String> likeUserMap);
	
	
	
	
}
