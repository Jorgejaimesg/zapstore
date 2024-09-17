package com.zapstore.customer.infrastructure.customerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

import com.zapstore.customer.application.FindAllCustomerUseCase;
import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;
import com.zapstore.customer.infrastructure.CustomerRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;

public class CustomerUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allCustomerButton, backButton;
    private int gobackindicator;

    public CustomerUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Customer");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Customer");
        title.setBounds(480, 10, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 90));
        title.setForeground(new Color(255,242,45));
        add(title);
        
        addButton = new JButton("Add");
        addButton.setBounds(520, 255, 150, 60);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(700, 255, 150, 60);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(520, 335, 150, 60);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        findButton = new JButton("Find");
        findButton.setBounds(700, 335, 150, 60);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

        allCustomerButton = new JButton("All Customers");
        allCustomerButton.setBounds(520, 415, 330, 60);
        allCustomerButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allCustomerButton.setForeground(new Color(255,242,45));
        allCustomerButton.setBackground(new Color(10,20,28));
        allCustomerButton.addActionListener(this);
        add(allCustomerButton);
    }
    public void startCustomer(int gobackindicator) {
        CustomerUI customerUI = new CustomerUI();
        customerUI.setBounds(0, 0, 1000, 600);
        customerUI.setVisible(true);
        customerUI.setResizable(false);
        customerUI.setLocationRelativeTo(null);
        customerUI.gobackindicator=gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddCustomerUI addCustomerUI = new AddCustomerUI();
            addCustomerUI.startAddCustomer(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteCustomerUI deleteCustomerUI = new DeleteCustomerUI();
            deleteCustomerUI.startDeleteCustomer(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindCustomerUI FindCustomerUI = new FindCustomerUI();
            FindCustomerUI.startFindCustomer(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateCustomerUI updateCustomerUI = new UpdateCustomerUI();
            updateCustomerUI.startUpdateCustomer(gobackindicator);
        }

        if (e.getSource()==allCustomerButton){
                CustomerService customerService = new CustomerRepository();
                FindAllCustomerUseCase findAllCustomersUseCase = new FindAllCustomerUseCase(customerService);
                List<Customer> Customers = findAllCustomersUseCase.findAllCustomer();
        
                String[] columns = {"id", "customer_type", "id_type", "first_name", "last_name", "email"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Customers.forEach(customer -> {
                    Object[] row = {customer.getId(), customer.getCustomer_type(), customer.getId_type(), customer.getFirst_name(), customer.getLast_name(), customer.getEmail()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Customer List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CustomerMenu customerMenu = new CustomerMenu();
                        customerMenu.startCustomerMenu(gobackindicator);
                    });
                });
        }

        }
    }

