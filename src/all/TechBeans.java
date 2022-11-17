package all;

public class TechBeans {
	private String techName;
	private String techTel;
	private String techLv;
	
	public TechBeans() {
//		this("디폴트정비사", "010-1111-2222", "사원");
	}
	
	public TechBeans(String techName, String techTel, String techLv) {
		this.techName = techName;
		this.techTel = techTel;
		this.techLv = techLv;
	}
	
	public String getTechName() {
		return techName;
	}
	
	public String getTechTel() {
		return techTel;
	}
	
	public String getTechLv() {
		return techLv;
	}
	
	public void setTechName(String techName) {
		this.techName = techName;
	}
	
	public void setTechTel(String techTel) {
		this.techTel = techTel;
	}
	
	public void setTechLv(String techLv) {
		this.techLv = techLv;
	}
}
