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
import able.secmgr.service.GroupService;
import able.secmgr.vo.GroupRoleVO;
import able.secmgr.vo.GroupUserVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class GroupServiceTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="groupServiceImpl")
	GroupService groupService;

	//추가 메소드만 작성
	
	//GroupRole
	@Test
	public void addRoles() throws Exception {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add("ROLE_TEST3");
		roleIds.add("ROLE_TEST4");
		String groupId = "testgroup1";
		String currentUser = "junittest";
		groupService.addRoles(roleIds, groupId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("groupId", groupId);
		List<GroupRoleVO> list = groupService.selectGroupRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(4, list.size());
		
		for(GroupRoleVO groupRole : list){
			assertNotNull(groupRole);
			assertEquals(groupId,groupRole.getGroupId());
			assertNotNull(groupRole.getRoleId());
			assertNotNull(groupRole.getRoleName());
			assertEquals(currentUser,groupRole.getRegUser());
			assertNotNull(groupRole.getRegDateTime());
		}
	}
	
	@Test
	public void delRoles() throws Exception {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add("ROLE_TEST1");
		roleIds.add("ROLE_TEST2");
		String groupId = "testgroup1";
		String currentUser = "junittest";
		groupService.delRoles(roleIds, groupId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("groupId", groupId);
		List<GroupRoleVO> list = groupService.selectGroupRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	//GroupUser
	@Test
	public void addUsers() throws Exception {
		List<String> userIds = new ArrayList<String>();
		userIds.add("testuser3");
		userIds.add("testuser4");
		String groupId = "testgroup1";
		String currentUser = "junittest";
		groupService.addUsers(userIds, groupId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("groupId", groupId);
		List<GroupUserVO> list = groupService.selectGroupUserVOList(hMap);
		assertNotNull(list);
		assertEquals(4, list.size());
		
		for(GroupUserVO groupUser : list){
			assertNotNull(groupUser);
			assertEquals(groupId,groupUser.getGroupId());
			assertNotNull(groupUser.getUserId());
			assertNotNull(groupUser.getUserName());
			assertEquals(currentUser,groupUser.getRegUser());
			assertNotNull(groupUser.getRegDateTime());
		}
	}
	
	@Test
	public void delUsers() throws Exception {
		List<String> userIds = new ArrayList<String>();
		userIds.add("testuser1");
		userIds.add("testuser2");
		String groupId = "testgroup1";
		String currentUser = "junittest";
		groupService.delUsers(userIds, groupId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("groupId", groupId);
		List<GroupUserVO> list = groupService.selectGroupUserVOList(hMap);
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
		//ROLES
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST2', 'testname2', 'y', 'testdesc2', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST3', 'testname3', 'y', 'testdesc3', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST4', 'testname4', 'y', 'testdesc4', 'junittest', CURRENT_TIMESTAMP )" );
		//USERS
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser1', 'testname1', '11111111', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser2', 'testname2', '22222222', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser3', 'testname3', '33333333', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser4', 'testname4', '44444444', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//GRUOP_ROLES
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_ROLES(GROUP_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'ROLE_TEST1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_ROLES(GROUP_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'ROLE_TEST2', 'junittest', CURRENT_TIMESTAMP )" );
		//GRUOP_USERS
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_USERS(GROUP_ID,USER_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testuser1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_USERS(GROUP_ID,USER_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testuser2', 'junittest', CURRENT_TIMESTAMP )" );
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
