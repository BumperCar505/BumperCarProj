package all;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TechListEdit_add extends JFrame {

	private JPanel contentPane;
	private JTextField techName;
	private JTextField techTel;
	private JTextField techLv;
	private JButton btnTechReg;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TechListEdit_add frame = new TechListEdit_add();
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
	public TechListEdit_add() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		폼 크기 : 600 * 500
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel techNamelbl = new JLabel("정비사 이름");
		techNamelbl.setBounds(97, 93, 390, 47);
		techNamelbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(techNamelbl);
		
		JLabel techTellbl = new JLabel("정비사 전화번호");
		techTellbl.setBounds(97, 183, 390, 47);
		techTellbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(techTellbl);
		
		JLabel techLvlbl = new JLabel("정비사 직급");
		techLvlbl.setBounds(97, 273, 390, 47);
		techLvlbl.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		contentPane.add(techLvlbl);
		
		techName = new JTextField();
		techName.setBounds(97, 135, 390, 39);
		contentPane.add(techName);
		techName.setColumns(10);
		
		techTel = new JTextField();
		techTel.setColumns(10);
		techTel.setBounds(97, 225, 390, 39);
		contentPane.add(techTel);
		
		techLv = new JTextField();
		techLv.setColumns(10);
		techLv.setBounds(97, 315, 390, 39);
		contentPane.add(techLv);
		
		btnTechReg = new JButton("등록");
		btnTechReg.setFont(new Font("나눔바른고딕", Font.PLAIN, 21));
		btnTechReg.setBounds(165, 388, Size.BTN_B_W, Size.BTN_B_H);
		btnTechReg.setBackground(new Color(244, 204, 204));
		btnTechReg.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		contentPane.add(btnTechReg);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TechListEdit_add.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(165, 10, 254, 80);
		contentPane.add(lblNewLabel);
		
		
		// 등록 버튼 누르면 현재 창이 닫히면서 TechListEdit 창이 뜸(데이터 이동 완료상태)
		// 또는 추가 버튼 누르면 기존 폼이 닫히고, 등록 버튼 누를때 기존 폼 새로 뜨게??????????????????????????????????????????????????????????
		btnTechReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GwakMemberMgr mgr = new GwakMemberMgr();
				GwakMemberBean bean =  new GwakMemberBean();

				bean.setTechName(techName.getText());
				bean.setTechTel(techTel.getText());
				bean.setTechLv(techLv.getText());	

				
				mgr.add(bean);
				
				TechListEdit a1 = new TechListEdit();
				a1.setVisible(true);
				
				dispose();//--
			}
		});
	}

}
