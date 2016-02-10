package com.springTestApp.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.springTestApp.dao.TestDAO;

public class TestDAOImpl implements TestDAO {
    private DataSource dataSource;

    public static final String ELEMENT_TABLE = "ElementTable";
    public static final String ATTRIBUTE_TABLE = "AttributeTable";
    public static final String VALUE_TABLE = "ValueTable";

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String fetchDataValue(String importIndex, String marketIndex, String timeIndex, String measureIndex) {

        if (importIndex.equals("") || marketIndex.equals("") || timeIndex.equals("") || measureIndex.equals("")) {
            return null;
        }

        String statement = " SELECT actualvalue FROM " + VALUE_TABLE + " WHERE importindex = ?::integer AND"
                        + " marketindex = ?::integer AND" + " timeindex = ?::integer AND" + " measureindex = ?::integer";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
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
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                return null;
            }
        }
    }
}
