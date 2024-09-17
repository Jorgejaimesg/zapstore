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

public class MainMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton AdminButton, CashierButton, StorageButton;

    public MainMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zap_logo.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 600, 500);
        add(logoImg);

        AdminButton = new JButton("Admin");
        AdminButton.setBounds(700, 190, 150, 60);
        AdminButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        AdminButton.setForeground(new Color(255,242,45));
        AdminButton.setBackground(new Color(10,20,28));
        AdminButton.addActionListener(this);
        add(AdminButton);

        CashierButton = new JButton("Cashier");
        CashierButton.setBounds(700, 270, 150, 60);
        CashierButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        CashierButton.setForeground(new Color(255,242,45));
        CashierButton.setBackground(new Color(10,20,28));
        CashierButton.addActionListener(this);
        add(CashierButton);

        StorageButton = new JButton("Storage");
        StorageButton.setBounds(700, 350, 150, 60);
        StorageButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        StorageButton.setForeground(new Color(255,242,45));
        StorageButton.setBackground(new Color(10,20,28));
        StorageButton.addActionListener(this);
        add(StorageButton);

    }
    public void startMainMenu() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setBounds(0, 0, 1000, 600);
        mainMenu.setVisible(true);
        mainMenu.setResizable(false);
        mainMenu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==AdminButton){
            // Oculta la ventana actual
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.startAdminMenu();
                });
            });
        }


        if(e.getSource()==StorageButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        StorageMenu storageMenu = new StorageMenu();
                        storageMenu.startStorageMenu();
                    });
                });
            }
        

        if(e.getSource()==CashierButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CashierMenu cashierMenu = new CashierMenu();
                        cashierMenu.startCashierMenu();
                    });
                });
            }
        }

        }

    

