package com.zapstore.customer_phone.infrastructure.customer_phoneui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.zapstore.customer_phone.application.CreateCustomerPhoneUseCase;
import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;
import com.zapstore.customer_phone.infrastructure.CustomerPhoneRepository;

public class AddCustomerPhoneUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelNumber, labelCustomer;
    private JButton addButton, backButton;
    private JTextField Number, Customer;
    private int gobackindicator;

    public AddCustomerPhoneUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add CustomerPhone");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/phone.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Phone");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelCustomer = new JLabel("Customer : ");
        labelCustomer.setBounds(60, 130, 150, 30);
        labelCustomer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCustomer.setForeground(new Color(255,242,45));
        add(labelCustomer);

        Customer = new JTextField();
        Customer.setBounds(170, 130, 255, 30);
        Customer.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Customer.setForeground(new Color(255,242,45));
        Customer.setBackground(new Color(10,20,28));
        add(Customer);
        
        labelNumber = new JLabel("Number : ");
        labelNumber.setBounds(60, 170, 150, 30);
        labelNumber.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelNumber.setForeground(new Color(255,242,45));
        add(labelNumber);

        Number = new JTextField();
        Number.setBounds(170, 170, 255, 30);
        Number.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Number.setForeground(new Color(255,242,45));
        Number.setBackground(new Color(10,20,28));
        add(Number);


        addButton = new JButton("Add");
        addButton.setBounds(125, 230, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 230, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddCustomerPhone(int gobackindicator) {
        AddCustomerPhoneUI addUI = new AddCustomerPhoneUI();
        addUI.setBounds(0, 0, 500, 350);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator=gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                int customer_phone = Integer.parseInt(Number.getText().trim());
                String customer_id = Customer.getText().trim();
                if (customer_phone>99999999 && customer_id.length()>0){
                CustomerPhone newCustomerPhone = new CustomerPhone();
                
                newCustomerPhone.setPhone_number(customer_phone);
                newCustomerPhone.setCustomer_id(customer_id);

                CustomerPhoneService customer_phoneService = new CustomerPhoneRepository();
                CreateCustomerPhoneUseCase createCustomerPhoneUseCase = new CreateCustomerPhoneUseCase(customer_phoneService);
                createCustomerPhoneUseCase.execute(newCustomerPhone);
                JOptionPane.showMessageDialog(this, "CustomerPhone added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Customer.setText("");
                Number.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerPhoneUI customer_phoneUI = new CustomerPhoneUI();
            customer_phoneUI.startCustomerPhone(gobackindicator);
    }
    }

    }

