package all;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.accessibility.Accessible;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class RegTech extends JFrame {
	
//	헤더 출력, 디자인 수정

	private JPanel contentPane;
	private JTable table;
	
	DefaultTableModel dtm;
    Vector<String> list;
    Vector<String> colName;
    private JTextField techName;
    private JTextField techLv;
    private JTextField techTel;
    
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegTech frame = new RegTech();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	


	RegTech()
    {
		setVisible(true);   
        setBounds(300, 300, Size.SCREEN_W, Size.SCREEN_H);
		setLocationRelativeTo(null);
		setTitle("다고쳐카센터 - 기술자 등록");

        colName = new Vector<String>();
        
        colName.add("정비사 이름");
        colName.add("정비사 전화번호");
        colName.add("정비사 직급");
        

        dtm = new DefaultTableModel(colName ,0){        

            @Override
            public boolean isCellEditable(int row, int column)  
            {
                return false;       
            }
        };
        
        
        table = new JTable(dtm);
        table.getTableHeader().setFont(new Font("NanumBarunGothic", Font.PLAIN, 18));
        
        table.getColumnModel().getColumn(0).setMinWidth(240);
        table.getColumnModel().getColumn(0).setMaxWidth(240);
        table.getColumnModel().getColumn(2).setMinWidth(200);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        
        
        table.getTableHeader().setReorderingAllowed(false);    
        table.getTableHeader().setResizingAllowed(false); 
        getContentPane().setLayout(null);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(641, 234, 844, 579);
        getContentPane().add(scrollPane);        
        
     
                            
        
        table.setRowHeight(25);
		table.setFont(new Font("NanumBarunGothic", Font.PLAIN, 18));

        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(SrvReg.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(717, 57, 230, 94);
        getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("등록된 정비사 목록");
		lblNewLabel_4.setBounds(981, 184, 184, 40);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		
		JButton btnTechNext = new JButton("다음");
		btnTechNext.setBackground(new Color(244, 204, 204));
		btnTechNext.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnTechNext.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
//		btnTechNext.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		btnTechNext.setBounds(687, 862, Size.BTN_B_W, Size.BTN_B_H);
		getContentPane().add(btnTechNext);
		
		techName = new JTextField();
		techName.setBounds(190, 342, 274, 45);
		techName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(techName);
		techName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		
		techTel = new JTextField();
		techTel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		techTel.setHorizontalAlignment(SwingConstants.CENTER);
		techTel.setBounds(190, 485, 274, 45);
		getContentPane().add(techTel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 128, 128)));
		panel.setBounds(44, 234, 562, 579);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JButton btnTechReg = new JButton("등록");
		btnTechReg.setFont(new Font("나눔바른고딕", Font.BOLD, 17));
		btnTechReg.setBackground(new Color(244, 204, 204));
		btnTechReg.setBounds(200, 482, Size.BTN_S_W, Size.BTN_S_H);
		btnTechReg.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		panel.add(btnTechReg);
		//        srvName.setColumns(10);
		
		techLv = new JTextField();
		techLv.setBounds(148, 399, 274, 45);
		techLv.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(techLv);
		techLv.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		
		
		JLabel lblNewLabel_1 = new JLabel("정비사이름");
		lblNewLabel_1.setBounds(145, 51, 140, 55);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		
		JLabel lblNewLabel_2 = new JLabel("정비사 전화번호");
		lblNewLabel_2.setBounds(145, 195, 356, 55);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		
		JLabel lblNewLabel_3 = new JLabel("정비사 직급");
		lblNewLabel_3.setBounds(145, 341, 259, 55);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));

		JButton btnTechDel = new JButton("삭제");
		btnTechDel.setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		btnTechDel.setBackground(new Color(244, 204, 204));
		btnTechDel.setBounds(1334, 170, Size.BTN_S_W, Size.BTN_S_H);
		btnTechDel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		getContentPane().add(btnTechDel);
		
		// 테이블 디자인 설정
        Vector<String> columnNames = new Vector<>();
        columnNames.add("정비사 이름");
        columnNames.add("정비사 전화번호");
        columnNames.add("정비사 직급");
		HashMap<String, Integer> columnWidthValues = new HashMap<>();
		columnWidthValues.put("정비사 이름", 100);
		columnWidthValues.put("정비사 전화번호", 100);
		columnWidthValues.put("정비사 직급", 100);
		
		TableDesigner.setFont(table, "NanumBarunGothic", 21);
		TableDesigner.setTableColumn(table, columnNames);
		TableDesigner.setTableTextCenter(table, columnNames);
		TableDesigner.resizeTableRow(table, 50);
		TableDesigner.resizeTableColumn(table, columnWidthValues);
		TableDesigner.resizeTableHeader(table);
		
//		저장 버튼 누르면 옆에 저장되게
		btnTechReg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputStr[] = new String[3];
				
				inputStr[0] = techName.getText();
				inputStr[1] = techTel.getText();
				inputStr[2] = techLv.getText();
			
				dtm = (DefaultTableModel)table.getModel();
				dtm.addRow(inputStr);
				
//				등록하고 난 뒤 다시 칸 비워주기
				
				techName.setText("");
				techTel.setText("");
				techLv.setText("");

			}
		});
		
		//		삭제 버튼 작동하기
//		여기서는 삭제 하시겠습니까 알림창 뜨지 않는다.
		btnTechDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}
				else {
					dtm.removeRow(table.getSelectedRow());
				}
			}
		});
		
//		다음 버튼 누르면 서비스 등록페이지로 이동
		
		btnTechNext.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

//				        데이터 techData에 넣어줌. 이렇게 하면 되겠지..??? 
						//RegTechData techData = new RegTechData();
						//techData.setTechName(techName.getText());
						//techData.setTechTel(techTel.getText());
						//techData.setTechLv(techLv.getText());
						
						setVisible(false); 
						new SrvReg();
				            	
				            	
						
			}
		});
	}
	
	public RegTech(GwakMemberBean comJoinInfo) {	
		
	}
}
		
	





	  
