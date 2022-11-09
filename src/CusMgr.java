import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

// ComServiceList
public class CusMgr extends JFrame {
	private JTable table;
	private JPanel getContentPane;
	private JPanel addPanel;
	private JTable tableCusList;
	private JScrollPane scCusList;
	private JButton btnAddCus;
	private JButton btnEditCus;
	private JButton btnDelCus;
	private JButton btnBackCusMain;
	private JLabel lblYellowCat;
	private final int FONT_SIZE = 21;
	private JPanel mainPanel;
	
//	db connect
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cardb2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String[] header = {"Num", "고객이름", "차번호", "브랜드", "차종", "주행거리","우편번호","주소","전화번호", "가입날짜"};
	DefaultTableModel model = new DefaultTableModel(header,0);

	
//	public void setFont() {
//		InputStream inputStream = null;
		
		// Font Setting
//		try {
//            String classPath = CusMgr.class.getResource("").getPath();
//            String path = URLDecoder.decode(classPath, "UTF-8");
//            inputStream = new BufferedInputStream(
//                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));
//
//            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
//            
//            btnAddCus.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
//            btnEditCus.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
//            btnDelCus.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
//            btnBackCusMain.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            
//    		// Table Font
//            tableCusList.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));

//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//        	if(inputStream != null) {
//        		try {
//            		inputStream.close();
//        		} catch(Exception e2) { 
//        			e2.printStackTrace();
//        		}
//        	}
//        }
//	}
//	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusMgr frame = new CusMgr();
					
//					frame.setFont();

					
				       
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	public CusMgr() {
		
		setVisible(true);
		setTitle("고객관리페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		

		
		getContentPane = new JPanel();
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getInfo();


		setContentPane(getContentPane);
		TextField tf = new TextField();
		

	
		
		// Num, Service Name, Provide Technician, Service Price
//		여기서부터 잠깐 놔둔다.
		
//		Object[][] data = {
//				{"1", "김땡땡", "63하 2234", "현대","소나타","3000km","11223","부산광역시","010-1111-1111","2022-02-23"}
//			
//		};
		table = new JTable(model);
		
		JTable tableCusList = new JTable(model);

//		model.addRow 테스트
//		model.addRow(new Object[] {"1", "김땡땡", "63하 2234", "현대","소나타","3000km","11223","부산광역시","010-1111-1111","2022-02-23"});
		
		
//		table.addMouseListener(new JTableMouseListener());
		
		
		JScrollPane scrollpane = new JScrollPane(table);
//		tableCusList.setBounds(994, 71, 548, 506);
		getContentPane.add(tableCusList);
		JLabel lblRegTec = new JLabel("");
		lblRegTec.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		
//		private JTable = table;

		
		// Table Set Area
		scCusList = new JScrollPane(tableCusList);
		scCusList.setBounds(100, 145, 1462, 750);
		scCusList.setVisible(true);
		getContentPane.setLayout(null);
		
		getContentPane.add(scCusList);
		
		// Button Create
		btnAddCus = new JButton("추가");
		btnAddCus.setBounds(100, 70, 150, 50);

		getContentPane.add(btnAddCus);
		
		btnEditCus = new JButton("수정");
		btnEditCus.setBounds(275, 70, 150, 50);
		getContentPane.add(btnEditCus);
		
//		btnDelCus = new JButton("삭제");
//		btnDelCus.setBounds(450, 70, 150, 50);
//		getContentPane.add(btnDelCus);
		
		btnBackCusMain = new JButton("돌아가기");
		btnBackCusMain.setBounds(690, 918, 290, 65);
		getContentPane.add(btnBackCusMain);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1666, 1037);
		getContentPane.add(mainPanel);
		mainPanel.setLayout(null);
//		JFrame.getContentPanel().add(addPanel);
	

//		button event
		
		//돌아가기 버튼 누르면 메인화면으로 간다.
		btnBackCusMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new ComMyPage();

			}
		});
		
		
	
		
//		추가부분 누르면 페이지 넘어가게 ok!
		btnAddCus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CusMgr_add();
				
			}
		});
		
//		수정버튼
		
		btnEditCus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int index = tableCusList.getSelectedRow();
					if(index == -1) {
						DialogManager.createMsgDialog("셀을 선택하지 않았습니다.", "\\img\\information5.png", "오류", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					int num = DialogManager.createMsgDialog("수정하시겠습니까","\\img\\question6.png", "수정",JOptionPane.YES_NO_OPTION);
			    	if(num==0){		    		
			    		setVisible(false); 
						new CusMgr_edit();
			    	}
			    	else if(num==1) {
			    		
			    	}
			   
		            } catch(Exception ex) {
		            	ex.printStackTrace();
		            }
				
		}; 
	});
	

//		btnDelCus.addActionListener(new ActionListener() {
////				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					
//				   try {
//					    int index = tableCusList.getSelectedRow();
//					    //만약 열을 선택하지 않았다면 
//				    	if(index == -1) {
//				    	 DialogManager.createMsgDialog("셀을 선택하지 않았습니다.","\\img\\information5.png", "오류",JOptionPane.PLAIN_MESSAGE);
//				    	 return;
//						} 
//				    	
////				    	열을 선택하였다면
//				    	int num = DialogManager.createMsgDialog("삭제하시겠습니까","\\img\\question6.png", "삭제",JOptionPane.YES_NO_OPTION);
//				    	if(num==0){
//				    		model.removeRow(tableCusList.getSelectedRow());
////				    		DB에도 삭제
//				    		MemberMgr mgr = new MemberMgr();
//				    		MemberBean bean = new MemberBean();
//				    		mgr.deleteCusMgr(bean);
//				    		
//				    		DialogManager.createMsgDialog("삭제 성공하였습니다.","\\img\\success1.png", "완료",JOptionPane.PLAIN_MESSAGE);
//				    	}
//				    	else if(num==1) {
//				    		
//				    	}
//				    
//				    	
//			            } catch(Exception ex) {
//			            	ex.printStackTrace();
//			            }
//					
//			}; 
//		});
	}
		
		

private void getInfo() {
	
//	DBConnectionMgr mgr = DBConnectionMgr.getInstance();
//	Connection con = null;
//	PreparedStatement psmt = null;
//	Statement stmt = null;
//	ResultSet rs = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	
	try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url,"root","1234");
		sql = "SELECT * FROM customer " ;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			model.addRow(new Object[] {
				rs.getInt("cusNum"),	
				rs.getString("cusName"),
				rs.getString("cusCarNum"),
				rs.getString("cusCarBrand"),
				rs.getString("cusCarType"),
				rs.getInt("cusKm"),
				rs.getInt("cusZip"),
				rs.getString("cusAddr"),
				rs.getString("cusTel"),
				rs.getString("cusDate")
				
			});
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {

	}

 }
}