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
import able.secmgr.service.ResourceService;
import able.secmgr.vo.ResourceRoleVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class ResourceServiceTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="resourceServiceImpl")
	ResourceService resourceService;

	//추가 메소드만 작성
	
	//ResourceRole
	@Test
	public void addRoles() throws Exception {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add("ROLE_TEST3");
		roleIds.add("ROLE_TEST4");
		String resId = "testresource1";
		String currentUser = "junittest";
		resourceService.addRoles(roleIds, resId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("resId", resId);
		List<ResourceRoleVO> list = resourceService.selectResourceRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(4, list.size());
		
		for(ResourceRoleVO resRole : list){
			assertNotNull(resRole);
			assertEquals(resId,resRole.getResId());
			assertNotNull(resRole.getRoleId());
			assertNotNull(resRole.getRoleName());
			assertEquals(currentUser,resRole.getRegUser());
			assertNotNull(resRole.getRegDateTime());
		}
	}
	
	@Test
	public void delRoles() throws Exception {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add("ROLE_TEST1");
		roleIds.add("ROLE_TEST2");
		String resId = "testresource1";
		String currentUser = "junittest";
		resourceService.delRoles(roleIds, resId, currentUser);
		
		HMap hMap = new HMap();
		hMap.put("resId", resId);
		List<ResourceRoleVO> list = resourceService.selectResourceRoleVOList(hMap);
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
		
		//RESOURCES
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCES(RES_ID,RES_NAME,RES_YN,RES_DESC,RES_SORT,RES_PATTERN,RES_TYPE,RES_REG_USER, RES_REG_DATETIME ) "
				+ "VALUES ( 'testresource1', 'testname1', 'y', 'testdesc1', 1,'/test1/**','url','junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCES(RES_ID,RES_NAME,RES_YN,RES_DESC,RES_SORT,RES_PATTERN,RES_TYPE,RES_REG_USER, RES_REG_DATETIME ) "
				+ "VALUES ( 'testresource2', 'testname1', 'y', 'testdesc2', 2,'/test2/**','url','junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCES(RES_ID,RES_NAME,RES_YN,RES_DESC,RES_SORT,RES_PATTERN,RES_TYPE,RES_REG_USER, RES_REG_DATETIME ) "
				+ "VALUES ( 'testresource3', 'testname3', 'y', 'testdesc3', 3,'/test3/**','url','junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCES(RES_ID,RES_NAME,RES_YN,RES_DESC,RES_SORT,RES_PATTERN,RES_TYPE,RES_REG_USER, RES_REG_DATETIME ) "
				+ "VALUES ( 'testresource4', 'testname4', 'y', 'testdesc4', 4,'/test4/**','url','junittest', CURRENT_TIMESTAMP )" );
		//ROLES
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST2', 'testname2', 'y', 'testdesc2', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST3', 'testname3', 'y', 'testdesc3', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST4', 'testname4', 'y', 'testdesc4', 'junittest', CURRENT_TIMESTAMP )" );
		//RESOURCE_ROLES
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCE_ROLES(RES_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testresource1', 'ROLE_TEST1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCE_ROLES(RES_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testresource1', 'ROLE_TEST2', 'junittest', CURRENT_TIMESTAMP )" );
	}
	
	@After
	public void after(){
		JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource);
		
		//RESOURCE_ROLE
		jdbcTemplate.execute("DELETE FROM RESOURCE_ROLES WHERE RES_ID LIKE 'test%'");
		//RESOURCES
		jdbcTemplate.execute("DELETE FROM RESOURCES WHERE RES_ID LIKE 'test%'");
		//ROLES
		jdbcTemplate.execute("DELETE FROM ROLES WHERE ROLE_ID LIKE 'ROLE_TEST%'");
	}
	
	@AfterClass
	public static void afterClass(){
	}

}
