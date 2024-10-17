<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 1
MemberDTO loginDto = new MemberDTO();
String userid = request.getParameter("userid");
String password = request.getParameter("password");
String name = request.getParameter("name");

loginDto.setUserid(userid);
loginDto.setPassword(password);
loginDto.setName(name);

// 2
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.isLogin(loginDto);


// 3. session.setAttribute()
// 4. null => login.jsp
//    !null => 도서목록으로 이동
if(dto != null){
	// 서버 쪽에서 정보 보관
	session.setAttribute("loginDto", dto);
	response.sendRedirect("/book/list_pro.jsp");
} else{
	response.sendRedirect("login.jsp");
}
%>