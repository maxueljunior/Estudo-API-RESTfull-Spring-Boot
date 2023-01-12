package br.com.erudio.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.model.Person;
import br.com.erudio.unittests.mapper.mocker.MockBook;
import br.com.erudio.unittests.mapper.mocker.MockPerson;

public class DozerConverterTest {

	MockPerson inputObject;
	MockBook inputObjectBook;

	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
		inputObjectBook = new MockBook();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("First Name Test0", output.getFirstName());
		assertEquals("Last Name Test0", output.getLastName());
		assertEquals("Addres Test0", output.getAddres());
		assertEquals("Male", output.getGender());
	}

	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);

		assertEquals(Long.valueOf(0L), outputZero.getKey());
		assertEquals("First Name Test0", outputZero.getFirstName());
		assertEquals("Last Name Test0", outputZero.getLastName());
		assertEquals("Addres Test0", outputZero.getAddres());
		assertEquals("Male", outputZero.getGender());

		PersonVO outputSeven = outputList.get(7);

		assertEquals(Long.valueOf(7L), outputSeven.getKey());
		assertEquals("First Name Test7", outputSeven.getFirstName());
		assertEquals("Last Name Test7", outputSeven.getLastName());
		assertEquals("Addres Test7", outputSeven.getAddres());
		assertEquals("Female", outputSeven.getGender());

		PersonVO outputTwelve = outputList.get(12);

		assertEquals(Long.valueOf(12L), outputTwelve.getKey());
		assertEquals("First Name Test12", outputTwelve.getFirstName());
		assertEquals("Last Name Test12", outputTwelve.getLastName());
		assertEquals("Addres Test12", outputTwelve.getAddres());
		assertEquals("Male", outputTwelve.getGender());
	}

	@Test
	public void parseVOToEntityTest() {
		Person output = DozerMapper.parseObject(inputObject.mockVO(), Person.class);
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("First Name Test0", output.getFirstName());
		assertEquals("Last Name Test0", output.getLastName());
		assertEquals("Addres Test0", output.getAddres());
		assertEquals("Male", output.getGender());
	}

	@Test
	public void parserVOListToEntityListTest() {
		List<Person> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);

		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("First Name Test0", outputZero.getFirstName());
		assertEquals("Last Name Test0", outputZero.getLastName());
		assertEquals("Addres Test0", outputZero.getAddres());
		assertEquals("Male", outputZero.getGender());

		Person outputSeven = outputList.get(7);

		assertEquals(Long.valueOf(7L), outputSeven.getId());
		assertEquals("First Name Test7", outputSeven.getFirstName());
		assertEquals("Last Name Test7", outputSeven.getLastName());
		assertEquals("Addres Test7", outputSeven.getAddres());
		assertEquals("Female", outputSeven.getGender());

		Person outputTwelve = outputList.get(12);

		assertEquals(Long.valueOf(12L), outputTwelve.getId());
		assertEquals("First Name Test12", outputTwelve.getFirstName());
		assertEquals("Last Name Test12", outputTwelve.getLastName());
		assertEquals("Addres Test12", outputTwelve.getAddres());
		assertEquals("Male", outputTwelve.getGender());
	}

	// Abaixo está fazendo a conversão de Book para BookVO e BookVO para Book

	@Test
	public void parseEntityToBookVOTest() throws ParseException {

		BookVO output = DozerMapper.parseObject(inputObjectBook.mockEntity(), BookVO.class);

		assertEquals(Integer.valueOf(0), output.getKey());
		assertEquals("Author Test0", output.getAuthor());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		data = formato.parse("01/01/9999");
		assertEquals(data, output.getLaunchDate());
		assertEquals(Double.valueOf(0), output.getPrice());
		assertEquals("Title Test0", output.getTitle());

	}

	@Test
	public void parseEntityListToBookVOListTest() throws ParseException {
		
		List<BookVO> outputList = DozerMapper.parseListObjects(inputObjectBook.mockEntityList(), BookVO.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		data = formato.parse("01/01/9999");

		BookVO outputZero = outputList.get(0);

		assertEquals(Integer.valueOf(0), outputZero.getKey());
		assertEquals("Author Test0", outputZero.getAuthor());
		assertEquals(data, outputZero.getLaunchDate());
		assertEquals(Double.valueOf(0), outputZero.getPrice());
		assertEquals("Title Test0", outputZero.getTitle());

		BookVO outputSeven = outputList.get(7);

		assertEquals(Integer.valueOf(7), outputSeven.getKey());
		assertEquals("Author Test7", outputSeven.getAuthor());
		assertEquals(data, outputSeven.getLaunchDate());
		assertEquals(Double.valueOf(7), outputSeven.getPrice());
		assertEquals("Title Test7", outputSeven.getTitle());
		
		BookVO outputTwelve = outputList.get(12);

		assertEquals(Integer.valueOf(12), outputTwelve.getKey());
		assertEquals("Author Test12", outputTwelve.getAuthor());
		assertEquals(data, outputTwelve.getLaunchDate());
		assertEquals(Double.valueOf(12), outputTwelve.getPrice());
		assertEquals("Title Test12", outputTwelve.getTitle());

	}

	@Test
	public void parseBookVOToEntityTest() throws ParseException {
		Book output = DozerMapper.parseObject(inputObjectBook.mockVO(), Book.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		data = formato.parse("01/01/9999");

		assertEquals(Integer.valueOf(0), output.getId());
		assertEquals("Author Test0", output.getAuthor());
		assertEquals(data, output.getLaunchDate());
		assertEquals(Double.valueOf(0), output.getPrice());
		assertEquals("Title Test0", output.getTitle());

	}

	@Test
	public void parserBookVOListToEntityListTest() throws ParseException {
		
		List<Book> outputList = DozerMapper.parseListObjects(inputObjectBook.mockVOList(), Book.class);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data;
		data = formato.parse("01/01/9999");

		Book outputZero = outputList.get(0);

		assertEquals(Integer.valueOf(0), outputZero.getId());
		assertEquals("Author Test0", outputZero.getAuthor());
		assertEquals(data, outputZero.getLaunchDate());
		assertEquals(Double.valueOf(0), outputZero.getPrice());
		assertEquals("Title Test0", outputZero.getTitle());

		Book outputSeven = outputList.get(7);

		assertEquals(Integer.valueOf(7), outputSeven.getId());
		assertEquals("Author Test7", outputSeven.getAuthor());
		assertEquals(data, outputSeven.getLaunchDate());
		assertEquals(Double.valueOf(7), outputSeven.getPrice());
		assertEquals("Title Test7", outputSeven.getTitle());
		
		Book outputTwelve = outputList.get(12);

		assertEquals(Integer.valueOf(12), outputTwelve.getId());
		assertEquals("Author Test12", outputTwelve.getAuthor());
		assertEquals(data, outputTwelve.getLaunchDate());
		assertEquals(Double.valueOf(12), outputTwelve.getPrice());
		assertEquals("Title Test12", outputTwelve.getTitle());
		
	}
}
