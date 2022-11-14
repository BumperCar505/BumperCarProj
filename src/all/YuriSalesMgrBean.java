package all;

public class YuriSalesMgrBean {
	
	private String cumNum;
	private String mainStatus;
	private String endDate;
	private int techNum;
	
	
	private int srvIncome;
	private int proIncome;
	private int proOut;
	
	private String buyDate;
	
	
	// 공임비 받는 부분
	public int getSrvIncome() {
		return srvIncome;
	}
	public void setSrvIncome(int srvIncome) {
		this.srvIncome = srvIncome;
	}
	
//	재료비 받는 부분
	public int getProIncome() {
		return proIncome;
	}
	public void setProIncome(int proIncome) {
		this.proIncome = proIncome;
	}
	
	
//	비용처리 되는 재료비
	public int getProOut() {
		return proOut;
	}
	public void setProOut(int proOut) {
		this.proOut = proOut;
	}
	
	
	
	
	public String getCumNum() {
		return cumNum;
	}
	public void setCumNum(String cumNum) {
		this.cumNum = cumNum;
	}
	
	public String getMainStatus() {
		return mainStatus;
	}
	public void setMainStatus(String mainStatus) {
		this.mainStatus = mainStatus;
	}
	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getTechNum() {
		return techNum;
	}
	public void setTechNum(int techNum) {
		this.techNum = techNum;
	}
	
	// 비용
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
}
