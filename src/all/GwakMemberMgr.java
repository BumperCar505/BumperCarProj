package all;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class GwakMemberMgr {
	
	private DBConnectionMgr pool;
	private LoginManager loginManager;
	
	public GwakMemberMgr() {
		//DBConnection 객체 10개 미리 생성.
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
	
	
	// select22 : DB에서 데이터 불러와서 테이블 채우기 ( TechListEdit)
	public GwakMemberBean Select22(DefaultTableModel model, String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();
		
				try {		
					con = pool.getConnection();
					sql = "select * from technician WHERE techComNum = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
						while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
		                 model.addRow(new Object[]{rs.getInt("techNum"), rs.getString("techName"), rs.getString("techTel"),rs.getString("techLv")});
		                }
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
				}
				return bean;
			}
	
	
	
	//  UnitStockMgr : DB에서 데이터 불러와서 테이블 채우기
	public GwakMemberBean SelectUnitMgrTable(DefaultTableModel model, String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();

		try {
			con = pool.getConnection();
			// 재고는 stckQty2 (정비완료시 재고 빠진것 업데이트 된 열)
			sql = "SELECT stock.stckNum, stock.stckUnitNum, unit.unitName, unit.unitVendor, sum(stock.stckQty2) "
					+ "FROM stock " 
					+ "inner join unit "
					+ "on stock.stckUnitNum = unit.unitNum "
					+ "WHERE stock.stckComNum = ? "
					+ "group by stock.stckUnitNum "
					+ "ORDER BY stock.stckUnitNum ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
	
					rs = pstmt.executeQuery();
					while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
						model.addRow(new Object[]{rs.getInt("stock.stckNum"), rs.getString("stock.stckUnitNum"), rs.getString("unit.unitName"), rs.getString("unit.unitVendor"),rs.getInt("sum(stock.stckQty2)")});
					}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
					}
					return bean;

				}
	
	
	
	// UnitStockMgr 테이블 값 받아오기
	public GwakMemberBean select2(String editIndex, String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();
		try {
			con = pool.getConnection();
			sql = "SELECT stock.stckNum, unit.unitNum, unit.unitName, unit.unitPrice, unit.unitVendor, stock.stckQty1, stock.stckBuyDate "
					+ "FROM unit "
					+ "JOIN stock "
					+ "ON unit.unitNum = stock.stckUnitNum "
					+ "where stock.stckUnitNum = ? "
					+ "AND stock.stckComNum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, editIndex);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setStckNum(rs.getInt("stock.stckNum"));
				bean.setUnitNum(rs.getString("unit.unitNum"));
				bean.setUnitName(rs.getString("unit.unitName"));
				bean.setUnitPrice(rs.getInt("unit.unitPrice"));
				bean.setUnitVendor(rs.getString("unit.unitVendor"));
				bean.setStckQty1(rs.getInt("stock.stckQty1"));
				bean.setStckBuyDate(rs.getString("stock.stckBuyDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return bean;
	}
	
	public GwakMemberBean ComboUnit(JComboBox<String> unitNameCmb, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();
	try {
		
		sql="SELECT distinct unit.unitName from unit "
				+ "inner join stock "
				+ "on stock.stckUnitNum = unit.unitNum "
				+ "WHERE stock.stckComNum = ? ";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
		String unitName = rs.getString("unitName");
		unitNameCmb.addItem(unitName);
		}
		
		}catch(SQLException e) {
			
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
	return bean;
	}
	
	
	// UnitStockMgr edit 창 기본값 세팅
		public GwakMemberBean Select4(int stckNum, String id){
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			GwakMemberBean bean = new GwakMemberBean();
			try {
				con = pool.getConnection();
				sql = "SELECT stock.stckBuyDate, unit.unitNum, unit.unitName, unit.unitVendor, stock.stckQty1 "
						+ "FROM unit "
						+ "JOIN stock "
						+ "ON unit.unitNum = stock.stckUnitNum "
						+ "where stock.stckNum = ? "
						+ "AND stock.stckComNum = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, stckNum);
				pstmt.setString(2, id);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					bean.setStckBuyDate(rs.getString("stock.stckBuyDate"));
					bean.setUnitNum(rs.getString("unit.unitNum"));
					bean.setUnitName(rs.getString("unit.unitName"));
					bean.setUnitVendor(rs.getString("unit.unitVendor"));
					bean.setStckQty1(rs.getInt("stock.stckQty1"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return bean;
		}
		
		
		
		// 콤보박스 값에 따라 텍스트필드 업데이트( UnitStockMgr_addUnit)
	      public GwakMemberBean Select5(String item){
	         Connection con = null;
	         PreparedStatement pstmt = null;
	         ResultSet rs = null;
	         String sql = null;
	         GwakMemberBean bean = new GwakMemberBean();
	         try {
	            con = pool.getConnection();
	            sql = "select * from unit where unitName= ?";
	                  
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, item);
	            rs = pstmt.executeQuery();
	            
	            if(rs.next()){
	               bean.setUnitPrice(rs.getInt("unitPrice"));
	               bean.setUnitVendor(rs.getString("unitVendor"));
	               
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
			sql = "update technician set techName=?,techTel=?,techLv=?z "
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
	
	// UnitstockMgr 구매내역 수정기능
		public boolean update2(GwakMemberBean bean, String id){
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "UPDATE stock "
						+ "SET stckQty1=?, stckBuyDate=? "
						+ "WHERE stckComNum = ? AND stckNum = ? ";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bean.getStckQty1());
				pstmt.setString(2, bean.getStckBuyDate());
				pstmt.setString(3, id); 
				pstmt.setInt(4, bean.getStckNum()); 
				
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
	public GwakMemberBean add(GwakMemberBean bean, String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		
		
		try {
			con = pool.getConnection();
			sql = "INSERT INTO technician (techNum, techComNum, techName, techTel, techLv) VALUES (NULL, ?, ?, ?, ?) ";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, bean.getTechName());
			pstmt.setString(3, bean.getTechTel());
			pstmt.setString(4, bean.getTechLv());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return bean;
	}
	
	
	// 품목 추가 기능 - UnitStockMgr_addUnit
		public GwakMemberBean addUnit(GwakMemberBean bean, String id){
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				con = pool.getConnection();
				sql = "INSERT INTO stock (stckComNum, stckUnitNum, stckQty1, stckQty2, stckBuyDate) "
						+ "SELECT ?, unit.unitNum, 0, 0, ? "
						+ "FROM unit WHERE unit.unitName = ? ";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2, bean.getStckBuyDate()); 
				pstmt.setString(3, bean.getUnitName()); 
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return bean;
		}
		
		// 구매 이력 추가기능 -  UnitStockMgr_addHistory
				public GwakMemberBean addHistoty(GwakMemberBean bean, String id){
					Connection con = null;
					PreparedStatement pstmt = null;
					String sql = null;
					
					try {
						con = pool.getConnection();
						sql = "INSERT INTO stock (stckNum, stckComNum, stckUnitNum, stckQty1, stckQty2, stckBuyDate) "
								+ "SELECT NULL, ?, unit.unitNum, ?, ?, ? "
								+ "FROM unit WHERE unit.unitName = ? ";
						pstmt = con.prepareStatement(sql);

						pstmt.setString(1, id);
						pstmt.setInt(2, bean.getStckQty1());
						pstmt.setInt(3, bean.getStckQty2());
						pstmt.setString(4, bean.getStckBuyDate()); 
						pstmt.setString(5, bean.getUnitName()); 
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
			// sql = "DELETE FROM technician WHERE techNum = ? ";
			sql = "UPDATE technician SET techDeleted_yn = 'Y' WHERE techNum = ? ";
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
	
	// UnitStockMgr의 삭제 기능
	public GwakMemberBean delete2(GwakMemberBean bean, String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			sql = "DELETE FROM stock WHERE stckComNum = ? AND stckUnitNum = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, bean.getStckUnitNum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return bean;
	}
	
	
	// 품목 등록 - 콤보박스에 리스트 추가
	public List<String> getEmpFirstName() {
        List<String> list = new ArrayList();
        Connection conn = null;

        try {
        	conn = pool.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT DISTINCT unitName "
                            + "FROM unit "
                            + "ORDER BY unitName ";
            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                list.add(results.getString("unitName"));
            }
        } catch (Exception e) {
            System.out.println("Exception = " + e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }                    
            }
        }
        return list;
    }
}








