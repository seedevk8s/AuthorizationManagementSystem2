package able.secmgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import able.com.service.HService;
import able.com.vo.HMap;
import able.secmgr.service.GroupService;
import able.secmgr.service.dao.GroupMDAO;
import able.secmgr.vo.GroupRoleVO;
import able.secmgr.vo.GroupUserVO;
import able.secmgr.vo.GroupVO;

/**
 * @ClassName   : GroupServiceImpl.java
 * @Description : GroupService 구현 클래스
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
@Service("groupService")
public class GroupServiceImpl extends HService implements GroupService {
	
	@Resource(name = "groupMDAO")
	GroupMDAO groupMDAO;

	/*
	 * @see able.secmgr.service.GroupService#selectGroupVOList(able.com.vo.HMap)
	 */
	@Override
	public List<GroupVO> selectGroupVOList(HMap hMap) throws Exception {
		return groupMDAO.selectGroupVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.GroupService#selectGroupVO(able.com.vo.HMap)
	 */
	@Override
	public GroupVO selectGroupVO(HMap hMap) throws Exception {
		return groupMDAO.selectGroupVO(hMap);
	}

	/*
	 * @see able.secmgr.service.GroupService#insertGroupVO(able.secmgr.vo.GroupVO)
	 */
	@Override
	public void insertGroupVO(GroupVO group) throws Exception {
		groupMDAO.insertGroupVO(group);
	}

	/*
	 * @see able.secmgr.service.GroupService#updateGroupVO(able.secmgr.vo.GroupVO)
	 */
	@Override
	public void updateGroupVO(GroupVO group) throws Exception {
		groupMDAO.updateGroupVO(group);
	}

	/*
	 * @see able.secmgr.service.GroupService#deleteGroupVO(able.com.vo.HMap)
	 */
	@Override
	public void deleteGroupVO(HMap hMap) throws Exception {
		groupMDAO.deleteGroupVO(hMap);
	}
	
	/*
	 * @see able.secmgr.service.GroupService#selectGroupUserVOList(able.com.vo.HMap)
	 */
    //GROUP_USERS
	@Override
	public List<GroupUserVO> selectGroupUserVOList(HMap hMap) throws Exception {
		return groupMDAO.selectGroupUserVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.GroupService#addUsers(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void addUsers(List<String> userIds, String groupId, String currentUser) throws Exception {
		
		GroupUserVO groupUser = new GroupUserVO();
		groupUser.setGroupId(groupId);
		groupUser.setRegUser(currentUser);
		
		for(String userId : userIds){
			groupUser.setUserId(userId);
			groupMDAO.insertGroupUserVO(groupUser);
		}
	}

	/*
	 * @see able.secmgr.service.GroupService#delUsers(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void delUsers(List<String> userIds, String groupId, String currentUser) throws Exception {
		HMap hMap = new HMap();
		hMap.put("groupId", groupId);
		
		for(String userId : userIds){
			hMap.put("userId", userId);
			groupMDAO.deleteGroupUserVO(hMap);
		}
	}
	
	/*
	 * @see able.secmgr.service.GroupService#selectAddableGroupUserVOList(able.com.vo.HMap)
	 */
	@Override
	public List<GroupUserVO> selectAddableGroupUserVOList(HMap hMap) {
		return groupMDAO.selectAddableGroupUserVOList(hMap);
	}

	//GROUP_ROLES
	/*
	 * @see able.secmgr.service.GroupService#selectGroupRoleVOList(able.com.vo.HMap)
	 */
	@Override
	public List<GroupRoleVO> selectGroupRoleVOList(HMap hMap) throws Exception {
		return groupMDAO.selectGroupRoleVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.GroupService#addRoles(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void addRoles(List<String> roleIds, String groupId, String currentUser) throws Exception {
		GroupRoleVO groupRole= new GroupRoleVO();
		groupRole.setGroupId(groupId);
		groupRole.setRegUser(currentUser);
		
		for(String roleId : roleIds){
			groupRole.setRoleId(roleId);
			groupMDAO.insertGroupRoleVO(groupRole);
			//권한 추가 로그
			logger.info("Grant role({}) to group({}) by user({})",roleId, groupId, currentUser);
		}
	}

	/*
	 * @see able.secmgr.service.GroupService#delRoles(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void delRoles(List<String> roleIds, String groupId, String currentUser) throws Exception {
		HMap hMap = new HMap();
		hMap.put("groupId", groupId);
		
		for(String roleId : roleIds){
			hMap.put("roleId", roleId);
			groupMDAO.deleteGroupRoleVO(hMap);
			//권한 삭제 로그
			logger.info("Revoke role({}) from group({}) by user({})",roleId, groupId, currentUser);
		}
	}
	
	/*
	 * @see able.secmgr.service.GroupService#selectAddableGroupRoleVOList(able.com.vo.HMap)
	 */
	@Override
	public List<GroupRoleVO> selectAddableGroupRoleVOList(HMap hMap) throws Exception {
		return groupMDAO.selectAddableGroupRoleVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.GroupService#selectMaxGroupId(able.com.vo.HMap)
	 */
	@Override
	public String selectMaxGroupId(HMap hMap) throws Exception {
		return groupMDAO.selectMaxGroupId(hMap);
	}




}
