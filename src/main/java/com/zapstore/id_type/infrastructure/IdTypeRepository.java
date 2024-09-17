package com.zapstore.id_type.infrastructure;

import com.zapstore.id_type.domain.entity.IdType;
import com.zapstore.id_type.domain.service.IdTypeService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class IdTypeRepository implements IdTypeService {
    private Connection connection;

        public IdTypeRepository() {
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
    public void createIdType(IdType id_type) {
            try {
            String query = "INSERT INTO id_type (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id_type.getId());
            ps.setString(2, id_type.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IdType deleteIdType(String Name) {
        String query = "DELETE FROM id_type WHERE name = ?";
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
    public Optional<IdType> findIdTypeByName(String Name) {
        String query = "SELECT id, name FROM id_type WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IdType id_type = new IdType(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(id_type);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<IdType> findIdTypeById(int codeIdType) {
        String query = "SELECT id, name FROM id_type WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeIdType);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    IdType id_type = new IdType(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(id_type);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<IdType> findAllIdType() {
                List<IdType> countries = new ArrayList<>();
        String query = "SELECT id, name FROM id_type";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IdType id_type = new IdType(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(id_type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateIdType(IdType id_type) {
        String query = "UPDATE id_type SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id_type.getName());
            ps.setInt(2, id_type.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
