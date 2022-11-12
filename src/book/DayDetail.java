package book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DayDetail extends JLabel {

	int year, month, days;
	BookMain bMain;
	BookDetail detail;
	int get_maintenance_num;
	String mon;
	String day;
	Date startTime;
	Calendar cal = Calendar.getInstance();
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");


	public DayDetail(int mainNum, String cusName, String cusCarNum, String srvName, String techName, String cusTel, BookMain bMain, String mainStartDay, String mainStartTime, String mainEndDay, String mainEndTime, int year, int month,
			int days) {
		this.get_maintenance_num = mainNum;
		this.bMain = bMain;
		
		setOpaque(true);
		setBackground(Color.WHITE);
		setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
//		try {
//			startTime = formatTime.parse(mainStartTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		setText(" " + cusName + ", " + srvName + ", " + mainStartTime + " ( 담당자 : " + techName + " )");

		setPreferredSize(new Dimension(400, 21));
	}

	
//	// 상세 일정 보기
//	public void move(int mainNum) {
//		Connection conn = bMain.getConn();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//
//		String sql = "SELECT customer.cusName, customer.cusCarNum, customer.cusCarBrand, customer.cusCarType, customer.cusTel, service.srvName, mainStartDay, mainStartTime, mainEndDay, mainEndTime "
//				+ "FROM maintenance "
//				+ "JOIN customer "
//				+ "ON customer.cusNum = maintenance.mainCusNum "
//				+ "JOIN service "
//				+ "ON service.srvNum = maintenance.mainSrvNum "
//				+ "WHERE maintenance.mainNum = ? ";
//		
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, get_maintenance_num);
//
//			rs = pstmt.executeQuery();
//			rs.next();
//			String cusName = rs.getString("cusName");
//			String cusCarNum = rs.getString("cusCarNum");
//			String cusCarBrand = rs.getString("cusCarBrand");
//			String cusCarType = rs.getString("cusCarType");
//			String cusTel = rs.getString("cusTel");
//			String srvName = rs.getString("srvName");
//			String mainStartDay = rs.getString("mainStartDay");
//			String mainStartTime = rs.getString("mainStartTime");
//			String mainEndDay = rs.getString("mainEndDay");
//			String mainEndTime = rs.getString("mainEndTime");
//			detail.setSchedule(mainNum, cusName, cusCarNum, cusCarBrand, cusCarType, cusTel, srvName, mainStartDay, mainStartTime, mainEndDay, mainEndTime);
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			bMain.closeDB(pstmt, rs);
//		}
//
//	}
}