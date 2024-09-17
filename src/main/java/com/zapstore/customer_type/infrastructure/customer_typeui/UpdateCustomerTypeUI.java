package com.zapstore.customer_type.infrastructure.customer_typeui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zapstore.customer_type.application.FindCustomerTypeByIdUseCase;
import com.zapstore.customer_type.application.UpdateCustomerTypeUseCase;
import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;
import com.zapstore.customer_type.infrastructure.CustomerTypeRepository;

import javax.swing.JOptionPane;

public class UpdateCustomerTypeUI extends JFrame implements ActionListener {
    private JLabel  labelcode, title, logoImg, labelName;
    private JTextField codecustomer_type, Name;
    private JButton updateButton, findButton, backButton;
    private int gobackindicator;

    CustomerTypeService customer_typeService = new CustomerTypeRepository();
    FindCustomerTypeByIdUseCase findCustomerTypeByIdUseCase = new FindCustomerTypeByIdUseCase(customer_typeService);
    UpdateCustomerTypeUseCase updateCustomerTypeUseCase = new UpdateCustomerTypeUseCase(customer_typeService);

    public UpdateCustomerTypeUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update CustomerType");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Customer Type");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 25));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codecustomer_type = new JTextField();
        codecustomer_type.setBounds(170, 130, 220, 30);
        codecustomer_type.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codecustomer_type.setForeground(new Color(255,242,45));
        codecustomer_type.setBackground(new Color(10,20,28));
        add(codecustomer_type);

        labelName = new JLabel("Name : ");
        labelName.setBounds(95, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 170, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);

        updateButton = new JButton("Update");
        updateButton.setBounds(125, 240, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 240, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }

    public void startUpdateCustomerType(int gobackindicator) {
        UpdateCustomerTypeUI updateUI = new UpdateCustomerTypeUI();
        updateUI.setBounds(0, 0, 520, 350);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int customer_typeCode = Integer.parseInt(codecustomer_type.getText().trim().toUpperCase());
                if (customer_typeCode>0){
                    Optional<CustomerType> customer_type = findCustomerTypeByIdUseCase.findCustomerTypeById(customer_typeCode);
                    if (customer_type.isPresent()){
                        Name.setVisible(true);
                        Name.setText(customer_type.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The CustomerType doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            int customer_typeid = Integer.parseInt(codecustomer_type.getText().trim());
            String customer_typeName = Name.getText().trim();
            CustomerType updatedCustomerType = new CustomerType();
            updatedCustomerType.setName(customer_typeName);
            updatedCustomerType.setId(customer_typeid);
            if (customer_typeName.length()>0){
                updateCustomerTypeUseCase.execute(updatedCustomerType);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }
            System.out.println(updatedCustomerType);
            Name.setText("");
            codecustomer_type.setText("");
            Name.setVisible(false);
    }

        if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerTypeUI customer_typeUI = new CustomerTypeUI();
            customer_typeUI.startCustomerType(gobackindicator);
        }
    }
    }
