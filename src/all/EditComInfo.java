package all;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

	private JPanel contentPane;
	private JTextField comNum;
	private JTextField comPw;
	private JTextField comPwCheck;
	private JTextField comName;
	private JTextField comEmail;
	private JTextField comZip;
	private JTextField comAddr;
	private JTextField comTel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditComInfo frame = new EditComInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditComInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		comNum = new JTextField();
		comNum.setFont(new Font("NanumBarunGothic", Font.PLAIN, 21));
		comNum.setEditable(false);
		comNum.setBounds(304, 76, 384, 42);
		panel.add(comNum);
		comNum.setColumns(10);
		
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
				UpdateComInfo();
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

	protected void UpdateComInfo() {
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
	}
}
