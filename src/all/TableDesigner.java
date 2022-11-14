package all;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * 테이블의 디자인을 적용시키는 클래스<br>
 * 모든 함수가 static으로 선언되어있습니다.<br>
 * 메소드 설명에 있는 번호 순서대로 적용<br>
 * @author MoonNight285
 */
public class TableDesigner {
	
    /**
     * 테이블에 사용할 폰트를 설정합니다.(사용순서 0번)
     * @param table 대상 테이블
     * @param fontFullName 테이블에서 사용할 폰트를 넣어주세요(확장자 넣지마세요).
     * @param FONT_SIZE 폰트 크기
     */
	public static void setFont(JTable table, String fontFullName, final int FONT_SIZE) {
		InputStream inputStream = null;
		
		try {
	        String classPath = ComManageComment.class.getResource("").getPath();
	        String path = URLDecoder.decode(classPath, "UTF-8");
	        path = path.split("all")[0];
	        inputStream = new BufferedInputStream(
	                new FileInputStream(path + "/font/" + fontFullName + ".ttf"));

	        Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        table.setFont(font.deriveFont(Font.PLAIN, FONT_SIZE));
	        table.getTableHeader().setFont(font.deriveFont(Font.BOLD, FONT_SIZE));
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch(IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch(FontFormatException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} finally {
			try {
				if(inputStream != null) { inputStream.close(); }
			} catch(IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}
	
    /**
     * 테이블의 제목 컬럼을 설정합니다.(사용순서 1번)
     * @param table 대상 테이블
     * @param headerNames 테이블에서 사용할 제목 컬럼을 입력하세요.
     */
	public static void setTableColumn(JTable table, Vector<String> headerNames) {	
		DefaultTableModel model = new DefaultTableModel(headerNames, 0);
		table.setModel(model);
	}
	
    /**
     * 테이블의 값을 가운데 정렬합니다.(사용순서 2번)
     * @param table 대상 테이블
     * @param columnNames 테이블에서 사용되는 컬럼의 이름 순서대로
     */
	public static void setTableTextCenter(JTable table, List<String> columnNames) {
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < columnNames.size(); ++i) {
			table.getColumn(columnNames.get(i)).setCellRenderer(render);
		}
	}
	
    /**
     * 테이블의 행의 높이를 설정합니다.(사용순서 3번)
     * @param table 대상 테이블
     * @param height 행의 높이
     */
	public static void resizeTableRow(JTable table, int height) {
		table.setRowHeight(50);
	}
	
    /**
     * 테이블의 열의 길이를 설정합니다.(사용순서 4번)<br>
     * columnWidths의 값을 HashMap으로 전달하세요<br>
     * <b>key 값은 컬럼의 이름 value 값은 해당 컬럼의 가로 길이입니다.</b>
     * @param table 대상 테이블
     * @param columnSizes 각 컬럼의 가로 길이 값
     */
	public static void resizeTableColumn(JTable table, HashMap<String, Integer> columnSizes) {
		Set<String> columnNames = columnSizes.keySet();
		for(String columnName : columnNames) {
			table.getColumn(columnName).setPreferredWidth(columnSizes.get(columnName));
		}
	}
	
    /**
     * 테이블의 행의 레이아웃을 조절합니다.(사용순서 5번)<br>
     * @param table 대상 테이블
     */
	public static void resizeTableHeader(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		String prefix = "<html><body><table><tr><td height=50>";
		String suffix = "</td></tr></table></body><html>";
		
		for (int col = 0; col < columnModel.getColumnCount(); col++) {
		    TableColumn column = columnModel.getColumn(col);
		    String text = prefix + columnModel.getColumn(col).getHeaderValue().toString() + suffix;
		    column.setHeaderValue(text);
		}
	}
}
