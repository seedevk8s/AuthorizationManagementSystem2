package able.secmgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import able.com.service.HService;
import able.com.vo.HMap;
import able.secmgr.service.UserService;
import able.secmgr.service.dao.GroupMDAO;
import able.secmgr.service.dao.UserMDAO;
import able.secmgr.vo.GroupUserVO;
import able.secmgr.vo.UserGroupVO;
import able.secmgr.vo.UserVO;

/**
 * @ClassName   : UserServiceImpl.java
 * @Description : UserService 구현 클래스
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
@Service("userService")
public class UserServiceImpl extends HService implements UserService {
	
	@Resource(name = "userMDAO")
	UserMDAO userMDAO;
	
	@Resource(name = "groupMDAO")
	GroupMDAO groupMDAO;

	/*
	 * @see able.secmgr.service.UserService#selectUserVOList(able.com.vo.HMap)
	 */
	@Override
	public List<UserVO> selectUserVOList(HMap hMap) throws Exception {
		return userMDAO.selectUserVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.UserService#selectUserVO(able.com.vo.HMap)
	 */
	@Override
	public UserVO selectUserVO(HMap hMap) throws Exception {
		return userMDAO.selectUserVO(hMap);
	}

	/*
	 * @see able.secmgr.service.UserService#insertUserVO(able.secmgr.vo.UserVO)
	 */
	@Override
	public void insertUserVO(UserVO user) throws Exception {
		userMDAO.insertUserVO(user);
	}

	/*
	 * @see able.secmgr.service.UserService#updateUserVO(able.secmgr.vo.UserVO)
	 */
	@Override
	public void updateUserVO(UserVO user) throws Exception {
		userMDAO.updateUserVO(user);
	}

	/*
	 * @see able.secmgr.service.UserService#deleteUserVO(able.com.vo.HMap)
	 */
	@Override
	public void deleteUserVO(HMap hMap) throws Exception {
		userMDAO.deleteUserVO(hMap);
	}
	
	//GROUP_USERS
	/*
	 * @see able.secmgr.service.UserService#selectUserGroupVOList(able.com.vo.HMap)
	 */
	@Override
	public List<UserGroupVO> selectUserGroupVOList(HMap hMap) throws Exception {
		return userMDAO.selectUserGroupVOList(hMap);
	}

	/*
	 * @see able.secmgr.service.UserService#addGroups(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void addGroups(List<String> groupIds, String userId,
			String currentUser) throws Exception {
		GroupUserVO groupUser= new GroupUserVO();
		groupUser.setUserId(userId);
		groupUser.setRegUser(currentUser);
		
		for(String groupId : groupIds){
			groupUser.setGroupId(groupId);
			groupMDAO.insertGroupUserVO(groupUser);
		}

	}

	/*
	 * @see able.secmgr.service.UserService#delGroups(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void delGroups(List<String> groupIds, String userId,
			String currentUser) throws Exception {
		HMap hMap = new HMap();
		hMap.put("userId", userId);
		
		for(String groupId : groupIds){
			hMap.put("groupId", groupId);
			groupMDAO.deleteGroupUserVO(hMap);
		}
	}

	/*
	 * @see able.secmgr.service.UserService#selectAddableUserGroupVOList(able.com.vo.HMap)
	 */
	@Override
	public List<UserGroupVO> selectAddableUserGroupVOList(HMap hMap) {
		return userMDAO.selectAddableUserGroupVOList(hMap);
	}

}
