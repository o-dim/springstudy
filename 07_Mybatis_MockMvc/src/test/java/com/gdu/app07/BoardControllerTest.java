package com.gdu.app07;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// Junit4
@RunWith(SpringJUnit4ClassRunner.class)

// ContextConfiguration
// 테스트에서 사용할 Bean이 @Component로 생성되었기 때문에 component-scan이 작성된 servlet-context.xml 의 경로를 작성해주어야한다
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

// 테스트 순서를 메소드명의 알파벳순으로 수행
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

// WebApplicationContext를 사용하기 위해서 필요한 애너테이션
@WebAppConfiguration
public class BoardControllerTest {
	// 테스트 수행객체
	private MockMvc mockMvc;
	
	// @WebAppConfiguration이 있어야 자동 주입이 가능하다
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardControllerTest.class);
	
	// @before
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
		
	}
	
	@Test
	public void a1삽입테스트() throws Exception	{
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/add.do")
				.param("title", "테스트제목")
				.param("content", "테스트내용")
				.param("writer", "테스트작성자"))
				.andReturn()
				.getFlashMap()
					.toString());
	}
	
	@Test
	public void a2수정테스트() throws Exception	{
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/modify.do")
				.param("title", "테스트제목2")
				.param("content", "테스트내용2")
				.param("boardNo", "1"))
				.andReturn()
				.getFlashMap()
					.toString());
	}
	@Test
	public void a3상세조회테스트() throws Exception	{
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/detail.do")
				.param("boardNo", "1"))
				.andReturn() 
				.getModelAndView()
				.getModel()
					.toString());
	}
	
	@Test
	public void a4목록테스트() throws Exception	{
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/list.do"))
				.andReturn() 
				.getModelAndView()
				.getModel()
					.toString());
	}
	
	@Test
	public void a삽입테스트() throws Exception	{
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/add.do")
				.param("title", "테스트제목")
				.param("content", "테스트내용")
				.param("writer", "테스트작성자"))
				.andReturn()
				.getFlashMap()
					.toString());
	}
	
	@Test
	public void a5삭제테스트() throws Exception	{
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/remove.do")
				.param("boardNo", "1"))
				.andReturn()
				.getFlashMap()
					.toString());
	}

}

