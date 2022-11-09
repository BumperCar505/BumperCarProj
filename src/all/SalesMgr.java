package all;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.Calendar;

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
import javax.swing.border.BevelBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

// ComServiceList
public class SalesMgr extends JFrame {
	private JTable table;
	private JPanel getContentPane;
	private JPanel addPanel;
	private JTable tableSalesList;
	private JScrollPane scSalesList;
//	private JButton btnAddCus;
//	private JButton btnEditCus;
//	private JButton btnDelCus;
	private JButton btnBackSalesMain;
	private JLabel lblYellowCat;
	private final int FONT_SIZE = 21;
	
//	이 페이지 해야 할 것들. 
//	1. 각 달의 마지막 날 가져와 달마다 다르게 생성되게(ex.31일, 29일)
//	2. 데이터가 있는 날, 없는 날 받아와서 그에 맞는 수입, 지출 보여주기(SalesMgr_day)
//	3. 달 옆에 페이지 넘길 수 있게 넣어서 넘겨 볼 수 있도록
//	4. 옆에 스크롤바 삽입
//	5. 총수입 총지출 확인할 수 있게(옆에 따로 만들면 좋을 듯)
//	6. 기간 설정하기
//	7. 덧셈 sql 쿼리
	
	
	
	String[] header = {"일", "수입", "지출"};
	DefaultTableModel model = new DefaultTableModel(header,0);
	private JTextField totalIncom;
	private JTextField totalCost;
	private JComboBox comboY;
	
	
	
//	앞으로 추가할 기능.
//	1. 여기있는 데이터 엑셀로 내보내는 기능
//	2. 프린트 연결
//	3. 화살표로 다른 달 넘길 수 있는 기능
//	일단 말이다... 
	
	
	
	
	
