package all;
//set은 값을 입력하는 것, get은 저장된 값을 불러오는 것.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class YuriSalesMgr_mgr {
	
	private DBConnectionMgr pool;
	
// 공임비
	public YuriSalesMgr_mgr() {
		//DBConnection 객체 10개 미리 생성
		pool = DBConnectionMgr.getInstance();
	}

	
	public YuriSalesMgrBean monthService(String cumNum, String endDate, String mainStatus, int techNum ){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		YuriSalesMgrBean bean = new YuriSalesMgrBean();
		try {
			con = pool.getConnection();
			sql  = "SELECT un.unitPrice "
					+ "FROM maintenance main "
					+ "JOIN detail dtl "
					+ "ON dtl.dtlSrvNum = main.mainSrvNum "
					+ "JOIN unit un "
					+ "ON un.unitNum = dtl.dtlUnitNum "
					+ "JOIN service srv "
					+ "ON srv.srvNum = dtl.dtlSrvNum "
					+ "JOIN technician tech "
					+ "ON tech.techNum = srv.srvTechNum "
					+ "WHERE main.mainComNum=1112233333 AND main.mainEndDay = ? and main.mainStatus=? "
					+ "AND srv.srvTechNum = ? AND un.unitNum LIKE 's%' AND dtl.dtlDeleted_yn='N' " ;

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cumNum);
			pstmt.setString(2, endDate);
			pstmt.setString(3, mainStatus);
			pstmt.setInt(4, techNum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setCumNum(rs.getString("mainComNum"));
				bean.setEndDate(rs.getString("mainEndDay"));
				bean.setMainStatus(rs.getString("mainStatus"));
				bean.setTechNum(rs.getInt("srvTechNum"));
				bean.setSrvIncome(rs.getInt("unitPrice"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	

// 수입에 들어가는 부품비
  public YuriSalesMgrBean monthProduct(String cumNum, String endDate, String mainStatus, int techNum ){
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	YuriSalesMgrBean bean = new YuriSalesMgrBean();
	try {
		con = pool.getConnection();
		sql  = "SELECT un.unitPrice "
				+ "FROM maintenance main "
				+ "JOIN detail dtl "
				+ "ON dtl.dtlSrvNum = main.mainSrvNum "
				+ "JOIN unit un "
				+ "ON un.unitNum = dtl.dtlUnitNum "
				+ "JOIN service srv "
				+ "ON srv.srvNum = dtl.dtlSrvNum "
				+ "JOIN technician tech "
				+ "ON tech.techNum = srv.srvTechNum "
				+ "WHERE main.mainComNum=? AND main.mainEndDay = ? and main.mainStatus=? "
				+ "AND srv.srvTechNum = ? AND un.unitNum LIKE 'p%' AND dtl.dtlDeleted_yn='N' " ;

		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, cumNum);
		pstmt.setString(2, endDate);
		pstmt.setString(3, mainStatus);
		pstmt.setInt(4, techNum);
		rs = pstmt.executeQuery();
		if(rs.next()){
			bean.setCumNum(rs.getString("mainComNum"));
			bean.setEndDate(rs.getString("mainEndDay"));
			bean.setMainStatus(rs.getString("mainStatus"));
			bean.setTechNum(rs.getInt("srvTechNum"));
			bean.setProIncome(rs.getInt("unitprice"));
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		pool.freeConnection(con, pstmt, rs);
	}
	return bean;
 }
  
  
  // 비용으로 나가는 부품
  public YuriSalesMgrBean monthOutput(String buyDate){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		YuriSalesMgrBean bean = new YuriSalesMgrBean();
		try {
			con = pool.getConnection();
			sql  = "SELECT un.unitprice * st.stckQty "
					+ "FROM stock st "
					+ "JOIN unit un "
					+ "ON st.stckUnitNum = un.unitNum "
					+ "WHERE st.stckBuyDate = ? " ;

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, buyDate);
		
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setBuyDate(rs.getString("stckBuyDate"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
  }
}









