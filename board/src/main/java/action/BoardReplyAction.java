package action;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dto.BoardDTO;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReplyAction implements Action {

	private String path;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDTO replyDto = new BoardDTO();

		replyDto.setTitle(request.getParameter("title"));
		replyDto.setContent(request.getParameter("content"));
		replyDto.setPassword(request.getParameter("password"));
		replyDto.setName(request.getParameter("name"));

		// hidden 부모 정보
		replyDto.setReLev(Integer.parseInt(request.getParameter("re_lev")));
		replyDto.setReRef(Integer.parseInt(request.getParameter("re_ref")));
		replyDto.setReSeq(Integer.parseInt(request.getParameter("re_seq")));
		replyDto.setBno(Integer.parseInt(request.getParameter("bno")));

		// 페이지 나누기
		int page = Integer.parseInt(request.getParameter("page"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		// 검색 추가
		String criteria = request.getParameter("criteria");
		String keyword = URLEncoder.encode(request.getParameter("keyword"), "utf-8");

		BoardService service = new BoardServiceImpl();
		boolean replyFlag = service.reply(replyDto);

		if (replyFlag) {
			path += "?bno=" + replyDto.getBno() + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		} else {
			path = "/replyView.do?bno=" + replyDto.getBno() + "&page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword=" + keyword;
		}

		return new ActionForward(path, true);
	}

}
