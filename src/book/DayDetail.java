package book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import all.LoginManager;

public class DayDetail extends JLabel {

	private final DBManager dbManager = new DBManager();
	private LoginManager loginManager;
	
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
		loginManager = loginManager.getInstance();
	    String id = loginManager.getLogComNum();
	    
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

		addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				BookDetail bookDetail = new BookDetail(); 
				bookDetail.setVisible(true);		
				bookDetail.getDetail(id, mainNum);
			}
		});
		
		setPreferredSize(new Dimension(400, 30));
	}
}