package com.zapstore.category.infrastructure;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CategoryRepository implements CategoryService {
    private Connection connection;

        public CategoryRepository() {
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
    public void createCategory(Category category) {
            try {
            String query = "INSERT INTO category (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, category.getId());
            ps.setString(2, category.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category deleteCategory(String Name) {
        String query = "DELETE FROM category WHERE name = ?";
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
    public Optional<Category> findCategoryByName(String Name) {
        String query = "SELECT id, name FROM category WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(category);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findCategoryById(int codeCategory) {
        String query = "SELECT id, name FROM category WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codeCategory);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category(
                            rs.getInt("id"),
                            rs.getString("name"));
                    return Optional.of(category);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<Category> findAllCategory() {
                List<Category> countries = new ArrayList<>();
        String query = "SELECT id, name FROM category";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"));
                        countries.add(category);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateCategory(Category category) {
        String query = "UPDATE category SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
