package com.zapstore.customer.infrastructure.customerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.zapstore.customer.application.CreateCustomerUseCase;
import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;
import com.zapstore.customer.infrastructure.CustomerRepository;
import com.zapstore.customer_type.application.FindAllCustomerTypeUseCase;
import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;
import com.zapstore.customer_type.infrastructure.CustomerTypeRepository;
import com.zapstore.id_type.application.FindAllIdTypeUseCase;
import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;
import com.zapstore.id_type.infrastructure.IdTypeRepository;

public class AddCustomerUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelName, labelId, labelIdType, labelCustomerType, labelEmail, labelLastName;
    private JButton addButton, backButton;
    private JComboBox<String> idBox, customerBox;
    private JTextField Name, idcustomer, LastName, Email;
    private int gobackindicator;

    CustomerTypeService customerTypeService = new CustomerTypeRepository();
    FindAllCustomerTypeUseCase findAllCustomerTypeUseCase = new FindAllCustomerTypeUseCase(customerTypeService);
    List<CustomerType> customerTypes = findAllCustomerTypeUseCase.findAllCustomerType();

    IdTypeService idTypeService = new IdTypeRepository();
    FindAllIdTypeUseCase findAllIdTypeUseCase = new FindAllIdTypeUseCase(idTypeService);
    List<IdType> idTypes = findAllIdTypeUseCase.findAllIdType();

    public AddCustomerUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Customer");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Customer");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelId = new JLabel("Id: ");
        labelId.setBounds(130, 130, 150, 30);
        labelId.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelId.setForeground(new Color(255,242,45));
        add(labelId);

        idcustomer = new JTextField();
        idcustomer.setBounds(170, 130, 255, 30);
        idcustomer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idcustomer.setForeground(new Color(255,242,45));
        idcustomer.setBackground(new Color(10,20,28));
        add(idcustomer);

        labelIdType = new JLabel("Id Type: ");
        labelIdType.setBounds(500, 130, 150, 30);
        labelIdType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelIdType.setForeground(new Color(255,242,45));
        add(labelIdType);

        idBox = new JComboBox<String>();
        idBox.setBounds(600, 130, 255, 30);
        idBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idBox.setForeground(new Color(255,242,45));
        idBox.setBackground(new Color(10,20,28));
        add(idBox);
        for(IdType idType : idTypes){
            idBox.addItem(String.valueOf(idType.getId())+". " + idType.getName());
        }

        labelCustomerType = new JLabel("Type: ");
        labelCustomerType.setBounds(500, 170, 150, 30);
        labelCustomerType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCustomerType.setForeground(new Color(255,242,45));
        add(labelCustomerType);

        customerBox = new JComboBox<String>();
        customerBox.setBounds(600, 170, 255, 30);
        customerBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        customerBox.setForeground(new Color(255,242,45));
        customerBox.setBackground(new Color(10,20,28));
        add(customerBox);
        for(CustomerType customerType : customerTypes){
            customerBox.addItem(String.valueOf(customerType.getId())+". " + customerType.getName());
        }

        labelName = new JLabel("Name: ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);

        labelLastName = new JLabel("Lastname: ");
        labelLastName.setBounds(65, 210, 150, 30);
        labelLastName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelLastName.setForeground(new Color(255,242,45));
        add(labelLastName);

        LastName = new JTextField();
        LastName.setBounds(170, 210, 255, 30);
        LastName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        LastName.setForeground(new Color(255,242,45));
        LastName.setBackground(new Color(10,20,28));
        add(LastName);

        labelEmail = new JLabel("Email : ");
        labelEmail.setBounds(500, 210, 150, 30);
        labelEmail.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelEmail.setForeground(new Color(255,242,45));
        add(labelEmail);

        Email = new JTextField();
        Email.setBounds(600, 210, 255, 30);
        Email.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Email.setForeground(new Color(255,242,45));
        Email.setBackground(new Color(10,20,28));
        add(Email);


        addButton = new JButton("Add");
        addButton.setBounds(400, 300, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddCustomer(int gobackindicator) {
        AddCustomerUI addUI = new AddCustomerUI();
        addUI.setBounds(0, 0, 1000, 400);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator=gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String customerId = idcustomer.getText().trim().toUpperCase();
                String customerName = Name.getText().trim();
                String customerLastName = LastName.getText().trim();
                String customerEmail = Email.getText().trim();
                int customerType = Integer.parseInt(TextBeforeDot(customerBox.getSelectedItem().toString().trim()));
                int customerIdType = Integer.parseInt(TextBeforeDot(idBox.getSelectedItem().toString().trim()));
                if (customerName.length()>0 && customerId.length()>0){
                Customer newCustomer = new Customer();
                
                newCustomer.setId(customerId);
                newCustomer.setFirst_name(customerName);
                newCustomer.setLast_name(customerLastName);
                newCustomer.setEmail(customerEmail);
                newCustomer.setCustomer_type(customerType);
                newCustomer.setId_type(customerIdType);
                
                CustomerService customerService = new CustomerRepository();
                CreateCustomerUseCase createCustomerUseCase = new CreateCustomerUseCase(customerService);
                createCustomerUseCase.execute(newCustomer);
                JOptionPane.showMessageDialog(this, "Customer added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
                idcustomer.setText("");
                LastName.setText("");
                Email.setText("");
                customerBox.removeAllItems();
                idBox.removeAllItems();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerUI customerUI = new CustomerUI();
            customerUI.startCustomer(gobackindicator);
    }
    }

    private String TextBeforeDot(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('.');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
    }

    }

