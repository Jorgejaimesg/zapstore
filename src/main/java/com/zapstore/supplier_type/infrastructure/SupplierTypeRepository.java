package com.zapstore.supplier_type.infrastructure;

import com.zapstore.supplier_type.domain.entity.SupplierType;
import com.zapstore.supplier_type.domain.service.SupplierTypeService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class SupplierTypeRepository implements SupplierTypeService {
    private Connection connection;

        public SupplierTypeRepository() {
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
    public void createSupplierType(SupplierType supplier_type) {
            try {
            String query = "INSERT INTO supplier_type (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, supplier_type.getId());
            ps.setString(2, supplier_type.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SupplierType deleteSupplierType(String Name) {
        String query = "DELETE FROM supplier_type WHERE name = ?";
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
    public Optional<SupplierType> findSupplierTypeByName(String Name) {
        String query = "SELECT id, name FROM supplier_type WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SupplierType supplier_type = new SupplierType(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(supplier_type);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SupplierType> findSupplierTypeById(int codeSupplierType) {
        String query = "SELECT id, name FROM supplier_type WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeSupplierType);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SupplierType supplier_type = new SupplierType(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(supplier_type);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<SupplierType> findAllSupplierType() {
                List<SupplierType> countries = new ArrayList<>();
        String query = "SELECT id, name FROM supplier_type";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SupplierType supplier_type = new SupplierType(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(supplier_type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateSupplierType(SupplierType supplier_type) {
        String query = "UPDATE supplier_type SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplier_type.getName());
            ps.setInt(2, supplier_type.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
