package all;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;

public class UnitBuyHistory extends JFrame {

	private JTable table;
	private JPanel contentPane;
	private LoginManager loginManager;
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String header[] = {"stckNum", "구입날짜","부품번호","부품명","벤더", "구입수량"};  // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(header, 0);
	/**
	 * Launch the application..
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					UnitBuyHistory frame = new UnitBuyHistory();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UnitBuyHistory(int stckNum, String unitNum) {
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 895, 730);
		contentPane = new JPanel();
		contentPane.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Select5(stckNum, unitNum);
		

//		폼 창이 화면 가운데서 뜨게 하는 기능
		setLocationRelativeTo(null); //--

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(0).setMinWidth(20);
		table.getColumnModel().getColumn(3).setResizable(false);
		contentPane.setLayout(null);

		table.setBounds(247, 231, 1170, 671);
		table.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		
		table.getColumn("stckNum").setWidth(0);
		table.getColumn("stckNum").setMinWidth(0);
		table.getColumn("stckNum").setMaxWidth(0);
		
//		테이블에 열 제목 나오게 하는 코드. 참고 : https://yyman.tistory.com/550
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		//--
		scrollPane.setBounds(54, 171, 770, 477);
		scrollPane.setAutoscrolls(true);
		contentPane.add (scrollPane) ; 
		
//		테이블 행 높이 조절
		table.setRowHeight(40);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitBuyHistory.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(309, 22, 261, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("구매내역 조회");
		lblNewLabel_1.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(85, 111, Size.BTN_S_W, Size.BTN_S_H);
		contentPane.add(lblNewLabel_1);
	}
	
	
	//  : DB에서 데이터 불러와서 테이블 채우기(제품 구매 이력)
	private void Select5(int stckNum, String unitNum){
			
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
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
			sql = "SELECT stock.stckNum, stock.stckBuyDate, unit.unitNum, unit.unitName, unit.unitVendor, stock.stckQty1 "
					+ "FROM stock "
					+ "inner join unit "
					+ "on stock.stckUnitNum = unit.unitNum "
					+ "WHERE stock.stckComNum = ? AND unit.unitNum = ? "
					+ "ORDER BY stock.stckBuyDate DESC ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // 사업자번호
			pstmt.setString(2, unitNum); // p001
			rs = pstmt.executeQuery();
				while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
                 model.addRow(new Object[]{rs.getInt("stock.stckNum"), rs.getString("stock.stckBuyDate"), rs.getString("unit.unitNum"), rs.getString("unit.unitName"), rs.getString("unit.unitVendor"),rs.getInt("stock.stckQty1")});
                }
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		}

		}
}
