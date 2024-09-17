package com.zapstore.supplier.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.supplier.domain.entity.Supplier;
import com.zapstore.supplier.domain.service.SupplierService;

public class SupplierRepository implements SupplierService{

        private Connection connection;

        public SupplierRepository() {
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
    public void createSupplier(Supplier supplier) {
            try {
            String query = "INSERT INTO supplier (nit, supplier_type, name, email, city_id) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplier.getNit());
            ps.setInt(2, supplier.getSupplier_type());
            ps.setString(3, supplier.getName());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getCity_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        String query = "UPDATE supplier SET supplier_type = ?, name = ?, email = ?, city_id = ? WHERE nit = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, supplier.getSupplier_type());
            ps.setString(2, supplier.getName());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getCity_id());
            ps.setString(5, supplier.getNit());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteSupplier(String supplierId) {
        String query = "DELETE FROM supplier WHERE nit = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplierId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<Supplier> findSupplierById(String supplierId) {
        String query = "SELECT nit, supplier_type, name, email, city_id FROM supplier WHERE nit = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Supplier supplier = new Supplier(
                        rs.getString("nit"),
                        rs.getInt("supplier_type"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city_id"));
                        return Optional.of(supplier);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<Supplier> findAllSupplier() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT nit, supplier_type, name, email, city_id FROM supplier";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Supplier supplier = new Supplier(
                        rs.getString("nit"),
                        rs.getInt("supplier_type"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city_id"));
                        suppliers.add(supplier);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;  
    }

}
