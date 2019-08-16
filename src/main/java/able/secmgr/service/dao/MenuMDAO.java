package able.secmgr.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import able.com.vo.HMap;
import able.secmgr.vo.MenuRoleVO;
import able.secmgr.vo.MenuVO;

/**
 * @ClassName   : MenuMDAO.java
 * @Description : 메뉴 데이터 매퍼 클래스
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
@Mapper("menuMDAO")
public interface MenuMDAO {
	
	public List<MenuVO> selectMenuVOList(HMap hMap);
	
	public MenuVO selectMenuVO(HMap hMap);
	
	public void insertMenuVO(MenuVO menu);
	
	public void updateMenuVO(MenuVO menu);
	
	public void deleteMenuVO(HMap hMap);
	
	public String selectMaxMenuId(HMap hMap);
	
	// MENU_ROLES
	public List<MenuRoleVO> selectMenuRoleVOList(HMap hMap);
	
	public void insertMenuRoleVO(MenuRoleVO menuRole);
	
	public void deleteMenuRoleVO(HMap hMap);

	public List<MenuRoleVO> selectAddableMenuRoleVOList(HMap hMap);


}
