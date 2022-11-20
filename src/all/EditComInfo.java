package all;


import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import book.BookCalendar;
import book.DBManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EditComInfo extends JFrame {

	private final DBManager dbManager = new DBManager();
	private LoginManager loginManager;
	
	private JPanel contentPane;
	private JTextField comNum;
	private JTextField comPw;
	private JTextField newComPw;
	private JTextField comName;
	private JTextField comEmail;
	private JTextField comZip;
	private JTextField comAddr;
	private JTextField comTel;
	
	JLabel password;
	JLabel newPassword;
	private JTextField newComPwCheck;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditComInfo frame = new EditComInfo();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EditComInfo() {
		loginManager = loginManager.getInstance();
	    String id = loginManager.getLogComNum();
	      
	      
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(706, 48, 238, 94);
		lblNewLabel.setIcon(new ImageIcon(EditComInfo.class.getResource("/img/YellowCat.png")));
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(438, 177, 1130, 597);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("기존 비밀번호");
		lblNewLabel_1_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_1.setBounds(79, 91, 215, 41);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("새로운 비밀번호");
		lblNewLabel_1_2.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_2.setBounds(79, 154, 215, 41);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("업체명");
		lblNewLabel_1_3.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_3.setBounds(79, 277, 215, 41);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_5 = new JLabel("우편번호");
		lblNewLabel_1_5.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_5.setBounds(79, 405, 215, 41);
		panel.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("주소");
		lblNewLabel_1_6.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_6.setBounds(79, 470, 215, 41);
		panel.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("전화번호");
		lblNewLabel_1_7.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_7.setBounds(79, 534, 215, 41);
		panel.add(lblNewLabel_1_7);
		
		this.comNum = new JTextField();
		this.comNum.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		this.comNum.setEditable(false);
		this.comNum.setBounds(306, 26, 384, 42);
		panel.add(this.comNum);
		
		comPw = new JTextField();
		comPw.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		comPw.setColumns(10);
		comPw.setBounds(306, 90, 384, 42);
		panel.add(comPw);
		
		newComPw = new JTextField();
		newComPw.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		newComPw.setColumns(10);
		newComPw.setBounds(306, 154, 384, 42);
		panel.add(newComPw);
		
		comName = new JTextField();
		comName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		comName.setColumns(10);
		comName.setBounds(307, 278, 384, 42);
		panel.add(comName);
		
		comEmail = new JTextField();
		comEmail.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		comEmail.setColumns(10);
		comEmail.setBounds(307, 342, 384, 42);
		panel.add(comEmail);
		
		comZip = new JTextField();
		comZip.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		comZip.setColumns(10);
		comZip.setBounds(307, 406, 169, 42);
		panel.add(comZip);
		
		comAddr = new JTextField();
		comAddr.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		comAddr.setColumns(10);
		comAddr.setBounds(307, 470, 384, 42);
		panel.add(comAddr);
		
		comTel = new JTextField();
		comTel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		comTel.setColumns(10);
		comTel.setBounds(307, 534, 384, 42);
		panel.add(comTel);
		
		JLabel lblNewLabel_1_4 = new JLabel("사업자등록번호");
		lblNewLabel_1_4.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_4.setBounds(79, 26, 215, 41);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("이메일");
		lblNewLabel_1_3_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_3_1.setBounds(79, 342, 215, 41);
		panel.add(lblNewLabel_1_3_1);
		
		password = new JLabel("");
		password.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		password.setBounds(716, 91, 238, 41);
		panel.add(password);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("새로운 비밀번호 확인");
		lblNewLabel_1_2_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1_2_1.setBounds(79, 213, 215, 41);
		panel.add(lblNewLabel_1_2_1);
		
		newComPwCheck = new JTextField();
		newComPwCheck.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		newComPwCheck.setColumns(10);
		newComPwCheck.setBounds(306, 213, 384, 42);
		panel.add(newComPwCheck);
		
		newPassword = new JLabel("");
		newPassword.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		newPassword.setBounds(716, 213, 320, 41);
		panel.add(newPassword);
		
		JButton btnFixedComInfo = new JButton("수정 완료");
		btnFixedComInfo.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnFixedComInfo.setBounds(687, 867, 290, 65);
		btnFixedComInfo.setBackground(new Color(244, 204, 204));
		btnFixedComInfo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnFixedComInfo);
		btnFixedComInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateComInfo(id);
			}
		});
		btnFixedComInfo.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		
		JLabel backgroundImg = new JLabel("");
		backgroundImg.setIcon(new ImageIcon(BookCalendar.class.getResource("/img/Car2.jpg")));
		backgroundImg.setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		contentPane.add(backgroundImg);
		
	}

	public void updateComInfo(String id) {
//		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
//		Connection conn = null;
		Connection conn = dbManager.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		password.setText("");
//		newPassword.setText("");
		
		try {
			String sql1 = "SELECT pw FROM login "
					+ "WHERE logComNum = ? ";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String rpw = rs.getString("pw");
				if (newComPw.getText().equals("")) {
					if (comPw.getText().equals(rpw)) {
						String sql2 = "UPDATE company, login SET login.pw = ?, comName = ?, comEmail = ?, comZip = ?, comAddr = ?, comTel = ? "
								+ "WHERE company.comNum = login.logComNum AND comNum = ? ";
						pstmt = conn.prepareStatement(sql2);
						
						pstmt.setString(1, this.comPw.getText());
						pstmt.setString(2, this.comName.getText());
						pstmt.setString(3, this.comEmail.getText());
						pstmt.setInt(4, Integer.parseInt(this.comZip.getText()));
						pstmt.setString(5, this.comAddr.getText());
						pstmt.setString(6, this.comTel.getText());
						pstmt.setString(7, id);
						pstmt.executeUpdate();
						DialogManager.createMsgDialog("수정되었습니다!", "\\img\\success1.png",
								"수정완료", JOptionPane.PLAIN_MESSAGE);
						setVisible(false);
						new ComMyPage();
					} else {
						password.setText("비밀번호가 틀립니다.");
						password.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
						password.setForeground(Color.RED);
					}
				} else {
					if (comPw.getText().equals(rpw) && newComPw.getText().equals(newComPwCheck.getText())) {
						String sql2 = "UPDATE company, login SET login.pw = ?, comName = ?, comEmail = ?, comZip = ?, comAddr = ?, comTel = ? "
								+ "WHERE company.comNum = login.logComNum AND comNum = ? ";
						pstmt = conn.prepareStatement(sql2);
						
						pstmt.setString(1, this.newComPw.getText());
						pstmt.setString(2, this.comName.getText());
						pstmt.setString(3, this.comEmail.getText());
						pstmt.setInt(4, Integer.parseInt(this.comZip.getText()));
						pstmt.setString(5, this.comAddr.getText());
						pstmt.setString(6, this.comTel.getText());
						pstmt.setString(7, id);
						pstmt.executeUpdate();
						
						DialogManager.createMsgDialog("수정되었습니다!", "\\img\\success1.png",
								"수정완료", JOptionPane.PLAIN_MESSAGE);
						
						setVisible(false);
						new ComMyPage();
						
						}
						else {
							newPassword.setText("비밀번호가 일치하지 않습니다.");
							newPassword.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
							newPassword.setForeground(Color.RED);
						}
				
					}
				
				}
			
		} catch (Exception e1) {
			e1.printStackTrace();
//		} finally {
//			dbManager.closeDB(pstmt);
//			dbManager.closeDB();
		}
	}
	
	
	public void showComInfo(String id) {
		Connection conn = dbManager.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT comNum, comName, comEmail, comZip, comAddr, comTel, login.pw "
				+ "FROM company "
				+ "JOIN login "
				+ "ON company.comNum = login.logComNum "
				+ "WHERE comNum = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				this.comNum.setText(rs.getString("comNum"));
				this.comName.setText(rs.getString("comName"));
				this.comEmail.setText(rs.getString("comEmail"));
				this.comZip.setText(rs.getString("comZip"));
				this.comAddr.setText(rs.getString("comAddr"));
				this.comTel.setText(rs.getString("comTel"));
				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
//		} finally {
//			dbManager.closeDB(pstmt, rs);
//			dbManager.closeDB();
		}
	}
}

