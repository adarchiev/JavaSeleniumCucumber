package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonMethodsDB {
	public Connection eodConn = null;

	/*
	 * Method to Setup DB Connection
	 */
	public Connection getSQLServerRDSConnectionToDPDATA(String sEnv) {
		String dbURL = null;

		try {
			// Connecting to the dpdata database
			dbURL = "jdbc:postgresql://url";
			eodConn = DriverManager.getConnection(dbURL, "username", "password");

		} catch (Exception e) {
			System.out.println("Connection Operation failed in " + sEnv);
		}
		return eodConn;
	}

	// Method to close dpdata database connection
	public void closeEodConnection() throws SQLException {
		eodConn.close();
	}

	/*
	 * Method returns List of Maps containing rows matching ReslutSet query
	 */
	public List<Map<String, Object>> resultSetToListOfMaps(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<Map<String, Object>> list = new ArrayList<>();

		while (rs.next()) {
			Map<String, Object> row = new HashMap<>();
			for (int i = 1; i <= columns; i++) {
				String key = md.getColumnName(i);

				Object value = null;
				try {
					value = rs.getObject(i);
				} catch (Exception e) {
					try {
						value = rs.getString(i);
					} catch (Exception e2) {
						e.printStackTrace();
					}
				}

				row.put(key, value);
			}
			list.add(row);
		}

		return list;

	}

	/*
	 * Method will return result set and takes String SQL query
	 */
	public ResultSet returnResultSetObject(String sql) throws SQLException {
		this.getSQLServerRDSConnectionToDPDATA(ConfigurationReader.getProperty("environament"));

		PreparedStatement stepThreeData = eodConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stepThreeData.executeQuery();
		this.closeEodConnection();
		return rs;
	}

	/*
	 * Method to update table or delete from table
	 */
	public void updateDB(String sql) throws SQLException {
		this.getSQLServerRDSConnectionToDPDATA(ConfigurationReader.getProperty("environament"));

		PreparedStatement stepThreeData = eodConn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		try {
			@SuppressWarnings("unused")
			ResultSet rs = stepThreeData.executeQuery();
		} catch (Exception e) {
		}

		this.closeEodConnection();

	}
}
