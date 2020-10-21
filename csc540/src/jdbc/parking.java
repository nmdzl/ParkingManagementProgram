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
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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
	private static void perminfo() throws SQLException {
		try {
            rs = statement.executeQuery("SELECT EP.pid, H.plate, EP.zid, EP.pType, EP.startDate, EP.endDate, EP.startHour, EP.startMinute, EP.expHour, EP.expMinute "
            		+ "FROM epermits EP, EhasV H "
            		+ "WHERE EP.univid = 1006020 AND EP.pid = H.pid");
		    System.out.println("UniqueID  CarLicense  ZoneID  SpaceType  StartTime  ExpTime");
		    System.out.println("-----------------------------------------------------------------------------------------------------");
    		while (rs.next()) {
    		    String pid = rs.getString("PID");
    		    String plate = rs.getString("PLATE");
    		    String zid = rs.getString("ZID");
    		    String ptype = rs.getString("PTYPE");
    		    String sd = rs.getString("STARTDATE");
    		    String ed = rs.getString("ENDDATE");
    		    String sh = rs.getString("STARTHOUR");
    		    String sm = rs.getString("STARTMINUTE");
    		    String eh = rs.getString("EXPHOUR");
    		    String em = rs.getString("EXPMINUTE");
    		    
    		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    		    LocalDateTime startTime = LocalDateTime.parse(sd, formatter);
    		    int startHr = Integer.parseInt(sh);
    		    int startMin = Integer.parseInt(sm);
    		    startTime = startTime.plusHours(startHr).plusMinutes(startMin);

    		    LocalDateTime endTime = LocalDateTime.parse(ed, formatter);
    		    int endHr = Integer.parseInt(eh);
    		    int endMin = Integer.parseInt(em);
    		    endTime = endTime.plusHours(endHr).plusMinutes(endMin);
    		    System.out.println(pid + "   " + plate + "   " + zid + "   " + ptype + "   " + startTime + "   " + endTime);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private static void vehinfo() throws SQLException {
		try {
            rs = statement.executeQuery("SELECT V.plate, V.carManf, V.carModel, V.carYear, V.carColor "
            		+ "FROM vehicles V, nepermits EP "
            		+ "WHERE EP.univid = 1006003 AND V.plate = EP.pvnumber");
		    System.out.println("LicencePlate  Manufacturer  Model  Year  Color");
		    System.out.println("-------------------------------------------------");
    		while (rs.next()) {
    		    String pl = rs.getString("PLATE");
    		    String cma = rs.getString("CARMANF");
    		    String cmo = rs.getString("CARMODEL");
    		    String cy = rs.getString("CARYEAR");
    		    String cc = rs.getString("CARCOLOR");
    		    System.out.println(pl + "   " + cma + "   " + cmo + "   " + cy + "   " + cc);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private static void availspace() throws SQLException {
		try {
            rs = statement.executeQuery("SELECT sid "
            		+ "FROM SPACES "
            		+ "WHERE LOT = 'Justice Lot' AND category = 'V' AND stype = 'Electric' AND sstatus = 0");
		    System.out.println("Space #");
		    System.out.println("----------------------------");
    		while (rs.next()) {
    		    String sid = rs.getString("SID");
    		    System.out.println(sid);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private static void viocar() throws SQLException {
		try {
            rs = statement.executeQuery("SELECT CID, CARNO, CMODEL, COLOR, CDATE, LNAME, CHH, CMM, VCAT, FEE, DUE "
            		+ "FROM Citation "
            		+ "WHERE STATUS = 'Unpaid'");
		    System.out.println("UniqueCitation#  LicenseNumber  Model  Color  Time  Lot  ViolationCat  Fee  Due");
		    System.out.println("-----------------------------------------------------------------------------------------");
    		while (rs.next()) {
    			String cid = rs.getString("CID");
    			String cno = rs.getString("CARNO");
    			String cmod = rs.getString("CMODEL");
    			String co = rs.getString("COLOR");
    			String cd = rs.getString("CDATE");
    			String ln = rs.getString("LNAME");
    			String ch = rs.getString("CHH");
    			String cm = rs.getString("CMM");
    			String vc = rs.getString("VCAT");
    			String fee = rs.getString("FEE");
    			String due = rs.getString("DUE");
    			
    			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    		    LocalDateTime cTime = LocalDateTime.parse(cd, formatter);
    		    int cHr = Integer.parseInt(ch);
    		    int cMin = Integer.parseInt(cm);
    		    cTime = cTime.plusHours(cHr).plusMinutes(cMin);
    		    
    		    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		    LocalDateTime dDate = LocalDateTime.parse(due, formatter);
    		    
    		    System.out.println(cid + "   " + cno + "   " + cmod + "   " + co + "   " + cTime + "   " + ln + "   " + vc + "   " + fee + "   " + dDate);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private static void empno() throws SQLException {
		try {
			rs = statement.executeQuery("SELECT COUNT(*) AS DNO "
					+ "FROM epermits "
					+ "WHERE zid = 'D'");
			 System.out.println("Number of employees in Zone D");
			 System.out.println("-------------------------------");
			while (rs.next()) {
				String dno = rs.getString("DNO");
				System.out.println(dno);
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
