import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ComJoin extends JFrame {
	

	private JPanel contentPane;
	private JTextField comNum;
	private JTextField comName;
	private JTextField comEmail;
	private JTextField comZip;
	private JTextField comAddr;
	private JTextField comTel;
	private JPasswordField comPw;
	private JPasswordField comPwCheck;


	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComJoin frame = new ComJoin();
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
	public ComJoin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel comNumlb = new JLabel("사업자 등록번호");
		comNumlb.setBounds(622, 140, 210, 47);
		comNumlb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comNumlb);
		
		JLabel comPwlb = new JLabel("비밀번호");
		comPwlb.setBounds(622, 230, 218, 47);
		comPwlb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comPwlb);
		
		JLabel comPwChecklb = new JLabel("비밀번호 재확인");
		comPwChecklb.setBounds(622, 320, 218, 47);
		comPwChecklb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comPwChecklb);
		
		JLabel comNamelb = new JLabel("업체명");
		comNamelb.setBounds(622, 410, 218, 47);
		comNamelb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comNamelb);
		
		JLabel comEmaillb = new JLabel("이메일");
		comEmaillb.setBounds(622, 500, 218, 47);
		comEmaillb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comEmaillb);
		
		JLabel comZiplb = new JLabel("우편번호");
		comZiplb.setBounds(622, 590, 218, 47);
		comZiplb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comZiplb);
		
		JLabel comAddrlb = new JLabel("주소");
		comAddrlb.setBounds(622, 680, 218, 47);
		comAddrlb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comAddrlb);
		
		JLabel comTellb = new JLabel("전화번호");
		comTellb.setBounds(622, 770, 218, 47);
		comTellb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(comTellb);
		
		JButton btnComNext = new JButton("다음");
		btnComNext.setBounds(695, 913, 290, 65);
		btnComNext.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		contentPane.add(btnComNext);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(695, 10, 256, 129);
		lblNewLabel.setIcon(new ImageIcon(ComJoin.class.getResource("/img/YellowCat.png")));
		contentPane.add(lblNewLabel);
		
		comNum = new JTextField();
		comNum.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comNum.setBounds(622, 183, 444, 47);
		contentPane.add(comNum);
		comNum.setColumns(10);
		
		comName = new JTextField();
		comName.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comName.setBounds(622, 453, 444, 47);
		comName.setColumns(10);
		contentPane.add(comName);
		
		comEmail = new JTextField();
		comEmail.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comEmail.setBounds(622, 543, 444, 47);
		comEmail.setColumns(10);
		contentPane.add(comEmail);
		
		comZip = new JTextField();
		comZip.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comZip.setBounds(622, 633, 444, 47);
		comZip.setColumns(10);
		contentPane.add(comZip);
		
		comAddr = new JTextField();
		comAddr.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comAddr.setBounds(622, 723, 444, 47);
		comAddr.setColumns(10);
		contentPane.add(comAddr);
		
		comTel = new JTextField();
		comTel.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comTel.setBounds(622, 813, 444, 47);
		comTel.setColumns(10);
		contentPane.add(comTel);
		
		comPw = new JPasswordField();
		comPw.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comPw.setBounds(622, 273, 444, 47);
		contentPane.add(comPw);
		
		comPwCheck = new JPasswordField();
		comPwCheck.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		comPwCheck.setBounds(622, 363, 444, 47);
		contentPane.add(comPwCheck);
		
//		폼 창이 화면 가운데서 뜨게 하는 기능
		setLocationRelativeTo(null);
		
	}
}
