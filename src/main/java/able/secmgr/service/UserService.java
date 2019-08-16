package able.secmgr.service;

import java.util.List;

import able.com.vo.HMap;
import able.secmgr.vo.UserGroupVO;
import able.secmgr.vo.UserVO;

/**
 * @ClassName   : UserService.java
 * @Description : 유저 서비스 클래스
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
public interface UserService {
	/**
	 * 유저 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<UserVO> selectUserVOList(HMap hMap) throws Exception;

	/**
	 * 유저 상세 정보
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public UserVO selectUserVO(HMap hMap) throws Exception;
	
	/**
	 * 유저 정보 등록
	 * @param user
	 * @throws Exception
	 */
	public void insertUserVO(UserVO user) throws Exception;
	
	/**
	 * 유저 정보 수정
	 * @param user
	 * @throws Exception
	 */
	public void updateUserVO(UserVO user) throws Exception;
	
	/**
	 * 유저 정보 삭제
	 * @param hMap
	 * @throws Exception
	 */
	public void deleteUserVO(HMap hMap) throws Exception;
	
	//GROUP_USERS
	/**
	 * 유저 그룹 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<UserGroupVO> selectUserGroupVOList(HMap hMap) throws Exception;
	
	/**
	 * 권한 추가
	 * @param groupIds
	 * @param userId
	 * @param currentUser
	 * @throws Exception
	 */
	public void addGroups(List<String> groupIds, String userId, String currentUser) throws Exception; 
	
	/**
	 * 권한 삭제
	 * @param groupIds
	 * @param userId
	 * @param currentUser
	 * @throws Exception
	 */
	public void delGroups(List<String> groupIds, String userId, String currentUser) throws Exception;

	/**
	 * 등록 가능한 유저 그룹 목록
	 * @param hMap
	 * @return
	 */
	public List<UserGroupVO> selectAddableUserGroupVOList(HMap hMap);
	
}
