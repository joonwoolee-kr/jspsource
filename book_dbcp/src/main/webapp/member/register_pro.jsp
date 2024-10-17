<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

// 1
MemberDTO insertDto = new MemberDTO();
String userid = request.getParameter("userid");
String password = request.getParameter("password");
String name = request.getParameter("name");

insertDto.setUserid(userid);
insertDto.setPassword(password);
insertDto.setName(name);

// 2
MemberDAO dao = new MemberDAO();
int insertRow = dao.insert(insertDto);

// 4. == 1(회원가입 성공) => login.jsp
//    == 0(회원가입 실패) => register.jsp
if(insertRow == 1){
	response.sendRedirect("login.jsp");
} else{
	response.sendRedirect("register.jsp");
}
%>