package able.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName   : UrlBasedController.java
 * @Description : UrlBased 컨트롤러
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
public class UrlBasedController {
	
	@ResponseBody
	@RequestMapping(value = "/user/access.do", method = RequestMethod.GET)
	public String userAccess() throws Exception {
		return "USER access!";
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/access.do", method = RequestMethod.GET)
	public String adminAccess() throws Exception {
		return "ADMIN access!";
	}
	
	@ResponseBody
	@RequestMapping(value = "/both/access.do", method = RequestMethod.GET)
	public String bothAccess() throws Exception {
		return "ADMIN & USER access!";
	}
	
	@ResponseBody
	@RequestMapping(value = "/anonymous/access.do", method = RequestMethod.GET)
	public String anonymousAccess() throws Exception {
		return "ANONYMOUS access!";
	}
}
