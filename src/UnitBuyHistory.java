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

public class UnitBuyHistory extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitBuyHistory frame = new UnitBuyHistory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UnitBuyHistory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

//		폼 창이 화면 가운데서 뜨게 하는 기능
		setLocationRelativeTo(null); //--

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String header[] = {"구입날짜","부품번호","부품명", "벤더", "구입수량"};
		String contents[][] = {
				{"2022-10-10","hh111","타이어","한국타이어", "20"},
				{"2022-09-09","hh111","타이어","한국타이어", "24"},
				{"2022-08-08","hh111","타이어","한국타이어", "11"},
				{"2022-07-07","hh111","타이어","한국타이어", "2"},
				{"2022-06-06","hh111","타이어","한국타이어", "8"},
		};

		
		DefaultTableModel model = new DefaultTableModel(contents, header);
		JTable table = new JTable(model);
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
