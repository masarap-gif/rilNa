/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FRONTENDADMIN;

/**
 *
 * @author ivan
 */

import DSA.LinkedListAccounts;
import DSA.NodeAccounts;
import LogSigBackEnd.UserService;
    import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class adminCOA extends parentComponent{
    private JPanel pan5 = new JPanel();
    private JPanel pan6 = new JPanel();
    private JPanel pan7 = new JPanel();
    private JLabel amlbl = new JLabel("Account Management");
    private JTable accountTable = new JTable();
    private JScrollPane accountTableScp = new JScrollPane(accountTable);
    
    private JTextField searchField = new JTextField();
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JTextField contact = new JTextField();
    private JTextField role = new JTextField();
    
    JLabel usern = new JLabel("Username");
    JLabel passw = new JLabel("Password");
    JLabel cont = new JLabel("Contact");
    JLabel rol = new JLabel("Role");
    
    private JButton exit = new JButton("Exit");
    private JButton searchbtn = new JButton("Search");
    private JButton addAccount = new JButton("Add Account");
    private JButton updAccount = new JButton("Update ");
    private JButton deleteAccount = new JButton("Delete Account");
    private JButton unblockAccount = new JButton("Unblock Account"); // no function yet
//    private JButton changePassword = new JButton("Update");
    private JButton backbtnCOA = new JButton("Back");
    private JComboBox<String> userCategory = new JComboBox<>(new String[]{"All Accounts", "Librarian Accounts", "Admin Accounts", "Blocked Accounts"});
    
    
    
    private static String[] columnsForAccounts = {"Member ID", "Username", "Password", "Contact", "Role"};
    
    
//    private static DefaultTableModel accountTableModel = new DefaultTableModel(columnsForAccounts, 0);
    DefaultTableModel model = new DefaultTableModel(columnsForAccounts, 0){
                
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            };

    
    private static LinkedListAccounts accounts;
    private static UserService userService;
    
    
    private String roles[] = {"Admin", "Librarian"};
    private  JComboBox cb = new JComboBox(roles);

    public adminCOA(UserService userService,LinkedListAccounts accounts) {
        this.userService =  userService;
        this.accounts = accounts;

        // Call to set up the UI components
//        setupUI();

        // Load the accounts initially
       // loadAccounts("All Accounts");
        loadAccountsToTable();
          userService.updateUserTable(accountTable);
    }

   
        public  void setupUI(){
        
        pan5.setBackground(new Color(0x99582A));
        pan5.setLayout(null);
        pan5.setPreferredSize(new Dimension(800, 875));

//        pan6.setBackground(new Color(0x6F1D1B));
//        pan6.setLayout(null);
//        pan6.setBounds(850, 86, 758, 840);
        exit.setBounds(1259, 15, 187, 56);
        exit.setBackground(new Color(0xBB9457));
        exit.setLayout(null);
        exit.setFont(new Font("Bebas Neue",Font.BOLD, 40));
        exit.setForeground(Color.white);
        exit.addActionListener(new ActionListener() {
             @Override
                public void actionPerformed(ActionEvent e) {
                    // Save changes before exiting
                    saveChanges();

                    // Create a new instance of the Dashboard and pass the updated accounts
                    dashBoard dashb = new dashBoard(userService, accounts);
                    dashb.setVisible(true);

                    // Close the current frame
                    ((JFrame) SwingUtilities.getWindowAncestor(exit)).dispose();
                }
            });
                      
                 
        pan7.setLayout(null);
        pan7.setBackground(new Color(0xD9D9D9));
        pan7.setBounds(0, 0, 1530, 86);
        pan7.add(exit);

        amlbl.setBounds(70, 21, 560, 77);
        amlbl.setFont(new Font("Bebas Neue", Font.BOLD, 50));
        amlbl.setForeground(new Color(0xBB9457));

        accountTable.setModel(model);
        accountTableScp.setBounds(50, 400, 1425, 350);

        searchField.setBounds(50, 350, 200, 30);
        searchbtn.setBounds(265, 350, 100, 30);
        userCategory.setBounds(400, 350, 200, 30);
        
        addAccount.setBounds(825, 350, 200, 40);
        addAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        addAccount.setForeground(new Color(0x6F1D1B));
        addAccount.setBackground(new Color (0xD9D9D9));
           
        updAccount.setBounds(1050, 350, 200, 40);
        updAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        updAccount.setForeground(new Color(0x6F1D1B));
        updAccount.setBackground(new Color (0xD9D9D9));
        
        deleteAccount.setBounds(1275, 350, 200, 40);
        deleteAccount.setFont(new Font("Bebas Neue", Font.BOLD, 20));
        deleteAccount.setForeground(new Color(0x6F1D1B));
        deleteAccount.setBackground(new Color (0xD9D9D9));
        
        
        pan5.add(usern);
        usern.setBounds(50, 110, 150, 50);
        usern.setForeground(Color.WHITE);
        usern.setFont(new Font("Futura", Font.CENTER_BASELINE,20));
        
        username.setBounds(150, 120, 200, 30);
        username.setText("Enter username");
        username.setFont(new Font("Futura", Font.PLAIN,14));
        username.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(java.awt.event.MouseEvent e){
                     username.setText("");
                 }
            });
        pan5.add(passw);
        passw.setBounds(400, 110, 150, 50);
        passw.setForeground(Color.WHITE);
        passw.setFont(new Font("Futura", Font.CENTER_BASELINE,20));
        
        password.setBounds(500, 120, 200, 30);
        password.setText("Enter Password");
        password.setFont(new Font("Futura", Font.PLAIN,14));
        password.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(java.awt.event.MouseEvent e){
                     password.setText("");
                 }
            });
        
        pan5.add(cont);
        cont.setBounds(750, 110, 150, 50);
        cont.setForeground(Color.WHITE);
        cont.setFont(new Font("Futura", Font.CENTER_BASELINE,20));
        
        contact.setBounds(830, 120, 200, 30);
        contact.setText("Enter Contact Number");
        contact.setFont(new Font("Futura", Font.PLAIN,14));
        contact.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseClicked(java.awt.event.MouseEvent e){
                     contact.setText("");
                 }
            });
            
          add(rol);
          rol.setBounds(1100, 110, 150, 50);
          rol.setForeground(Color.WHITE);
          rol.setFont(new Font("Futura", Font.CENTER_BASELINE,20));
          
          add(cb);
          cb.setBounds(1150, 120, 250, 30);
          cb.setFont(new Font("Futura", Font.PLAIN,14));
 
        addAccount.addActionListener(e -> addAccount());
        updAccount.addActionListener(e -> updateAccount());
        deleteAccount.addActionListener(e -> deleteAccountt());
