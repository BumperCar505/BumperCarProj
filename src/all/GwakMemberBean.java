package all;


public class GwakMemberBean {

	// Com
	private String comName;
	private String comNum;
	private String comEmail;
	private String comZip;
	private String comAddr;
	private String comTel;
	private String comDate;
	private String comPw;
	private String comPwCheck;
	
	// technician 테이블 .
	private int techNum;
	private String techName;
	private String techTel;
	private String techLv;
	private String techComNum;
	
	// stock 테이블
	private int stckNum;
	private String stckComNum;	

	private String stckUnitNum;
	private int stckQty1;
	private int stckQty2;
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
	
	public int getStckQty1() {
		return stckQty1;
	}
	
	public void setStckQty1(int stckQty1) {
		this.stckQty1 = stckQty1;
	}
	
	public int getStckQty2() {
		return stckQty2;
	}

	public void setStckQty2(int stckQty2) {
		this.stckQty2 = stckQty2;
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

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
//
	public String getComNum() {
		return comNum;
	}

	public void setComNum(String comNum) {
		this.comNum = comNum;
	}

	public String getComEmail() {
		return comEmail;
	}

	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	public String getComZip() {
		return comZip;
	}

	public void setComZip(String comZip) {
		this.comZip = comZip;
	}

	public String getComAddr() {
		return comAddr;
	}

	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}

	public String getComTel() {
		return comTel;
	}

	public void setComTel(String comTel) {
		this.comTel = comTel;
	}

	public String getComDate() {
		return comDate;
	}

	public void setComDate(String comDate) {
		this.comDate = comDate;
	}

	public String getComPw() {
		return comPw;
	}

	public void setComPw(String comPw) {
		this.comPw = comPw;
	}

	public String getComPwCheck() {
		return comPwCheck;
	}

	public void setComPwCheck(String comPwCheck) {
		this.comPwCheck = comPwCheck;
	}



	
}
