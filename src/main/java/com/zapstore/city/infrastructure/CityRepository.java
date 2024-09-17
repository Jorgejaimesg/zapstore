package com.zapstore.city.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.zapstore.city.domain.entity.City;
import com.zapstore.city.domain.entity.CityShow;
import com.zapstore.city.domain.service.CityService;


public class CityRepository implements CityService{
    
        private Connection connection;
        public CityRepository() {
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
        public void createCity(City City) {
            try {
            String query = "INSERT INTO city (id, name, region_id) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, City.getCodecity());
            ps.setString(2, City.getNamecity());
            ps.setString(3, City.getCodereg());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        @Override
        public List<CityShow> findAllCity() {
            List<CityShow> cities = new ArrayList<>();
            String query = "SELECT c.id, c.name , r.name AS region, co.name as Country FROM City c  JOIN region r ON r.id = c.region_id JOIN country co ON co.id = r.country_id";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CityShow Cityshow = new CityShow(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("region"),
                                rs.getString("Country"));
                                cities.add(Cityshow);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cities;
        }
        @Override
        public List<City> findAllCityByRegion(String RegionID) {
            List<City> Cities = new ArrayList<>();
            String query = "SELECT id, name FROM City where region_id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, RegionID);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        City city = new City(
                                rs.getString("id"),
                                rs.getString("name"), RegionID);
                                Cities.add(city);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Cities;
        }
        @Override
        public Optional<City> deleteCityByName(String RegionID, String name) {
            String query = "DELETE FROM city WHERE (region_id = ? && name = ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, RegionID);
                ps.setString(2, name);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public Optional<City> findCityByName(String RegionID, String name) {
            String query = "SELECT id, name, region_id FROM city WHERE (region_id = ? && name = ?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, RegionID);
                ps.setString(2, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        City city = new City(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("region_id"));
                        return Optional.of(city);
                    }
    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        }
        @Override
        public Optional<City> findCityByCode(String CityID) {
            String query = "SELECT id, name, region_id FROM City WHERE id=?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, CityID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        City City = new City(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getString("region_id"));
                        return Optional.of(City);
                    }
    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Optional.empty();
        
        }
        @Override
        public void updateCity(City City) {
            String query = "UPDATE City SET region_id = ?, name = ? WHERE id = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, City.getCodereg());
                ps.setString(2, City.getNamecity());
                ps.setString(3, City.getCodecity());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        }
    
