package jayslabs.test.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class JDBCUtil {

	String url = "jdbc:mysql://localhost:3306/jayqadbt";
	String user = "tester1";
	String pwd = "123K@must@k@456";
	String selectAll = "select * from testcase";
	
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	} 
	
	public List<HashMap<String, String>> getDBdata() throws SQLException {
		
		List<HashMap<String, String>> maplist = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> datamap = new HashMap<String, String>();

		
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(selectAll);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		ArrayList<String> headers = new ArrayList<String>();
		
		// The column count starts from 1
		for (int i = 1; i <= columnCount; i++ ) {
		  String name = rsmd.getColumnName(i);
		  headers.add(name);
		}
		
		Iterator iter;
		String key = "";
		String value = "";
		while (rs.next()) {
			iter = headers.iterator();
			
			while (iter.hasNext()) {
				key = iter.next().toString();
				value = rs.getString(key);
				datamap.put(key, value);
			}
			maplist.add(datamap);
		}
		
		return maplist;
	}
}
