//package jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication5;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;

public class parking {

	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	private static BufferedReader reader = null;
	private static Connection con = null;
	private static Statement statement = null;
	private static ResultSet rs = null;
	private static Scanner in;

	public static void main(String[] args) {
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
	    	        	System.out.println(" 0 - Get Visitor Permit");
	    	        	System.out.println(" 1 - Exit Lot");
	    	        	System.out.println(" 2 - Pay Citation");
	    	        	System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("0")) {
	    	            	System.out.println("\n Please enter Plate of Car:");
	    	            	String plate = in.nextLine();
	    	            	rs = statement.executeQuery("select * from VEHICLES where plate='"+plate+"'");
	    	            	if(!rs.next()) {
	    	            		System.out.println(" This plate hasn't enrolled, please provide more information.");
	    	            		System.out.println(" Please enter Manufacturer:");
	    	            		String manf = in.nextLine();
	    	            		System.out.println(" Please enter Model:");
	    	            		String model = in.nextLine();
	    	            		System.out.println("\n Please enter Year of car:");
	    	            		Integer cyear = in.nextInt();
	    	            		in.nextLine();
	    	            		System.out.println("\n Please enter color of car:");
	    	            		String color = in.nextLine();
	    	            		statement.executeUpdate("insert into vehicles values('"+plate+"','"+manf+"','"+model+"',"+cyear+",'"+color+"')");
	    	            	}
	    	            	System.out.println("\n Please enter Name of Lot:");
	    	            	String lname = in.nextLine();
	    	            	System.out.println("\n Please enter duration of reservation:");
	    	            	Integer duration = in.nextInt();
	    	            	in.nextLine();
	    	            	System.out.println("\n Please enter parking type:");
	    	            	String type = in.nextLine();
	    	            	LocalDateTime mydate = LocalDateTime.now();
	    	            	DateTimeFormatter myformat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	    	            	String date = mydate.format(myformat);
	    	            	int hour = mydate.getHour();
	    	            	int minute = mydate.getMinute();
	    	            	int expirehour = hour + duration;
	    	            	
	    	            	
	    	            	statement.executeUpdate("insert into vpermits values('20V0066P','"+plate+"','V','"+type+"','"+date+"','"+date+"',"+hour+","+minute+","+expirehour+","+minute+","+duration+",151,'"+lname+"')");
	    	            	//need to update about Start date and time, calculate the end time and date. 2.Generate pid & spacenum.
//	    	            	rs = statement.executeQuery("select * from vpermits");
//	    	            	 System.out.println("Permit ID	|	plate	|	category	|	type	|	start date & time	|	end date &time	|	duration	|	space NO	|	Lot name");
//	    	            	while (rs.next()) {
//	    	        		    String permitid = rs.getString("pid");
//	    	        		    String plate2 = rs.getString("pvnumber");
//	    	        		    String category = rs.getString("zid");
//	    	        		    String type2 = rs.getString("pType");
//	    	        		    String startdate = rs.getString("startDate");
//	    	        		    String enddate = rs.getString("expDate");
//	    	        		    String dur = rs.getString("requestedHour");
//	    	        		    String spacenum = rs.getString("spaceNum");
//	    	        		    String lot = rs.getString("lname");
//	    	        		   	System.out.println("	"+permitid+"	"+plate2+"	"+category+"	"+type2+"	"+startdate+"	"+enddate+"	"+dur+"	"+spacenum+"	"+lot);
//	    	        		}
	    	            }
	    	            if(s1.equals("1")) {
	    	            	System.out.println("\n Please enter Name of Lot:");
	    	            	String lname = in.nextLine();
	    	            	System.out.println("\n Please enter Space Number:");
	    	            	String number = in.nextLine();
//	    	            	String lname = "Premiere Lot";
//	    	            	String number = "200";
	    	            	rs = statement.executeQuery("select category from SPACES where LOT='" + lname + "' AND sid =" + number);
	    	            	if(rs.next()) {
	    	            		String type = rs.getString("category");
	    	            	
	    	            	statement.executeUpdate("UPDATE SPACES set sstatus = 0 WHERE LOT='" + lname + "' AND sid = " + number);
	    	            	if(type.equals("Visitor"))
	    	            		statement.executeUpdate("update LOTS set VSPACE = VSPACE+1 where LNAME=" + lname);
	    	            	else
	    	            		statement.executeUpdate("update LOTS set TSPACE = TSPACE+1 where LNAME=" + lname);
	    	            	}
	    	            }
	    	            if(s1.equals("2")) {
	    	            	System.out.println("\n Please enter Citation ID:");
	    	            	String id = in.nextLine();
	    	            	rs = statement.executeQuery("select * from citation where CID=" + id);
	    	            	while (rs.next()) {
	    	            		int a = rs.getInt("CID");
	    	        		    String status = rs.getString("STATUS");
	    	        		    System.out.println("Before payment: CID = " + a + "     Status = " + status);
	    	        		}
	    	            	statement.executeUpdate("update citation set status = 'paid' where CID=" + id);
	    	            	rs = statement.executeQuery("select * from citation where CID=" + id);
	    	            	while (rs.next()) {
	    	            		int a = rs.getInt("CID");
	    	        		    String status = rs.getString("STATUS");
	    	        		    System.out.println("After payment: CID = " + a + "     Status = " + status);
	    	        		}
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
	        
	        if (con != null) {
				con.close();
			}
	        
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		} finally {
			// close file reader
			if (reader != null) {
				reader.close();
			}
			// close db connection
//			if (con != null) {
//				con.close();
//			}
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
    
	public static void dropAllTables() {
        try {
            System.out.println("drop all existing Table");
            //statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");

            statement.executeUpdate("DROP TABLE LOTS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE ZONES CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE LHASZ CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE VEHICLES CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE VPERMITS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE EPERMITS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE NEPERMITS CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE EHASV CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE CITATION CASCADE CONSTRAINTS");
            statement.executeUpdate("DROP TABLE SPACES CASCADE CONSTRAINTS");
            //statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }
}
