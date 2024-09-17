package com.zapstore.product.infrastructure.productui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import com.zapstore.product.application.CreateProductUseCase;
import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;
import com.zapstore.product.infrastructure.ProductRepository;
import com.zapstore.brand.application.FindAllBrandUseCase;
import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import com.zapstore.brand.infrastructure.BrandRepository;
import com.zapstore.category.application.FindAllCategoryUseCase;
import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import com.zapstore.category.infrastructure.CategoryRepository;

import javax.swing.SwingUtilities;

public class AddProductUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelCategory, labelReference, labelStockMin, labelName, labelBrand, labelSalePrice;
    private JButton addButton, backButton;
    private JComboBox<String> brandBox, categoryBox;
    private JTextField reference, stockMin, Name, salePrice;
    private int gobackindicator;

    BrandService brandService = new BrandRepository();
    FindAllBrandUseCase findAllBrandUseCase = new FindAllBrandUseCase(brandService);
    List<Brand> AllBrand = findAllBrandUseCase.findAllBrand();

    CategoryService categoryService = new CategoryRepository();
    FindAllCategoryUseCase findAllCategoryUseCase = new FindAllCategoryUseCase(categoryService);
    List<Category> AllCategory = findAllCategoryUseCase.findAllCategory();

    ProductService productService = new ProductRepository();
    CreateProductUseCase createProductUseCase = new CreateProductUseCase(productService);

    public AddProductUI(){

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Add Product");
        getContentPane().setBackground(new Color(10,20,28));
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());

        ImageIcon imagenOriginal = new ImageIcon(getClass().getClassLoader().getResource("images/product.png"));
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon imagen = new ImageIcon(imagenRedimensionada);

        logoImg = new JLabel(imagen);
        logoImg.setBounds(80, 20, 90, 90);
        add(logoImg);

        title = new JLabel("Add Product");
        title.setBounds(400, 20, 400, 90);
        title.setFont(new Font("Andale Mono", Font.BOLD, 35));
        title.setForeground(new Color(255,242,45));
        add(title);

        labelName = new JLabel("Name: ");
        labelName.setBounds(80, 130, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 130, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);

        labelCategory = new JLabel("Category: ");
        labelCategory.setBounds(75, 170, 150, 30);
        labelCategory.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCategory.setForeground(new Color(255,242,45));
        add(labelCategory);


        categoryBox = new JComboBox<String>();
        categoryBox.setBounds(170, 170, 255, 30);
        categoryBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        categoryBox.setForeground(new Color(255,242,45));
        categoryBox.setBackground(new Color(10,20,28));
        add(categoryBox);
        for(Category category : AllCategory){
            categoryBox.addItem(category.getId()+". "+category.getName());
        }

        labelBrand = new JLabel("Brand: ");
        labelBrand.setBounds(85, 210, 150, 30);
        labelBrand.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelBrand.setForeground(new Color(255,242,45));
        add(labelBrand);

        brandBox = new JComboBox<String>();
        brandBox.setBounds(170, 210, 255, 30);
        brandBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        brandBox.setForeground(new Color(255,242,45));
        brandBox.setBackground(new Color(10,20,28));
        add(brandBox);
        for(Brand brand : AllBrand){
            brandBox.addItem(brand.getId()+". "+brand.getName());
        }

        labelReference = new JLabel("Reference: ");
        labelReference.setBounds(500, 130, 150, 30);
        labelReference.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelReference.setForeground(new Color(255,242,45));
        add(labelReference);

        reference = new JTextField();
        reference.setBounds(620, 130, 255, 30);
        reference.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        reference.setForeground(new Color(255,242,45));
        reference.setBackground(new Color(10,20,28));
        add(reference);

        labelSalePrice = new JLabel("Price: ");
        labelSalePrice.setBounds(500, 170, 150, 30);
        labelSalePrice.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelSalePrice.setForeground(new Color(255,242,45));
        add(labelSalePrice);

        salePrice = new JTextField();
        salePrice.setBounds(620, 170, 255, 30);
        salePrice.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        salePrice.setForeground(new Color(255,242,45));
        salePrice.setBackground(new Color(10,20,28));
        add(salePrice);

        labelStockMin = new JLabel("Stock min : ");
        labelStockMin.setBounds(500, 210, 150, 30);
        labelStockMin.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelStockMin.setForeground(new Color(255,242,45));
        add(labelStockMin);

        stockMin = new JTextField();
        stockMin.setBounds(620, 210, 255, 30);
        stockMin.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        stockMin.setForeground(new Color(255,242,45));
        stockMin.setBackground(new Color(10,20,28));
        add(stockMin);


        addButton = new JButton("Add");
        addButton.setBounds(400, 300, 120, 30);
        addButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        addButton.setForeground(new Color(255,242,45));
        addButton.setBackground(new Color(10,20,28));
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);
    }

    public void startAddProduct(int gobackindicator) {
        AddProductUI addUI = new AddProductUI();
        addUI.setBounds(0, 0, 1000, 400);
        addUI.setVisible(true);
        addUI.setResizable(false);
        addUI.setLocationRelativeTo(null);
        addUI.gobackindicator = gobackindicator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addButton){

            try {
                String productName = Name.getText().trim().toUpperCase();
                String productReference = reference.getText().trim();
                Float productSalePrice = Float.valueOf(salePrice.getText().trim());
                int productStockMin = Integer.parseInt(stockMin.getText().trim());
                int productBrand = Integer.parseInt(TextBeforeDot(brandBox.getSelectedItem().toString().trim()));
                int productCategory = Integer.parseInt(TextBeforeDot(categoryBox.getSelectedItem().toString().trim()));
                if (productName.length()>0){
                Product newProduct = new Product();
                
                newProduct.setName(productName);
                newProduct.setReference(productReference);
                newProduct.setSale_price(productSalePrice);
                newProduct.setStock_min(productStockMin);
                newProduct.setBrand_id(productBrand);
                newProduct.setCategory_id(productCategory);
                newProduct.setStock(0);
                
                ProductService productService = new ProductRepository();
                CreateProductUseCase createProductUseCase = new CreateProductUseCase(productService);
                createProductUseCase.execute(newProduct);
                JOptionPane.showMessageDialog(this, "Product added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                Name.setText("");
                reference.setText("");
                salePrice.setText("");
                stockMin.setText("");
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
    
                // Abre la nueva ventana 
                SwingUtilities.invokeLater(() -> {
                    ProductUI ProductUI = new ProductUI();
                    ProductUI.startProduct(gobackindicator);
                });
            });
    }
    }

    private String TextBeforeDot(String text) {
        // Buscar la posición del primer punto en la cadena
        int position = text.indexOf('.');
        if (position != -1) {
            return text.substring(0, position);
        } else {
            return text;
        }
    }

    }

