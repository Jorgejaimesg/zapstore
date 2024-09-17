package com.zapstore.supplier_phone.infrastructure.supplier_phoneui;
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

import com.zapstore.supplier_phone.application.FindSupplierPhoneByIdUseCase;
import com.zapstore.supplier_phone.application.UpdateSupplierPhoneUseCase;
import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;
import com.zapstore.supplier_phone.infrastructure.SupplierPhoneRepository;

import javax.swing.JOptionPane;

public class UpdateSupplierPhoneUI extends JFrame implements ActionListener {
    private JLabel  labelcode, title, logoImg, labelName, labelPhone, Name;
    private JTextField codesupplier_phone, Phone;
    private JButton updateButton, findButton, backButton;
    private int gobackindicator;

    SupplierPhoneService supplier_phoneService = new SupplierPhoneRepository();
    FindSupplierPhoneByIdUseCase findSupplierPhoneByIdUseCase = new FindSupplierPhoneByIdUseCase(supplier_phoneService);
    UpdateSupplierPhoneUseCase updateSupplierPhoneUseCase = new UpdateSupplierPhoneUseCase(supplier_phoneService);

    public UpdateSupplierPhoneUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update SupplierPhone");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/phone.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Phone");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codesupplier_phone = new JTextField();
        codesupplier_phone.setBounds(170, 130, 220, 30);
        codesupplier_phone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codesupplier_phone.setForeground(new Color(255,242,45));
        codesupplier_phone.setBackground(new Color(10,20,28));
        add(codesupplier_phone);

        labelName = new JLabel("Supplier : ");
        labelName.setBounds(60, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JLabel();
        Name.setBounds(170, 170, 220, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);
        
        labelPhone = new JLabel("Phone : ");
        labelPhone.setBounds(95, 210, 150, 30);
        labelPhone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelPhone.setForeground(new Color(255,242,45));
        add(labelPhone);

        Phone = new JTextField();
        Phone.setBounds(170, 210, 220, 30);
        Phone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Phone.setForeground(new Color(255,242,45));
        Phone.setBackground(new Color(10,20,28));
        Phone.setVisible(false);
        add(Phone);

        updateButton = new JButton("Update");
        updateButton.setBounds(125, 290, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 290, 120, 30);
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

    public void startUpdateSupplierPhone(int gobackindicator) {
        UpdateSupplierPhoneUI updateUI = new UpdateSupplierPhoneUI();
        updateUI.setBounds(0, 0, 520, 450);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int supplier_phoneCode = Integer.parseInt(codesupplier_phone.getText().trim());
                if (supplier_phoneCode>0){
                    Optional<SupplierPhone> supplier_phone = findSupplierPhoneByIdUseCase.findSupplierPhoneById(supplier_phoneCode);
                    if (supplier_phone.isPresent()){
                        Name.setVisible(true);
                        Name.setText(supplier_phone.get().getSupplier_id());
                        Phone.setVisible(true);
                        Phone.setText(String.valueOf(supplier_phone.get().getPhone_number()));
                    } else {
                        JOptionPane.showMessageDialog(this, "The SupplierPhone doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            int supplier_phoneid = Integer.parseInt(codesupplier_phone.getText().trim());
            String supplier_id = Name.getText().trim();
            int phone_number= Integer.parseInt(Phone.getText().trim());
            SupplierPhone updatedSupplierPhone = new SupplierPhone();
            updatedSupplierPhone.setId(supplier_phoneid);
            updatedSupplierPhone.setSupplier_id(supplier_id);
            updatedSupplierPhone.setPhone_number(phone_number);
                updateSupplierPhoneUseCase.execute(updatedSupplierPhone);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
            codesupplier_phone.setText("");
            Phone.setText("");
            Name.setVisible(false);
            Phone.setVisible(false);
    
            }
            

        if(e.getSource()==backButton){
            this.setVisible(false);
            SupplierPhoneUI supplier_phoneUI = new SupplierPhoneUI();
            supplier_phoneUI.startSupplierPhone(gobackindicator);
        }
    }
}
