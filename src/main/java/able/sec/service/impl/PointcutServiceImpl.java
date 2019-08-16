package able.sec.service.impl;

import org.springframework.stereotype.Service;

import able.sec.service.PointcutService;

/**
 * @ClassName   : PointcutServiceImpl.java
 * @Description : PointcutService 구현 클래스
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
@Service("pointcutService")
public class PointcutServiceImpl implements PointcutService {

	@Override
	public String userAccess() {
		return "USER access!";
	}

	@Override
	public String adminAccess() {
		return "ADMIN access!";
	}

	@Override
	public String bothAccess() {
		return "ADMIN & USER access!";
	}

	@Override
	public String anonymousAccess() {
		return "ANONYMOUS access!";
	}

}