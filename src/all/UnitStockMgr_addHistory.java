package all;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import com.mysql.cj.xdevapi.Statement;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import java.awt.Color;
import java.awt.Cursor;

public class UnitStockMgr_addHistory extends JFrame {

	private JPanel contentPane;
	private JTextField unitQty1;
	private JFormattedTextField unitBuyDate;
	private JButton btnUnitReg;
	private JLabel lblNewLabel;
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private static final DateFormat df = new SimpleDateFormat("yyyy/mm/dd");


	/**
	 * Launch the application...
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitStockMgr_addHistory frame = new UnitStockMgr_addHistory();
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
	public UnitStockMgr_addHistory() {
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
		
		JLabel unitQtylbl = new JLabel("입고 수량");
		unitQtylbl.setBounds(93, 309, 390, 47);
		unitQtylbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitQtylbl);
		
		JLabel unitBuyDatelbl = new JLabel("구입 날짜");
		unitBuyDatelbl.setBounds(93, 414, 390, 47);
		unitBuyDatelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitBuyDatelbl);
		
		unitQty1 = new JTextField();
		unitQty1.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		unitQty1.setColumns(10);
		unitQty1.setBounds(93, 351, 390, 39);
		unitQty1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(unitQty1);
		
		
		try  {
			unitBuyDate = new JFormattedTextField();
			MaskFormatter ssnFormatter = new MaskFormatter("####-##-##");
			//JFormattedTextField unitBuyDate  = null; 
		    //ssnFormatter = new MaskFormatter("####-##-##");
		    unitBuyDate = new JFormattedTextField(ssnFormatter);
		    unitBuyDate.setToolTipText("YYYY-MM-DD 형식으로 입력하세요.");
		    unitBuyDate.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
			unitBuyDate.setColumns(10);
			unitBuyDate.setBounds(93, 456, 390, 39);
			unitBuyDate.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
			contentPane.add(unitBuyDate);
		}
		catch (ParseException e)  {
		    e.printStackTrace();
		}
		
		
		

		
		btnUnitReg = new JButton("등록");
		btnUnitReg.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnUnitReg.setBounds(143, 619, Size.BTN_B_W, Size.BTN_B_H);
		btnUnitReg.setBackground(new Color(244, 204, 204));
		btnUnitReg.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
	            Color.red, Color.red));
		contentPane.add(btnUnitReg);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitStockMgr_addHistory.class.getResource("/img/YellowCat.png")));
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
			String sql="SELECT distinct unit.unitName from unit "
					+ "inner join stock "
					+ "on stock.stckUnitNum = unit.unitNum "
					+ "WHERE stock.stckComNum = ? ";
			
			pstmt = con.prepareStatement(sql);
//			★★★★★★★★★★     pstmt.setString(1, bean.getStckComNum()); // 실제 -> 사업자번호 값 받아오기★★★★★★★★★★
			pstmt.setString(1, "1112233333"); // 테스트용
			rs = pstmt.executeQuery();
			
			while(rs.next()){
			String unitName = rs.getString("unitName");
			unitNameCmb.addItem(unitName);
			}
			
			}catch(SQLException e) {
				
			}
		
		/////////////////////////////////
		
		
		
		
		// 등록 완료 버튼
		btnUnitReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				GwakMemberMgr mgr = new GwakMemberMgr();
				GwakMemberBean bean =  new GwakMemberBean();

				// 임의설정 -> 나중에 값 받아오도록 변경
				bean.setStckComNum("1112233333");
				
				bean.setStckQty1(Integer.parseInt(unitQty1.getText()));
				bean.setStckQty2(Integer.parseInt(unitQty1.getText()));
				bean.setStckBuyDate(unitBuyDate.getText());
				

				String Uname = unitNameCmb.getSelectedItem().toString();
				bean.setUnitName(Uname);

				mgr.addHistoty(bean);
				
				UnitStockMgr uform = new UnitStockMgr();
				uform.setVisible(true);
				
				dispose();//--

				
			}
		});
		
		
		

		 


	}
}
