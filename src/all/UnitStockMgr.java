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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
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
	private LoginManager loginManager;
	private UnitStockMgr unitStockMgr;
	

	private String header[] = {"stckNum", "부품번호","부품명","벤더", "재고수량"};
	private DefaultTableModel model = new DefaultTableModel(header, 0);

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
		
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// GwakMemberMgr에서 호출 -> 테이블 값 채워넣기
		GwakMemberMgr mgr = new GwakMemberMgr();
		GwakMemberBean bean = mgr.SelectUnitMgrTable(model, id);

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
		
		table.getColumn("stckNum").setWidth(0);
		table.getColumn("stckNum").setMinWidth(0);
		table.getColumn("stckNum").setMaxWidth(0);
		
//		테이블에 열 제목 나오게 하는 코드. 참고 : https://yyman.tistory.com/550
		JScrollPane scrollPane = new JScrollPane(table); //
		scrollPane.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		
		scrollPane.setBounds(239, 236, 1186, 533);
		scrollPane.setAutoscrolls(true);
		contentPane.add (scrollPane) ;
		
//		테이블 행 높이 조절
		table.setRowHeight(40);

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
		
		
		// 구매 이력 버튼 누르면 실행 됨 -> 새 폼 띄우기
		btnUnitBuyHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				int column1 = 0;
				int column2 = 1;
				
				if(row == -1) {
					JOptionPane.showConfirmDialog(null, "셀을 선택하지 않으셨습니다.", "구매 이력", JOptionPane.DEFAULT_OPTION);
				}
				else {
					int stckNum = (int) table.getValueAt(row, column1);
					String unitNum = (String) table.getValueAt(row, column2);
					UnitBuyHistory history = new UnitBuyHistory(stckNum, unitNum);
					
					
					history.setVisible(true);
				}
			}
		});
		

		// 삭제 버튼 누르면 실행됨
				btnDelUnitStock.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int row = table.getSelectedRow();
						int column = 1;
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
									bean.setStckComNum(id);
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

						UnitStockMgr_addUnit history = new UnitStockMgr_addUnit();

						history.setVisible(true);
						
						//현재 메인창 닫기(업데이트를 위해)
						dispose();

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
				// 메인화면은 visible true, 현재화면은 false
				ComMyPage myPage = new ComMyPage();
				myPage.setVisible(true);
				
				// 현재 메인창 닫기(업데이트를 위해)
				dispose();
			}
		});

	}
	

	
			}
	
	



