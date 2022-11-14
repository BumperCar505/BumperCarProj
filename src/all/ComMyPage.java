package all;


import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

import book.BookCalendar;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class ComMyPage {

   private JFrame frame;
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               ComMyPage window = new ComMyPage();
               
//               window.frame.setVisible(true);
               
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public ComMyPage() {
//      frame.setLocationRelativeTo(null);
      initialize();
      frame.setVisible(true); 
      frame.setLocationRelativeTo(null); //화면 가운데에 뜨기

   
   }

   /**
    * Initialize the contents of the frame.s
    */
   public void initialize() {
      
      frame = new JFrame();
      frame.setBounds(100, 100, Size.SCREEN_W, Size.SCREEN_H);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setTitle("다고쳐카센터 - 메인화면");
      
      JLabel lblNewLabel = new JLabel("New label");
      lblNewLabel.setIcon(new ImageIcon(ComMyPage.class.getResource("/img/YellowCat.png")));
      lblNewLabel.setBounds(718, 48, 227, 80);
      frame.getContentPane().add(lblNewLabel);
      
      JPanel panel = new JPanel();
      panel.setBounds(329, 154, 1006, 735);
      frame.getContentPane().add(panel);
      panel.setLayout(null);
      
      JButton btnEditComInfo = new JButton("업체정보 수정");
      btnEditComInfo.setBounds(358, 51, Size.BTN_B_W, Size.BTN_B_H);
      btnEditComInfo.setBackground(new Color(244, 204, 204));
      btnEditComInfo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnEditComInfo);
      btnEditComInfo.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnEditTechList = new JButton("정비사 목록 수정");
      btnEditTechList.setBounds(358, 137, Size.BTN_B_W, Size.BTN_B_H);
      btnEditTechList.setBackground(new Color(244, 204, 204));
      btnEditTechList.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnEditTechList);
      btnEditTechList.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      
      JButton btnEditSrvInfo = new JButton("서비스 목록 수정");
      btnEditSrvInfo.setBounds(358, 223, Size.BTN_B_W, Size.BTN_B_H);
      btnEditSrvInfo.setBackground(new Color(244, 204, 204));
      btnEditSrvInfo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnEditSrvInfo);
      btnEditSrvInfo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnEditSrvInfo.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrUnitStock = new JButton("부품 재고 관리");
      btnMgrUnitStock.setBounds(358, 309, Size.BTN_B_W, Size.BTN_B_H);
      btnMgrUnitStock.setBackground(new Color(244, 204, 204));
      btnMgrUnitStock.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnMgrUnitStock);
      btnMgrUnitStock.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrBook = new JButton("예약 관리");
      btnMgrBook.setBounds(358, 395, Size.BTN_B_W, Size.BTN_B_H);
      btnMgrBook.setBackground(new Color(244, 204, 204));
      btnMgrBook.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnMgrBook);
      btnMgrBook.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrCustomer = new JButton("고객 관리");
      btnMgrCustomer.setBounds(358, 481, Size.BTN_B_W, Size.BTN_B_H);
      btnMgrCustomer.setBackground(new Color(244, 204, 204));
      btnMgrCustomer.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnMgrCustomer);
      btnMgrCustomer.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrSales = new JButton("매출 관리");
      btnMgrSales.setBounds(358, 567, Size.BTN_B_W, Size.BTN_B_H);
      btnMgrSales.setBackground(new Color(244, 204, 204));
      btnMgrSales.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnMgrSales);
      btnMgrSales.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrComment = new JButton("코멘트 관리");
      btnMgrComment.setBounds(358, 653, Size.BTN_B_W, Size.BTN_B_H);
      btnMgrComment.setBackground(new Color(244, 204, 204));
      btnMgrComment.setBorder(new BevelBorder(BevelBorder.RAISED, Color.red, Color.red, 
				Color.red, Color.red));
      panel.add(btnMgrComment);
      btnMgrComment.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
//         코멘트 관리 페이지로
      btnMgrComment.addActionListener(new ActionListener() {
            
         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false); 
            new ComManageComment();

         }
      });
      
//         매출관리페이지로
      btnMgrSales.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false); 
            new SalesMgr();

         }
      });
      
      
//      고객관리페이지로
         btnMgrCustomer.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false); 
            new CusMgr();

         }
      });
      
//      일정 관리 페이지로
      btnMgrBook.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false); 
               
               EditComInfo editComInfo = new EditComInfo();
               editComInfo.showComInfo("1112233333");

            }
         });
      
//      부품 재고 관리 페이지로
      btnMgrUnitStock.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false); 
               new UnitStockMgr();

            }
         });
      
//      서비스 목록 수정 페이지로 
      btnEditSrvInfo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false); 
               new ComSrvList();

            }
         });
      
//      정비사목록 수정 페이지로
      btnEditTechList.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.setVisible(false); 
               new TechListEdit();

            }
         });
      
      
//      업체정보 수정 페이지로
      btnEditComInfo.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            
            EditComInfo editComInfo = new EditComInfo();
            editComInfo.showComInfo("1112233333");
         }
      });
         
   }
}