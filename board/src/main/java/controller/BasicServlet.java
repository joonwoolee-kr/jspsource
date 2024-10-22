package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.BoardCreateAction;
import action.BoardDeleteAction;
import action.BoardListAction;
import action.BoardReadAction;
import action.BoardReadCntAction;
import action.BoardUpdateAction;


/**
 * Servlet implementation class BasicServlet
 */
// 1024 * 1024 * 5 (5MB)
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("*.do")
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");

		// 톰캣서버의 path 수정하지 않았다면
		// RequestURI = /프로젝트명/경로명 => /moedel2/login.do
		// ContextPath = /프로젝트명 => /moedel2
		// "/model2/login.do".substring(7) 경로명만 추출 => /login.do
		String requestUri = request.getRequestURI(); // /login.do
		String contextPath = request.getContextPath();
		String cmd = requestUri.substring(contextPath.length()); // /login.do

		// syso
		// System.out.println("requestUri " + requestUri);
		// System.out.println("contextPath " + contextPath);

		Action action = null;
		
		if (cmd.equals("/list.do")) { // 목록
			action = new BoardListAction("/board/list.jsp");
		} else if(cmd.equals("/read.do")) { // 상세 조회
			action = new BoardReadAction("/board/read.jsp");
		} else if(cmd.equals("/modify.do")) { // 수정화면
			action = new BoardReadAction("/board/modify.jsp");
		} else if(cmd.equals("/update.do")) { // 업데이트
			action = new BoardUpdateAction("/read.do"); // 수정 성공 후 상세조회로 이동
		} else if(cmd.equals("/delete.do")) { // 삭제
			action = new BoardDeleteAction("/list.do");
		} else if(cmd.equals("/create.do")) { // 작성
			action = new BoardCreateAction("/list.do");
		} else if(cmd.equals("/cntupdate.do")) { // 조회수 증가
			action = new BoardReadCntAction("/read.do");
		}


		ActionForward af = null;

		try {
			af = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (af.isRedirect()) {
			response.sendRedirect(af.getPath());
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
