package service;

import dao.BookDAO;
import dto.BookDTO;

public class BookServiceImpl implements BookService {
	
	private BookDAO dao = new BookDAO();

	@Override
	public java.util.List<BookDTO> List() {
		return dao.getList();
	}

	@Override
	public BookDTO read(int code) {
		return dao.getRow(code);
	}

	@Override
	public boolean update(BookDTO updateDTO) {
		return dao.update(updateDTO)==1?true:false;
	}

	@Override
	public boolean delete(int code) {
		return dao.delete(code)==1?true:false;
	}

	@Override
	public boolean insert(BookDTO insertDTO) {
		return dao.insert(insertDTO)==1?true:false;
	}

}
