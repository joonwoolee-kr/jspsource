<%@page import="dao.MemberDAO"%>
<%@page import="dto.ChangeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// password.jsp에서 넘긴 값 가져와서 ChangeDTO에 담기
ChangeDTO changeDTO = new ChangeDTO();
changeDTO.setUserid(request.getParameter("userid"));
changeDTO.setCurrentPassword(request.getParameter("current_password"));
changeDTO.setChangePassword(request.getParameter("change_password"));

MemberDAO dao = new MemberDAO();
int updateRow = dao.update(changeDTO);

// updateRow == 1일 때 세션 종료 / login.jsp로 이동
// updateRow == 0일 때 password.jsp로 이동	
if(updateRow == 1){
	session.invalidate();
	response.sendRedirect("login.jsp");
} else if(updateRow == 0){
	response.sendRedirect("password.jsp");
}
%>