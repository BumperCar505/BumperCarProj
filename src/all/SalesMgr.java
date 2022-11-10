package all;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.border.BevelBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;

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
            inputStream = new BufferedInputStream(
                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            
            btnBackSalesMain.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            
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

	
		
		//테이블 생성
		Object[] columns = {"일", "수입", "지출"};
		Object[][] rowNames = {
				{"1", "1,100,000", "1,300,000"},
				{"2", "1,100,000", "1,300,000"},
				{"3", "1,100,000", "1,300,000"},
				{"4", "1,100,000", "1,300,000"},
				{"5", "1,100,000", "1,300,000"},
				{"6", "1,100,000", "1,300,000"}
				

				
	
		};
		
		// Text Align Center
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		
		tableSalesList = new JTable(rowNames, columns);
		tableSalesList.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		tableSalesList.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "1,100,000", "1,300,000"},
				{"2", "1,100,000", "1,300,000"},
				{"3", "1,100,000", "1,300,000"}

			},
			new String[] {
//				"\uC77C", "\uC218\uC785", "\uC9C0\uCD9C" 한글로 수정 가능!
					"일", "수입","지출"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		tableSalesList.getColumn("일").setCellRenderer(render);
		tableSalesList.getColumn("수입").setCellRenderer(render);
		tableSalesList.getColumn("지출").setCellRenderer(render);
	
		
		// Column Not Move
		tableSalesList.getTableHeader().setReorderingAllowed(false);
		
		// Column Change Width
		tableSalesList.getColumn("수입").setPreferredWidth(200);
		tableSalesList.getColumn("지출").setPreferredWidth(200);
		
		// Row Change Height 
		tableSalesList.setRowHeight(55);
		
		// Table Set Area
		scSalesList = new JScrollPane(tableSalesList);
		scSalesList.setBounds(86, 140, 1462, 453);
		scSalesList.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		scSalesList.setVisible(true);
		getContentPane.setLayout(null);
		
		getContentPane.add(scSalesList);
		

		
		btnBackSalesMain = new JButton("돌아가기");
		btnBackSalesMain.setBounds(650, 614, 290, 65);
		getContentPane.add(btnBackSalesMain);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setBounds(710, 50, 230, 80);
		lblYellowCat.setIcon(new ImageIcon(CusMgr.class.getResource("/img/YellowCat.png")));
		getContentPane.add(lblYellowCat);

		
		
		btnBackSalesMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				new ComMyPage();

			}
		});
		
		
		//더블클릭
//		table.addMouseListener(new MouseAdpter() {
//		@Override
//		public void MyMouseListener(MouseEvent e) {
//			if (e.getClickCount() == 2) {
//				setVisible(false);
//				new SalesMgr_day();
//			}
//		});S
		
//		@Override
//		public void mouseClick(MouseEvent e) {
//			if(e.getClickCount()==2) {
//				
//			}
//		}
		
		//더블클릭했을 때 일일 페이지로 넘어가게
//		System.out.print(this);
//		this.table.setFocusable(false);
//		table.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if(e.getClickCount() == 2) {
//					setVisible(false);
////					new SalesMgr_day();
//				}
//			}
//		});
		
		//더블클릭하면 화면 넘어가게(일일매출관리페이지로) 
		tableSalesList.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = tableSalesList.rowAtPoint(evt.getPoint());
		        int col = tableSalesList.columnAtPoint(evt.getPoint());
		        if (evt.getClickCount() == 2) {
		        	setVisible(false);
				new SalesMgr_day();
		        }
		    }
		});
		
	}
}