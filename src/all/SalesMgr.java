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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

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
import javax.swing.ScrollPaneConstants;
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
	private JScrollPane scSalesDList_1;
	private JButton btnBackSales;
	private JLabel lblYellowCat;
	private final int FONT_SIZE = 21;
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private JTextField year;
	private JTextField month;
	private LoginManager loginManager;
	private JLabel lblBackGround;
	
	

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesMgr frame = new SalesMgr();
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
	
	
	
	
	public SalesMgr() {
//		getSales();
		setVisible(true);
		setTitle("다고쳐카센터 - 고객관리페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		loginManager = loginManager.getInstance();
	    String id = loginManager.getLogComNum();
	    



		
		getContentPane = new JPanel();
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		//비용버튼
		setContentPane(getContentPane);
		TextField tf = new TextField();
		JButton btnGoNext = new JButton("비용");
		btnGoNext.setBounds(240, 94, 106, 41);
		btnGoNext.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		getContentPane.add(btnGoNext);
		btnGoNext.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
	
		btnGoNext.setBackground(new Color(244, 204, 204));
		
//		돌아가기버튼
//		JButton btnBackSalesMain = new JButton("돌아가기");
//		btnBackSalesMain.setBounds(650, 922, 290, 65);
//		btnBackSalesMain.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				new ComMyPage();
//			}
//		});
		btnBackSales = new JButton("돌아가기");
		btnBackSales.setBounds(650, 817, 290, 65);
		getContentPane.add(btnBackSales);
		btnBackSales.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		
		btnBackSales.setBackground(new Color(244, 204, 204));
		getContentPane.add(btnBackSales);
		btnBackSales.setBackground(new Color(244, 204, 204));
		btnBackSales.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnBackSales.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		
		tableSalesD = new JTable(model);
		tableSalesD.setBounds(1, 1, 1443, 0);
		tableSalesD.setAutoCreateRowSorter(true);
		tableSalesD.setRowMargin(4);
		tableSalesD.setRowHeight(30);
		tableSalesD.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		tableSalesD.setDefaultEditor(Object.class, null); // 테이블 값 수정 안되게
		tableSalesD.getTableHeader().setResizingAllowed(false);
		tableSalesD.getTableHeader().setReorderingAllowed(false);
		
		Vector<String> columnHeaders = new Vector<>();
		columnHeaders.add("날짜");
		columnHeaders.add("정비사명");
		columnHeaders.add("고객명");
		columnHeaders.add("서비스명");
		columnHeaders.add("부품명");
		columnHeaders.add("가격");
		
		HashMap<String, Integer> columnWidthValues = new HashMap<>();
		columnWidthValues.put("날짜", 90);
		columnWidthValues.put("정비사명", 80);
		columnWidthValues.put("고객명", 75);
		columnWidthValues.put("서비스명", 50);
		columnWidthValues.put("부품명", 50);
		columnWidthValues.put("가격", 80);
		
		
		TableDesigner.setFont(tableSalesD, "NanumBarunGothic", FONT_SIZE);
		TableDesigner.setTableColumn(tableSalesD, columnHeaders);
		TableDesigner.setTableTextCenter(tableSalesD, columnHeaders);
		TableDesigner.resizeTableRow(tableSalesD, 50);
		TableDesigner.resizeTableColumn(tableSalesD, columnWidthValues);
		TableDesigner.resizeTableHeader(tableSalesD);
		
		JScrollPane scrollpane = new JScrollPane(table);
		getContentPane.add(tableSalesD);
		JLabel lblRegTec = new JLabel("");
		lblRegTec.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		getContentPane.setLayout(null);
		
		scSalesDList = new JScrollPane(tableSalesD);
		scSalesDList.setBounds(100, 145, 1462, 750);
		scSalesDList.setEnabled(false);
		scSalesDList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scSalesDList.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		
		getContentPane.add(scSalesDList);
//		TextField tf = new TextField();
	
	
	
		scSalesDList_1 = new JScrollPane(table);
		scSalesDList_1.setBounds(100, 145, 1462, 630);
		scSalesDList_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		scSalesDList_1.setVisible(true);
		getContentPane.setLayout(null);
		

		getContentPane.add(scSalesDList_1);//외곽 라인
		
		
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);
		
		year = new JTextField();
		year.setBounds(1227, 102, 106, 33);
		year.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		getContentPane.add(year);
		year.setColumns(10);
		
		month = new JTextField();
		month.setBounds(1362, 102, 76, 33);
		month.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		month.setColumns(10);
		getContentPane.add(month);
		
		JLabel lblNewLabel = new JLabel("년");
		lblNewLabel.setBounds(1335, 105, 29, 24);
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		getContentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("월");
		lblNewLabel_1.setBounds(1442, 105, 29, 24);
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		getContentPane.add(lblNewLabel_1);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(1472, 102, 87, 33);
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		getContentPane.add(btnSearch);
		btnSearch.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnSearch.setBackground(new Color(244, 204, 204));
		
		JLabel lblNewLabel_2 = new JLabel("매출관리페이지");
		lblNewLabel_2.setBounds(100, 102, 132, 33);
		lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.BOLD, 19));
		getContentPane.add(lblNewLabel_2);
		
		
		lblBackGround = new JLabel("");
	    lblBackGround.setIcon(new ImageIcon(ComLogin.class.getResource("/img/Car2.jpg")));
	    lblBackGround.setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
	    getContentPane.add(lblBackGround);

		

	
		
		
		
	
		
		
		// 비용관리페이지로 넘어가게
		btnGoNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SalesMgr_day();

			}
		});
		
	
		
		// 돌아가기 버튼 눌렀을 때 월 매출관리 페이지로 이동
		btnBackSales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
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
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			String sql = null;
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
//			String header[] = {"날짜","정비사명","서비스명","고객명","부품비","부품명"}; 
//			DefaultTableModel model = new DefaultTableModel(header, 0);
//			table.setModel(model);
	
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
					+ " WHERE DATE_FORMAT(mainEndDay,'%Y-%m') = DATE_FORMAT(now(),?) and mainStatus='정비완료' AND tech.techComNum=? AND un.unitNum LIKE 'p%' " ;

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boxTotalDay); 
			pstmt.setString(2, id); 

		
			DefaultTableModel dtm = (DefaultTableModel)tableSalesD.getModel();
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
	
	//들어가면 현재 년 월에 맞는 값 나오게
