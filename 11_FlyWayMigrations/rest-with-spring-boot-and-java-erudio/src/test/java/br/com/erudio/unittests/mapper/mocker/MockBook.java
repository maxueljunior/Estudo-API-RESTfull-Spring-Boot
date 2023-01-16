package br.com.erudio.unittests.mapper.mocker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

public class MockBook {


    public Book mockEntity(){
        return mockEntity(0L);
    }
    
    public BookVO mockVO(){
        return mockVO(0L);
    }
    
    public List<Book> mockEntityList(){
        List<Book> books = new ArrayList<Book>();
        for (long i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList(){
        List<BookVO> books = new ArrayList<>();
        for (long i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Long number){
        Book books = new Book();
        
        books.setAuthor("Author Test" + number);
        books.setId(number);
        books.setLaunchDate(new Date());
        books.setPrice(1D);
        books.setTitle("Title Test" + number);
        
        return books;
    }

    public BookVO mockVO(Long number){
        BookVO books = new BookVO();

        books.setAuthor("Author Test" + number);
        books.setKey(number);
        books.setLaunchDate(new Date());
        books.setPrice(1D);
        books.setTitle("Title Test" + number);
        
        return books;
    }

}
