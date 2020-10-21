
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication5;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
			// menu
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
				} catch (Exception InputMismatchException) {
					System.out.println("Invaild input, please try again");
				}
				if (s.equals("q")) {
					key = false;
				}
				// admin
				if (s.equals("0")) {
					boolean k = true;
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO ADMIN!--------------------\n");
						System.out.println(" 0 - Add new parking lot");
						System.out.println(" 1 - Add new parking zone");
						System.out.println(" 2 - Assign zone to lot");
						System.out.println(" 3 - Assign type to space");
						System.out.println(" 4 - Assign permit");
						System.out.println(" 5 - Check visitor valid parking");
						System.out.println(" 6 - Check non-visitor valid parking");
						System.out.println(" 7 - Issue new citation");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("m")) {
							k = false;
							break;
						}
						if (s1.equals("0")) {
							System.out.println("\n--------------------ADD LOT--------------------\n");
							System.out.println("\nPlease enter the name of the parking lot");
							String lname = in.nextLine();
							System.out.println("\nPlease enter the address of the parking lot");
							String ladd = in.nextLine();
							int ls = -2;
							int lv = -1;
							while (lv > ls || ls <= 0 || lv < 0) {
								System.out.println("\nPlease enter the # of spaces of the parking lot");
								String st = in.nextLine();
								try {
									ls = Integer.valueOf(st);
								} catch (Exception e) {
									System.out.println("Invalid input");
								}

								System.out.println("\nPlease enter the # of visitor spaces of the parking lot");
								String str = in.nextLine();
								try {
									lv = Integer.valueOf(str);
								} catch (Exception e) {
									System.out.println("Invalid input");
								}

								if (lv > ls) {
									System.out.println(
											"\nWarning! The visitor spaces cannot larger than the parking spaces!");
								}
							}
							System.out.println("\nPlease enter the addition notes of the parking lot");
							String lnotes = in.nextLine();
							addlot(lname, ladd, ls, lv, lnotes);
						}
						if (s1.equals("2")) {
							System.out.println("\n--------------------ASSIGN ZONE--------------------\n");
							System.out.println("\nPlease enter the name of the parking lot");
							String lname = in.nextLine();
							System.out.println("\nPlease enter the ID of the parking ZONE");
							String zid = in.nextLine();
							zoneToLot(lname, zid);
						}
						if (s1.equals("1")) {
							System.out.println("\n--------------------ADD ZONE--------------------\n");
							System.out.println("\nPlease enter the ID of the parking ZONE");
							String zid = in.nextLine();
							addZone(zid);
						}
						if (s1.equals("3")) {
							System.out.println("\n--------------------ASSIGN SPACE--------------------\n");
							System.out.println("\nPlease enter the name of the parking lot");
							String lname = in.nextLine();
							// System.out.println("\nPlease enter the # of the parking space");
							int snumber = 0;
							while (snumber <= 0) {
								System.out.println("\nPlease enter the # of the parking space");
								String st = in.nextLine();
								try {
									snumber = Integer.valueOf(st);
								} catch (Exception e) {
									System.out.println("Invalid input");
								}
							}

							System.out.println("\nPlease enter the type of the parking space");
							String stype = in.nextLine();
							System.out.println("\nPlease enter the ZONE ID of the parking space");
							String szone = in.nextLine();
							assignSpace(lname, snumber, stype, szone);
						}
						if (s1.equals("4")) {
							while (true) {
								System.out.println("\n--------------------ASSIGN PERMITS--------------------\n");
								System.out.println(" 1 - Employee Permit");
								System.out.println(" 2 - Student Permit");
								System.out.println(" 3 - Add vehicle for existing Employee Permit");
								System.out.println(" 4 - Add Vehicle");
								System.out.println(" b - Back to ADMIN Menu");
								try {
									System.out.println("\nEnter number to perform actions: ");
									s1 = in.nextLine();
								} catch (Exception InputMismatchException) {
									System.out.println("Invaild input, please try again");
								}

								if (s1.equals("b")) {
									break;
								}
								if (s1.equals("1")) {
									System.out.println("\n--------------------ASSIGN EPERMIT--------------------\n");
									
									int uid = 0;
									while (uid < 1000000 || uid > 9999999) {
										System.out.println("\nPlease enter the UnivID of the Permit(between 1000000 - 9999999):");
										String st = in.nextLine();
										try {
											uid = Integer.valueOf(st);
										} catch (Exception e) {
											System.out.println("Invalid input");
										}
									}
									System.out.println("\nPlease enter the car plate of the Permit");
									String plate = in.nextLine();
									System.out.println("\nPlease enter the Zone of the Permit");
									String zone = in.nextLine();
									System.out.println("\nPlease enter the vehicle type of the Permit");
									String vtype = in.nextLine();
									System.out.println("\nPlease enter the start date of the Permit(YYYY-MM-DD)");
									String sdate = in.nextLine();
									assignEPermit(plate,uid,zone,vtype,sdate);
								}
								if (s1.equals("2")) {
									System.out.println("\n--------------------ASSIGN NEPERMIT--------------------\n");
									
									int uid = 0;
									while (uid < 1000000 || uid > 9999999) {
										System.out.println("\nPlease enter the UnivID of the Permit(between 1000000 - 9999999):");
										String st = in.nextLine();
										try {
											uid = Integer.valueOf(st);
										} catch (Exception e) {
											System.out.println("Invalid input");
										}
									}
									System.out.println("\nPlease enter the car plate of the Permit");
									String plate = in.nextLine();
									System.out.println("\nPlease enter the Zone of the Permit");
									String zone = in.nextLine();
									System.out.println("\nPlease enter the vehicle type of the Permit");
									String vtype = in.nextLine();
									System.out.println("\nPlease enter the start date of the Permit(YYYY-MM-DD)");
									String sdate = in.nextLine();
									assignNEPermit(plate,uid,zone,vtype,sdate);
								}
								if (s1.equals("3")) {
									System.out.println("\n--------------------ADD VEHICLE TO EPERMITS--------------------\n");
									System.out.println("\nPlease enter the plate# of the vehicle");
									String vid = in.nextLine();
									System.out.println("\nPlease enter the epermit number");
									String pid = in.nextLine();
									addVtoE(vid, pid);
								}
								if (s1.equals("4")) {
									System.out.println("\n--------------------ADD VEHICLE--------------------\n");
									System.out.println("\nPlease enter the plate# of the vehicle");
									String pid = in.nextLine();
									System.out.println("\nPlease enter the Manufacture of the vehicle");
									String mfc = in.nextLine();
									System.out.println("\nPlease enter the Model of the vehicle");
									String mdl = in.nextLine();
									int vy = 0;
									while (vy <= 1900 || vy >= 2021) {
										System.out.println("\nPlease enter the Year of the vehicle");
										String st = in.nextLine();
										try {
											vy = Integer.valueOf(st);
										} catch (Exception e) {
											System.out.println("Invalid input year");
										}
									}
									System.out.println("\nPlease enter the color of the vehicle");
									String vc = in.nextLine();
									
									addVehicle(pid,mfc,mdl,vy,vc);
									
								}

							}

						}
						if (s1.equals("5")) {
							System.out.println("\n--------------------CHECK VPARKING--------------------\n");
							System.out.println("\nPlease enter the name of the parking lot");
							String lname = in.nextLine();
							int snumber = 0;
							while (snumber <= 0) {
								System.out.println("\nPlease enter the # of the parking space");
								String st = in.nextLine();
								try {
									snumber = Integer.valueOf(st);
								} catch (Exception e) {
									System.out.println("Invalid input");
								}
							}
							System.out.println("\nPlease enter the plate of the visitor vehicle");
							String plate = in.nextLine();
							
							if (checkVV(lname,snumber,plate)) {
								System.out.println("Valid Visitor Parking!");
							} else {
								System.out.println("Invalid Visitor Parking!");
							}
						}
						if (s1.equals("6")) {
							k = false;
							break;
						}
						if (s1.equals("7")) {
							k = false;
							break;
						}
					}
				}

				// emp
				if (s.equals("1")) {
					boolean k = true;
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO EMP!--------------------\n");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("m")) {
							k = false;
							break;
						}
					}
				}

				// student
				if (s.equals("2")) {
					boolean k = true;
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO STUDENT!--------------------\n");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("m")) {
							k = false;
							break;
						}
					}
				}

				// visitor
				if (s.equals("3")) {
					boolean k = true;
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO VISITOR!--------------------\n");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("m")) {
							k = false;
							break;
						}
					}
				}

				// sample
				if (s.equals("4")) {
					boolean k = true;
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO SAMPLE!--------------------\n");
						System.out.println(" 0 - Show parking zone");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("0")) {
							showzone();
						}
						if (s1.equals("m")) {
							k = false;
							break;
						}
					}
				}

				// report
				if (s.equals("5")) {
					boolean k = true;
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO REPORT!--------------------\n");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("m")) {
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
		} finally {
			// close file reader
			in.close();
			// close db connection
			if (con != null) {
				con.close();
			}
		}
	}

	private static boolean checkVV(String lname, int snumber, String plate) {
		// TODO Auto-generated method stub
		try {			
			String Q = "SELECT CATEGORY FROM SPACES WHERE LOT = '" + lname + "' AND sid = " + snumber;
			rs = statement.executeQuery(Q);
			if (rs.next()) {
				String capa = rs.getString("CATEGORY");
				if(!capa.equals("V")) {
					return false;
				}
			}
			else {
				return false;
			}
			
			String QV = "SELECT * FROM VPERMITS WHERE PVNUMBER = '" + plate + "' AND LNAME ='" + lname + "'" ;

			rs = statement.executeQuery(QV);
			LocalDateTime late = null;
			int hour = 0;
			int minute = 0;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			while (rs.next()) {
				String exp = rs.getString("EXPDATE");
				hour = rs.getInt("EXPHOUR");
				minute = rs.getInt("EXPMINUTE");
				
				LocalDateTime t = LocalDateTime.parse(exp, formatter);
				if(late != null) {
					if(late.compareTo(t) < 0) {
						late = t;
					}
				}
				else {
					late = t;
				}
			}				
			late = late.plusHours(hour).plusMinutes(minute);
			LocalDateTime current = LocalDateTime.now();
			
			if(current.compareTo(late) < 0) {
				return false;
			}
			else return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addVtoE(String vid, String pid) {
		// TODO Auto-generated method stub
		try {
			String Q = "SELECT COUNT(*) FROM EHASV WHERE PID = '" + pid + "'" ;

			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("COUNT(*)");
				if(capa > 0) {
					System.out.println("Maxium number of Car reached.");
					return;
				}
			}
			
			String QV = "SELECT COUNT(*) FROM EHASV WHERE PLATE = '" + vid + "'" ;

			rs = statement.executeQuery(QV);
			if (rs.next()) {
				int capaV = rs.getInt("COUNT(*)");
				if(capaV > 0) {
					System.out.println("Car had already been added.");
					return;
				}
			}

			String Query = "\nINSERT INTO EHASV VALUES('" + pid + "'," + "'" + vid + "')";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking Permit " + pid + " successfully assigned car " + vid);
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void assignNEPermit(String plate, int uid, String zone, String vtype, String sdate) {
		// TODO Auto-generated method stub
		try {
			String Q = "SELECT COUNT(*) FROM NEPERMITS";

			rs = statement.executeQuery(Q);
			int capa = 0;
			if (rs.next()) {
				capa = rs.getInt("COUNT(*)");

			}
			LocalDate date = LocalDate.parse(sdate);
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		      
		    sdate = date.format(myFormatObj);
		    String edate = date.plusMonths(4).format(myFormatObj); 
			String hex = Integer.toHexString(capa);
			String eid = "20" + zone;
			int zs = 8 - eid.length() - hex.length();
			for (int i = 0; i < zs; i++) {
				eid = eid + 0;
			}
			eid = eid + hex;
			String Query = "\nINSERT INTO EPERMITS VALUES('" + eid + "'," + "'" + plate + "'," + "'" + zone + "'," + "'" + vtype + "',"  + "'" + sdate + "'," + "'" + edate + "',0,0,23,59," + uid + ")";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking Permit for " + uid + " successfully assigned!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void assignEPermit(String plate, int uid, String zone, String vtype, String sdate) {
		try {
			String Q = "SELECT COUNT(*) FROM EPERMITS";

			rs = statement.executeQuery(Q);
			int capa = 0;
			if (rs.next()) {
				capa = rs.getInt("COUNT(*)");

			}
			LocalDate date = LocalDate.parse(sdate);
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		      
		    sdate = date.format(myFormatObj);
		    String edate = date.plusDays(364).format(myFormatObj); 
			String hex = Integer.toHexString(capa);
			String eid = "20" + zone;
			int zs = 8 - eid.length() - hex.length();
			for (int i = 0; i < zs; i++) {
				eid = eid + 0;
			}
			eid = eid + hex;
			String Query = "\nINSERT INTO EPERMITS VALUES('" + eid + "'," + "'" + plate + "'," + "'" + zone + "'," + "'" + vtype + "',"  + "'" + sdate + "'," + "'" + edate + "',0,0,23,59," + uid + ")";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking Permit for " + uid + " successfully assigned!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void addVehicle(String pid, String mfc, String mdl, int vy, String vc) {
		try {
			String Query = "\nINSERT INTO vehicles VALUES('" + pid + "'," + "'" + mfc + "'," + "'" + mdl + "'," + vy + ",'" + vc + "')\n";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Vehicle " + pid + " successfully added!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void assignSpace(String lname, int snumber, String stype, String szone) {
		try {
			String Q = "SELECT TSPACE FROM LOTS WHERE LNAME = '" + lname + "'";
			System.out.println(Q);
			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("TSPACE");
				if (capa < snumber) {
					System.out.println("Space Number exceeds limit!");
					return;
				}
			}

			String Query = "\nINSERT INTO SPACES VALUES('" + lname + "'," + snumber + ",'" + stype + "'," + "'" + szone
					+ "', 0)\n";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking space " + lname + "#" + snumber + " successfully assigned!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void addZone(String zid) {
		try {
			String Query = "\nINSERT INTO ZONES VALUES('" + zid + "')\n";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking ZONE " + zid + " successfully created!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void zoneToLot(String lname, String zid) {
		try {
			String Query = "\nINSERT INTO LHASZ VALUES('" + zid + "'" + ",'" + lname + "')\n";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking Lot " + lname + " successfully assigned Zone " + zid + "!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void addlot(String lname, String ladd, int ls, int lv, String lnotes) {
		try {
			String Query = "\nINSERT INTO LOTS VALUES('" + lname + "'" + ",'" + ladd + "'," + ls + "," + lv + ",'"
					+ lnotes + "')\n";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Parking Lot " + lname + " successfully added.");
			System.out.println("----------------------------");
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
			final List<String> table = SQLFileCache.getInstance().getQueries("table.sql");
			executeSQL(table, con);

			rs = statement.executeQuery("SELECT table_name FROM user_tables");
			while (rs.next()) {
				String s = rs.getString("table_name");
				System.out.println(s + " has been created!");
			}
			final List<String> value = SQLFileCache.getInstance().getQueries("value.sql");
			executeSQL(value, con);
			System.out.println("Value inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void executeSQL(final List<String> queries, Connection conn) throws SQLException, IOException {

		for (final String sql : queries) {
			final Statement stmt = conn.createStatement();
			try {
				stmt.execute(sql);
			} catch (final SQLException e) {
				throw new SQLException(e.getMessage() + " from executing: " + sql, e.getSQLState(), e.getErrorCode());
			} finally {
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
			// statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");

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
			// statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}
}
