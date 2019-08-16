package able.secmgr.service;

import java.util.List;

import able.com.vo.HMap;
import able.secmgr.vo.ResourceRoleVO;
import able.secmgr.vo.ResourceVO;

/**
 * @ClassName   : ResourceService.java
 * @Description : 리소스 서비스 클래스
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
public interface ResourceService {
	/**
	 * 리소스 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<ResourceVO> selectResourceVOList(HMap hMap) throws Exception;
	
	/**
	 * 리소스 상세 정보
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public ResourceVO selectResourceVO(HMap hMap) throws Exception;
	
	/**
	 * 리소스 정보 등록
	 * @param resource
	 * @throws Exception
	 */
	public void insertResourceVO(ResourceVO resource) throws Exception;
	
	/**
	 * 리소스 정보 수정
	 * @param resource
	 * @throws Exception
	 */
	public void updateResourceVO(ResourceVO resource) throws Exception;
	
	/**
	 * 리소스 정보 삭제
	 * @param hMap
	 * @throws Exception
	 */
	public void deleteResourceVO(HMap hMap) throws Exception;
	
	/**
	 * 리소스 아이디(Sequence)
	 * @param hMap
	 * @return
	 */
	public String selectMaxResId(HMap hMap);
	
	// RESOURCE_ROLE
	/**
	 * 리소스 권한 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<ResourceRoleVO> selectResourceRoleVOList(HMap hMap) throws Exception;
	
	/**
	 * 권한 추가
	 * @param roleIds
	 * @param resId
	 * @param currentUser
	 * @throws Exception
	 */
	public void addRoles(List<String> roleIds, String resId, String currentUser) throws Exception;
	
	/**
	 * 권한 삭제
	 * @param roleIds
	 * @param resId
	 * @param currentUser
	 * @throws Exception
	 */
	public void delRoles(List<String> roleIds, String resId, String currentUser) throws Exception;

	/**
	 * 등록 가능한 리소스 권한 목록
	 * @param hMap
	 * @return
	 */
	public List<ResourceRoleVO> selectAddableResourceRoleVOList(HMap hMap);
	
}
