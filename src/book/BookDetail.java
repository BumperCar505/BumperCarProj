package book;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import all.DBConnectionMgr;
import all.LoginManager;
import all.Size;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;


public class BookDetail extends JFrame {

	private DBConnectionMgr pool;
	private LoginManager loginManager;
	
	JPanel contentPane;
	JTextField cusName;
	JTextField cusCarNum;
	JTextField cusCarBrand;
	JTextField cusCarType;
	JTextField cusTel;
	JTextField cusBookTime;
	JTextField completedTime;
	JComboBox<String> srvName;
	JComboBox<String> techName;
	JComboBox statusBox;
	JButton btnBook;
	
	int mainNum;
	
	int get_maintenance_num;
	String selectedSrv;
	
	
	String[] status = { "예약됨", "정비중", "정비완료", "예약취소" };
	ImageIcon[] images = {
			new ImageIcon("/img/statusOrange.png"),
			new ImageIcon("/img/statusBlue.png"),
			new ImageIcon("/img/statusGreen.png"),
			new ImageIcon("/img/statusGray.png")
	};
	

	public BookDetail() {
		pool = DBConnectionMgr.getInstance();
		
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBook = new JButton();
		btnBook.setBackground(new Color(244, 204, 204));
		btnBook.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		btnBook.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnBook.setBounds(332, 923, 150, 50);
		contentPane.add(btnBook);
		
		JPanel panel = new JPanel();
		panel.setBounds(73, 137, 458, 759);
//		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("고객명");
		lblNewLabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel.setBounds(0, 43, 144, 36);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("차번호");
		lblNewLabel_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1.setBounds(0, 122, 144, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("브랜드");
		lblNewLabel_2.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_2.setBounds(0, 201, 144, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_3.setBounds(0, 359, 144, 36);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("서비스");
		lblNewLabel_4.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_4.setBounds(0, 438, 144, 36);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("예약 시간");
		lblNewLabel_5.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_5.setBounds(0, 596, 144, 36);
		panel.add(lblNewLabel_5);
		
		cusName = new JTextField();
		cusName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertCusInfo();
			}
		});

		cusName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusName.setBounds(169, 30, 289, 50);
		panel.add(cusName);
//		cusName.setColumns(10);
		
		cusCarNum = new JTextField();
		cusCarNum.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarNum.setColumns(10);
		cusCarNum.setBounds(169, 110, 289, 50);
		panel.add(cusCarNum);
		
		cusCarBrand = new JTextField();
		cusCarBrand.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarBrand.setColumns(10);
		cusCarBrand.setBounds(169, 190, 289, 50);
		panel.add(cusCarBrand);
		
		cusCarType = new JTextField();
		cusCarType.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarType.setColumns(10);
		cusCarType.setBounds(169, 270, 289, 50);
		panel.add(cusCarType);
		
		cusTel = new JTextField();
		cusTel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusTel.setColumns(10);
		cusTel.setBounds(169, 350, 289, 50);
		panel.add(cusTel);
		
		srvName = new JComboBox();
		srvName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		srvName.setBounds(169, 430, 289, 50);
		panel.add(srvName);
		
		srvName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedSrv = (String) srvName.getSelectedItem();
				techList(id, selectedSrv);
			}
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("차종");
		lblNewLabel_2_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_2_1.setBounds(0, 280, 144, 36);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("정비 완료 시간");
		lblNewLabel_5_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_5_1.setBounds(0, 675, 144, 36);
		panel.add(lblNewLabel_5_1);
		
		cusBookTime = new JTextField();
		cusBookTime.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusBookTime.setColumns(10);
		cusBookTime.setBounds(169, 590, 289, 50);
		panel.add(cusBookTime);
		
		completedTime = new JTextField();
		completedTime.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		completedTime.setColumns(10);
		completedTime.setBounds(169, 670, 289, 50);
		panel.add(completedTime);
		
		JLabel lblNewLabel_4_1 = new JLabel("정비사");
		lblNewLabel_4_1.setFont(new Font("Dialog", Font.BOLD, 21));
		lblNewLabel_4_1.setBounds(0, 517, 144, 36);
		panel.add(lblNewLabel_4_1);
		
		techName = new JComboBox();
		techName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		techName.setBounds(169, 510, 289, 50);
		panel.add(techName);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(BookDetail.class.getResource("/img/YellowCat.png")));
		lblNewLabel_6.setBounds(176, 45, 223, 63);
		contentPane.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(73, 923, 54, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(18, 14, 22, 22);
		panel_1.add(statusLabel);
		
		statusBox = new JComboBox(status);
		statusBox.setSelectedItem(null);
		statusBox.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));

		statusBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
