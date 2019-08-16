package able.sec.log.event;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import able.sec.log.service.CommonLogService;
import able.sec.log.vo.CommonLogVO;

/**
 * @ClassName   : LoginAuditingListenser.java
 * @Description : 로그인 성공 실패 여부를 감시하여 로깅하는 클래스
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
@Component
public class LoginAuditingListenser implements ApplicationListener<ApplicationEvent> {

	@Resource(name="commonLogService")
	CommonLogService commonLogService;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		CommonLogVO commonLogVO = new CommonLogVO();
		try {
			commonLogVO.setIp(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			logger.debug("No ip address : ", e.getMessage());
		}

		if (event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
			Authentication authentication = authEvent.getAuthentication();
			commonLogVO.setType("I");
			commonLogVO.setUserId(authentication.getName());
			commonLogVO.setSuccessYn("Y");
			commonLogService.insertLogInOutLog(commonLogVO);
		}

		if (event instanceof AbstractAuthenticationFailureEvent) {
			AbstractAuthenticationFailureEvent authEvent = (AbstractAuthenticationFailureEvent) event;
			Authentication authentication = authEvent.getAuthentication();
			commonLogVO.setType("I");
			commonLogVO.setUserId(authentication.getName());
			commonLogVO.setSuccessYn("N");
			commonLogService.insertLogInOutLog(commonLogVO);
		}
		
	}

}
