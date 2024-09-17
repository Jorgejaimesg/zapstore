package com.zapstore.menu.infraestructure;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.zapstore.brand.infrastructure.brandui.BrandUI;
import com.zapstore.category.infrastructure.categoryui.CategoryUI;
import com.zapstore.product.infrastructure.productui.ProductUI;
import com.zapstore.supplier_product.infrastructure.supplier_productui.SupplierProductUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.event.ActionListener;

public class ProductMenu extends JFrame implements ActionListener {
    private JLabel logoImg;
    private JButton ProductButton, CategoryButton, BrandButton, AssignementButton, backButton;
    private int gobackindicator = 0;
    public ProductMenu(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zapProducts.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(20, 40, 500, 400);
        add(logoImg);

        ProductButton = new JButton("Product");
        ProductButton.setBounds(550, 110, 170, 60);
        ProductButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        ProductButton.setForeground(new Color(255,242,45));
        ProductButton.setBackground(new Color(10,20,28));
        ProductButton.addActionListener(this);
        add(ProductButton);

        CategoryButton = new JButton("Category");
        CategoryButton.setBounds(550, 190, 170, 60);
        CategoryButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        CategoryButton.setForeground(new Color(255,242,45));
        CategoryButton.setBackground(new Color(10,20,28));
        CategoryButton.addActionListener(this);
        add(CategoryButton);

        BrandButton = new JButton("Brand");
        BrandButton.setBounds(550, 270, 170, 60);
        BrandButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        BrandButton.setForeground(new Color(255,242,45));
        BrandButton.setBackground(new Color(10,20,28));
        BrandButton.addActionListener(this);
        add(BrandButton);

        AssignementButton = new JButton("Assignment");
        AssignementButton.setBounds(550, 350, 170, 60);
        AssignementButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        AssignementButton.setForeground(new Color(255,242,45));
        AssignementButton.setBackground(new Color(10,20,28));
        AssignementButton.addActionListener(this);
        add(AssignementButton);
        
        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

    }
    public void startProductMenu(int number) {
        ProductMenu mainMenu = new ProductMenu();
        mainMenu.gobackindicator=number;
        mainMenu.setBounds(0, 0, 1000, 600);
        mainMenu.setVisible(true);
        mainMenu.setResizable(false);
        mainMenu.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ProductButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        ProductUI productUI = new ProductUI();
                        productUI.startProduct(gobackindicator);
                    });
                });
        }
        
        if(e.getSource()==CategoryButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        CategoryUI categoryUI = new CategoryUI();
                        categoryUI.startCategory(gobackindicator);
                    });
                });
        }

        if(e.getSource()==BrandButton){
                 // Oculta la ventana actual
                SwingUtilities.invokeLater(() -> {
                    setVisible(false);
                    dispose(); // Asegúrate de liberar recursos
    
                    // Abre la nueva ventana 
                    SwingUtilities.invokeLater(() -> {
                        BrandUI brandUI = new BrandUI();
                        brandUI.startBrand(gobackindicator);
                    });
                });
        }
        if(e.getSource()==AssignementButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos

                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    SupplierProductUI supplierProductui = new SupplierProductUI();
                    supplierProductui.startSupplierProduct(gobackindicator);
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
                }else if (gobackindicator==3) {
                    SwingUtilities.invokeLater(() -> {
                        StorageMenu storagemenu = new StorageMenu();
                        storagemenu.startStorageMenu();
                    });
                }
                // Abre la nueva ventana 
            });
        }

        }

    }