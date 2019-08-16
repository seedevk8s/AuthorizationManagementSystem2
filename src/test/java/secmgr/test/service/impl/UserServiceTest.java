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
import able.secmgr.service.UserService;
import able.secmgr.vo.UserGroupVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class UserServiceTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="userServiceImpl")
	UserService userService;

	//추가 메소드만 작성
	
	//GroupUser
	@Test
	public void addGroups() throws Exception {
		List<String> groupIds = new ArrayList<String>();
		groupIds.add("testgroup3");
		groupIds.add("testgroup4");
		String userId = "testuser1";
		String currentUser = "junittest";
		userService.addGroups(groupIds, userId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("userId", userId);
		List<UserGroupVO> list = userService.selectUserGroupVOList(hMap);
		assertNotNull(list);
		assertEquals(4, list.size());
		
		for(UserGroupVO userGroup : list){
			assertNotNull(userGroup);
			assertEquals(userId,userGroup.getUserId());
			assertNotNull(userGroup.getGroupId());
			assertNotNull(userGroup.getGroupName());
			assertEquals(currentUser,userGroup.getRegUser());
			assertNotNull(userGroup.getRegDateTime());
		}
	}
	
	@Test
	public void delUsers() throws Exception {
		List<String> groupIds = new ArrayList<String>();
		groupIds.add("testgroup1");
		groupIds.add("testgroup2");
		String userId = "testuser1";
		String currentUser = "junittest";
		userService.delGroups(groupIds, userId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("userId", userId);
		List<UserGroupVO> list = userService.selectUserGroupVOList(hMap);
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
		
		//GROUPS
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUPS( GROUP_ID, GROUP_NAME, GROUP_YN, GROUP_REG_USER, GROUP_REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testname1', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUPS( GROUP_ID, GROUP_NAME, GROUP_YN, GROUP_REG_USER, GROUP_REG_DATETIME ) "
				+ "VALUES ( 'testgroup2', 'testname4', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUPS( GROUP_ID, GROUP_NAME, GROUP_YN, GROUP_REG_USER, GROUP_REG_DATETIME ) "
				+ "VALUES ( 'testgroup3', 'testname4', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUPS( GROUP_ID, GROUP_NAME, GROUP_YN, GROUP_REG_USER, GROUP_REG_DATETIME ) "
				+ "VALUES ( 'testgroup4', 'testname4', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//USERS
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser1', 'testname1', '11111111', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser2', 'testname2', '22222222', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser3', 'testname3', '33333333', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser4', 'testname4', '44444444', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//GRUOP_USERS
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_USERS(GROUP_ID,USER_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testuser1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_USERS(GROUP_ID,USER_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup2', 'testuser1', 'junittest', CURRENT_TIMESTAMP )" );
	}
	
	@After
	public void after(){
		JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource);
		
		//GROUP_ROLES
		jdbcTemplate.execute("DELETE FROM GROUP_ROLES WHERE GROUP_ID LIKE 'test%'");
		//GROUP_USERS
		jdbcTemplate.execute("DELETE FROM GROUP_USERS WHERE GROUP_ID LIKE 'test%'");
		//GROUPS
		jdbcTemplate.execute("DELETE FROM GROUPS WHERE GROUP_ID LIKE 'test%'");
		//ROLES
		jdbcTemplate.execute("DELETE FROM ROLES WHERE ROLE_ID LIKE 'ROLE_TEST%'");
		//ROLES
		jdbcTemplate.execute("DELETE FROM USERS WHERE USER_ID LIKE 'test%'");
	}
	
	@AfterClass
	public static void afterClass(){
	}

}
