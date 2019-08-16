package able.sec.log.event;

import java.net.InetAddress;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import able.sec.log.service.CommonLogService;
import able.sec.log.vo.CommonLogVO;
import able.security.userdetails.util.AbleUserDetailsHelper;

/**
 * @ClassName   : LogAspect.java
 * @Description : AOP를 이용한 사용 내역 로깅 클래스
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
public class LogAspect {
	
	@Resource(name="commonLogService")
	CommonLogService commonLogService;

	public void printLog(JoinPoint joinPoint) throws Exception{

		//URL 추출을 위한 HttpServletRequest 접근
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String getIpAddr = InetAddress.getLocalHost().getHostAddress();
		Signature signature = joinPoint.getSignature();
		String name = signature.getName();
		String functionType = "";
		
		if(name.indexOf("select") > -1){
			functionType = "S";
		}
		else if(name.indexOf("insert") > -1){
			functionType = "I";
		}
		else if(name.indexOf("update") > -1){
			functionType = "U";
		}
		else if(name.indexOf("delete") > -1){
			functionType = "D";
		}
		
		CommonLogVO commonLogVO = new CommonLogVO();
		commonLogVO.setUserId(AbleUserDetailsHelper.getUserName());
		commonLogVO.setIp(getIpAddr);
		commonLogVO.setMenuName(signature.getName());
		commonLogVO.setUseType(functionType);
		commonLogVO.setUrl(request.getRequestURI());
		//DB insert
		commonLogService.insertUseLog(commonLogVO);
	}

}
