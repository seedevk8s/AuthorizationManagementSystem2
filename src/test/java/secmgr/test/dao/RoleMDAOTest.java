package secmgr.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import able.com.vo.HMap;
import able.secmgr.service.dao.RoleMDAO;
import able.secmgr.vo.RoleVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class RoleMDAOTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="roleMDAO")
	RoleMDAO roleMDAO;

	@Test
	public void selectRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "ROLE_TEST1");
		
		RoleVO roleVO = roleMDAO.selectRoleVO(hMap);
		assertNotNull(roleVO);
		assertEquals("ROLE_TEST1", roleVO.getId());
		assertEquals("testname1", roleVO.getName());
		assertEquals("testdesc1", roleVO.getDesc());
		assertEquals("y",roleVO.getYn());
		assertEquals("junittest",roleVO.getRegUser());
		assertNotNull(roleVO.getRegDateTime());
		assertNull(roleVO.getModUser());
		assertNull(roleVO.getModDateTime());
	}
	
	@Test
	public void insertRoleVO() throws Exception {
		RoleVO role = new RoleVO();
		role.setId("ROLE_TEST3");
		role.setName("testname3");
		role.setDesc("testdesc3");
		role.setYn("y");
		role.setRegUser("junittest");
		roleMDAO.insertRoleVO(role);
		
		HMap hMap = new HMap();
		hMap.put("id", "ROLE_TEST3");
		RoleVO roleVO = roleMDAO.selectRoleVO(hMap);
		assertNotNull(roleVO);
		assertEquals(role.getId(), roleVO.getId());
		assertEquals(role.getName(), roleVO.getName());
		assertEquals(role.getDesc(), roleVO.getDesc());
		assertEquals(role.getYn(),roleVO.getYn());
		assertEquals(role.getRegUser(), roleVO.getRegUser());
		assertNotNull(roleVO.getRegDateTime());
		assertNull(roleVO.getModUser());
		assertNull(roleVO.getModDateTime());
	}

	@Test
	public void updateRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "ROLE_TEST1");
		RoleVO roleVO = roleMDAO.selectRoleVO(hMap);
		
		roleVO.setName("testname2");
		roleVO.setModUser("junittest");
		roleVO.setDesc("testdesc2");
		roleVO.setYn("n");
		
		roleMDAO.updateRoleVO(roleVO);
		
		RoleVO role = roleMDAO.selectRoleVO(hMap);
		assertNotNull(role);
		assertEquals(roleVO.getName(), role.getName());
		assertEquals(roleVO.getDesc(), role.getDesc());
		assertEquals(roleVO.getYn(), role.getYn());
		assertEquals(roleVO.getModUser(), role.getModUser());
		assertNotNull(role.getModDateTime());
	}
	
	@Test
	public void deleteRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "ROLE_TEST1");
		roleMDAO.deleteRoleVO(hMap);
		
		RoleVO roleVO = roleMDAO.selectRoleVO(hMap);
		assertNull(roleVO);
	}
	
	@BeforeClass
	public static void beforeClass(){
	}
	
	@Before
	public void before(){
		new JdbcTemplate(dataSource).execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
	}
	
	@After
	public void after(){
		new JdbcTemplate(dataSource).execute("DELETE FROM ROLES WHERE ROLE_ID LIKE 'ROLE_TEST%'");
	}
	
	@AfterClass
	public static void afterClass(){
	}

}
