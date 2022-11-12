package all;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class ComSrvListSub1 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textFieldSrvName;
	private JLabel lblSrvName;
	private JLabel lblProvideTechList;
	private JLabel lblSrvPrice;
	private JLabel lblRegTechList;
	private JLabel lblSelectedTechDel;
	private JList<String> listProvideTech;
	private JButton btnSrvReg;
	private JButton btnSrvSave;
	private JButton btnSelectedTechDel;
	private JComboBox<String> comboBoxTech;
	private JComboBox<String> comboBoxPrice;
	private JScrollPane sc;
	private final int FONT_SIZE = 20;
	private boolean comboBoxTechInitFlag = false; // 생성자 호출시 이벤트 발생해서 리스트에 값 들어가는거 방지
	private LoginManager loginManager;
	
	public ComSrvListSub1 setFont() {
		InputStream inputStream = null;
		
		// Font Setting
		try {
            String classPath = ComSrvListSub1.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
            path = path.split("all")[0];
            inputStream = new BufferedInputStream(
                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            textFieldSrvName.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            lblSrvName.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            lblProvideTechList.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            lblSrvPrice.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            listProvideTech.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnSrvReg.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnSrvSave.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            comboBoxTech.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            comboBoxPrice.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            lblRegTechList.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            lblSelectedTechDel.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnSelectedTechDel.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(inputStream != null) {
        		try {
            		inputStream.close();
        		} catch(Exception e2) { 
        			e2.printStackTrace();
        		}
        	}
        }
		
		return this;
	}
	
	private List<String> getDbTechNames(String comId) {
		// DB에 접속해서 로그인한 회사의 기술자명단을 조회해서 반환한다.
		List<String> list = new ArrayList<String>();
		String query = "SELECT techNum, techName FROM technician "
				+ "WHERE techComNum = ? "
				+ "ORDER BY techNum";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.addParams(comId);
		List<HashMap<String, String>> result = communicator.executeQueryToList("techNum", "techName");
		
		if(result == null) {
			return null;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				String techNum = row.get("techNum");
				String techName = row.get("techName");
				list.add(techNum + ". " + techName);
			}
		}
		
		return list;
	}
	
	private List<String> getDbPrice() {
		// DB에 접속해서 등록되어있는 공임비를 조회해서 반환한다.
		List<String> list = new ArrayList<String>();
		String query = "SELECT unitName, unitPrice FROM unit "
				+ "WHERE unitName LIKE '공임비%' "
				+ "ORDER BY unitName";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		List<HashMap<String, String>> result = communicator.executeQueryToList("unitName", "unitPrice");
		
		if(result == null) {
			return null;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				String priceName = row.get("unitName");
				String price = row.get("unitPrice");
				list.add(priceName + "(" + price + ")");
			}
		}
		
		return list;
	}
	
	private boolean isDbUsedService(String comId, String srvName) {
		String query = "SELECT COUNT(*) FROM service AS ser "
				+ "INNER JOIN technician AS tech ON ser.srvTechNum = tech.techNum "
				+ "WHERE ser.srvName = ? AND ser.deleted_yn = 'N' "
				+ "AND tech.techComNum = ? ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.addParams(srvName.trim(), comId);
		List<HashMap<String, String>> result = communicator.executeQueryToList("COUNT(*)");
		
		if(result == null) {
			return true;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				String count = row.get("COUNT(*)");
				
				if(count.equals("0")) {
					return false;
				} else {
					return true;
				}
			}
		}
		
		return true;
	}
	
	private void setDbService(List<Integer> techNums, String srvName) {
		String query = "INSERT INTO service(srvTechNum, srvName) "
				+ "VALUES";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendInsertValueQuery(2, techNums.size());
		
		for(int i = 0; i < techNums.size(); ++i) {
			communicator.addParams(techNums.get(i), srvName.trim());
		}
		
		communicator.executeUpdate();
	}
	
	private List<String> getDbServiceNumber(List<Integer> techNums, String srvName) {
		List<String> list = new ArrayList<String>();
		String query = "SELECT srvNum FROM service WHERE srvName = ? AND "
				+ "srvTechNum IN ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.addParams(srvName.trim());
		communicator.appendLastValueQuery(techNums.size());
		
		for(int i = 0; i < techNums.size(); ++i) {
			communicator.addParams(techNums.get(i));
		}
		
		List<HashMap<String, String>> result = communicator.executeQueryToList("srvNum");
		if(result == null) {
			return null;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				String srvNum = row.get("srvNum");
				list.add(srvNum);
			}
		}
		
		return list;
	}
	
	private String getDbPriceNumber(String priceName) {
		String priceNumber = "";
		String query = "SELECT unitNum FROM unit WHERE unitName = ? ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query, priceName);
		List<HashMap<String, String>> result = communicator.executeQueryToList("unitNum");
		if(result == null) {
			return null;
		} else {
			priceNumber = result.get(0).get("unitNum");
		}
		
		return priceNumber;
	}
	
	private void setDbPriceInfo(List<Integer> srvNumbers, String priceNumber) {
		String query = "INSERT INTO detail(dtlSrvNum, dtlUnitNum, dtlUnitQty) "
				+ "VALUES";
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendInsertValueQuery(3, srvNumbers.size());
		
		for(int i = 0; i < srvNumbers.size(); ++i) {
			communicator.addParams(srvNumbers.get(i), priceNumber, 1);
		}
		
		communicator.executeUpdate();
	}
	
	private void addNewService(String srvName, String techInfo, String priceInfo) {
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComSrvListSub1 frame = new ComSrvListSub1();
					frame.setVisible(true);
					frame.setFont();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// 테스트 코드입니다.
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == btnSrvReg) {
			System.out.println("이벤트 작동함1");
		} else if(obj == btnSrvSave) { 
			System.out.println("이벤트 작동함2");
		} else if (obj == comboBoxTech) {
			if(comboBoxTechInitFlag == false) {
				comboBoxTechInitFlag = true;
				return;
			}
			
			// 현재 설정되어있는 정비사 목록을 가져온다.
			ArrayList<String> list = getListData();
			
			// 선택되어있는 정비사가 목록에 이미 존재하는지 확인
			if(list.contains(comboBoxTech.getSelectedItem().toString()) == false) { 
				list.add(comboBoxTech.getSelectedItem().toString());
			} else {
				DialogManager.createMsgDialog("이미 목록에 존재하는<br>정비사입니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			list.sort(null);
			addListData(list);
		} else if(obj == btnSelectedTechDel) {
			String techName = listProvideTech.getSelectedValue();
			ArrayList<String> list = getListData();
			listProvideTech.removeAll();
			addListData(list, techName);
		}
	}
	
	private ArrayList<String> getListData() {
		// 현재 설정되어있는 정비사 목록을 가져온다.
		ArrayList<String> list = new ArrayList<>();
		for(int i = 0; i < listProvideTech.getModel().getSize(); ++i) {
			list.add(listProvideTech.getModel().getElementAt(i).toString());
		}
		return list;
	}
	
	private void addListData(List<String> list) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(int i = 0; i < list.size(); ++i) {
			listModel.addElement(list.get(i));
		}
		listProvideTech.setModel(listModel);
	}
	
	private void addListData(List<String> list, String ignoreName) {
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i).equals(ignoreName)) {
				continue;
			}
			listModel.addElement(list.get(i));
		}
		listProvideTech.setModel(listModel);
	}
	
	private void addComboBoxData(JComboBox<String> comboBox, List<String> list) {
		for(int i = 0; i < list.size(); ++i) {
			comboBox.addItem(list.get(i));
		}
	}
	
	/**
	 * Create the frame.
	 */
	public ComSrvListSub1() {
		loginManager = LoginManager.getInstance();
		loginManager.login("com", "6665544444");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 518);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("신규 서비스 등록");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSrvName = new JLabel("서비스 명");
		lblSrvName.setBounds(40, 18, 100, 40);
		contentPane.add(lblSrvName);
		
		lblProvideTechList = new JLabel("제공된 정비사 목록");
		lblProvideTechList.setBounds(40, 120, 200, 40);
		contentPane.add(lblProvideTechList);
		
		lblSrvPrice = new JLabel("서비스 가격");
		lblSrvPrice.setBounds(40, 350, 100, 40);
		contentPane.add(lblSrvPrice);
		
		textFieldSrvName = new JTextField();
		textFieldSrvName.setBounds(208, 17, 200, 40);
		textFieldSrvName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSrvName.setColumns(10);
		contentPane.add(textFieldSrvName);
		
		sc = new JScrollPane(listProvideTech);
		sc.setBounds(208, 127, 200, 150);
		sc.setVisible(true);
		contentPane.add(sc);
		listProvideTech = new JList<>();
		sc.setViewportView(listProvideTech);
		
		btnSrvReg = new JButton("추가");
		btnSrvReg.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		btnSrvReg.setBackground(new Color(244, 204, 204));
		btnSrvReg.setBounds(150, 408, Size.BTN_S_W, Size.BTN_S_H);
		btnSrvReg.setVisible(false);
		btnSrvReg.addActionListener(this);
		contentPane.add(btnSrvReg);
		
		btnSrvSave = new JButton("저장");
		btnSrvSave.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		btnSrvSave.setBackground(new Color(244, 204, 204));
		btnSrvSave.setBounds(150, 408, Size.BTN_S_W, Size.BTN_S_H);
		btnSrvSave.setVisible(false);
		btnSrvSave.addActionListener(this);
		contentPane.add(btnSrvSave);
		
		comboBoxTech = new JComboBox<>();
		comboBoxTech.setBounds(208, 79, 200, 32);
		comboBoxTech.addActionListener(this);
		contentPane.add(comboBoxTech);
		
		lblRegTechList = new JLabel("등록된 정비사 목록");
		lblRegTechList.setBounds(40, 75, 200, 40);
		contentPane.add(lblRegTechList);
		
		lblSelectedTechDel = new JLabel("선택된 정비사 삭제");
		lblSelectedTechDel.setBounds(40, 295, 200, 40);
		contentPane.add(lblSelectedTechDel);
		
		btnSelectedTechDel = new JButton("삭제하기");
		btnSelectedTechDel.setBounds(208, 295, 200, 40);
		btnSelectedTechDel.addActionListener(this);
		contentPane.add(btnSelectedTechDel);
		
		comboBoxPrice = new JComboBox<String>();
		comboBoxPrice.setBounds(208, 355, 200, 32);
		contentPane.add(comboBoxPrice);
		
		addComboBoxData(comboBoxTech, getDbTechNames(loginManager.getLogComNum()));
		addComboBoxData(comboBoxPrice, getDbPrice());
		btnSrvReg.setVisible(true);
	}
	
	// 테이블에서 셀 선택후 수정버튼을 클릭했을때 이 생성자 호출
	public ComSrvListSub1(String srvName, String srvPrice, List<String> srvTechList) {
		this();
		this.setTitle("기존 서비스 수정");
		textFieldSrvName.setText(srvName);
		comboBoxPrice.setSelectedItem(srvPrice);
		btnSrvReg.setVisible(false);
		btnSrvSave.setVisible(true);
		addListData(srvTechList);
	}
}
