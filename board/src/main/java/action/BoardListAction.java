package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardListAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardService service = new BoardServiceImpl();
		List<BoardDTO> list = service.listAll();
		
		request.setAttribute("list", list);
		
		return new ActionForward(path, false);
	}

}
