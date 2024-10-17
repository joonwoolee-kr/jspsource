package service;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberServiceImpl implements MemberService {
	// DAO 호출
	
	MemberDAO dao = new MemberDAO();

	@Override
	public int create(MemberDTO memberDto) {
		return dao.create(memberDto);
	}

	@Override
	public MemberDTO read(MemberDTO memberDto) {
		return dao.read(memberDto);
	}

	@Override
	public int update(MemberDTO memberDto) {
		return dao.update(memberDto);
	}

	@Override
	public int delete(MemberDTO memberDto) {
		return dao.delete(memberDto);
	}

}
