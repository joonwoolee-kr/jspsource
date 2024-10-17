<%@page import="dto.BookDTO"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 1. 가져올 값(== 넘어오는 값)이 있는지 확인
// 시작하는 페이지에서 form이 존재
// a 태그 ? 다음에 넘어오는지
int code = Integer.parseInt(request.getParameter("code"));

// 2. DB 작업
BookDAO dao = new BookDAO();
int deleteRow = dao.delete(code);

// 3. 결과값을 공유

	
// 4. 페이지 이동(client 볼 페이지)
// 0이 리턴되면 modify_pro.jsp
// 1이 리턴되면 list_pro.jsp
if(deleteRow == 0){
	response.sendRedirect("modify_pro.jsp?code=" + code);
} else if(deleteRow == 1){
	response.sendRedirect("list_pro.jsp");
}
%>