//        deleteAccount.addActionListener(e -> {
//           int selectedRow = accountTable.getSelectedRow();
//    if (selectedRow == -1) {
//        JOptionPane.showMessageDialog(null, "Please select an account to delete.", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Retrieve the userID from the selected row (column index may vary, here assumed to be 0)
//    int userID = (int) accountTable.getValueAt(selectedRow, 0);
//
//    // Perform deletion using userID
//    boolean deleted = accounts.deleteById(userID);
//
//    if (deleted) {
//        JOptionPane.showMessageDialog(null, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
//    } else {
//        JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
//    }
//
//    // Reload the table
//    loadAccounts("All Accounts");
//        });
//
        userCategory.addActionListener(e -> filterAccounts());
        searchbtn.addActionListener(e -> searchAccounts());
        searchField.addActionListener(e -> searchAccounts());
//
//        
        loadAccounts("All Accounts");
        
        pan5.add(pan6);
        pan5.add(pan7);
        pan5.add(accountTableScp);
        pan5.add(searchField);
        pan5.add(searchbtn);
        pan5.add(userCategory);
        pan5.add(username);
        pan5.add(password);
        pan5.add(contact);
        pan5.add(role);
        pan5.add(addAccount);
        pan5.add(updAccount);
        pan5.add(deleteAccount);

  

        this.add(pan5);
        
        this.setSize(1537, 875);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Account Management");
        this.setResizable(false);
    }
        private void deleteAccountt(){
            int selectedRow = accountTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete an account.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve the userID from the selected row (column index may vary, here assumed to be 0)
        int userID = (int) accountTable.getValueAt(selectedRow, 0);

        // Perform deletion using userID
        boolean deleted = accounts.deleteById(userID);

        if (deleted) {
            JOptionPane.showMessageDialog(null, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Reload the table
        loadAccounts("All Accounts");
         
        }
        private void updateAccount(){
                try{
                        // Get the selected row in the table
                int row = accountTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get the new values to update
                String userN = username.getText().trim();
                String passW = new String(password.getPassword()).trim(); // Convert password to string
                String conT = contact.getText().trim();
                String rol = cb.getSelectedItem().toString();

                // Update specific fields only if they are not empty or unchanged
                DefaultTableModel model = (DefaultTableModel) accountTable.getModel();

                // Update the username if it has changed and is not empty
                if (!userN.isEmpty() && !userN.equals(model.getValueAt(row, 1))) {
                    model.setValueAt(userN, row, 1); // Update username (column 1)
                }

                // Update the password if it has changed
                if (!passW.isEmpty() && !passW.equals(model.getValueAt(row, 2))) {
                    model.setValueAt(passW, row, 2); // Update password (column 2)
                }

                // Update the contact number if it has changed
                if (!conT.isEmpty() && !conT.equals(model.getValueAt(row, 3))) {
                    model.setValueAt(conT, row, 3); // Update contact (column 3)
                }

                // Update the role if it has changed
                if (!rol.equals(model.getValueAt(row, 4))) {
                    model.setValueAt(rol, row, 4); // Update role (column 4)
                }

                JOptionPane.showMessageDialog(null, "Successfully Updated", "Account Management", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "An error occurred. Try again", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
           
    private void addAccount() {
        // Retrieve input from the text fields and combo box
    int id = accounts.generateRandomID(); // Generate a random ID
    String userN = username.getText().trim();
    String passW = new String(password.getPassword()).trim(); // Convert password to string
    String conT = contact.getText().trim();
    String roL = cb.getSelectedItem().toString();
    boolean exist = true;

    // Validate inputs - Check if any field is empty
    if (userN.isEmpty() || passW.isEmpty() || conT.isEmpty()) {
        JOptionPane.showMessageDialog(null, "All fields must have a value.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate password length (at least 13 characters)
    if (passW.length() < 13) {
        JOptionPane.showMessageDialog(null, "Password must be at least 13 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate contact number length (exactly 11 characters)
    if (conT.length() != 11 || !conT.matches("[0-9]+")) {
        JOptionPane.showMessageDialog(null, "Contact number must be exactly 11 digits long and contain only numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    //to avoid duplicates
    for (NodeAccounts account : accounts.getAllAccounts()) { // getAllAccounts()from LinkedListAcc retrieves all accounts
    if (account.getUserName().equals(userN)) {
        JOptionPane.showMessageDialog(
            null, 
            "Username already exists. Please choose a different username.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        exist = false;
        return;
    }
    
    if (account.getPassword().equals(passW)) {
        JOptionPane.showMessageDialog(
            null, 
            "Password already exists. Please choose a different password.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        exist = false;
        return;
    }
    
    if (account.getContactNum().equals(conT)) {
        JOptionPane.showMessageDialog(
            null, 
            "Contact number already exists. Please choose a different contact number.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
        exist = false;
        return;
    }
}

    try {
        // Add user to the LinkedList (accounts object)
        accounts.insertAtBeginning( userN, passW, conT,id, roL);
        
        // Update the user table in the UI (accountTable)
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.addRow(new Object[]{id, userN, conT, roL});
//            userService.addUser(id, userN, passW, conT, roL);
        // Show success message
        JOptionPane.showMessageDialog(null, "Account added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Optionally, reload the accounts (if necessary) to refresh the UI
        loadAccounts("All Accounts");

        // Clear input fields after successful addition
        username.setText("");
        password.setText("");
        contact.setText("");
        cb.setSelectedIndex(0);

    } catch (Exception e) {
        // Handle any exceptions (e.g., database errors)
        JOptionPane.showMessageDialog(null, "An error occurred while adding the account: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

      public void populateTable() {
          System.out.println("Andito kana sa populatetable");
     DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
    model.setRowCount(0);  // Clear the table

    NodeAccounts current = accounts.getHead();  // Assuming you have a head pointer in the LinkedList
    while (current != null) {
         System.out.println("Adding account: " + current.getUserName());
        model.addRow(new Object[] {
            
            current.getMemberID(), 
            current.getUserName(), 
            current.getPassword(),
            current.getContactNum(), 
            current.getRole()
        });
       
        current = current.next;  // Traverse the linked list
    }
   }
    private void filterAccounts() {
        String selectedCategory = (String) userCategory.getSelectedItem();
        loadAccounts(selectedCategory);
    }

    private void searchAccounts() {
        String searchText = searchField.getText().trim();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Field must not empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

       
        model.setRowCount(0);
        NodeAccounts current = accounts.getHead();
        while (current != null) {
            if (String.valueOf(current.getMemberID()).contains(searchText) || 
    current.getUserName().contains(searchText) || 
    current.getRole().contains(searchText.toLowerCase())) {
    model.addRow(new Object[]{
        current.getMemberID(),
        current.getUserName(),
        current.getPassword(),
        current.getContactNum(),
        current.getRole()
    });
}

            current = current.next;
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No results found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void loadAccountsToTable() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        model.setRowCount(0); // Clear existing rows
        
        // Iterate through the LinkedListAccounts and load data into the table
        NodeAccounts current = accounts.getHead();
        while (current != null) {
            Object[] row = new Object[5];
            row[0] = current.getMemberID();
            row[1] = current.getUserName();
            row[2] = current.getPassword();
            row[3] = current.getContactNum();
            row[4] = current.getRole();
            model.addRow(row);
            current = current.next;
        }
    }
    private void saveChanges() {
    // Here, we ensure all changes made to the accounts in the table are saved back to the LinkedList
    NodeAccounts current = accounts.getHead();
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();
        
        int rowCount = model.getRowCount();
        
        // Loop through all rows and update the accounts in the LinkedList
        for (int i = 0; i < rowCount; i++) {
            int memberID = (int) model.getValueAt(i, 0);  // Assuming the first column is the member ID
            String username = (String) model.getValueAt(i, 1);
            String password = (String) model.getValueAt(i, 2);
            String contact = (String) model.getValueAt(i, 3);
            String role = (String) model.getValueAt(i, 4);
            
            // Update the LinkedList node corresponding to the current row
            while (current != null) {
                if (current.getMemberID() == memberID) {
                    current.setUserName(username);
                    current.setPassword(password);
                    current.setContactNum(contact);
                    current.setRole(role);
                    break;
                }
                current = current.next;
            }
            current = accounts.getHead();  // Reset current to the head for the next iteration
        }
    }

    private void loadAccounts(String filterRole) {
    model.setRowCount(0); // Clear the table

    NodeAccounts current = accounts.getHead();
    while (current != null) {
        boolean addRow = false;

        // Determine if the row should be added based on the filter
        if (filterRole.equals("All Accounts")) {
            addRow = true; // Show all accounts
        } else if (filterRole.equals("Librarian Accounts") && current.getRole().equals("librarian")) {
            addRow = true;
        } else if (filterRole.equals("Admin Accounts") && current.getRole().equals("admin")) {
            addRow = true;
//        } else if (filterRole.equals("User Accounts") && current.getRole().equals("user")) {
//            addRow = true;
        } else if (filterRole.equals("Blocked Accounts") && current.getRole().equals("blocked")) {
            addRow = true;
        }

        // Add matching rows to the table
        if (addRow) {
            model.addRow(new Object[]{
                current.getMemberID(),
        current.getUserName(),
        current.getPassword(),
        current.getContactNum(),
        current.getRole()
            });
        }

        current = current.next;
    }
}


       
    
    

    public static void main(String[] args) {
//        adminCOA create = new adminCOA();
//        create.setVisible(true);
    }
}

