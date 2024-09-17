package com.zapstore.menu.infraestructure;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.zapstore.supplier_order.infrastructure.supplier_orderui.*;
import com.zapstore.payment_method.infrastructure.payment_methodui.PaymentMethodUI;
import com.zapstore.status_order.infrastructure.status_orderui.StatusOrderUI;
import com.zapstore.supplier_order.infrastructure.supplier_orderui.DeleteSupplierOrderUI;
import com.zapstore.supplier_order.infrastructure.supplier_orderui.NewSupplierOrderUI;
import com.zapstore.supplier_order.infrastructure.supplier_orderui.UpdateStatusOrderStockUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.event.ActionListener;

public class OrderMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton NewButton, UpdateButton, RefoundButton, FindButton, StatusButton, PaymentButton, backButton;
    private int gobackindicator = 0;
    public OrderMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zaporders.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 500, 400);
        add(logoImg);

        NewButton = new JButton("New Order");
        NewButton.setBounds(550, 110, 170, 60);
        NewButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        NewButton.setForeground(new Color(255,242,45));
        NewButton.setBackground(new Color(10,20,28));
        NewButton.addActionListener(this);
        add(NewButton);

        UpdateButton = new JButton("Delete");
        UpdateButton.setBounds(550, 190, 170, 60);
        UpdateButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        UpdateButton.setForeground(new Color(255,242,45));
        UpdateButton.setBackground(new Color(10,20,28));
        UpdateButton.addActionListener(this);
        add(UpdateButton);

        RefoundButton = new JButton("Refund");
        RefoundButton.setBounds(550, 270, 170, 60);
        RefoundButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        RefoundButton.setForeground(new Color(255,242,45));
        RefoundButton.setBackground(new Color(10,20,28));
        RefoundButton.addActionListener(this);
        add(RefoundButton);

        FindButton = new JButton("Find");
        FindButton.setBounds(750, 110, 170, 60);
        FindButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        FindButton.setForeground(new Color(255,242,45));
        FindButton.setBackground(new Color(10,20,28));
        FindButton.addActionListener(this);
        // FindButton.setVisible(false);
        add(FindButton);

        StatusButton = new JButton("Status");
        StatusButton.setBounds(750, 190, 170, 60);
        StatusButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        StatusButton.setForeground(new Color(255,242,45));
        StatusButton.setBackground(new Color(10,20,28));
        StatusButton.addActionListener(this);
        add(StatusButton);

        PaymentButton = new JButton("Payment");
        PaymentButton.setBounds(750, 270, 170, 60);
        PaymentButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        PaymentButton.setForeground(new Color(255,242,45));
        PaymentButton.setBackground(new Color(10,20,28));
        PaymentButton.addActionListener(this);
        add(PaymentButton);
        
        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

    }
    public void startOrderMenu(int number) {
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.gobackindicator=number;
        orderMenu.setBounds(0, 0, 1000, 600);
        orderMenu.setVisible(true);
        orderMenu.setResizable(false);
        orderMenu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==NewButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        NewSupplierOrderUI orderUI = new NewSupplierOrderUI();
                        orderUI.startNewSupplierOrder(gobackindicator);
                    });
                });
        }
        
        if(e.getSource()==UpdateButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        DeleteSupplierOrderUI DeleteSupplierOrder = new DeleteSupplierOrderUI();
                        DeleteSupplierOrder.startDeleteSupplierOrder(gobackindicator);
                    });
                });
        }

        if(e.getSource()==RefoundButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        UpdateStatusOrderStockUI order_phoneui = new UpdateStatusOrderStockUI();
                        order_phoneui.startUpdateSupplierOrder(gobackindicator);
                    });
                });
        }

        if(e.getSource()==FindButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        FindSupplierOrderUI countryUI = new FindSupplierOrderUI();
                        countryUI.startFindSupplierOrder(gobackindicator);
                    });
                });
        }
        if(e.getSource()==StatusButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        StatusOrderUI statusOrderUI = new StatusOrderUI();
                        statusOrderUI.startStatusOrder(gobackindicator);
                    });
                });
        }
        if(e.getSource()==PaymentButton){
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        PaymentMethodUI paymentMethodUI = new PaymentMethodUI();
                        paymentMethodUI.startPaymentMethod(gobackindicator, 2);
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
