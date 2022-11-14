package all;


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
      
      JLabel lblNewLabel = new JLabel("New label");
      lblNewLabel.setIcon(new ImageIcon(ComMyPage.class.getResource("/img/YellowCat.png")));
      lblNewLabel.setBounds(718, 48, 227, 80);
      frame.getContentPane().add(lblNewLabel);
      
      JPanel panel = new JPanel();
      panel.setBounds(329, 154, 1006, 735);
      frame.getContentPane().add(panel);
      panel.setLayout(null);
      
      JButton btnEditComInfo = new JButton("업체정보 수정");
      btnEditComInfo.setBounds(358, 41, 290, 45);
      panel.add(btnEditComInfo);
      btnEditComInfo.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnEditTechList = new JButton("정비사 목록 수정");
      btnEditTechList.setBounds(358, 127, 290, 45);
      panel.add(btnEditTechList);
      btnEditTechList.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      
      JButton btnEditSrvInfo = new JButton("서비스 목록 수정");
      btnEditSrvInfo.setBounds(358, 213, 290, 45);
      panel.add(btnEditSrvInfo);
      btnEditSrvInfo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnEditSrvInfo.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrUnitStock = new JButton("부품 재고 관리");
      btnMgrUnitStock.setBounds(358, 299, 290, 45);
      panel.add(btnMgrUnitStock);
      btnMgrUnitStock.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrBook = new JButton("예약 관리");
      btnMgrBook.setBounds(358, 385, 290, 45);
      panel.add(btnMgrBook);
      btnMgrBook.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrCustomer = new JButton("고객 관리");
      btnMgrCustomer.setBounds(358, 471, 290, 45);
      panel.add(btnMgrCustomer);
      btnMgrCustomer.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrSales = new JButton("매출 관리");
      btnMgrSales.setBounds(358, 557, 290, 45);
      panel.add(btnMgrSales);
      btnMgrSales.setFont(new Font("나눔바른고딕", Font.BOLD, 21));
      
      JButton btnMgrComment = new JButton("코멘트 관리");
      btnMgrComment.setBounds(358, 643, 290, 45);
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