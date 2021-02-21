package com.post.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.member.domain.MemberVO;
import com.post.domain.ImageFileVO;
import com.post.domain.LikeVO;
import com.post.domain.PostVO;
import com.post.domain.ReplyVO;
import com.post.domain.TagVO;
import com.post.mapper.PostMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class PostServiceImpl implements PostService {

	@Inject
	private PostMapper pMapper;

	/*
	 * @Override public List<PostVO> getPost(int pIdx) { return
	 * pMapper.getPost(pIdx); }
	 */

	@Override
	public MemberVO getPostEditer(int pIdx) {
		return pMapper.getPostEditer(pIdx);
	}

	@Override
	public PostVO getPost(int pIdx) {
		return pMapper.getPost(pIdx);
	}

	@Override
	public List<LikeVO> getLike(int pIdx) {
		return pMapper.getLike(pIdx);
	}

	@Override
	public List<ReplyVO> getReply(int pIdx) {
		return pMapper.getReply(pIdx);
	}

	@Override
	public List<ImageFileVO> getImageFile(int pIdx) {
		return pMapper.getImageFile(pIdx);
	}

	@Override
	public List<TagVO> getTag(int pIdx) {
		return pMapper.getTag(pIdx);
	}

	@Override
	public int replyCount(int pIdx) {
		return pMapper.replyCount(pIdx);
	}

	@Override
	public int likeCount(int pIdx) {
		return pMapper.likeCount(pIdx);
	}
	
	//댓글기능가져오기
	@Override
	public MemberVO getReplyEditer(int rmIdx) {
		return pMapper.getReplyEditer(rmIdx);
	}

	//댓글달기기능
	@Override
	public int subReply(Map<String, String> replyInfo) {
		return pMapper.subReply(replyInfo);
	}

	@Override
	public int likeUp(Map<String, String> likeUserMap) {
		
		return pMapper.likeUp(likeUserMap);
	}

	@Override
	public Integer alreadyLikeChk(Map<String, String> likeUserMap) {
		return pMapper.alreadyLikeChk(likeUserMap);
	}

	@Override
	public int likeDown(Map<String, String> likeUserMap) {
		return pMapper.likeDown(likeUserMap);
	}

	@Override
	public int uploadImage(ImageFileVO imageVO) {
		// TODO Auto-generated method stub
		return pMapper.uploadImage(imageVO);
	}

	@Override
	public int insertPost(PostVO post) {
		// TODO Auto-generated method stub
		return pMapper.insertPost(post);
	}

	@Override
	public int selectPidx() {
		// TODO Auto-generated method stub
		return pMapper.selectPidx();
	}

	@Override
	public String selectImgName(String filename) {
		// TODO Auto-generated method stub
		return pMapper.selectImgName(filename);
	}

	@Override
	public int deleteImage(String imgName) {
		// TODO Auto-generated method stub
		return pMapper.deleteImage(imgName);
	}

	@Override
	public int deleteImages() {
		// TODO Auto-generated method stub
		return pMapper.deleteImages();
	}

	@Override
	public int updateImgPidx(int pidx) {
		// TODO Auto-generated method stub
		return pMapper.updateImgPidx(pidx);
	}

	@Override
	public List<String> findPidxZero() {
		// TODO Auto-generated method stub
		return pMapper.findPidxZero();
	}

	@Override
	public int insertTag(TagVO tag) {
		// TODO Auto-generated method stub
		return pMapper.insertTag(tag);
	}

	@Override
	public List<PostVO> selectAllPost(Map<String,String> map) {
		// TODO Auto-generated method stub
		return pMapper.selectAllPost(map);
	}

	@Override
	public List<String> getYearMonth() {
		// TODO Auto-generated method stub
		return pMapper.getYearMonth();
	}

	@Override
	public int selectImgCount() {
		// TODO Auto-generated method stub
		return pMapper.selectImgCount();
	}

	@Override
	public List<Integer> selectPidxBymidx(int midx) {
		// TODO Auto-generated method stub
		return pMapper.selectPidxBymidx(midx);
	}

	@Override
	public List<String> selectTname(int pidx) {
		// TODO Auto-generated method stub
		return pMapper.selectTname(pidx);
	}

	@Override
	public int uplikeCount(Map<String, String> likeUserMap) {
		// TODO Auto-generated method stub
		return pMapper.uplikeCount(likeUserMap);
	}

	@Override
	public int downlikeCount(Map<String, String> likeUserMap) {
		// TODO Auto-generated method stub
		return pMapper.downlikeCount(likeUserMap);
	}

	
	
	

}
