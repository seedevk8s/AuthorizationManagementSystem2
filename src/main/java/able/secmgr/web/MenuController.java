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
import able.secmgr.service.MenuService;
import able.secmgr.vo.MenuRoleVO;
import able.secmgr.vo.MenuVO;
import able.security.userdetails.util.AbleUserDetailsHelper;

/**
 * @ClassName   : MenuController.java
 * @Description : 메뉴 서비스 컨트롤러
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
public class MenuController extends HController{
	
	@Resource(name="menuService")
	MenuService menuSerivce;
	
	@RequestMapping(value = "/secmgr/menu/selectMenuList.do", method = RequestMethod.GET)
	public String selectMenuList(HMap hMap, ModelMap model) throws Exception {
		
		List<MenuVO> allMenuVOList = menuSerivce.selectMenuVOList(hMap);
		int totalSize = allMenuVOList.size();

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
		List<MenuVO> menuVOList = menuSerivce.selectMenuVOList(hMap);
		model.put("menuVOList", menuVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/menu/menuList";
	}
	
	@RequestMapping(value = "/secmgr/menu/selectMenu.do", method = RequestMethod.GET)
	public String selectMenu(HMap hMap, ModelMap model) throws Exception{
		
		MenuVO menuVO = menuSerivce.selectMenuVO(hMap);
		model.put("menuVO", menuVO);
		
		hMap.put("menuId", hMap.get("id"));
		
		List<MenuRoleVO> menuRoleVOList = menuSerivce.selectMenuRoleVOList(hMap);
		model.put("menuRoleVOList", menuRoleVOList);
		
		return "able/secmgr/menu/menuInfo";
	}
	
	@RequestMapping(value = "/secmgr/menu/popupMenuRole.do", method = RequestMethod.GET)
	public String popupMenuRole(HMap hMap, ModelMap model) throws Exception {
		//추가 가능한 Role
		List<MenuRoleVO> allAddableMenuRoleVOList = menuSerivce.selectAddableMenuRoleVOList(hMap);
		int totalSize = allAddableMenuRoleVOList.size();
		
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
		List<MenuRoleVO> addableMenuRoleVOList = menuSerivce.selectAddableMenuRoleVOList(hMap);
		model.put("addableMenuRoleVOList", addableMenuRoleVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/menu/popupMenuRole";
	}
	
	@RequestMapping(value = "/secmgr/menu/deleteRoles.do", method = RequestMethod.POST)
	public String deleteRoles(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//menuId, roleIds
		String menuId = (String) hMap.get("menuId");

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
			menuSerivce.delRoles(roleIds, menuId, currentUser);
		}
		
		return "redirect:/secmgr/menu/selectMenu.do?id="+menuId;
	}
	
	@RequestMapping(value = "/secmgr/menu/addRoles.do", method = RequestMethod.POST)
	public String addRoles(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//menuId, roleIds
		String menuId = (String) hMap.get("menuId");

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
			menuSerivce.addRoles(roleIds, menuId, currentUser);
		}
		
		return "able/secmgr/closePopup";
	}
	
	@RequestMapping(value = "/secmgr/menu/updateMenu.do", method = RequestMethod.POST)
	public String updateMenu(@ModelAttribute("menuVO") @Valid MenuVO menu, BindingResult result, ModelMap model) throws Exception{
		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
			}
			
			HMap hMap = new HMap();
			hMap.put("id", menu.getId());
			//<input>에 없는 값 읽기
			MenuVO m = menuSerivce.selectMenuVO(hMap);
			menu.setRegUser(m.getRegUser());
			menu.setRegDateTime(m.getRegDateTime());
			menu.setModUser(m.getModUser());
			menu.setModDateTime(m.getModDateTime());
			
			hMap.put("menuId", menu.getId());
			
			List<MenuRoleVO> menuRoleVOList = menuSerivce.selectMenuRoleVOList(hMap);
			model.put("menuRoleVOList", menuRoleVOList);
			
			return "able/secmgr/menu/menuInfo";
		} 

		
		//현재 사용자
		String modUser = AbleUserDetailsHelper.getUserName();
		menu.setModUser(modUser);
		menu.setModDateTime(null);
		logger.debug("update menu: " + menu.toString());
		//업데이트
		menuSerivce.updateMenuVO(menu);
		
		return "redirect:/secmgr/menu/selectMenu.do?id="+menu.getId();
	}
	
	@RequestMapping(value = "/secmgr/menu/createMenu.do", method = RequestMethod.POST)
	public String createMenu(@ModelAttribute("menuVO") @Valid MenuVO menu, BindingResult result) throws Exception{
		logger.debug("menu : " +menu.toString());

		//중복 ID 검사
		HMap hMap = new HMap();
		hMap.put("id", menu.getId());
		if( menuSerivce.selectMenuVO(hMap) != null){
			result.addError(new FieldError("menuVO", "id", menu.getId(),false,null,null,
					"이미 사용중인 ID 입니다"));
		}
		
		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
			}
			return "able/secmgr/menu/menuForm";
		}
		
		//현재 사용자
		String regUser = AbleUserDetailsHelper.getUserName();
		menu.setRegUser(regUser);
		logger.debug("create menu: " + menu.toString());
		//DB 입력
		menuSerivce.insertMenuVO(menu);
		
		return "redirect:/secmgr/menu/selectMenu.do?id="+menu.getId();
	}
	
	@RequestMapping(value = "/secmgr/menu/showMenuForm.do", method = RequestMethod.GET)
	public String showMenuForm(HMap hMap, ModelMap model) throws Exception{
		//빈 객체 설정
		MenuVO menu = new MenuVO();
		//ID 생성
		String maxId = menuSerivce.selectMaxMenuId(hMap);
		int num=1;
		if(maxId != null){
			num = Integer.parseInt(maxId.replace("M", "")) +1;
		}

		String id = String.format("M%05d", num);
		menu.setId(id);
		model.addAttribute("menuVO", menu);
		return "able/secmgr/menu/menuForm";
	}
	
	@RequestMapping(value = "/secmgr/menu/deleteMenu.do", method = RequestMethod.GET)
	public String deleteMenu(HMap hMap, ModelMap model) throws Exception{
		menuSerivce.deleteMenuVO(hMap);
		return "redirect:/secmgr/menu/selectMenuList.do";
	}
	 
}
