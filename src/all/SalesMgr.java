package all;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.modelmbean.ModelMBean;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Button;

// ComServiceList
public class SalesMgr extends JFrame {
	private JPanel getContentPane;
	private JTable tableSalesD;
	private JTable table;
	private JScrollPane scSalesDList;
	private JButton btnAddSalesD;
	private JButton btnEditSalesD;
	private JButton btnDelSalesD;
	private JButton btnBackSales;
	private JLabel lblYellowCat;
	private final int FONT_SIZE = 21;
	String header[] = {"날짜","직원명", "고객명", "서비스명","부품명" ,"금액"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private JTextField year;
	private JTextField month;
	private LoginManager loginManager;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesMgr frame = new SalesMgr();
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
	
	
	
	
	public SalesMgr() {
		setVisible(true);
		loginManager = loginManager.getInstance();
	    String id = loginManager.getLogComNum();
		setTitle("매출관리페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		getContentPane = new JPanel();
		getContentPane.setEnabled(false); //수정불가하게
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(getContentPane);
//		TextField tf = new TextField();
		
//		테이블
		
		JTable table = new JTable(model);
		table.setFont(new Font("나눔바른고딕", Font.PLAIN,20));
		
		
		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(239, 236, 1186, 533);
		scrollpane.setAutoscrolls(true);

		
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setRowHeight(40);
		scrollpane.setLayout(null);
		
	
	
		scSalesDList = new JScrollPane(table);
		scSalesDList.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		scSalesDList.setBounds(100, 145, 1462, 630);
		scSalesDList.setVisible(true);
		getContentPane.setLayout(null);
		

		getContentPane.add(scSalesDList);//외곽 라인
		
		btnBackSales = new JButton("돌아가기");
		btnBackSales.setBounds(650, 817, Size.BTN_B_W, Size.BTN_B_H);
		getContentPane.add(btnBackSales);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1558, 145, 17, 630);
		getContentPane.add(scrollBar);
		
		year = new JTextField();
		year.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		year.setBounds(1227, 102, 106, 33);
		getContentPane.add(year);
		year.setColumns(10);
		
		month = new JTextField();
		month.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		month.setColumns(10);
		month.setBounds(1362, 102, 76, 33);
		getContentPane.add(month);
		
		JLabel lblNewLabel = new JLabel("년");
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(1335, 105, 29, 24);
		getContentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("월");
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(1442, 105, 29, 24);
		getContentPane.add(lblNewLabel_1);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		btnSearch.setBounds(1472, 102, 87, 33);
		getContentPane.add(btnSearch);
		
		JLabel lblNewLabel_2 = new JLabel("매출관리페이지");
		lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.BOLD, 19));
		lblNewLabel_2.setBounds(100, 102, 132, 33);
		getContentPane.add(lblNewLabel_2);
		
		JButton btnGoNext = new JButton("비용");
		btnGoNext.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		btnGoNext.setBounds(236, 109, 76, 23);
		getContentPane.add(btnGoNext);
		
		
		
		
		btnGoNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new SalesMgr_day();

			}
		});
		
	
		
		// 돌아가기 버튼 눌렀을 때 월 매출관리 페이지로 이동
		btnBackSales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new ComMyPage();

			}
		});
		
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String boxYear = year.getText();
				String boxMonth = month.getText();
				String boxTotalDay = boxYear + "-" + boxMonth ;
				

			Connection con = null;
			PreparedStatement pstmt, pstmt2 = null;
			ResultSet rs , rs2 = null;
			String sql, sql2 = null;
			YuriSalesMgr_mgr mgr = new YuriSalesMgr_mgr();
			YuriSalesMgrBean bean = new YuriSalesMgrBean();
			System.out.println(boxTotalDay);
	
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(url, "root", "1234");
			
	
			sql = "SELECT mainEndDay, tech.techName, srv.srvName, cus.cusName, un.unitPrice, un.unitName "
					+ "FROM maintenance   "
					+ "	JOIN service srv  "
					+ "	ON mainSrvNum = srv.srvNum  "
					+ "	JOIN technician tech  "
					+ "	ON srv.srvTechNum = tech.techNum  "
					+ "	JOIN customer cus "
					+ "	ON cus.cusNum = mainCusNum "
					+ "	JOIN detail dtl "
					+ "	ON dtl.dtlSrvNum = srv.srvNum "
					+ "	JOIN unit un "
					+ "	ON un.unitNum = dtl.dtlUnitNum "
					+ " WHERE DATE_FORMAT(mainEndDay,'%Y-%m') = DATE_FORMAT(now(),?) and mainStatus='정비완료' AND tech.techComNum='1112233333' AND un.unitNum LIKE 'p%' " ;

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boxTotalDay); 
//			pstmt.setString(2, id); 

		
	
			rs = pstmt.executeQuery();
				while(rs.next()){         
	             model.addRow(new Object[]{
	            		 rs.getString("mainEndDay"), 
	            		 rs.getString("tech.techName"), 
	            		 rs.getString("cus.cusName"),
	            		 rs.getString("srv.srvName"), 
	            		 rs.getString("un.unitName"),
	            		 rs.getInt("un.unitPrice")

	             	});
	            }
				
			} catch (SQLException eq) {
				eq.printStackTrace();
			} finally {
				
		}
	}
	});
		
			
			
	}
}