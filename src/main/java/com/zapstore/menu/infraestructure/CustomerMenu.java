package com.zapstore.menu.infraestructure;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.zapstore.city.infrastructure.cityui.CityUI;
import com.zapstore.country.infrastructure.countryui.CountryUI;
import com.zapstore.customer.infrastructure.customerui.CustomerUI;
import com.zapstore.customer_address.infrastructure.customer_addressui.CustomerAddressUI;
import com.zapstore.customer_phone.infrastructure.customer_phoneui.CustomerPhoneUI;
import com.zapstore.customer_type.infrastructure.customer_typeui.CustomerTypeUI;
import com.zapstore.id_type.infrastructure.id_typeui.IdTypeUI;
import com.zapstore.region.infrastructure.regionui.RegionUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.event.ActionListener;

public class CustomerMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton CustomerButton, AddressButton, PhoneButton, idTypeButton, typeButton, countryButton, RegionButton, cityButton, backButton;
    private int gobackindicator = 0;
    public CustomerMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapcustomers.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 500, 400);
        add(logoImg);

        CustomerButton = new JButton("Customer");
        CustomerButton.setBounds(550, 110, 170, 60);
        CustomerButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        CustomerButton.setForeground(new Color(255,242,45));
        CustomerButton.setBackground(new Color(10,20,28));
        CustomerButton.addActionListener(this);
        add(CustomerButton);

        AddressButton = new JButton("Address");
        AddressButton.setBounds(550, 190, 170, 60);
        AddressButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        AddressButton.setForeground(new Color(255,242,45));
        AddressButton.setBackground(new Color(10,20,28));
        AddressButton.addActionListener(this);
        add(AddressButton);

        PhoneButton = new JButton("Phones");
        PhoneButton.setBounds(550, 270, 170, 60);
        PhoneButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        PhoneButton.setForeground(new Color(255,242,45));
        PhoneButton.setBackground(new Color(10,20,28));
        PhoneButton.addActionListener(this);
        add(PhoneButton);

        idTypeButton = new JButton("Id Type");
        idTypeButton.setBounds(550, 350, 170, 60);
        idTypeButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        idTypeButton.setForeground(new Color(255,242,45));
        idTypeButton.setBackground(new Color(10,20,28));
        idTypeButton.addActionListener(this);
        add(idTypeButton);

        typeButton = new JButton("Type");
        typeButton.setBounds(750, 110, 170, 60);
        typeButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        typeButton.setForeground(new Color(255,242,45));
        typeButton.setBackground(new Color(10,20,28));
        typeButton.addActionListener(this);
        add(typeButton);

        countryButton = new JButton("Country");
        countryButton.setBounds(750, 190, 170, 60);
        countryButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        countryButton.setForeground(new Color(255,242,45));
        countryButton.setBackground(new Color(10,20,28));
        countryButton.addActionListener(this);
        add(countryButton);

        RegionButton = new JButton("Region");
        RegionButton.setBounds(750, 270, 170, 60);
        RegionButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        RegionButton.setForeground(new Color(255,242,45));
        RegionButton.setBackground(new Color(10,20,28));
        RegionButton.addActionListener(this);
        add(RegionButton);

        cityButton = new JButton("City");
        cityButton.setBounds(750, 350, 170, 60);
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
    public void startCustomerMenu(int number) {
        CustomerMenu mainMenu = new CustomerMenu();
        mainMenu.gobackindicator=number;
        mainMenu.setBounds(0, 0, 1000, 600);
        mainMenu.setVisible(true);
        mainMenu.setResizable(false);
        mainMenu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==CustomerButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CustomerUI customerui = new CustomerUI();
                        customerui.startCustomer(gobackindicator);
                    });
                });
        }
        
        if(e.getSource()==AddressButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CustomerAddressUI customer_addressui = new CustomerAddressUI();
                        customer_addressui.startCustomerAddress(gobackindicator);
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
                        CustomerPhoneUI customer_phoneui = new CustomerPhoneUI();
                        customer_phoneui.startCustomerPhone(gobackindicator);
                    });
                });
        }
        if(e.getSource()==idTypeButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    IdTypeUI id_typeui = new IdTypeUI();
                    id_typeui.startIdType(gobackindicator);
                });
            });
        }
        if(e.getSource()==typeButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CustomerTypeUI customer_typeUI = new CustomerTypeUI();
                        customer_typeUI.startCustomerType(gobackindicator);
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
                        countryUI.startCountry(gobackindicator, 1);
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
                        regionUI.startRegion(gobackindicator, 1);
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
                        cityUI.startCity(gobackindicator, 1);
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
                        CashierMenu cash = new CashierMenu();
                        cash.startCashierMenu();
                    });
                }
                // Abre la nueva ventana 
            });
        }

        }

    }