package able.sec.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import able.sec.service.MethodService;

/**
 * @ClassName   : MethodController.java
 * @Description : 메소드 컨트롤러
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
public class MethodController {
	
	@Resource(name="methodService")
	private MethodService methodService;
	
	@ResponseBody
	@RequestMapping(value = "/method/user/access.do", method = RequestMethod.GET)
	public String userAccess() throws Exception {
		return methodService.userAccess();
	}
	
	@ResponseBody
	@RequestMapping(value = "/method/admin/access.do", method = RequestMethod.GET)
	public String adminAccess() throws Exception {
		return methodService.adminAccess();
	}
	
	@ResponseBody
	@RequestMapping(value = "/method/both/access.do", method = RequestMethod.GET)
	public String bothAccess() throws Exception {
		return methodService.bothAccess();
	}
	
	@ResponseBody
	@RequestMapping(value = "/method/anonymous/access.do", method = RequestMethod.GET)
	public String anonymousAccess() throws Exception {
		return methodService.anonymousAccess();
	}
}
