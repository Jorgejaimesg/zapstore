package com.zapstore.order_detail.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.domain.service.OrderDetailService;

public class OrderDetailRepository implements OrderDetailService{

        private Connection connection;

        public OrderDetailRepository() {
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
    public void createOrderDetail(OrderDetail order_detail) {
            try {
            String query = "INSERT INTO order_detail (product_id, quantity, purchase_price, order_id, supplier_id) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, order_detail.getProduct_id());
            ps.setInt(2, order_detail.getQuantity());
            ps.setFloat(3, order_detail.getPurchase_price());
            ps.setInt(4, order_detail.getOrder_id());
            ps.setString(5, order_detail.getSupplier_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderDetail(OrderDetail order_detail) {
        String query = "UPDATE order_detail SET product_id=?, quantity=?, purchase_price=?, order_id=?, sub_total=?, supplier_id=? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, order_detail.getProduct_id());
            ps.setInt(2, order_detail.getQuantity());
            ps.setFloat(3, order_detail.getPurchase_price());
            ps.setInt(4, order_detail.getOrder_id());
            ps.setFloat(5, order_detail.getSub_total());
            ps.setString(6, order_detail.getSupplier_id());
            ps.setInt(7, order_detail.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteOrderDetail(int order_detailId) {
        String query = "DELETE FROM order_detail WHERE order_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, order_detailId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<OrderDetail> findOrderDetailById(int order_detailId) {
        String query = "SELECT id, product_id, quantity, purchase_price, order_id, sub_total, supplier_id FROM order_detail WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, order_detailId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail order_detail = new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("order_id"),
                        rs.getFloat("purchase_price"),
                        rs.getFloat("sub_total"),
                        rs.getString("supplier_id"));
                        return Optional.of(order_detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<OrderDetail> findAllOrderDetail() {
        List<OrderDetail> order_details = new ArrayList<>();
        String query = "SELECT id, product_id, quantity, purchase_price, order_id, sub_total, supplier_id  FROM order_detail";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail order_detail = new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("order_id"),
                        rs.getFloat("purchase_price"),
                        rs.getFloat("sub_total"),
                        rs.getString("supplier_id"));
                        order_details.add(order_detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_details;  
    }

    @Override
    public List<OrderDetail> findOrderDetailBySale(int SaleId) {
        List<OrderDetail> order_details = new ArrayList<>();
        String query = "SELECT id, product_id, quantity, purchase_price, order_id, sub_total, supplier_id FROM order_detail WHERE order_id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SaleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail order_detail = new OrderDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("order_id"),
                        rs.getFloat("purchase_price"),
                        rs.getFloat("sub_total"),
                        rs.getString("supplier_id"));
                        order_details.add(order_detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order_details;  
    }

}
