package all;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

// ComServiceList
public class ComSrvList extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTable tableSrvList;
	private JScrollPane scSrvList;
	private JButton btnAddsrv;
	private JButton btnEditSrv;
	private JButton btnDelSrv;
	private JButton btnBackMain;
	private JLabel lblYellowCat;
	private final int FONT_SIZE = 21;
	private LoginManager loginManager;
	private Vector<String> headerNames = new Vector<>(Arrays.asList("번호", "서비스 명", "제공 정비사", "서비스 가격"));
	
	public void setFont() {
		InputStream inputStream = null;
		
		// Font Setting
		try {
            String classPath = ComSrvList.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
            path = path.split("all")[0];
            inputStream = new BufferedInputStream(
                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            btnAddsrv.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnEditSrv.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnDelSrv.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnBackMain.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            
    		// Table Font
    		tableSrvList.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
    		tableSrvList.getTableHeader().setFont(font.deriveFont(Font.BOLD, FONT_SIZE));

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
	}
	
	private void setTableHeader(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		String prefix = "<html><body><table><tr><td height=50>";
		String suffix = "</td></tr></table></body><html>";
		
		for (int col = 0; col < columnModel.getColumnCount(); col++) {
		    TableColumn column = columnModel.getColumn(col);
		    String text = prefix + columnModel.getColumn(col).getHeaderValue().toString() + suffix;
		    column.setHeaderValue(text);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComSrvList frame = new ComSrvList();
					frame.setVisible(true);
					frame.setFont();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private List<ComSrvBeans> getDbService(String comId) {
		List<ComSrvBeans> list = new ArrayList<ComSrvBeans>();
		String query = "SELECT ser.srvNum, ser.srvName, tech.techName, u.unitName, u.unitPrice "
				+ "FROM service AS ser "
				+ "INNER JOIN technician AS tech ON ser.srvTechNum = tech.techNum "
				+ "INNER JOIN detail AS de ON ser.srvNum = de.dtlSrvNum "
				+ "INNER JOIN unit AS u ON de.dtlUnitNum = u.unitNum "
				+ "WHERE u.unitName LIKE '공임비%' AND tech.techComNum = ? "
				+ "AND ser.deleted_yn = 'N' "
				+ "ORDER BY ser.srvNum";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query, comId);
		List<HashMap<String, String>> result = communicator.executeQueryToList(
				"srvNum", "srvName", "techName", "unitName", "unitPrice");
		if(result == null) {
			return null;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				ComSrvBeans beans = new ComSrvBeans();
				beans.setSrvNum(Integer.parseInt(row.get("srvNum")));
				beans.setSrvName(row.get("srvName"));
				beans.setTechName(row.get("techName"));
				beans.setUnitName(row.get("unitName"));
				beans.setUnitPrice(Integer.parseInt(row.get("unitPrice")));
				list.add(beans);
			}
		}
		
		return list;
	}
	
	private List<Integer> getDbServiceNumber(String comId, String srvName) {
		List<Integer> list = new ArrayList<Integer>();
		String query = "SELECT ser.srvNum "
				+ "FROM service AS ser "
				+ "INNER JOIN technician AS tech ON ser.srvTechNum = tech.techNum "
				+ "INNER JOIN detail AS de ON ser.srvNum = de.dtlSrvNum "
				+ "INNER JOIN unit AS u ON de.dtlUnitNum = u.unitNum "
				+ "WHERE u.unitName LIKE '공임비%' AND tech.techComNum = ? "
				+ "AND srvName = ? AND ser.deleted_yn = 'N' "
				+ "ORDER BY ser.srvNum";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query, comId, srvName);
		List<HashMap<String, String>> result = communicator.executeQueryToList("srvNum");
		if(result == null) {
			return null;
		} else {
			for(int i = 0; i < result.size(); ++i) {
				HashMap<String, String> row = result.get(i);
				list.add(Integer.parseInt(row.get("srvNum")));
			}
		}
		
		return list;
	}
	
	private boolean setDbDetailNumber(List<Integer> detailsrvNumbers) {
		String query = "UPDATE detail SET deleted_yn = 'Y' "
				+ "WHERE dtlSrvNum IN ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendLastValueQuery(detailsrvNumbers.size());
		
		for(int i = 0; i < detailsrvNumbers.size(); ++i) {
			communicator.addParams(detailsrvNumbers.get(i));
		}
		
		int result = communicator.executeUpdate();
		
		if(result != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean setDbServiceNumber(List<Integer> srvNumbers) {
		String query = "UPDATE service SET deleted_yn = 'Y'"
				+ "WHERE srvNum IN ";
		
		QueryCommunicator communicator = new QueryCommunicator();
		communicator.setQuery(query);
		communicator.appendLastValueQuery(srvNumbers.size());
		
		for(int i = 0; i < srvNumbers.size(); ++i) {
			communicator.addParams(srvNumbers.get(i));
		}
		
		int result = communicator.executeUpdate();
		
		if(result != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean deleteService(String comId, String... srvNames) {
		boolean result = false;
		
		for(int i = 0; i < srvNames.length; ++i) {
			List<Integer> srvNumbers = getDbServiceNumber(comId, srvNames[i]);
			result = setDbDetailNumber(srvNumbers);
			result = setDbServiceNumber(srvNumbers);
		}
		
		return result;
	}
	
	private List<Vector<String>> reassembleData(List<ComSrvBeans> beans) {
		List<Vector<String>> tableRows = new ArrayList<Vector<String>>();
		HashMap<String, String> provideTechList = new HashMap<String, String>();
		HashMap<String, String> srvPriceInfos = new HashMap<String, String>();
		
		for(int i = 0; i < beans.size(); ++i) {
			ComSrvBeans comSrvBeans = beans.get(i);
			if(provideTechList.getOrDefault(comSrvBeans.getSrvName(), "").equals("")) {
				provideTechList.put(comSrvBeans.getSrvName(), comSrvBeans.getTechName());
			} else {
				String provideTechNames = provideTechList.get(comSrvBeans.getSrvName());
				provideTechList.put(comSrvBeans.getSrvName(), provideTechNames + ", " + comSrvBeans.getTechName());
			}
			
			if(srvPriceInfos.getOrDefault(comSrvBeans.getSrvName(), "").equals("")) {
				srvPriceInfos.put(comSrvBeans.getSrvName(), comSrvBeans.getUnitName() + "(" + 
						comSrvBeans.getUnitPrice() + ")");
			}
		}
		
		int rowCount = 1;
		Set<String> srvNames = provideTechList.keySet();
		for(String srvName : srvNames) {
			Vector<String> tableRow = new Vector<String>();
			String provideTechs = provideTechList.get(srvName);
			String srvPriceInfo = srvPriceInfos.get(srvName);
			tableRow.add(String.valueOf(rowCount++));
			tableRow.add(srvName);
			tableRow.add(provideTechs);
			tableRow.add(srvPriceInfo);
			tableRows.add(tableRow);
		}
		
		return tableRows;
	}
	
	private void setTableColumn(List<Vector<String>> datas) {
		// 가져온 데이터를 테이블에 저장
		
		DefaultTableModel model = new DefaultTableModel(headerNames, 0);
		for(int i = 0; i < datas.size(); ++i) {
			model.addRow(datas.get(i));
		}
		
		tableSrvList.setModel(model);
	}
	
	private void setTableTextCenter(JTable table) {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getColumn("번호").setCellRenderer(render);
		table.getColumn("서비스 명").setCellRenderer(render);
		table.getColumn("제공 정비사").setCellRenderer(render);
		table.getColumn("서비스 가격").setCellRenderer(render);
	}
	
	private void resizeTableRow(JTable table) {
		table.setRowHeight(50);
	}
	
	private void resizeTableColumn(JTable table) {
		table.getColumn("번호").setPreferredWidth(30);
		table.getColumn("서비스 명").setPreferredWidth(30);
		table.getColumn("제공 정비사").setPreferredWidth(150);
		table.getColumn("서비스 가격").setPreferredWidth(200);
	}
	
	private void resizeTableHeader(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		String prefix = "<html><body><table><tr><td height=50>";
		String suffix = "</td></tr></table></body><html>";
		
		for (int col = 0; col < columnModel.getColumnCount(); col++) {
		    TableColumn column = columnModel.getColumn(col);
		    String text = prefix + columnModel.getColumn(col).getHeaderValue().toString() + suffix;
		    column.setHeaderValue(text);
		}
	}
	
	private int[] getSelectedNumbers(JTable table) {
		int[] selectedRows = table.getSelectedRows();
		int[] selectedNumbers = new int[selectedRows.length];
		
		for(int i = 0; i < selectedRows.length; ++i) {
			selectedNumbers[i] = Integer.parseInt(table.getValueAt(selectedRows[i], 0).toString());
		}
		
		return selectedNumbers;
	}
	
	private String[] getSelectedSrvNames(JTable table) {
		int[] selectedRows = table.getSelectedRows();
		String[] selectedsrvNames = new String[selectedRows.length];
		
		for(int i = 0; i < selectedRows.length; ++i) {
			selectedsrvNames[i] = table.getValueAt(selectedRows[i], 1).toString();
		}
		
		return selectedsrvNames;
	}
	
	public void requestRefreshAllDatas(ComSrvList comSrvList) {
		if(comSrvList.equals(this)) {
			refreshAllDatas();
		}
	}
	
	private void refreshAllDatas() {
		setTableColumn(reassembleData(getDbService(loginManager.getLogComNum())));
		setTableTextCenter(tableSrvList);
		resizeTableRow(tableSrvList);
		resizeTableColumn(tableSrvList);
		resizeTableHeader(tableSrvList); // 반드시 이게 마지막으로 설정되어야 함
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == btnAddsrv) { // 서비스 신규 추가
			new ComSrvListSub1(this).setFont();
		} else if(obj == btnEditSrv) { // 기존 서비스 수정
			int selectedRow = tableSrvList.getSelectedRow();
			
			if(selectedRow == -1) {
				DialogManager.createMsgDialog("선택된 셀이 없습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			String selectedSrvName = tableSrvList.getValueAt(selectedRow, 1).toString();
			String[] selectedTechNames = tableSrvList.getValueAt(selectedRow, 2).toString().trim().split(", ");
			String selectedSrvPrice = tableSrvList.getValueAt(selectedRow, 3).toString();
			
			ArrayList<String> techList = new ArrayList<>(List.of(selectedTechNames));
			
			new ComSrvListSub1(this, selectedSrvName, selectedSrvPrice, techList).setFont();
		} else if(obj == btnDelSrv) { // 기존 서비스 삭제
			int[] selectedRows = getSelectedNumbers(tableSrvList);
			
			if(selectedRows.length == 0) {
				DialogManager.createMsgDialog("선택된 셀이 없습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			int result = DialogManager.createMsgDialog("정말로 삭제 할까요?", "\\img\\question6.png",
					"알림", JOptionPane.YES_NO_OPTION);
			if(result == 0) {
				if(deleteService(loginManager.getLogComNum(), getSelectedSrvNames(tableSrvList))) {
					refreshAllDatas();
					DialogManager.createMsgDialog("삭제 되었습니다.", "\\img\\success1.png",
							"알림", JOptionPane.PLAIN_MESSAGE);
				} else {
					DialogManager.createMsgDialog("삭제가 실패했습니다.", "\\img\\information5.png",
							"에러", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				DialogManager.createMsgDialog("삭제가 취소되었습니다.", "\\img\\information5.png",
						"알림", JOptionPane.PLAIN_MESSAGE);
			}
		} else if(obj == btnBackMain) {
			new ComMyPage();
			this.dispose();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public ComSrvList() {
		loginManager = LoginManager.getInstance();
		
		setTitle("다고쳐카센터 - 서비스 목록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableSrvList = new JTable();
		tableSrvList.setAutoCreateRowSorter(true);
		tableSrvList.setDefaultEditor(Object.class, null); // 테이블 수정 안되게
		tableSrvList.getTableHeader().setResizingAllowed(false);
		
		// Column Not Move
		tableSrvList.getTableHeader().setReorderingAllowed(false);
		
		// Table Set Area
		scSrvList = new JScrollPane(tableSrvList);
		scSrvList.setVisible(true);
		scSrvList.setBounds(100, 145, 1462, 750);
		
		contentPane.add(scSrvList);
		
		// Button Create
		btnAddsrv = new JButton("추가");
		btnAddsrv.setBackground(new Color(244, 204, 204));
		btnAddsrv.setBounds(100, 70, Size.BTN_S_W, Size.BTN_S_H);
		btnAddsrv.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnAddsrv.addActionListener(this);
		contentPane.add(btnAddsrv);
		
		btnEditSrv = new JButton("수정");
		btnEditSrv.setBackground(new Color(244, 204, 204));
		btnEditSrv.setBounds(275, 70, Size.BTN_S_W, Size.BTN_S_H);
		btnEditSrv.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnEditSrv.addActionListener(this);
		contentPane.add(btnEditSrv);
		
		btnDelSrv = new JButton("삭제");
		btnDelSrv.setBackground(new Color(244, 204, 204));
		btnDelSrv.setBounds(450, 70, Size.BTN_S_W, Size.BTN_S_H);
		btnDelSrv.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnDelSrv.addActionListener(this);
		contentPane.add(btnDelSrv);
		
		btnBackMain = new JButton("돌아가기");
		btnBackMain.setBackground(new Color(244, 204, 204));
		btnBackMain.setBounds(670, 918, Size.BTN_B_W, Size.BTN_B_H);
		btnBackMain.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnBackMain.addActionListener(this);
		contentPane.add(btnBackMain);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setIcon(new ImageIcon(ComSrvList.class.getResource("/img/YellowCat.png")));
		lblYellowCat.setBounds(710, 50, 230, 80);
		contentPane.add(lblYellowCat);
		
		this.setFont();
		
		refreshAllDatas();
	}
}
