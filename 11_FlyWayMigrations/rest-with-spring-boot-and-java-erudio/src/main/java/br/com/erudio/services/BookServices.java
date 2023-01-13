package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository repository;
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	public List<BookVO> findAll(){
		
		logger.info("Buscando uma pessoa");
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		return books;
	}
	
	public BookVO findById(Integer id) {
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		return vo;
	}
	
	public BookVO create(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		return vo;
	}
	
	public BookVO update(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		return vo;
		
	}
	
	public void delete(Integer id) {
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
		repository.delete(entity);
		
	}
	
}
