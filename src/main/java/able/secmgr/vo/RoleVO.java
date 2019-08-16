package able.secmgr.vo;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

/**
 * @ClassName   : RoleVO.java
 * @Description : 권한 VO 클래스
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
public class RoleVO {
	
	@Pattern(regexp="^ROLE_.*", message = "ROLE_ 로 시작해야합니다")
	private String id;		//id
	private String name;		//이름
	private String yn;		//사용여부
	private String desc;		//설명
	
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
	public String getYn() {
		return yn;
	}
	public void setYn(String yn) {
		this.yn = yn;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
		return "RoleVO [id=" + id + ", name=" + name + ", yn=" + yn + ", desc="
				+ desc + ", modUser=" + modUser + ", modDateTime="
				+ modDateTime + ", regUser=" + regUser + ", regDateTime="
				+ regDateTime + "]";
	}
}
