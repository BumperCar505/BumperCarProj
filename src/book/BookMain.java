package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMain {

	private Connection conn = null;
	
//	private final DBConnectionMgr dbManager = new DBConnectionMgr();  // DB연결 
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private String user = "root";
	private String password = "1234";
	
//	public UserInfo = new UserInfo();  // 유저 정보 저장 
	
//	화면 닫을 때 발생하는 메서드 
//	public void frameClose() {
//		dbManager.closeDB();
//		System.exit(0);
//	}
	
	public Connection getConn() {
		if (conn == null) {
			connect();
			if (conn == null) {  // DB 접속 못하면 재시도 
				getConn();
			}
		}
		return conn;
	}
	
	private void connect() {
		if (conn == null) {
			try {
				Class.forName(driver);
				this.conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (this.conn == null) {
				System.out.println("DB 연결 실패");
			} else {
				System.out.println("DB 연결 성곻");
			}
		}
	}
	
	void closeDB() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	void closeDB(PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	void closeDB(PreparedStatement pstmt) {

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public Connection getConn() { return dbManager.getConn(); }
	
//	public void closeDB(PreparedStatement pstmt, ResultSet rs) { dbManager.closeDB(pstmt, rs); }
	
//	public void closeDB(PreparedStatement pstmt) { dbManager.closeDB(pstmt); }
	
	public static void main(String[] args) {
		BookMain bMain = new BookMain();
	}
}
