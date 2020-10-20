//package jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication5;
import java.sql.*;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class parking {

	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";

	public static void main(String[] args) {
		try {
			executeScriptUsingStatement();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void executeScriptUsingStatement() throws IOException, SQLException {
		String scriptFilePath = "1.sql";
		BufferedReader reader = null;
		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			String user = "rwu5"; // For example, "jsmith"
			String passwd = "abcd1234"; // Your 9 digit student ID number

				// Get a connection from the first driver in the
				// DriverManager list that recognizes the URL jdbcURL

			con = DriverManager.getConnection(jdbcURL, user, passwd);
			// create statement object
			statement = con.createStatement();
			final List<String> queries = SQLFileCache.getInstance().getQueries( "1.sql" );
	        executeSQL( queries, con );
            ResultSet rs = null;
            rs = statement.executeQuery("SELECT table_name FROM user_tables");
    		while (rs.next()) {
    		    String s = rs.getString("table_name");
    		    System.out.println(s);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close file reader
			if (reader != null) {
				reader.close();
			}
			// close db connection
			if (con != null) {
				con.close();
			}
		}
	   }
	private static void executeSQL ( final List<String> queries, Connection conn ) throws SQLException, IOException {

        final long start = System.currentTimeMillis();
        for ( final String sql : queries ) {
            final Statement stmt = conn.createStatement();
            try {
                stmt.execute( sql );
            }
            catch ( final SQLException e ) {
                throw new SQLException( e.getMessage() + " from executing: " + sql, e.getSQLState(), e.getErrorCode() );
            }
            finally {
                stmt.close();
            }
        }

    }

	static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Throwable whatever) {
			}
		}
	}
}
