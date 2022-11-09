import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * @author MoonNight285
 * 메세지 다이얼로그 생성기
 * 매개변수 : 레이블의 내용, 레이블의 아이콘, 다이얼로그 제목, 다이얼로그 옵션
 * 반환값1 : -2(비정상적으로 작동했을때 반환하는 값)
 * 반환값2 : -1(확인 버튼만 있는 다이얼로그 클릭시 반환값, 예 & 아니오 다이얼로그에서 X 버튼 클릭시 반환값)
 * 반환값3 : 0(예, 아니오 다이얼로그에서 예 클릭시 반환값)
 * 반환값4 : 1(예, 아니오 다이얼로그에서 아니오 클릭시 반환값)
 */
public class DialogManager {
	public static int createMsgDialog(String labelMsg, String labelImgPath, String DialogTitle, int dialogOption) {
		InputStream inputStream = null;
		
		try {
			JLabel label = new JLabel("");
			label.setText("<html><center>" + labelMsg);
			String classPath = DialogManager.class.getResource("").getPath();
            String path = URLDecoder.decode(classPath, "UTF-8");
            String imgPath = path + labelImgPath;
            
            ImageIcon icon = new ImageIcon(imgPath);
            label.setIcon(icon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            
            inputStream = new BufferedInputStream(new FileInputStream(path + "/font/NanumBarunGothic.ttf"));
            
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            label.setFont(font.deriveFont(Font.BOLD, 21));
            
            if(dialogOption == JOptionPane.PLAIN_MESSAGE) {
    			JOptionPane.showMessageDialog(null, label, DialogTitle, JOptionPane.PLAIN_MESSAGE);
    			return -1;
            } else if(dialogOption == JOptionPane.YES_NO_OPTION) {
            	// YES => 0, NO => 1 Return
            	return JOptionPane.showConfirmDialog(null, label, DialogTitle, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            }
		} catch(Exception ex) {
			// ex.getMessage();
			JOptionPane.showMessageDialog(null, "DialogManger Error 1");
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch(IOException ex) {
				// ex.getMessage();
				JOptionPane.showMessageDialog(null, "DialogManger Error 2");
			}
		}
		
		return -2; // 비정상적 작동
	}
}
