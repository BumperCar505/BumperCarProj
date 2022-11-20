package all;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;

// ComLogin
public class ComLogin extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField comId;
	private JPasswordField comPw;
	private JButton btnComLogin;
	private JButton btnComJoin;
	private JLabel lblBackGround;

	public void setFont() {
		InputStream inputStream = null;
		
		// Font Setting
		try {
            String classPath = ComLogin.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
            path = path.split("all")[0];
            inputStream = new BufferedInputStream(
                    new FileInputStream(path + "font/NanumBarunGothic.ttf"));
            
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            comPw.setFont(font.deriveFont(Font.BOLD, 24));
            comId.setFont(font.deriveFont(Font.BOLD, 24));
            btnComLogin.setFont(font.deriveFont(Font.BOLD, 24));
            btnComJoin.setFont(font.deriveFont(Font.BOLD, 24));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(inputStream != null) {
        		try {
            		inputStream.close();
        		} catch(Exception e2) { 
        			e2.printStackTrace();
        		}
        	}
        }
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComLogin frame = new ComLogin();
					frame.setVisible(true);
					frame.setFont();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String loginDb(String id, String pwd) {
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT logComNum, pw FROM login "
					+ "WHERE seperator = ? AND logComNum = ? AND pw = ? ";
			con = mgr.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, "com");
			psmt.setString(2, id);
			psmt.setString(3, pwd);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String rsId = rs.getString("logComNum");
				String rsPwd = rs.getString("pw");
				
				if(rsId.equals(id) == true && rsPwd.equals(pwd) == true) {
					return id;
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(psmt != null) {psmt.close();}
				if(con != null) {con.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String id = null;
		
		if(obj == btnComLogin) {
			id = loginDb(comId.getText().trim(), String.valueOf(comPw.getPassword()));
			
			if(id != null) {
				LoginManager loginManager = LoginManager.getInstance();
				loginManager.login("com", id);
				DialogManager.createMsgDialog("로그인에 성공했습니다.<br>" + id + "님 환영합니다!", "\\img\\success1.png",
						"성공", JOptionPane.PLAIN_MESSAGE);
				new ComMyPage();
				this.dispose();
			} else {
				DialogManager.createMsgDialog("로그인에 실패했습니다.<br>아이디나 암호를 확인하세요.", 
						"\\img\\information5.png", "실패", JOptionPane.PLAIN_MESSAGE);
			}
		} else if (obj == btnComJoin) {
			new ComJoin().setVisible(true);
			this.dispose();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public ComLogin() {
		setTitle("다고쳐카센터 - 로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comId = new JTextField();
		comId.setBounds(714, 333, 328, 66);
		comId.setText("");
		comId.setHorizontalAlignment(SwingConstants.CENTER);
		comId.setColumns(
				10);
		comId.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		contentPane.add(comId);
		
		comPw = new JPasswordField();
		comPw.setBounds(714, 409, 328, 66);
		comPw.setText("");
		comPw.setHorizontalAlignment(SwingConstants.CENTER);
		comPw.setColumns(10);
		comPw.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		contentPane.add(comPw);
		
		btnComLogin = new JButton("로그인");
		btnComLogin.setBounds(714, 530, 328, 71);
		btnComLogin.setForeground(new Color(0, 0, 0));
		btnComLogin.setBackground(new Color(244, 204, 204));
		btnComLogin.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnComLogin.addActionListener(this);
		contentPane.add(btnComLogin);
		
		btnComJoin = new JButton("회원가입");
		btnComJoin.setBounds(714, 611, 328, 71);
		btnComJoin.setBackground(new Color(244, 204, 204));
		btnComJoin.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnComJoin.addActionListener(this);
		contentPane.add(btnComJoin);
		
		JLabel lblYellowCat = new JLabel("");
		lblYellowCat.setIcon(new ImageIcon(ComLogin.class.getResource("/img/YellowCat.png")));
		lblYellowCat.setBounds(714, 215, 230, 80);
		contentPane.add(lblYellowCat);
		
		JPanel panelComId = new JPanel();
		panelComId.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.control, new Color(240, 240, 240), new Color(240, 240, 240), new Color(240, 240, 240)));
		panelComId.setBounds(638, 333, 71, 66);
		contentPane.add(panelComId);
		panelComId.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ComLogin.class.getResource("/img/profile.png")));
		lblNewLabel.setBounds(3, 0, 71, 66);
		panelComId.add(lblNewLabel);
		
		JPanel panelComPwd = new JPanel();
		panelComPwd.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.control, SystemColor.control, SystemColor.control, SystemColor.control));
		panelComPwd.setBounds(638, 409, 71, 66);
		contentPane.add(panelComPwd);
		panelComPwd.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ComLogin.class.getResource("/img/lock.png")));
		lblNewLabel_1.setBounds(3, 0, 71, 66);
		panelComPwd.add(lblNewLabel_1);
		
		lblBackGround = new JLabel("");
		lblBackGround.setIcon(new ImageIcon(ComLogin.class.getResource("/img/Car.png")));
		lblBackGround.setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		contentPane.add(lblBackGround);
		
		this.setFont();
	}
}
