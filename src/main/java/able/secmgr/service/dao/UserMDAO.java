package able.secmgr.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import able.com.vo.HMap;
import able.secmgr.vo.UserGroupVO;
import able.secmgr.vo.UserVO;

/**
 * @ClassName   : UserMDAO.java
 * @Description : 유저 데이터 매퍼 클래스
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
@Mapper("userMDAO")
public interface UserMDAO {
	
	public List<UserVO> selectUserVOList(HMap hMap);
	
	public UserVO selectUserVO(HMap hMap);
	
	public void insertUserVO(UserVO user);
	
	public void updateUserVO(UserVO user);
	
	public void deleteUserVO(HMap hMap);
	
	public List<UserGroupVO> selectUserGroupVOList(HMap hMap);

	public List<UserGroupVO> selectAddableUserGroupVOList(HMap hMap);

}