//			private void getSales() {
//				Connection conn = null;
//				PreparedStatement pstmt = null;
//				ResultSet rs = null;
//				String sql = "";
//				
//				loginManager = loginManager.getInstance();
//			    String id = loginManager.getLogComNum();
//			    
//				YuriSalesMgr_mgr mgr = new YuriSalesMgr_mgr();
//				YuriSalesMgrBean bean = new YuriSalesMgrBean();
////			    
////			    String header[] = {"techNum","정비사 이름","전화번호","직급","aa","bb"}; 
////		        DefaultTableModel model = new DefaultTableModel(header, 0);
////		        table.setModel(model);
//			    
////			    현재 년도, 월 구하기
//			    Date date = new Date();
//			    String sDate = "%tY";
//			    String nowYear = String.format(sDate, date);
//			  
//			    
//			    sDate = "%tm";
//				String nowMonth = String.format(sDate, date);
//				
//				String nowDate = nowYear + "-" + nowMonth;
//		
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url,"root","1234");
//			sql = sql = "SELECT mainEndDay, tech.techName, srv.srvName, cus.cusName, un.unitPrice, un.unitName "
//					+ "FROM maintenance   "
//					+ "	JOIN service srv  "
//					+ "	ON mainSrvNum = srv.srvNum  "
//					+ "	JOIN technician tech  "
//					+ "	ON srv.srvTechNum = tech.techNum  "
//					+ "	JOIN customer cus "
//					+ "	ON cus.cusNum = mainCusNum "
//					+ "	JOIN detail dtl "
//					+ "	ON dtl.dtlSrvNum = srv.srvNum "
//					+ "	JOIN unit un "
//					+ "	ON un.unitNum = dtl.dtlUnitNum "
//					+ " WHERE DATE_FORMAT(mainEndDay,'%Y-%m') = DATE_FORMAT(now(),?) and mainStatus='정비완료' AND tech.techComNum=? AND un.unitNum LIKE 'p%' " ;
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, nowDate); 
//			pstmt.setString(2, id); 
//
//		
//			DefaultTableModel model = (DefaultTableModel)tableSalesD.getModel();
////			String header[] = {"techNum","정비사 이름","전화번호","직급","a","b"}; 
////	        DefaultTableModel model = new DefaultTableModel(header, 0);
//	        table.setModel(model);
//			rs = pstmt.executeQuery();
//				while(rs.next()){         
//					model.addRow(new Object[]{
//	            		 rs.getString("mainEndDay"), 
//	            		 rs.getString("tech.techName"), 
//	            		 rs.getString("cus.cusName"),
//	            		 rs.getString("srv.srvName"), 
//	            		 rs.getString("un.unitName"),
//	            		 rs.getInt("un.unitPrice")
//	             	});
//	            }
//				
//			} catch (SQLException eq) {
//				eq.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} finally {
//				
//			}
		
//		}
}