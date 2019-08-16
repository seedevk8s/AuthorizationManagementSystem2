package able.secmgr.vo;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName   : UserVO.java
 * @Description : 유저 VO 클래스
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
public class UserVO {
	
	@NotEmpty
	private String id;		//id
	private String name;		//이름
	@NotEmpty
	@Length(min=8, message="최소 {2}자 이상이어야 합니다" )
	private String pwd;		//패스워드
	@NotEmpty
	@Length(min=8, message="최소 {2}자 이상이어야 합니다" )
	private String pwdVerify; //패스워드 확인용
	private String yn;		//사용여부
	
	private String modUser;		//수정한 유저
	private Timestamp modDateTime;	//수정 시각
	private String regUser;		//등록한 유저
	private Timestamp regDateTime;	//등록 시각
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwdVerify() {
		return pwdVerify;
	}
	public void setPwdVerify(String pwdVerify) {
		this.pwdVerify = pwdVerify;
	}
	public String getYn() {
		return yn;
	}
	public void setYn(String yn) {
		this.yn = yn;
	}
	public String getModUser() {
		return modUser;
	}
	public void setModUser(String modUser) {
		this.modUser = modUser;
	}
	public Timestamp getModDateTime() {
		return modDateTime;
	}
	public void setModDateTime(Timestamp modDateTime) {
		this.modDateTime = modDateTime;
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
		return "UserVO [id=" + id + ", name=" + name + ", yn="
				+ yn + ", modUser=" + modUser + ", modDateTime=" + modDateTime
				+ ", regUser=" + regUser + ", regDateTime=" + regDateTime + "]";
	}

}
