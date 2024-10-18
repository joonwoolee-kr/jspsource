package service;

import java.util.List;

import dto.BookDTO;

public interface BookService {
	public List<BookDTO> List(String keyword);
	public BookDTO read(int code);
	public boolean update(BookDTO updateDTO);
	public boolean delete(int code);
	public boolean insert(BookDTO insertDTO);
}
