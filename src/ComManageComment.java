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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.JCheckBox;

// CommentManage
public class ComManageComment extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTable tableCommentList;
	private JScrollPane scCommentList;
	private JLabel lblYellowCat;
	private JLabel lblScore;
	private JLabel lblEndDate;
	private JLabel lblStartDate;
	private JButton btnBackCommentMain;
	private JButton btnHideComment;
	private JButton btnSearchComment;
	private JButton btnShowComment;
	private JCheckBox checkBox;
	private final int FONT_SIZE = 21;
	private Vector<String> headerNames = new Vector<>(Arrays.asList("번호", "고객명", "방문날짜", "서비스내용", "별점", "코멘트"));
	private LoginManager loginManager;
	
	// model1 : 시작일, model2 : 종료일
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	
	public ComManageComment setFont() {
		InputStream inputStream = null;
		
		// Font Setting
		try {
            String classPath = ComManageComment.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
            inputStream = new BufferedInputStream(
                    new FileInputStream(path + "/font/NanumBarunGothic.ttf"));

            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            
            lblScore.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
            lblScore.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
            lblEndDate.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
            lblStartDate.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
            btnHideComment.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnShowComment.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnBackCommentMain.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            btnSearchComment.setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
    		// Table Font	
            tableCommentList.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
            tableCommentList.getTableHeader().setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
            checkBox.setFont(font.deriveFont(Font.BOLD, 18));

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
	
	private int[] getSelectedNumbers(JTable table) {
		int[] selectedRows = table.getSelectedRows();
		int[] selectedNumbers = new int[selectedRows.length];
		
		for(int i = 0; i < selectedRows.length; ++i) {
			selectedNumbers[i] = Integer.parseInt(table.getValueAt(selectedRows[i], 0).toString());
		}
		
		return selectedNumbers;
	}
	
	private void getLimitedReviews() {
		boolean flag = false;
		int startYear = model1.getYear();
		int startMonth = model1.getMonth();
		int startDay = model1.getDay();
		int endYear = model2.getYear();
		int endMonth = model2.getMonth();
		int endDay = model2.getDay();
		
		Calendar startDate = Calendar.getInstance();
		startDate.set(startYear, startMonth, startDay);
		Calendar endDate = Calendar.getInstance();
		endDate.set(endYear, endMonth, endDay);
		
		if(startDate.after(endDate)) {
			flag = true;
		}
		
		if(flag == true) {
			DialogManager.createMsgDialog("방문 종료일은 방문 시작일보다<br> 이전날짜가 될수없습니다.", "\\img\\information5.png",
					"에러", JOptionPane.PLAIN_MESSAGE);
		} else {
			refreshAllDatas(startDate, endDate);
			DialogManager.createMsgDialog("검색이 완료되었습니다.", "\\img\\success1.png",
					"알림", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == btnHideComment) {
			int[] selectedNumbers = getSelectedNumbers(tableCommentList);
			
			if(selectedNumbers.length == 0) {
				DialogManager.createMsgDialog("선택된 셀이 없습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			int result = DialogManager.createMsgDialog("정말로 선택한 댓글을 숨길까요?", "\\img\\question6.png",
					"알림", JOptionPane.YES_NO_OPTION);
			if(result == 0) {
				hideDbReviews(loginManager.getLogComNum(), selectedNumbers);
				
				if(checkBox.isSelected()) {
					getLimitedReviews();
				} else {
					refreshAllDatas();
					DialogManager.createMsgDialog("정상적으로 처리되었습니다.", "\\img\\success1.png",
							"알림", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				DialogManager.createMsgDialog("작업이 취소되었습니다.", "\\img\\information5.png",
						"알림", JOptionPane.PLAIN_MESSAGE);
			}
		} else if(obj == btnSearchComment) {
			if(checkBox.isSelected() == true) {
				getLimitedReviews();
			} else {
				refreshAllDatas();
				DialogManager.createMsgDialog("검색이 완료되었습니다.", "\\img\\success1.png",
						"알림", JOptionPane.PLAIN_MESSAGE);
			}
			
		} else if(obj == checkBox) {
			if(checkBox.isSelected() == true) {
				datePicker1.setVisible(true);
				datePicker2.setVisible(true);
				model1.setSelected(true);
				model2.setSelected(true);
			} else {
				datePicker1.setVisible(false);
				datePicker2.setVisible(false);
				model1.setSelected(false);
				model2.setSelected(false);
			}
		} else if(obj == btnBackCommentMain) {
			new ComMyPage();
			this.dispose();
		} else if(obj == btnShowComment) {
			int[] selectedNumbers = getSelectedNumbers(tableCommentList);
			
			if(selectedNumbers.length == 0) {
				DialogManager.createMsgDialog("선택된 셀이 없습니다.", "\\img\\information5.png",
						"에러", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			int result = DialogManager.createMsgDialog("정말로 선택한 댓글을 보이게할까요?", "\\img\\question6.png",
					"알림", JOptionPane.YES_NO_OPTION);
			if(result == 0) {
				showDbReviews(loginManager.getLogComNum(), selectedNumbers);
				
				if(checkBox.isSelected()) {
					getLimitedReviews();
				} else {
					refreshAllDatas();
					DialogManager.createMsgDialog("정상적으로 처리되었습니다.", "\\img\\success1.png",
							"알림", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				DialogManager.createMsgDialog("작업이 취소되었습니다.", "\\img\\information5.png",
						"알림", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	// 
	private void refreshAllDatas() {
		setTableColumn(getDbReviews(loginManager.getLogComNum()));
		setTableTextCenter(tableCommentList);
		resizeTableRow(tableCommentList);
		resizeTableColumn(tableCommentList);
		resizeTableHeader(tableCommentList); // 반드시 이게 마지막으로 설정되어야 함
		setAvgScore(getDbAvgReviewScore(loginManager.getLogComNum()));
	}
	
	private void refreshAllDatas(Calendar startDate, Calendar endDate) {
		setTableColumn(getDbReviews(loginManager.getLogComNum(), startDate, endDate));
		setTableTextCenter(tableCommentList);
		resizeTableRow(tableCommentList);
		resizeTableColumn(tableCommentList);
		resizeTableHeader(tableCommentList); // 반드시 이게 마지막으로 설정되어야 함
		setAvgScore(getDbAvgReviewScore(loginManager.getLogComNum(), startDate, endDate));
	}
	
	// 조회된 데이터가(모든 컬럼) List<Vector<String>> 타입으로 반환됩니다.
	private List<Vector<String>> getDbReviews(String rvwComNum) {
		// DB에서 데이터 전체 조회
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Vector<String>> datas = new ArrayList<Vector<String>>();
		String query = "SELECT re.rvwNum, re.rvwStar, re.rvwCont, re.rvwDate, re.deleted_yn, "
				+ "cus.cusName, ser.srvName "
				+ "FROM review AS re "
				+ "INNER JOIN maintenance AS main ON re.rvwMainNum = main.mainNum "
				+ "INNER JOIN customer AS cus ON main.mainCusNum = cus.cusNum "
				+ "INNER JOIN service AS ser ON main.mainSrvNum = ser.srvNum "
				+ "WHERE re.rvwComNum = ? "
				+ "ORDER BY re.rvwNum ASC ";
		
		try {
			conn = mgr.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, rvwComNum);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int number = rs.getInt("rvwNum");
				int star = rs.getInt("rvwStar");
				String comment = rs.getString("rvwCont");
				String visitedDate = rs.getString("rvwDate");
				String delete = rs.getString("deleted_yn");
				String cusName = rs.getString("cusName");
				String srvName = rs.getString("srvName");
				
				Vector<String> data = new Vector<>();
				data.add(String.valueOf(number));
				data.add(cusName);
				data.add(visitedDate);
				data.add(srvName);
				data.add(String.valueOf(star));
				data.add(comment);
				
				if(delete.equals("Y")) {
					hideStar(data, TableHeaderNumber.STAR.ordinal());
					hideComment(data, TableHeaderNumber.COMMENT.ordinal());
				} else {
					showStar(data, TableHeaderNumber.STAR.ordinal());
				}
				
				datas.add(data);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return datas;
	}
	
	// 리뷰를 기간 한정해서 조회
	private List<Vector<String>> getDbReviews(String rvwComNum, Calendar startDate, Calendar endDate) {
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String sDate = simpleDateFormat.format(startDate.getTime());
		String eDate = simpleDateFormat.format(endDate.getTime());
		
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Vector<String>> datas = new ArrayList<Vector<String>>();
		String query = "SELECT re.rvwNum, re.rvwStar, re.rvwCont, re.rvwDate, re.deleted_yn, "
				+ "cus.cusName, ser.srvName "
				+ "FROM review AS re "
				+ "INNER JOIN maintenance AS main ON re.rvwMainNum = main.mainNum "
				+ "INNER JOIN customer AS cus ON main.mainCusNum = cus.cusNum "
				+ "INNER JOIN service AS ser ON main.mainSrvNum = ser.srvNum "
				+ "WHERE re.rvwDate >= ? AND re.rvwDate <= ? "
				+ "AND re.rvwComNum = ? "
				+ "ORDER BY re.rvwNum ASC";
		
		try {
			conn = mgr.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, sDate);
			psmt.setString(2, eDate);
			psmt.setString(3, rvwComNum);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int number = rs.getInt("rvwNum");
				int star = rs.getInt("rvwStar");
				String comment = rs.getString("rvwCont");
				String visitedDate = rs.getString("rvwDate");
				String delete = rs.getString("deleted_yn");
				String cusName = rs.getString("cusName");
				String srvName = rs.getString("srvName");
				
				Vector<String> data = new Vector<>();
				data.add(String.valueOf(number));
				data.add(cusName);
				data.add(visitedDate);
				data.add(srvName);
				data.add(String.valueOf(star));
				data.add(comment);
				
				if(delete.equals("Y")) {
					hideStar(data, TableHeaderNumber.STAR.ordinal());
					hideComment(data, TableHeaderNumber.COMMENT.ordinal());
				} else {
					showStar(data, TableHeaderNumber.STAR.ordinal());
				}
				
				datas.add(data);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return datas;
	}

	private double getDbAvgReviewScore(String rvwComNum) {
		// DB에서 평균점수 조회
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "SELECT AVG(rvwStar) AS avgValue FROM review "
				+ "WHERE deleted_yn != 'Y' AND rvwComNum = ? ";
		double avgValue = 0.0;
		
		try {
			conn = mgr.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, rvwComNum);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				avgValue = rs.getDouble("avgValue");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return avgValue;
	}
	
	private double getDbAvgReviewScore(String rvwComNum, Calendar startDate, Calendar endDate) {
		// DB에서 기간한정해서 평균점수 조회
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		String sDate = simpleDateFormat.format(startDate.getTime());
		String eDate = simpleDateFormat.format(endDate.getTime());
		
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "SELECT AVG(rvwStar) AS avgValue FROM review "
				+ "WHERE rvwDate >= ? AND rvwDate <= ? "
				+ "AND deleted_yn != 'Y' AND rvwComNum = ? ";
		double avgValue = 0.0;
		
		try {
			conn = mgr.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, sDate);
			psmt.setString(2, eDate);
			psmt.setString(3, rvwComNum);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				avgValue = rs.getDouble("avgValue");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return avgValue;
	}
	
	private boolean hideDbReviews(String rvwComNum, int[] selectedNumbers) {
		// DB에 저장되어있는 리뷰중 선택한 리뷰들을 코멘트와 별점 안보이게 처리
		boolean flag = false;
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		String query = "UPDATE review SET deleted_yn = 'Y' WHERE rvwNum = ? "
				+ "AND rvwComNum = ? ";
		
		try {
			conn = mgr.getConnection();
			psmt = conn.prepareStatement(query);
			
			for(int i = 0; i < selectedNumbers.length; ++i) {
				psmt.setInt(1, selectedNumbers[i]);
				psmt.setString(2, rvwComNum);
				int result = psmt.executeUpdate();
				
				if(result == 1) {
					flag = true;
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return flag;
	}
	
	private boolean showDbReviews(String rvwComNum, int[] selectedNumbers) {
		// DB에 저장되어있는 리뷰중 선택한 리뷰들을 코멘트와 별점 보이게 변경
		boolean flag = false;
		DBConnectionMgr mgr = DBConnectionMgr.getInstance();
		Connection conn = null;
		PreparedStatement psmt = null;
		String query = "UPDATE review SET deleted_yn = 'N' WHERE rvwNum = ? "
				+ "AND rvwComNum = ? ";
		
		try {
			conn = mgr.getConnection();
			psmt = conn.prepareStatement(query);
			
			for(int i = 0; i < selectedNumbers.length; ++i) {
				psmt.setInt(1, selectedNumbers[i]);
				psmt.setString(2, rvwComNum);
				int result = psmt.executeUpdate();
				
				if(result == 1) {
					flag = true;
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage());
		} finally {
			try {
				if(psmt != null) {psmt.close();}
				if(conn != null) {conn.close();}
			} catch(SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		
		return flag;
	}
	
	private void showStar(Vector<String> data, int idx) {
		// 가져온 리뷰가 숨김처리가 아니라면 별점 표시
		String strStar = "";
		for(int i = 0; i < Integer.parseInt(data.get(idx)); ++i) {
			strStar += "★";
		}
		data.set(idx, strStar);
	}
	
	private void hideStar(Vector<String> data, int idx) {
		// 가져온 리뷰가 숨김처리라면 별점 대체
		data.set(idx, "");
	}
	
	private void hideComment(Vector<String> data, int idx) {
		// 가져온 리뷰가 숨김처리라면 문자열 대체
		data.set(idx, "숨김처리된 댓글입니다.");
	}
	
	private void setTableColumn(List<Vector<String>> datas) {
		// 가져온 데이터를 테이블에 저장
		
		DefaultTableModel model = new DefaultTableModel(headerNames, 0);
		for(int i = 0; i < datas.size(); ++i) {
			model.addRow(datas.get(i));
		}
		
		tableCommentList.setModel(model);
	}
	
	private void setTableTextCenter(JTable table) {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getColumn("번호").setCellRenderer(render);
		table.getColumn("고객명").setCellRenderer(render);
		table.getColumn("방문날짜").setCellRenderer(render);
		table.getColumn("서비스내용").setCellRenderer(render);
		table.getColumn("별점").setCellRenderer(render);
		table.getColumn("코멘트").setCellRenderer(render);
	}
	
	private void resizeTableRow(JTable table) {
		table.setRowHeight(50);
	}
	
	private void resizeTableColumn(JTable table) {
		table.getColumn("번호").setPreferredWidth(30);
		table.getColumn("고객명").setPreferredWidth(30);
		table.getColumn("방문날짜").setPreferredWidth(150);
		table.getColumn("서비스내용").setPreferredWidth(200);
		table.getColumn("별점").setPreferredWidth(30);
		table.getColumn("코멘트").setPreferredWidth(500);
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
	
	private void setAvgScore(double score) {
		// 가져온 평균점수를 저장
		lblScore.setText("평균 별점 : " + String.valueOf(score));
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComManageComment frame = new ComManageComment();
					frame.setVisible(true);
					frame.setFont();
					
					// 리뷰 전체 들고오는 테스트 코드
					// frame.setTableColumn(frame.searchDbReviews());  
					
					// 기간 한정해서 들고오는 부분 테스트 코드
					// Calendar startDate = Calendar.getInstance();
					// startDate.set(2022, 9, 10);
					// Calendar endDate = Calendar.getInstance();
					// endDate.set(2022, 10, 3);
					// frame.searchDbReviews(startDate, endDate);
					
					// 전체 평균 리뷰 점수 들고오는 테스트 코드
					// System.out.println(frame.getDbAvgReviewScore());
					
					// 기간 한정해서 리뷰 점수 들고오는 테스트 코드
					// Calendar startDate = Calendar.getInstance();
					// startDate.set(2022, 9, 10);
					// Calendar endDate = Calendar.getInstance();
					// endDate.set(2022, 10, 3);
					// System.out.println(frame.getDbAvgReviewScore(startDate, endDate));
					
					// 숨기기
					// System.out.println(frame.hideDbReviews(new int[] {1, 5, 3}));
					
					// 보여주기
					// System.out.println(frame.showDbReviews(new int[] {1, 3, 5}));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComManageComment() {
		loginManager = LoginManager.getInstance();
		loginManager.login("com", "1112233333"); // 가상 로그인 추후 삭제바람
		
		setTitle("다고쳐카센터 - 코멘트관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); // 이거 없으면 정상적으로 레이아웃 안그려진다..
		
		tableCommentList = new JTable();
		tableCommentList.setAutoCreateRowSorter(true);
		tableCommentList.setDefaultEditor(Object.class, null); // 테이블 값 수정 안되게
		tableCommentList.getTableHeader().setResizingAllowed(false);
		
		// Column Not Move
		tableCommentList.getTableHeader().setReorderingAllowed(false);
		
		// 처음 창 로딩시 전체 데이터 DB에서 조회
		setTableColumn(getDbReviews(loginManager.getLogComNum()));
		setTableTextCenter(tableCommentList);
		resizeTableRow(tableCommentList);
		resizeTableColumn(tableCommentList);
		resizeTableHeader(tableCommentList); // 반드시 이게 마지막으로 설정되어야 함
		
		// Table Set Area
		scCommentList = new JScrollPane(tableCommentList);
		scCommentList.setVisible(true);
		scCommentList.setBounds(100, 145, 1462, 750);
		
		contentPane.add(scCommentList);
		
		lblYellowCat = new JLabel("");
		lblYellowCat.setIcon(new ImageIcon(ComManageComment.class.getResource("/img/YellowCat.png")));
		lblYellowCat.setBounds(710, 50, 230, 80);
		contentPane.add(lblYellowCat);
		
		btnBackCommentMain = new JButton("돌아가기");
		btnBackCommentMain.setBackground(new Color(244, 204, 204));
		btnBackCommentMain.setBounds(670, 918, Size.BTN_B_W, Size.BTN_B_H);
		btnBackCommentMain.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnBackCommentMain.addActionListener(this);
		contentPane.add(btnBackCommentMain);
		
		btnHideComment = new JButton("댓글 숨기기");
		btnHideComment.setBackground(new Color(244, 204, 204));
		btnHideComment.setBounds(100, 70, Size.BTN_S_W, Size.BTN_S_H);
		btnHideComment.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnHideComment.addActionListener(this);
		contentPane.add(btnHideComment);
		
		lblScore = new JLabel("평균 별점 : 4.0");
		lblScore.setBounds(425, 74, 160, 42);
		setAvgScore(getDbAvgReviewScore(loginManager.getLogComNum()));
		contentPane.add(lblScore);
		
		btnSearchComment = new JButton("검색하기");
		btnSearchComment.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
		btnSearchComment.setBackground(new Color(244, 204, 204));
		btnSearchComment.setBounds(1412, 70, 150, 50);
		btnSearchComment.addActionListener(this);
		contentPane.add(btnSearchComment);
		
		lblEndDate = new JLabel("방문 종료일");
		lblEndDate.setBounds(1160, 94, 110, 42);
		contentPane.add(lblEndDate);
		
		lblStartDate = new JLabel("방문 시작일");
		lblStartDate.setBounds(1160, 52, 110, 42);
		contentPane.add(lblStartDate);
		
		model1 = new UtilDateModel();
		model1.setSelected(false);
		datePanel1 = new JDatePanelImpl(model1);
		datePicker1 = new JDatePickerImpl(datePanel1);
		datePicker1.setBounds(1275, 61, 125, 40);
		datePicker1.setVisible(false);
		contentPane.add(datePicker1);
		
		model2 = new UtilDateModel();
		model2.setSelected(false);
		datePanel2 = new JDatePanelImpl(model2);
		datePicker2 = new JDatePickerImpl(datePanel2);
		datePicker2.setBounds(1275, 102, 125, 40);
		datePicker2.setVisible(false);
		contentPane.add(datePicker2);
		
		checkBox = new JCheckBox("범위 검색 사용");
		checkBox.setBounds(1155, 25, 135, 35);
		checkBox.addActionListener(this);
		contentPane.add(checkBox);
		
		btnShowComment = new JButton("댓글 보여주기");
		btnShowComment.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		btnShowComment.setBackground(new Color(244, 204, 204));
		btnShowComment.setBounds(262, 70, 150, 50);
		btnShowComment.addActionListener(this);
		contentPane.add(btnShowComment);
	}
}

enum TableHeaderNumber {
	NUMBER, CUS_NAME, VISITED_DATE, SRVICE_NAME, STAR, COMMENT;
}

