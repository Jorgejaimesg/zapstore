package com.zapstore.customer_address.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class CustomerAddressRepository implements CustomerAddressService {
        private Connection connection;

        public CustomerAddressRepository() {
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
    public void createCustomerAddress(CustomerAddress customer_address) {
        try {
            String query = "INSERT INTO customer_address (ZipCode, details, city_id, customer_id) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customer_address.getZipCode());
            ps.setString(2,customer_address.getDetails());
            ps.setString(3,customer_address.getCity_id());
            ps.setString(4,customer_address.getCustomer_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomerAddress(CustomerAddress customerAddress) {
        String query = "UPDATE customer_address SET ZipCode = ?, details = ?, city_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerAddress.getZipCode());
            ps.setString(2,customerAddress.getDetails());
            ps.setString(3,customerAddress.getCity_id());
            ps.setInt(4,customerAddress.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomerAddress(int customerAddressId) {
        String query = "DELETE FROM customer_address WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerAddressId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomerAddress> findCustomerAddressById(int customerAddressId) {
    String query = "SELECT id, ZipCode, details, city_id, customer_id FROM customer_address WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, customerAddressId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CustomerAddress customer_address = new CustomerAddress(
                        rs.getInt("id"),
                        rs.getInt("ZipCode"),
                        rs.getString("details"),
                        rs.getString("city_id"),
                        rs.getString("customer_id"));
                    return Optional.of(customer_address);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();    
    }

    @Override
    public List<CustomerAddress> findCustomerAddressByCustomer(String customerId) {
        String query = "SELECT id, ZipCode, details, city_id, customer_id FROM customer_address WHERE customer_id = ?";
        List<CustomerAddress>Address = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerAddress customer_address = new CustomerAddress(
                        rs.getInt("id"),
                        rs.getInt("ZipCode"),
                        rs.getString("details"),
                        rs.getString("city_id"),
                        rs.getString("customer_id"));
                        Address.add(customer_address);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Address;
    }

    @Override
    public List<CustomerAddress> findAllCustomerAddress() {
            List<CustomerAddress>Address = new ArrayList<>();
        String query = "SELECT id, ZipCode, details, city_id, customer_id FROM customer_address";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerAddress customer_address = new CustomerAddress(
                        rs.getInt("id"),
                        rs.getInt("ZipCode"),
                        rs.getString("details"),
                        rs.getString("city_id"),
                        rs.getString("customer_id"));
                        Address.add(customer_address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Address;
    }

}
