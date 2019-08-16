package secmgr.test.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
import able.secmgr.service.MenuService;
import able.secmgr.vo.MenuRoleVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class MenuServiceTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="menuServiceImpl")
	MenuService menuService;

	//추가 메소드만 작성
	
	//ResourceRole
	@Test
	public void addRoles() throws Exception {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add("ROLE_TEST3");
		roleIds.add("ROLE_TEST4");
		String menuId = "testmenu1";
		String currentUser = "junittest";
		menuService.addRoles(roleIds, menuId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("menuId", menuId);
		List<MenuRoleVO> list = menuService.selectMenuRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(4, list.size());
		
		for(MenuRoleVO menuRole : list){
			assertNotNull(menuRole);
			assertEquals(menuId,menuRole.getMenuId());
			assertNotNull(menuRole.getRoleId());
			assertNotNull(menuRole.getRoleName());
			assertEquals(currentUser,menuRole.getRegUser());
			assertNotNull(menuRole.getRegDateTime());
		}
	}
	
	@Test
	public void delRoles() throws Exception {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add("ROLE_TEST1");
		roleIds.add("ROLE_TEST2");
		String menuId = "testmenu1";
		String currentUser = "junittest";
		menuService.delRoles(roleIds, menuId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("menuId", menuId);
		List<MenuRoleVO> list = menuService.selectMenuRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	//Test setting
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
				+ "VALUES ( 'testmenu2', 'testname2', 'y', 'testdesc1','junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU(MENU_ID,MENU_NAME,MENU_YN,MENU_DESC,MENU_REG_USER, MENU_REG_DATETIME ) "
				+ "VALUES ( 'testmenu3', 'testname3', 'y', 'testdesc1','junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU(MENU_ID,MENU_NAME,MENU_YN,MENU_DESC,MENU_REG_USER, MENU_REG_DATETIME ) "
				+ "VALUES ( 'testmenu4', 'testname4', 'y', 'testdesc4','junittest', CURRENT_TIMESTAMP )" );
		//ROLES
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST2', 'testname2', 'y', 'testdesc2', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST3', 'testname3', 'y', 'testdesc3', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST4', 'testname4', 'y', 'testdesc4', 'junittest', CURRENT_TIMESTAMP )" );
		//MENU_ROLES
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU_ROLES(MENU_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testmenu1', 'ROLE_TEST1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "MENU_ROLES(MENU_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testmenu1', 'ROLE_TEST2', 'junittest', CURRENT_TIMESTAMP )" );
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
