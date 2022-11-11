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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UnitBuyHistory extends JFrame {

	private JTable table;
	private JPanel contentPane;
	private String driver  = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/cardb5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String header[] = {"구입날짜","부품번호","부품명","벤더", "구입수량"};  // 테이블 컬럼 값들
	private DefaultTableModel model = new DefaultTableModel(header, 0);
	/**
	 * Launch the application.
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
	public UnitBuyHistory(String unitName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 895, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

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
//		테이블에 열 제목 나오게 하는 코드. 참고 : https://yyman.tistory.com/550
		JScrollPane scrollPane = new JScrollPane(table);
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
		lblNewLabel_1.setBounds(54, 113, 133, 48);
		contentPane.add(lblNewLabel_1);

	}
}
