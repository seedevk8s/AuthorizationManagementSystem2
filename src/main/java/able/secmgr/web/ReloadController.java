package able.secmgr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import able.com.web.HController;
import able.security.intercept.AbleReloadableFilterInvocationSecurityMetadataSource;

/**
 * <pre>
 * Statements
 * </pre>
 *
 * @ClassName   : ReloadController.java
 * @Description : 리소스 리로드 컨트롤러 
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
public class ReloadController extends HController{
	
	@Autowired(required=false)
	AbleReloadableFilterInvocationSecurityMetadataSource ableReloadableFilterInvocationSecurityMetadataSource;
	
	
	@RequestMapping(value = "/secmgr/resource/reload.do", method = RequestMethod.GET)
	public String reloadSecuredUrl() throws Exception {
		if(ableReloadableFilterInvocationSecurityMetadataSource != null){
			ableReloadableFilterInvocationSecurityMetadataSource.reload();
		}
		return "redirect:/secmgr/resource/selectResourceList.do";
	}
	
}