	public void setFont() {
		InputStream inputStream = null;
		
		// Font Setting
		try {
            String classPath = SalesMgr.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
//            inputStream = new BufferedInputStream(
//                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));

//            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            
//            btnBackSalesMain.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            
    		// Table Font
//            tableCusList.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));

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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesMgr frame = new SalesMgr();
//					frame.setVisible(true);
					frame.setFont();
				
					
				       
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	/**
	 * Create the frame.
	 */
	
	
	
	
	public SalesMgr() {
		
//		SalesMgr.addMouseListener(new MouseAdapter() {
//		@Override
//		public addMouseListener(MouseEvent e) {
//			if (e.getClickCount() == 2) {
//				setVisible(false);
//				new SalesMgr_day();
//			}
//		}
//		});
		
		setVisible(true);
		setTitle("수입관리페이지");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		getContentPane = new JPanel();
		getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(getContentPane);
		TextField tf = new TextField();
		
//		addPanel = new JPanel();
		
//		년도 선택 콤보박스 넣기
		comboY = new JComboBox();
		comboY.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		comboY.setModel(new DefaultComboBoxModel(new String[] {"2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"}));
		comboY.setBounds(82, 107, 95, 36);
		
//		기본적으로 현재 년도 출력되게 잠시보류..
		LocalDate now = LocalDate.now();
		String year = Integer.toString(now.getYear());
		getContentPane.add(comboY);
		comboY.setSelectedItem("year");
		System.out.print(year);

//		매 달 콤보박스 넣기
		JComboBox comboM = new JComboBox();
		comboM.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
		comboM.setModel(new DefaultComboBoxModel(new String[] {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"}));
		comboM.setBounds(189, 107, 87, 36);
		
//		화면에 들어가면 기본적으로 현재 달이 출력될 수 있게.
				 
		int dayOfMonth = now.getDayOfMonth();
		comboM.setSelectedIndex(dayOfMonth);
		getContentPane.add(comboM);
		
		//테이블 생성
		
		table = new JTable(model);
		table.setDragEnabled(true);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(40);
		table.setAlignmentY(5.0f);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("나눔바른고딕", Font.PLAIN, 17));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setAlignmentX(10.0f);
		 table.getTableHeader().setReorderingAllowed(false); //컬럼 이동 불가
		 table.getTableHeader().setResizingAllowed(false); //컬럼 크기 조절 불가
		 

		 
		 
		
		JTable tableSalesList = new JTable(model);
//		JScrollPane scrollpane = new JScrollPane(tableSalesList);
		getContentPane.add(table);
		scSalesList = new JScrollPane(table);
		scSalesList.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		scSalesList.setBounds(82, 153, 1099, 392);
		scSalesList.setVisible(true);
		getContentPane.setLayout(null);
		
		getContentPane.add(scSalesList);
		
		JScrollPane scrollPane = new JScrollPane();
//		scSalesList.setColumnHeaderView(scrollPane);
//		model.addRow(new Object[] {"1", "김땡땡", "63하 2234"}); //열 잘 들어가는지 테스트
		
		
//		콤보박스에서 해당 값 가져오기.
		 String str1 = comboM.getSelectedItem().toString();
			
		 char str2 = str1.charAt(0);
		 
		 int comboMonth = Character.getNumericValue(str2);
		
//		Calendar cal = Calendar.getInstance();
	//  1월이 0으로 인식되기 때문에 month+1을 해줘야 한다.
		
		int comboMonth = comboM.getSelectedItem();
		cal.set(2022,comboMonth+1,1);
		
		
//		 System.out.println(comboMonth + comboMonth); int가 되었는지 확인
//		 int comboMonth = Integer.parseInt(str2);
//		 System.out.println(comboMonth);

//		int monthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
//		for(int i = 1; i<=monthDay; i++) {
//			model.addRow(new Object[] {i,"",""});
//		}
		   JPanel jpList = new JPanel();		
		   jpList.setLayout(new GridBagLayout());			
		   JScrollPane scrollSingle = new JScrollPane(jpList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		   ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		   scrollSingle.setPreferredSize(new Dimension(400, 200));

		btnBackSalesMain = new JButton("돌아가기");
		btnBackSalesMain.setBounds(648, 555, 290, 65);
		getContentPane.add(btnBackSalesMain);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);
		
		JLabel lblNewLabel = new JLabel("총 수입");
		lblNewLabel.setFont(new Font("나눔바른고딕", Font.BOLD, 22));
		lblNewLabel.setBounds(1264, 209, 95, 73);
		getContentPane.add(lblNewLabel);
		
		totalIncom = new JTextField();
		totalIncom.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		totalIncom.setBounds(1381, 211, 166, 59);
		getContentPane.add(totalIncom);
		totalIncom.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(1223, 154, 387, 297);
		getContentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("총 지출");
		lblNewLabel_1.setBounds(46, 165, 83, 26);
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.BOLD, 22));
		panel.add(lblNewLabel_1);
		
		totalCost = new JTextField();
		totalCost.setFont(new Font("나눔바른고딕", Font.PLAIN, 18));
		totalCost.setColumns(10);
		totalCost.setBounds(159, 149, 166, 59);
		panel.add(totalCost);
		
		
		

		
		
		
		
//		돌아가기 버튼 누르면 main으로 이동
		btnBackSalesMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new ComMyPage();

			}
		});
		
		
		
		//더블클릭하면 화면 넘어가게(일일매출관리페이지로) 
//		tableSalesList.addMouseListener(new java.awt.event.MouseAdapter() {
//		    @Override
//		    public void mouseClicked(java.awt.event.MouseEvent evt) {
//		        int row = table.rowAtPoint(evt.getPoint());
//		        int col = table.columnAtPoint(evt.getPoint());
//		        if (evt.getClickCount() == 2) {
//		        	setVisible(false);
//				new SalesMgr_day();
//		        }
//		    }
//		});
		
//		드롭박스 바뀔 때마다 값 바뀌게
		comboM.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
//		    	 String str1 = comboM.getSelectedItem().toString();
//					
//				 char str2 = str1.charAt(0);
//				 
//				 int comboMonth = Character.getNumericValue(str2);
//				
//				Calendar cal = Calendar.getInstance();
//			//  1월이 0으로 인식되기 때문에 month+1을 해줘야 한다.
//
//				cal.set(2022,comboMonth,1);
//
//				int monthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//				
//				for(int i = 1; i<=monthDay; i++) {
//					model.addRow(new Object[] {i,"",""});
//				}

		    }
		});
		
	}
}