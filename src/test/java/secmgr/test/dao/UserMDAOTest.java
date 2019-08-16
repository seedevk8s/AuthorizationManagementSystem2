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
import able.secmgr.service.dao.UserMDAO;
import able.secmgr.vo.UserGroupVO;
import able.secmgr.vo.UserVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class UserMDAOTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="userMDAO")
	UserMDAO userMDAO;
	
	@Test
	public void selectUserVOList() throws Exception {
		HMap hMap = new HMap();
		List<UserVO> list = userMDAO.selectUserVOList(hMap);
		assertNotNull(list);
		assertEquals(5,list.size());

		//paging test
		hMap = new HMap();
		hMap.put("rowCount", 1);
		hMap.put("offset", 1);
		list = userMDAO.selectUserVOList(hMap);
		assertNotNull(list);
		assertEquals(1,list.size());
		assertEquals("testuser2", list.get(0).getId());
		
		//search test
		hMap = new HMap();
		hMap.put("qcol", "name");
		hMap.put("qval", "name5");
		list = userMDAO.selectUserVOList(hMap);
		assertNotNull(list);
		assertEquals(1,list.size());
		assertEquals("testuser5", list.get(0).getId());
		
		//order test
		hMap = new HMap();
		hMap.put("ocol", "regDate");
		hMap.put("order", "desc");
		list = userMDAO.selectUserVOList(hMap);
		assertNotNull(list);
		assertEquals(5,list.size());
		assertEquals("testuser6", list.get(0).getId());
	}

	@Test
	public void selectUserVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testuser1");
		
		UserVO userVO = userMDAO.selectUserVO(hMap);
		assertNotNull(userVO);
		assertEquals("testuser1", userVO.getId());
		assertEquals("testname1", userVO.getName());
		assertEquals("y",userVO.getYn());
		assertEquals("junittest",userVO.getRegUser());
		assertNotNull(userVO.getRegDateTime());
		assertNull(userVO.getModUser());
		assertNull(userVO.getModDateTime());
	}
	
	@Test
	public void insertUserVO() throws Exception {
		UserVO user = new UserVO();
		user.setId("testuser3");
		user.setPwd("3333333");
		user.setName("testusername3");
		user.setYn("y");
		user.setRegUser("junittest");
		userMDAO.insertUserVO(user);
		
		HMap hMap = new HMap();
		hMap.put("id", "testuser3");
		UserVO userVO = userMDAO.selectUserVO(hMap);
		assertNotNull(userVO);
		assertEquals(user.getId(), userVO.getId());
		assertEquals(user.getName(), userVO.getName());
		assertEquals(user.getYn(),userVO.getYn());
		assertEquals(user.getRegUser(), userVO.getRegUser());
		assertNotNull(userVO.getRegDateTime());
		assertNull(userVO.getModUser());
		assertNull(userVO.getModDateTime());
	}

	@Test
	public void updateUserVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testuser1");
		UserVO userVO = userMDAO.selectUserVO(hMap);
		
		userVO.setName("testname2");
		userVO.setModUser("junittest");
		userVO.setYn("n");
		
		userMDAO.updateUserVO(userVO);
		
		UserVO user = userMDAO.selectUserVO(hMap);
		assertNotNull(user);
		assertEquals(userVO.getName(), user.getName());
		assertEquals(userVO.getYn(), user.getYn());
		assertEquals(userVO.getModUser(), user.getModUser());
		assertNotNull(user.getModDateTime());
	}
	
	@Test
	public void updateUserVO_pwd() throws Exception{
		HMap hMap = new HMap();
		hMap.put("id", "testuser1");
		UserVO userVO = userMDAO.selectUserVO(hMap);
		userVO.setPwd("22222222");
		userVO.setModUser("junittest");
		
		userMDAO.updateUserVO(userVO);
		String pwd = new JdbcTemplate(dataSource).queryForObject("SELECT USER_PWD FROM USERS WHERE USER_ID = 'testuser1'", String.class);
		assertEquals("22222222", pwd);
	}

	@Test
	public void deleteUserVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testuser2");
		userMDAO.deleteUserVO(hMap);
		
		UserVO userVO = userMDAO.selectUserVO(hMap);
		assertNull(userVO);
	}
	
	//UserGroup
	@Test
	public void selectGroupUserVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("groupId", "testgroup1");
		hMap.put("userId", "testuser1");
		userMDAO.selectUserGroupVOList(hMap);

		List<UserGroupVO> list = userMDAO.selectUserGroupVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		UserGroupVO groupUserVO = list.get(0);
		assertEquals("testuser1",groupUserVO.getUserId());
		assertEquals("testgroup1", groupUserVO.getGroupId());
		assertEquals("testname1",groupUserVO.getGroupName());
		assertEquals("junittest", groupUserVO.getRegUser());
		assertNotNull(groupUserVO.getRegDateTime());
	}
	
	@BeforeClass
	public static void beforeClass(){
	}
	
	@Before
	public void before(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser1', 'testname1', '11111111', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser2', 'testname2', '22222222', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser4', 'testname4', '44444444', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser5', 'testname5', '55555555', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser6', 'testname6', '66666666', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//GROUPS
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUPS( GROUP_ID, GROUP_NAME, GROUP_YN, GROUP_REG_USER, GROUP_REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testname1', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//GRUOP_USERS
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_USERS(GROUP_ID,USER_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testuser1', 'junittest', CURRENT_TIMESTAMP )" );
	}
	
	@After
	public void after(){
		JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource);
		//GROUP_USERS
		jdbcTemplate.execute("DELETE FROM GROUP_USERS WHERE GROUP_ID LIKE 'test%'");
		//GROUPS
		jdbcTemplate.execute("DELETE FROM GROUPS WHERE GROUP_ID LIKE 'test%'");
		//USERS
		jdbcTemplate.execute("DELETE FROM USERS WHERE USER_ID LIKE 'test%'");
	}
	
	@AfterClass
	public static void afterClass(){
	}

}
