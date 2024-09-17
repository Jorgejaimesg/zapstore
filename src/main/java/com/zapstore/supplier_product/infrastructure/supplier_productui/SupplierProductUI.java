package com.zapstore.supplier_product.infrastructure.supplier_productui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.zapstore.menu.infraestructure.ProductMenu;

public class SupplierProductUI extends JFrame implements ActionListener{
    private JLabel title, title1, logoImg;
    private JButton addButton, findProductButton, findSupplierButton, backButton;
    private int gobackindicator;
    public SupplierProductUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SupplierProducts");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zap_logo.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Supplier's");
        title.setBounds(520, -50, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 70));
        title.setForeground(new Color(255,242,45));
        add(title);

        title1 = new JLabel("Products");
        title1.setBounds(550, 10, 500, 300);
        title1.setFont(new Font("Andale Mono", Font.BOLD, 70));
        title1.setForeground(new Color(255,242,45));
        add(title1);
        
        addButton = new JButton("Add");
        addButton.setBounds(520, 255, 330, 60);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("🔙");
        backButton.setBounds(0, 0, 60, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        findProductButton = new JButton("Find By product");
        findProductButton.setBounds(520, 335, 330, 60);
        findProductButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        findProductButton.setForeground(new Color(255,242,45));
        findProductButton.setBackground(new Color(10,20,28));
        findProductButton.addActionListener(this);
        add(findProductButton);

        findSupplierButton = new JButton("Find By supplier");
        findSupplierButton.setBounds(520, 420, 330, 60);
        findSupplierButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        findSupplierButton.setForeground(new Color(255,242,45));
        findSupplierButton.setBackground(new Color(10,20,28));
        findSupplierButton.addActionListener(this);
        add(findSupplierButton);

    }
    public void startSupplierProduct(int gobackindicator) {
        SupplierProductUI supplier_productUI = new SupplierProductUI();
        supplier_productUI.setBounds(0, 0, 1000, 600);
        supplier_productUI.setVisible(true);
        supplier_productUI.setResizable(false);
        supplier_productUI.setLocationRelativeTo(null);
        supplier_productUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddSupplierProductUI addSupplierProductUI = new AddSupplierProductUI();
            addSupplierProductUI.startAddSupplierProduct(gobackindicator);
        }

        if(e.getSource()==findSupplierButton){
            this.setVisible(false);
            FindBySupplierUI FindBySupplier = new FindBySupplierUI();
            FindBySupplier.startfindBySupplier(gobackindicator);
        }
        

        if(e.getSource()==findProductButton){
            this.setVisible(false);
            FindByProductUI FindByProduct = new FindByProductUI();
            FindByProduct.startfindByproduct(gobackindicator);
        }


        if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                    SwingUtilities.invokeLater(() -> {
                    ProductMenu ProductMenu = new ProductMenu();
                    ProductMenu.startProductMenu(gobackindicator);
                    });

                // Abre la nueva ventana 
            });
        }

        }
    }

