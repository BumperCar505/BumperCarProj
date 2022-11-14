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
	private ComSrvList parent;
	private String seletedSrvName;
	private List<String> seletedTechList;
	
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
	
	private boolean setDbService(List<Integer> techNums, String srvName) {
		String query = "INSERT INTO service(srvTechNum, srvName) "
				+ "VALUES";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendInsertValueQuery(2, techNums.size());
		
		for(int i = 0; i < techNums.size(); ++i) {
			communicator.addParams(techNums.get(i), srvName.trim());
		}
		
		return communicator.executeUpdate() != -1 ? true : false;
	}
	
	private boolean setDbServiceStatus(List<Integer> srvNums) {
		String query = "UPDATE service SET deleted_yn = 'Y'"
				+ "WHERE srvNum IN ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendLastValueQuery(srvNums.size());
		
		for(int i = 0; i < srvNums.size(); ++i) {
			communicator.addParams(srvNums.get(i));
		}
		
		return communicator.executeUpdate() != -1 ? true : false;
	}
	
	private boolean setDbDetailStatus(List<Integer> srvNums) {
		String query = "UPDATE detail SET deleted_yn = 'Y'"
				+ "WHERE dtlSrvNum IN ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendLastValueQuery(srvNums.size());
		
		for(int i = 0; i < srvNums.size(); ++i) {
			communicator.addParams(srvNums.get(i));
		}
		
		return communicator.executeUpdate() != -1 ? true : false;
	}
	
	private List<Integer> getDbServiceNumber(List<Integer> techNums, String srvName) {
		List<Integer> list = new ArrayList<Integer>();
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
				list.add(Integer.parseInt(srvNum));
			}
		}
		
		return list;
	}
	
	private List<Integer> getDbServiceNumber(String comId, String srvName) {
		List<Integer> list = new ArrayList<Integer>();
		String query = "SELECT ser.srvNum FROM service AS ser "
				+ "INNER JOIN technician AS tech ON ser.srvTechNum = tech.techNum "
				+ "WHERE ser.srvName = ? AND tech.techComNum = ? "
				+ "AND ser.deleted_yn = 'N'";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query, srvName, comId);
		List<HashMap<String, String>> result = communicator.executeQueryToList("srvNum");
		if(result == null) {
			return null;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				String srvNum = row.get("srvNum");
				list.add(Integer.parseInt(srvNum));
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
	
	private boolean setDbPriceInfo(List<Integer> srvNumbers, String priceNumber) {
		String query = "INSERT INTO detail(dtlSrvNum, dtlUnitNum, dtlUnitQty) "
				+ "VALUES";
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendInsertValueQuery(3, srvNumbers.size());
		
		for(int i = 0; i < srvNumbers.size(); ++i) {
			communicator.addParams(srvNumbers.get(i), priceNumber, 1);
		}
		
		return communicator.executeUpdate() != -1 ? true : false;
	}
	
	private boolean addNewService(String srvName, List<String> techInfos, String priceInfo) {
		if(isDbUsedService(loginManager.getLogComNum(), srvName)) {
			return false;
		}
		
		List<Integer> techNums = new ArrayList<Integer>();
		for(int i = 0; i < techInfos.size(); ++i) {
			techNums.add(Integer.parseInt(techInfos.get(i).split("\\.")[0]));
		}
		
		setDbService(techNums, srvName);
		List<Integer> srvNums = getDbServiceNumber(techNums, srvName);
		String priceNumber = getDbPriceNumber(priceInfo.split("\\(")[0]);
		setDbPriceInfo(srvNums, priceNumber);
		
		return true;
	}
	
	private boolean editService(String comId, String srvName, String priceInfo, List<String> techInfos) {
		if(seletedSrvName.equals(srvName)) {
			// 서비스 이름이 변경되지 않았다면 아무것도 안함
		} else if(isDbUsedService(loginManager.getLogComNum(), srvName)) {
			return false;
		}
		
		List<Integer> oldTechNums = new ArrayList<Integer>();
		for(int i = 0; i < seletedTechList.size(); ++i) {
			oldTechNums.add(Integer.parseInt(seletedTechList.get(i).split("\\.")[0]));
		}
		
		List<Integer> newTechNums = new ArrayList<Integer>();
		for(int i = 0; i < techInfos.size(); ++i) {
			newTechNums.add(Integer.parseInt(techInfos.get(i).split("\\.")[0]));
		}
		
		List<Integer> oldSrvNums = getDbServiceNumber(oldTechNums, seletedSrvName);
		boolean result1 = setDbServiceStatus(oldSrvNums);
		boolean result2 = setDbDetailStatus(oldSrvNums);
		String srvPriceNumber = getDbPriceNumber(priceInfo.split("\\(")[0]);
		boolean result3 = setDbService(newTechNums, srvName);
		List<Integer> newSrvNums = getDbServiceNumber(comId, srvName);
		boolean result4 = setDbPriceInfo(newSrvNums, srvPriceNumber);
		seletedSrvName = srvName;
		seletedTechList = getListData();
		
		return result1 && result2 && result3 && result4;
	}
	
	private boolean isBlank() {
		if(textFieldSrvName.getText().equals("")) {
			return true;
		}
		
		if(getListData().size() == 0) {
			return true;
		}
		
		return false;
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
			if(isBlank()) {
				DialogManager.createMsgDialog("서비스 명이나 정비사가 선택되지않았습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			String srvName = textFieldSrvName.getText().trim();
			List<String> techList = getListData();
			String priceInfo = comboBoxPrice.getSelectedItem().toString();
			if(addNewService(srvName, techList, priceInfo)) {
				parent.requestRefreshAllDatas(parent);
				DialogManager.createMsgDialog("정상적으로 처리되었습니다.", "\\img\\success1.png",
						"알림", JOptionPane.PLAIN_MESSAGE);
			} else {
				DialogManager.createMsgDialog("동일한 서비스가 이미 사용중입니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
			}
		} else if(obj == btnSrvSave) { 
			if(isBlank()) {
				DialogManager.createMsgDialog("서비스 명이나 정비사가 선택되지않았습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			String srvName = textFieldSrvName.getText().trim();
			List<String> techList = getListData();
			String priceInfo = comboBoxPrice.getSelectedItem().toString();
			if(editService(loginManager.getLogComNum(), srvName, priceInfo, techList)) {
				parent.requestRefreshAllDatas(parent);
				DialogManager.createMsgDialog("정상적으로 처리되었습니다.", "\\img\\success1.png",
						"알림", JOptionPane.PLAIN_MESSAGE);
			} else {
				DialogManager.createMsgDialog("이미 사용중인 서비스 이름이거나<br> 수정에 실패했습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
			}
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
	
	private List<String> getComboBoxData(JComboBox<String> comboBox) {
		List<String> list = new ArrayList<String>();
		
		for(int i = 0; i < comboBox.getItemCount(); ++i) {
			list.add(comboBox.getItemAt(i));
		}
		
		return list;
	}
	
	private List<String> reassembleTechNames(List<String> techAllName, List<String> regTechNames) {
		List<String> list = new ArrayList<String>();
		
		for(int aCount = 0; aCount < techAllName.size(); ++aCount) {
			for(int rCount = 0; rCount < regTechNames.size(); ++rCount) {
				if(techAllName.get(aCount).split(" ")[1].equals(regTechNames.get(rCount))) {
					list.add(techAllName.get(aCount));
					break;
				}
			}
		}
		
		return list;
	}
	
	/**
	 * Create the frame.
	 */
	public ComSrvListSub1() {
		loginManager = LoginManager.getInstance();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 518);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
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
	}
	
	public ComSrvListSub1(ComSrvList parent) {
		this();
		this.setTitle("신규 서비스 등록");
		btnSrvReg.setVisible(true);
		this.parent = parent;
	}
	
	// 테이블에서 셀 선택후 수정버튼을 클릭했을때 이 생성자 호출
	public ComSrvListSub1(ComSrvList parent, String srvName, String srvPrice, List<String> srvTechList) {
		this();
		this.setTitle("기존 서비스 수정");
		textFieldSrvName.setText(srvName);
		comboBoxPrice.setSelectedItem(srvPrice);
		btnSrvSave.setVisible(true);
		addListData(reassembleTechNames(getComboBoxData(comboBoxTech), srvTechList));
		this.parent = parent;
		seletedSrvName = srvName;
		seletedTechList = getListData();
	}
}
