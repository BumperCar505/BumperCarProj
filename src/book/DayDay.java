package book;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import all.LoginManager;

import java.awt.FlowLayout;

public class DayDay extends JFrame {
	
	private final DBManager dbManager = new DBManager();
	private LoginManager loginManager;

	private JPanel contentPane;

	ArrayList<JLabel> plan_list = new ArrayList<JLabel>();
	ArrayList<JLabel> day_list = new ArrayList<JLabel>();
	JLabel la_day, plan_count;
	String printDay;
	JPanel p_center, lday, panel;
	int year, month, days;

	public void setDayDate(int year, int month, int days) {
		this.year = year;
		this.month = month;
		this.days = days;
		
		if (year > 0) {
			setDaySchedule(year, month, days);
		}
	}
	
		
	public DayDay(int year, int month, int days) {
		loginManager = loginManager.getInstance();
	    String id = loginManager.getLogComNum();
	    
		setTitle((month+1) + "월 " + days + "일 일정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);


		JLabel l = new JLabel();
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setBounds(193, 10, 98, 36);
		l.setText((month+1) + "월 " + days + "일");
		l.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		contentPane.add(l);

		panel = new JPanel();
		panel.setBounds(12, 59, 460, 332);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		contentPane.add(panel);

		JButton btnAdd = new JButton("추가");
		btnAdd.setBounds(192, 401, 100, 50);
		btnAdd.setBackground(new Color(244, 204, 204));
		btnAdd.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		btnAdd.setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		contentPane.add(btnAdd);


	
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BookDetail bookDetail = new BookDetail();
				bookDetail.setVisible(true);
				bookDetail.srvList(id);
				bookDetail.setDate(year, month, days);
				bookDetail.btnBook.setText("등록");

				bookDetail.btnBook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bookDetail.insertDetail(id);
						bookDetail.setVisible(false);
						setVisible(false);
					}
				});
				
			}
		});
	}

//	날짜 클릭하면 나오는 폼 스케쥴
	public void setDaySchedule(int year, int month, int days) {
		Connection conn = dbManager.getConn();
//		Connection conn = null;

		
		String sql = "SELECT mainNum, customer.cusName, customer.cusCarNum, customer.cusTel, service.srvName, technician.techName, mainStartDay, mainStartTime, mainEndDay, mainEndTime "
				+ "FROM maintenance "
				+ "JOIN customer "
				+ "ON customer.cusNum = maintenance.mainCusNum "
				+ "JOIN service "
				+ "ON service.srvNum = maintenance.mainSrvNum "
				+ "JOIN technician "
				+ "ON technician.techNum = mainTechNum "
				+ "WHERE mainStartDay = ? "
				+ "ORDER BY mainStartTime ASC ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String now_date = this.year + "-" + (this.month + 1) + "-" + this.days;
		
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, now_date);
			
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			rs.beforeFirst();
			

				for (int i = 0; i < total; i++) {
					rs.next();
					int mainNum = rs.getInt("mainNum");
					String cusName = rs.getString("cusName");
					String cusCarNum = rs.getString("cusCarNum");
					String srvName = rs.getString("srvName");
					String techName = rs.getString("techName");
					String cusTel = rs.getString("cusTel");
					String mainStartDay = rs.getString("mainStartDay");
					String mainStartTime = rs.getString("mainStartTime");
					String mainEndDay = rs.getString("mainEndDay");
					String mainEndTime = rs.getString("mainEndTime");
					
					DayDetail dayLabel = new DayDetail(mainNum, cusName, cusCarNum, srvName, techName, cusTel, mainStartDay, mainStartTime, mainEndDay, mainEndTime, year, month,
							 days);
					
//					System.out.println(dayLabel);
					panel.add(dayLabel);
					
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
//		} finally {
//			dbManager.closeDB(pstmt, rs);
//			dbManager.closeDB();
			
		}
		
	}
}
