/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDLIB;
import BACKENDLIB.BookBase;
import BACKENDLIB.arryList;
import DSA.LL;
import DSA.LinkedlistBook;
import static DSA.LinkedlistBook.quickSortByTitle;
import DSA.NodeBook;
import DSA.NodeTransac;
import FRONTENDLIB.BORROWINGUI;
import static FRONTENDLIB.RecordPayUI.bookList;
import static FRONTENDLIB.VIEWLISTUI.bookList;

   //import static Project.VIEWLISTUI.*;
//import Project.arryList.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ivan
 */
public class ReturnUI extends parentComponent implements BookBase,ActionListener {
   private JLabel userLbl, isbnLbl, borrowLbl, dueDateLbl, fineAmountLbl, titleLbl,DetailsLBL,transacID,AddFee,watFee,Tfee,sFee,pfee,Dfee,mfee;
    private JTextField userField, isbnField, transacIDField, fineAmountField,isbns;
    private JSpinner borrowDateSpinner, dueDateSpinner;
    private JButton returnButton, cancelButton, updateButton;
    private JPanel topPanel, sidePanel, mainPanel,butt,addFee;
    private DefaultTableModel tableModel;
    private JTable transactionTable;
    private JScrollPane tableScrollPane;
     private JCheckBox waterDamage, tornPages, stains, missingPages, coverDamage, penMarker;
      ImageIcon searchIMG = new ImageIcon("images\\search.png");
      private LL feeList = new LL();
    JPanel MainPanel = new JPanel(null);
     private Map<String, List<String>> booksByGenre;
    private Date borrowDate, dueDate,returnDate;
      private JComboBox<String> genreComboBox;
    public static RecordPayUI pay ;
    public static arryList transactionList;
    public static LinkedlistBook bookList;
   
  

    public ReturnUI(arryList transactionList,RecordPayUI pay, LinkedlistBook bookList) {
        this.transactionList = transactionList;
        this.bookList = bookList;
        this.pay = pay;
        initUI();
      
    }

    // Initialize the user interface
    public void initUI() {
        
          MainPanel.setSize(1280, 1049);
            MainPanel.setBounds(0, 0, 1473, 1061);
            MainPanel.setVisible(true);
            MainPanel.setLayout(null);
            MainPanel.setBackground(new Color(0x6F1D1B));

        // Panels
        initPanels();

        // Labels
        initLabels();

        // Text Fields
        initTextFields();

        // Buttons
        initButtons();

        // Table
        initTable();

        // Add components to panels
        addComponentsToPanels();

        // Make frame visible
    
    }

    // Initialize panels
    private void initPanels() {
       
        sidePanel = createPanel(770, 0, 547 , 1020, new Color(0xBB9457));
     
       
        butt = createPanel(4,128,490,804,new Color(0xBB9457));
        addFee = createPanel(26,443,491,167,new Color(111,29,27,50));
       
        
        setIconImage(icon);

       MainPanel. add(sidePanel);
      sidePanel.add(butt);
      butt.add(addFee);
   
        
    }
    

    // Create a panel with specified bounds and background color
    private JPanel createPanel(int x, int y, int width, int height, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(bgColor);
        panel.setBounds(x, y, width, height);
        return panel;
    }

