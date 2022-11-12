package all;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 쿼리 전달용 클래스<br>
 * 객체 생성후 사용하면 됩니다.<br>
 * 사용방법 : 객체 생성(최초 1회만) => 쿼리 및 쿼리와 파라미터 설정 => 서버에 전송<br>
 * <b>서버에 전송후 설정된 쿼리 및 파라미터는 초기화됩니다.</b><br>
 * 내부적으로 서버와 연결후 알아서 close() 처리합니다.
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
     * 전달하고자하는 쿼리를 설정합니다.<br>
     * 쿼리의 값이 덮어씌워지고 파라미터는 설정되지않습니다.
     * @param query 전달할 쿼리
     */
	public void setQuery(String query) {
		this.query = query;
		this.params.clear();
	}
	
    /**
     * 전달하고자하는 쿼리와 파라미터를 설정합니다.<br>
     * <b>반드시 쿼리에 들어갈 순서대로 파라미터를 설정하세요.</b><br>
     * <b>이전의 쿼리의 값이 덮어씌워집니다.</b><br>
     * <b>이전에 저장된 파라미터가 지워지고 새롭게 추가됩니다!</b>
     * <p><b>예) INSERT INTO service(srvTechNum, srvName)
			VALUES(?, ?) 라는 쿼리를 전달했다면 ? 안에 전달한 파라미터가 들어갑니다.</b></p>
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
     * 전달할 파라미터를 추가합니다.<br>
     * <b>반드시 쿼리에 들어갈 순서대로 파라미터를 추가하세요.</b><br>
     * 서버로 전송전까지 파라미터를 계속 추가할수있습니다.<br>
     * @param params 추가할 파라미터
     */
	public void addParams(Object... params) {
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
     * <p>Insert 쿼리문에 ? 와 () 를 전달된 개수만큼 추가합니다.</p>
     * <p>Insert 쿼리를 한번에 날리기 위한 용도입니다.</p>
     * <p><b>반드시 Insert문에 사용해야합니다.</b><p>
     * <p><b>전달한 쿼리문은 반드시 VALUE/VALUES가 마지막으로 끝나야합니다.</b><p>
     * <p><b>예) INSERT INTO service(srvTechNum, srvName)
		VALUES(7, '엔진오일교체'), (8, '엔진오일교체'), (9, '엔진오일교체') 같은 쿼리를 전달한다면<br>
		INSERT INTO service(srvTechNum, srvName) VALUES 까지 만들어서 저장하면됩니다.</b><p>
     * @param paramCount () 안에 ? 를 추가할 개수를 전달
     * @param repeatCount () 를 추가할 개수를 전달
     */
	public void appendInsertValueQuery(int paramCount, int repeatCount) {
		String values = "(";
		
		for(int rCount = 0; rCount < repeatCount; ++rCount) {
			for(int pCount = 0; pCount < paramCount; ++pCount) {
				if(pCount == paramCount - 1) {
					values += "?)";
				} else {
					values += "?, ";
				}
			}
			
			if(rCount != repeatCount - 1) {
				values += ", (";
			}
		}
		
		query = query + values;
	}
	
    /**
     * <p>쿼리문에 ? 를 전달된 개수만큼 추가합니다.</p>
     * <p>마지막에 동적으로 파라미터가 늘어나는 부분을 대응하기 위해 만들었습니다.</p>
     * <p><b>반드시 (?,?,?) 형태로 끝나는 쿼리에 사용해야합니다.</b><p>
     * <p><b>예) SELECT srvNum FROM service WHERE srvName = '엔진오일교체' AND
			srvTechNum IN (7, 8, 9) 같은 쿼리가 있다면<br> SELECT srvNum FROM service WHERE srvName = '엔진오일교체' AND
			srvTechNum IN 로 끝나게 쿼리값을 전달해야합니다.</b><p>
     * @param paramCount () 안에 ? 를 추가할 개수를 전달
     */
	public void appendLastValueQuery(int paramCount) {
		String values = "(";
		
		for(int pCount = 0; pCount < paramCount; ++pCount) {
			if(pCount == paramCount - 1) {
				values += "?)";
			} else {
				values += "?, ";
			}
		}
		
		query = query + values;
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
