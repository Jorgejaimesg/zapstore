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
public class CashierMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton customerButton, SaleButton, ProductsButton, backButton;

    public CashierMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Zap Cashier");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapcashier.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 600, 500);
        add(logoImg);

        customerButton = new JButton("Customer");
        customerButton.setBounds(700, 190, 150, 60);
        customerButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        customerButton.setForeground(new Color(255,242,45));
        customerButton.setBackground(new Color(10,20,28));
        customerButton.addActionListener(this);
        add(customerButton);

        SaleButton = new JButton("Sales");
        SaleButton.setBounds(700, 270, 150, 60);
        SaleButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        SaleButton.setForeground(new Color(255,242,45));
        SaleButton.setBackground(new Color(10,20,28));
        SaleButton.addActionListener(this);
        add(SaleButton);

        ProductsButton = new JButton("Products");
        ProductsButton.setBounds(700, 350, 150, 60);
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
    public void startCashierMenu() {
        CashierMenu mainMenu = new CashierMenu();
        mainMenu.setBounds(0, 0, 1000, 600);
        mainMenu.setVisible(true);
        mainMenu.setResizable(false);
        mainMenu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==customerButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    CustomerMenu customerMenu = new CustomerMenu();
                    customerMenu.startCustomerMenu(2);
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
                    productMenu.startProductMenu(2);
                });
            });
        }

        if(e.getSource()==SaleButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    SaleMenu saleMenu = new SaleMenu();
                    saleMenu.startSaleMenu(2);
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
