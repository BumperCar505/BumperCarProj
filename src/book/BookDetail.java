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
	



	public BookDetail() {
			
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 890);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//		lblNewLabel_7.setVerticalAlignment(SwingConstants.CENTER);
//		statusLabel.setIcon(new ImageIcon(BookDetail.class.getResource("/img/statusYellow.png")));
		
		btnBook = new JButton();
		btnBook.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnBook.setBounds(332, 765, 150, 50);
		contentPane.add(btnBook);
		
		JPanel panel = new JPanel();
		panel.setBounds(73, 137, 458, 618);
//		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("고객명");
		lblNewLabel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel.setBounds(0, 36, 144, 36);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("차번호");
		lblNewLabel_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_1.setBounds(0, 108, 144, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("브랜드");
		lblNewLabel_2.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_2.setBounds(0, 180, 144, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_3.setBounds(0, 324, 144, 36);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("서비스");
		lblNewLabel_4.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_4.setBounds(0, 396, 144, 36);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("예약 시간");
		lblNewLabel_5.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_5.setBounds(0, 468, 144, 36);
		panel.add(lblNewLabel_5);
		
		cusName = new JTextField();
		cusName.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusName.setBounds(169, 24, 289, 50);
		panel.add(cusName);
//		cusName.setColumns(10);
		
		cusCarNum = new JTextField();
		cusCarNum.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarNum.setColumns(10);
		cusCarNum.setBounds(169, 98, 289, 50);
		panel.add(cusCarNum);
		
		cusCarBrand = new JTextField();
		cusCarBrand.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarBrand.setColumns(10);
		cusCarBrand.setBounds(169, 172, 289, 50);
		panel.add(cusCarBrand);
		
		cusCarType = new JTextField();
		cusCarType.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusCarType.setColumns(10);
		cusCarType.setBounds(169, 246, 289, 50);
		panel.add(cusCarType);
		
		cusTel = new JTextField();
		cusTel.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusTel.setColumns(10);
		cusTel.setBounds(169, 320, 289, 50);
		panel.add(cusTel);
		
		JComboBox srvName = new JComboBox();
		srvName.setBounds(169, 394, 289, 50);
		panel.add(srvName);
		
		JLabel lblNewLabel_2_1 = new JLabel("차종");
		lblNewLabel_2_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_2_1.setBounds(0, 252, 144, 36);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("정비 완료 시간");
		lblNewLabel_5_1.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		lblNewLabel_5_1.setBounds(0, 540, 144, 36);
		panel.add(lblNewLabel_5_1);
		
		cusBookTime = new JTextField();
		cusBookTime.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		cusBookTime.setColumns(10);
		cusBookTime.setBounds(169, 468, 289, 50);
		panel.add(cusBookTime);
		
		completedTime = new JTextField();
		completedTime.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		completedTime.setColumns(10);
		completedTime.setBounds(169, 542, 289, 50);
		panel.add(completedTime);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(BookDetail.class.getResource("/img/YellowCat.png")));
		lblNewLabel_6.setBounds(176, 45, 223, 63);
		contentPane.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(73, 765, 54, 50);
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
		
		
		statusBox.setBounds(130, 765, 150, 50);
		
//		statusBox.addItem(statusSymbol);
//		statusBox.addItem(statusText);
//		statusBox.setRenderer();
		contentPane.add(statusBox);
		
		
	}
	
	public void setSchedule(int mainNum, String cusName, String cusCarNum, String cusCarBrand, String cusCarType, String cusTel,
			String srvName, String mainStartDay, String mainStartTime, String mainEndDay, String mainEndTime) {
		this.mainNum = mainNum;
		this.cusName.setText(cusName);
		this.cusCarNum.setText(cusCarNum);
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
		this.cusCarNum.setEnabled(true);
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
