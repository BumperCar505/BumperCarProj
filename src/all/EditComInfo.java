package all;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import book.DBManager;

import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	
	private JPanel contentPane;
	private JTextField comNum;
	private JTextField comPw;
	private JTextField comPwCheck;
	private JTextField comName;
	private JTextField comEmail;
	private JTextField comZip;
	private JTextField comAddr;
	private JTextField comTel;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditComInfo frame = new EditComInfo();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EditComInfo() {
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
		panel.setBounds(438, 177, 803, 701);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("비밀번호");
		lblNewLabel_1_1.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_1.setBounds(158, 139, 136, 41);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("비밀번호 재확인");
		lblNewLabel_1_2.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_2.setBounds(132, 204, 162, 41);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("업체명");
		lblNewLabel_1_3.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_3.setBounds(158, 267, 136, 41);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_5 = new JLabel("우편번호");
		lblNewLabel_1_5.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_5.setBounds(158, 395, 136, 41);
		panel.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("주소");
		lblNewLabel_1_6.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_6.setBounds(158, 460, 136, 41);
		panel.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("전화번호");
		lblNewLabel_1_7.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_7.setBounds(158, 524, 136, 41);
		panel.add(lblNewLabel_1_7);
		
		this.comNum = new JTextField();
		this.comNum.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		this.comNum.setEditable(false);
		this.comNum.setBounds(304, 76, 384, 42);
		panel.add(this.comNum);
		
		comPw = new JTextField();
		comPw.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comPw.setColumns(10);
		comPw.setBounds(304, 140, 384, 42);
		panel.add(comPw);
		
		comPwCheck = new JTextField();
		comPwCheck.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comPwCheck.setColumns(10);
		comPwCheck.setBounds(304, 204, 384, 42);
		panel.add(comPwCheck);
		
		comName = new JTextField();
		comName.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comName.setColumns(10);
		comName.setBounds(304, 268, 384, 42);
		panel.add(comName);
		
		comEmail = new JTextField();
		comEmail.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comEmail.setColumns(10);
		comEmail.setBounds(304, 332, 384, 42);
		panel.add(comEmail);
		
		comZip = new JTextField();
		comZip.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comZip.setColumns(10);
		comZip.setBounds(304, 396, 169, 42);
		panel.add(comZip);
		
		comAddr = new JTextField();
		comAddr.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comAddr.setColumns(10);
		comAddr.setBounds(304, 460, 384, 42);
		panel.add(comAddr);
		
		comTel = new JTextField();
		comTel.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comTel.setColumns(10);
		comTel.setBounds(304, 524, 384, 42);
		panel.add(comTel);
		
		JButton btnFixedComInfo = new JButton("수정 완료");
		btnFixedComInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditComInfo editComInfo = new EditComInfo();
				editComInfo.updateComInfo("1112233333");
			}
		});
		btnFixedComInfo.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		btnFixedComInfo.setBounds(281, 622, Size.BTN_B_W, Size.BTN_B_H);
		panel.add(btnFixedComInfo);
		
		JLabel lblNewLabel_1_4 = new JLabel("사업자등록번호");
		lblNewLabel_1_4.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_4.setBounds(128, 76, 166, 41);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("이메일");
		lblNewLabel_1_3_1.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		lblNewLabel_1_3_1.setBounds(158, 332, 136, 41);
		panel.add(lblNewLabel_1_3_1);
	}

	public void updateComInfo(String comNum) {
//		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
//		Connection conn = null;
		Connection conn = dbManager.getConn();
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		
		String sql = "UPDATE company, login SET login.pw = ?, comName = ?, comEmail = ?, comZip = ?, comAddr = ?, comTel = ? "
				+ "WHERE company.comNum = login.logComNum AND comNum = ? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, this.comPw.getText());
			pstmt.setString(2, this.comName.getText());
			pstmt.setString(3, this.comEmail.getText());
			pstmt.setString(4, this.comZip.getText());
			pstmt.setString(5, this.comAddr.getText());
			pstmt.setString(6, this.comTel.getText());
			pstmt.setString(7, comNum);
			pstmt.executeUpdate();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			dbManager.closeDB(pstmt);
			dbManager.closeDB();
		}
	}
	
	
	public void showComInfo(String comNum) {
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
			pstmt.setString(1, comNum);
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
		} finally {
			dbManager.closeDB(pstmt, rs);
			dbManager.closeDB();
		}
	}
	
	
}
