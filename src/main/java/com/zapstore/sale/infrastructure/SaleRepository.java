package com.zapstore.sale.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;

public class SaleRepository implements SaleService{

        private Connection connection;

        public SaleRepository() {
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
    public void createSale(Sale sale) {
            try {
            String query = "INSERT INTO sale (total_price, discount_amount, discount_percent, customer_id, payment_method_id, status_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, sale.getTotal_price());
            ps.setFloat(2, sale.getDiscount_amount());
            ps.setFloat(3, sale.getDiscount_percent());
            ps.setString(4, sale.getCustomer_id());
            ps.setInt(5, sale.getPayment_method_id());
            ps.setInt(6, sale.getStatus_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSale(Sale sale) {
        String query = "UPDATE sale SET total_price = ?, discount_amount = ?, discount_percent = ?, customer_id = ?, payment_method_id = ?, status_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, sale.getTotal_price());
            ps.setFloat(2, sale.getDiscount_amount());
            ps.setFloat(3, sale.getDiscount_percent());
            ps.setString(4, sale.getCustomer_id());
            ps.setInt(5, sale.getPayment_method_id());
            ps.setInt(6, sale.getStatus_id());
            ps.setInt(7, sale.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteSale(int saleId) {
        String query = "DELETE FROM sale WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, saleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<Sale> findSaleById(int saleId) {
        String query = "SELECT id, total_price, discount_amount, discount_percent, customer_id, payment_method_id, status_id, date FROM sale WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, saleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale(
                        rs.getInt("id"),
                        rs.getFloat("total_price"),
                        rs.getFloat("discount_amount"),
                        rs.getFloat("discount_percent"),
                        rs.getString("customer_id"),
                        rs.getInt("payment_method_id"),
                        rs.getInt("status_id"),
                        rs.getString("date"));
                        return Optional.of(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<Sale> findAllSale() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT  id, total_price, discount_amount, discount_percent, customer_id, payment_method_id, status_id, date FROM sale";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale(
                        rs.getInt("id"),
                        rs.getFloat("total_price"),
                        rs.getFloat("discount_amount"),
                        rs.getFloat("discount_percent"),
                        rs.getString("customer_id"),
                        rs.getInt("payment_method_id"),
                        rs.getInt("status_id"),
                        rs.getString("date"));
                        sales.add(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;  
    }

    @Override
    public Optional<Sale> findLastSale() {
    String query = "SELECT id, total_price, discount_amount, discount_percent, customer_id, payment_method_id, status_id, date FROM sale ORDER BY date DESC LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale(
                        rs.getInt("id"),
                        rs.getFloat("total_price"),
                        rs.getFloat("discount_amount"),
                        rs.getFloat("discount_percent"),
                        rs.getString("customer_id"),
                        rs.getInt("payment_method_id"),
                        rs.getInt("status_id"),
                        rs.getString("date"));
                        return Optional.of(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    }

}
