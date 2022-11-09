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

public class UnitStockMgr_edit extends JFrame {

	private JPanel contentPane;
	private JTextField unitName;
	private JTextField unitPrice;
	private JTextField unitVendor;
	private JButton btnUnitSave;
	private JLabel lblNewLabel;
	private JTextField stockUnitQty;
	private JTextField stockUnitBuyDate;
	
	String editIndex2 = "";
	private JTextField unitNum;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					UnitStockMgr frame = new UnitStockMgr();
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
	public UnitStockMgr_edit(String editIndex) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		폼 크기 : 600 * 500
		setBounds(100, 100, 592, 764);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel unitNamelbl = new JLabel("부품명");
		unitNamelbl.setBounds(93, 181, 390, 47);
		unitNamelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitNamelbl);
		
		JLabel unitPricelbl = new JLabel("부품가격");
		unitPricelbl.setBounds(93, 271, 390, 47);
		unitPricelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitPricelbl);
		
		JLabel unitVendorlbl = new JLabel("벤더");
		unitVendorlbl.setBounds(93, 361, 390, 47);
		unitVendorlbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(unitVendorlbl);
		
		unitName = new JTextField();
		unitName.setEditable(false);
		unitName.setBounds(93, 223, 390, 39);
		contentPane.add(unitName);
		unitName.setColumns(10);
		
		unitPrice = new JTextField();
		unitPrice.setEditable(false);
		unitPrice.setColumns(10);
		unitPrice.setBounds(93, 313, 390, 39);
		contentPane.add(unitPrice);
		
		unitVendor = new JTextField();
		unitVendor.setEditable(false);
		unitVendor.setColumns(10);
		unitVendor.setBounds(93, 403, 390, 39);
		contentPane.add(unitVendor);
		
		btnUnitSave = new JButton("저장");
		btnUnitSave.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		btnUnitSave.setBounds(142, 650, Size.BTN_B_W, Size.BTN_B_H);
		contentPane.add(btnUnitSave);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UnitStockMgr_edit.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(161, 10, 254, 80);
		contentPane.add(lblNewLabel);
		
		JLabel stockUnitQtylbl = new JLabel("부품수량");
		stockUnitQtylbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		stockUnitQtylbl.setBounds(93, 451, 390, 47);
		contentPane.add(stockUnitQtylbl);
		
		stockUnitQty = new JTextField();
		stockUnitQty.setColumns(10);
		stockUnitQty.setBounds(93, 493, 390, 39);
		contentPane.add(stockUnitQty);
		
		JLabel stockUnitBuyDatelbl = new JLabel("구매날짜");
		stockUnitBuyDatelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		stockUnitBuyDatelbl.setBounds(93, 541, 390, 47);
		contentPane.add(stockUnitBuyDatelbl);
		
		stockUnitBuyDate = new JTextField();
		stockUnitBuyDate.setColumns(10);
		stockUnitBuyDate.setBounds(93, 583, 390, 39);
		contentPane.add(stockUnitBuyDate);
				
		
		JLabel unitNumlbl = new JLabel("\uBD80\uD488\uBC88\uD638");
		unitNumlbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		unitNumlbl.setBounds(93, 91, 390, 47);
		contentPane.add(unitNumlbl);
		
		unitNum = new JTextField();
		unitNum.setEditable(false);
		unitNum.setText((String) null);
		unitNum.setColumns(10);
		unitNum.setBounds(93, 133, 390, 39);
		contentPane.add(unitNum);
		
		
		GwakMemberMgr mgr = new GwakMemberMgr();
		GwakMemberBean bean =  mgr.select2(editIndex);
		
		unitNum.setText(bean.getUnitNum());
		unitName.setText(bean.getUnitName());
		unitPrice.setText(Integer.toString(bean.getUnitPrice()));
		unitVendor.setText(bean.getUnitVendor());
		stockUnitQty.setText(Integer.toString(bean.getStckQty()));
		stockUnitBuyDate.setText(bean.getStckBuyDate());
		
		String editIndex2 = editIndex;
		
		// 등록 버튼 누르면 현재 창이 닫히면서 TechListEdit 창이 뜸(데이터 이동 완료상태)
		// 또는 추가 버튼 누르면 기존 폼이 닫히고, 등록 버튼 누를때 기존 폼 새로 뜨게??????????????????????????????????????????????????????????
		btnUnitSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GwakMemberMgr mgr = new GwakMemberMgr();
				GwakMemberBean bean =  new GwakMemberBean();
				
				bean.setStckQty(Integer.parseInt(stockUnitQty.getText()));
				bean.setStckBuyDate(stockUnitBuyDate.getText());

				mgr.update2(bean,editIndex2);
				
				UnitStockMgr uform = new UnitStockMgr();
				uform.setVisible(true);
				
				dispose();//--
			}
		});
		
		
	}
}
