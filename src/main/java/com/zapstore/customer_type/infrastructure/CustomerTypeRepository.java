package com.zapstore.customer_type.infrastructure;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CustomerTypeRepository implements CustomerTypeService {
    private Connection connection;

        public CustomerTypeRepository() {
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
    public void createCustomerType(CustomerType customer_type) {
            try {
            String query = "INSERT INTO customer_type (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, customer_type.getId());
            ps.setString(2, customer_type.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerType deleteCustomerType(String Name) {
        String query = "DELETE FROM customer_type WHERE name = ?";
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
    public Optional<CustomerType> findCustomerTypeByName(String Name) {
        String query = "SELECT id, name FROM customer_type WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CustomerType customer_type = new CustomerType(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(customer_type);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<CustomerType> findCustomerTypeById(int codeCustomerType) {
        String query = "SELECT id, name FROM customer_type WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeCustomerType);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CustomerType customer_type = new CustomerType(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(customer_type);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<CustomerType> findAllCustomerType() {
                List<CustomerType> countries = new ArrayList<>();
        String query = "SELECT id, name FROM customer_type";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerType customer_type = new CustomerType(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(customer_type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateCustomerType(CustomerType customer_type) {
        String query = "UPDATE customer_type SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer_type.getName());
            ps.setInt(2, customer_type.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
