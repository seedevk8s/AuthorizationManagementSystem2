package able.secmgr.web;

import java.util.Arrays;
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
import able.secmgr.service.ResourceService;
import able.secmgr.vo.ResourceRoleVO;
import able.secmgr.vo.ResourceVO;
import able.security.userdetails.util.AbleUserDetailsHelper;

/**
 * @ClassName   : ResourceController.java
 * @Description : 리소스 서비스 컨트롤러
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
public class ResourceController extends HController{
	
	@Resource(name="resourceService")
	ResourceService resourceSerivce;
	
	@RequestMapping(value = "/secmgr/resource/selectResourceList.do", method = RequestMethod.GET)
	public String selectResourceList(HMap hMap, ModelMap model) throws Exception {
		
		List<ResourceVO> allResourceVOList = resourceSerivce.selectResourceVOList(hMap);
		int totalSize = allResourceVOList.size();

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
		List<ResourceVO> resourceVOList = resourceSerivce.selectResourceVOList(hMap);
		model.put("resourceVOList", resourceVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/resource/resourceList";
	}
	
	@RequestMapping(value = "/secmgr/resource/selectResource.do", method = RequestMethod.GET)
	public String selectResource(HMap hMap, ModelMap model) throws Exception{
		
		ResourceVO resourceVO = resourceSerivce.selectResourceVO(hMap);
		model.put("resourceVO", resourceVO);
		
		hMap.put("resId", hMap.get("id"));
		
		List<ResourceRoleVO> resourceRoleVOList = resourceSerivce.selectResourceRoleVOList(hMap);
		model.put("resourceRoleVOList", resourceRoleVOList);
		
		return "able/secmgr/resource/resourceInfo";
	}
	
	@RequestMapping(value = "/secmgr/resource/popupResourceRole.do", method = RequestMethod.GET)
	public String popupResourceRole(HMap hMap, ModelMap model) throws Exception {
		//추가 가능한 Role
		List<ResourceRoleVO> allAddableResourceRoleVOList = resourceSerivce.selectAddableResourceRoleVOList(hMap);
		int totalSize = allAddableResourceRoleVOList.size();

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
		List<ResourceRoleVO> addableResourceRoleVOList = resourceSerivce.selectAddableResourceRoleVOList(hMap);
		model.put("addableResourceRoleVOList", addableResourceRoleVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/resource/popupResourceRole";
	}
	
	@RequestMapping(value = "/secmgr/resource/deleteRoles.do", method = RequestMethod.POST)
	public String deleteRoles(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//resourceId, roleIds
		String resourceId = (String) hMap.get("resId");

		//roleId 있는 경우만 작동
		if(hMap.containsKey("roleId")){
			String[] roleIdArr;
			//한개 선택시 String, 복수 선택시 String[]
			if(hMap.get("roleId") instanceof String){
				roleIdArr = new String[]{(String)hMap.get("roleId")};
			} else {
				roleIdArr = (String[]) hMap.get("roleId");
			}
			List<String> roleIds = Arrays.asList(roleIdArr);
			
			//현재 사용자
			String currentUser = AbleUserDetailsHelper.getUserName();
			//삭제 서비스
			resourceSerivce.delRoles(roleIds, resourceId, currentUser);
		}
		
		return "redirect:/secmgr/resource/selectResource.do?id="+resourceId;
	}
	
	@RequestMapping(value = "/secmgr/resource/addRoles.do", method = RequestMethod.POST)
	public String addRoles(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//resId, roleIds
		String resourceId = (String) hMap.get("resId");

		//roleId 있는 경우만 작동
		if(hMap.containsKey("roleId")){
			String[] roleIdArr;
			//한개 선택시 String, 복수 선택시 String[]
			if(hMap.get("roleId") instanceof String){
				roleIdArr = new String[]{(String)hMap.get("roleId")};
			} else {
				roleIdArr = (String[]) hMap.get("roleId");
			}
			List<String> roleIds = Arrays.asList(roleIdArr);
			
			//현재 사용자
			String currentUser = AbleUserDetailsHelper.getUserName();
			//삭제 서비스
			resourceSerivce.addRoles(roleIds, resourceId, currentUser);
		}
		
		return "able/secmgr/closePopup";
	}
	
	@RequestMapping(value = "/secmgr/resource/updateResource.do", method = RequestMethod.POST)
	public String updateResource(@ModelAttribute("resourceVO") @Valid ResourceVO resource, BindingResult result, ModelMap model) throws Exception{
		
		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
				
				HMap hMap = new HMap();
				hMap.put("id", resource.getId());
				//<input>에 없는 값 읽기
				ResourceVO r = resourceSerivce.selectResourceVO(hMap);
				resource.setRegUser(r.getRegUser());
				resource.setRegDateTime(r.getRegDateTime());
				resource.setModUser(r.getModUser());
				resource.setModDateTime(r.getModDateTime());
				
				hMap.put("resId", resource.getId());
				List<ResourceRoleVO> resourceRoleVOList = resourceSerivce.selectResourceRoleVOList(hMap);
				model.put("resourceRoleVOList", resourceRoleVOList);
				
				return "able/secmgr/resource/resourceInfo";
			}
		} else {
			//현재 사용자
			String modUser = AbleUserDetailsHelper.getUserName();
			resource.setModUser(modUser);
			resource.setModDateTime(null);
			logger.debug("update resource: " + resource.toString());
			//업데이트
			resourceSerivce.updateResourceVO(resource);
		}
		
		return "redirect:/secmgr/resource/selectResource.do?id="+resource.getId();
	}
	
	@RequestMapping(value = "/secmgr/resource/createResource.do", method = RequestMethod.POST)
	public String createResource(@ModelAttribute("resourceVO") @Valid ResourceVO resource, BindingResult result) throws Exception{
		logger.debug("resource : " +resource.toString());

		//중복 ID 검사
		HMap hMap = new HMap();
		hMap.put("id", resource.getId());
		if( resourceSerivce.selectResourceVO(hMap) != null){
			result.addError(new FieldError("resourceVO", "id", resource.getId(),false,null,null,
					"이미 사용중인 ID 입니다"));
		}
		
		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
			}
			return "able/secmgr/resource/resourceForm";
		}
		
		//현재 사용자
		String regUser = AbleUserDetailsHelper.getUserName();
		resource.setRegUser(regUser);
		logger.debug("create resource: " + resource.toString());
		//DB 입력
		resourceSerivce.insertResourceVO(resource);
		
		return "redirect:/secmgr/resource/selectResource.do?id="+resource.getId();
	}
	
	@RequestMapping(value = "/secmgr/resource/showResourceForm.do", method = RequestMethod.GET)
	public String showResourceForm(HMap hMap, ModelMap model) throws Exception{
		//빈 객체 설정
		ResourceVO resource = new ResourceVO();
		//ID 생성
		String maxId = resourceSerivce.selectMaxResId(hMap);
		int num=1;
		if(maxId != null){
			num = Integer.parseInt(maxId.replace("R", "")) +1;
		}

		String id = String.format("R%05d", num);
		resource.setId(id);
		resource.setSort(1);
		model.addAttribute("resourceVO", resource);
		return "able/secmgr/resource/resourceForm";
	}
	
	@RequestMapping(value = "/secmgr/resource/deleteResource.do", method = RequestMethod.GET)
	public String deleteResource(HMap hMap, ModelMap model) throws Exception{
		resourceSerivce.deleteResourceVO(hMap);
		return "redirect:/secmgr/resource/selectResourceList.do";
	}
	 
}
