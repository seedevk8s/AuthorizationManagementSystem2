package able.sec.log.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import able.sec.log.vo.CommonLogVO;

/**
 * @ClassName   : CommonLogMDAO.java
 * @Description : 공통 로그 매퍼 클래스
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
@Mapper("commonLogMDAO")
public interface CommonLogMDAO {
	
	public void insertAuthChangeLog(CommonLogVO commonLogVO);
	
	public void insertLogInOutLog(CommonLogVO commonLogVO);
	
	public void insertUseLog(CommonLogVO commonLogVO);
	
	public List<String> selectRoles(String groupId);
	
	public List<String> selectUsers(String groupId);
}
