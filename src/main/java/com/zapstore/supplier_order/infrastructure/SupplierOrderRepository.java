package com.zapstore.supplier_order.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;

public class SupplierOrderRepository implements SupplierOrderService{

        private Connection connection;

        public SupplierOrderRepository() {
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
    public void createSupplierOrder(SupplierOrder supplier_order) {
            try {
            String query = "INSERT INTO supplier_order (total_price, payment_method_id, status_id) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, supplier_order.getTotal_price());
            ps.setInt(2, supplier_order.getPayment_method_id());
            ps.setInt(3, supplier_order.getStatus_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSupplierOrder(SupplierOrder supplier_order) {
        String query = "UPDATE supplier_order SET total_price = ?, payment_method_id = ?, status_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, supplier_order.getTotal_price());
            ps.setInt(2, supplier_order.getPayment_method_id());
            ps.setInt(3, supplier_order.getStatus_id());
            ps.setInt(4, supplier_order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteSupplierOrder(int supplier_orderId) {
        String query = "DELETE FROM supplier_order WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, supplier_orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<SupplierOrder> findSupplierOrderById(int supplier_orderId) {
        String query = "SELECT id, total_price, payment_method_id, status_id, date FROM supplier_order WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, supplier_orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierOrder supplier_order = new SupplierOrder(
                        rs.getInt("id"),
                        rs.getFloat("total_price"),
                        rs.getString("date"),
                        rs.getInt("status_id"),
                        rs.getInt("payment_method_id"));
                        return Optional.of(supplier_order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<SupplierOrder> findAllSupplierOrder() {
        List<SupplierOrder> supplier_orders = new ArrayList<>();
        String query = "SELECT  id, total_price, payment_method_id, status_id, date FROM supplier_order";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierOrder supplier_order = new SupplierOrder(
                        rs.getInt("id"),
                        rs.getFloat("total_price"),
                        rs.getString("date"),
                        rs.getInt("status_id"),
                        rs.getInt("payment_method_id"));
                        supplier_orders.add(supplier_order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier_orders;  
    }

    @Override
    public Optional<SupplierOrder> findLastSupplierOrder() {
    String query = "SELECT id, total_price, payment_method_id, status_id, date FROM supplier_order ORDER BY date DESC LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierOrder supplier_order = new SupplierOrder(
                        rs.getInt("id"),
                        rs.getFloat("total_price"),
                        rs.getString("date"),
                        rs.getInt("status_id"),
                        rs.getInt("payment_method_id"));
                        return Optional.of(supplier_order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    }

}
