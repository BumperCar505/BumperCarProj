package all;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
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
import java.awt.Dimension;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;
import java.awt.CardLayout;

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
	

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String[] header = {"Num", "고객이름", "차번호", "브랜드", "차종", "주행거리","우편번호","주소","전화번호", "가입날짜"};
	DefaultTableModel model = new DefaultTableModel(header,0);

//	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusMgr frame = new CusMgr();
				       
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
		

		table = new JTable(model);
		
		JTable tableCusList = new JTable(model);
		tableCusList.setAutoCreateRowSorter(true);
		tableCusList.setRowMargin(4);
		tableCusList.setRowHeight(30);
		tableCusList.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		
		JScrollPane scrollpane = new JScrollPane(table);
		getContentPane.add(tableCusList);
		JLabel lblRegTec = new JLabel("");
		lblRegTec.setFont(new Font("나눔바른고딕", Font.BOLD, 20));
		
		scCusList = new JScrollPane(tableCusList);
		scCusList.setEnabled(false);
		scCusList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scCusList.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		scCusList.setBounds(100, 145, 1462, 750);
		getContentPane.setLayout(null);
		
		getContentPane.add(scCusList);
		
		// Button Create
		btnAddCus = new JButton("추가");
		btnAddCus.setBounds(100, 70, 150, 50);

		getContentPane.add(btnAddCus);
		
		btnEditCus = new JButton("수정");
		btnEditCus.setBounds(275, 70, 150, 50);
		getContentPane.add(btnEditCus);
		
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
		mainPanel.setLayout(new CardLayout(0, 0));
//		
//		JScrollPane scroll = new JScrollPane(table);  // 스크롤패널을 선언
//		scroll.setBounds(0,0,160,160);
//		table.add(scroll); 
		
		JPanel jpList = new JPanel();		
	    jpList.setLayout(new GridBagLayout());			
		JScrollPane scrollSingle = new JScrollPane(jpList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollSingle.setPreferredSize(new Dimension(400, 200));
	


		
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
				int row = tableCusList.getSelectedRow();
				int column = 0;
				try {
					int index = tableCusList.getSelectedRow();
					if(index == -1) {
						DialogManager.createMsgDialog("셀을 선택하지 않았습니다.", "\\img\\information5.png", "오류", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					int num = DialogManager.createMsgDialog("수정하시겠습니까","\\img\\question6.png", "수정",JOptionPane.YES_NO_OPTION);
			    	if(num==0){		    		
			    		int editIndex = (int) tableCusList.getValueAt(row, column);	
						CusMgr_edit edit = new CusMgr_edit(editIndex);
						edit.setVisible(true);
						setVisible(false); 
			    	}
			    	else if(num==1) {
			    		
			    	}
			   
		            } catch(Exception ex) {
		            	ex.printStackTrace();
		            }
				
		}; 
	});
	}

		private void getInfo() {

			
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