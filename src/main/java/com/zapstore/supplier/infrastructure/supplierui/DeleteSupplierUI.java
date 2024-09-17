package com.zapstore.supplier.infrastructure.supplierui;

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

import com.zapstore.supplier.application.DeleteSupplierUseCase;
import com.zapstore.supplier.domain.service.SupplierService;
import com.zapstore.supplier.infrastructure.SupplierRepository;

public class DeleteSupplierUI extends JFrame implements ActionListener{
    SupplierService supplierService = new SupplierRepository();
    DeleteSupplierUseCase deleteSupplierUseCase = new DeleteSupplierUseCase(supplierService);

    private JLabel logoImg, title, labelId;
    private JTextField Id;
    private JButton deleteButton, backButton;
    private int gobackindicator;

    public DeleteSupplierUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Supplier");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/address.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Supplier");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelId = new JLabel("Supplier: ");
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

    public void startDeleteSupplier(int gobackindicator) {
        DeleteSupplierUI deleteUIA = new DeleteSupplierUI();
        deleteUIA.setBounds(0, 0, 500, 330);
        deleteUIA.setVisible(true);
        deleteUIA.setResizable(false);
        deleteUIA.setLocationRelativeTo(null);
        deleteUIA.gobackindicator = gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButton){

            try {
                String supplierid = Id.getText();
                if (supplierid.length()>0){   
                deleteSupplierUseCase.execute(supplierid);
                JOptionPane.showMessageDialog(this, "Supplier deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            SupplierUI supplierUI = new SupplierUI();
            supplierUI.startSupplier(gobackindicator);
    }
    
    }
}
