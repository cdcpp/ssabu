package com.post.mapper;

import java.util.List;

import com.member.domain.MemberVO;
import com.post.domain.PostVO;
import com.post.domain.TagVO;

public interface SearchMapper {
	//�ش� �̸��� ���� ����� ����Ʈ�� �����´�.
		List<MemberVO> searchMemberList(String word);

		//�˻�� ���� �±����̺��� �Ⱦ� �ش� �±װ� �ִ� �±׸���Ʈ�� �����´�.
		List<TagVO> searchTagForPidx(String word);

		//ã�� �±׸� �����ִ� �Խñ� ��ȣ ��������
		PostVO searchPost(int pidx);
		
		//�ι��˻����� �ش��ι��� ���� �ֱٿ� �� ���� ��������
		PostVO newPost(String mIdx);
}