    // Initialize labels
    private void initLabels() {
        titleLbl = createLabel("Returning", new Font("Plus Jakarta San", Font.PLAIN, 70), Color.white, 31, 20, 500, 115);
        userLbl = createLabel("Student Number", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0x6F1D1B), 26, 22, 500, 59);
        isbnLbl = createLabel("ISBN", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0x6F1D1B), 26, 172, 139, 59);
        borrowLbl = createLabel("Borrow Date", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0x6F1D1B), 26, 303, 232, 59);
        transacID = createLabel("TransacID", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0x6F1D1B), 286, 172, 500, 59);
        dueDateLbl = createLabel("Due Date", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0x6F1D1B), 288, 303, 232, 59);
        fineAmountLbl = createLabel("Fine ", new Font("Plus Jakarta Sans", Font.PLAIN, 36), new Color(0x6F1D1B), 29, 610  , 267, 59);
            DetailsLBL = createLabel("Details", new Font("Plus Jakarta Sans", Font.PLAIN, 70), Color.white, 150, 15, 667, 100);
            AddFee = createLabel("Aditional Fee",new Font("Plus Jakarta Sans",Font.PLAIN,36),Color.white,8,2,267,59);
              
                                
        //172,139,59
       
        MainPanel.add(titleLbl);
        butt.add(userLbl);
        butt.add(isbnLbl);
        butt.add(borrowLbl);
        butt.add(dueDateLbl);
        butt.add(fineAmountLbl);
        butt.add(transacID);
        addFee.add(AddFee);
        
        sidePanel.add(DetailsLBL);
         
    }

    // Create a label with specified font, color, and bounds
    private JLabel createLabel(String text, Font font, Color color, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }
    private void unSelectBox(){
        waterDamage.setSelected(false);
          tornPages.setSelected(false);
            stains.setSelected(false);
              missingPages.setSelected(false);
                coverDamage.setSelected(false);
                  penMarker.setSelected(false);
    }
    private void CheckBox(){
         waterDamage = createCheckbox("Water Damage");
        tornPages = createCheckbox("Torn Pages");
        stains = createCheckbox("Stains");
        missingPages = createCheckbox("Missing Pages");
        coverDamage = createCheckbox("Cover Damage");
        penMarker = createCheckbox("Pen Marker");
        
        waterDamage.setBounds(26, 55, 208, 23);
          tornPages.setBounds(26, 87, 208, 23);
            stains.setBounds(26, 128, 208, 23);
              missingPages.setBounds(268, 55, 208, 23);
                coverDamage.setBounds(268, 87, 208, 23);
                  penMarker.setBounds(268, 128, 208, 23);
        
         addFee.add(waterDamage);
         // Empty space next to the checkbox
        addFee.add(tornPages);
        
        addFee.add(stains);
        
        addFee.add(missingPages);
        
        addFee.add(coverDamage);
       
        addFee.add(penMarker);
        
        
        waterDamage.addActionListener(e -> updateTotalFee());
tornPages.addActionListener(e -> updateTotalFee());
stains.addActionListener(e -> updateTotalFee());
missingPages.addActionListener(e -> updateTotalFee());
coverDamage.addActionListener(e -> updateTotalFee());
penMarker.addActionListener(e -> updateTotalFee());
      
          feeList.add(waterDamage, 50); // Example: Water damage costs 50
    feeList.add(tornPages, 30);   // Example: Torn pages cost 30
    feeList.add(stains, 20);      // Example: Stains cost 20
    feeList.add(missingPages, 100); // Example: Missing pages cost 100
    feeList.add(coverDamage, 40);   // Example: Cover damage costs 40
    feeList.add(penMarker, 25);  
   
    }
    
     private JCheckBox createCheckbox(String label) {
        JCheckBox checkbox = new JCheckBox(label);
        checkbox.setFont(new Font("Plus Jakarta Sans", Font.PLAIN, 16));
        checkbox.setForeground(Color.WHITE);
        checkbox.setBackground(new Color(0xBB9457));
        checkbox.setOpaque(true);
      
        return checkbox;
    }

    // Initialize text fields
    private void initTextFields() {
        userField = createTextField(26  , 225, 223, 70);
        isbnField = createTextField(26, 81, 481, 70);
        transacIDField = createTextField(286, 225, 223, 70);
        fineAmountField = createTextField(26, 656, 614, 76);
        
       fineAmountField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
        transacIDField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
       isbnField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
         userField.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
                    
        sidePanel.add(userField);
        sidePanel.add(isbnField);
        sidePanel.add(transacIDField);
        sidePanel.add(fineAmountField);

        borrowDateSpinner = createDateSpinner(26, 351, 223, 70);
        dueDateSpinner = createDateSpinner(288, 352, 221, 70);
        
       dueDateSpinner.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));
        borrowDateSpinner.setFont(new Font("Plus Jakarta Sans",Font.PLAIN,24));

        sidePanel.add(borrowDateSpinner);
        sidePanel.add(dueDateSpinner);
    }

    // Create a text field with specified bounds
    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(new Color(0x99582A));
        textField.setBorder(null);
        return textField;
    }

    // Create a date spinner with specified bounds
    private JSpinner createDateSpinner(int x, int y, int width, int height) {
        SpinnerDateModel model = new SpinnerDateModel();
    JSpinner dateSpinner = new JSpinner(model);
    
    // Set the size of the spinner
    dateSpinner.setBounds(x, y, width, height);

    // Format the date to dd/MM/yyyy
    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
    dateSpinner.setEditor(editor);
        dateSpinner.setBounds(x, y, width, height);
        dateSpinner.setBackground(Color.white);
        return dateSpinner;
    }
    public void resetTable() {
    DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
    model.setRowCount(0); // Clears all rows
}

    // Initialize buttons
    private void initButtons() {
    // Button initialization
    returnButton = createButton("Return", 245, 900, 250, 88);
    cancelButton = createButton("Delete", 30, 900, 190, 88);
    cancelButton.setFont(new Font("Bebas Neue",Font.PLAIN,42));
    cancelButton.addActionListener(new ActionListener(){
        @Override
         public void actionPerformed(ActionEvent e) {
        int row = transactionTable.getSelectedRow(); 
String eme = transactionTable.getValueAt(row, 1).toString();
        int isb = Integer.parseInt(eme);

           
// Get the selected row index
        if (row >= 0) { // Ensure a row is selected
            // Remove the transaction from the list
            
             NodeBook selectedBook = bookList.getBookByIsbn(isb);
            if (selectedBook != null) {
                selectedBook.setIsAvailable(true); // Set availability to true
                System.out.println("Book availability updated for ISBN: " + isb);
            } else {
                System.out.println("Book not found with ISBN: " + isb);
            }
            
            
            arryList.removeTransaction(row);
            
            
            // Remove the row from the table model
            DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
            model.removeRow(row); 
  JOptionPane.showMessageDialog(null, "Transaction canceled and book availability updated.", 
                                          "Success", JOptionPane.INFORMATION_MESSAGE);// Update the table UI
        } else {
            // If no row is selected, show a message to the user
            JOptionPane.showMessageDialog(null, "Please select a transaction to cancel.", 
                                          "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
    });
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
                             DefaultTableModel model = (DefaultTableModel) transactionTable.getModel();
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
            isbns.setBounds(355, 172, 356, 65);
            isbns.setBackground(new Color(0xBB9457));
            isbns.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            isbns.setForeground(Color.white);
            isbns.setLayout(null);
            isbns.setBorder(null);
            isbns.add(search);
            
                 genreComboBox = new JComboBox<>(BookBase.genre);
            genreComboBox.addItem("Select Genre");
            genreComboBox.setSelectedIndex(4);
            genreComboBox.setBounds(31, 172, 291, 65);
            genreComboBox.setPreferredSize(new Dimension(333, 76));
            genreComboBox.setBackground(new Color(0x99582A));
            genreComboBox.setFont(new Font("Plus Jakarta Sans", Font.ITALIC, 24));
            genreComboBox.setForeground(Color.white);

           // quickSort(BookBase.genre, 0, BookBase.genre.length - 1);
            genreComboBox.addActionListener((ActionEvent e) -> {
                 DefaultTableModel model = (DefaultTableModel) tableModel;
        model.setRowCount(0); 
         boolean found = false;
                 String selectedGenre = (String) genreComboBox.getSelectedItem();
             if (!selectedGenre.equals("All")) {
            Object[][] getBookData = bookList.getBookData(); // Get the book data

            // Loop the books t check the genre
            for (Object[] book : getBookData) {
                String bookGenre = (String) book[3];
                if (bookGenre.equalsIgnoreCase(selectedGenre)) {
                    String availability = (boolean) book[4] ? "Available" : "Checked Out";
                    model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
                    found = true;
                }
            }

            // If no books were found for slected gen
            if (!found) {
                JOptionPane.showMessageDialog(this, "No books found in the genre: " + selectedGenre, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            //display sorted books "All"
            updateTableWithSortedBooks(tableModel); 
        }  // Assuming populateTable can accept the sorted array
});
       
   

    // Add action listeners
   

    // Add buttons to panels
    sidePanel.add(returnButton);
    sidePanel.add(cancelButton);
    MainPanel.add(isbns);
    MainPanel.add(genreComboBox);


    

    // Add ActionListener to returnButton
    returnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int row = transactionTable.getSelectedRow();

        // Check if a row is selected
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to return!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate inputs
        if (userField.getText().isEmpty() || isbnField.getText().isEmpty() || 
            transacIDField.getText().isEmpty() || fineAmountField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Parse inputs
            String userID = userField.getText();
            String transactionID = transacIDField.getText();
            String fineAmountText = fineAmountField.getText();
            Date borrowDate = (Date) borrowDateSpinner.getValue();
            Date dueDate = (Date) dueDateSpinner.getValue();

            // Parse fineAmountText and isbn safely
            double overdue = Double.parseDouble(fineAmountText);
            int cd = Integer.parseInt(isbnField.getText());

            // Format dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String borrowDateStr = dateFormat.format(borrowDate);
            String dueDateStr = dateFormat.format(dueDate);
            
             int index = arryList.TransacId.indexOf(transactionID);
             if (index == -1) {
    JOptionPane.showMessageDialog(null, "Transaction ID not found!");
    return; // Exit the method to prevent further execution
}
             
             System.out.println(index);
            // Set status
            String status = "Returned";

            // Perform action
            JOptionPane.showMessageDialog(null, "Redirecting to record...");
            
            // Call add method from RecordPayUI instance
            
          //create new isntancec of archived for recordpyment
           DefaultTableModel tb = (DefaultTableModel) pay.table.getModel();
           pay.updateTransactionStatus(index,status,overdue,tb);
            
            // Remove selected row from the table
            bookList.updateAvailability(cd, true);
            //pay.updateTransactionStatus(index, status, overdue);
            if (index >= 0) {
                
            arryList.updateTransaction(index, "Returned", overdue);

            arryList.removeTransaction(index);
            tableModel.removeRow(row);

            JOptionPane.showMessageDialog(null, "Book return recorded and row deleted successfully!");
             // Unselect checkbox
             unSelectBox();
             userField.setText("");
               transacIDField.setText("");
                fineAmountField.setText("");
                isbnField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Transaction not found in the backend data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
            
            
           
            

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input in numeric fields!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    });
}
     private void updateTableWithSortedBooks(DefaultTableModel defTab) {
    // Get the sorted books data
    Object[][] sortedBooks = bookList.sortByTitle(); // Calls quickSortByTitle() inside sortByTitle()

    // Clear the table model
    defTab.setRowCount(0);

    // Add the sorted books to the table model
    for (Object[] book : sortedBooks) {
        defTab.addRow(book);
    }
}
                
    public void resetTableData() {
    DefaultTableModel model = (DefaultTableModel) tableModel;
    Object[][] getBookData = bookList.getBookData();
    model.setRowCount(0); // Clear the table
    
    for (Object[] book : getBookData) {
        String availability = (boolean) book[4] ? "Available" : "Checked Out";
        model.addRow(new Object[]{book[0], book[1], book[2], book[3], availability});
    }
}
                 

      
      
     
      
        
        
   
    
   

    // Create a button with specified text and bounds
    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Bebas Neue", Font.BOLD, 48));
        button.setForeground(new Color(0x6F1D1B));
        button.setBackground(Color.white);
        button.setBounds(x, y, width, height);
        return button;
    }

    // Initialize the table
    private void initTable() {
        
        tableModel = new DefaultTableModel(new String[]{"UserID", "ISBN", "Transaction ID", "Due date", "Borrow Date"}, 0);
        transactionTable = new JTable(tableModel);
       
        transactionTable.addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                 table();
            }
           
        });
      

        tableScrollPane = new JScrollPane(transactionTable);
        tableScrollPane.setBounds(31, 250, 680
                , 700);
        MainPanel.add(tableScrollPane);

        populateTable();
        
         
    }

    // Populate the table with transaction data
    private void populateTable() {
        tableModel.setRowCount(0); // Clear existing rows

        for (NodeTransac transaction : arryList.getTransactions()) {
            Object[] rowData = {
                transaction.getID(),
                transaction.code(),
                transaction.getUserId(),
                transaction.getDueDate(),
                transaction.borrowDate(),
                transaction.getPrice(),
                transaction.getstatus()
                
                    
            };
            tableModel.addRow(rowData);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     private class Clear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            resetTable();
           
    }
     }

    // Action listener for update button
    private class UpdateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = transactionTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row to update.");
                return;
            }

            // Validate inputs
            String userId = userField.getText().trim();
            int isbnCode = parseISBN(isbnField.getText().trim());
            String transactionId = transacIDField.getText().trim();
          Date  borrowDate = (Date) borrowDateSpinner.getValue();
           Date dueDate = (Date) dueDateSpinner.getValue();

            if (userId.isEmpty() || transactionId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                return;
            }

            // Update transaction
             returnDate = new Date();
             String genre =bookList.getGenre(isbnCode);
            double fine = caculateFine(genre,borrowDate,dueDate,returnDate);
            
            NodeTransac updatedTransaction = new NodeTransac(userId,isbnCode, transactionId, borrowDate, dueDate);
            arryList.updateTransaction(row, updatedTransaction);

            // Refresh table
            populateTable();
        }
    }

    // Parse ISBN from string input
    private int parseISBN(String isbn) {
        try {
            return Integer.parseInt(isbn);
        } catch (NumberFormatException e) {
            return -1; // Invalid ISBN
        }
    }

    // Mouse listener for the table
    public void table(){
      
            int row = transactionTable.getSelectedRow();
             if (row != -1) {
              userField.setText(tableModel.getValueAt(row, 0).toString());
        isbnField.setText(tableModel.getValueAt(row, 1).toString());
        transacIDField.setText(tableModel.getValueAt(row, 2).toString());
 SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
             try {
        // Retrieve the Date objects directly from the table (assuming columns 3 and 4 store Date objects)
        Object borrowDateObj = tableModel.getValueAt(row, 3); // Borrow Date column
        Object dueDateObj = tableModel.getValueAt(row, 4);   // Due Date column

        if (borrowDateObj instanceof Date && dueDateObj instanceof Date) {
            Date borrowDate = (Date) borrowDateObj;
            Date dueDate = (Date) dueDateObj;

            // Print the dates for debugging
            System.out.println("Borrow Date: " + borrowDate);
            System.out.println("Due Date: " + dueDate);

            // Set the spinners to the Date objects
            borrowDateSpinner.setValue(borrowDate);
            dueDateSpinner.setValue(dueDate);

            // Get ISBN from the text field
            String getISBN = isbnField.getText();
            try {
                int ISBN = Integer.parseInt(getISBN);  // Convert ISBN from string to integer
                String genre = bookList.getGenre(ISBN);  // Get the genre based on the ISBN
                
                // Print the genre for debugging
                System.out.println("Genre: " + genre);
                
                if (genre != null) {
                    // Calculate the fine using the retrieved genre
                     returnDate = new Date();
                     System.out.println(returnDate);// Assuming the current date as the return date
                    double fine = caculateFine(genre, borrowDate, dueDate, returnDate);
                    
                    double totalSelectedFees = 0.0;
                    // Print the fine for debugging
                    System.out.println("Calculated fine: " + fine);
                    System.out.println("Updating fineAmountField: " + String.format("%.2f", fine));
                    fineAmountField.setText(String.format("%.2f", fine)); 
                    
                    double selectedFee = feeList.calculateTotalFee(fine); // Example method to calculate selected fees
        fineAmountField.setText(String.format("%.2f", fine + selectedFee));
// Display the fine amount
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found for the given ISBN", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException b) {
                JOptionPane.showMessageDialog(null, "Invalid ISBN format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Error: Retrieved values are not Date objects.");
        }
    } catch (Exception c) {
        System.out.println("Error retrieving date: " + c.getMessage());
    }
            return null;
        }

        @Override
        protected void done() {
            // Any final UI updates after background task is complete
        }
    };

    worker.execute();  // Start the background task
}
    }
    
    private void addComponentsToPanels() {
    // Add components to the top panel (if needed)
    // Button for closing the window

    // Add components to the side panel
    MainPanel.add(titleLbl);
    butt.add(userLbl);
    butt.add(isbnLbl);
    butt.add(borrowLbl);
    butt.add(dueDateLbl);
    butt.add(fineAmountLbl);
    butt.add(userField);
    butt.add(isbnField);
    butt.add(transacIDField);
    butt.add(fineAmountField);
    butt.add(borrowDateSpinner);
    butt.add(dueDateSpinner);
    sidePanel.add(returnButton);
   
    
    sidePanel.add(butt);
     CheckBox();

    // Add components to the main panel
   // Add the table to the main panel
}
    
    private void Confirm(String id, int code,String userID , Date dueDate, Date brrwDate, Double amount, String Status){
        JOptionPane.showMessageDialog(null, "The book has been added in recordPayment");
         arryList.addTransaction(id, code, userID, brrwDate , dueDate, amount,Status);
          bookList.updateAvailability(code, false);
     //    pay.addRecord(id,code,userID,brrwDate,dueDate,amount,Status);
        JOptionPane.showMessageDialog(null, "Book has been returned!", id, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateTable() {
        Object[][] bookData = bookList.getBookData();

        // Clear any existing rows in the table
        tableModel.setRowCount(0);

        // Add each book's data to the table
        for (Object[] book : bookData) {
            tableModel.addRow(book);
        }
    }
    private void processRecordPayment() {
    // Get user inputs
    String userID = userField.getText();
    String isbnText = isbnField.getText();
    String transactionID = transacIDField.getText();
    Date borrowDate = (Date) borrowDateSpinner.getValue();
    Date dueDate = (Date) dueDateSpinner.getValue();
    Date returnDates = returnDate; // Assume you have a returnDate field in the UI

    try {
        int isbn = Integer.parseInt(isbnText);

        // Search for the book in the linked list
        NodeBook book = LinkedlistBook.head;
        while (book != null) {
            if (book.getISBN() == isbn) {
                String genre = book.getGenre();
                double fine = caculateFine(genre, borrowDate, dueDate, returnDates);

                // Add record to the Record Payment table
                updateRecordPaymentTable(transactionID, isbn, userID, borrowDate, dueDate, returnDates, fine);
                return;
            }
            book = book.getNext();
        }

        JOptionPane.showMessageDialog(null, "Book with ISBN " + isbn + " not found", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid ISBN input", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateRecordPaymentTable(String transactionID, int isbn, String userID, Date borrowDate, Date dueDate, Date returnDate, double fine) {
    // Assuming `recordPaymentTable` is your JTable for Record Payment
//    DefaultTableModel model = (DefaultTableModel) RecordPayUI.table.getModel();
//
//    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
//    model.addRow(new Object[]{
//        transactionID,
//        isbn,
//        userID,
//        dateFormat.format(borrowDate),
//        dateFormat.format(dueDate),
//        dateFormat.format(returnDate),
//        fine == 0.0 ? "Paid" : "Overdue: " + fine
//   });
}


    public double  caculateFine(String genre, Date brrwDate, Date DueDate, Date returnDate){
         System.out.println("Borrow Date: " + brrwDate);
System.out.println("Due Date: " + DueDate);
System.out.println("Return Date: " + returnDate);
    long diffInMiles = returnDate.getTime() - DueDate.getTime();
    
    long overDue = TimeUnit.MILLISECONDS.toDays(diffInMiles);
    System.out.println("overdue: " + overDue);
    
  if (overDue <= 0) {
        return 0.0;
    }

    // Fine rates based on genre
    double fineFee = 0.0;

    if (genre.equals("Fiction")) {
        fineFee = 5.0;
          System.out.println("genre: Fiction");
    } else if (genre.equals("Non-Fiction")) {
        fineFee = 3.0;
          System.out.println("genre: non ficrtion");
    } else if (genre.equals("Education")) {
        fineFee = 8.0;
         System.out.println("genre: education");
    } else {
        JOptionPane.showMessageDialog(null, "Invalid genre", "Error", JOptionPane.ERROR_MESSAGE);
        return 0.0; // Return 0 if the genre is invalid
    }
    double fe= overDue * fineFee;
    double nyes = fe+ feeList.calculateTotalFee();

    // Return the total fine
    return fe;

    }
        

    private void updateTotalFee() {
    int totalFee = feeList.calculateTotalFee(); // Call your method to calculate the total
      System.out.println("Calculated Total Fee: " + totalFee);
   fineAmountField.setText(String.format("%.2f", (double) totalFee));; // Update the fee display
}


   public static void main(String [] args){
       arryList list = new arryList();
       RecordPayUI nice ;
   //    new ReturnUI(nice);
   }
}

