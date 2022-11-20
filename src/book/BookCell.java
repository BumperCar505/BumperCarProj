package book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import all.DBConnectionMgr;
import all.LoginManager;

import java.awt.event.MouseAdapter;

// 날짜 정보
public class BookCell extends JPanel {
	
	private DBConnectionMgr pool;
	private LoginManager loginManager;

	ArrayList<JLabel> plan_list = new ArrayList<JLabel>();
	ArrayList<JLabel> day_list = new ArrayList<JLabel>();
	JLabel la_day, plan_count;
	String printDay;
	JPanel p_center, lday;
	int year, month, days;
	int get_maintenance_num;
	
	
	public BookCell() {
		pool = DBConnectionMgr.getInstance();
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
		
		lday = new JPanel();
		la_day = new JLabel();
		lday.add(la_day);
		
		plan_count = new JLabel();
		
		plan_count.setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		p_center = new JPanel();
		
		setLayout(new BorderLayout());
		add(lday, BorderLayout.NORTH);
		p_center.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(p_center);

		
	}
	

	public void setCellDate(int year, int month, int days, String id) {
		this.year = year;
		this.month = month;
		this.days = days;
		
		if (year > 0) {
			setSchedule(id);
		}
	}
	
	
	public void setCellColor(Color color) { p_center.setBackground(color); }
	
	public void setOtherMonthDay() {
		lday.setBackground(null);
		setCellColor(null);
		setBorder(BorderFactory.createEmptyBorder());
		la_day.setText("");
	}
	
	public void setMonthDay() {
		lday.setBackground(Color.WHITE);  // 날짜부분 배경
		setCellColor(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		la_day.setText("" + days);
		
		
		p_center.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// cell 클릭
				DayDay dayDay = new DayDay(year, month, days);
				dayDay.setDayDate(year, month, days);
				dayDay.setVisible(true);
				
			}
		});
	}
	
	
	public void labelDel() {
		for (int i = 0; i < plan_list.size(); i++) {
			p_center.remove(plan_list.get(i));
		}
		
		while (plan_list.size() > 0) {
			plan_list.remove(0);
		}
		this.updateUI();
	}
	
	public void setSchedule(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		String sql = "SELECT mainNum, customer.cusName, customer.cusCarNum, customer.cusTel, service.srvName, mainStartDay, mainStartTime, mainEndDay, mainEndTime "
				+ "FROM maintenance "
				+ "JOIN customer "
				+ "ON customer.cusNum = maintenance.mainCusNum "
				+ "JOIN service "
				+ "ON service.srvNum = maintenance.mainSrvNum "
				+ "WHERE mainStartDay = ?  AND mainComNum = ? "
				+ "ORDER BY mainStartTime ASC ";
		
		String now_date = year + "-" + (month + 1) + "-" + days;
		
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, now_date);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			rs.beforeFirst();
			
			if (total > 3) {
				for (int i = 0; i < total; i++) {
					rs.next();
					int mainNum = rs.getInt("mainNum");
					String cusName = rs.getString("cusName");
					String cusCarNum = rs.getString("cusCarNum");
					String srvName = rs.getString("srvName");
					String cusTel = rs.getString("cusTel");
					String mainStartDay = rs.getString("mainStartDay");
					String mainStartTime = rs.getString("mainStartTime");
					String mainEndDay = rs.getString("mainEndDay");
					String mainEndTime = rs.getString("mainEndTime");
					
					PlanInfo tmpLabel = new PlanInfo(mainNum, cusName, cusCarNum, srvName, cusTel, mainStartDay, mainStartTime, mainEndDay, mainEndTime, year, month,
							 days);
				
					plan_list.add(tmpLabel);
					if (plan_list.size() < 3) {
						p_center.add(tmpLabel);
					}
					else {
					plan_count.setText("+ " + (plan_list.size() - 2) + " ... ");  // 일정이 많으면 +숫자로 표시 
				}
			}
			p_center.add(plan_count);
			plan_list.add(plan_count);
			} else if (total < 4) {
				for (int i = 0; i < total; i++) {
					rs.next();
					int mainNum = rs.getInt("mainNum");
					String cusName = rs.getString("cusName");
					String cusCarNum = rs.getString("cusCarNum");
					String srvName = rs.getString("srvName");
					String cusTel = rs.getString("cusTel");
					String mainStartDay = rs.getString("mainStartDay");
					String mainStartTime = rs.getString("mainStartTime");
					String mainEndDay = rs.getString("mainEndDay");
					String mainEndTime = rs.getString("mainEndTime");

					PlanInfo tmpLabel = new PlanInfo(mainNum, cusName, cusCarNum, srvName, cusTel, mainStartDay, mainStartTime, mainEndDay, mainEndTime, year, month,
							 days);
					plan_list.add(tmpLabel);
					p_center.add(tmpLabel);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
		
	}
	
}