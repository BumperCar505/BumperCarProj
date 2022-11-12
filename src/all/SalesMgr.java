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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
   private JButton btnBackSalesMain;
   private JLabel lblYellowCat;
   private final int FONT_SIZE = 21;



   String[] header = {"일", "수입", "지출"};
   DefaultTableModel model = new DefaultTableModel(header,0);
   
    private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cardb2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
   
//   DefaultTableModel에 넣는 방법을 찾아
   private JTextField totalIncom;
   private JTextField totalCost;
   private JComboBox comboY;
   private JComboBox comboM;
   
   YuriSalesMgr_mgr mgr = new YuriSalesMgr_mgr();
   YuriSalesMgrBean bean = new YuriSalesMgrBean();
    int a = bean.getSrvIncome();
	int b = bean.getProIncome();
	int c = bean.getProOut();
	
	int result = a + b;

   
   String year;
   String month;
   String day;
   String dbDate = year + '-' + month + '-' + day;

   int techNum;
  

   public void setFont() {
      InputStream inputStream = null;

      // Font Setting
      try {
         String classPath = SalesMgr.class.getResource("").getPath();
         String path = URLDecoder.decode(classPath, "UTF-8");

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

      setVisible(true);
      setTitle("수입관리페이지");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
      this.setLocationRelativeTo(null);
      this.setResizable(false);

//       mgr = DBConnectionMgr.getInstance();
      
      getContentPane = new JPanel();
      getContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(getContentPane);
      TextField tf = new TextField();

//      년도 선택 콤보박스 넣기
      comboY = new JComboBox();
      comboY.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
      comboY.setModel(new DefaultComboBoxModel(new String[] {"2022","2021","2020","2019","2018","2017","2016","2015","2014","2013","2012"}));
      comboY.setBounds(82, 107, 95, 36);
      
//      매 달 콤보박스 넣기
      JComboBox comboM = new JComboBox();
      comboM.setFont(new Font("나눔바른고딕", Font.PLAIN, 19));
      comboM.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
      comboM.setBounds(189, 107, 87, 36);

      getContentPane.add(comboM);
      getContentPane.add(comboY);

      String stringYear = comboY.getSelectedItem().toString();
      int ComboSelectY = Integer.valueOf(stringYear); //콤보박스에서 선택된 년도 값.


      
      String stringMonth = comboM.getSelectedItem().toString();
      int ComboSelectM = Integer.valueOf(stringMonth); //콤보박스에서 선택된 달의 값

      Calendar cal = Calendar.getInstance();
      cal.set(ComboSelectY,(ComboSelectM+1),1);
      int monthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


      
      for(int j = 1; j<=monthDay; j++) {
         model.addRow(new Object[] {j,"",""});
      }


      
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
//      JScrollPane scrollpane = new JScrollPane(tableSalesList);
      getContentPane.add(table);
      scSalesList = new JScrollPane(table);
      scSalesList.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
      scSalesList.setBounds(82, 153, 1099, 392);
      scSalesList.setVisible(true);
      getContentPane.setLayout(null);

      getContentPane.add(scSalesList);

      JScrollPane scrollPane = new JScrollPane();
//      scSalesList.setColumnHeaderView(scrollPane);
//      model.addRow(new Object[] {"1", "김땡땡", "63하 2234"}); //열 잘 들어가는지 테스트


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

//      돌아가기 버튼 누르면 main으로 이동
      btnBackSalesMain.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new ComMyPage();

         }
      });



      //더블클릭하면 화면 넘어가게(일일매출관리페이지로)
//      tableSalesList.addMouseListener(new java.awt.event.MouseAdapter() {
//          @Override
//          public void mouseClicked(java.awt.event.MouseEvent evt) {
//              int row = table.rowAtPoint(evt.getPoint());
//              int col = table.columnAtPoint(evt.getPoint());
//              if (evt.getClickCount() == 2) {
//                 setVisible(false);
//            new SalesMgr_day();
//              }
//          }
//      });
      
   
      
//      드롭박스 바뀔 때마다 값 바뀌게
      comboM.addActionListener (new ActionListener () {
         public void actionPerformed(ActionEvent e) {
            
//           콤보박스 값을 바꾸면 먼저 전체 열들을 다 삭제시켜준다.
            model.setNumRows(0);
            
            String stringYear = comboY.getSelectedItem().toString();
            int ComboSelectY = Integer.valueOf(stringYear); //콤보박스에서 선택된 년도 값.


            
            String stringMonth = comboM.getSelectedItem().toString();
            int ComboSelectM = Integer.valueOf(stringMonth); //콤보박스에서 선택된 달의 값


            Calendar cal = Calendar.getInstance();
            cal.set(ComboSelectY,(ComboSelectM-1),1);
            int monthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);


            for(int j = 1; j<=monthDay; j++) {
            
               model.addRow(new Object[] {j, result,c } //돌면서 계산할 수있게
               
              );
           }
         }
       });
      
      }
   //db연결
   private void monthIncome() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"root","1234");
			sql = "SELECT un.unitPrice "
					+ "FROM maintenance main "
					+ "JOIN detail dtl "
					+ "ON dtl.dtlSrvNum = main.mainSrvNum "
					+ "JOIN unit un "
					+ "ON un.unitNum = dtl.dtlUnitNum "
					+ "JOIN service srv "
					+ "ON srv.srvNum = dtl.dtlSrvNum "
					+ "JOIN technician tech "
					+ "ON tech.techNum = srv.srvTechNum "
					+ "WHERE main.mainComNum=? AND main.mainEndDay = ? and main.mainStatus=? "
					+ "AND srv.srvTechNum = ? AND un.unitNum LIKE 's%' AND dtl.dtlDeleted_yn='N' " ;

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getString("unitprice")
				});
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		
		}
		}
		   

   }

            


   
      