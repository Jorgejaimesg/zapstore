package com.zapstore.brand.infrastructure;

import com.zapstore.brand.domain.entity.Brand;
import com.zapstore.brand.domain.service.BrandService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class BrandRepository implements BrandService {
    private Connection connection;

        public BrandRepository() {
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
    public void createBrand(Brand brand) {
            try {
            String query = "INSERT INTO brand (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, brand.getId());
            ps.setString(2, brand.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Brand deleteBrand(String Name) {
        String query = "DELETE FROM brand WHERE name = ?";
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
    public Optional<Brand> findBrandByName(String Name) {
        String query = "SELECT id, name FROM brand WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Brand brand = new Brand(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(brand);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Brand> findBrandById(int codeBrand) {
        String query = "SELECT id, name FROM brand WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeBrand);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Brand brand = new Brand(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(brand);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<Brand> findAllBrand() {
                List<Brand> countries = new ArrayList<>();
        String query = "SELECT id, name FROM brand";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Brand brand = new Brand(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(brand);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateBrand(Brand brand) {
        String query = "UPDATE brand SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, brand.getName());
            ps.setInt(2, brand.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
