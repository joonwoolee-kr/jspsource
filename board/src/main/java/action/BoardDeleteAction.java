package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {
	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO deleteDto = new BoardDTO();
		deleteDto.setBno(Integer.parseInt(request.getParameter("bno")));
		deleteDto.setPassword(request.getParameter("password"));

		BoardService service = new BoardServiceImpl();
		boolean deleteFlag = service.delete(deleteDto);
		
		if (deleteFlag) {
			// path += "?bno=" + deleteDto.getBno();
		} else {
			path = "/modify.do?bno=" + deleteDto.getBno();
		}
		
		return new ActionForward(path, true);
	}

}
