package able.secmgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import able.com.service.HService;
import able.com.vo.HMap;
import able.secmgr.service.RoleService;
import able.secmgr.service.dao.RoleMDAO;
import able.secmgr.vo.RoleVO;

/**
 * @ClassName   : RoleServiceImpl.java
 * @Description : RoleService 구현 클래스
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
@Service("roleService")
public class RoleServiceImpl extends HService implements RoleService {
	
	@Resource(name = "roleMDAO")
	RoleMDAO roleMDAO;

	/*
	 * @see able.secmgr.service.RoleService#selectRoleVOList(able.com.vo.HMap)
	 */
	@Override
	public List<RoleVO> selectRoleVOList(HMap hMap) throws Exception {
		return roleMDAO.selectRoleVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.RoleService#selectRoleVO(able.com.vo.HMap)
	 */
	@Override
	public RoleVO selectRoleVO(HMap hMap) throws Exception {
		return roleMDAO.selectRoleVO(hMap);
	}

	/*
	 * @see able.secmgr.service.RoleService#insertRoleVO(able.secmgr.vo.RoleVO)
	 */
	@Override
	public void insertRoleVO(RoleVO role) throws Exception {
		roleMDAO.insertRoleVO(role);
	}

	/*
	 * @see able.secmgr.service.RoleService#updateRoleVO(able.secmgr.vo.RoleVO)
	 */
	@Override
	public void updateRoleVO(RoleVO role) throws Exception {
		roleMDAO.updateRoleVO(role);
	}

	/*
	 * @see able.secmgr.service.RoleService#deleteRoleVO(able.com.vo.HMap)
	 */
	@Override
	public void deleteRoleVO(HMap hMap) throws Exception {
		roleMDAO.deleteRoleVO(hMap);
	}

}
