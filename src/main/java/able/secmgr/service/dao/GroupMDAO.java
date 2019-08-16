package able.secmgr.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import able.com.vo.HMap;
import able.secmgr.vo.GroupRoleVO;
import able.secmgr.vo.GroupUserVO;
import able.secmgr.vo.GroupVO;

/**
 * @ClassName   : GroupMDAO.java
 * @Description : 그룹 데이터 매퍼 클래스
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
@Mapper("groupMDAO")
public interface GroupMDAO {
	
	public List<GroupVO> selectGroupVOList(HMap hMap);
	
	public GroupVO selectGroupVO(HMap hMap);
	
	public void insertGroupVO(GroupVO group);
	
	public void updateGroupVO(GroupVO group);
	
	public void deleteGroupVO(HMap hMap);
	
	public String selectMaxGroupId(HMap hMap);
	
	// GROUP_ROLES
	public List<GroupRoleVO> selectGroupRoleVOList(HMap hMap);
	
	public void insertGroupRoleVO(GroupRoleVO groupRole);
	
	public void deleteGroupRoleVO(HMap hMap);
	
	public List<GroupRoleVO> selectAddableGroupRoleVOList(HMap hMap);
	
	// GROUP_USERS
	public List<GroupUserVO> selectGroupUserVOList(HMap hMap);
	
	public void insertGroupUserVO(GroupUserVO groupUser);
	
	public void deleteGroupUserVO(HMap hMap);

	public List<GroupUserVO> selectAddableGroupUserVOList(HMap hMap);


}
