package com.zapstore.product.infrastructure.productui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import com.zapstore.product.application.FindProductByIdUseCase;
import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;
import com.zapstore.product.infrastructure.ProductRepository;
import com.zapstore.brand.application.FindBrandByIdUseCase;
import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import com.zapstore.brand.infrastructure.BrandRepository;
import com.zapstore.category.application.FindCategoryByIdUseCase;
import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import com.zapstore.category.infrastructure.CategoryRepository;

public class FindProductUI extends JFrame implements ActionListener{
    private JLabel logoImg, title, labelCategory, labelReference, labelStockMin, labelName, labelBrand, labelSalePrice,labelId,labelStock, stock, reference, stockMin, Name, salePrice, brandBox, categoryBox;
    private JTextField idproduct;
    private JButton findButton, backButton, newButton;
    private int gobackindicator;
    BrandService brandService = new BrandRepository();
    FindBrandByIdUseCase findBrandByIdUseCase = new FindBrandByIdUseCase(brandService);

    CategoryService categoryService = new CategoryRepository();
    FindCategoryByIdUseCase findCategoryByIdUseCase = new FindCategoryByIdUseCase(categoryService);

    ProductService productService = new ProductRepository();
    FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productService);

    public FindProductUI(){
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

        title = new JLabel("Find Product");
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

        Name = new JLabel();
        Name.setBounds(170, 170, 255, 30);
        Name.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        Name.setForeground(new Color(255,242,45));
        Name.setBackground(new Color(10,20,28));
        add(Name);

        labelCategory = new JLabel("Category: ");
        labelCategory.setBounds(60, 210, 150, 30);
        labelCategory.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelCategory.setForeground(new Color(255,242,45));
        add(labelCategory);


        categoryBox = new JLabel();
        categoryBox.setBounds(170, 210, 255, 30);
        categoryBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        categoryBox.setForeground(new Color(255,242,45));
        categoryBox.setBackground(new Color(10,20,28));
        add(categoryBox);

        labelBrand = new JLabel("Brand: ");
        labelBrand.setBounds(85, 250, 150, 30);
        labelBrand.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelBrand.setForeground(new Color(255,242,45));
        add(labelBrand);

        brandBox = new JLabel();
        brandBox.setBounds(170, 250, 255, 30);
        brandBox.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        brandBox.setForeground(new Color(255,242,45));
        brandBox.setBackground(new Color(10,20,28));
        add(brandBox);

        labelReference = new JLabel("Reference: ");
        labelReference.setBounds(500, 130, 150, 30);
        labelReference.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelReference.setForeground(new Color(255,242,45));
        add(labelReference);

        reference = new JLabel();
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

        salePrice = new JLabel();
        salePrice.setBounds(620, 170, 255, 30);
        salePrice.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        salePrice.setForeground(new Color(255,242,45));
        salePrice.setBackground(new Color(10,20,28));
        add(salePrice);

        labelStock = new JLabel("Stock: ");
        labelStock.setBounds(500, 210, 150, 30);
        labelStock.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelStock.setForeground(new Color(255,242,45));
        add(labelStock);

        stock = new JLabel();
        stock.setBounds(620, 210, 255, 30);
        stock.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        stock.setForeground(new Color(255,242,45));
        stock.setBackground(new Color(10,20,28));
        add(stock);
        
        labelStockMin = new JLabel("Stock Min: ");
        labelStockMin.setBounds(500, 250, 150, 30);
        labelStockMin.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        labelStockMin.setForeground(new Color(255,242,45));
        add(labelStockMin);

        stockMin = new JLabel();
        stockMin.setBounds(620, 250, 255, 30);
        stockMin.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        stockMin.setForeground(new Color(255,242,45));
        stockMin.setBackground(new Color(10,20,28));
        add(stockMin);

        backButton = new JButton("Go Back");
        backButton.setBounds(540, 300, 120, 30);
        backButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        backButton.setForeground(new Color(255,242,45));
        backButton.setBackground(new Color(10,20,28));
        backButton.addActionListener(this);
        add(backButton);

        newButton = new JButton("New");
        newButton.setBounds(400, 300, 120, 30);
        newButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        newButton.setForeground(new Color(255,242,45));
        newButton.setBackground(new Color(10,20,28));
        newButton.addActionListener(this);
        add(newButton);

        findButton = new JButton("🔍");
        findButton.setBounds(400, 130, 60, 30);
        findButton.setFont(new Font("Andale Mono", Font.PLAIN, 20));
        findButton.setForeground(new Color(255,242,45));
        findButton.setBackground(new Color(10,20,28));
        findButton.addActionListener(this);
        add(findButton);

    }

    public void startFindProduct(int gobackindicator) {
        FindProductUI findUI = new FindProductUI();
        findUI.setBounds(0, 0, 1000, 400);
        findUI.setVisible(true);
        findUI.setResizable(false);
        findUI.setLocationRelativeTo(null);
        findUI.gobackindicator = gobackindicator;
        
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
                        brandBox.setText(foundBrand.get().getId()+". "+foundBrand.get().getName());
                        categoryBox.setVisible(true);
                        categoryBox.setText(foundCategory.get().getId()+". "+foundCategory.get().getName());

                        
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

        if(e.getSource()==newButton){
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
            brandBox.setText("");
            categoryBox.setVisible(false);
            categoryBox.setText("");
        
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

    }