//				int index = cb.getSelectedIndex();
//				statusLabel.setIcon(images[index]);

				
				if ("예약됨".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusOrange.png")));
				} else if ("정비중".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusBlue.png")));
				} else if ("정비완료".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusGreen.png")));
				} else if ("예약취소".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusGray.png")));
				}
			}
		});
		
		
		statusBox.setBounds(130, 923, 150, 50);
		
//		statusBox.addItem(statusSymbol);
//		statusBox.addItem(statusText);
//		statusBox.setRenderer();
		contentPane.add(statusBox);
		
		
	}
	public void setDate(int year, int month, int days) {
		String mon, day;
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
		this.cusBookTime.setText(year + "-" + mon + "-" + day + " ");
	}
	
	public void srvList(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT DISTINCT srvName "
					+ "FROM service "
					+ "JOIN technician "
					+ "ON technician.techNum = service.srvTechNum "
					+ "WHERE technician.techComNum = ? "
					+ "AND deleted_yn = 'N' ";
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
					String srvName = rs.getString("srvName");
					this.srvName.addItem(srvName);
			}
			srvName.setSelectedItem(null);
		} catch (Exception e1) {			
			e1.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
	}
	
	public void techList(String id, String selectedSrv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		techName.removeAllItems();
		
		try {
			String sql = "SELECT DISTINCT techName "
					+ "FROM technician "
					+ "JOIN service "
					+ "ON service.srvTechNum = technician.techNum "
					+ "WHERE technician.techComNum = ? AND service.srvName = ? "
					+ "AND service.deleted_yn = 'N' ";
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, selectedSrv);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
					String techName = rs.getString("techName");
					this.techName.addItem(techName);
			}
			techName.setSelectedItem(null);
		} catch (Exception e1) {			
			e1.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
	}
	
	public void getDetail(String id, int mainNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		String sql = "SELECT mainNum, customer.cusName, customer.cusCarNum, customer.cusCarBrand, customer.cusCarType, customer.cusTel, service.srvName, technician.techName, mainStatus, mainStartDay, mainStartTime, mainEndDay, mainEndTime "
				+ "FROM maintenance "
				+ "JOIN customer "
				+ "ON customer.cusNum = maintenance.mainCusNum "
				+ "JOIN service "
				+ "ON service.srvNum = maintenance.mainSrvNum "
				+ "JOIN technician "
				+ "ON technician.techNum = maintenance.mainTechNum "
				+ "WHERE maintenance.mainNum = ? ";
		

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mainNum);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				this.cusName.setText(rs.getString("cusName"));
				this.cusCarNum.setText(rs.getString("cusCarNum"));
				this.cusCarBrand.setText(rs.getString("cusCarBrand"));
				this.cusCarType.setText(rs.getString("cusCarType"));
				this.cusTel.setText(rs.getString("cusTel"));

				String srvName = rs.getString("srvName");
				String techName = rs.getString("techName");
				
				this.cusBookTime.setText(rs.getString("mainStartDay") + " " + rs.getString("mainStartTime"));
				this.completedTime.setText(rs.getString("mainEndDay") + " " + rs.getString("mainEndTime"));

				srvList(id);
				this.srvName.setSelectedItem(srvName);
				
				techList(id, srvName);
				this.techName.setSelectedItem(techName);
				
				this.statusBox.setSelectedItem(rs.getString("mainStatus"));
				
				btnBook.setText("저장");
				btnBook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateDetail(mainNum);
						setVisible(false);
					}
				});
				
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}

	}
	
	
	public void insertCusInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql1 = "SELECT cusName, cusCarNum, cusCarBrand, cusCarType, cusTel "
					+ "FROM customer "
					+ "WHERE cusName = ? ";
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, this.cusName.getText());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				this.cusCarNum.setText(rs.getString("cusCarNum"));
				this.cusCarBrand.setText(rs.getString("cusCarBrand"));
				this.cusCarType.setText(rs.getString("cusCarType"));
				this.cusTel.setText(rs.getString("cusTel"));
			}

		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
	}
	
	public void insertDetail(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "INSERT INTO maintenance (mainComNum, mainCusNum, mainSrvNum, mainStartDay, mainStartTime, mainEndDay, mainEndTime, mainStatus, mainTechNum) "
					+ "SELECT ? , (SELECT customer.cusNum FROM customer WHERE customer.cusName = ?), "
					+ "(SELECT service.srvNum FROM service WHERE service.srvName = ? AND srvTechNum = (SELECT techNum FROM technician WHERE techName = ?)), "
					+ "?, ?, ?, ?, ?, (SELECT technician.techNum FROM technician WHERE technician.techName = ?) ";

			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, this.cusName.getText());
			pstmt.setString(3, (String) this.srvName.getSelectedItem());
			pstmt.setString(4, (String) this.techName.getSelectedItem());
			pstmt.setString(5, this.cusBookTime.getText().substring(0, 10));
			pstmt.setString(6, this.cusBookTime.getText().substring(11));
			pstmt.setString(7, this.completedTime.getText().substring(0, 10));
			pstmt.setString(8, this.completedTime.getText().substring(11));
			pstmt.setString(9, (String) this.statusBox.getSelectedItem());
			pstmt.setString(10, (String) this.techName.getSelectedItem());
			
			pstmt.executeUpdate();
			
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt);
		}
	}
	
	public void updateDetail(int mainNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE maintenance, customer SET customer.cusName = ?, customer.cusCarNum = ?, customer.cusCarBrand = ?, customer.cusCarType = ?, customer.cusTel = ?, "
					+ "mainSrvNum = (SELECT srvNum FROM service WHERE srvName = ? AND srvTechNum = (SELECT techNum FROM technician WHERE techName = ?)), "
					+ "mainTechNum = (SELECT techNum FROM technician WHERE techName = ?), mainStatus = ?, mainStartDay = ?, mainStartTime = ?, mainEndDay = ?, mainEndTime = ? "
					+ "WHERE customer.cusNum = maintenance.mainCusNum AND maintenance.mainNum = ? ";
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, this.cusName.getText());
			pstmt.setString(2, this.cusCarNum.getText());
			pstmt.setString(3, this.cusCarBrand.getText());
			pstmt.setString(4, this.cusCarType.getText());
			pstmt.setString(5, this.cusTel.getText());
			pstmt.setString(6, (String) this.srvName.getSelectedItem());
			pstmt.setString(7, (String) this.techName.getSelectedItem());
			pstmt.setString(8, (String) this.techName.getSelectedItem());
			pstmt.setString(9, (String) this.statusBox.getSelectedItem());
			pstmt.setString(10, this.cusBookTime.getText().substring(0, 10));
			pstmt.setString(11, this.cusBookTime.getText().substring(11));
			pstmt.setString(12, this.completedTime.getText().substring(0, 10));
			pstmt.setString(13, this.completedTime.getText().substring(11));
			pstmt.setInt(14, mainNum);
			
			pstmt.executeUpdate();
		} catch (Exception e3) {
			e3.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt);

		}
	}
}

