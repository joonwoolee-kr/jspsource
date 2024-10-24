package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import dto.SearchDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReadAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int bno = Integer.parseInt(request.getParameter("bno"));

		// 페이지 나누기
		int page = Integer.parseInt(request.getParameter("page"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		// 검색 추가
		String criteria = request.getParameter("criteria");
		String keyword = request.getParameter("keyword");
		
		SearchDTO searchDTO = new SearchDTO(criteria, keyword, page, amount);		
		

		BoardService service = new BoardServiceImpl();
		BoardDTO dto = service.getRow(bno);

		request.setAttribute("dto", dto);
		request.setAttribute("searchDTO", searchDTO);

		return new ActionForward(path, false);
	}
}
