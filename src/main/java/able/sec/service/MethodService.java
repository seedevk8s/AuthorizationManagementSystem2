package able.sec.service;

/**
 * @ClassName   : MethodService.java
 * @Description : 메소드 서비스 클래스
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
public interface MethodService {
	
	public String userAccess();
	
	public String adminAccess();
	
	public String bothAccess();
	
	public String anonymousAccess();

}
