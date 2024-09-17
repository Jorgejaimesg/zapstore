package com.zapstore.product.infrastructure.productui;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

import com.zapstore.menu.infraestructure.ProductMenu;
import com.zapstore.product.application.FindAllProductUseCase;
import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;
import com.zapstore.product.infrastructure.ProductRepository;

public class ProductUI extends JFrame implements ActionListener{
    private JLabel title, logoImg;
    private JButton addButton, deleteButton, updateButton, findButton, allProductButton, backButton;
    private int gobackindicator;
    public ProductUI(){


        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Product");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/product.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(450, 500, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(10, 40, 500, 500);
        add(logoImg);

        title = new JLabel("Products");
        title.setBounds(480, 10, 500, 300);
        title.setFont(new Font("Andale Mono", Font.BOLD, 90));
        title.setForeground(new Color(255,242,45));
        add(title);
        
        addButton = new JButton("Add");
        addButton.setBounds(520, 255, 150, 60);
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

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(700, 255, 150, 60);
        deleteButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        deleteButton.setForeground(new Color(255,242,45));
        deleteButton.setBackground(new Color(10,20,28));
        deleteButton.addActionListener(this);
        add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(520, 335, 150, 60);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        findButton = new JButton("Find");
        findButton.setBounds(700, 335, 150, 60);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

        allProductButton = new JButton("All Products");
        allProductButton.setBounds(520, 415, 330, 60);
        allProductButton.setFont(new Font("Andale Mono", Font.PLAIN, 25));
        allProductButton.setForeground(new Color(255,242,45));
        allProductButton.setBackground(new Color(10,20,28));
        allProductButton.addActionListener(this);
        add(allProductButton);
    }
    public void startProduct(int gobackindicator) {
        ProductUI productUI = new ProductUI();
        productUI.setBounds(0, 0, 1000, 600);
        productUI.setVisible(true);
        productUI.setResizable(false);
        productUI.setLocationRelativeTo(null);
        productUI.gobackindicator = gobackindicator;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){
            this.setVisible(false);
            AddProductUI addProductUI = new AddProductUI();
            addProductUI.startAddProduct(gobackindicator);
        }
        
        if(e.getSource()==deleteButton){
            this.setVisible(false);
            DeleteProductUI deleteProductUI = new DeleteProductUI();
            deleteProductUI.startDeleteProduct(gobackindicator);
        }

        if(e.getSource()==findButton){
            this.setVisible(false);
            FindProductUI FindProductUI = new FindProductUI();
            FindProductUI.startFindProduct(gobackindicator);
        }
        

        if(e.getSource()==updateButton){
            this.setVisible(false);
            UpdateProductUI updateProductUI = new UpdateProductUI();
            updateProductUI.startUpdateProduct(gobackindicator);
        }

        if (e.getSource()==allProductButton){
                ProductService productService = new ProductRepository();
                FindAllProductUseCase findAllProductsUseCase = new FindAllProductUseCase(productService);
                List<Product> Products = findAllProductsUseCase.findAllProduct();
        
                String[] columns = {"id", "category_id", "reference", "stock", "stock_min", "name", "brand_id", "sale_price"};
                DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

                Products.forEach(product -> {
                    Object[] row = {product.getId(), product.getCategory_id(), product.getReference(), product.getStock(), product.getStock_min(), product.getName(), product.getBrand_id(), product.getSale_price()};
                    defaultTableModel.addRow(row);
                });

        JTable table = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(null, scrollPane, "Product List", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==backButton){
            // Oculta la ventana actual
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

