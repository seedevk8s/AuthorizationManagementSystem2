package able.sec.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import able.com.service.HService;
import able.sec.log.service.CommonLogService;
import able.sec.log.service.dao.CommonLogMDAO;
import able.sec.log.vo.CommonLogVO;

/**
 * @ClassName   : CommonLogServiceImpl.java
 * @Description : 공통 로그 서비스 구현 클래스
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
@Service("commonLogService")
public class CommonLogServiceImpl extends HService implements CommonLogService {

	@Resource(name="commonLogMDAO")
	private CommonLogMDAO commonLogMDAO;
	
	@Override
	public void insertAuthChangeLog(CommonLogVO commonLogVO) {
		commonLogMDAO.insertAuthChangeLog(commonLogVO);
	}
	
	@Override
	public void insertLogInOutLog(CommonLogVO commonLogVO) {
		commonLogMDAO.insertLogInOutLog(commonLogVO);
	}

	@Override
	public void insertUseLog(CommonLogVO commonLogVO) {
		commonLogMDAO.insertUseLog(commonLogVO);
	}

	@Override
	public List<String> selectRoles(String groupId) {
		return commonLogMDAO.selectRoles(groupId);
	}

	@Override
	public void insertAuthUserChangeLog(List<String> userIds, String groupId,
			String currentUser, String param) {
		List<String> roleIds = selectRoles(groupId);
		
		CommonLogVO commonLogVO = new CommonLogVO();
		commonLogVO.setAdminId(currentUser);
		
		if("add".equals(param)){
			//Create
			commonLogVO.setChangeType("C");
		}
		else{
			//Delete
			commonLogVO.setChangeType("D");
		}
		
		for(String userId : userIds){
			commonLogVO.setUserId(userId);
			for(String roleId : roleIds){
				commonLogVO.setAuthority(roleId);
				commonLogMDAO.insertAuthChangeLog(commonLogVO);
			}
		}
	}

	@Override
	public void insertAuthRoleChangeLog(List<String> roleIds, String groupId,
			String currentUser, String param) {
		List<String> userIds = selectUsers(groupId);
		
		CommonLogVO commonLogVO = new CommonLogVO();
		commonLogVO.setAdminId(currentUser);
		
		if("add".equals(param)){
			//Create
			commonLogVO.setChangeType("C");
		}
		else{
			//Delete
			commonLogVO.setChangeType("D");
		}
		
		for(String roleId : roleIds){
			commonLogVO.setAuthority(roleId);
			for(String userId : userIds){
				commonLogVO.setUserId(userId);
				commonLogMDAO.insertAuthChangeLog(commonLogVO);
			}
		}
	}

	@Override
	public List<String> selectUsers(String groupId) {
		return commonLogMDAO.selectUsers(groupId);
	}

}
