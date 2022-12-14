package book;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import all.ComMyPage;
import all.LoginManager;
import all.Size;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class BookCalendar extends JFrame {
	
	private LoginManager loginManager;

	ArrayList<BookCell> cell_list = new ArrayList<BookCell>();
	ArrayList<DayDetail> day_list = new ArrayList<DayDetail>();
	JPanel p_center, p_south;
	JButton prev, next;
	JLabel la_month;
	JPanel[] p_dayOfWeek = new JPanel[7];
	JLabel[] la_dayOfWeek = new JLabel[7];
	
	String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };

	Calendar cal = Calendar.getInstance();
	
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int days = 0;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCalendar frame = new BookCalendar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BookCalendar() {
		loginManager = loginManager.getInstance();
		String id = loginManager.getLogComNum();
		
//		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BookCalendar.class.getResource("/img/YellowCat.png")));
		lblNewLabel.setBounds(717, 10, 230, 86);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 106, 1664, 798);
		contentPane.add(panel);
		panel.setLayout(null);
		p_center = new JPanel();
		p_center.setBounds(177, 60, 1285, 42);
		panel.add(p_center);
		
		p_center.setLayout(new GridLayout(1, 7));
		p_center.setPreferredSize(new Dimension(800, 50));
		p_south = new JPanel();
		p_south.setBounds(177, 100, 1285, 688);
		panel.add(p_south);
		p_south.setLayout(new GridLayout(6, 7));
		p_south.setPreferredSize(new Dimension(800, 420));
		
		next = new JButton("다음 달");
		next.setBackground(new Color(244, 204, 204));
		next.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		next.setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		next.setBounds(1361, 1, 98, 50);
		panel.add(next);
		
		prev = new JButton("이전 달");
		prev.setBackground(new Color(244, 204, 204));
		prev.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		prev.setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		prev.setBounds(179, 1, 98, 50);
		panel.add(prev);
		
		la_month = new JLabel();
		la_month.setBounds(420, 2, 800, 50);
		panel.add(la_month);
		la_month.setHorizontalAlignment(JLabel.CENTER);
		
		JButton btnReset = new JButton(" 새로고침");
		btnReset.setIcon(new ImageIcon(BookCalendar.class.getResource("/img/refresh.png")));
		btnReset.setBackground(new Color(238, 238, 238));
		btnReset.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCal(id);
			}
		});
		btnReset.setFont(new Font("NanumBarunGothic", Font.BOLD, 16));
		btnReset.setBounds(1174, 1, 115, 50);
		panel.add(btnReset);
		
		// 이전 달
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (month > 0) {
					month--;
				} else {
					year--;
					month = 11;
				}
				setCal(id);
			}
			
		});
		
		// 다음 달
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (month < 11) {
					month ++;
				} else {
					year++;
					month = 0;
				}
				setCal(id);
			}
		});
		
		JButton btnBackCusMain = new JButton("돌아가기");
		btnBackCusMain.setBackground(new Color(244, 204, 204));
		btnBackCusMain.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, Color.red, Color.red));
		btnBackCusMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ComMyPage();
			}
		});
		btnBackCusMain.setFont(new Font("NanumBarunGothic", Font.BOLD, 21));
		btnBackCusMain.setBounds(687, 914, Size.BTN_B_W, Size.BTN_B_H);
		contentPane.add(btnBackCusMain);
		
		JLabel backgroundImg = new JLabel("");
		backgroundImg.setIcon(new ImageIcon(BookCalendar.class.getResource("/img/Car2.jpg")));
		backgroundImg.setBounds(0, 0, Size.SCREEN_W, Size.SCREEN_H);
		contentPane.add(backgroundImg);
		
		initCal();
		setCal(id);
		setWeek();
	}
	
	public void setWeek() {
		for (int i=0; i< p_dayOfWeek.length; i++) {
			p_dayOfWeek[i] = new JPanel();
			la_dayOfWeek[i] = new JLabel("");
			
			la_dayOfWeek[i].setText(dayOfWeek[i]);
			if (i == 0) {  // 일요일 
				la_dayOfWeek[i].setForeground(Color.RED);
			}
			if (i == 6) {  // 토요일 
				la_dayOfWeek[i].setForeground(Color.BLUE);
			}
			p_dayOfWeek[i].add(la_dayOfWeek[i]);
			p_dayOfWeek[i].setBackground(new Color(244, 204, 204));
			p_dayOfWeek[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			la_dayOfWeek[i].setFont(new Font("NanumBarunGothic", Font.BOLD, 19));
			p_center.add(p_dayOfWeek[i]);
		}
	}
	
	public void initCal() {
		for (int i = 0; i < 42; i++) {
			BookCell p_day = new BookCell();
			cell_list.add(p_day);
			p_south.add(cell_list.get(i));
		}
	}
	
	public void setCal(String id) {
		for (int i = 0; i < cell_list.size(); i++) {
			BookCell tmpCell = new BookCell();
			tmpCell = cell_list.get(i);
			tmpCell.labelDel();
			
		}
		days = 0;
		
		la_month.setText(year + "년 " + (month + 1) + "월");
		la_month.setFont(new Font("Dialog", Font.BOLD, 25));
		cal.set(year, month, 1);
		
		
		int startday = cal.get(Calendar.DAY_OF_WEEK);
		int lastday = cal.getActualMaximum(Calendar.DATE);
		
		for (int i = 0; i < 42; i++) {
			BookCell tmp_cell = cell_list.get(i);
			
			if((i + 1) >= startday && days < lastday) {
				days++;
				tmp_cell.setCellDate(year, month, days, id);
				tmp_cell.setMonthDay();
								
			}
			else {
				tmp_cell.setCellDate(0, 0, 0, id);
				tmp_cell.setOtherMonthDay();
			}
			
			if ((i % 7) == 6) {
				cell_list.get(i).la_day.setForeground(Color.BLUE);
			}
			else if ((i % 7) == 0) {
				cell_list.get(i).la_day.setForeground(Color.RED);
			}
			cell_list.get(i).la_day.setFont(new Font("NanumBarunGothic", Font.BOLD, 18));
		}
	}
}
