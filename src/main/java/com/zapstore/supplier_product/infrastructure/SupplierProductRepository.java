package com.zapstore.supplier_product.infrastructure;

import com.zapstore.supplier_product.domain.entity.SupplierProduct;
import com.zapstore.supplier_product.domain.service.SupplierProductService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SupplierProductRepository implements SupplierProductService {
    private Connection connection;

        public SupplierProductRepository() {
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
    public void createSupplierProduct(SupplierProduct supplier_product) {
            try {
            String query = "INSERT INTO supplier_product (id_product, id_supplier) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, supplier_product.getId());
            ps.setString(2, supplier_product.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SupplierProduct deleteSupplierProduct(String Name, int productId) {
        String query = "DELETE FROM supplier_product WHERE (id_supplier = ? && id_product = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SupplierProduct> findSupplierProductByName(String Name) {
        String query = "SELECT id_product, id_supplier FROM supplier_product WHERE id_supplier = ?";
        List<SupplierProduct> suppliers = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierProduct supplier_product = new SupplierProduct(
                            rs.getInt("id_product"),
                            rs.getString("id_supplier"));
                            suppliers.add(supplier_product);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public List<SupplierProduct> findSupplierProductById(int id_product) {
        List<SupplierProduct> suppliers = new ArrayList<>();
        String query = "SELECT id_product, id_supplier FROM supplier_product WHERE id_product = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id_product);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierProduct supplier_product = new SupplierProduct(
                            rs.getInt("id_product"),
                            rs.getString("id_supplier"));
                            suppliers.add(supplier_product);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;

    }

    @Override
    public List<SupplierProduct> findAllSupplierProduct() {
                List<SupplierProduct> countries = new ArrayList<>();
        String query = "SELECT id_product, id_supplier FROM supplier_product";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierProduct supplier_product = new SupplierProduct(
                        rs.getInt("id_product"),
                        rs.getString("id_supplier"));
                        countries.add(supplier_product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateSupplierProduct(SupplierProduct supplier_product) {
        String query = "UPDATE supplier_product SET id_supplier = ? WHERE id_product = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplier_product.getName());
            ps.setInt(2, supplier_product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
