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
import able.sec.log.service.CommonLogService;
import able.secmgr.service.GroupService;
import able.secmgr.vo.GroupRoleVO;
import able.secmgr.vo.GroupUserVO;
import able.secmgr.vo.GroupVO;
import able.security.userdetails.util.AbleUserDetailsHelper;

/**
 * @ClassName   : GroupController.java
 * @Description : 그룹 서비스 컨트롤러
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
public class GroupController extends HController{
	
	@Resource(name="groupService")
	GroupService groupSerivce;
	
	@Resource(name="commonLogService")
	CommonLogService commonLogService;
	
	@RequestMapping(value = "/secmgr/group/selectGroupList.do", method = RequestMethod.GET)
	public String selectGroupList(HMap hMap, ModelMap model) throws Exception {
		
		List<GroupVO> allGroupVOList = groupSerivce.selectGroupVOList(hMap);
		int totalSize = allGroupVOList.size();

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
		//hsqldb
		//hMap.put("pageUnit", pageUnit);
		//hMap.put("offset", offset);
		
		//oracle
		hMap.put("limit", pageUnit*pageNo);
		hMap.put("offset", offset*(pageNo-1));
		
		//해당 내용 얻기
		List<GroupVO> groupVOList = groupSerivce.selectGroupVOList(hMap);
		model.put("groupVOList", groupVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/group/groupList";
	}
	
	@RequestMapping(value = "/secmgr/group/selectGroup.do", method = RequestMethod.GET)
	public String selectGroup(HMap hMap, ModelMap model) throws Exception{
		
		GroupVO groupVO = groupSerivce.selectGroupVO(hMap);
		model.put("groupVO", groupVO);
		
		hMap.put("groupId", hMap.get("id"));
		
		List<GroupRoleVO> groupRoleVOList = groupSerivce.selectGroupRoleVOList(hMap);
		model.put("groupRoleVOList", groupRoleVOList);
		
		List<GroupUserVO> groupUserVOList = groupSerivce.selectGroupUserVOList(hMap);
		model.put("groupUserVOList", groupUserVOList);
		
		return "able/secmgr/group/groupInfo";
	}
	
	@RequestMapping(value = "/secmgr/group/popupGroupRole.do", method = RequestMethod.GET)
	public String popupGroupRole(HMap hMap, ModelMap model) throws Exception {
		//추가 가능한 Role
		List<GroupRoleVO> allAddableGroupRoleVOList = groupSerivce.selectAddableGroupRoleVOList(hMap);
		int totalSize = allAddableGroupRoleVOList.size();
		
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
		hMap.put("pageUnit", pageUnit);
		hMap.put("offset", offset);
				
		//해당 내용 얻기
		List<GroupRoleVO> addableGroupRoleVOList = groupSerivce.selectAddableGroupRoleVOList(hMap);
		model.put("addableGroupRoleVOList", addableGroupRoleVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/group/popupGroupRole";
	}
	
	@RequestMapping(value = "/secmgr/group/deleteRoles.do", method = RequestMethod.POST)
	public String deleteRoles(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//groupId, roleIds
		String groupId = (String) hMap.get("groupId");

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
			groupSerivce.delRoles(roleIds, groupId, currentUser);
			//Logging
			commonLogService.insertAuthRoleChangeLog(roleIds, groupId, currentUser, "del");
		}
		
		return "redirect:/secmgr/group/selectGroup.do?id="+groupId;
	}
	
	@RequestMapping(value = "/secmgr/group/addRoles.do", method = RequestMethod.POST)
	public String addRoles(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//groupId, roleIds
		String groupId = (String) hMap.get("groupId");

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
			groupSerivce.addRoles(roleIds, groupId, currentUser);
			//Logging
			commonLogService.insertAuthRoleChangeLog(roleIds, groupId, currentUser, "add");
		}
		
		return "able/secmgr/closePopup";
	}
	
	@RequestMapping(value = "/secmgr/group/popupGroupUser.do", method = RequestMethod.GET)
	public String popupGroupUser(HMap hMap, ModelMap model) throws Exception {
		//추가 가능한 User
		List<GroupUserVO> allAddableGroupUserVOList = groupSerivce.selectAddableGroupUserVOList(hMap);
		int totalSize = allAddableGroupUserVOList.size();
		
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
		hMap.put("pageUnit", pageUnit);
		hMap.put("offset", offset);
		
		//해당 내용 얻기
		List<GroupUserVO> addableGroupUserVOList = groupSerivce.selectAddableGroupUserVOList(hMap);
		model.put("addableGroupUserVOList", addableGroupUserVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/group/popupGroupUser";
	}
	
	@RequestMapping(value = "/secmgr/group/deleteUsers.do", method = RequestMethod.POST)
	public String deleteUsers(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//groupId, userIds
		String groupId = (String) hMap.get("groupId");

		//userId 있는 경우만 작동
		if(hMap.containsKey("userId")){
			String[] userIdArr;
			//한개 선택시 String, 복수 선택시 String[]
			if(hMap.get("userId") instanceof String){
				userIdArr = new String[]{(String)hMap.get("userId")};
			} else {
				userIdArr = (String[]) hMap.get("userId");
			}
			List<String> userIds = Arrays.asList(userIdArr);
			
			//현재 사용자
			String currentUser = AbleUserDetailsHelper.getUserName();
			//삭제 서비스
			groupSerivce.delUsers(userIds, groupId, currentUser);
			//Logging
			commonLogService.insertAuthUserChangeLog(userIds, groupId, currentUser, "del");
		}
		
		return "redirect:/secmgr/group/selectGroup.do?id="+groupId;
	}
	
	@RequestMapping(value = "/secmgr/group/addUsers.do", method = RequestMethod.POST)
	public String addUsers(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//groupId, userIds
		String groupId = (String) hMap.get("groupId");

		//userId 있는 경우만 작동
		if(hMap.containsKey("userId")){
			String[] userIdArr;
			//한개 선택시 String, 복수 선택시 String[]
			if(hMap.get("userId") instanceof String){
				userIdArr = new String[]{(String)hMap.get("userId")};
			} else {
				userIdArr = (String[]) hMap.get("userId");
			}
			List<String> userIds = Arrays.asList(userIdArr);
			
			//현재 사용자
			String currentUser = AbleUserDetailsHelper.getUserName();
			//삭제 서비스
			groupSerivce.addUsers(userIds, groupId, currentUser);
			//Logging
			commonLogService.insertAuthUserChangeLog(userIds, groupId, currentUser, "add");
		}
		
		return "able/secmgr/closePopup";
	}
	
	@RequestMapping(value = "/secmgr/group/updateGroup.do", method = RequestMethod.POST)
	public String updateGroup(GroupVO group, ModelMap model) throws Exception{
		//현재 사용자
		String modUser = AbleUserDetailsHelper.getUserName();
		group.setModUser(modUser);
		logger.debug("update group: " + group.toString());
		//업데이트
		groupSerivce.updateGroupVO(group);
		
		return "redirect:/secmgr/group/selectGroup.do?id="+group.getId();
	}
	
	@RequestMapping(value = "/secmgr/group/createGroup.do", method = RequestMethod.POST)
	public String createGroup(@ModelAttribute("groupVO") @Valid GroupVO group, BindingResult result) throws Exception{
		logger.debug("group : " +group.toString());

		//중복 ID 검사
		HMap hMap = new HMap();
		hMap.put("id", group.getId());
		if( groupSerivce.selectGroupVO(hMap) != null){
			result.addError(new FieldError("groupVO", "id", group.getId(),false,null,null,
					"이미 사용중인 ID 입니다"));
		}
		
		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
			}
			return "able/secmgr/group/groupForm";
		}
		
		//현재 사용자
		String regUser = AbleUserDetailsHelper.getUserName();
		group.setRegUser(regUser);
		logger.debug("create group: " + group.toString());
		//DB 입력
		groupSerivce.insertGroupVO(group);
		
		return "redirect:/secmgr/group/selectGroup.do?id="+group.getId();
	}
	
	@RequestMapping(value = "/secmgr/group/showGroupForm.do", method = RequestMethod.GET)
	public String showGroupForm(HMap hMap, ModelMap model) throws Exception{
		//빈 객체 설정
		GroupVO group = new GroupVO();
		//ID 생성
		String maxId = groupSerivce.selectMaxGroupId(hMap);
		int num=1;
		if(maxId != null){
			num = Integer.parseInt(maxId.replace("G", "")) +1;
		}

		String id = String.format("G%05d", num);
		group.setId(id);
		model.addAttribute("groupVO", group);
		return "able/secmgr/group/groupForm";
	}
	
	@RequestMapping(value = "/secmgr/group/deleteGroup.do", method = RequestMethod.GET)
	public String deleteGroup(HMap hMap, ModelMap model) throws Exception{
		groupSerivce.deleteGroupVO(hMap);
		return "redirect:/secmgr/group/selectGroupList.do";
	}
	 
}
