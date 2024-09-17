package com.zapstore.customer_phone.infrastructure.customer_phoneui;

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

import com.zapstore.customer_phone.application.FindCustomerPhoneByIdUseCase;
import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;
import com.zapstore.customer_phone.infrastructure.CustomerPhoneRepository;

public class FindCustomerPhoneUI extends JFrame implements ActionListener{
    private JLabel  labelcode, title, logoImg, labelName, Name, Phone, labelPhone;
    private JTextField codecustomer_phone;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;

    CustomerPhoneService customer_phoneService = new CustomerPhoneRepository();
    FindCustomerPhoneByIdUseCase findCustomerPhoneByIdUseCase = new FindCustomerPhoneByIdUseCase(customer_phoneService);

    public FindCustomerPhoneUI(){
                setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Find CustomerPhone");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/phone.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Find Phone");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codecustomer_phone = new JTextField();
        codecustomer_phone.setBounds(170, 130, 220, 30);
        codecustomer_phone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codecustomer_phone.setForeground(new Color(255,242,45));
        codecustomer_phone.setBackground(new Color(10,20,28));
        add(codecustomer_phone);

        labelName = new JLabel("Customer : ");
        labelName.setBounds(60, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 170, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setVisible(false);
        add(Name);
        
        labelPhone = new JLabel("Phone : ");
        labelPhone.setBounds(95, 210, 150, 30);
        labelPhone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelPhone.setForeground(new Color(255,242,45));
        add(labelPhone);

        Phone = new JLabel();
        Phone.setBounds(170, 210, 220, 30);
        Phone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Phone.setForeground(new Color(255,242,45));
        Phone.setVisible(false);
        add(Phone);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 290, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(125, 290, 120, 30);
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

    public void startFindCustomerPhone(int gobackindicator) {
        FindCustomerPhoneUI findUI = new FindCustomerPhoneUI();
        findUI.setBounds(0, 0, 520, 450);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int customer_phoneCode = Integer.parseInt(codecustomer_phone.getText().trim().toUpperCase());
                if (customer_phoneCode>0){
                    Optional<CustomerPhone> customer_phone = findCustomerPhoneByIdUseCase.findCustomerPhoneById(customer_phoneCode);
                    if (customer_phone.isPresent()){
                        Name.setVisible(true);
                        Phone.setVisible(true);
                        Name.setText(customer_phone.get().getCustomer_id());
                        Phone.setText(String.valueOf(customer_phone.get().getPhone_number()));
                    } else {
                        JOptionPane.showMessageDialog(this, "The CustomerPhone doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

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
            Phone.setVisible(false);
            Phone.setText("");
            codecustomer_phone.setText("");
        
        }
        if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerPhoneUI customer_phoneUI = new CustomerPhoneUI();
            customer_phoneUI.startCustomerPhone(gobackindicator);
        }

    }

    }

