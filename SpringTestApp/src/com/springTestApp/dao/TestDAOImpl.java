package com.springTestApp.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.springTestApp.vo.Device;
import com.springTestApp.dao.TestDAO;

public class TestDAOImpl implements TestDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Device device) {

        String sql = "INSERT INTO Inventory ( id, carnet, currnetUsage, dateAllocated, "+
        "dateRecvd, dateRetired, deadSensor, equipmentName, lastCleaning, location, "+
        "model, pastCarnet, startupUsage, station, status) VALUES (?, ?, ?, ?, ?, ?, "+
        "?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, device.getId());
            ps.setString(2, device.getCarnet());
            ps.setString(3, device.getCurrnetUsage());
            ps.setString(4, device.getDateAllocated());
            ps.setString(5, device.getDateRecvd());
            ps.setString(6, device.getDateRetired());
            ps.setString(7, device.getDeadSensor());
            ps.setString(8, device.getEquipmentName());
            ps.setString(9, device.getLastCleaning());
            ps.setString(10, device.getLocation());
            ps.setString(11, device.getModel());
            ps.setString(12, device.getPastCarnet());
            ps.setString(13, device.getStartupUsage());
            ps.setString(14, device.getStation());
            ps.setString(15, device.getStatus());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public List<Device> list() {

        String sql = "SELECT * FROM Inventory";

        Connection conn = null;
        List<Device> devices = new ArrayList<Device>();

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            Device device = null;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                device = new Device();
                device.setId(rs.getString("id"));
                device.setCarnet(rs.getString("carnet"));
                device.setCurrnetUsage(rs.getString("currnetUsage"));
                device.setDateAllocated(rs.getString("dateAllocated"));
                device.setDateRecvd(rs.getString("dateRecvd"));
                device.setDateRetired(rs.getString("dateRetired"));
                device.setDeadSensor(rs.getString("deadSensor"));
                device.setEquipmentName(rs.getString("equipmentName"));
                device.setLastCleaning(rs.getString("lastCleaning"));
                device.setLocation(rs.getString("location"));
                device.setModel(rs.getString("model"));
                device.setPastCarnet(rs.getString("pastCarnet"));
                device.setStartupUsage(rs.getString("startupUsage"));
                device.setStation(rs.getString("station"));
                device.setStatus(rs.getString("status"));
                
                devices.add(device);
            }
            rs.close();
            ps.close();
            return devices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void delete(String id) {

        String sql = "DELETE FROM Inventory WHERE ID = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
