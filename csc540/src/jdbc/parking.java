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
import java.util.HashMap;
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
	    	        	System.out.println(" 1 - Report the number of citations in all lots during last 30 days");
	    	        	System.out.println(" 2 - Report the number of visitor permits of different permit type");
	    	        	System.out.println(" 3 - Report the total revenue generated for all visitor's parking zones");
	    	        	System.out.println(" m - Return to Main Menu");
	    	            try {
	    	            	System.out.println("\nEnter number to perform actions: ");
	    	            	s1 = in.nextLine();
	    	            } catch (Exception InputMismatchException ) {
	    	                System.out.println("Invaild input, please try again");
	    	            }
	    	            if(s1.equals("1")) {
	    	            	citationNumberInAllLots();
	    	            }
	    	            if(s1.equals("2")) {
    	            		System.out.println("--- All Parking Lots ---");
    	            		showLots();
    	            		System.out.println("\nEnter a parking lot: ");
    	            		String lot = "";
	    	            	try {
	    	            		lot = in.nextLine();
	    	            	} catch (Exception InputMismatchException) {
	    	           			System.out.println("Invalid input, please try again");
	    	           		}
	    	            	System.out.println("\nEnter the starting date (dd-MMM-yy): ");
	    	            	String startDate = "";
	    	            	try {
	    	            		startDate = in.nextLine();
	    	            	} catch (Exception InputMismatchException) {
	    	           			System.out.println("Invalid input, please try again");
	    	           		}
	    	            	System.out.println("\nEnter the ending date (dd-MMM-yy): ");
	    	            	String endDate = "";
	    	            	try {
	    	            		endDate = in.nextLine();
	    	            	} catch (Exception InputMismatchException) {
	    	           			System.out.println("Invalid input, please try again");
	    	           		}
	    	            	vpermitNmberInLot(lot, startDate, endDate);
	    	            }
	    	            if(s1.equals("3")) {
	    	            	System.out.println("\nEnter the Year and Month (yy-mm): ");
	    	            	String year_month = "";
	    	            	try {
	    	            		year_month = in.nextLine();
	    	            	} catch (Exception InputMismatchException) {
	    	           			System.out.println("Invalid input, please try again");
	    	           		}
	    	            	if (year_month.length() != 5) {
	    	            		System.out.println("\nInvalid input: " + year_month);
	    	            		continue;
	    	            	}
	    	            	String year = year_month.substring(0, 2);
	    	            	int month;
	    	            	try {
	    	            		month = Integer.parseInt(year_month.substring(3, 5));
	    	            	} catch(Exception e) {
	    	            		System.out.println("\nInvalid input: " + year_month);
	    	            		continue;
	    	            	}
	    	            	switch(month) {
	    	            	case 1:
	    	            		calculateAllRevenue("01-JAN-" + year, "31-JAN-" + year);
	    	            		break;
	    	            	case 2:
	    	            		calculateAllRevenue("01-FEB-" + year, "28-FEB-" + year);
	    	            		break;
	    	            	case 3:
	    	            		calculateAllRevenue("01-MAR-" + year, "31-MAR-" + year);
	    	            		break;
	    	            	case 4:
	    	            		calculateAllRevenue("01-APR-" + year, "30-APR-" + year);
	    	            		break;
	    	            	case 5:
	    	            		calculateAllRevenue("01-MAY-" + year, "31-MAY-" + year);
	    	            		break;
	    	            	case 6:
	    	            		calculateAllRevenue("01-JUN-" + year, "30-JUN-" + year);
	    	            		break;
	    	            	case 7:
	    	            		calculateAllRevenue("01-JUL-" + year, "31-JUL-" + year);
	    	            		break;
	    	            	case 8:
	    	            		calculateAllRevenue("01-AUG-" + year, "31-AUG-" + year);
	    	            		break;
	    	            	case 9:
	    	            		calculateAllRevenue("01-SEP-" + year, "30-SEP-" + year);
	    	            		break;
	    	            	case 10:
	    	            		calculateAllRevenue("01-OCT-" + year, "31-OCT-" + year);
	    	            		break;
	    	            	case 11:
	    	            		calculateAllRevenue("01-NOV-" + year, "30-NOV-" + year);
	    	            		break;
	    	            	case 12:
	    	            		calculateAllRevenue("01-DEC-" + year, "31-DEC-" + year);
	    	            		break;
	    	            	default:
	    	            		System.out.println("\nInvalid input: " + year_month);
	    	            	}
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
		}finally {
			// close file reader
			in.close();
			// close db connection
			if (con != null) {
				con.close();
			}
		}
	}
	
	private static void calculateAllRevenue(String startDate, String endDate) throws SQLException {
		try {
			HashMap<Integer, Double> revenues = new HashMap<>();
			// fines involved with a visitor permit's plate number
			rs = statement.executeQuery("SELECT CDATE, SUM(FEE) FROM Citation WHERE CDATE BETWEEN '" + startDate + "' AND '" + endDate + "' AND (VCAT = 'No Permit' OR CARNO IN (SELECT pvnumber FROM vpermits)) GROUP BY CDATE");
			while (rs.next()) {
				int date = Integer.parseInt(rs.getString("CDATE").substring(8, 10));
				double fine = Float.parseFloat(rs.getString("SUM(FEE)"));
				revenues.put(date, revenues.getOrDefault(date, 0.0) + fine);
			}
			// ticket fees of visitor permits
			System.out.println("Date | Total Revenues");
			System.out.println("-----------------------------");
			for (int date = 1; date < 32; date++) {
				if (revenues.containsKey(date)) {
					System.out.println(date + "    " + revenues.get(date));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void showLots() throws SQLException {
		try {
			rs = statement.executeQuery("SELECT * FROM LOTS");
			while (rs.next()) {
				String lname = rs.getString("LNAME");
				System.out.println(lname);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void vpermitNmberInLot(String lot, String startDate, String endDate) throws SQLException {
		try {
			rs = statement.executeQuery("SELECT pType, COUNT(*) FROM vpermits WHERE lname = '" + lot + "' AND startDate BETWEEN '" + startDate + "' AND '" + endDate + "' GROUP BY pType ORDER BY pType");
			System.out.println("Permit Type | Number");
			System.out.println("-----------------------------");
			while (rs.next()) {
				String pType = rs.getString("pType");
				String count = rs.getString("COUNT(*)");
				System.out.println(pType + "   " + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void citationNumberInAllLots() throws SQLException {
		try {
			rs = statement.executeQuery("SELECT LNAME, COUNT(*) FROM Citation WHERE MONTHS_BETWEEN(CDATE, SYSDATE) <= 1 GROUP BY LNAME ORDER BY LNAME");
			System.out.println("Lot Name | Citation Number");
			System.out.println("-----------------------------");
			while (rs.next()) {
				String lname = rs.getString("LNAME");
				String count = rs.getString("COUNT(*)");
				System.out.println(lname + "   " + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

			String user = "bsun8"; // For example, "jsmith"
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
            statement.executeUpdate("DROP TABLE SPACES CASCADE CONSTRAINTS");
            //statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }
}
