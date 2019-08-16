package able.sec.service;

/**
 * @ClassName   : PointcutService.java
 * @Description : 포인트컷 서비스
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
public interface PointcutService {
	
	public String userAccess();
	
	public String adminAccess();
	
	public String bothAccess();
	
	public String anonymousAccess();

}
