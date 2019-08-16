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
import able.secmgr.service.dao.GroupMDAO;
import able.secmgr.vo.GroupRoleVO;
import able.secmgr.vo.GroupUserVO;
import able.secmgr.vo.GroupVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class GroupMDAOTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="groupMDAO")
	GroupMDAO groupMDAO;

	@Test
	public void selectGroupVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testgroup1");
		
		GroupVO groupVO = groupMDAO.selectGroupVO(hMap);
		assertNotNull(groupVO);
		assertEquals("testgroup1", groupVO.getId());
		assertEquals("testname1", groupVO.getName());
		assertEquals("y",groupVO.getYn());
		assertEquals("junittest",groupVO.getRegUser());
		assertNotNull(groupVO.getRegDateTime());
		assertNull(groupVO.getModUser());
		assertNull(groupVO.getModDateTime());
	}
	
	@Test
	public void insertGroupVO() throws Exception {
		GroupVO group = new GroupVO();
		group.setId("testgroup3");
		group.setName("testname3");
		group.setYn("y");
		group.setRegUser("junittest");
		groupMDAO.insertGroupVO(group);
		
		HMap hMap = new HMap();
		hMap.put("id", "testgroup3");
		GroupVO groupVO = groupMDAO.selectGroupVO(hMap);
		assertNotNull(groupVO);
		assertEquals(group.getId(), groupVO.getId());
		assertEquals(group.getName(), groupVO.getName());
		assertEquals(group.getYn(),groupVO.getYn());
		assertEquals(group.getRegUser(), groupVO.getRegUser());
		assertNotNull(groupVO.getRegDateTime());
		assertNull(groupVO.getModUser());
		assertNull(groupVO.getModDateTime());
	}

	@Test
	public void updateGroupVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testgroup1");
		GroupVO groupVO = groupMDAO.selectGroupVO(hMap);
		
		groupVO.setName("testname2");
		groupVO.setYn("n");
		groupVO.setModUser("junittest");
		
		groupMDAO.updateGroupVO(groupVO);
		
		GroupVO resource = groupMDAO.selectGroupVO(hMap);
		assertNotNull(resource);
		assertEquals(groupVO.getName(), resource.getName());
		assertEquals(groupVO.getYn(), resource.getYn());
		assertEquals(groupVO.getModUser(), resource.getModUser());
		assertNotNull(resource.getModDateTime());
	}
	
	@Test
	public void deleteGroupVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testgroup4");
		groupMDAO.deleteGroupVO(hMap);
		
		GroupVO groupVO = groupMDAO.selectGroupVO(hMap);
		assertNull(groupVO);
	}
	
	//GroupRole
	@Test
	public void selectGroupRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("groupId", "testgroup1");
		hMap.put("roleId", "ROLE_TEST1");
		groupMDAO.selectGroupRoleVOList(hMap);

		List<GroupRoleVO> list = groupMDAO.selectGroupRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		GroupRoleVO resourceRoleVO = list.get(0);
		assertEquals("testgroup1", resourceRoleVO.getGroupId());
		assertEquals("ROLE_TEST1",resourceRoleVO.getRoleId());
		assertEquals("testname1",resourceRoleVO.getRoleName());
		assertEquals("junittest", resourceRoleVO.getRegUser());
		assertNotNull(resourceRoleVO.getRegDateTime());
	}
	
	@Test
	public void insertGroupRoleVO() throws Exception {
		GroupRoleVO groupRoleVO = new GroupRoleVO();
		groupRoleVO.setGroupId("testgroup4");
		groupRoleVO.setRoleId("ROLE_TEST4");
		groupRoleVO.setRegUser("junittest");
		groupMDAO.insertGroupRoleVO(groupRoleVO);
		
		HMap hMap = new HMap();
		hMap.put("groupId", "testgroup4");
		hMap.put("roleId", "ROLE_TEST4");
		groupMDAO.selectGroupRoleVOList(hMap);

		List<GroupRoleVO> list = groupMDAO.selectGroupRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		GroupRoleVO groupRole = list.get(0);
		assertEquals("testgroup4", groupRole.getGroupId());
		assertEquals("ROLE_TEST4",groupRole.getRoleId());
		assertEquals("testname4",groupRole.getRoleName());
		assertEquals("junittest", groupRole.getRegUser());
		assertNotNull(groupRole.getRegDateTime());
		
	}
	
	@Test
	public void deleteResourceRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("groupId", "testgroup1");
		hMap.put("roleId", "ROLE_TEST1");
		groupMDAO.deleteGroupRoleVO(hMap);
		
		List<GroupRoleVO> list = groupMDAO.selectGroupRoleVOList(hMap);
		assertEquals(0,list.size());
	}
	
	//GroupUser
	@Test
	public void selectGroupUserVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("groupId", "testgroup1");
		hMap.put("userId", "testuser1");
		groupMDAO.selectGroupUserVOList(hMap);

		List<GroupUserVO> list = groupMDAO.selectGroupUserVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		GroupUserVO groupUserVO = list.get(0);
		assertEquals("testgroup1", groupUserVO.getGroupId());
		assertEquals("testuser1",groupUserVO.getUserId());
		assertEquals("testname1",groupUserVO.getUserName());
		assertEquals("junittest", groupUserVO.getRegUser());
		assertNotNull(groupUserVO.getRegDateTime());
	}
	
	@Test
	public void insertGroupUserVO() throws Exception {
		GroupUserVO groupRoleVO = new GroupUserVO();
		groupRoleVO.setGroupId("testgroup4");
		groupRoleVO.setUserId("testuser4");
		groupRoleVO.setRegUser("junittest");
		groupMDAO.insertGroupUserVO(groupRoleVO);
		
		HMap hMap = new HMap();
		hMap.put("groupId", "testgroup4");
		hMap.put("userId", "testuser4");
		groupMDAO.selectGroupUserVOList(hMap);

		List<GroupUserVO> list = groupMDAO.selectGroupUserVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		GroupUserVO groupRole = list.get(0);
		assertEquals("testgroup4", groupRole.getGroupId());
		assertEquals("testuser4",groupRole.getUserId());
		assertEquals("testname4",groupRole.getUserName());
		assertEquals("junittest", groupRole.getRegUser());
		assertNotNull(groupRole.getRegDateTime());
	}
	
	@Test
	public void deleteGroupUserVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("resId", "testresource1");
		hMap.put("roleId", "ROLE_TEST1");
		groupMDAO.deleteGroupRoleVO(hMap);
		
		List<GroupUserVO> list = groupMDAO.selectGroupUserVOList(hMap);
		assertEquals(0,list.size());
	}
	
	@Test
	public void selectMaxGroupId() throws Exception{
		HMap hMap = new HMap();
		String id = groupMDAO.selectMaxGroupId(hMap);
		assertNotNull(id);
	}
	
	
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
				+ "VALUES ( 'testgroup4', 'testname4', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//ROLES
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST4', 'testname4', 'y', 'testdesc4', 'junittest', CURRENT_TIMESTAMP )" );
		//USERS
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser1', 'testname1', '11111111', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO USERS( USER_ID, USER_NAME, USER_PWD, USER_YN, USER_REG_USER, USER_REG_DATETIME ) "
				+ "VALUES ( 'testuser4', 'testname4', '44444444', 'y', 'junittest', CURRENT_TIMESTAMP )" );
		//GRUOP_ROLES
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_ROLES(GROUP_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'ROLE_TEST1', 'junittest', CURRENT_TIMESTAMP )" );
		//GRUOP_USERS
		jdbcTemplate.execute("INSERT INTO "
				+ "GROUP_USERS(GROUP_ID,USER_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testgroup1', 'testuser1', 'junittest', CURRENT_TIMESTAMP )" );
		
		
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
