package all;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class CusMgr_edit extends JFrame{

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
	private JPanel contentPane;
	private CusMgr parent;
	
	int aa = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CusMgr_edit(int a) {
		this.setVisible(true);
		this.setTitle("다고쳐카센터 - 기존고객 수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 615, 800);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		cusName = new JTextField();
		cusName.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusName.setBounds(200, 19, 307, 54);
		cusName.setHorizontalAlignment(SwingConstants.CENTER);
	
		contentPane.add(cusName);
		cusName.setColumns(10);
		
				
				
				
				
		cusCarNum = new JTextField();
		cusCarNum.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
		cusCarNum.setColumns(10);
		cusCarNum.setBounds(200, 95, 307, 54);
		cusCarNum.setHorizontalAlignment(SwingConstants.CENTER);

		contentPane.add(cusCarNum);
				
				cusCarBrand = new JTextField();
				cusCarBrand.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
				cusCarBrand.setColumns(10);
				cusCarBrand.setBounds(200, 175, 307, 54);
				cusCarBrand.setHorizontalAlignment(SwingConstants.CENTER);
				contentPane.add(cusCarBrand);

						
						cusCarType = new JTextField();
						cusCarType.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						cusCarType.setColumns(10);
						cusCarType.setBounds(200, 249, 307, 54);
						cusCarType.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(cusCarType);
						
						cusKm = new JTextField();
						cusKm.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						cusKm.setColumns(10);
						cusKm.setBounds(200, 329, 307, 54);
						cusKm.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(cusKm);
						
						cusZip = new JTextField();
						cusZip.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						cusZip.setColumns(10);
						cusZip.setBounds(200, 404, 307, 54);
						cusZip.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(cusZip);
						
						cusAddr = new JTextField();
						cusAddr.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						cusAddr.setColumns(10);
						cusAddr.setBounds(200, 478, 307, 54);
						cusAddr.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(cusAddr);
						
						cusTel = new JTextField();
						cusTel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						cusTel.setColumns(10);
						cusTel.setBounds(200, 551, 307, 54);
						cusTel.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(cusTel);
						
						cusDate = new JTextField();
						cusDate.setEnabled(false);
						cusDate.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						cusDate.setColumns(10);
						cusDate.setBounds(200, 625, 307, 54);
						cusDate.setHorizontalAlignment(SwingConstants.CENTER);
						contentPane.add(cusDate);
						
						
						JLabel lblCusName = new JLabel("고객이름");
						lblCusName.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCusName.setBounds(66, 10, 122, 72);
						contentPane.add(lblCusName);
						
						JLabel lblCarNum = new JLabel("차 번호");
						lblCarNum.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCarNum.setBounds(66, 86, 122, 72);
						contentPane.add(lblCarNum);
						
						JLabel lblCarBrand = new JLabel("브랜드");
						lblCarBrand.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCarBrand.setBounds(66, 166, 122, 72);
						contentPane.add(lblCarBrand);
						
						JLabel lblCarType = new JLabel("차종");
						lblCarType.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCarType.setBounds(66, 240, 122, 72);
						contentPane.add(lblCarType);
						
						JLabel lblCusKm = new JLabel("주행거리");
						lblCusKm.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCusKm.setBounds(66, 320, 122, 72);
						contentPane.add(lblCusKm);
						
						JLabel lblCusZip = new JLabel("우편번호");
						lblCusZip.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCusZip.setBounds(66, 395, 122, 72);
						contentPane.add(lblCusZip);
						
						JLabel lblCusAddr = new JLabel("주소");
						lblCusAddr.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCusAddr.setBounds(76, 469, 122, 72);
						contentPane.add(lblCusAddr);
						
						lblCusTel = new JLabel("전화번호");
						lblCusTel.setBounds(66, 542, 122, 72);
						contentPane.add(lblCusTel);
						lblCusTel.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						
						JLabel lblCusDate = new JLabel("가입날짜");
						lblCusDate.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
						lblCusDate.setBounds(66, 616, 122, 72);
						contentPane.add(lblCusDate);
						
						JButton btnCusEditSave = new JButton("저장");
						btnCusEditSave.setFont(new Font("나눔바른고딕", Font.BOLD, 30));
						btnCusEditSave.setBounds(220, 690, 162, 61);
						btnCusEditSave.setBackground(new Color(244, 204, 204));
						btnCusEditSave.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
					            Color.red, Color.red));
						contentPane.add(btnCusEditSave);
		
		
		//////////////////////db에 있는 값 넣기
		YuriCusMgr_mgr mgr = new YuriCusMgr_mgr();
		YuriCusMgrBean bean =  mgr.select_(a);
		
		cusName.setText(bean.getCusName());
		cusCarNum.setText(bean.getCusCarNum());
		cusCarBrand.setText(bean.getCusCarBrand());
		cusCarType.setText(bean.getCusCarType());
		cusKm.setText(String.valueOf(bean.getCusKm()));
		cusZip.setText(String.valueOf(bean.getCusZip()));
		cusAddr.setText(bean.getCusAddr());
		cusTel.setText(bean.getCusTel());
		cusDate.setText(bean.getCusDate());

		aa = a;
		
		
		btnCusEditSave.addActionListener(new ActionListener() {
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
				
				mgr.updateCusMgr(bean, a);
				parent.requestGetInfo(parent);
				
				DialogManager.createMsgDialog("저장 되었습니다.", "\\img\\success1.png",
						"성공", JOptionPane.PLAIN_MESSAGE);
				
				dispose();
			}
		});	
	}

	public CusMgr_edit(CusMgr parent, int selectedRowNumber) {
		this(selectedRowNumber);
		this.parent = parent;
	}
}
