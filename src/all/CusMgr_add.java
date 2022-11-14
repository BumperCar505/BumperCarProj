
package all;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;

//import com.mysql.cj.xdevapi.Statement;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class CusMgr_add {

	private JFrame frame;
	private JTextField cusName;
	private JTextField cusCarNum;
	private JTextField cusCarBrand;
	private JTextField cusCarType;
	private JTextField cusKm;
	private JTextField cusZip;
	private JTextField cusAddr;
	private JTextField cusTel;
	private JTextField cusDate;
	private JLabel lblCusTel;
	private JLabel lblNewLabel_2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CusMgr_add window = new CusMgr_add();
//					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CusMgr_add() {

		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("다고쳐카센터 - 신규고객등록");
		frame.setBounds(100, 100, 615, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		panel.setBounds(12, 10, 574, 662);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//텍스트필드
		cusName = new JTextField();
		cusName.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusName.setBounds(185, 25, 307, 54);
		cusName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusName);
		cusName.setColumns(10);
		
		cusCarNum = new JTextField();
		cusCarNum.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusCarNum.setColumns(10);
		cusCarNum.setBounds(185, 105, 307, 54);
		cusCarNum.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusCarNum);
		
		cusCarBrand = new JTextField();
		cusCarBrand.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusCarBrand.setColumns(10);
		cusCarBrand.setBounds(185, 185, 307, 54);
		cusCarBrand.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusCarBrand);
		
		cusCarType = new JTextField();
		cusCarType.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusCarType.setColumns(10);
		cusCarType.setBounds(185, 265, 307, 54);
		cusCarType.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusCarType);
		
		cusKm = new JTextField();
		cusKm.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusKm.setColumns(10);
		cusKm.setBounds(185, 345, 307, 54);
		cusKm.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusKm);
		
		cusZip = new JTextField();
		cusZip.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusZip.setColumns(10);
		cusZip.setBounds(185, 425, 307, 54);
		cusZip.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusZip);
		
		cusAddr = new JTextField();
		cusAddr.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusAddr.setColumns(10);
		cusAddr.setBounds(185, 505, 307, 54);
		cusAddr.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusAddr);
		
		cusTel = new JTextField();
		cusTel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusTel.setColumns(10);
		cusTel.setBounds(185, 585, 307, 54);
		cusTel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cusTel);
		

		
		JLabel lblCusName = new JLabel("고객이름");
		lblCusName.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCusName.setBounds(66, 15, 122, 72);
		panel.add(lblCusName);
		
		JLabel lblCarNum = new JLabel("차 번호");
		lblCarNum.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCarNum.setBounds(66, 95, 122, 72);
		panel.add(lblCarNum);
		
		JLabel lblCarBrand = new JLabel("브랜드");
		lblCarBrand.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCarBrand.setBounds(66, 175, 122, 72);
		panel.add(lblCarBrand);
		
		JLabel lblCarType = new JLabel("차종");
		lblCarType.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCarType.setBounds(66, 255, 122, 72);
		panel.add(lblCarType);
		
		JLabel lblCusKm = new JLabel("주행거리");
		lblCusKm.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCusKm.setBounds(66, 335, 122, 72);
		panel.add(lblCusKm);
		
		JLabel lblCusZip = new JLabel("우편번호");
		lblCusZip.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCusZip.setBounds(66, 415, 122, 72);
		panel.add(lblCusZip);
		
		JLabel lblCusAddr = new JLabel("주소");
		lblCusAddr.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		lblCusAddr.setBounds(76, 500, 122, 72);
		panel.add(lblCusAddr);
		
		lblCusTel = new JLabel("전화번호");
		lblCusTel.setBounds(63, 590, 122, 46);
		panel.add(lblCusTel);
		lblCusTel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(CusMgr_add.class.getResource("/img/YellowCat.png")));
		lblNewLabel_2.setBounds(231, 38, 235, 80);
		frame.getContentPane().add(lblNewLabel_2);
		// 저장버튼
		JButton btnCusSave = new JButton("저장");
		btnCusSave.setVisible(true);
		btnCusSave.setBounds(220, 682, Size.BTN_S_W, Size.BTN_B_H);
		btnCusSave.setBackground(new Color(244, 204, 204));
		btnCusSave.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
	            Color.red, Color.red));
		frame.getContentPane().add(btnCusSave);
		btnCusSave.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
		

		

	
		
//		저장 버튼을 눌렀을 때
		btnCusSave.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	YuriCusMgr_mgr mgr = new YuriCusMgr_mgr();
	            	YuriCusMgrBean bean = new YuriCusMgrBean();
	            	
	            	bean.setCusName(cusName.getText());
	            	bean.setCusCarNum(cusCarNum.getText());
	            	bean.setCusCarBrand(cusCarBrand.getText());
	            	bean.setCusCarType(cusCarType.getText());
	            	bean.setCusKm (Integer.parseInt(cusKm.getText().toString()));
	            	bean.setCusZip (Integer.parseInt(cusZip.getText().toString()));
	            	bean.setCusAddr(cusAddr.getText());
	            	bean.setCusTel(cusTel.getText());

	            	
	            	mgr.insertCusMgr(bean);
	            	
	            	frame.dispose();
	    			new CusMgr();
	            	
	            }
	           
	            
	        });
	}
}
