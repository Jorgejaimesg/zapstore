package com.zapstore.country.infrastructure;

import com.zapstore.country.domain.entity.Country;
import com.zapstore.country.domain.service.CountryService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class CountryRepository implements CountryService {
    private Connection connection;

        public CountryRepository() {
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
    public void createCountry(Country country) {
            try {
            String query = "INSERT INTO country (id, name) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, country.getCodeCountry());
            ps.setString(2, country.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(String Name) {
        String query = "DELETE FROM country WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findCountryByName(String Name) {
        String query = "SELECT id, name FROM country WHERE name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Country country = new Country(
                            rs.getString("id"),
                            rs.getString("name"));
                    return Optional.of(country);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Country> findCountryByCode(String id) {
        String query = "SELECT id, name FROM country WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Country country = new Country(
                            rs.getString("id"),
                            rs.getString("name"));
                    return Optional.of(country);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }

    @Override
    public List<Country> findAllCountry() {
                List<Country> countries = new ArrayList<>();
        String query = "SELECT id, name FROM country";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country(
                        rs.getString("id"),
                        rs.getString("name"));
                        countries.add(country);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void updateCountry(Country country) {
        String query = "UPDATE country SET name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, country.getName());
            ps.setString(2, country.getCodeCountry());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

}
