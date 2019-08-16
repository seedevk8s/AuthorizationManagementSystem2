package able.secmgr.vo;

import java.sql.Timestamp;

/**
 * @ClassName   : GroupUserVO.java
 * @Description : 그룹 유저 VO 클래스
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
public class GroupUserVO {
	
	private String groupId;		//group id
	private String userId;		//user id
	private String userName;	//user name
	private String userYn;		//user yn

	private String regUser;		//등록한 유저
	private Timestamp	regDateTime;	//등록 시각
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserYn() {
		return userYn;
	}
	public void setUserYn(String userYn) {
		this.userYn = userYn;
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
		return "GroupUserVO [groupId=" + groupId + ", userId=" + userId
				+ ", userName=" + userName + ", userYn=" + userYn
				+ ", regUser=" + regUser + ", regDateTime=" + regDateTime + "]";
	}
	
}
