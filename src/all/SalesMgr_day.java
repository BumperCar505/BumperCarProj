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

// ComServiceList
public class SalesMgr_day extends JFrame {
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
	String header[] = {"Num", "직원명", "고객명", "서비스명", "건수", "총 금액", "내용","금액"};
	DefaultTableModel model = new DefaultTableModel(header, 0);
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void setFont() {
		InputStream inputStream = null;
		
		// Font Setting
		try {
            String classPath = SalesMgr_day.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
            inputStream = new BufferedInputStream(
                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            btnAddSalesD.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnEditSalesD.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnDelSalesD.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnBackSales.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
//            
//    		// Table Font
////            tableSalesD.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
//
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
//	
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
		setTitle("일일 매출관리페이지");
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
		scSalesDList.setBounds(100, 145, 1462, 462);
		scSalesDList.setVisible(true);
		getContentPane.setLayout(null); //이거 없으면 쪼그라든다.
		

		getContentPane.add(scSalesDList);//외곽 라인
		
		btnBackSales = new JButton("돌아가기");
		btnBackSales.setBounds(648, 635, Size.BTN_B_W, Size.BTN_B_H);
		getContentPane.add(btnBackSales);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1558, 145, 17, 463);
		getContentPane.add(scrollBar);
//		JFrame.getContentPanel().add(addPanel);
		
		
//   	 * 매개변수 : 레이블의 내용, 레이블의 아이콘, 다이얼로그 제목, 다이얼로그 옵션
//   	 * 반환값1 : -2(비정상적으로 작동했을때 반환하는 값)
//   	 * 반환값2 : -1(확인 버튼만 있는 다이얼로그 클릭시 반환값, 예 & 아니오 다이얼로그에서 X 버튼 클릭시 반환값)
//   	 * 반환값3 : 0(예, 아니오 다이얼로그에서 예 클릭시 반환값)
//   	 * 반환값4 : 1(예, 아니오 다이얼로그에서 아니오 클릭시 반환값)
//   	DialogManager.createMsgDialog("삭제하시겠습니까","\\img\\YellowCat.png", "삭제",JOptionPane.YES_NO_OPTION);
		  
	
		
		// 돌아가기 버튼 눌렀을 때 월 매출관리 페이지로 이동
		btnBackSales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new SalesMgr();

			}
		});
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		YuriSalesMgr_mgr mgr = new YuriSalesMgr_mgr();
		YuriSalesMgrBean bean = new YuriSalesMgrBean();
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "1234");
			
			// UnitStockMgr 메인화면 테이블 값 쿼리문
			// 재고는 stckQty2 (정비완료시 재고 빠진것 업데이트 된 열)
			sql = "SELECT tech.techName,srv.srvName, cus.cusName "
					+ "FROM maintenance  "
					+ "JOIN service srv "
					+ "ON mainSrvNum = srv.srvNum "
					+ "JOIN technician tech "
					+ "ON srv.srvTechNum = tech.techNum "
					+ "JOIN customer cus "
					+ "ON cus.cusNum = mainCusNum "
					+ "WHERE mainEndDay = ? and mainStatus='정비완료' " ;
	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "1112233333"); 
	
			rs = pstmt.executeQuery();
				while(rs.next()){         
	             model.addRow(new Object[]{rs.getString("stock.stckUnitNum"), rs.getString("unit.unitName"), rs.getString("unit.unitVendor"),rs.getString("sum(stock.stckQty2)")});
	            }
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		}
		
			
			
	}
}