package com.zapstore.customer.infrastructure.customerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.zapstore.customer.application.DeleteCustomerUseCase;
import com.zapstore.customer.domain.service.CustomerService;
import com.zapstore.customer.infrastructure.CustomerRepository;

public class DeleteCustomerUI extends JFrame implements ActionListener{
    CustomerService customerService = new CustomerRepository();
    DeleteCustomerUseCase deleteCustomerUseCase = new DeleteCustomerUseCase(customerService);

    private JLabel logoImg, title, labelId;
    private JButton deleteButton, backButton;
    private JTextField Id;
    private int gobackindicator;

    public DeleteCustomerUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Customer");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/customer.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Customer");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelId = new JLabel("Customer: ");
        labelId.setBounds(65, 130, 150, 30);
        labelId.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelId.setForeground(new Color(255,242,45));
        add(labelId);

        Id = new JTextField();
        Id.setBounds(170, 130, 255, 30);
        Id.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Id.setForeground(new Color(255,242,45));
        Id.setBackground(new Color(10,20,28));
        add(Id);

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

    public void startDeleteCustomer(int gobackindicator) {
        DeleteCustomerUI deleteUIA = new DeleteCustomerUI();
        deleteUIA.setBounds(0, 0, 500, 300);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator =gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                String customerId = Id.getText().trim();
                if (customerId.length()>0){   
                deleteCustomerUseCase.execute(customerId);
                JOptionPane.showMessageDialog(this, "Customer deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Id.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Id.", "Error", JOptionPane.ERROR_MESSAGE);
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
    }

