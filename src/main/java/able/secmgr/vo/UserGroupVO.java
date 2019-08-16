package able.secmgr.vo;

import java.sql.Timestamp;

/**
 * @ClassName   : UserGroupVO.java
 * @Description : 유저 그룹 VO 클래스
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
public class UserGroupVO {
	
	private String userId;		//user id
	private String groupId;		//group id
	private String groupName;	//group name
	private String groupYn;		//group yn

	private String regUser;		//등록한 유저
	private Timestamp	regDateTime;	//등록 시각
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupYn() {
		return groupYn;
	}
	public void setGroupYn(String groupYn) {
		this.groupYn = groupYn;
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
		return "UserGroupVO [userId=" + userId + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", groupYn=" + groupYn
				+ ", regUser=" + regUser + ", regDateTime=" + regDateTime + "]";
	}
	
}
