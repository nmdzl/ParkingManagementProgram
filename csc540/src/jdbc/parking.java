/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication5;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class parking {

	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	private static BufferedReader reader = null;
	private static Connection con = null;
	private static Statement statement = null;
	private static Scanner in;
	private static ResultSet rs = null;

	public static void main(String[] args) throws SQLException {
		try {
			connectDB();
			dropAllTables();
			setup();
			//menu
			in = new Scanner(System.in);
	        boolean key = true;
	        String s = "";
	        while (key) {
	        	System.out.println("#####################################################");
	        	System.out.println("##                                                 ##");
	        	System.out.println("##  Welcome to University Parking Service System!  ##");
	        	System.out.println("##                                                 ##");
	        	System.out.println("#####################################################\n");
	        	System.out.println(" 0 - UPS Admin");
	            System.out.println(" 1 - Employee");
	            System.out.println(" 2 - Student");
	            System.out.println(" 3 - Visitor");
	            System.out.println(" 4 - Sample Queries ");
	            System.out.println(" 5 - Reporting Queries ");
	            System.out.println(" q - Exit System ");
	            try {
	                System.out.println("\nEnter number to perform actions: ");
	                s = in.nextLine();
	            } catch (Exception InputMismatchException ) {
	                System.out.println("Invaild input, please try again");
	            }
	            if(s.equals("q")) {
	            	key = false;
	            }
	            //admin
	            if(s.equals("0")) {
	    	        boolean k = true;
	    	        String s1 = "";
	    	        while (key) {
	    	        	System.out.println("\n--------------------HELLO ADMIN!--------------------\n");
	    	            System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("m")) {
	    	            	k = false;
	    	            	break;
	    	            }
	    	        }
	            }
	            
	            //emp
	            if(s.equals("1")) {
	    	        boolean k = true;
	    	        String s1 = "";
	    	        while (key) {
	    	        	System.out.println("\n--------------------HELLO EMP!--------------------\n");
	    	            System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("m")) {
	    	            	k = false;
	    	            	break;
	    	            }
	    	        }
	            }
	            
	            //student
	            if(s.equals("2")) {
	    	        boolean k = true;
	    	        String s1 = "";
	    	        while (key) {
	    	        	System.out.println("\n--------------------HELLO STUDENT!--------------------\n");
	    	            System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("m")) {
	    	            	k = false;
	    	            	break;
	    	            }
	    	        }
	            }
	            
	            //visitor
	            if(s.equals("3")) {
	    	        boolean k = true;
	    	        String s1 = "";
	    	        while (key) {
	    	        	System.out.println("\n--------------------HELLO VISITOR!--------------------\n");
	    	            System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("m")) {
	    	            	k = false;
	    	            	break;
	    	            }
	    	        }
	            }
	            
	            //sample
	            if(s.equals("4")) {
	    	        boolean k = true;
	    	        String s1 = "";
	    	        while (key) {
	    	        	System.out.println("\n--------------------HELLO SAMPLE!--------------------\n");
	    	        	System.out.println(" 0 - Show parking zone");
	    	            System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("0")) {
	    	            	showzone();
	    	            }
	    	            if(s1.equals("m")) {
	    	            	k = false;
	    	            	break;
	    	            }
	    	        }
	            }
	            
	            //report
	            if(s.equals("5")) {
	    	        boolean k = true;
	    	        String s1 = "";
	    	        while (key) {
	    	        	System.out.println("\n--------------------HELLO REPORT!--------------------\n");
	    	            System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("m")) {
	    	            	k = false;
	    	            	break;
	    	            }
	    	        }
	            }
	        }
	        
	        
	        
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// close file reader
			in.close();
			// close db connection
			if (con != null) {
				con.close();
			}
		}
	}
	private static void showzone() throws SQLException {
		try {
            rs = statement.executeQuery("SELECT * FROM LHASZ");
		    System.out.println("Zone    Parking Lot");
		    System.out.println("----------------------------");
    		while (rs.next()) {
    		    String s = rs.getString("ZID");
    		    String p = rs.getString("LNAME");
    		    System.out.println(s + "   " + p);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	static void connectDB() throws IOException, SQLException {

		try {
			Class.forName("oracle.jdbc.OracleDriver");

			String user = "rwu5"; // For example, "jsmith"
			String passwd = "abcd1234"; // Your 9 digit student ID number

				// Get a connection from the first driver in the
				// DriverManager list that recognizes the URL jdbcURL

			con = DriverManager.getConnection(jdbcURL, user, passwd);
			// create statement object
			statement = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	static void setup() throws IOException, SQLException {

		try {
			final List<String> table = SQLFileCache.getInstance().getQueries( "table.sql" );
	        executeSQL( table, con );
            
            rs = statement.executeQuery("SELECT table_name FROM user_tables");
    		while (rs.next()) {
    		    String s = rs.getString("table_name");
    		    System.out.println(s + " has been created!");
    		}
    		final List<String> value = SQLFileCache.getInstance().getQueries( "value.sql" );
	        executeSQL( value, con );
	        System.out.println("Value inserted");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private static void executeSQL ( final List<String> queries, Connection conn ) throws SQLException, IOException {

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
    public static void dropAllTables() {
        try {
            System.out.println("drop all existing Table");
            //statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");

            statement.executeUpdate("DROP TABLE LOTS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE SPACES CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE ZONES CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE LHASZ CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE VEHICLES CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE VPERMITS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE EPERMITS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE NEPERMITS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE EHASV CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE CITATION CASCADE CONSTRAINTS");
            //statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }
}
