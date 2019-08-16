package able.secmgr.service.dao;

import java.util.List;

import able.com.mybatis.Mapper;
import able.com.vo.HMap;
import able.secmgr.vo.ResourceRoleVO;
import able.secmgr.vo.ResourceVO;

/**
 * @ClassName   : ResourceMDAO.java
 * @Description : 리소스 데이터 매퍼 클래스
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
@Mapper("resourceMDAO")
public interface ResourceMDAO {
	
	public List<ResourceVO> selectResourceVOList(HMap hMap);
	
	public ResourceVO selectResourceVO(HMap hMap);
	
	public void insertResourceVO(ResourceVO resource);
	
	public void updateResourceVO(ResourceVO resource);
	
	public void deleteResourceVO(HMap hMap);
	
	public String selectMaxResId(HMap hMap);
	
	// RESOURCE_ROLES
	public List<ResourceRoleVO> selectResourceRoleVOList(HMap hMap);
	
	public void insertResourceRoleVO(ResourceRoleVO resourceRole);
	
	public void deleteResourceRoleVO(HMap hMap);

	public List<ResourceRoleVO> selectAddableResourceRoleVOList(HMap hMap);


}
