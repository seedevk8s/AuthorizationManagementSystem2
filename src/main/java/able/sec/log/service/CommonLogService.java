package able.sec.log.service;

import java.util.List;

import able.sec.log.vo.CommonLogVO;

/**
 * @ClassName   : CommonLogService.java
 * @Description : 클래스 설명을 기술합니다.
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
public interface CommonLogService {
	/**
	 * 권한 변경 로그 등록
	 * @param commonLogVO
	 */
	public void insertAuthChangeLog(CommonLogVO commonLogVO);
	
	/**
	 * 권한 변경 유저 로그 등록
	 * @param userIds
	 * @param groupId
	 * @param currentUser
	 * @param param
	 */
	public void insertAuthUserChangeLog(List<String> userIds, String groupId, String currentUser, String param);
	
	/**
	 * 다수 권한 변경에 따른 로그 등록 
	 * @param roleIds
	 * @param groupId
	 * @param currentUser
	 * @param param
	 */
	public void insertAuthRoleChangeLog(List<String> roleIds, String groupId, String currentUser, String param);
	
	/**
	 * 그룹에 속한 권한 목록
	 * @param groupId
	 * @return
	 */
	public List<String> selectRoles(String groupId);
	
	/**
	 * 그룹에 속한 유저 목록
	 * @param groupId
	 * @return
	 */
	public List<String> selectUsers(String groupId);
	
	/**
	 * 로그인/로그아웃 로그 등록
	 * @param commonLogVO
	 */
	public void insertLogInOutLog(CommonLogVO commonLogVO);

	/**
	 * 사용 로그 등록
	 * @param commonLogVO
	 */
	public void insertUseLog(CommonLogVO commonLogVO);
}
