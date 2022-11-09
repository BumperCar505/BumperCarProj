package book;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;


public class BookDetail extends JFrame {

	JPanel contentPane;
	JTextField cusName;
	JTextField cusCarNum;
	JTextField cusCarBrand;
	JTextField cusCarType;
	JTextField cusTel;
	JTextField cusBookTime;
	JTextField completedTime;
	JButton btnBook;
	
	int mainNum;
	
	String[] status = { "예약됨", "정비중", "정비완료", "예약취소" };
	ImageIcon[] images = {
			new ImageIcon("/img/statusOrange.png"),
			new ImageIcon("/img/statusBlue.png"),
			new ImageIcon("/img/statusGreen.png"),
			new ImageIcon("/img/statusGray.png")
	};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookDetail frame = new BookDetail();
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
	public BookDetail() {
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//		lblNewLabel_7.setVerticalAlignment(SwingConstants.CENTER);
//		statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusYellow.png")));
		
		btnBook = new JButton();
		btnBook.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnBook.setBounds(240, 775, 150, 50);
		contentPane.add(btnBook);
		
		JButton btnBookCancel = new JButton("예약 취소");
		btnBookCancel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnBookCancel.setBounds(408, 775, 150, 50);
		contentPane.add(btnBookCancel);
		
		JPanel panel = new JPanel();
		panel.setBounds(46, 118, 512, 637);
//		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("고객명");
		lblNewLabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel.setBounds(37, 33, 144, 36);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("차번호");
		lblNewLabel_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1.setBounds(37, 109, 144, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("브랜드");
		lblNewLabel_2.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_2.setBounds(37, 185, 144, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_3.setBounds(37, 337, 144, 36);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("서비스");
		lblNewLabel_4.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_4.setBounds(37, 409, 144, 36);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("예약 시간");
		lblNewLabel_5.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_5.setBounds(37, 489, 144, 36);
		panel.add(lblNewLabel_5);
		
		cusName = new JTextField();
		cusName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusName.setBounds(202, 26, 289, 50);
		panel.add(cusName);
//		cusName.setColumns(10);
		
		cusCarNum = new JTextField();
		cusCarNum.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarNum.setColumns(10);
		cusCarNum.setBounds(202, 102, 289, 50);
		panel.add(cusCarNum);
		
		cusCarBrand = new JTextField();
		cusCarBrand.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarBrand.setColumns(10);
		cusCarBrand.setBounds(202, 178, 289, 50);
		panel.add(cusCarBrand);
		
		cusCarType = new JTextField();
		cusCarType.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarType.setColumns(10);
		cusCarType.setBounds(202, 254, 289, 50);
		panel.add(cusCarType);
		
		cusTel = new JTextField();
		cusTel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusTel.setColumns(10);
		cusTel.setBounds(202, 330, 289, 50);
		panel.add(cusTel);
		
		JComboBox srvName = new JComboBox();
		srvName.setBounds(202, 406, 289, 50);
		panel.add(srvName);
		
		JLabel lblNewLabel_2_1 = new JLabel("차종");
		lblNewLabel_2_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_2_1.setBounds(37, 261, 144, 36);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("정비 완료 시간");
		lblNewLabel_5_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_5_1.setBounds(37, 565, 144, 36);
		panel.add(lblNewLabel_5_1);
		
		cusBookTime = new JTextField();
		cusBookTime.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusBookTime.setColumns(10);
		cusBookTime.setBounds(202, 482, 289, 50);
		panel.add(cusBookTime);
		
		completedTime = new JTextField();
		completedTime.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		completedTime.setColumns(10);
		completedTime.setBounds(202, 558, 289, 50);
		panel.add(completedTime);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(BookDetail.class.getResource("/img/YellowCat.png")));
		lblNewLabel_6.setBounds(176, 45, 223, 63);
		contentPane.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(46, 775, 54, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel statusLabel = new JLabel();
		statusLabel.setBounds(18, 14, 22, 22);
		panel_1.add(statusLabel);
		
		JComboBox statusBox = new JComboBox(status);
		statusBox.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));

		statusBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
//				int index = cb.getSelectedIndex();
//				statusLabel.setIcon(images[index]);
				if ("예약됨".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusOrange.png")));
				} else if ("정비중".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusBlue.png")));
				} else if ("정비완료".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusGreen.png")));
				} else if ("예약취소".equals(cb.getSelectedItem())) {
					statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusGray.png")));
				}
			}
		});
		
		
		statusBox.setBounds(103, 775, 116, 50);
		
//		statusBox.addItem(statusSymbol);
//		statusBox.addItem(statusText);
//		statusBox.setRenderer();
		contentPane.add(statusBox);
		
		
	}
	
	public void setSchedule(int mainNum, String cusName, String cusCarNum, String cusCarBrand, String cusCarType, String cusTel,
			String srvName, String mainStartDay, String mainStartTime, String mainEndDay, String mainEndTime) {
		this.mainNum = mainNum;
		this.cusName.setText(cusName);
		this.cusCarBrand.setText(cusCarBrand);
		this.cusCarType.setText(cusCarType);
		this.cusTel.setText(cusTel);
		// 콤보박스 선택값
		cusBookTime.setText(mainStartDay + mainStartTime);
		completedTime.setText(mainEndDay + mainEndTime);
		

//		if (writer_no != bMain.userInfo.getMember_no()) {
//			t_title.setBackground(new Color(189, 189, 189));
//			t_title.setDisabledTextColor(Color.BLACK);
//			t_title.setEnabled(false);
//			c_phase.setEnabled(false);
//			t_main.setBackground(new Color(189, 189, 189));
//			t_main.setDisabledTextColor(Color.BLACK);
//			t_main.setEnabled(false);
//
//			bt_regist.setText("돌아가기");
//			try {
//				st_date = format.parse(start_date);
//				// System.out.println(st_date);
//				st_picker.setDate(st_date);
//				ed_date = format.parse(end_date);
//				// System.out.println(ed_date);
//				end_picker.setDate(ed_date);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//			st_picker.setEnabled(false);
//			end_picker.setEnabled(false);
//		} else {

		btnBook.setText("수정");
		this.cusName.setEnabled(true);
		this.cusCarBrand.setEnabled(true);
		this.cusCarType.setEnabled(true);
		this.cusTel.setEnabled(true);
		this.cusBookTime.setEnabled(true);
		this.completedTime.setEnabled(true);

//			t_title.setBackground(Color.WHITE);
//			t_main.setBackground(Color.white);
//			c_phase.setBackground(Color.white);
//			st_picker.setEnabled(true);
//			end_picker.setEnabled(true);
//			try {
//				st_date = format.parse(start_date);
//				st_picker.setDate(st_date);
//				ed_date = format.parse(end_date);
//				end_picker.setDate(ed_date);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}

		}
	}
//}
