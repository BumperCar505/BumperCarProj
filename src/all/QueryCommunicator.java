package all;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>쿼리 전달용 클래스</p>
 * <p>객체 생성후 사용하면 됩니다.<p>
 * <p>사용방법 : 객체 생성(최초 1회만) => 쿼리 및 쿼리와 파라미터 설정 => 서버에 전송</p>
 * <p>서버에 전송후 설정된 쿼리 및 파라미터는 초기화됩니다.</p>
 * <p>내부적으로 서버와 연결후 알아서 close() 처리합니다.</p>
 * @author MoonNight285
 */
public class QueryCommunicator {
	private DBConnectionMgr mgr;
	private Connection connection;
	private PreparedStatement psmt;
	private ResultSet rs;
	private String query;
	private List<Object> params;
	
    /**
     * 드라이버에 설정된 기준으로 동작합니다.
     */
	public QueryCommunicator() {
		params = new ArrayList<Object>();
		mgr = DBConnectionMgr.getInstance();
	}
	
    /**
     * 외부 드라이버에 설정된 기준으로 동작합니다.
     * getInstance()로 반환된 객체를 넣으세요
     * @param mgr 드라이버 정보가 있는 DB 객체
     */
	public QueryCommunicator(DBConnectionMgr mgr) {
		params = new ArrayList<Object>();
		this.mgr = mgr;
	}
	
    /**
     * 전달하고자하는 쿼리를 설정합니다.
     * @param query 전달할 쿼리
     */
	public void setQuery(String query) {
		this.query = query;
		this.params.clear();
	}
	
    /**
     * 전달하고자하는 쿼리와 파라미터를 설정합니다.
     * 반드시 쿼리에 들어갈 순서대로 파라미터를 설정하세요.
     * @param query 전달할 쿼리
     * @param params 전달할 파라미터
     */
	public void setQuery(String query, Object... params) {
		setQuery(query);
		for(int i = 0; i < params.length; ++i) {
			this.params.add(params[i]);
		}
	}
	
    /**
     * 현재 설정된 쿼리를 반환합니다.
     * @return 값이 있는경우 String 없는경우 null이 반환됩니다.
     */
	public String getQuery() {
		return query;
	}
	
    /**
     * 전달할 파라미터를 설정합니다.
     * 반드시 쿼리에 들어갈 순서대로 파라미터를 설정하세요.
     * @param params 전달할 파라미터
     */
	public void setParams(Object... params) {
		this.params.clear();
		for(int i = 0; i < params.length; ++i) {
			this.params.add(params[i]);
		}
	}
	
    /**
     * 설정되어있는 파라미터들을 가져옵니다.
     * @return 값이 있는경우 List 타입으로 없는경우 길이가 0인 List가 반환됩니다.
     */
	public List<Object> getParams() {
		return params;
	}
	
    /**
     * 설정된 쿼리를 지웁니다.
     */
	private void clearQuery() {
		query = null;
	}
	
    /**
     * 설정된 파라미터들을 지웁니다.
     */
	private void clearParams() {
		params.clear();
	}
	
    /**
     * 전달할 쿼리에 등록되어있는 파라미터들을 결합시킵니다.
     * @return 성공시 true 실패시 false가 반환됩니다.
     */
	private boolean combineParams() {
		try {
			for(int i = 0; i < params.size(); ++i) {
				String classType = params.get(i).getClass().getSimpleName();
				Object value = params.get(i);
				
				if(classType.equals("Integer")) {
					psmt.setInt(i + 1, Integer.parseInt(value.toString()));
				} else if(classType.equals("Double")) {
					psmt.setDouble(i + 1, Double.parseDouble(value.toString()));
				} else {
					psmt.setString(i + 1, value.toString());
				}
			}
			
			return true;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
    /**
     * 연결되어있는 서버에 쿼리를 전송합니다.
     * @param columnNames 서버에서 가져올 컬럼의 이름들을 넣으세요.(순서 무관)
     * @return key 값은 컬럼의 이름 value 값은 해당 컬럼의 값(String으로 반환)<br> 값이 없는경우 List의 데이터의 개수가 0<br> 에러 발생시 null 반환
     */
	public List<HashMap<String, String>> executeQuery(String... columnNames) {
		try {
			connection = mgr.getConnection();
			psmt = connection.prepareStatement(query);
			
			if(combineParams() == false) {
				return null; // 파라미터 결합 실패시 null 반환
			}
			
			rs = psmt.executeQuery();
			
			List<HashMap<String, String>> rows = new ArrayList<HashMap<String,String>>();
			while(rs.next()) {
				HashMap<String, String> columnValues = new HashMap<String, String>();
				
				for(int i = 0; i < columnNames.length; ++i) {
					columnValues.put(columnNames[i], rs.getString(columnNames[i]));
				}
				
				rows.add(columnValues);
			}
			
			return rows;
		} catch(SQLException ex) { 
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				clearQuery();
				clearParams();
				if(rs != null) { rs.close(); }
				if(psmt != null) { psmt.close(); }
				if(connection != null) { connection.close(); }
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		// 에러 발생시 null
		return null;
	}
	
    /**
     * 연결되어있는 서버에 쿼리를 전송합니다.
     * @return 영향을 받은 행의 수가 반환됩니다.<br> 실패시 -1이 반환됩니다.
     */
	public int executeUpdate() {
		try {
			connection = mgr.getConnection();
			psmt = connection.prepareStatement(query);
			
			if(combineParams() == false) {
				return -1; // 파라미터 결합 실패시 -1 반환
			}
			
			int affectedRows = psmt.executeUpdate();
			
			return affectedRows;
		} catch(SQLException ex) { 
			ex.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				clearQuery();
				clearParams();
				if(psmt != null) { psmt.close(); }
				if(connection != null) { connection.close(); }
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		// 에러 발생시 -1 반환
		return -1;
	}
}
