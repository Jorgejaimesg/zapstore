package com.zapstore.customer_type.infrastructure.customer_typeui;

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

import com.zapstore.customer_type.application.FindAllCustomerTypeUseCase;
import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;
import com.zapstore.customer_type.infrastructure.CustomerTypeRepository;
import com.zapstore.menu.infraestructure.CustomerMenu;

public class CustomerTypeUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allCustomerTypeButton, backButton;
    private int gobackindicator;

    public CustomerTypeUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CustomerTypes");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Customer Types");
        title.setBounds(480, 10, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 60));
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




        allCustomerTypeButton = new JButton("All CustomerTypes");
        allCustomerTypeButton.setBounds(520, 415, 330, 60);
        allCustomerTypeButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allCustomerTypeButton.setForeground(new Color(255,242,45));
        allCustomerTypeButton.setBackground(new Color(10,20,28));
        allCustomerTypeButton.addActionListener(this);
        add(allCustomerTypeButton);
    }
    public void startCustomerType(int gobackindicator) {
        CustomerTypeUI customer_typeUI = new CustomerTypeUI();
        customer_typeUI.setBounds(0, 0, 1000, 600);
        customer_typeUI.setVisible(true);
        customer_typeUI.setResizable(false);
        customer_typeUI.setLocationRelativeTo(null);
        customer_typeUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddCustomerTypeUI addCustomerTypeUI = new AddCustomerTypeUI();
            addCustomerTypeUI.startAddCustomerType(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteCustomerTypeUI deleteCustomerTypeUI = new DeleteCustomerTypeUI();
            deleteCustomerTypeUI.startDeleteCustomerType(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindCustomerTypeUI FindCustomerTypeUI = new FindCustomerTypeUI();
            FindCustomerTypeUI.startFindCustomerType(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateCustomerTypeUI updateCustomerTypeUI = new UpdateCustomerTypeUI();
            updateCustomerTypeUI.startUpdateCustomerType(gobackindicator);
        }

        if (e.getSource()==allCustomerTypeButton){
                CustomerTypeService customer_typeService = new CustomerTypeRepository();
                FindAllCustomerTypeUseCase findAllCustomerTypesUseCase = new FindAllCustomerTypeUseCase(customer_typeService);
                List<CustomerType> Countries = findAllCustomerTypesUseCase.findAllCustomerType();
        
                String[] columns = {"ID", "Name"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Countries.forEach(CustomerType -> {
                    Object[] row = {CustomerType.getId(), CustomerType.getName()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "CustomerType List", JOptionPane.PLAIN_MESSAGE);
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

