package able.secmgr.service;

import java.util.List;

import able.com.vo.HMap;
import able.secmgr.vo.MenuRoleVO;
import able.secmgr.vo.MenuVO;

/**
 * @ClassName   : MenuService.java
 * @Description : 메뉴 서비스 클래스
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
public interface MenuService {
	
    /**
     * 메뉴 목록
     * @param hMap
     * @return
     * @throws Exception
     */
	public List<MenuVO> selectMenuVOList(HMap hMap) throws Exception;
	
	/**
	 * 메뉴 상세 정보
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public MenuVO selectMenuVO(HMap hMap) throws Exception;
	
	/**
	 * 메뉴 정보 등록
	 * @param menu
	 * @throws Exception
	 */
	public void insertMenuVO(MenuVO menu) throws Exception;
	
	/**
	 * 메뉴 정보 수정
	 * @param menu
	 * @throws Exception
	 */
	public void updateMenuVO(MenuVO menu) throws Exception;
	
	/**
	 * 메뉴 정보 삭제
	 * @param hMap
	 * @throws Exception
	 */
	public void deleteMenuVO(HMap hMap) throws Exception;
	
	/**
	 * 메뉴 아이디(Sequence)
	 * @param hMap
	 * @return
	 */
	public String selectMaxMenuId(HMap hMap);
	
	//MENU_ROLES
	/**
	 * 메뉴 권한 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<MenuRoleVO> selectMenuRoleVOList(HMap hMap) throws Exception;
	
	/**
	 * 권한 추가
	 * @param roleIds
	 * @param menuId
	 * @param currentUser
	 * @throws Exception
	 */
	public void addRoles(List<String> roleIds, String menuId, String currentUser) throws Exception;
	
	/**
	 * 권한 삭제
	 * @param roleIds
	 * @param menuId
	 * @param currentUser
	 * @throws Exception
	 */
	public void delRoles(List<String> roleIds, String menuId, String currentUser) throws Exception;

	/**
	 * 등록 가능한 메뉴 권한 목록
	 * @param hMap
	 * @return
	 */
	public List<MenuRoleVO> selectAddableMenuRoleVOList(HMap hMap);

	
}
