package com.zapstore.region.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.region.domain.entity.Region;
import com.zapstore.region.domain.service.RegionService;

public class RegionRepository implements RegionService {
    
        private Connection connection;
        public RegionRepository() {
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
    public void createRegion(Region region) {
            try {
            String query = "INSERT INTO region (id, name, country_id) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, region.getCodereg());
            ps.setString(2, region.getNamereg());
            ps.setString(3, region.getCodeCountry());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Region> findAllRegion() {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT r.id, r.name , c.name as Country FROM region r JOIN country c ON c.id = r.country_id";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Region region = new Region(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("Country"));
                            regions.add(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;
    }

    @Override
    public List<Region> findAllRegionByCountry(String CountryID) {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT id, name FROM Region where country_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, CountryID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Region region = new Region(
                            rs.getString("id"),
                            rs.getString("name"), CountryID);
                            regions.add(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regions;
    }

    @Override
    public Optional<Region> deleteRegionByName(String CountryID, String name) {
        String query = "DELETE FROM region WHERE (country_id = ? && name = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, CountryID);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Region> findRegionByName(String CountryID, String name) {
        String query = "SELECT id, name, country_id FROM region WHERE (country_id = ? && name = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, CountryID);
            ps.setString(2, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Region Region = new Region(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("country_id"));
                    return Optional.of(Region);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Region> findRegionByCode(String RegionID) {
        String query = "SELECT id, name, country_id FROM Region WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, RegionID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Region region = new Region(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("country_id"));
                    return Optional.of(region);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateRegion(Region region) {
        String query = "UPDATE region SET id = ?, name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, region.getCodeCountry());
            ps.setString(2, region.getNamereg());
            ps.setString(3, region.getCodereg());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    

}
