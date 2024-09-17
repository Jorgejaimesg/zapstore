package com.zapstore.status_sale.infrastructure;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Optional;
import java.util.Properties;

public class StatusSaleRepository implements StatusSaleService {
    private Connection connection;

        public StatusSaleRepository() {
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
    public void createStatusSale(StatusSale status_sale) {
            try {
            String query = "INSERT INTO status_sale (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, status_sale.getId());
            ps.setString(2, status_sale.getDescriptionmode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StatusSale deleteStatusSale(String Name) {
        String query = "DELETE FROM status_sale WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<StatusSale> findStatusSaleByName(String Name) {
        String query = "SELECT id, name FROM status_sale WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StatusSale status_sale = new StatusSale(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(status_sale);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<StatusSale> findStatusSaleById(int codeStatusSale) {
        String query = "SELECT id, name FROM status_sale WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeStatusSale);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StatusSale status_sale = new StatusSale(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(status_sale);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<StatusSale> findAllStatusSale() {
                List<StatusSale> countries = new ArrayList<>();
        String query = "SELECT id, name FROM status_sale";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StatusSale status_sale = new StatusSale(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(status_sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateStatusSale(StatusSale status_sale) {
        String query = "UPDATE status_sale SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status_sale.getDescriptionmode());
            ps.setInt(2, status_sale.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
