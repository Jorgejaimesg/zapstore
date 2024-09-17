package com.zapstore.product.infrastructure.productui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zapstore.product.application.FindProductByIdUseCase;
import com.zapstore.product.application.UpdateProductUseCase;
import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;
import com.zapstore.product.infrastructure.ProductRepository;

import com.zapstore.brand.application.FindAllBrandUseCase;
import com.zapstore.brand.application.FindBrandByIdUseCase;
import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import com.zapstore.brand.infrastructure.BrandRepository;
import com.zapstore.category.application.FindAllCategoryUseCase;
import com.zapstore.category.application.FindCategoryByIdUseCase;
import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import com.zapstore.category.infrastructure.CategoryRepository;


import javax.swing.JOptionPane;

public class UpdateProductUI extends JFrame implements ActionListener {
    private JLabel logoImg, title, labelCategory, labelReference, labelStock, labelId, labelStockMin, labelName, labelBrand, labelSalePrice;
    private JComboBox<String> brandBox, categoryBox;
    private JTextField reference, stockMin, Name, salePrice, stock, idproduct;
    private JButton updateButton, findButton, backButton, resetButton;
    private int gobackindicator; 

    ProductService productService = new ProductRepository();
    FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productService);
    UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(productService);
    
    BrandService brandService = new BrandRepository();
    FindAllBrandUseCase findAllBrandUseCase = new FindAllBrandUseCase(brandService);
    List<Brand> AllBrand = findAllBrandUseCase.findAllBrand();
    FindBrandByIdUseCase findBrandByIdUseCase = new FindBrandByIdUseCase(brandService);
    
    CategoryService categoryService = new CategoryRepository();
    FindAllCategoryUseCase findAllCategoryUseCase = new FindAllCategoryUseCase(categoryService);
    List<Category> AllCategory = findAllCategoryUseCase.findAllCategory();
    FindCategoryByIdUseCase findCategoryByIdUseCase = new FindCategoryByIdUseCase(categoryService);

    public UpdateProductUI(){
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

        labelId = new JLabel("Id: ");
        labelId.setBounds(110, 130, 150, 30);
        labelId.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelId.setForeground(new Color(255,242,45));
        add(labelId);

        idproduct = new JTextField();
        idproduct.setBounds(170, 130, 230, 30);
        idproduct.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        idproduct.setForeground(new Color(255,242,45));
        idproduct.setBackground(new Color(10,20,28));
        add(idproduct);

        labelName = new JLabel("Name: ");
        labelName.setBounds(80, 170, 150, 30);
        labelName.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelName.setForeground(new Color(255,242,45));
        add(labelName);

        Name = new JTextField();
        Name.setBounds(170, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        Name.setVisible(false);
        add(Name);

        labelCategory = new JLabel("Category: ");
        labelCategory.setBounds(60, 210, 150, 30);
        labelCategory.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCategory.setForeground(new Color(255,242,45));
        add(labelCategory);


        categoryBox = new JComboBox<String>();
        categoryBox.setBounds(170, 210, 255, 30);
        categoryBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        categoryBox.setForeground(new Color(255,242,45));
        categoryBox.setBackground(new Color(10,20,28));
        categoryBox.setVisible(false);
        add(categoryBox);
        for(Category category : AllCategory){
            categoryBox.addItem(category.getId()+". "+category.getName());
        }

        labelBrand = new JLabel("Brand: ");
        labelBrand.setBounds(85, 250, 150, 30);
        labelBrand.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelBrand.setForeground(new Color(255,242,45));
        add(labelBrand);

        brandBox = new JComboBox<String>();
        brandBox.setBounds(170, 250, 255, 30);
        brandBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        brandBox.setForeground(new Color(255,242,45));
        brandBox.setBackground(new Color(10,20,28));
        brandBox.setVisible(false);
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
        reference.setVisible(false);
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
        salePrice.setVisible(false);
        add(salePrice);

        
        labelStock = new JLabel("Stock: ");
        labelStock.setBounds(500, 210, 150, 30);
        labelStock.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelStock.setForeground(new Color(255,242,45));
        add(labelStock);

        stock = new JTextField();
        stock.setBounds(620, 210, 255, 30);
        stock.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        stock.setForeground(new Color(255,242,45));
        stock.setBackground(new Color(10,20,28));
        stock.setVisible(false);
        add(stock);

        labelStockMin = new JLabel("Stock min : ");
        labelStockMin.setBounds(500, 250, 150, 30);
        labelStockMin.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelStockMin.setForeground(new Color(255,242,45));
        add(labelStockMin);

        stockMin = new JTextField();
        stockMin.setBounds(620, 250, 255, 30);
        stockMin.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        stockMin.setForeground(new Color(255,242,45));
        stockMin.setBackground(new Color(10,20,28));
        stockMin.setVisible(false);
        add(stockMin);

        backButton = new JButton("Go Back");
        backButton.setBounds(580, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(440, 300, 120, 30);
        updateButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        updateButton.setForeground(new Color(255,242,45));
        updateButton.setBackground(new Color(10,20,28));
        updateButton.addActionListener(this);
        add(updateButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(300, 300, 120, 30);
        resetButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        resetButton.setForeground(new Color(255,242,45));
        resetButton.setBackground(new Color(10,20,28));
        resetButton.addActionListener(this);
        add(resetButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);


    }

    public void startUpdateProduct(int gobackindicator) {
        UpdateProductUI updateUI = new UpdateProductUI();
        updateUI.setBounds(0, 0, 1000, 400);
        updateUI.setVisible(true);
        updateUI.setResizable(false);
        updateUI.setLocationRelativeTo(null);
        updateUI.gobackindicator = gobackindicator;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==findButton){
            try {
                int productId = Integer.parseInt(idproduct.getText().trim());
                if (productId>0){
                    Optional<Product> product = findProductByIdUseCase.findProductById(productId);
                    if (product.isPresent()){
                        idproduct.setEnabled(false);
                        findButton.setEnabled(false);
                        Product foundProduct = product.get();
                        Name.setVisible(true);
                        Name.setText(foundProduct.getName());
                        reference.setVisible(true);
                        reference.setText(foundProduct.getReference());
                        salePrice.setVisible(true);
                        salePrice.setText(foundProduct.getSale_price().toString());
                        stock.setVisible(true);
                        stock.setText(Integer.toString(foundProduct.getStock()));
                        stockMin.setVisible(true);
                        stockMin.setText(Integer.toString(foundProduct.getStock_min()));
                        Optional<Brand> foundBrand = findBrandByIdUseCase.findBrandById(foundProduct.getBrand_id());
                        Optional<Category> foundCategory = findCategoryByIdUseCase.findCategoryById(foundProduct.getCategory_id());
                        brandBox.setVisible(true);
                        brandBox.setSelectedItem(foundBrand.get().getId()+". "+foundBrand.get().getName());
                        categoryBox.setVisible(true);
                        categoryBox.setSelectedItem(foundCategory.get().getId()+". "+foundCategory.get().getName());

                        
                    } else {
                        JOptionPane.showMessageDialog(this, "The Product doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Name.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource()==updateButton){
            
            try {
                int productID = Integer.parseInt(idproduct.getText().trim());
                String productName = Name.getText().trim().toUpperCase();
                String productReference = reference.getText().trim();
                Float productSalePrice = Float.valueOf(salePrice.getText().trim());
                int productStockMin = Integer.parseInt(stockMin.getText().trim());
                int productBrand = Integer.parseInt(TextBeforeDot(brandBox.getSelectedItem().toString().trim()));
                int productCategory = Integer.parseInt(TextBeforeDot(categoryBox.getSelectedItem().toString().trim()));
                int productStock = Integer.parseInt(stock.getText().trim());
                Product updatedProduct = new Product();
                                
                updatedProduct.setId(productID);
                updatedProduct.setName(productName);
                updatedProduct.setReference(productReference);
                updatedProduct.setSale_price(productSalePrice);
                updatedProduct.setStock_min(productStockMin);
                updatedProduct.setBrand_id(productBrand);
                updatedProduct.setCategory_id(productCategory);
                updatedProduct.setStock(productStock);
            
                updateProductUseCase.execute(updatedProduct);
                JOptionPane.showMessageDialog(this, "Updated succesfully", "succes", JOptionPane.INFORMATION_MESSAGE);
            
            }catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Error to update", "Error", JOptionPane.ERROR_MESSAGE);

            }
            resetButton.doClick();

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
        if(e.getSource()==resetButton){
            findButton.setEnabled(true);
            idproduct.setEnabled(true);
            idproduct.setText("");
            Name.setVisible(false);
            Name.setText("");
            reference.setVisible(false);
            reference.setText("");
            salePrice.setVisible(false);
            salePrice.setText("");
            stock.setVisible(false);
            stock.setText("");
            stockMin.setVisible(false);
            stockMin.setText("");
            brandBox.setVisible(false);
            categoryBox.setVisible(false);
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
