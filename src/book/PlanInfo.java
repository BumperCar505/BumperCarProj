package book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;

//import org.apache.poi.hssf.util.HSSFColor.PALE_BLUE;


public class PlanInfo extends JLabel {
	BookMain bMain;
	BookDetail detail = new BookDetail();
	PlanCount planCount;
	int get_maintenance_num;
	String mon;
	String day;
	Date this_date;
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public PlanInfo(int mainNum, String cusName, String cusCarNum, String srvName, String cusTel, BookMain bMain, String mainStartDay, String mainStartTime, String mainEndDay, String mainEndTime, int year, int month,
			int days, PlanCount planCount) {
//		this.get_schedule_no = schedule_no;
		this.get_maintenance_num = mainNum;
		this.bMain = bMain;
		this.planCount = planCount;
		
		setOpaque(true);
//		setBackground(getColor(schedule_no));
		setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		if (month < 10) {
			mon = "0" + (month + 1);
		} else {
			mon = String.valueOf(month + 1);
		}
		if (days < 10) {
			day = "0" + days;
		} else {
			day = Integer.toString(days);
		}
		String thisDay = year + "-" + mon + "-" + day;
		try {
			this_date = formatter.parse(thisDay);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal.setTime(this_date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (mainStartDay.equals(thisDay) || dayofweek == 1) { // dayofweek가 1이라는게 그날이 일요일이라는것
			setText(cusName + "-" + srvName);
		} else {
			setText("  ");
		}
		setPreferredSize(new Dimension(170, 21));
	}




}