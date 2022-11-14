package all;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JList;

public class UnitStockMgr_addUnit extends JFrame {

	private JPanel contentPane;
	private JTextField unitPrice;
	private JTextField unitVendor;
	private JButton btnUnitReg;
	private JLabel lblNewLabel;
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	

	/**
	 * Launch the application...
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UnitStockMgr_addUnit frame = new UnitStockMgr_addUnit();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public UnitStockMgr_addUnit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		폼 크기 : 600 * 500
		setBounds(100, 100, 592, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel unitNamelbl = new JLabel("부품명");
		unitNamelbl.setBounds(93, 191, 390, 47);
		unitNamelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitNamelbl);
		
		JLabel unitPricelbl = new JLabel("부품가격");
		unitPricelbl.setBounds(93, 309, 390, 47);
		unitPricelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitPricelbl);
		
		JLabel unitVendorlbl = new JLabel("벤더");
		unitVendorlbl.setBounds(93, 414, 390, 47);
		unitVendorlbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitVendorlbl);
		
		unitPrice = new JTextField();
		unitPrice.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		unitPrice.setEditable(false);
		unitPrice.setColumns(10);
		unitPrice.setBounds(93, 351, 390, 39);
		unitPrice.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(unitPrice);
		
		unitVendor = new JTextField();
		unitVendor.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		unitVendor.setEditable(false);
		unitVendor.setColumns(10);
		unitVendor.setBounds(93, 456, 390, 39);
		unitVendor.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(unitVendor);
		
		btnUnitReg = new JButton("등록");
		btnUnitReg.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnUnitReg.setBounds(143, 619, Size.BTN_B_W, Size.BTN_B_H);
		btnUnitReg.setBackground(new Color(244, 204, 204));
		btnUnitReg.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnUnitReg);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitStockMgr_addUnit.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(161, 54, 254, 80);
		contentPane.add(lblNewLabel);
		
		// 콤보박스
		JComboBox unitNameCmb = new JComboBox();
		unitNameCmb.setMaximumRowCount(10);
		unitNameCmb.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		unitNameCmb.setBounds(93, 238, 390, 39);
		unitNameCmb.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(unitNameCmb);
		
		
		try {
			con = DriverManager.getConnection(url, "root", "1234");
			String sql="SELECT unitName FROM unit WHERE unitNum LIKE 'p%' ";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
			String unitName = rs.getString("unitName");
			unitNameCmb.addItem(unitName);
			}
			
			}catch(SQLException e) {
				
			}
		
		
		
		

		
		
		/////////////////////////////////
		// 콤보박스 누르면 이벤트 발생
	      unitNameCmb.addActionListener(new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	                  JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
	                  cb.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
   
	                  	String item = cb.getSelectedItem().toString();
	                        
	                    GwakMemberMgr mgr = new GwakMemberMgr();
	                    GwakMemberBean bean = new GwakMemberBean();
	                    bean = mgr.Select5(item);
	                        
	                    String p = Integer.toString(bean.getUnitPrice());
	                        
	                    unitPrice = new JTextField(p);
	                    unitPrice.setColumns(10);
	                    unitPrice.setEditable(false);
	                    unitPrice.setBounds(93, 351, 390, 39);
	                    unitPrice.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
	                    unitPrice.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
	                    contentPane.add(unitPrice);
	                        
	                    unitVendor = new JTextField(bean.getUnitVendor());
	                    unitVendor.setColumns(10);
	                    unitVendor.setEditable(false);
	                    unitVendor.setBounds(93, 456, 390, 39);
	                    unitVendor.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
	                    unitVendor.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
	                    contentPane.add(unitVendor);            
	             }
	        });

		
		

		// 등록 완료 버튼
		btnUnitReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				GwakMemberMgr mgr = new GwakMemberMgr();
				GwakMemberBean bean =  new GwakMemberBean();

				
				// 임의설정 -> 나중에 값 받아오도록 변경
				bean.setStckComNum("1112233333");
				
				String Uname = unitNameCmb.getSelectedItem().toString();
				bean.setUnitName(Uname);

				mgr.addUnit(bean);
				
				UnitStockMgr uform = new UnitStockMgr();
				uform.setVisible(true);
				
				dispose();//--
				
			}
		});
		
		// 콤보박스 값 추가

	
		
		
		
	}
	

		
	
}
