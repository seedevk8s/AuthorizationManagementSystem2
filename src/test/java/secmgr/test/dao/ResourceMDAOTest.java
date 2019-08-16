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
import able.secmgr.service.dao.ResourceMDAO;
import able.secmgr.vo.ResourceRoleVO;
import able.secmgr.vo.ResourceVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring/context-common.xml",
									"file:src/main/resources/spring/context-datasource.xml",
									"file:src/main/resources/spring/context-mapper.xml",
									"file:src/main/resources/spring/context-transaction.xml"})
public class ResourceMDAOTest {
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	@Resource(name="resourceMDAO")
	ResourceMDAO resourceMDAO;

	@Test
	public void selectResourceVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testresource1");
		
		ResourceVO resourceVO = resourceMDAO.selectResourceVO(hMap);
		assertNotNull(resourceVO);
		assertEquals("testresource1", resourceVO.getId());
		assertEquals("testname1", resourceVO.getName());
		assertEquals("y",resourceVO.getYn());
		assertEquals("testdesc1", resourceVO.getDesc());
		assertEquals(1, resourceVO.getSort());
		assertEquals("/test1/**", resourceVO.getPattern());
		assertEquals("junittest",resourceVO.getRegUser());
		assertEquals("url",resourceVO.getType());
		assertNotNull(resourceVO.getRegDateTime());
		assertNull(resourceVO.getModUser());
		assertNull(resourceVO.getModDateTime());
	}
	
	@Test
	public void insertResourceVO() throws Exception {
		ResourceVO resource = new ResourceVO();
		resource.setId("testresource3");
		resource.setName("testname3");
		resource.setYn("y");
		resource.setDesc("testdesc3");
		resource.setSort(1);
		resource.setPattern("/test3/**");
		resource.setType("url");
		resource.setRegUser("junittest");
		resourceMDAO.insertResourceVO(resource);
		
		HMap hMap = new HMap();
		hMap.put("id", "testresource3");
		ResourceVO resourceVO = resourceMDAO.selectResourceVO(hMap);
		assertNotNull(resourceVO);
		assertEquals(resource.getId(), resourceVO.getId());
		assertEquals(resource.getName(), resourceVO.getName());
		assertEquals(resource.getDesc(), resourceVO.getDesc());
		assertEquals(resource.getYn(),resourceVO.getYn());
		assertEquals(resource.getRegUser(), resourceVO.getRegUser());
		assertNotNull(resourceVO.getRegDateTime());
		assertNull(resourceVO.getModUser());
		assertNull(resourceVO.getModDateTime());
	}

	@Test
	public void updateResourceVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testresource1");
		ResourceVO resourceVO = resourceMDAO.selectResourceVO(hMap);
		
		resourceVO.setName("testname2");
		resourceVO.setYn("n");
		resourceVO.setDesc("testdesc2");
		resourceVO.setSort(2);
		resourceVO.setPattern("/test2/**");
		resourceVO.setType("url");
		resourceVO.setModUser("junittest");
		
		resourceMDAO.updateResourceVO(resourceVO);
		
		ResourceVO resource = resourceMDAO.selectResourceVO(hMap);
		assertNotNull(resource);
		assertEquals(resourceVO.getName(), resource.getName());
		assertEquals(resourceVO.getDesc(), resource.getDesc());
		assertEquals(resourceVO.getYn(), resource.getYn());
		assertEquals(resourceVO.getModUser(), resource.getModUser());
		assertNotNull(resource.getModDateTime());
	}
	
	@Test
	public void deleteResourceVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("id", "testresource4");
		resourceMDAO.deleteResourceVO(hMap);
		
		ResourceVO resourceVO = resourceMDAO.selectResourceVO(hMap);
		assertNull(resourceVO);
	}
	
	//ResourceRole
	@Test
	public void selectResourceRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("resId", "testresource1");
		hMap.put("roleId", "ROLE_TEST1");
		resourceMDAO.selectResourceRoleVOList(hMap);

		List<ResourceRoleVO> list = resourceMDAO.selectResourceRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		ResourceRoleVO resourceRoleVO = list.get(0);
		assertEquals("testresource1", resourceRoleVO.getResId());
		assertEquals("ROLE_TEST1",resourceRoleVO.getRoleId());
		assertEquals("testname1",resourceRoleVO.getRoleName());
		assertEquals("junittest", resourceRoleVO.getRegUser());
		assertNotNull(resourceRoleVO.getRegDateTime());
	}
	
	@Test
	public void insertResourceRoleVO() throws Exception {
		ResourceRoleVO resRoleVO = new ResourceRoleVO();
		resRoleVO.setResId("testresource4");
		resRoleVO.setRoleId("ROLE_TEST4");
		resRoleVO.setRegUser("junittest");
		resourceMDAO.insertResourceRoleVO(resRoleVO);
		
		HMap hMap = new HMap();
		hMap.put("resId", "testresource4");
		hMap.put("roleId", "ROLE_TEST4");
		resourceMDAO.selectResourceRoleVOList(hMap);

		List<ResourceRoleVO> list = resourceMDAO.selectResourceRoleVOList(hMap);
		assertNotNull(list);
		assertEquals(1, list.size());
		
		ResourceRoleVO resourceRoleVO = list.get(0);
		assertEquals("testresource4", resourceRoleVO.getResId());
		assertEquals("ROLE_TEST4",resourceRoleVO.getRoleId());
		assertEquals("testname4",resourceRoleVO.getRoleName());
		assertEquals("junittest", resourceRoleVO.getRegUser());
		assertNotNull(resourceRoleVO.getRegDateTime());
		
	}
	
	@Test
	public void deleteResourceRoleVO() throws Exception {
		HMap hMap = new HMap();
		hMap.put("resId", "testresource1");
		hMap.put("roleId", "ROLE_TEST1");
		resourceMDAO.deleteResourceRoleVO(hMap);
		
		List<ResourceRoleVO> list = resourceMDAO.selectResourceRoleVOList(hMap);
		assertEquals(0,list.size());
	}
	
	
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
				+ "VALUES ( 'testresource4', 'testname4', 'y', 'testdesc4', 4,'/test4/**','url','junittest', CURRENT_TIMESTAMP )" );
		//ROLES
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST1', 'testname1', 'y', 'testdesc1', 'junittest', CURRENT_TIMESTAMP )" );
		jdbcTemplate.execute("INSERT INTO ROLES( ROLE_ID, ROLE_NAME, ROLE_YN, ROLE_DESC, ROLE_REG_USER, ROLE_REG_DATETIME ) "
				+ "VALUES ( 'ROLE_TEST4', 'testname4', 'y', 'testdesc4', 'junittest', CURRENT_TIMESTAMP )" );
		//RESOURCE_ROLES
		jdbcTemplate.execute("INSERT INTO "
				+ "RESOURCE_ROLES(RES_ID,ROLE_ID, REG_USER, REG_DATETIME ) "
				+ "VALUES ( 'testresource1', 'ROLE_TEST1', 'junittest', CURRENT_TIMESTAMP )" );
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
