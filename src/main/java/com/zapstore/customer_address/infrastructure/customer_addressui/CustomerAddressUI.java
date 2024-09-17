package com.zapstore.customer_address.infrastructure.customer_addressui;

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

import com.zapstore.customer_address.application.FindAllCustomerAddressUseCase;
import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;
import com.zapstore.customer_address.infrastructure.CustomerAddressRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;

import javax.swing.SwingUtilities;

public class CustomerAddressUI extends JFrame implements ActionListener{
    private JLabel title, title1, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allCustomerAddressButton, backButton;
    private int gobackindicator;

    public CustomerAddressUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CustomerAddresss");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/address.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Customer");
        title.setBounds(520, -50, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 70));
        title.setForeground(new Color(255,242,45));
        add(title);

        title1 = new JLabel("Address");
        title1.setBounds(550, 10, 500, 300);
        title1.setFont(new Font("Andale Mono", Font.BOLD, 70));
        title1.setForeground(new Color(255,242,45));
        add(title1);
        
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

        allCustomerAddressButton = new JButton("All Address");
        allCustomerAddressButton.setBounds(520, 415, 330, 60);
        allCustomerAddressButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allCustomerAddressButton.setForeground(new Color(255,242,45));
        allCustomerAddressButton.setBackground(new Color(10,20,28));
        allCustomerAddressButton.addActionListener(this);
        add(allCustomerAddressButton);
    }
    public void startCustomerAddress(int gobackindicator) {
        CustomerAddressUI customer_addressUI = new CustomerAddressUI();
        customer_addressUI.setBounds(0, 0, 1000, 600);
        customer_addressUI.setVisible(true);
        customer_addressUI.setResizable(false);
        customer_addressUI.setLocationRelativeTo(null);
        customer_addressUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddCustomerAddressUI addCustomerAddressUI = new AddCustomerAddressUI();
            addCustomerAddressUI.startAddCustomerAddress(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteCustomerAddressUI deleteCustomerAddressUI = new DeleteCustomerAddressUI();
            deleteCustomerAddressUI.startDeleteCustomerAddress(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindCustomerAddressUI FindCustomerAddressUI = new FindCustomerAddressUI();
            FindCustomerAddressUI.startFindCustomerAddress(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateCustomerAddressUI updateCustomerAddressUI = new UpdateCustomerAddressUI();
            updateCustomerAddressUI.startUpdateCustomerAddress(gobackindicator);
        }

        if (e.getSource()==allCustomerAddressButton){
                CustomerAddressService customer_addressService = new CustomerAddressRepository();
                FindAllCustomerAddressUseCase findAllCustomerAddresssUseCase = new FindAllCustomerAddressUseCase(customer_addressService);
                List<CustomerAddress> Countries = findAllCustomerAddresssUseCase.findAllCustomerAddress();
        
                String[] columns = {"id", "ZipCode", "details", "city_id", "customer_id"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(CustomerAddress -> {
                    Object[] row = {CustomerAddress.getId(), CustomerAddress.getZipCode(), CustomerAddress.getDetails(), CustomerAddress.getCity_id(), CustomerAddress.getCustomer_id()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "CustomerAddress List", JOptionPane.PLAIN_MESSAGE);
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

