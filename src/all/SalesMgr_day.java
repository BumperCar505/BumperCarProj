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
public class SalesMgr_day extends JFrame {
	private JPanel getContentPane;
	private JTable tableSalesDay;
	private JTable table;
	private JScrollPane scSalesDList;
	private JButton btnAddSalesD;
	private JButton btnEditSalesD;
	private JButton btnDelSalesD;
	private JButton btnBackSales;
	private JLabel lblYellowCat;
	private JLabel lblBackGround;
	private final int FONT_SIZE = 21;
//	String header[] = {"날짜","부품번호", "부품이름", "수량","부품단가" ,"총비용"};
//	DefaultTableModel model = new DefaultTableModel(header, 0);
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
					SalesMgr_day frame = new SalesMgr_day();
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
	
	
	
	
	public SalesMgr_day() {
		
		setVisible(true);
		setTitle("다고쳐카센터 - 비용관련페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		loginManager = loginManager.getInstance();
	    String id = loginManager.getLogComNum();
		

		
		getContentPane = new JPanel();
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(getContentPane);
		TextField tf = new TextField();
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		
		tableSalesDay = new JTable(model);
		tableSalesDay.setBounds(1, 1, 1443, 0);
		tableSalesDay.setAutoCreateRowSorter(true);
		tableSalesDay.setRowMargin(4);
		tableSalesDay.setRowHeight(30);
		tableSalesDay.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		tableSalesDay.setDefaultEditor(Object.class, null); // 테이블 값 수정 안되게
		tableSalesDay.getTableHeader().setResizingAllowed(false);
		tableSalesDay.getTableHeader().setReorderingAllowed(false);
		
		Vector<String> columnHeaders = new Vector<>();
		columnHeaders.add("날짜");
		columnHeaders.add("부품번호");
		columnHeaders.add("부품명");
		columnHeaders.add("수량");
		columnHeaders.add("부품단가");
		columnHeaders.add("총비용");
		
		HashMap<String, Integer> columnWidthValues = new HashMap<>();
		columnWidthValues.put("날짜", 90);
		columnWidthValues.put("부품번호", 80);
		columnWidthValues.put("부품명", 75);
		columnWidthValues.put("수량", 50);
		columnWidthValues.put("부품단가", 50);
		columnWidthValues.put("총비용", 80);
		
		
		TableDesigner.setFont(tableSalesDay, "NanumBarunGothic", FONT_SIZE);
		TableDesigner.setTableColumn(tableSalesDay, columnHeaders);
		TableDesigner.setTableTextCenter(tableSalesDay, columnHeaders);
		TableDesigner.resizeTableRow(tableSalesDay, 50);
		TableDesigner.resizeTableColumn(tableSalesDay, columnWidthValues);
		TableDesigner.resizeTableHeader(tableSalesDay);
		
		JScrollPane scrollpane = new JScrollPane(table);
		getContentPane.add(tableSalesDay);
		JLabel lblRegTec = new JLabel("");
		lblRegTec.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		
		btnBackSales = new JButton("돌아가기");
		btnBackSales.setBounds(650, 931, 290, 65);
		getContentPane.add(btnBackSales);
		btnBackSales.setBackground(new Color(244, 204, 204));
		btnBackSales.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnBackSales.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		
		
		
	
	
		
		// 돌아가기 버튼 눌렀을 때 월 매출관리 페이지로 이동
		btnBackSales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SalesMgr();
				dispose();

			}
		});
		getContentPane.setLayout(null);
	
		
		scSalesDList = new JScrollPane(tableSalesDay);
		scSalesDList.setBounds(100, 171, 1462, 750);
		scSalesDList.setEnabled(false);
		scSalesDList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scSalesDList.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		
		getContentPane.add(scSalesDList);
//		TextField tf = new TextField();
	
	
	
//		scSalesDList = new JScrollPane(table);
//		scSalesDList.setBounds(100, 145, 1462, 630);
//		scSalesDList.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
//		scSalesDList.setVisible(true);
//		getContentPane.setLayout(null);
		

		getContentPane.add(scSalesDList);//외곽 라인
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);
		
		year = new JTextField();
		year.setBounds(1219, 102, 106, 33);
		year.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		getContentPane.add(year);
		year.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("년");
		lblNewLabel.setBounds(1327, 105, 29, 24);
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		getContentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("월");
		lblNewLabel_1.setBounds(1434, 105, 29, 24);
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 16));
		getContentPane.add(lblNewLabel_1);
		
		month = new JTextField();
		month.setBounds(1354, 102, 76, 33);
		month.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		month.setColumns(10);
		getContentPane.add(month);
		
		lblBackGround = new JLabel("");
	      lblBackGround.setIcon(new ImageIcon(ComLogin.class.getResource("/img/Car2.jpg")));
	      lblBackGround.setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
	      getContentPane.add(lblBackGround);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(1475, 101, 87, 33);
		btnSearch.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		getContentPane.add(btnSearch);
		btnSearch.setBackground(new Color(244, 204, 204));
		btnSearch.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnSearch.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		
		JLabel lblNewLabel_2 = new JLabel("비용관리페이지");
		lblNewLabel_2.setBounds(104, 102, 146, 28);
		lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		getContentPane.add(lblNewLabel_2);
		
		
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
			
	
			sql = "SELECT st.stckBuyDate,st.stckUnitNum , un.unitName, st.stckQty1, un.unitPrice , st.stckQty1*un.unitPrice "
					+ "FROM stock st "
					+ "JOIN unit un "
					+ "ON st.stckUnitNum = un.unitNum "
					+ "WHERE DATE_FORMAT(st.stckBuyDate,'%Y-%m') = DATE_FORMAT(now(),?) and un.unitNum LIKE 'p%' and stckComNum = ?  " ;

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boxTotalDay); 
			pstmt.setString(2, id); 
			rs = pstmt.executeQuery();
		
			DefaultTableModel model = (DefaultTableModel)tableSalesDay.getModel();
			
				while(rs.next()){         
	             model.addRow(new Object[]{
	            		 rs.getString("st.stckBuyDate"), 
	            		 rs.getString("st.stckUnitNum"), 
	            		 rs.getString("un.unitName"),
	            		 rs.getInt("st.stckQty1"), 
	            		 rs.getInt("un.unitPrice"),
	            		 rs.getInt("st.stckQty1*un.unitPrice")

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