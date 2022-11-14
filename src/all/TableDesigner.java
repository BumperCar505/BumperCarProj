package all;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class TableDesigner {
	
    /**
     * 테이블의 제목 컬럼을 설정합니다.
     * @param table 대상 테이블
     * @param headerNames 테이블에서 사용할 제목 컬럼을 입력하세요.
     */
	public static void setTableColumn(JTable table, Vector<String> headerNames) {
		DefaultTableModel model = new DefaultTableModel(headerNames, 0);
		table.setModel(model);
	}
	
    /**
     * 테이블의 값을 가운데 정렬합니다.
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
     * 테이블의 행의 높이를 설정합니다.
     * @param table 대상 테이블
     * @param height 행의 높이
     */
	public static void resizeTableRow(JTable table, int height) {
		table.setRowHeight(50);
	}
	
    /**
     * 테이블의 열의 길이를 설정합니다.<br>
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
     * 테이블의 행의 레이아웃을 조절합니다.<br>
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
