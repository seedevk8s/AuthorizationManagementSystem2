package able.secmgr.service;

import java.util.List;

import able.com.vo.HMap;
import able.secmgr.vo.RoleVO;

/**
 * @ClassName   : RoleService.java
 * @Description : 롤 서비스 클래스
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
public interface RoleService {
	/**
	 * 권한 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<RoleVO> selectRoleVOList(HMap hMap) throws Exception;
	
	/**
	 * 권한 상세 정보
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public RoleVO selectRoleVO(HMap hMap) throws Exception;
	
	/**
	 * 권한 정보 등록
	 * @param role
	 * @throws Exception
	 */
	public void insertRoleVO(RoleVO role) throws Exception;
	
	/**
	 * 권한 정보 수정
	 * @param role
	 * @throws Exception
	 */
	public void updateRoleVO(RoleVO role) throws Exception;
	
	/**
	 * 권한 정보 삭제
	 * @param hMap
	 * @throws Exception
	 */
	public void deleteRoleVO(HMap hMap) throws Exception;
}
