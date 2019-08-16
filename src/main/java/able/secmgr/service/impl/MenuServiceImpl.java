package able.secmgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import able.com.service.HService;
import able.com.vo.HMap;
import able.secmgr.service.MenuService;
import able.secmgr.service.dao.MenuMDAO;
import able.secmgr.vo.MenuRoleVO;
import able.secmgr.vo.MenuVO;

/**
 * @ClassName   : MenuServiceImpl.java
 * @Description : MenuService 구현 클래스
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
@Service("menuService")
public class MenuServiceImpl extends HService implements MenuService {

	@Resource(name = "menuMDAO")
	MenuMDAO menuMDAO;
	
	/*
	 * @see able.secmgr.service.MenuService#selectMenuVOList(able.com.vo.HMap)
	 */
	@Override
	public List<MenuVO> selectMenuVOList(HMap hMap) throws Exception {
		return menuMDAO.selectMenuVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.MenuService#selectMenuVO(able.com.vo.HMap)
	 */
	@Override
	public MenuVO selectMenuVO(HMap hMap) throws Exception {
		return menuMDAO.selectMenuVO(hMap);
	}

	/*
	 * @see able.secmgr.service.MenuService#insertMenuVO(able.secmgr.vo.MenuVO)
	 */
	@Override
	public void insertMenuVO(MenuVO menu) throws Exception {
		menuMDAO.insertMenuVO(menu);
	}

	/*
	 * @see able.secmgr.service.MenuService#updateMenuVO(able.secmgr.vo.MenuVO)
	 */
	@Override
	public void updateMenuVO(MenuVO menu) throws Exception {
		menuMDAO.updateMenuVO(menu);
	}

	/*
	 * @see able.secmgr.service.MenuService#deleteMenuVO(able.com.vo.HMap)
	 */
	@Override
	public void deleteMenuVO(HMap hMap) throws Exception {
		menuMDAO.deleteMenuVO(hMap);
	}

	/*
	 * @see able.secmgr.service.MenuService#selectMaxMenuId(able.com.vo.HMap)
	 */
	@Override
	public String selectMaxMenuId(HMap hMap) {
		return menuMDAO.selectMaxMenuId(hMap);
	}
	
	/*
	 * @see able.secmgr.service.MenuService#selectMenuRoleVOList(able.com.vo.HMap)
	 */
	//MENU_ROLES
	@Override
	public List<MenuRoleVO> selectMenuRoleVOList(HMap hMap) throws Exception {
		return menuMDAO.selectMenuRoleVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.MenuService#addRoles(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void addRoles(List<String> roleIds, String menuId, String currentUser) throws Exception {
		MenuRoleVO menuRole= new MenuRoleVO();
		menuRole.setMenuId(menuId);
		menuRole.setRegUser(currentUser);
		
		for(String roleId : roleIds){
			menuRole.setRoleId(roleId);
			menuMDAO.insertMenuRoleVO(menuRole);
			//권한 추가 로그
			logger.info("Grant role({}) to menu({}) by user({})",roleId, menuId, currentUser);
		}
	}

	/*
	 * @see able.secmgr.service.MenuService#delRoles(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void delRoles(List<String> roleIds, String menuId, String currentUser) throws Exception {
		HMap hMap = new HMap();
		hMap.put("menuId", menuId);
		
		for(String roleId : roleIds){
			hMap.put("roleId", roleId);
			menuMDAO.deleteMenuRoleVO(hMap);
			//권한 삭제 로그
			logger.info("Revoke role({}) from menu({}) by user({})",roleId, menuId, currentUser);
		}
	}

	/*
	 * @see able.secmgr.service.MenuService#selectAddableMenuRoleVOList(able.com.vo.HMap)
	 */
	@Override
	public List<MenuRoleVO> selectAddableMenuRoleVOList(HMap hMap) {
		return menuMDAO.selectAddableMenuRoleVOList(hMap);
	}
	

}
