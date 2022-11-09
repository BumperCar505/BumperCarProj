import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JList;

public class UnitStockMgr_add extends JFrame {

	private JPanel contentPane;
	private JTextField unitName;
	private JTextField unitPrice;
	private JTextField unitVendor;
	private JButton btnUnitReg;
	private JLabel lblNewLabel;
	private JTextField stockUnitQty;
	private JTextField stockUnitBuyDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UnitStockMgr_add frame = new UnitStockMgr_add();
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
	public UnitStockMgr_add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		폼 크기 : 600 * 500
		setBounds(100, 100, 592, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel unitNamelbl = new JLabel("부품명");
		unitNamelbl.setBounds(93, 93, 390, 47);
		unitNamelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitNamelbl);
		
		JLabel unitPricelbl = new JLabel("부품가격");
		unitPricelbl.setBounds(93, 183, 390, 47);
		unitPricelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitPricelbl);
		
		JLabel unitVendorlbl = new JLabel("벤더");
		unitVendorlbl.setBounds(93, 273, 390, 47);
		unitVendorlbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitVendorlbl);
		
		unitName = new JTextField();
		unitName.setBounds(93, 135, 390, 39);
		contentPane.add(unitName);
		unitName.setColumns(10);
		
		unitPrice = new JTextField();
		unitPrice.setColumns(10);
		unitPrice.setBounds(93, 225, 390, 39);
		contentPane.add(unitPrice);
		
		unitVendor = new JTextField();
		unitVendor.setColumns(10);
		unitVendor.setBounds(93, 315, 390, 39);
		contentPane.add(unitVendor);
		
		btnUnitReg = new JButton("등록");
		btnUnitReg.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		btnUnitReg.setBounds(143, 619, Size.BTN_B_W, Size.BTN_B_H);
		contentPane.add(btnUnitReg);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitStockMgr_add.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(161, 10, 254, 80);
		contentPane.add(lblNewLabel);
		
		JLabel stockUnitQtylbl = new JLabel("부품수량");
		stockUnitQtylbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		stockUnitQtylbl.setBounds(93, 363, 390, 47);
		contentPane.add(stockUnitQtylbl);
		
		stockUnitQty = new JTextField();
		stockUnitQty.setColumns(10);
		stockUnitQty.setBounds(93, 405, 390, 39);
		contentPane.add(stockUnitQty);
		
		JLabel stockUnitBuyDatelbl = new JLabel("구매날짜");
		stockUnitBuyDatelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		stockUnitBuyDatelbl.setBounds(93, 453, 390, 47);
		contentPane.add(stockUnitBuyDatelbl);
		
		stockUnitBuyDate = new JTextField();
		stockUnitBuyDate.setColumns(10);
		stockUnitBuyDate.setBounds(93, 495, 390, 39);
		contentPane.add(stockUnitBuyDate);
		
		// 등록 버튼 누르면 현재 창이 닫히면서 TechListEdit 창이 뜸(데이터 이동 완료상태)
		// 또는 추가 버튼 누르면 기존 폼이 닫히고, 등록 버튼 누를때 기존 폼 새로 뜨게??????????????????????????????????????????????????????????
		btnUnitReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnitStockMgr uform = new UnitStockMgr();
				dispose();//--
			}
		});
	}
}
