package com.zapstore.customer_phone.infrastructure;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CustomerPhoneRepository implements CustomerPhoneService {
    private Connection connection;

        public CustomerPhoneRepository() {
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
    public void createCustomerPhone(CustomerPhone customer_phone) {
            try {
            String query = "INSERT INTO customer_phone (customer_id, phone_number) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customer_phone.getCustomer_id());
            ps.setLong(2,customer_phone.getPhone_number());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerPhone deleteCustomerPhone(int id) {
        String query = "DELETE FROM customer_phone WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerPhone> findCustomerPhoneByCustomer(String customerId) {
        String query = "SELECT id, customer_id, phone_number FROM customer_phone WHERE customer_id = ?";
        List<CustomerPhone>phones = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerPhone customer_phone = new CustomerPhone(
                            rs.getInt("id"),
                            rs.getString("customer_id"),
                            rs.getLong("phone_number"));
                            phones.add(customer_phone);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public Optional<CustomerPhone> findCustomerPhoneById(int codeCustomerPhone) {
        String query = "SELECT id, customer_id, phone_number FROM customer_phone WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeCustomerPhone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CustomerPhone customer_phone = new CustomerPhone(
                        rs.getInt("id"),
                        rs.getString("customer_id"),
                        rs.getLong("phone_number"));
                    return Optional.of(customer_phone);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<CustomerPhone> findAllCustomerPhone() {
        List<CustomerPhone>phones = new ArrayList<>();
        String query = "SELECT id, customer_id, phone_number FROM customer_phone";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerPhone customer_phone = new CustomerPhone(
                        rs.getInt("id"),
                        rs.getString("customer_id"),
                        rs.getLong("phone_number"));
                        phones.add(customer_phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public void updateCustomerPhone(CustomerPhone customer_phone) {
        String query = "UPDATE customer_phone SET phone_number = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, customer_phone.getPhone_number());
            ps.setInt(2, customer_phone.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
