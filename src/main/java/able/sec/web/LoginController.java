package able.sec.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import able.com.web.HController;

/**
 * @ClassName   : LoginController.java
 * @Description : 로그인 컨트롤러
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
public class LoginController extends HController{
	
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String login() throws Exception {
		return "able/sec/loginForm";
	}
	
	@RequestMapping(value = "/loginSuccess.do", method = RequestMethod.GET)
	public String success() throws Exception {
		logger.debug("Login sucess!!!");
		return "able/sec/loginSuccess";
	}
	
	@RequestMapping(value = "/accessDenied.do", method = RequestMethod.GET)
	public String deniedPage() throws Exception {
		return "able/sec/accessDenied";
	}
	
}



































