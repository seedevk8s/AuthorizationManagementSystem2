package able.secmgr.service;

import java.util.List;

import able.com.vo.HMap;
import able.secmgr.vo.GroupRoleVO;
import able.secmgr.vo.GroupUserVO;
import able.secmgr.vo.GroupVO;

/**
 * @ClassName   : GroupService.java
 * @Description : 그룹 서비스 클래스
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
public interface GroupService {
	
    /**
     * 그룹 목록
     * @param hMap
     * @return
     * @throws Exception
     */
	public List<GroupVO> selectGroupVOList(HMap hMap) throws Exception;
	
	/**
	 * 그룹 상세 정보
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public GroupVO selectGroupVO(HMap hMap) throws Exception;
	
	/**
	 * 그룹 정보 등록
	 * @param group
	 * @throws Exception
	 */
	public void insertGroupVO(GroupVO group) throws Exception;
	
	/**
	 * 그룹 정보 수정
	 * @param group
	 * @throws Exception
	 */
	public void updateGroupVO(GroupVO group) throws Exception;
	
	/**
	 * 그룹 정보 삭제
	 * @param hMap
	 * @throws Exception
	 */
	public void deleteGroupVO(HMap hMap) throws Exception;
	
	/**
	 * 그룹 아이디(Sequence)
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public String selectMaxGroupId(HMap hMap) throws Exception;
	
	//GROUP_USERS
	/**
	 * 그룹 내 유저 리스트
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<GroupUserVO> selectGroupUserVOList(HMap hMap) throws Exception;
	
	/**
	 * 그룹에 유저 추가
	 * @param userIds
	 * @param groupId
	 * @param currentUser
	 * @throws Exception
	 */
	public void addUsers(List<String> userIds, String groupId, String currentUser) throws Exception; 
	
	/**
	 * 그룹에서 유저 삭제
	 * @param userIds
	 * @param groupId
	 * @param currentUser
	 * @throws Exception
	 */
	public void delUsers(List<String> userIds, String groupId, String currentUser) throws Exception;
	
	/**
	 * 등록 가능한 유저 목록
	 * @param hMap
	 * @return
	 */
	public List<GroupUserVO> selectAddableGroupUserVOList(HMap hMap);
	
	//GROUP_ROLES
	/**
	 * 그룹 권한 리스트
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<GroupRoleVO> selectGroupRoleVOList(HMap hMap) throws Exception;
	
	/**
	 * 그룹에 권한 추가
	 * @param roleIds
	 * @param groupId
	 * @param currentUser
	 * @throws Exception
	 */
	public void addRoles(List<String> roleIds, String groupId, String currentUser) throws Exception;
	
	/**
	 * 그룹에서 권한 삭제
	 * @param roleIds
	 * @param groupId
	 * @param currentUser
	 * @throws Exception
	 */
	public void delRoles(List<String> roleIds, String groupId, String currentUser) throws Exception;
	
	/**
	 * 등록 가능한 권한 목록
	 * @param hMap
	 * @return
	 * @throws Exception
	 */
	public List<GroupRoleVO> selectAddableGroupRoleVOList(HMap hMap) throws Exception;

}
