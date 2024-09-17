package com.zapstore.menu.infraestructure;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton CustomerButton, SupplierButton, OrderButton, saleButton, ProductsButton, backButton;

    public AdminMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/zapadmin.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapadmin.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 600, 500);
        add(logoImg);

        CustomerButton = new JButton("Customers");
        CustomerButton.setBounds(700, 110, 170, 60);
        CustomerButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        CustomerButton.setForeground(new Color(255,242,45));
        CustomerButton.setBackground(new Color(10,20,28));
        CustomerButton.addActionListener(this);
        add(CustomerButton);

        SupplierButton = new JButton("Suppliers");
        SupplierButton.setBounds(700, 190, 170, 60);
        SupplierButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        SupplierButton.setForeground(new Color(255,242,45));
        SupplierButton.setBackground(new Color(10,20,28));
        SupplierButton.addActionListener(this);
        add(SupplierButton);

        OrderButton = new JButton("Orders");
        OrderButton.setBounds(700, 270, 170, 60);
        OrderButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        OrderButton.setForeground(new Color(255,242,45));
        OrderButton.setBackground(new Color(10,20,28));
        OrderButton.addActionListener(this);
        add(OrderButton);

        saleButton = new JButton("Sales");
        saleButton.setBounds(700, 350, 170, 60);
        saleButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        saleButton.setForeground(new Color(255,242,45));
        saleButton.setBackground(new Color(10,20,28));
        saleButton.addActionListener(this);
        add(saleButton);

        ProductsButton = new JButton("Products");
        ProductsButton.setBounds(700, 430, 170, 60);
        ProductsButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        ProductsButton.setForeground(new Color(255,242,45));
        ProductsButton.setBackground(new Color(10,20,28));
        ProductsButton.addActionListener(this);
        add(ProductsButton);
        
        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

    }
    public void startAdminMenu() {
        AdminMenu mainMenu = new AdminMenu();
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
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.startCustomerMenu(1);
                });
            });
        }

        if(e.getSource()==OrderButton){

            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    OrderMenu orderMenu = new OrderMenu();
                    orderMenu.startOrderMenu(1);
                });
            });
        }
        if(e.getSource()==saleButton){

            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    SaleMenu saleMenu = new SaleMenu();
                    saleMenu.startSaleMenu(1);
                });
            });
        }
        if(e.getSource()==ProductsButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    ProductMenu productMenu = new ProductMenu();
                    productMenu.startProductMenu(1);
                });
            });
        }
        

        if(e.getSource()==SupplierButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    SupplierMenu supplierMenu = new SupplierMenu();
                    supplierMenu.startSupplierMenu(1);
                });
            });
        }

        if(e.getSource()==backButton){
            // Oculta la ventana actual
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.startMainMenu();
                });
            });
        }

        }

    }