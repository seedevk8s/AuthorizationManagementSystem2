package able.sec.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import able.sec.service.PointcutService;

/**
 * @ClassName   : PointcutController.java
 * @Description : 포인트컷 컨트롤러
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
public class PointcutController {
	
	@Resource(name="pointcutService")
	private PointcutService pointcutService;
	
	@ResponseBody
	@RequestMapping(value = "/pointcut/user/access.do", method = RequestMethod.GET)
	public String userAccess() throws Exception {
		return pointcutService.userAccess();
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointcut/admin/access.do", method = RequestMethod.GET)
	public String adminAccess() throws Exception {
		return pointcutService.adminAccess();
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointcut/both/access.do", method = RequestMethod.GET)
	public String bothAccess() throws Exception {
		return pointcutService.bothAccess();
	}
	
	@ResponseBody
	@RequestMapping(value = "/pointcut/anonymous/access.do", method = RequestMethod.GET)
	public String anonymousAccess() throws Exception {
		return pointcutService.anonymousAccess();
	}
}
