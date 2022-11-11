package all;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class UnitStockMgr extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnDelUnitStock;
	private JButton btnBackUnitStockMain;
	
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String header[] = {"부품번호","부품명","벤더", "재고수량"};  // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(header, 0);

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitStockMgr frame = new UnitStockMgr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}


	// Create the frame.
	public UnitStockMgr() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Select3();

		// 폼 창이 화면 가운데서 뜨게 하는 기능
		setLocationRelativeTo(null); //--
		setContentPane(contentPane);
		
		//테이블 생성		
		table = new JTable(model);
		
		table.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));

		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(3).setResizable(false);
		contentPane.setLayout(null);

		table.setBounds(247, 231, 1170, 671);
		
//		테이블에 열 제목 나오게 하는 코드. 참고 : https://yyman.tistory.com/550
		JScrollPane scrollPane = new JScrollPane(table); //
		
		scrollPane.setBounds(239, 236, 1186, 533);
		scrollPane.setAutoscrolls(true);
		contentPane.add (scrollPane) ;
		
//		테이블 행 높이 조절
		table.setRowHeight(40);

		
		JButton btnAddUnitStock = new JButton("부품 입고");
		btnAddUnitStock.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnAddUnitStock.setBounds(239, 174, Size.BTN_S_W, Size.BTN_S_H);
		contentPane.add(btnAddUnitStock);
		
		btnDelUnitStock = new JButton("부품 삭제");
		btnDelUnitStock.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnDelUnitStock.setBounds(401, 174, 150, 50);
		contentPane.add(btnDelUnitStock);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitStockMgr.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(704, 29, 255, 112);
		contentPane.add(lblNewLabel);
		
		// 돌아가기 버튼
		btnBackUnitStockMain = new JButton("돌아가기");
		btnBackUnitStockMain.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		btnBackUnitStockMain.setBounds(687, 824, Size.BTN_B_W, Size.BTN_B_H);
		contentPane.add(btnBackUnitStockMain);
		
		// 구매 이력 버튼
		JButton btnUnitBuyHistory = new JButton("구매 이력");
		btnUnitBuyHistory.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnUnitBuyHistory.setBounds(1275, 174, 150, 50);
		contentPane.add(btnUnitBuyHistory);
		
		
		// 구매 이력 버튼 누르면 실행 됨 -> 새 폼 띄우기
		btnUnitBuyHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String unitName = "a";
				
				UnitBuyHistory history = new UnitBuyHistory(unitName);
				history.setVisible(true);
			}
		});
		
		
		//추가 버튼 누르면 실행됨 -> 새 폼 띄우기
		btnAddUnitStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnitStockMgr_add add = new UnitStockMgr_add();
				add.setVisible(true);
			}
		});
		
		// 삭제 버튼 누르면 실행됨
				btnDelUnitStock.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int row = table.getSelectedRow();
						int column = 0;
						String editIndex = (String) table.getValueAt(row, column);
						
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
									bean.setStckComNum("1112233333");
									bean.setStckUnitNum(editIndex);
					            	mgr.delete2(bean);
					            	
					            	DialogManager.createMsgDialog("<html><h3>삭제되었습니다.</h3>", "/img/success1.png", "삭제", JOptionPane.CLOSED_OPTION);
					            } else if (result == 1) {
					            	   
					            	}
					            } catch(Exception ex) {
					            	
					            }
				        	}
						}
					});
		
		// 돌아가기 버튼 누르면 실행됨 -> 현재 화면 닫고 메인화면 띄우기
		btnBackUnitStockMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 메인화면은 visible true, 현재화면은 false

			}
		});
		
	}


	//  : DB에서 데이터 불러와서 테이블 채우기
			private void Select3(){
					
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				GwakMemberBean bean = new GwakMemberBean();

				try {
					Class.forName(driver);
					con = DriverManager.getConnection(url, "root", "1234");
					
					// UnitStockMgr 메인화면 테이블 값 쿼리문
					// 재고는 stckQty2 (정비완료시 재고 빠진것 업데이트 된 열)
					sql = "SELECT stock.stckUnitNum, unit.unitName, unit.unitVendor, sum(stock.stckQty2) "
							+ "FROM stock " 
							+ "inner join unit "
							+ "on stock.stckUnitNum = unit.unitNum "
							+ "WHERE stock.stckComNum = ? "
							+ "group by stock.stckUnitNum ";

					pstmt = con.prepareStatement(sql);
//	★★★★★★★★★★     pstmt.setString(1, bean.getStckComNum()); // 실제 -> 사업자번호 값 받아오기★★★★★★★★★★
					pstmt.setString(1, "1112233333"); // 테스트용
			
					rs = pstmt.executeQuery();
						while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
		                 model.addRow(new Object[]{rs.getString("stock.stckUnitNum"), rs.getString("unit.unitName"), rs.getString("unit.unitVendor"),rs.getString("sum(stock.stckQty2)")});
		                }
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
				}
				
				
				
					
					
				}
				
				
				
				
			}
	
	




