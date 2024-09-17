package com.zapstore.supplier_phone.infrastructure;

import com.zapstore.supplier_phone.domain.entity.SupplierPhone;
import com.zapstore.supplier_phone.domain.service.SupplierPhoneService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class SupplierPhoneRepository implements SupplierPhoneService {
    private Connection connection;

        public SupplierPhoneRepository() {
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
    public void createSupplierPhone(SupplierPhone supplier_phone) {
            try {
            String query = "INSERT INTO supplier_phone (supplier_id, phone_number) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplier_phone.getSupplier_id());
            ps.setLong(2,supplier_phone.getPhone_number());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SupplierPhone deleteSupplierPhone(int id) {
        String query = "DELETE FROM supplier_phone WHERE id = ?";
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
    public List<SupplierPhone> findSupplierPhoneBySupplier(String supplierId) {
        String query = "SELECT id, supplier_id, phone_number FROM supplier_phone WHERE supplier_id = ?";
        List<SupplierPhone>phones = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplierId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierPhone supplier_phone = new SupplierPhone(
                            rs.getInt("id"),
                            rs.getString("supplier_id"),
                            rs.getLong("phone_number"));
                            phones.add(supplier_phone);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public Optional<SupplierPhone> findSupplierPhoneById(int codeSupplierPhone) {
        String query = "SELECT id, supplier_id, phone_number FROM supplier_phone WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeSupplierPhone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SupplierPhone supplier_phone = new SupplierPhone(
                        rs.getInt("id"),
                        rs.getString("supplier_id"),
                        rs.getLong("phone_number"));
                    return Optional.of(supplier_phone);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<SupplierPhone> findAllSupplierPhone() {
        List<SupplierPhone>phones = new ArrayList<>();
        String query = "SELECT id, supplier_id, phone_number FROM supplier_phone";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierPhone supplier_phone = new SupplierPhone(
                        rs.getInt("id"),
                        rs.getString("supplier_id"),
                        rs.getLong("phone_number"));
                        phones.add(supplier_phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public void updateSupplierPhone(SupplierPhone supplier_phone) {
        String query = "UPDATE supplier_phone SET phone_number = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, supplier_phone.getPhone_number());
            ps.setInt(2, supplier_phone.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
