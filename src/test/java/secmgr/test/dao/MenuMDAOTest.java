package secmgr.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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
import able.secmgr.service.dao.MenuMDAO;
import able.secmgr.vo.MenuRoleVO;
import able.secmgr.vo.MenuVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class MenuMDAOTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="menuMDAO")
	MenuMDAO menuMDAO;

	@Test
	public void selectMenuVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testmenu1");
		
		MenuVO menuVO = menuMDAO.selectMenuVO(hMap);
		assertNotNull(menuVO);
		assertEquals("testmenu1", menuVO.getId());
		assertEquals("testname1", menuVO.getName());
		assertEquals("y",menuVO.getYn());
		assertEquals("testdesc1", menuVO.getDesc());
		assertNotNull(menuVO.getRegDateTime());
		assertNull(menuVO.getModUser());
		assertNull(menuVO.getModDateTime());
	}
	
	@Test
	public void insertMenuVO() throws Exception {
		MenuVO menu = new MenuVO();
		menu.setId("testmenu3");
		menu.setName("testname3");
		menu.setYn("y");
		menu.setDesc("testdesc3");
		menu.setRegUser("junittest");
		menuMDAO.insertMenuVO(menu);
		
		HMap hMap = new HMap();
		hMap.put("id", "testmenu3");
		MenuVO menuVO = menuMDAO.selectMenuVO(hMap);
		assertNotNull(menuVO);
		assertEquals(menu.getId(), menuVO.getId());
		assertEquals(menu.getName(), menuVO.getName());
		assertEquals(menu.getDesc(), menuVO.getDesc());
		assertEquals(menu.getYn(),menuVO.getYn());
		assertEquals(menu.getRegUser(), menuVO.getRegUser());
		assertNotNull(menuVO.getRegDateTime());
		assertNull(menuVO.getModUser());
		assertNull(menuVO.getModDateTime());
	}

	@Test
	public void updateMenuVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testmenu1");
		MenuVO menuVO = menuMDAO.selectMenuVO(hMap);
		
		menuVO.setName("testname2");
		menuVO.setYn("n");
		menuVO.setDesc("testdesc2");
		menuVO.setModUser("junittest");
		
		menuMDAO.updateMenuVO(menuVO);
		
		MenuVO menu = menuMDAO.selectMenuVO(hMap);
		assertNotNull(menu);
		assertEquals(menuVO.getName(), menu.getName());
		assertEquals(menuVO.getDesc(), menu.getDesc());
		assertEquals(menuVO.getYn(), menu.getYn());
		assertEquals(menuVO.getModUser(), menu.getModUser());
		assertNotNull(menu.getModDateTime());
	}
	
	@Test
	public void deleteMenuVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testmenu4");
		menuMDAO.deleteMenuVO(hMap);
		
		MenuVO menuVO = menuMDAO.selectMenuVO(hMap);
		assertNull(menuVO);
	}
	
	//MenuRole
	@Test
	public void selectMenuRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("menuId", "testmenu1");
		hMap.put("roleId", "ROLE_TEST1");
		menuMDAO.selectMenuRoleVOList(hMap);

		List<MenuRoleVO> list = menuMDAO.selectMenuRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		MenuRoleVO menuRoleVO = list.get(0);
		assertEquals("testmenu1", menuRoleVO.getMenuId());
		assertEquals("ROLE_TEST1",menuRoleVO.getRoleId());
		assertEquals("testname1",menuRoleVO.getRoleName());
		assertEquals("junittest", menuRoleVO.getRegUser());
		assertNotNull(menuRoleVO.getRegDateTime());
	}
	
	@Test
	public void insertMenuRoleVO() throws Exception {
		MenuRoleVO menuRoleVO = new MenuRoleVO();
		menuRoleVO.setMenuId("testmenu4");
		menuRoleVO.setRoleId("ROLE_TEST4");
		menuRoleVO.setRegUser("junittest");
		menuMDAO.insertMenuRoleVO(menuRoleVO);
		
		HMap hMap = new HMap();
		hMap.put("menuId", "testmenu4");
		hMap.put("roleId", "ROLE_TEST4");
		menuMDAO.selectMenuRoleVOList(hMap);

		List<MenuRoleVO> list = menuMDAO.selectMenuRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		MenuRoleVO menuRole = list.get(0);
		assertEquals("testmenu4", menuRole.getMenuId());
		assertEquals("ROLE_TEST4",menuRole.getRoleId());
		assertEquals("testname4",menuRole.getRoleName());
		assertEquals("junittest", menuRole.getRegUser());
		assertNotNull(menuRole.getRegDateTime());
		
	}
	
	@Test
	public void deleteMenuRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("menuId", "testmenu1");
		hMap.put("roleId", "ROLE_TEST1");
		menuMDAO.deleteMenuRoleVO(hMap);
		
		List<MenuRoleVO> list = menuMDAO.selectMenuRoleVOList(hMap);
		assertEquals(0,list.size());
	}
	
	
	@BeforeClass
	public static void beforeClass(){
	}
	
	@Before
	public void before(){
		JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource); 
		
		//MENU
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU(MENU_ID,MENU_NAME,MENU_YN,MENU_DESC,MENU_REG_USER, MENU_REG_DATETIME ) "
				+ "VALUES ( 'testmenu1', 'testname1', 'y', 'testdesc1','junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU(MENU_ID,MENU_NAME,MENU_YN,MENU_DESC,MENU_REG_USER, MENU_REG_DATETIME ) "
				+ "VALUES ( 'testmenu4', 'testname4', 'y', 'testdesc4','junittest', CURRENT_TIMESTAMP )" );
		//ROLES
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST4', 'testname4', 'y', 'testdesc4', 'junittest', CURRENT_TIMESTAMP )" );
		//MENU_ROLES
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU_ROLES(MENU_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testmenu1', 'ROLE_TEST1', 'junittest', CURRENT_TIMESTAMP )" );
	}
	
	@After
	public void after(){
		JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource);
		
		//MENU_ROLES
		jdbcTemplate.execute("DELETE FROM MENU_ROLES WHERE MENU_ID LIKE 'test%'");
		//MENU
		jdbcTemplate.execute("DELETE FROM MENU WHERE MENU_ID LIKE 'test%'");
		//ROLES
		jdbcTemplate.execute("DELETE FROM ROLES WHERE ROLE_ID LIKE 'ROLE_TEST%'");
	}
	
	@AfterClass
	public static void afterClass(){
	}

}
