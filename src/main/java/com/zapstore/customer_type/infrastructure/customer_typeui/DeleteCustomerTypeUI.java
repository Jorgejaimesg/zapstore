package com.zapstore.customer_type.infrastructure.customer_typeui;

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
import javax.swing.JOptionPane;

import com.zapstore.customer_type.application.DeleteCustomerTypeUseCase;
import com.zapstore.customer_type.application.FindAllCustomerTypeUseCase;
import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;
import com.zapstore.customer_type.infrastructure.CustomerTypeRepository;

public class DeleteCustomerTypeUI extends JFrame implements ActionListener{
    CustomerTypeService customer_typeService = new CustomerTypeRepository();
    DeleteCustomerTypeUseCase deleteCustomerTypeUseCase = new DeleteCustomerTypeUseCase(customer_typeService);
    FindAllCustomerTypeUseCase findAllCustomerTypeUseCase = new FindAllCustomerTypeUseCase(customer_typeService);
    List<CustomerType> countries = findAllCustomerTypeUseCase.findAllCustomerType();

    private JLabel logoImg, title, labelName;
    private JButton deleteButton, backButton;
    private JComboBox<String> Name;
    private int gobackindicator;

    public DeleteCustomerTypeUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete CustomerType");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Customer Type");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 20));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("type: ");
        labelName.setBounds(65, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JComboBox<String>();
        Name.setBounds(170, 130, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);
        Name.addItem("");
        for(CustomerType customer_type : countries){
            Name.addItem(customer_type.getName());
        };



        deleteButton = new JButton("Delete");
        deleteButton.setBounds(125, 200, 120, 30);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 200, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startDeleteCustomerType(int gobackindicator) {
        DeleteCustomerTypeUI deleteUIA = new DeleteCustomerTypeUI();
        deleteUIA.setBounds(0, 0, 500, 300);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator = gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                String customer_typeName = Name.getSelectedItem().toString();
                if (customer_typeName.length()>0){   
                deleteCustomerTypeUseCase.execute(customer_typeName);
                JOptionPane.showMessageDialog(this, "CustomerType deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setSelectedItem("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            this.setVisible(false);
            CustomerTypeUI customer_typeUI = new CustomerTypeUI();
            customer_typeUI.startCustomerType(gobackindicator);
    }
    }
    }

