package all;

public class ServiceBeans {
	private int srvNum;
	private int unitPrice;
	private String srvName;
	private String techName;
	private String unitName;
	
	public int getSrvNum() {
		return srvNum;
	}
	
	public int getUnitPrice() {
		return unitPrice;
	}
	
	public String getSrvName() {
		return srvName;
	}
	
	public String getTechName() {
		return techName;
	}
	
	public String getUnitName() {
		return unitName;
	}
	
	public void setSrvNum(int srvNum) {
		this.srvNum = srvNum;
	}
	
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}
	
	public void setTechName(String techName) {
		this.techName = techName;
	}
	
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
