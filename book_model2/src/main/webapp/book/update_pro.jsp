<%@page import="dto.BookDTO"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");

// 1. 가져올 값(== 넘어오는 값)이 있는지 확인
// 시작하는 페이지에서 form이 존재
// a 태그 ? 다음에 넘어오는지

// pk(code), 수정할 컬럼(price, description) 가져오기
BookDTO dto = new BookDTO();
int code = Integer.parseInt(request.getParameter("code"));
int price = Integer.parseInt(request.getParameter("price"));
String description = request.getParameter("description");

dto.setCode(code);
dto.setPrice(price);
dto.setDescription(description);

// 2. DB 작업
BookDAO dao = new BookDAO();
int updateRow = dao.update(dto);

// 3. 결과값을 공유(수정, 삭제, 삽입 X)
	
// 4. 페이지 이동(client 볼 페이지)
// 0이 리턴되면 modify_pro.jsp
// 1이 리턴되면 list_pro.jsp
// 공유할 게 없으면 sendRedirect()
if(updateRow == 0){
	response.sendRedirect("modify_pro.jsp?code=" + dto.getCode());
} else if(updateRow == 1){
	response.sendRedirect("list_pro.jsp");
}
%>