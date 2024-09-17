package com.zapstore.product.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.product.domain.entity.Product;
import com.zapstore.product.domain.service.ProductService;

public class ProductRepository implements ProductService{

        private Connection connection;

        public ProductRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("zapstore.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createProduct(Product product) {
            try {
            String query = "INSERT INTO product (category_id, reference, stock, stock_min, name, brand_id, sale_price) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, product.getCategory_id());
            ps.setString(2, product.getReference());
            ps.setInt(3, product.getStock());
            ps.setInt(4, product.getStock_min());
            ps.setString(5, product.getName());
            ps.setInt(6, product.getBrand_id());
            ps.setFloat(7,product.getSale_price());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        String query = "UPDATE product SET category_id = ?, reference = ?, stock = ?, stock_min = ?, name = ?, brand_id = ?, sale_price = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, product.getCategory_id());
            ps.setString(2, product.getReference());
            ps.setInt(3, product.getStock());
            ps.setInt(4, product.getStock_min());
            ps.setString(5, product.getName());
            ps.setInt(6, product.getBrand_id());
            ps.setFloat(7,product.getSale_price());
            ps.setInt(8, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteProduct(String productId) {
        String query = "DELETE FROM product WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<Product> findProductById(int productId) {
        String query = "SELECT id, category_id, reference, stock, stock_min, name, brand_id, sale_price FROM product WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("reference"),
                        rs.getInt("stock"),
                        rs.getInt("stock_min"),
                        rs.getString("name"),
                        rs.getInt("brand_id"),
                        rs.getFloat("sale_price"));
                        return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT id, category_id, reference, stock, stock_min, name, brand_id, sale_price FROM product";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getInt("category_id"),
                        rs.getString("reference"),
                        rs.getInt("stock"),
                        rs.getInt("stock_min"),
                        rs.getString("name"),
                        rs.getInt("brand_id"),
                        rs.getFloat("sale_price"));
                        products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;  
    }

}
