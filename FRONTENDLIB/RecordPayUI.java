/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;

import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import BACKENDLIB.transac;
import DSA.LinkedlistBook;
import DSA.NodeBook;
import DSA.NodeTransac;
import static FRONTENDLIB.VIEWLISTUI.bookList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class RecordPayUI extends parentComponent implements BookBase  {
     JPanel panel = new JPanel();
     JPanel mainPanel = new JPanel();
    JPanel pnl2 = new JPanel();
    JLabel a;
    JLabel title = new JLabel("Payment Record");
    DefaultTableModel tb;
    JTable table;
    JScrollPane sp;
    static arryList list = new arryList();
     ImageIcon searchIMG = new ImageIcon("images\\search.png");
    static LinkedlistBook bookList;
    private JComboBox<String> option;
    JTextField isbns;

    public RecordPayUI(arryList list, LinkedlistBook bookList) {
        tb = new DefaultTableModel(
            new String[]{"User ID", "ISBN", "Transaction ID", "Due Date", "Borrow Date", "Fine", "Status"},
            0
        );
      
        table = new JTable(tb);
              table.setBorder(BorderFactory.createLineBorder(new Color(0x6F1D1B), 1));
        sp = new JScrollPane(table);
        RecordPayUI.list = list;
        RecordPayUI.bookList = bookList;
        initializePanel();
    }

    private void initializePanel() {
    
        this.setLayout(null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date returnDate = calendar.getTime();

        panel.setBounds(0, 0, 1280, 1049);
        panel.setBackground(new Color(0x6F1D1B));
        panel.setLayout(null);
         Image img = searchIMG.getImage();  // Transform it 
            Image newImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Resize it
            searchIMG = new ImageIcon(newImg);

           JButton search = new JButton();
            search.setBounds(266, 5, 81, 53);
            search.setBackground(new Color(0xBB9457));
            search.setBorderPainted(false);
            search.setOpaque(true);
            search.setContentAreaFilled(true);
            search.setIcon(searchIMG);
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int searchISBN = Integer.parseInt(isbns.getText());  // ISBN entered by user
                        NodeBook foundBook = bookList.LinearSeach(searchISBN);  // Call search method

                        if (foundBook != null) {
                            // Book found, display its details in pnl3
                            JOptionPane.showMessageDialog(null, "ISBN Does exist");
                             DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);  // Clear the table

                model.addRow(new Object[] {
                    foundBook.getTitle(), 
                    foundBook.getAuthor(), 
                    foundBook.getISBN(), 
                    foundBook.getGenre(),
                    foundBook.getIsAvailable() ? "Available" : "Not Available", 
                    foundBook.getBookId(), 
                    foundBook.getQuan(),
                    foundBook.getStatus(),
                    foundBook.shelft()
                });

                        }
                        else{
                            JOptionPane.showMessageDialog(null, "ISBN Does not exist");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid ISBN number.");
                    }
                }
            });

            isbns = new JTextField();
            isbns.setBounds(31, 141, 356, 65);
            isbns.setBackground(new Color(0xBB9457));
            isbns.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            isbns.setForeground(Color.white);
            isbns.setLayout(null);
            isbns.setBorder(null);
            isbns.add(search);
       

        title.setBounds(31, 19, 800, 100);
        title.setFont(new Font("Plus Jakarta Sans", Font.BOLD, 64));
        title.setForeground(Color.white);

        sp.setBounds(45, 250, 1200, 724);

        option = new JComboBox<>(new String[]{"Option1", "Option2"}); // Replace with real options
        option.setBounds(450, 141, 233, 65);
        option.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 16));
        option.setBackground(new Color(0x99582A));
        option.setForeground(Color.white);

        panel.add(title);
        panel.add(sp);
        panel.add(option);
        panel.add(isbns);

                 
        mainPanel = new JPanel();
       
        mainPanel.setSize(1280, 1049);
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 1280, 1049);
        mainPanel.setBackground(new Color(0x6F1D1B));
        
        mainPanel.add(panel);

        populateTable();
         mainPanel.setVisible(true);
    }
     

    private void populateTable() {
        tb.setRowCount(0); // Clear existing rows

        for (NodeTransac transaction : list.getTransactionRecords()) {
            Object[] rowData = {
                transaction.getUserId(),
                transaction.code(),
                transaction.getID(),
                transaction.getDueDate(),
                transaction.borrowDate(),
                transaction.getPrice(),
                transaction.getstatus()
            };
            tb.addRow(rowData);
        }
    }

public void updateTransactionStatus(int index, String status, double fine, DefaultTableModel tb) {
    System.out.println("Row count: " + tb.getRowCount());
        if (index >= 0 && index < arryList.getTransactionRecords().size()) {
            // Get the existing transaction
            NodeTransac transaction = arryList.getTransactionRecords().get(index);

            // Update the status and fine
            transaction.setstatus(status);
            transaction.setPrice(fine);
            
             if (index >= 0 && index < tb.getRowCount()) {
    tb.setValueAt(status, index, 6); // Update Status column
    tb.setValueAt(fine, index, 5); // Update Fine column
} else {
    System.out.println("Invalid index: " + index);
}

            // Update the transaction in the list
           arryList. getTransactions().set(index, transaction);
            } else {
            System.out.println("Index out of bounds or transaction not found");
        }
    }



    
    public static void main(String[] args) {
        new RecordPayUI(list, bookList);
    }
    
}
 
    

   




