package able.sec.service.impl;

import org.springframework.stereotype.Service;

import able.sec.service.MethodService;

/**
 * @ClassName   : MethodServiceImpl.java
 * @Description : MethodService 구현 클래스
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
@Service("methodService")
public class MethodServiceImpl implements MethodService {

	/*
	 * @see able.sec.service.MethodService#userAccess()
	 */
	@Override
	public String userAccess() {
		return "USER access!";
	}

	/*
	 * @see able.sec.service.MethodService#adminAccess()
	 */
	@Override
	public String adminAccess() {
		return "ADMIN access!";
	}

	/*
	 * @see able.sec.service.MethodService#bothAccess()
	 */
	@Override
	public String bothAccess() {
		return "ADMIN & USER access!";
	}

	/*
	 * @see able.sec.service.MethodService#anonymousAccess()
	 */
	@Override
	public String anonymousAccess() {
		return "ANONYMOUS access!";
	}

}