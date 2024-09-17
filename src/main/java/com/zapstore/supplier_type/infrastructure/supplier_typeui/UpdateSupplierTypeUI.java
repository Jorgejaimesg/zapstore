package com.zapstore.supplier_type.infrastructure.supplier_typeui;
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

import com.zapstore.supplier_type.application.FindSupplierTypeByIdUseCase;
import com.zapstore.supplier_type.application.UpdateSupplierTypeUseCase;
import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;
import com.zapstore.supplier_type.infrastructure.SupplierTypeRepository;

import javax.swing.JOptionPane;

public class UpdateSupplierTypeUI extends JFrame implements ActionListener {
    private JLabel  labelcode, title, logoImg, labelName;
    private JTextField codesupplier_type, Name;
    private JButton updateButton, findButton, backButton;
    private int gobackindicator;

    SupplierTypeService supplier_typeService = new SupplierTypeRepository();
    FindSupplierTypeByIdUseCase findSupplierTypeByIdUseCase = new FindSupplierTypeByIdUseCase(supplier_typeService);
    UpdateSupplierTypeUseCase updateSupplierTypeUseCase = new UpdateSupplierTypeUseCase(supplier_typeService);

    public UpdateSupplierTypeUI(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Update Supplier Type");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/supplier_type.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Update Supplier Type");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 23));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelcode = new JLabel("Code : ");
        labelcode.setBounds(100, 130, 150, 30);
        labelcode.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelcode.setForeground(new Color(255,242,45));
        add(labelcode);

        codesupplier_type = new JTextField();
        codesupplier_type.setBounds(170, 130, 220, 30);
        codesupplier_type.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        codesupplier_type.setForeground(new Color(255,242,45));
        codesupplier_type.setBackground(new Color(10,20,28));
        add(codesupplier_type);

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

    public void startUpdateSupplierType(int gobackindicator) {
        UpdateSupplierTypeUI updateUI = new UpdateSupplierTypeUI();
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
                int supplier_typeCode = Integer.parseInt(codesupplier_type.getText().trim().toUpperCase());
                if (supplier_typeCode>0){
                    Optional<SupplierType> supplier_type = findSupplierTypeByIdUseCase.findSupplierTypeById(supplier_typeCode);
                    if (supplier_type.isPresent()){
                        Name.setVisible(true);
                        Name.setText(supplier_type.get().getName());
                    } else {
                        JOptionPane.showMessageDialog(this, "The SupplierType doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            int supplier_typeid = Integer.parseInt(codesupplier_type.getText().trim());
            String supplier_typeName = Name.getText().trim();
            SupplierType updatedSupplierType = new SupplierType();
            updatedSupplierType.setName(supplier_typeName);
            updatedSupplierType.setId(supplier_typeid);
            if (supplier_typeName.length()>0){
                updateSupplierTypeUseCase.execute(updatedSupplierType);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }
            System.out.println(updatedSupplierType);
            Name.setText("");
            codesupplier_type.setText("");
            Name.setVisible(false);
    }

        if(e.getSource()==backButton){
            this.setVisible(false);
            SupplierTypeUI supplier_typeUI = new SupplierTypeUI();
            supplier_typeUI.startSupplierType(gobackindicator);
        }
    }
    }
