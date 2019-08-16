package able.secmgr.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import able.com.vo.HMap;
import able.secmgr.vo.RoleVO;

/**
 * @ClassName   : RoleMDAO.java
 * @Description : 권한 데이터 매퍼 클래스
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
@Mapper("roleMDAO")
public interface RoleMDAO {
	
	public List<RoleVO> selectRoleVOList(HMap hMap);
	
	public RoleVO selectRoleVO(HMap hMap);
	
	public void insertRoleVO(RoleVO role);
	
	public void updateRoleVO(RoleVO role);
	
	public void deleteRoleVO(HMap hMap);

}
