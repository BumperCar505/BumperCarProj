package all;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class test extends JFrame {

	private JPanel contentPane;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private GwakMemberBean comJoinInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					test frame = new test();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test(GwakMemberBean comJoinInfo) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		GwakMemberBean bean = new GwakMemberBean();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "1234");

			sql = "INSERT INTO company (comNum, comName, comEmail, comZip, comAddr, comTel, comDate) "
					+ "SELECT ?, ?, ?, ?, ?, ?, NOW() ";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, comJoinInfo.getComNum());
			pstmt.setString(2, comJoinInfo.getComName());
			pstmt.setString(3, comJoinInfo.getComEmail());
			pstmt.setString(4, comJoinInfo.getComZip());
			pstmt.setString(5, comJoinInfo.getComAddr());
			pstmt.setString(6, comJoinInfo.getComTel());

		
			pstmt.executeUpdate();
	
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		}
		
		
	}

}
