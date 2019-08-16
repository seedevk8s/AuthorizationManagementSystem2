package able.secmgr.web;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import able.com.vo.HMap;
import able.com.web.HController;
import able.com.web.view.PagingInfo;
import able.secmgr.service.UserService;
import able.secmgr.vo.UserGroupVO;
import able.secmgr.vo.UserVO;
import able.security.userdetails.util.AbleUserDetailsHelper;

/**
 * @ClassName   : UserController.java
 * @Description : 유저 서비스 컨트롤러
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
public class UserController extends HController{
	
	@Resource(name="userService")
	UserService userSerivce;
	
	@RequestMapping(value = "/secmgr/user/selectUserList.do", method = RequestMethod.GET)
	public String selectUserList(HMap hMap, ModelMap model) throws Exception {
		
		List<UserVO> allUserVOList = userSerivce.selectUserVOList(hMap);
		int totalSize = allUserVOList.size();
		
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
		List<UserVO> userVOList = userSerivce.selectUserVOList(hMap);
		model.put("userVOList", userVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/user/userList";
	}
	
	@RequestMapping(value = "/secmgr/user/selectUser.do", method = RequestMethod.GET)
	public String selectUser(HMap hMap, ModelMap model) throws Exception{
		
		UserVO userVO = userSerivce.selectUserVO(hMap);
		model.put("userVO", userVO);
		
		hMap.put("userId", hMap.get("id"));
		
		List<UserGroupVO> userGroupVOList = userSerivce.selectUserGroupVOList(hMap);
		model.put("userGroupVOList", userGroupVOList);
		
		return "able/secmgr/user/userInfo";
	}
	
	@RequestMapping(value = "/secmgr/user/popupUserGroup.do", method = RequestMethod.GET)
	public String popupGroupUser(HMap hMap, ModelMap model) throws Exception {
		//추가 가능한 User
		List<UserGroupVO> allAddableUserGroupVOList = userSerivce.selectAddableUserGroupVOList(hMap);
		int totalSize = allAddableUserGroupVOList.size();

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
		List<UserGroupVO> addableUserGroupVOList = userSerivce.selectAddableUserGroupVOList(hMap);
		model.put("addableUserGroupVOList", addableUserGroupVOList);
		model.put("hMap", hMap);
		model.put("pagingInfo", pagingInfo);
		
		return "able/secmgr/user/popupUserGroup";
	}
	
	@RequestMapping(value = "/secmgr/user/deleteGroups.do", method = RequestMethod.POST)
	public String deleteGroups(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//userId, groupIds
		String userId = (String) hMap.get("userId");

		//groupId 있는 경우만 작동
		if(hMap.containsKey("groupId")){
			String[] groupIdArr;
			//한개 선택시 String, 복수 선택시 String[]
			if(hMap.get("groupId") instanceof String){
				groupIdArr = new String[]{(String)hMap.get("groupId")};
			} else {
				groupIdArr = (String[]) hMap.get("groupId");
			}
			List<String> groupIds = Arrays.asList(groupIdArr);
			
			//현재 사용자
			String currentUser = AbleUserDetailsHelper.getUserName();
			//삭제 서비스
			userSerivce.delGroups(groupIds, userId, currentUser);
		}
		
		return "redirect:/secmgr/user/selectUser.do?id="+userId;
	}
	
	@RequestMapping(value = "/secmgr/user/addGroups.do", method = RequestMethod.POST)
	public String addGroups(HMap hMap, ModelMap model) throws Exception {
		
		logger.debug(hMap.toString());
		//userId, groupIds
		String userId = (String) hMap.get("userId");

		//groupId 있는 경우만 작동
		if(hMap.containsKey("groupId")){
			String[] groupIdArr;
			//한개 선택시 String, 복수 선택시 String[]
			if(hMap.get("groupId") instanceof String){
				groupIdArr = new String[]{(String)hMap.get("groupId")};
			} else {
				groupIdArr = (String[]) hMap.get("groupId");
			}
			List<String> groupIds = Arrays.asList(groupIdArr);
			
			//현재 사용자
			String currentUser = AbleUserDetailsHelper.getUserName();
			//삭제 서비스
			userSerivce.addGroups(groupIds, userId, currentUser);
		}
		
		return "able/secmgr/closePopup";
	}
	
	@RequestMapping(value = "/secmgr/user/updateUser.do", method = RequestMethod.POST)
	public String updateUser(UserVO user, ModelMap model) throws Exception{
		//현재 사용자
		String modUser = AbleUserDetailsHelper.getUserName();
		user.setModUser(modUser);
		logger.debug("update user : " + user.toString());
		//업데이트
		userSerivce.updateUserVO(user);
		
		return "redirect:/secmgr/user/selectUser.do?id="+user.getId();
	}
	
	@RequestMapping(value = "/secmgr/user/createUser.do", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("userVO") @Valid UserVO user, BindingResult result) throws Exception{
		logger.debug("user : " +user.toString());

		//중복 ID 검사
		HMap hMap = new HMap();
		hMap.put("id", user.getId());
		if( userSerivce.selectUserVO(hMap) != null){
			result.addError(new FieldError("userVO", "id", user.getId(),false,null,null,
					"이미 사용중인 ID 입니다"));
		}

		//패스워드 일치 검사
		String pwd = user.getPwd();
		String pwdVerify = user.getPwdVerify();
		if(StringUtils.hasText(pwd) && StringUtils.hasText(pwdVerify)){
			if(!pwd.equals(pwdVerify)){
				result.addError(new FieldError("userVO", "pwd", user.getPwd(),false,null,null,
						"패스워드가 일치하지 않습니다"));
				result.addError(new FieldError("userVO", "pwdVerify", user.getPwdVerify(),false,null,null,
						"패스워드가 일치하지 않습니다"));
			}
		}

		//hibernate validation
		if(result.hasErrors()){
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError e : list) {
				logger.debug("ObjectError : " + e);
			}
			return "able/secmgr/user/userForm";
		}
		
		//현재 사용자
		String regUser = AbleUserDetailsHelper.getUserName();
		user.setRegUser(regUser);
		logger.debug("create user : " + user.toString());
		//DB입력
		userSerivce.insertUserVO(user);
		
		return "redirect:/secmgr/user/selectUserList.do";
	}
	
	@RequestMapping(value = "/secmgr/user/showUserForm.do", method = RequestMethod.GET)
	public String showUserForm(HMap hMap, ModelMap model) throws Exception{
		//빈 객체 설정
		UserVO user = new UserVO();
		model.addAttribute("userVO", user);
		return "able/secmgr/user/userForm";
	}
	
	@RequestMapping(value = "/secmgr/user/deleteUser.do", method = RequestMethod.GET)
	public String deleteUser(HMap hMap, ModelMap model) throws Exception{
		userSerivce.deleteUserVO(hMap);
		return "redirect:/secmgr/user/selectUserList.do";
	}
	 
}
