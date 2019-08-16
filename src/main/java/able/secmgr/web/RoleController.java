package able.secmgr.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import able.com.vo.HMap;
import able.com.web.HController;
import able.com.web.view.PagingInfo;
import able.secmgr.service.RoleService;
import able.secmgr.vo.RoleVO;
import able.security.userdetails.util.AbleUserDetailsHelper;

/**
 * @ClassName   : RoleController.java
 * @Description : 롤 서비스 컨트롤러
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
@Controller
public class RoleController extends HController{
	
	@Resource(name="roleService")
	private RoleService roleSerivce;
	
	@RequestMapping(value = "/secmgr/role/selectRoleList.do", method = RequestMethod.GET)
	public String selectRoleList(HMap hMap, ModelMap model) throws Exception {
		
		List<RoleVO> allRoleVOList = roleSerivce.selectRoleVOList(hMap);
		int totalSize = allRoleVOList.size();
		
		//페이지 지정 없을시 1페이지
		int pageNo = hMap.containsKey("pageNo") ? Integer.parseInt((String) hMap.getString("pageNo")) : 1;
		
		int pageUnit = propertiesService.getInt("pageUnit");	// 1페이지 게시물 수
		int pageSize = propertiesService.getInt("pageSize");	// 페이지 리스트의 페이지 수
		
		PagingInfo pagingInfo = new PagingInfo();
		pagingInfo.setCurrentPageNo(pageNo);	//현재 페이지
		pagingInfo.setRecordCountPerPage(pageUnit);	// 1페이지 게시물 수
		pagingInfo.setPageSize(pageSize);	//페이지 리스트의 페이지 수 
		pagingInfo.setTotalRecordCount(totalSize);	// 전체 게시물 수 
		
		int offset = pagingInfo.getFirstRecordIndex();
		
		/*hsqldb
		hMap.put("pageUnit", pageUnit);
		hMap.put("offset", offset);*/

		//oracle
		hMap.put("limit", pageUnit*pageNo);
		hMap.put("offset", offset*(pageNo-1));
		
		//해당 내용 얻기
		List<RoleVO> roleVOList = roleSerivce.selectRoleVOList(hMap);
		model.put("roleVOList", roleVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/role/roleList";
	}
	
	@RequestMapping(value = "/secmgr/role/selectRole.do", method = RequestMethod.GET)
	public String selectRole(HMap hMap, ModelMap model) throws Exception{
		
		RoleVO roleVO = roleSerivce.selectRoleVO(hMap);
		
		model.put("roleVO", roleVO);
		return "able/secmgr/role/roleInfo";
	}
	
	@RequestMapping(value = "/secmgr/role/updateRole.do", method = RequestMethod.POST)
	public String updateRole(RoleVO role, ModelMap model) throws Exception{
		//현재 사용자
		String modUser = AbleUserDetailsHelper.getUserName();
		role.setModUser(modUser);
		logger.debug("update role : " + role.toString());
		//업데이트
		roleSerivce.updateRoleVO(role);
		
		return "redirect:/secmgr/role/selectRole.do?id="+role.getId();
	}
	
	@RequestMapping(value = "/secmgr/role/createRole.do", method = RequestMethod.POST)
	public String createRole(@ModelAttribute("roleVO") @Valid RoleVO role, BindingResult result) throws Exception{
		logger.debug("role : " +role.toString());

		//중복 ID 검사
		HMap hMap = new HMap();
		hMap.put("id", role.getId());
		if( roleSerivce.selectRoleVO(hMap) != null){
			result.addError(new FieldError("roleVO", "id", role.getId(),false,null,null,
					"이미 사용중인 ID 입니다"));
		}
		
		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
			}
			return "able/secmgr/role/roleForm";
		}
		
		//현재 사용자
		String regUser = AbleUserDetailsHelper.getUserName();
		role.setRegUser(regUser);
		logger.debug("create role : " + role.toString());
		//DB입력
		roleSerivce.insertRoleVO(role);
		
		return "redirect:/secmgr/role/selectRoleList.do";
	}
	
	@RequestMapping(value = "/secmgr/role/showRoleForm.do", method = RequestMethod.GET)
	public String showRoleForm(HMap hMap, ModelMap model) throws Exception{
		//빈 객체 설정
		RoleVO role = new RoleVO();
		role.setId("ROLE_"); //prefix 설정
		model.addAttribute("roleVO", role);
		return "able/secmgr/role/roleForm";
	}
	
	@RequestMapping(value = "/secmgr/role/deleteRole.do", method = RequestMethod.GET)
	public String deleteRole(HMap hMap, ModelMap model) throws Exception{
		roleSerivce.deleteRoleVO(hMap);
		return "redirect:/secmgr/role/selectRoleList.do";
	}
	 
}
