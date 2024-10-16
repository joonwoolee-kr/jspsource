package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	// 드라이버 로드
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##java";
		String password = "12345";

		return DriverManager.getConnection(url, user, password);
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CRUD 메소드

	// R(Read) - 전체조회, 특정(pk)조회, 제목조회
	// 조회 메소드 작성하기
	// 리턴 타입: List<~DTO> or ~DTO => sql 구문 보고 결정
	// List<~DTO>: where 절 없는 경우, where 절이 pk가 아닌 경우
	// ~DTO: where 절이 pk인 경우
	
	// 전달인자: ()에 어떻게 작성할 것인가? => sql 구문의 ? 보고 결정
	
	public MemberDTO isLogin(MemberDTO loginDTO) {
		MemberDTO dto = null;
		try {
			con = getConnection();
			String sql = "SELECT * FROM MEMBERTBL WHERE USERID = ? AND PASSWORD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginDTO.getUserid());
			pstmt.setString(2, loginDTO.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return dto;
	}
	
	public boolean dupId(String userId) {

		try {
			con = getConnection();
			String sql = "SELECT  * FROM MEMBERTBL WHERE  USERID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) { // 아이디 있음
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return true; // 중복 아이디 없음
	}

	// U(Update) - 수정
	// 수정 메소드 작성하기
	// 리턴타입: int

	
	// D(Delete) - 삭제
	// 삭제 메소드 작성하기
	// 리턴타입: int

	
	// C(Create) - 삽입(회원가입)
	// 삽입 메소드 작성하기
	// 리턴타입: int
	public int insert(MemberDTO insertDTO) {
		int insertRow = 0;
		
		try {
			con = getConnection();
			String sql = "INSERT INTO membertbl values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDTO.getUserid());
			pstmt.setString(2, insertDTO.getName());
			pstmt.setString(3, insertDTO.getPassword());
			
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return insertRow;
	}

}
