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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class TechListEdit extends JFrame {

	
	
	private JPanel contentPane;
	private JTable table;
	private JButton btnEditTech;
	private JButton btnDelTech;
	private JButton btnBackMain;
	
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String header[] = {"techNum","정비사 이름","전화번호","직급"};  // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(header, 0);
	


	

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TechListEdit frame = new TechListEdit();
					frame.setVisible(true);
					//frame.reload();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	
	public void reload() {
		
	}
	
	
	
	
	public TechListEdit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setEnabled(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Select2();

//		폼 창이 화면 가운데서 뜨게 하는 기능
		setLocationRelativeTo(null); //--
		setContentPane(contentPane);
		
		
//		테이블 생성
		
		//db데이터 select, update 하는 클래스
//		MemberMgr mgr = new MemberMgr(); 

		table = new JTable(model);
		

//		JTable table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(3).setResizable(false);
		contentPane.setLayout(null);

		table.setBounds(247, 231, 1170, 671);
//		테이블에 열 제목 나오게 하는 코드. 참고 : https://yyman.tistory.com/550
		JScrollPane scrollPane = new JScrollPane(table);
		//--
		scrollPane.setBounds(239, 236, 1186, 533);
		scrollPane.setAutoscrolls(true);
		contentPane.add (scrollPane) ; 
//		테이블 행 높이 조절
		table.setRowHeight(40);

		
		JButton btnAddTech = new JButton("추가");
		btnAddTech.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnAddTech.setBounds(239, 174, Size.BTN_S_W, Size.BTN_S_H);
		contentPane.add(btnAddTech);
		
		btnEditTech = new JButton("수정");
		btnEditTech.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnEditTech.setBounds(401, 174, 150, 50);
		contentPane.add(btnEditTech);
		
		btnDelTech = new JButton("삭제");
		btnDelTech.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		btnDelTech.setBounds(563, 174, 150, 50);
		contentPane.add(btnDelTech);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TechListEdit.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(704, 29, 255, 112);
		contentPane.add(lblNewLabel);
		
		// 돌아가기 버튼
		btnBackMain = new JButton("돌아가기");
		btnBackMain.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		btnBackMain.setBounds(687, 824, Size.BTN_B_W, Size.BTN_B_H);
		contentPane.add(btnBackMain);
		
		
		
		
		//추가 버튼 누르면 실행됨 -> 새 폼 띄우기
		btnAddTech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TechListEdit_add add = new TechListEdit_add();
				add.setVisible(true);
				
				dispose();
			}
		});
		
		
		
		
		// 수정 버튼 누르면 실행됨 -> 새 폼 띄우기 
		btnEditTech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				int column = 0;
					
				
				if(row == -1){
		            JOptionPane.showConfirmDialog(null, "셀을 선택하지 않으셨습니다.", "삭제", JOptionPane.DEFAULT_OPTION);
		        }
				else {
					int editIndex = (int) table.getValueAt(row, column);	
					TechListEdit_edit edit = new TechListEdit_edit(editIndex);
					edit.setVisible(true);
					dispose();
				}
				
				
			}
		});
		
		
		
		
		
		// 삭제 버튼 누르면 실행됨
		btnDelTech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				int column = 0;
				int editIndex = (int) table.getValueAt(row, column);
				
				if(row == -1){
		            JOptionPane.showConfirmDialog(null, "셀을 선택하지 않으셨습니다.", "삭제", JOptionPane.DEFAULT_OPTION);
		        }
				
		        else {
		        	try {
		        		int result = DialogManager.createMsgDialog("<html><h3>삭제하시겠습니까?</h3>", "/img/trash.png", "삭제", JOptionPane.YES_NO_OPTION);
   
			            if (result == 0) {
			            	model.removeRow(row);
			            	
			            	GwakMemberMgr mgr = new GwakMemberMgr();
							GwakMemberBean bean =  new GwakMemberBean();
							
							bean.setTechNum(editIndex);
			            	mgr.delete(bean);
			            	
			            	DialogManager.createMsgDialog("<html><h3>삭제되었습니다.</h3>", "/img/success1.png", "삭제", JOptionPane.CLOSED_OPTION);
			            } else if (result == 1) {
			            	   
			            	}
			            } catch(Exception ex) {

			      }
		       }
			}
		});
		
		
		
		// 돌아가기 버튼 누르면 실행됨 -> 현재 화면 닫고 메인화면 띄우기
		btnBackMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 메인화면은 visible true, 현재화면은 false

			}
		});
		
		

	}
		// select2 : DB에서 데이터 불러와서 테이블 채우기
		private void Select2(){
				
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
//			MemberBean bean = new MemberBean();
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, "root", "1234");
				sql = "select * from technician ";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

					while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
	                 model.addRow(new Object[]{rs.getInt("techNum"), rs.getString("techName"), rs.getString("techTel"),rs.getString("techLv")});
	                }
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
			}
		}
}




