package com.zapstore.sale_detail.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class SaleDetailRepository implements SaleDetailService{

        private Connection connection;

        public SaleDetailRepository() {
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
    public void createSaleDetail(SaleDetail sale_detail) {
            try {
            String query = "INSERT INTO sale_detail (product_id, quantity, sale_price, sale_id) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, sale_detail.getProduct_id());
            ps.setInt(2, sale_detail.getQuantity());
            ps.setFloat(3, sale_detail.getSale_price());
            ps.setInt(4, sale_detail.getSale_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSaleDetail(SaleDetail sale_detail) {
        String query = "UPDATE sale_detail SET product_id=?, quantity=?, sale_price=?, sale_id=?, sub_total=? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, sale_detail.getProduct_id());
            ps.setInt(2, sale_detail.getQuantity());
            ps.setFloat(3, sale_detail.getSale_price());
            ps.setInt(4, sale_detail.getSale_id());
            ps.setFloat(5, sale_detail.getSub_total());
            ps.setInt(6, sale_detail.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteSaleDetail(int sale_detailId) {
        String query = "DELETE FROM sale_detail WHERE sale_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, sale_detailId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<SaleDetail> findSaleDetailById(int sale_detailId) {
        String query = "SELECT id, product_id, quantity, sale_price, sale_id, sub_total FROM sale_detail WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, sale_detailId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SaleDetail sale_detail = new SaleDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("sale_id"),
                        rs.getFloat("sale_price"),
                        rs.getFloat("sub_total"));
                        return Optional.of(sale_detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<SaleDetail> findAllSaleDetail() {
        List<SaleDetail> sale_details = new ArrayList<>();
        String query = "SELECT id, product_id, quantity, sale_price, sale_id, sub_total FROM sale_detail";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SaleDetail sale_detail = new SaleDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("sale_id"),
                        rs.getFloat("sale_price"),
                        rs.getFloat("sub_total"));
                        sale_details.add(sale_detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale_details;  
    }

    @Override
    public List<SaleDetail> findSaleDetailBySale(int SaleId) {
        List<SaleDetail> sale_details = new ArrayList<>();
        String query = "SELECT id, product_id, quantity, sale_price, sale_id, sub_total FROM sale_detail WHERE sale_id =?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SaleId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SaleDetail sale_detail = new SaleDetail(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("sale_id"),
                        rs.getFloat("sale_price"),
                        rs.getFloat("sub_total"));
                        sale_details.add(sale_detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sale_details;  
    }

}
