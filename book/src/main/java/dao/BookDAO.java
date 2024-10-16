package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BookDTO;

public class BookDAO {
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
	public BookDTO getRow(int code) {
		BookDTO dto = null;
		try {
			con = getConnection();
			String sql = "SELECT * FROM BOOKTBL b WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			// sql 구문 ? 해결
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			// where 절에 pk 사용된 경우 하나만 추출됨
			if(rs.next()) {
				dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return dto;
	}
	
	
	
	public List<BookDTO> getList() {
		List<BookDTO> list = new ArrayList<BookDTO>();

		try {
			con = getConnection();
			String sql = "SELECT * FROM BOOKTBL";
			pstmt = con.prepareStatement(sql);
			// sql 구문 ? 해결
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// dto에 컬럼 별로 담고 list에 추가
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	// U(Update) - 수정
	// 수정 메소드 작성하기
	// 리턴타입: int
	// 전달인자: ()에 어떻게 작성할 것인가? => sql 구문의 ? 보고 결정
	public int update(BookDTO updateDTO) {
		int updateRow = 0;
		
		try {
			con = getConnection();
			String sql = "UPDATE BOOKTBL SET PRICE = ?, DESCRIPTION = ? WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			// sql 구문 ? 해결
			pstmt.setInt(1, updateDTO.getPrice());
			pstmt.setString(2, updateDTO.getDescription());
			pstmt.setInt(3, updateDTO.getCode());
			
			updateRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return updateRow;
	}
	
	// D(Delete) - 삭제
	// 삭제 메소드 작성하기
	// 리턴타입: int
	public int delete(int code) {
		int deleteRow = 0;
		
		try {
			con = getConnection();
			String sql = "DELETE FROM BOOKTBL WHERE CODE = ?";
			pstmt = con.prepareStatement(sql);
			// sql 구문 ? 해결
			pstmt.setInt(1, code);
			
			deleteRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return deleteRow;
	}
	
	// C(Create) - 삽입
	// 삽입 메소드 작성하기
	// 리턴타입: int
	public int insert(BookDTO insertDTO) {
		int insertRow = 0;
		
		try {
			con = getConnection();
			String sql = "INSERT INTO booktbl VALUES(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			// sql 구문 ? 해결
			pstmt.setInt(1, insertDTO.getCode());
			pstmt.setString(2, insertDTO.getTitle());
			pstmt.setString(3, insertDTO.getWriter());
			pstmt.setInt(4, insertDTO.getPrice());
			pstmt.setString(5, insertDTO.getDescription());
			
			insertRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, pstmt);
		}
		return insertRow;
	}
}
