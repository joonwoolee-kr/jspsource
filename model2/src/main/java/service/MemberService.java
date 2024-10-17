package service;

import dto.MemberDTO;

public interface MemberService {
	// DAO의 CRUD 메소드 호출
	public int create(MemberDTO memberDto);
	public MemberDTO read(MemberDTO memberDto);
	public int update(MemberDTO memberDto);
	public int delete(MemberDTO memberDto);
}
