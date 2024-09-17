package com.zapstore.supplier_phone.infrastructure.supplier_phoneui;

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

import com.zapstore.supplier_phone.application.CreateSupplierPhoneUseCase;
import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;
import com.zapstore.supplier_phone.infrastructure.SupplierPhoneRepository;

public class AddSupplierPhoneUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelNumber, labelSupplier;
    private JButton addButton, backButton;
    private JTextField Number, Supplier;
    private int gobackindicator;

    public AddSupplierPhoneUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add SupplierPhone");
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

        labelSupplier = new JLabel("Supplier : ");
        labelSupplier.setBounds(60, 130, 150, 30);
        labelSupplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSupplier.setForeground(new Color(255,242,45));
        add(labelSupplier);

        Supplier = new JTextField();
        Supplier.setBounds(170, 130, 255, 30);
        Supplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Supplier.setForeground(new Color(255,242,45));
        Supplier.setBackground(new Color(10,20,28));
        add(Supplier);
        
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

    public void startAddSupplierPhone(int gobackindicator) {
        AddSupplierPhoneUI addUI = new AddSupplierPhoneUI();
        addUI.setBounds(0, 0, 500, 350);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                int supplier_phone = Integer.parseInt(Number.getText().trim());
                String supplier_id = Supplier.getText().trim();
                if (supplier_phone>99999999 && supplier_id.length()>0){
                SupplierPhone newSupplierPhone = new SupplierPhone();
                
                newSupplierPhone.setPhone_number(supplier_phone);
                newSupplierPhone.setSupplier_id(supplier_id);

                SupplierPhoneService supplier_phoneService = new SupplierPhoneRepository();
                CreateSupplierPhoneUseCase createSupplierPhoneUseCase = new CreateSupplierPhoneUseCase(supplier_phoneService);
                createSupplierPhoneUseCase.execute(newSupplierPhone);
                JOptionPane.showMessageDialog(this, "SupplierPhone added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Supplier.setText("");
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
            SupplierPhoneUI supplier_phoneUI = new SupplierPhoneUI();
            supplier_phoneUI.startSupplierPhone(gobackindicator);
    }
    }

    }

