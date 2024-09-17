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
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.zapstore.supplier_product.application.CreateSupplierProductUseCase;
import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;
import com.zapstore.supplier_product.infrastructure.SupplierProductRepository;

public class AddSupplierProductUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelSupplier, labelProduct;
    private JButton addButton, backButton;
    private JTextField ProductID, supplierID;
    private int gobackindicator;
    public AddSupplierProductUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add SupplierProduct");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/zap_logo.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Assignment");
        title.setBounds(200, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelSupplier = new JLabel("Supplier id : ");
        labelSupplier.setBounds(95, 130, 150, 30);
        labelSupplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSupplier.setForeground(new Color(255,242,45));
        add(labelSupplier);

        supplierID = new JTextField();
        supplierID.setBounds(210, 130, 255, 30);
        supplierID.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        supplierID.setForeground(new Color(255,242,45));
        supplierID.setBackground(new Color(10,20,28));
        add(supplierID);

        labelProduct = new JLabel("Product Id : ");
        labelProduct.setBounds(95, 170, 150, 30);
        labelProduct.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelProduct.setForeground(new Color(255,242,45));
        add(labelProduct);

        ProductID = new JTextField();
        ProductID.setBounds(210, 170, 255, 30);
        ProductID.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        ProductID.setForeground(new Color(255,242,45));
        ProductID.setBackground(new Color(10,20,28));
        add(ProductID);


        addButton = new JButton("Add");
        addButton.setBounds(150, 210, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(300, 210, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddSupplierProduct(int gobackindicator) {
        AddSupplierProductUI addUI = new AddSupplierProductUI();
        addUI.setBounds(0, 0, 600, 300);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator=gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String newSupplierId = supplierID.getText().trim();
                int newProductId = Integer.parseInt(ProductID.getText().trim());
                if (newSupplierId.length()>0 && newProductId>0){
                SupplierProduct newSupplierProduct = new SupplierProduct();
                
                newSupplierProduct.setName(newSupplierId);
                newSupplierProduct.setId(newProductId);

                SupplierProductService supplier_productService = new SupplierProductRepository();
                CreateSupplierProductUseCase createSupplierProductUseCase = new CreateSupplierProductUseCase(supplier_productService);
                createSupplierProductUseCase.execute(newSupplierProduct);
                JOptionPane.showMessageDialog(this, "SupplierProduct added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                supplierID.setText("");
                ProductID.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    if(e.getSource()==backButton){
            SwingUtilities.invokeLater(() -> {
                setVisible(false);
                dispose(); // Asegúrate de liberar recursos
                    SwingUtilities.invokeLater(() -> {
                        SupplierProductUI saleMenu = new SupplierProductUI();
                        saleMenu.startSupplierProduct(gobackindicator);
                    });
                });
    }
    }

    }

