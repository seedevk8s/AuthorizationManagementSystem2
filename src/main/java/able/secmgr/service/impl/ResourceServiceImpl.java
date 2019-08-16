package able.secmgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import able.com.service.HService;
import able.com.vo.HMap;
import able.secmgr.service.ResourceService;
import able.secmgr.service.dao.ResourceMDAO;
import able.secmgr.vo.ResourceRoleVO;
import able.secmgr.vo.ResourceVO;

/**
 * @ClassName   : ResourceServiceImpl.java
 * @Description : ResourceService 구현 클래스
 * @author ADM기술팀
 * @since 2016. 7. 1.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 7. 1.       ADM기술팀                                      최초 생성
 * </pre>
 */
@Service("resourceService")
public class ResourceServiceImpl extends HService implements ResourceService {
	
	@Resource(name = "resourceMDAO")
	ResourceMDAO resourceMDAO;

	/*
	 * @see able.secmgr.service.ResourceService#selectResourceVOList(able.com.vo.HMap)
	 */
	@Override
	public List<ResourceVO> selectResourceVOList(HMap hMap) throws Exception {
		return resourceMDAO.selectResourceVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.ResourceService#selectResourceVO(able.com.vo.HMap)
	 */
	@Override
	public ResourceVO selectResourceVO(HMap hMap) throws Exception {
		return resourceMDAO.selectResourceVO(hMap);
	}

	/*
	 * @see able.secmgr.service.ResourceService#insertResourceVO(able.secmgr.vo.ResourceVO)
	 */
	@Override
	public void insertResourceVO(ResourceVO resource) throws Exception {
		resourceMDAO.insertResourceVO(resource);
	}

	/*
	 * @see able.secmgr.service.ResourceService#updateResourceVO(able.secmgr.vo.ResourceVO)
	 */
	@Override
	public void updateResourceVO(ResourceVO resource) throws Exception {
		resourceMDAO.updateResourceVO(resource);
	}

	/*
	 * @see able.secmgr.service.ResourceService#deleteResourceVO(able.com.vo.HMap)
	 */
	@Override
	public void deleteResourceVO(HMap hMap)	throws Exception {
		resourceMDAO.deleteResourceVO(hMap);
	}

	/*
	 * @see able.secmgr.service.ResourceService#selectMaxResId(able.com.vo.HMap)
	 */
	@Override
	public String selectMaxResId(HMap hMap) {
		return resourceMDAO.selectMaxResId(hMap);
	}
	
	
	/*
	 * @see able.secmgr.service.ResourceService#selectResourceRoleVOList(able.com.vo.HMap)
	 */
	//RESOURCE_ROLES
	@Override
	public List<ResourceRoleVO> selectResourceRoleVOList(HMap hMap)	throws Exception {
		return resourceMDAO.selectResourceRoleVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.ResourceService#addRoles(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void addRoles(List<String> roleIds, String resId, String currentUser) throws Exception {
		ResourceRoleVO resourceRole= new ResourceRoleVO();
		resourceRole.setResId(resId);
		resourceRole.setRegUser(currentUser);
		
		for(String roleId : roleIds){
			resourceRole.setRoleId(roleId);
			resourceMDAO.insertResourceRoleVO(resourceRole);
			//권한 추가 로그
			logger.info("Grant role({}) to resource({}) by user({})",roleId, resId, currentUser);
		}
	}

	/*
	 * @see able.secmgr.service.ResourceService#delRoles(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void delRoles(List<String> roleIds, String resId, String currentUser) throws Exception {
		HMap hMap = new HMap();
		hMap.put("resId", resId);
		
		for(String roleId : roleIds){
			hMap.put("roleId", roleId);
			resourceMDAO.deleteResourceRoleVO(hMap);
			//권한 삭제 로그
			logger.info("Revoke role({}) from resource({}) by user({})",roleId, resId, currentUser);
		}
	}


	/*
	 * @see able.secmgr.service.ResourceService#selectAddableResourceRoleVOList(able.com.vo.HMap)
	 */
	@Override
	public List<ResourceRoleVO> selectAddableResourceRoleVOList(HMap hMap) {
		return resourceMDAO.selectAddableResourceRoleVOList(hMap);
	}

}
