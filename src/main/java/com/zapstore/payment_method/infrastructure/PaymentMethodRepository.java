package com.zapstore.payment_method.infrastructure;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class PaymentMethodRepository implements PaymentMethodService {
    private Connection connection;

        public PaymentMethodRepository() {
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
    public void createPaymentMethod(PaymentMethod payment_method) {
            try {
            String query = "INSERT INTO payment_method (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, payment_method.getId());
            ps.setString(2, payment_method.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PaymentMethod deletePaymentMethod(String Name) {
        String query = "DELETE FROM payment_method WHERE name = ?";
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
    public Optional<PaymentMethod> findPaymentMethodByName(String Name) {
        String query = "SELECT id, name FROM payment_method WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PaymentMethod payment_method = new PaymentMethod(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(payment_method);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<PaymentMethod> findPaymentMethodById(int codePaymentMethod) {
        String query = "SELECT id, name FROM payment_method WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codePaymentMethod);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PaymentMethod payment_method = new PaymentMethod(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(payment_method);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<PaymentMethod> findAllPaymentMethod() {
                List<PaymentMethod> countries = new ArrayList<>();
        String query = "SELECT id, name FROM payment_method";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PaymentMethod payment_method = new PaymentMethod(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(payment_method);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updatePaymentMethod(PaymentMethod payment_method) {
        String query = "UPDATE payment_method SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, payment_method.getName());
            ps.setInt(2, payment_method.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
