package br.com.erudio.unittests.mapper.mocker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.model.Book;

public class MockBook {


    public Book mockEntity() throws ParseException {
        return mockEntity(0);
    }
    
    public BookVO mockVO() throws ParseException {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() throws ParseException {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() throws ParseException {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) throws ParseException {
        Book books = new Book();
        
        books.setAuthor("Author Test" + number);
        books.setId(number);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        data = formato.parse("01/01/9999");
        books.setLaunchDate(data);
        books.setPrice(number.doubleValue());
        books.setTitle("Title Test" + number);
        
        return books;
    }

    public BookVO mockVO(Integer number) throws ParseException {
        BookVO books = new BookVO();

        books.setAuthor("Author Test" + number);
        books.setKey(number);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        data = formato.parse("01/01/9999");
        books.setLaunchDate(data);
        books.setPrice(number.doubleValue());
        books.setTitle("Title Test" + number);
        
        return books;
    }

}
