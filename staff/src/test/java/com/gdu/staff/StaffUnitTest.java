package com.gdu.staff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffUnitTest {

	@Autowired
	private StaffMapper staffMapper;
	
	@Before
	public void 입력() {
		StaffDTO staff = new StaffDTO("99999", "김기획", "기획부", 5000);
		assertEquals(1, staffMapper.addStaff(staff)); 
	}
	
	@Test
	public void 사원조회테스트() {
		int query = 11111;
		StaffDTO staff = staffMapper.getStaffByNo(query);
		assertNotNull(staff);
	}
	
	
}
