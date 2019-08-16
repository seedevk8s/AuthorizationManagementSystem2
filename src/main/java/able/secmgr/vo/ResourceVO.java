package able.secmgr.vo;

import java.sql.Timestamp;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName   : ResourceVO.java
 * @Description : 리소스 VO 클래스
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
public class ResourceVO {
	
	@NotEmpty
	private String id;		//id
	private String name;		//이름
	private String yn;		//사용여부
	private String desc;		//설명
	
	@Min(value=1, message="{value} 이상이어야 합니다")
	private int sort;		//정렬순
	@NotEmpty
	private String pattern;	//패턴
	private String type;		//종류 (url,method,pointcut)
	
	private String modUser;		//수정한 유저
	private Timestamp modDateTime;	//수정 시각
	private String regUser;		//등록한 유저
	private Timestamp	regDateTime;	//등록 시각
	
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		return "ResourceVO [id=" + id + ", name=" + name + ", yn=" + yn
				+ ", desc=" + desc + ", sort=" + sort + ", pattern=" + pattern
				+ ", type=" + type + ", modUser=" + modUser + ", modDateTime="
				+ modDateTime + ", regUser=" + regUser + ", regDateTime="
				+ regDateTime + "]";
	}
	
}
