package all;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class UnitStockMgr extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tableUnitList;
	private JButton btnDelUnitStock;
	private JButton btnBackUnitStockMain;
	private LoginManager loginManager;
	private UnitStockMgr unitStockMgr;
	private JScrollPane scUnitList;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

//	private String header[] = {"stckNum", "부품번호","부품명","벤더", "재고수량"};
//	private DefaultTableModel model = new DefaultTableModel(header, 0);

	// Launch the application..
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UnitStockMgr frame = new UnitStockMgr();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	// Create the frame.
	public UnitStockMgr() {
		
		setVisible(true);
		setTitle("다고쳐카센터 - 부품재고관리");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		// 폼 창이 화면 가운데서 뜨게 하는 기능
		setLocationRelativeTo(null); //--
		setContentPane(contentPane);
		
		//테이블 생성
		tableUnitList = new JTable(model);
		
		Vector<String> columnHeaders = new Vector<>();
		columnHeaders.add("stckNum");
		columnHeaders.add("부품번호");
		columnHeaders.add("부품명");
		columnHeaders.add("벤더");
		columnHeaders.add("재고수량");
		
		HashMap<String, Integer> columnWidthValues = new HashMap<>();
		columnWidthValues.put("stckNum", 20);
		columnWidthValues.put("부품번호", 80);
		columnWidthValues.put("부품명", 75);
		columnWidthValues.put("벤더", 50);
		columnWidthValues.put("재고수량", 50);
		
		TableDesigner.setFont(tableUnitList, "NanumBarunGothic", 21);
		TableDesigner.setTableColumn(tableUnitList, columnHeaders);
		TableDesigner.setTableTextCenter(tableUnitList, columnHeaders);
		TableDesigner.resizeTableRow(tableUnitList, 50);
		TableDesigner.resizeTableColumn(tableUnitList, columnWidthValues);
		TableDesigner.resizeTableHeader(tableUnitList);

		
		scUnitList = new JScrollPane(tableUnitList);
		scUnitList.setEnabled(false);
		scUnitList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scUnitList.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		scUnitList.setBounds(500, 250, 800, 500);
		contentPane.setLayout(null);
		contentPane.add(scUnitList);
		
		JPanel jpList = new JPanel();		
	    jpList.setLayout(new GridBagLayout());			
		JScrollPane scrollSingle = new JScrollPane(jpList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSingle.setPreferredSize(new Dimension(400, 200));

		// 품목 추가 버튼
		JButton btnAddUnitStock = new JButton("품목 추가");
		btnAddUnitStock.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnAddUnitStock.setBounds(239, 174, Size.BTN_S_W, Size.BTN_S_H);
		btnAddUnitStock.setBackground(new Color(244, 204, 204));
		btnAddUnitStock.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnAddUnitStock);
		
		// 품목 삭제 버튼
		btnDelUnitStock = new JButton("품목 삭제");
		btnDelUnitStock.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnDelUnitStock.setBounds(563, 174, 150, 50);
		btnDelUnitStock.setBackground(new Color(244, 204, 204));
		btnDelUnitStock.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnDelUnitStock);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitStockMgr.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(704, 29, 255, 112);
		contentPane.add(lblNewLabel);
		
		// 돌아가기 버튼
		btnBackUnitStockMain = new JButton("돌아가기");
		btnBackUnitStockMain.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnBackUnitStockMain.setBounds(687, 824, Size.BTN_B_W, Size.BTN_B_H);
		btnBackUnitStockMain.setBackground(new Color(244, 204, 204));
		btnBackUnitStockMain.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnBackUnitStockMain);
		
		// 구매 이력 버튼
		JButton btnUnitBuyHistory = new JButton("구매 이력");
		btnUnitBuyHistory.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnUnitBuyHistory.setBounds(1275, 174, 150, 50);
		btnUnitBuyHistory.setBackground(new Color(244, 204, 204));
		btnUnitBuyHistory.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnUnitBuyHistory);
		
		// 입고 등록 버튼
		JButton btnAddUnitStockHistory = new JButton("입고 등록");
		btnAddUnitStockHistory.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnAddUnitStockHistory.setBounds(401, 174, 150, 50);
		btnAddUnitStockHistory.setBackground(new Color(244, 204, 204));
		btnAddUnitStockHistory.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnAddUnitStockHistory);
		
		
		getInfo();
		
		// 구매 이력 버튼 누르면 실행 됨 -> 새 폼 띄우기
		btnUnitBuyHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableUnitList.getSelectedRow();
				int column1 = 0;
				int column2 = 1;
				
				if(row == -1) {
					JOptionPane.showConfirmDialog(null, "셀을 선택하지 않으셨습니다.", "구매 이력", JOptionPane.DEFAULT_OPTION);
				}
				else {
					int stckNum = (int) tableUnitList.getValueAt(row, column1);
					String unitNum = (String) tableUnitList.getValueAt(row, column2);
					UnitBuyHistory history = new UnitBuyHistory(stckNum, unitNum);
					
					
					history.setVisible(true);
				}
			}
		});
		

		// 삭제 버튼 누르면 실행됨
				btnDelUnitStock.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int row = tableUnitList.getSelectedRow();
						int column = 1;
						String editIndex = (String) tableUnitList.getValueAt(row, column);
						
						if(row == -1){
				            JOptionPane.showConfirmDialog(null, "셀을 선택하지 않으셨습니다.", "삭제", JOptionPane.DEFAULT_OPTION);
				        }
						
				        else {
				        	try {
				        		int result = DialogManager.createMsgDialog("<html><h3>삭제하시겠습니까?</h3>", "/img/question6.png", "삭제", JOptionPane.YES_NO_OPTION);
		   
					            if (result == 0) {
					            	model.removeRow(row);
					            	
					            	GwakMemberMgr mgr = new GwakMemberMgr();
									GwakMemberBean bean =  new GwakMemberBean();
									
									// 사업자번호 : 임시방편
									bean.setStckUnitNum(editIndex);
					            	mgr.delete2(bean, id);
					            	
					            	DialogManager.createMsgDialog("<html><h3>삭제되었습니다.</h3>", "/img/success1.png", "삭제", JOptionPane.CLOSED_OPTION);
					            } else if (result == 1) {
					            	   
					            	}
					            } catch(Exception ex) {
					            	
					            }
				        	}
						}
					});
				
				
				// 품목 등록 버튼 누르면 실행됨 -> 현재 화면 닫고 메인화면 띄우기
				btnAddUnitStock.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 메인화면은 visible true, 현재화면은 false
						
						
						
						
						
						UnitStockMgr_addUnit history = new UnitStockMgr_addUnit(unitStockMgr);
						
						history.setVisible(true);
						
						// 현재 메인창 닫기(업데이트를 위해)
						//dispose();

					}
				});
				
				
				// 입고 추가 버튼 누르면 실행됨 -> 현재 화면 닫고 메인화면 띄우기
				btnAddUnitStockHistory.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 메인화면은 visible true, 현재화면은 false
						UnitStockMgr_addHistory history = new UnitStockMgr_addHistory();
						
						
						history.setVisible(true);
						
						// 현재 메인창 닫기(업데이트를 위해)
						dispose();

					}
				});
				
		
		// 돌아가기 버튼 누르면 실행됨 -> 현재 화면 닫고 메인화면 띄우기
		btnBackUnitStockMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						new ComMyPage();
						dispose();
				}		
		});

		unitStockMgr = this;
	}
	
	
	// GwakMemberMgr에서 호출 -> 테이블 값 채워넣기
	//GwakMemberMgr mgr = new GwakMemberMgr();
	//GwakMemberBean bean = mgr.SelectUnitMgrTable(tableUnitList, id);
	
	private void getInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();

			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,"root","1234");
				sql = "SELECT stock.stckNum, stock.stckUnitNum, unit.unitName, unit.unitVendor, sum(stock.stckQty2) "
						+ "FROM stock " 
						+ "inner join unit "
						+ "on stock.stckUnitNum = unit.unitNum "
						+ "WHERE stock.stckComNum = ? "
						+ "group by stock.stckUnitNum "
						+ "ORDER BY stock.stckUnitNum ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
	
				DefaultTableModel model = (DefaultTableModel)tableUnitList.getModel();
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt("stock.stckNum"),
							rs.getString("stock.stckUnitNum"),
							rs.getString("unit.unitName"),
							rs.getString("unit.unitVendor"),
							rs.getString("sum(stock.stckQty2)"),
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {

			}
		}
	
	public void requestSelectUnit(UnitStockMgr unitStockMgr) {
		if(unitStockMgr.equals(this)) {
			getInfo();

		}
	}
}
	
	




