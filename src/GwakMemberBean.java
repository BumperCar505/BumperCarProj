

public class GwakMemberBean {

	// technician 테이블 
	private int techNum;
	private String techName;
	private String techTel;
	private String techLv;
	private String techComNum;
	
	// stock 테이블
	private int stckNum;
	private String stckComNum;	

	private String stckUnitNum;
	private int stckQty;
	private String stckBuyDate;
	
	// unit 테이블
	private String unitNum;
	private String unitName;
	private int unitPrice;
	private String unitVendor;
	
	
	
	// unit 테이블
	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	
	public String getStckComNum() {
		return stckComNum;
	}

	public void setStckComNum(String stckComNum) {
		this.stckComNum = stckComNum;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnitVendor() {
		return unitVendor;
	}

	public void setUnitVendor(String unitVendor) {
		this.unitVendor = unitVendor;
	}


	

	// stock 테이블
	public int getStckNum() {
		return stckNum;
	}
	
	public void setStckNum(int stckNum) {
		this.stckNum = stckNum;
	}
	
	public String getStckUnitNum() {
		return stckUnitNum;
	}
	
	public void setStckUnitNum(String stckUnitNum) {
		this.stckUnitNum = stckUnitNum;
	}
	
	public int getStckQty() {
		return stckQty;
	}
	
	public void setStckQty(int stckQty) {
		this.stckQty = stckQty;
	}
	
	public String getStckBuyDate() {
		return stckBuyDate;
	}
	
	public void setStckBuyDate(String stckBuyDate) {
		this.stckBuyDate = stckBuyDate;
	}

	
	
	// technician 테이블
	// TechNum
	public int getTechNum() {
		return techNum;
	}
	public void setTechNum(int techNum) {
		this.techNum = techNum;
	}
	
	// ComNum
	public String getTechComNum() {
		return techComNum;
	}
	public void setTechComNum(String techComNum) {
		this.techComNum = techComNum;
	}

	// TechName
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}
	
	// TechTel
	public String getTechTel() {
		return techTel;
	}
	public void setTechTel(String techTel) {
		this.techTel = techTel;
	}
	
	// TechLv
	public String getTechLv() {
		return techLv;
	}
	public void setTechLv(String techLv) {
		this.techLv = techLv;
	}
	

	
}
