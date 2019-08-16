package able.sec.log.vo;


/**
 * @ClassName   : CommonLogVO.java
 * @Description : 공통 로그 vo
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
public class CommonLogVO {
	
	private static String system = "F0001";	//정보시스템 코드 (Example)
	private String adminId;		//관리자 ID
	private String userId;		//사용자 ID
	private String authority;	//Group, Role
	private String changeType;	//G:Grant, U:Update, R:Revoke
	private String ip;			//Client IP Address
	private String menuName;	//호출한 메뉴
	private String useType;		//I:Insert, U:Update, D:Delete, S:Select
	private String url;			//호출한 URL
	private String type;		//I:Login, O:Out
	private String successYn;	//성공여부
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSuccessYn() {
		return successYn;
	}
	public void setSuccessYn(String successYn) {
		this.successYn = successYn;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static void setSystem(String system) {
		CommonLogVO.system = system;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSystem() {
		return system;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	
		
}
