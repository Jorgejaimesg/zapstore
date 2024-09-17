package com.zapstore.customer.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;

public class CustomerRepository implements CustomerService{

        private Connection connection;

        public CustomerRepository() {
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
    public void createCustomer(Customer customer) {
            try {
            String query = "INSERT INTO customer (id, customer_type, id_type, first_name, last_name, email) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer.getId());
            ps.setInt(2, customer.getCustomer_type());
            ps.setInt(3, customer.getId_type());
            ps.setString(4, customer.getFirst_name());
            ps.setString(5, customer.getLast_name());
            ps.setString(6, customer.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String query = "UPDATE customer SET customer_type = ?, id_type = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customer.getCustomer_type());
            ps.setInt(2, customer.getId_type());
            ps.setString(3, customer.getFirst_name());
            ps.setString(4, customer.getLast_name());
            ps.setString(5, customer.getEmail());
            ps.setString(6, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void deleteCustomer(String customerId) {
        String query = "DELETE FROM customer WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    
    }

    @Override
    public Optional<Customer> findCustomerById(String customerId) {
        String query = "SELECT id, customer_type, id_type, first_name, last_name, email FROM customer WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer(
                        rs.getString("id"),
                        rs.getInt("customer_type"),
                        rs.getInt("id_type"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                        return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty(); 
    
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT id, customer_type, id_type, first_name, last_name, email FROM customer";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer(
                        rs.getString("id"),
                        rs.getInt("customer_type"),
                        rs.getInt("id_type"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                        customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;  
    }

}
