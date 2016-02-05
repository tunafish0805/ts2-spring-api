package com.springTestApp.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.springTestApp.vo.Device;
import com.springTestApp.dao.TestDAO;

public class TestDAOImpl implements TestDAO {
    private DataSource dataSource;
    private Connection conn;
    
    public static final String ELEMENT_TABLE = "ElementTable";
    public static final String ATTRIBUTE_TABLE = "AttributeTable";
    public static final String VALUE_TABLE = "ValueTable";

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
    
    @Override
    public String fetchDataValue(String importIndex, String marketIndex, String timeIndex, String measureIndex) {
        connectDB();
        
        if (importIndex.equals("") || marketIndex.equals("") || timeIndex.equals("") || measureIndex.equals("")) {
            return null;
        }

        String statement = " SELECT actualvalue FROM " + VALUE_TABLE +
                           " WHERE importindex = ?::integer AND" +
                           " marketindex = ?::integer AND" +
                           " timeindex = ?::integer AND" +
                           " measureindex = ?::integer";
        try {
            if (conn == null || conn.isClosed()) connectDB();
            PreparedStatement ps = conn.prepareStatement(statement);
            ps.setString(1, importIndex);
            ps.setString(2, marketIndex);
            ps.setString(3, timeIndex);
            ps.setString(4, measureIndex);
            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            String actualValue = rs.getString(1).trim();
            ps.close();
            return actualValue;
        }
        catch (SQLException e) {
            return null;
        }
        finally {
            try { conn.close(); }
            catch (SQLException e) {
                return null;
            }
        }
    }
    
    public void connectDB() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                            + "Include in your library path!");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver Registered...");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://rainbowjellybeans.cnc8wwjfvy2q.us-east-1.rds.amazonaws.com:5432/postgres", "rooty_tooty", "password");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("DB Connect Successful...");
            this.conn = connection;
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
