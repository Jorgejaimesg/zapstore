package com.zapstore.status_order.infrastructure;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class StatusOrderRepository implements StatusOrderService {
    private Connection connection;

        public StatusOrderRepository() {
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
    public void createStatusOrder(StatusOrder status_order) {
            try {
            String query = "INSERT INTO status_order (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, status_order.getId());
            ps.setString(2, status_order.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StatusOrder deleteStatusOrder(String Name) {
        String query = "DELETE FROM status_order WHERE name = ?";
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
    public Optional<StatusOrder> findStatusOrderByName(String Name) {
        String query = "SELECT id, name FROM status_order WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StatusOrder status_order = new StatusOrder(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(status_order);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<StatusOrder> findStatusOrderById(int codeStatusOrder) {
        String query = "SELECT id, name FROM status_order WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeStatusOrder);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    StatusOrder status_order = new StatusOrder(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(status_order);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<StatusOrder> findAllStatusOrder() {
                List<StatusOrder> countries = new ArrayList<>();
        String query = "SELECT id, name FROM status_order";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StatusOrder status_order = new StatusOrder(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(status_order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateStatusOrder(StatusOrder status_order) {
        String query = "UPDATE status_order SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, status_order.getName());
            ps.setInt(2, status_order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
