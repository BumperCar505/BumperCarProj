package all;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GwakMemberMgr {
	
	private DBConnectionMgr pool;
	
	public GwakMemberMgr() {
		//DBConnection 객체 10개 미리 생성
		pool = DBConnectionMgr.getInstance();
	}

	

	// techListEdit
	public GwakMemberBean select(int techNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();
		try {
			con = pool.getConnection();
			sql = "select * from technician where techNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, techNum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setTechNum(rs.getInt("techNum"));
				bean.setTechComNum(rs.getString("techComNum"));
				bean.setTechName(rs.getString("techName"));
				bean.setTechTel(rs.getString("techTel"));
				bean.setTechLv(rs.getString("techLv"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	
	// UnitStockMgr edit - select로 값 받아오기
	public GwakMemberBean select2(String editIndex){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();
		try {
			con = pool.getConnection();
			sql = "SELECT unit.unitNum, unit.unitName, unit.unitPrice, unit.unitVendor, stock.stckQty, stock.stckBuyDate "
					+ "FROM unit "
					+ "JOIN stock "
					+ "ON unit.unitNum = stock.stckUnitNum "
					+ "where stock.stckUnitNum = ? "
					+ "AND stock.stckComNum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editIndex);
//			pstmt.setString(2, "1112233333");
			pstmt.setString(2, "1112233333");
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setUnitNum(rs.getString("unit.unitNum"));
				bean.setUnitName(rs.getString("unit.unitName"));
				bean.setUnitPrice(rs.getInt("unit.unitPrice"));
				bean.setUnitVendor(rs.getString("unit.unitVendor"));
				bean.setStckQty(rs.getInt("stock.stckQty"));
				bean.setStckBuyDate(rs.getString("stock.stckBuyDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	
	
	// techListEdit_edit 수정기능
	public boolean update(GwakMemberBean bean,int index){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update technician set techName=?,techTel=?,techLv=? "
					+ "where techNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getTechName());
			pstmt.setString(2, bean.getTechTel());
			pstmt.setString(3, bean.getTechLv());
			pstmt.setInt(4, index);
			
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// UnitstockMgr_edit 수정기능
		public boolean update2(GwakMemberBean bean,String index){
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "UPDATE stock "
						+ "SET stckQty=?, stckBuyDate=? "
						+ "WHERE stckUnitNum = ? ";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bean.getStckQty());
				pstmt.setString(2, bean.getStckBuyDate());
				pstmt.setString(3, index);
				
				int cnt = pstmt.executeUpdate();
				if(cnt==1) flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}
	
	

	// 마지막 techNum 찾기
	  public GwakMemberBean lastNum(){ 
		  Connection con = null; 
		  PreparedStatement pstmt = null; 
		  ResultSet rs = null; 
		  String sql = null; 
		  GwakMemberBean bean = new GwakMemberBean(); 
		  try { con = pool.getConnection(); 
	  		sql = "select MAX(techNum) from technician "; 
	  		sql = "select * from technician ORDER BY ROWID LIMIT 1 "; 
	  		pstmt = con.prepareStatement(sql); rs = pstmt.executeQuery();
	  
	  		if(rs.next()){
	  			bean.setTechNum(rs.getInt("MAX(techNum)")); 
	  		}
		  } 
		  catch (Exception e) {
			  e.printStackTrace(); 
		  } 
		  finally {
			  pool.freeConnection(con, pstmt, rs); 
		  } 
		  return bean; 
	  }
	 
	
	// 추가 기능 - TechListedit
	public GwakMemberBean add(GwakMemberBean bean){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "INSERT INTO technician (techNum, comNum, techName, techTel, techLv) VALUES (NULL, '8885577777', ?, ?, ?) ";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bean.getTechName());
			pstmt.setString(2, bean.getTechTel());
			pstmt.setString(3, bean.getTechLv());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return bean;
	}
	
	// DELETE FROM 테이블이름 WHERE 필드이름=데이터값
	// DELETE FROM technician WHERE techNum=?
	// 삭제 기능
	public GwakMemberBean delete(GwakMemberBean bean){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "DELETE FROM technician WHERE techNum = ? ";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, bean.getTechNum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return bean;
	}
}









