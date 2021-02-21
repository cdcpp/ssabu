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
	
		int uploadImage(ImageFileVO imageVO); // �̹��� ���ε��ϱ�

	int insertPost(PostVO post); // ���ۼ��ϱ�

	int selectPidx(); // �۹�ȣ ��������

	String selectImgName(String filename); // �̹̽� ������ ���ϸ� ��������

	int deleteImage(String imgName); //���ε�� �̹��� ����
	
	int deleteImages(); //�۾��� ȭ�� ���Խ� �ӽ� �����س��� �̹��� ��� ����
	
	int updateImgPidx(int pidx); // ���ۼ��� �̹������Ͽ� �ش� �۹�ȣ �ο�

	List<String> findPidxZero(); //0�� �� ��������
	
	int insertTag(TagVO tag); //�±� �����ϱ�

	List<PostVO> selectAllPost(Map<String,String> map); // �α����� ������ ��� �Խù� ��������

	List<String> getYearMonth();//�ۼ��� �۵��� �⵵-���� ��������

	int selectImgCount();//�Խñ� �ۼ��� �̹��� ���θ� Ȯ��
	
	List<Integer> selectPidxBymidx(int midx); //�α����� ȸ���� �۹�ȣ ��������

	List<String> selectTname(int pidx); // ȸ���� �۹�ȣ�� �±׸� ��������
	
	/*������ ���������� �޼ҵ�*/
	int replyCount(int pIdx);
	/*���ƿ� �޼ҵ�*/
	int likeCount(int pIdx);
	/*��۴ܻ���� ������ �������� �޼ҵ�*/
	MemberVO getReplyEditer(int rmIdx);
	/*��� �ۼ��ϱ�*/
	int subReply(Map<String, String> replyInfo);
	
	/*�Խñ� ���ƿ�ó��*/
	int likeUp(Map<String, String> likeUserMap);
	Integer alreadyLikeChk(Map<String, String> likeUserMap);
	int likeDown(Map<String, String> likeUserMap);
	int uplikeCount(Map<String, String> likeUserMap);
	int downlikeCount(Map<String, String> likeUserMap);
	
	
	
}
