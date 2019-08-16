package able.secmgr.vo;

import java.sql.Timestamp;

/**
 * @ClassName   : MenuRoleVO.java
 * @Description : 메뉴 권한 VO 클래스
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
public class MenuRoleVO {
	
	private String menuId;		//menu id
	private String roleId;		//role id
	private String roleName;	//role name
	private String roleYn;		//role yn

	private String regUser;		//등록한 유저
	private Timestamp	regDateTime;	//등록 시각
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleYn() {
		return roleYn;
	}
	public void setRoleYn(String roleYn) {
		this.roleYn = roleYn;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public Timestamp getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(Timestamp regDateTime) {
		this.regDateTime = regDateTime;
	}
	@Override
	public String toString() {
		return "MenuRoleVO [menuId=" + menuId + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", roleYn=" + roleYn
				+ ", regUser=" + regUser + ", regDateTime=" + regDateTime + "]";
	}
	
}
