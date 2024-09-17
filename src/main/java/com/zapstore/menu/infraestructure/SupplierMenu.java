package com.zapstore.menu.infraestructure;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.zapstore.city.infrastructure.cityui.CityUI;
import com.zapstore.country.infrastructure.countryui.CountryUI;
import com.zapstore.region.infrastructure.regionui.RegionUI;
import com.zapstore.supplier.infrastructure.supplierui.SupplierUI;
import com.zapstore.supplier_phone.infrastructure.supplier_phoneui.SupplierPhoneUI;
import com.zapstore.supplier_type.infrastructure.supplier_typeui.SupplierTypeUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.event.ActionListener;

public class SupplierMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton SupplierButton, TypesButton, PhoneButton, AssignamentButton, countryButton, RegionButton, cityButton, backButton;
    private int gobackindicator = 0;
    public SupplierMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapsuppliers.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 500, 400);
        add(logoImg);

        SupplierButton = new JButton("Supplier");
        SupplierButton.setBounds(550, 110, 170, 60);
        SupplierButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        SupplierButton.setForeground(new Color(255,242,45));
        SupplierButton.setBackground(new Color(10,20,28));
        SupplierButton.addActionListener(this);
        add(SupplierButton);

        TypesButton = new JButton("Type");
        TypesButton.setBounds(550, 190, 170, 60);
        TypesButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        TypesButton.setForeground(new Color(255,242,45));
        TypesButton.setBackground(new Color(10,20,28));
        TypesButton.addActionListener(this);
        add(TypesButton);

        PhoneButton = new JButton("Phones");
        PhoneButton.setBounds(550, 270, 170, 60);
        PhoneButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        PhoneButton.setForeground(new Color(255,242,45));
        PhoneButton.setBackground(new Color(10,20,28));
        PhoneButton.addActionListener(this);
        add(PhoneButton);

        AssignamentButton = new JButton("Id Type");
        AssignamentButton.setBounds(550, 350, 370, 60);
        AssignamentButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        AssignamentButton.setForeground(new Color(255,242,45));
        AssignamentButton.setBackground(new Color(10,20,28));
        AssignamentButton.addActionListener(this);
        add(AssignamentButton);

        countryButton = new JButton("Country");
        countryButton.setBounds(750, 110, 170, 60);
        countryButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        countryButton.setForeground(new Color(255,242,45));
        countryButton.setBackground(new Color(10,20,28));
        countryButton.addActionListener(this);
        add(countryButton);

        RegionButton = new JButton("Region");
        RegionButton.setBounds(750, 190, 170, 60);
        RegionButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        RegionButton.setForeground(new Color(255,242,45));
        RegionButton.setBackground(new Color(10,20,28));
        RegionButton.addActionListener(this);
        add(RegionButton);

        cityButton = new JButton("City");
        cityButton.setBounds(750, 270, 170, 60);
        cityButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        cityButton.setForeground(new Color(255,242,45));
        cityButton.setBackground(new Color(10,20,28));
        cityButton.addActionListener(this);
        add(cityButton);
        
        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

    }
    public void startSupplierMenu(int number) {
        SupplierMenu supplierMenu = new SupplierMenu();
        supplierMenu.gobackindicator=number;
        supplierMenu.setBounds(0, 0, 1000, 600);
        supplierMenu.setVisible(true);
        supplierMenu.setResizable(false);
        supplierMenu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==SupplierButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        SupplierUI supplierUI = new SupplierUI();
                        supplierUI.startSupplier(gobackindicator);
                    });
                });
        }
        
        if(e.getSource()==TypesButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        SupplierTypeUI supplier_typeui = new SupplierTypeUI();
                        supplier_typeui.startSupplierType(gobackindicator);
                    });
                });
        }

        if(e.getSource()==PhoneButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        SupplierPhoneUI supplier_phoneui = new SupplierPhoneUI();
                        supplier_phoneui.startSupplierPhone(gobackindicator);
                    });
                });
        }
        if(e.getSource()==AssignamentButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    // IdTypeUI id_typeui = new IdTypeUI();
                    // id_typeui.startIdType(gobackindicator);
                });
            });
        }
        if(e.getSource()==countryButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CountryUI countryUI = new CountryUI();
                        countryUI.startCountry(gobackindicator, 2);
                    });
                });
        }
        if(e.getSource()==RegionButton){
SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        RegionUI regionUI = new RegionUI();
                        regionUI.startRegion(gobackindicator, 2);
                    });
                });
        }
        if(e.getSource()==cityButton){
SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CityUI cityUI = new CityUI();
                        cityUI.startCity(gobackindicator, 2);
                    });
                });
        }

        if(e.getSource()==backButton){
            // Oculta la ventana actual
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                if (gobackindicator==1){
                    SwingUtilities.invokeLater(() -> {
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.startAdminMenu();
                    });
                } else if (gobackindicator==2) {
                    SwingUtilities.invokeLater(() -> {
                        StorageMenu storageMenu = new StorageMenu();
                        storageMenu.startStorageMenu();
                    });
                }
                // Abre la nueva ventana 
            });
        }

        }

    }