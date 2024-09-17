package com.zapstore.customer.infrastructure.customerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import com.zapstore.customer.application.FindCustomerByIdUseCase;
import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;
import com.zapstore.customer.infrastructure.CustomerRepository;
import com.zapstore.customer_type.application.FindCustomerTypeByIdUseCase;
import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;
import com.zapstore.customer_type.infrastructure.CustomerTypeRepository;
import com.zapstore.id_type.application.FindIdTypeByIdUseCase;
import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;
import com.zapstore.id_type.infrastructure.IdTypeRepository;

public class FindCustomerUI extends JFrame implements ActionListener{
    private JLabel  labelId, title, logoImg, labelName, Name, labelIdType, labelCustomerType, labelEmail, labelLastName, LastName, Email, idBox, customerBox;
    private JTextField idcustomer;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;

    CustomerService customerService = new CustomerRepository();
    FindCustomerByIdUseCase findCustomerByIdUseCase = new FindCustomerByIdUseCase(customerService);

    CustomerTypeService customerTypeService = new CustomerTypeRepository();
    FindCustomerTypeByIdUseCase findCustomerTypeByIdUseCase = new FindCustomerTypeByIdUseCase(customerTypeService);

    IdTypeService idTypeService = new IdTypeRepository();
    FindIdTypeByIdUseCase findIdTypeByIdUseCase = new FindIdTypeByIdUseCase(idTypeService);

    public FindCustomerUI(){
                setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Customer");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find Customer");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelId = new JLabel("Id: ");
        labelId.setBounds(130, 130, 100, 30);
        labelId.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelId.setForeground(new Color(255,242,45));
        add(labelId);

        idcustomer = new JTextField();
        idcustomer.setBounds(170, 130, 220, 30);
        idcustomer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idcustomer.setForeground(new Color(255,242,45));
        idcustomer.setBackground(new Color(10,20,28));
        add(idcustomer);

        labelIdType = new JLabel("Id Type: ");
        labelIdType.setBounds(500, 130, 150, 30);
        labelIdType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelIdType.setForeground(new Color(255,242,45));
        add(labelIdType);

        idBox = new JLabel();
        idBox.setBounds(600, 130, 255, 30);
        idBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idBox.setForeground(new Color(255,242,45));
        idBox.setBackground(new Color(10,20,28));
        idBox.setVisible(false);
        add(idBox);

        labelCustomerType = new JLabel("Type: ");
        labelCustomerType.setBounds(500, 170, 150, 30);
        labelCustomerType.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCustomerType.setForeground(new Color(255,242,45));
        add(labelCustomerType);

        customerBox = new JLabel();
        customerBox.setBounds(600, 170, 255, 30);
        customerBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        customerBox.setForeground(new Color(255,242,45));
        customerBox.setBackground(new Color(10,20,28));
        customerBox.setVisible(false);
        add(customerBox);

        labelName = new JLabel("Name: ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);

        labelLastName = new JLabel("Lastname: ");
        labelLastName.setBounds(65, 210, 150, 30);
        labelLastName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelLastName.setForeground(new Color(255,242,45));
        add(labelLastName);

        LastName = new JLabel();
        LastName.setBounds(170, 210, 255, 30);
        LastName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        LastName.setForeground(new Color(255,242,45));
        LastName.setBackground(new Color(10,20,28));
        LastName.setVisible(false);
        add(LastName);

        labelEmail = new JLabel("Email : ");
        labelEmail.setBounds(500, 210, 150, 30);
        labelEmail.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelEmail.setForeground(new Color(255,242,45));
        add(labelEmail);

        Email = new JLabel();
        Email.setBounds(600, 210, 255, 30);
        Email.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Email.setForeground(new Color(255,242,45));
        Email.setBackground(new Color(10,20,28));
        Email.setVisible(false);
        add(Email);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(400, 300, 120, 30);
        newButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        newButton.setForeground(new Color(255,242,45));
        newButton.setBackground(new Color(10,20,28));
        newButton.addActionListener(this);
        add(newButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }

    public void startFindCustomer(int gobackindicator) {
        FindCustomerUI findUI = new FindCustomerUI();
        findUI.setBounds(0, 0, 1000, 400);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.gobackindicator =gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                String customerId = idcustomer.getText().trim().toUpperCase();
                if (customerId.length()>0){
                    Optional<Customer> customer = findCustomerByIdUseCase.findCustomerById(customerId);
                    if (customer.isPresent()){
                        Customer foundCustomer = customer.get();
                        Name.setVisible(true);
                        Name.setText(foundCustomer.getFirst_name());
                        idBox.setVisible(true);
                        Optional<IdType> typeId = findIdTypeByIdUseCase.findIdTypeById(foundCustomer.getId_type());
                        idBox.setText(typeId.get().getName());
                        customerBox.setVisible(true);
                        Optional<CustomerType> customerType = findCustomerTypeByIdUseCase.findCustomerTypeById(foundCustomer.getCustomer_type());
                        customerBox.setText(customerType.get().getName());
                        LastName.setVisible(true);
                        LastName.setText(foundCustomer.getLast_name());
                        Email.setVisible(true);
                        Email.setText(foundCustomer.getEmail());
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "The Customer doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==newButton){
            Name.setVisible(false);
            Name.setText("");
            idBox.setVisible(false);
            idBox.setText("");
            customerBox.setVisible(false);
            customerBox.setText("");
            LastName.setVisible(false);
            LastName.setText("");
            Email.setVisible(false);
            Email.setText("");
            idcustomer.setText("");
        
        }
        if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerUI customerUI = new CustomerUI();
            customerUI.startCustomer(gobackindicator);
        }

    }

    }

