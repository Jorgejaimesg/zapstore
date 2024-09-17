package com.zapstore.supplier_phone.infrastructure.supplier_phoneui;

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
import javax.swing.JTextField;

import com.zapstore.supplier_phone.application.DeleteSupplierPhoneUseCase;
import com.zapstore.supplier_phone.application.FindSupplierPhoneBySupplierUseCase;
import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;
import com.zapstore.supplier_phone.infrastructure.SupplierPhoneRepository;

public class DeleteSupplierPhoneUI extends JFrame implements ActionListener{
    SupplierPhoneService supplier_phoneService = new SupplierPhoneRepository();
    DeleteSupplierPhoneUseCase deleteSupplierPhoneUseCase = new DeleteSupplierPhoneUseCase(supplier_phoneService);
    FindSupplierPhoneBySupplierUseCase findSupplierPhoneBySupplierUseCase = new FindSupplierPhoneBySupplierUseCase(supplier_phoneService);

    private JLabel logoImg, title, labelName, labelPhone;
    private JTextField Name;
    private JButton deleteButton, backButton, Namebutton;
    private JComboBox<String> phonesBox;
    private int gobackindicator;

    public DeleteSupplierPhoneUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete SupplierPhone");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/phone.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Delete Phone");
        title.setBounds(180, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Supplier: ");
        labelName.setBounds(65, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);
        
        Name = new JTextField();
        Name.setBounds(170, 130, 190, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);
        
        Namebutton = new JButton("🔍");
        Namebutton.setBounds(370, 130, 60, 30);
        Namebutton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Namebutton.setForeground(new Color(255,242,45));
        Namebutton.setBackground(new Color(10,20,28));
        add(Namebutton);
        Namebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarphones();
            }
        });
        
        labelPhone = new JLabel("Phone: ");
        labelPhone.setBounds(65, 170, 150, 30);
        labelPhone.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelPhone.setForeground(new Color(255,242,45));
        add(labelPhone);
        
        phonesBox = new JComboBox<String>();
        phonesBox.setBounds(170, 170, 260, 30);
        phonesBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        phonesBox.setForeground(new Color(255,242,45));
        phonesBox.setBackground(new Color(10,20,28));
        add(phonesBox);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(125, 230, 120, 30);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(275, 230, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startDeleteSupplierPhone(int gobackindicator) {
        DeleteSupplierPhoneUI deleteUIA = new DeleteSupplierPhoneUI();
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
                int supplier_phoneid = Integer.parseInt(TextBeforeDot(phonesBox.getSelectedItem().toString()));
                if (supplier_phoneid>0){   
                deleteSupplierPhoneUseCase.execute(supplier_phoneid);
                JOptionPane.showMessageDialog(this, "SupplierPhone deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
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

    private void actualizarphones() {
        phonesBox.removeAllItems();
        try {
            String supplierID = Name.getText();
            List<SupplierPhone> Phones = findSupplierPhoneBySupplierUseCase.findSupplierPhoneBySupplier(supplierID);
            for(SupplierPhone supplierPhone : Phones){
                phonesBox.addItem(String.valueOf(supplierPhone.getId())+". "+String.valueOf(supplierPhone.getPhone_number()));
            };
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    private String TextBeforeDot(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('.');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
    }
}
