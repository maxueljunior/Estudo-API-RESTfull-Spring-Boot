package br.com.erudio.integrationtests.controller.withjson;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.configs.TestConfigs;
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.erudio.integrationtests.vo.AccountCredentialsVO;
import br.com.erudio.integrationtests.vo.BookVO;
import br.com.erudio.integrationtests.vo.TokenVO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class BookControllerJsonTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	private static BookVO book;

	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		book = new BookVO();
	}

	@Test
	@Order(0)
	public void authorization() throws JsonMappingException, JsonProcessingException {
		AccountCredentialsVO user = new AccountCredentialsVO("leandro", "admin123");
		var accessToken = given().basePath("/auth/signin").port(TestConfigs.SERVER_PORT)
				.contentType(TestConfigs.CONTENT_TYPE_JSON).body(user).when().post().then().statusCode(200).extract()
				.body().as(TokenVO.class).getAccessToken();
		specification = new RequestSpecBuilder()
				.addHeader(TestConfigs.HEADER_PARAM_AUTHORIZATION, "Bearer " + accessToken).setBasePath("/api/book/v1")
				.setPort(TestConfigs.SERVER_PORT).addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
	}

	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockBook();

		var content = given().spec(specification).contentType(TestConfigs.CONTENT_TYPE_JSON).body(book).when().post()
				.then().statusCode(200).extract().body().asString();

		BookVO persistedBook = objectMapper.readValue(content, BookVO.class);
		book = persistedBook;

		assertNotNull(persistedBook);

		assertNotNull(persistedBook.getId());
		assertNotNull(persistedBook.getAuthor());
		assertNotNull(persistedBook.getLaunchDate());
		assertNotNull(persistedBook.getPrice());
		assertNotNull(persistedBook.getTitle());

		assertTrue(persistedBook.getId() > 0);

		assertEquals("Maxuel", persistedBook.getAuthor());
		assertEquals(1.0, persistedBook.getPrice());
		assertEquals("Avengers", persistedBook.getTitle());

	}

	@Test
	@Order(2)
	public void testUpdate() throws JsonMappingException, JsonProcessingException {

		book.setAuthor("Maxuel Vieira Toba Junior");
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.body(book)
					.when()
					.post()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();

		BookVO persistedBook = objectMapper.readValue(content, BookVO.class);
		book = persistedBook;

		assertNotNull(persistedBook);

		assertNotNull(persistedBook.getId());
		assertNotNull(persistedBook.getAuthor());
		assertNotNull(persistedBook.getLaunchDate());
		assertNotNull(persistedBook.getPrice());
		assertNotNull(persistedBook.getTitle());

		assertTrue(persistedBook.getId() > 0);

		assertEquals("Maxuel Vieira Toba Junior", persistedBook.getAuthor());
		assertEquals(1.0, persistedBook.getPrice());
		assertEquals("Avengers", persistedBook.getTitle());

	}
	
	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException{
		mockBook();
		
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.header(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
					.pathParam("id", book.getId())
					.when()
					.get("{id}")
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
		BookVO persistedBook = objectMapper.readValue(content, BookVO.class);
		book = persistedBook;
		
		assertNotNull(persistedBook);

		assertNotNull(persistedBook.getId());
		assertNotNull(persistedBook.getAuthor());
		assertNotNull(persistedBook.getLaunchDate());
		assertNotNull(persistedBook.getPrice());
		assertNotNull(persistedBook.getTitle());
		
		assertEquals(book.getId(), persistedBook.getId());
		
		assertEquals("Maxuel Vieira Toba Junior", persistedBook.getAuthor());
		assertEquals(1.0, persistedBook.getPrice());
		assertEquals("Avengers", persistedBook.getTitle());
	}
	
	@Test
	@Order(4)
	public void testDelete() throws JsonMappingException, JsonProcessingException{
		given().spec(specification)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", book.getId())
				.when()
				.delete("{id}")
			.then()
				.statusCode(204);
	}
	
	@Test
	@Order(5)
	public void testFindAll() throws JsonMappingException, JsonProcessingException {
		var content = given().spec(specification)
				.contentType(TestConfigs.CONTENT_TYPE_JSON)
					.when()
					.get()
				.then()
					.statusCode(200)
						.extract()
						.body()
							.asString();
		
		List<BookVO> livro = objectMapper.readValue(content, new TypeReference<List<BookVO>>() {});
		
		BookVO foundBookOne = livro.get(0);
		
		assertNotNull(foundBookOne.getId());
		assertNotNull(foundBookOne.getAuthor());
		assertNotNull(foundBookOne.getLaunchDate());
		assertNotNull(foundBookOne.getTitle());
		assertNotNull(foundBookOne.getPrice());
		
		assertEquals(1, foundBookOne.getId());
		
		assertEquals("Michael C. Feathers", foundBookOne.getAuthor());
		assertEquals(49.0, foundBookOne.getPrice());
		assertEquals("Working effectively with legacy code", foundBookOne.getTitle());
		
		
		BookVO foundBookThree = livro.get(4);
		
		assertNotNull(foundBookThree.getId());
		assertNotNull(foundBookThree.getAuthor());
		assertNotNull(foundBookThree.getLaunchDate());
		assertNotNull(foundBookThree.getTitle());
		assertNotNull(foundBookThree.getPrice());
		
		assertEquals(5, foundBookThree.getId());
		
		assertEquals("Steve McConnell", foundBookThree.getAuthor());
		assertEquals(58.0, foundBookThree.getPrice());
		assertEquals("Code complete", foundBookThree.getTitle());
	}
	
	@Test
	@Order(6)
	public void testFindAllWithoutToken() throws JsonMappingException, JsonProcessingException{
		RequestSpecification specificationWithoutToken = new RequestSpecBuilder()
				.setBasePath("/api/book/v1")
				.setPort(TestConfigs.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		given().spec(specificationWithoutToken)
			.contentType(TestConfigs.CONTENT_TYPE_JSON)
				.when()
				.get()
			.then()
				.statusCode(403);
		
	}
	

	private void mockBook() {
		book.setAuthor("Maxuel");
		book.setLaunchDate(new Date());
		book.setPrice(1.0);
		book.setTitle("Avengers");
	}
}
