package all;

// db연결...
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.*;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Vector;



public class RegTech extends JFrame {
	private DBConnectionMgr pool;

	private JPanel contentPane;
	private JTextField textField;

	private JTextField techName;
	private JTextField techTel;
	private JTextField techLv;
	private LineBorder LineBorderRegTec1;
	private LineBorder LineBorderRegTec2;
	private JTable table;
	private JTable listTech;
	private static int n;
	private int flag = 0;
	TextField tf = new TextField();
	private JTextField textField_1;
	int a = 0; 
	
//	여기서부터
	 Connection conn = null;

	 Statement stmt = null;
	 
	 PreparedStatement pstmt = null;

	 ResultSet rs = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegTech frame = new RegTech();
//					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public RegTech() {
		
		
		
		
		
		setVisible(true);
		pool = DBConnectionMgr.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		
		
		
		
		
		contentPane = new JPanel();

		setContentPane(contentPane);
		

		
		

		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//		panel.setForeground(new Color(255, 0, 0));
		panel.setBounds(57, 93, 535, 565);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(949, 139, 684, 745);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(64, 113, 564, 576);
		panel_1.add(panel_2);
		contentPane.setLayout(null);

	
		
		textField = new JTextField();
		textField.setBounds(218, 37, 266, 62);
		textField.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		textField.setText("등록된 정비사 목록");
		panel_1.add(textField);
		textField.setColumns(10);
		
	
		
		
//		정보 입력하는 텍스트 필드 모음
		techName = new JTextField();
		techName.setBounds(26, 99, 206, 31);
		techName.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		panel.add(techName);
		techName.setColumns(10);
		
		techLv = new JTextField();
		techLv.setBounds(26, 347, 206, 31);
		techLv.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		techLv.setColumns(10);
		panel.add(techLv);
		
		
		techTel = new JTextField();
		techTel.setBounds(26, 220, 206, 31);
		techTel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		techTel.setColumns(10);
		panel.add(techTel);
		contentPane.add(panel);
		
		
//		라벨 모음
		
		
		JLabel lblNewLabel = new JLabel("정비사이름");
		lblNewLabel.setBounds(26, 45, 100, 25);
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		panel.add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("정비사 전화번호");
		lblNewLabel_1.setBounds(26, 172, 145, 25);
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		panel.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("정비사 직급");
		lblNewLabel_2.setBounds(26, 291, 105, 25);
		lblNewLabel_2.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		panel.add(lblNewLabel_2);
		setLocationRelativeTo(null);
		
		
//		우측 표 삽입
	
	// 여기서 부터
		String header[]= {"정비사 이름", "정비사전화번호", "정비사 직급"};
//
//
		DefaultTableModel model = new DefaultTableModel(header,0);
		JTable listTech = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
//
//		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setAutoscrolls(true);
//		
		listTech.setBounds(994, 71, 548, 565);
		contentPane.add(listTech);
		JLabel lblRegTec = new JLabel("");
		lblRegTec.setFont(new Font("나눔바른고딕", Font.BOLD, 15));

		
//		고양이 이미지 삽입
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(655, 22, 357, 146);
		lblNewLabel_3.setIcon(new ImageIcon(RegTech.class.getResource("/img/YellowCat.png")));
		contentPane.add(lblNewLabel_3);
	
		
		
//		등록 버튼
		JButton btnTechReg = new JButton("등록");
		btnTechReg.setBounds(222, 451, 73, 33);
		btnTechReg.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		panel.add(btnTechReg);
		
//		삭제버튼
		JButton btnTechDel = new JButton("삭제");
		btnTechDel.setBounds(1459, 26, 83, 35);
		contentPane.add(btnTechDel);
		panel.setLayout(null);
		
		
		
//		다음버튼
		JButton btnTechNext = new JButton("다음");
		btnTechNext.setBounds(655, 611, 172, 49);
		btnTechNext.setFont(new Font("나눔바른고딕", Font.BOLD, 22));
		contentPane.add(btnTechNext);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(1542, 71, 17, 565);
		contentPane.add(scrollBar);
		
		JLabel lblNewLabel_4 = new JLabel("입력된 정비사");
		lblNewLabel_4.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		lblNewLabel_4.setBounds(1184, 36, 140, 25);
		contentPane.add(lblNewLabel_4);
	
		
		
	

		
	

//		저장 버튼 누르면 옆에 저장되게
		btnTechReg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputStr[] = new String[3];
				
				inputStr[0] = techName.getText();
				inputStr[1] = techTel.getText();
				inputStr[2] = techLv.getText();
				 
				
				model.addRow(inputStr);
				
//				등록하고 난 뒤 다시 칸 비워주기
				
				techName.setText("");
				techTel.setText("");
				techLv.setText("");
//			
				
//				String techNN = techName.getText();
//				String techTT = techTel.getText();
//				String techLL = techLv.getText();
				
				

			}
		});
		
		
		
	
//		삭제 버튼 작동하기
//		여기서는 삭제 하시겠습니까 알림창 뜨지 않는다.
		btnTechDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listTech.getSelectedRow() == -1) {
					return;
				}
				else {
					model.removeRow(listTech.getSelectedRow());
				}
			}
		});
		
//		다음 버튼 누르면 서비스 등록페이지로 이동
		
		btnTechNext.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

//				        데이터 techData에 넣어줌. 이렇게 하면 되겠지..??? 
						RegTechData techData = new RegTechData();
						techData.setTechName(techName.getText());
						techData.setTechTel(techTel.getText());
						techData.setTechLv(techLv.getText());
						
						setVisible(false); 
						new SrvReg();
				            	
				            	
						
			}
		});
	}

		
//		데이터 넘겨주기
		class RegTechData {
			private String techName;
			private String techTel;
			private String techLv;
			
			public String getTechName() {
				return techName;
			}
			public void setTechName(String techName) {
				this.techName = techName;
			}
			
			public String getTechTel() {
				return techTel;
			}
			public void setTechTel(String techTel) {
				this.techTel = techTel;
			}
			
			public String getTechLv() {
				return techLv;
			}
			public void setTechLv(String techLv) {
				this.techLv = techLv;
			}
		}
//		은비언니가 받을 때는 new RegTech(RegTecData regTec) // maybe...

}




	

		