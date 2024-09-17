package com.zapstore.supplier_product.infrastructure.supplier_productui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.zapstore.supplier_product.application.DeleteSupplierProductUseCase;
import com.zapstore.supplier_product.application.FindSupplierProductByIdUseCase;
import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;
import com.zapstore.supplier_product.infrastructure.SupplierProductRepository;

public class FindByProductUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelSupplier, labelProduct;
    private JTextField ProductID;
    private JComboBox<String> supplierID;
    private JButton deleteButtton, backButton, FindButton;
    private int gobackindicator;

    SupplierProductService supplierProductService = new SupplierProductRepository();
    DeleteSupplierProductUseCase deleteSupplierProductUseCase = new DeleteSupplierProductUseCase(supplierProductService);
    FindSupplierProductByIdUseCase findSupplierProductByIdUseCase = new FindSupplierProductByIdUseCase(supplierProductService);
    public FindByProductUI(){

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
        labelSupplier.setBounds(95, 170, 150, 30);
        labelSupplier.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSupplier.setForeground(new Color(255,242,45));
        add(labelSupplier);

        supplierID = new JComboBox<String>();
        supplierID.setBounds(210, 170, 255, 30);
        supplierID.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        supplierID.setForeground(new Color(255,242,45));
        supplierID.setBackground(new Color(10,20,28));
        add(supplierID);

        labelProduct = new JLabel("Product Id : ");
        labelProduct.setBounds(95, 130, 150, 30);
        labelProduct.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelProduct.setForeground(new Color(255,242,45));
        add(labelProduct);

        ProductID = new JTextField();
        ProductID.setBounds(210, 130, 200, 30);
        ProductID.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        ProductID.setForeground(new Color(255,242,45));
        ProductID.setBackground(new Color(10,20,28));
        add(ProductID);


        deleteButtton = new JButton("Delete");
        deleteButtton.setBounds(150, 210, 120, 30);
        deleteButtton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        deleteButtton.setForeground(new Color(255,242,45));
        deleteButtton.setBackground(new Color(10,20,28));
        deleteButtton.addActionListener(this);
        add(deleteButtton);

        backButton = new JButton("Go Back");
        backButton.setBounds(300, 210, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        FindButton = new JButton("🔍");
        FindButton.setBounds(410, 130, 55, 30);
        FindButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        FindButton.setForeground(new Color(255,242,45));
        FindButton.setBackground(new Color(10,20,28));
        FindButton.addActionListener(this);
        add(FindButton);

        
    }

    public void startfindByproduct(int gobackindicator) {
        FindByProductUI addUI = new FindByProductUI();
        addUI.setBounds(0, 0, 600, 300);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator=gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deleteButtton){
            deleteSupplierProductUseCase.execute(supplierID.getSelectedItem().toString(), Integer.parseInt(ProductID.getText()));
            supplierID.removeAllItems();
            ProductID.setText("");
        }
        
        if(e.getSource()==FindButton){
        supplierID.removeAllItems();
        List<SupplierProduct> listsp = findSupplierProductByIdUseCase.findSupplierProductById(Integer.parseInt(ProductID.getText()));
        for (SupplierProduct supplierProduct : listsp){
            supplierID.addItem(supplierProduct.getName());
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

