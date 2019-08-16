package able.sec.log.event;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import able.sec.log.service.CommonLogService;
import able.sec.log.vo.CommonLogVO;

/**
 * @ClassName   : LogoutAuditingListener.java
 * @Description : 로그아웃 및 세션 만료를 감시하여 로깅하는 클래스
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
public class LogoutAuditingListener implements ApplicationListener<SessionDestroyedEvent>{
	
	@Resource(name="commonLogService")
	CommonLogService commonLogService;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event)
    {
		
        List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
        UserDetails ud;
        
        CommonLogVO commonLogVO = new CommonLogVO();
        
        for (SecurityContext securityContext : lstSecurityContext)
        {
            ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
            try {
    			commonLogVO.setIp(InetAddress.getLocalHost().getHostAddress());
    		} catch (UnknownHostException e) {
    			logger.debug("No ip address : ", e.getMessage());
    		}
            commonLogVO.setType("O");
			commonLogVO.setUserId(ud.getUsername());
			commonLogVO.setSuccessYn("Y");
			commonLogService.insertLogInOutLog(commonLogVO);
        }
    }
}
