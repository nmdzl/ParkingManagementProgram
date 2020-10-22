
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
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;

public class parking {

	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	private static Connection con = null;
	private static Statement statement = null;
	private static ResultSet rs = null;
	private static Scanner in;

	public static void main(String[] args) throws SQLException {
		try {
			connectDB();
			in = new Scanner(System.in);
			String s = "";
			System.out.println("Wanna Drop All Tables?(Y/N)");
			s = in.nextLine();
			if (s.equalsIgnoreCase("Y")) {
				dropAllTables();
			}

			setup();
			// menu

			boolean key = true;

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
										System.out.println(
												"\nPlease enter the UnivID of the Permit(between 1000000 - 9999999):");
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
									assignEPermit(plate, uid, zone, vtype, sdate);
								}
								if (s1.equals("2")) {
									System.out.println("\n--------------------ASSIGN NEPERMIT--------------------\n");

									int uid = 0;
									while (uid < 1000000 || uid > 9999999) {
										System.out.println(
												"\nPlease enter the UnivID of the Permit(between 1000000 - 9999999):");
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
									assignNEPermit(plate, uid, zone, vtype, sdate);
								}
								if (s1.equals("3")) {
									System.out.println(
											"\n--------------------ADD VEHICLE TO EPERMITS--------------------\n");
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
									LocalDateTime mydate = LocalDateTime.now();
									while (vy <= 1900 || vy >= mydate.getYear()) {
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

									addVehicle(pid, mfc, mdl, vy, vc);

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

							if (checkVV(lname, snumber, plate)) {
								System.out.println("Valid Visitor Parking!");
							} else {
								System.out.println("Invalid Visitor Parking!");
							}
						}
						if (s1.equals("6")) {
							System.out.println("\n--------------------CHECK NVPARKING--------------------\n");
							System.out.println("\nPlease enter the name of the parking lot");
							String lname = in.nextLine();

							System.out.println("\nPlease enter the plate of the vehicle");
							String plate = in.nextLine();

							if (checkNVV(lname, plate)) {
								System.out.println("Valid Parking!");
							} else {
								System.out.println("Invalid Parking!");
							}
						}
						if (s1.equals("7")) {
							System.out.println("\n--------------------ISSUE CIATATION--------------------\n");
							System.out.println("\nPlease enter the plate of the vehicle");
							String plate = in.nextLine();
							System.out.println("\nPlease enter the Model of the vehicle");
							String model = in.nextLine();
							System.out.println("\nPlease enter the Color of the vehicle");
							String color = in.nextLine();
							System.out.println("\nPlease enter the name of the parking Lot");
							String lname = in.nextLine();
							System.out.println("\nPlease enter the category of the citation");
							String cat = in.nextLine();

							addCitation(plate, model, color, lname, cat);
						}
					}
				}

				// emp or student
				if (s.equals("1") || s.equals("2")) {
					boolean k = true;
					String s1 = "";
					while (k) {
						System.out.println("\n--------------------HELLO User!--------------------\n");
						System.out.println(" 0 - Enter Lot");
						System.out.println(" 1 - Exit Lot");
						System.out.println(" 2 - Pay Citation");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("0")) {
							Enterlot();
						}
						if (s1.equals("1")) {
							Exitlot();
						}
						if (s1.equals("2")) {
							Paycitation();
						}
						if (s1.equals("m")) {
							k = false;
						}
					}
				}

				// student
//	            if(s.equals("2")) {
//	    	        boolean k = true;
//	    	        String s1 = "";
//	    	        while (key) {
//	    	        	System.out.println("\n--------------------HELLO STUDENT!--------------------\n");
//	    	            System.out.println(" m - Return to Main Menu");
//	    	            try {
//	    	            	System.out.println("\nEnter number to perform actions: ");
//	    	            	s1 = in.nextLine();
//	    	            } catch (Exception InputMismatchException ) {
//	    	                System.out.println("Invaild input, please try again");
//	    	            }
//	    	            if(s1.equals("m")) {
//	    	            	k = false;
//	    	            	break;
//	    	            }
//	    	        }
//	            }

				// visitor
				if (s.equals("3")) {
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
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("0")) {
							try {
								LocalDateTime mydate = LocalDateTime.now();
								System.out.println("\n Please enter Plate of Car:");
								String plate = in.nextLine();
								rs = statement.executeQuery("select * from VEHICLES where plate='" + plate + "'");
								if (!rs.next()) {
									
									System.out.println(" This plate hasn't enrolled, please provide more information.");
									System.out.println("\nPlease enter the plate# of the vehicle");
									String pid = in.nextLine();
									System.out.println("\nPlease enter the Manufacture of the vehicle");
									String mfc = in.nextLine();
									System.out.println("\nPlease enter the Model of the vehicle");
									String mdl = in.nextLine();
									int vy = 0;
									while (vy <= 1900 || vy >= mydate.getYear()) {	
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

									addVehicle(pid, mfc, mdl, vy, vc);
								}
								System.out.println("\n Please enter Name of Lot:");
								String lname = in.nextLine();
								String Q = "SELECT TSPACE FROM LOTS WHERE LNAME = '" + lname + "'";
								System.out.println(Q);
								String hex = "";
								int la = 0;
								rs = statement.executeQuery(Q);
								if (rs.next()) {
									la = rs.getInt("TSPACE");
									System.out.println("\nTotal Spaces: " + la);
								}
								else {
									System.out.println("No such Parking Lot!");
									throw new NullPointerException();
								}
								String QV = "SELECT COUNT(*) FROM VPERMITS";
								rs = statement.executeQuery(QV);
								if (rs.next()) {
									int capa = rs.getInt("COUNT(*)");
									hex = Integer.toHexString(capa);
								}
								int snumber = 0;
								while (snumber <= 0 ||snumber > la) {
									System.out.println("\nPlease enter the # of the parking space");
									String st = in.nextLine();
									try {
										snumber = Integer.valueOf(st);
									} catch (Exception e) {
										System.out.println("Invalid input");
									}
								}
								
								try {
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								System.out.println("\n Please enter duration of reservation:");
								Integer duration = in.nextInt();
								in.nextLine();
								System.out.println("\n Please enter parking type:");
								String type = in.nextLine();
								
								DateTimeFormatter myformat = DateTimeFormatter.ofPattern("dd-MMM-yy");
								String date = mydate.format(myformat);
								int hour = mydate.getHour();
								int minute = mydate.getMinute();
								int expirehour = hour + duration;
								int year = mydate.getYear()-2000;
								String vpid = year + "V";

								int n = 8 - 3 - hex.length();
								for (int i = 0; i < n; i++) {
									vpid = vpid + "0";
								}
								vpid = vpid + hex;
								String sy = "insert into vpermits values('" + vpid + "'" + ",'" + plate
										+ "','V','" + type + "','" + date + "','" + date + "'," + hour + "," + minute
										+ "," + expirehour + "," + minute + "," + duration + "," + snumber + ",'"
										+ lname + "')";
								System.out.println(sy);
								statement.executeUpdate("insert into Spaces values('" + lname + "'," + snumber + ",'"
										+ type + "','V',1)");
								statement.executeUpdate("insert into vpermits values('" + vpid + "'" + ",'" + plate
										+ "','V','" + type + "','" + date + "','" + date + "'," + hour + "," + minute
										+ "," + expirehour + "," + minute + "," + duration + "," + snumber + ",'"
										+ lname + "')");
								System.out.println("Visitor Permit " + vpid + " has been successfully assigned!");
								// need to update about Start date and time, calculate the end time and date.
								// 2.Generate pid & spacenum.
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
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (s1.equals("1")) {
							Exitlot();
						}
						if (s1.equals("2")) {
							Paycitation();
						}
						if (s1.equals("m")) {
							break;
						}
					}
				}

				// sample
				if (s.equals("4")) {
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO SAMPLE!--------------------\n");
						System.out.println(" 1 - Show the list of zones for each lot as tuple pairs");
						System.out.println(" 2 - Get permit information for a given employee with UnivID: 1006020");
						System.out.println(" 3 - Get vehicle information for a particular UnivID: 1006003");
						System.out.println(
								" 4 - Find an available space# for Visitor for an electric vehicle in a specific parking lot: Justice Lot");
						System.out.println(" 5 - Find any cars that are currently in violation");
						System.out.println(" 6 - How many employees have permits for parking zone D");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("1")) {
							showzone();
						}
						if (s1.equals("2")) {
							perminfo();
						}
						if (s1.equals("3")) {
							vehinfo();
						}
						if (s1.equals("4")) {
							availspace();
						}
						if (s1.equals("5")) {
							viocar();
						}
						if (s1.equals("6")) {
							empno();
						}
						if (s1.equals("m")) {
							break;
						}
					}
				}

				// report
				if (s.equals("5")) {
					String s1 = "";
					while (key) {
						System.out.println("\n--------------------HELLO REPORT!--------------------\n");
						System.out.println(
								" 1 - Report the number of citations in all lots for a three month period (07/01/2020 - 09/30/2020)");
						System.out.println(" 2 - Report the number of visitor permits of different permit type");
						System.out.println(" 3 - Report the total revenue generated for all visitor's parking zones");
						System.out.println(" m - Return to Main Menu");
						try {
							System.out.println("\nEnter number to perform actions: ");
							s1 = in.nextLine();
						} catch (Exception InputMismatchException) {
							System.out.println("Invaild input, please try again");
						}
						if (s1.equals("1")) {
							citationNumberInAllLots();
						}
						if (s1.equals("2")) {
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
						if (s1.equals("3")) {
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
							} catch (Exception e) {
								System.out.println("\nInvalid input: " + year_month);
								continue;
							}
							switch (month) {
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
						if (s1.equals("m")) {
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
		} finally {
			// close file reader
			in.close();
			// close db connection
			if (con != null) {
				con.close();
			}
		}
	}

	private static void addCitation(String plate, String model, String color, String lname, String cat) {
		// TODO Auto-generated method stub
		try {
			String Q = "SELECT COUNT(*) FROM CITATION";

			rs = statement.executeQuery(Q);
			int capa = 0;
			if (rs.next()) {
				capa = rs.getInt("COUNT(*)");
			}
			String cid = "1";
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			int chh = date.getHour();
			int cmm = date.getMinute();
			String sdate = date.format(myFormatObj);
			String ddate = date.plusMonths(1).format(myFormatObj);
			String hex = Integer.toHexString(capa);
			int zs = 4 - hex.length();
			for (int i = 0; i < zs; i++) {
				cid = cid + 0;
			}
			int fee = 20;
			if (cat.equals("Invalid Permit")) {
				fee = 20;
			} else if (cat.equals("Expired Permit")) {
				fee = 25;
			} else if (cat.equals("No Permit")) {

			}
			cid = cid + hex;
			String Query = "\nINSERT INTO CITATION VALUES('" + cid + "'," + "'" + plate + "'," + "'" + model + "',"
					+ "'" + color + "'," + "'" + sdate + "'," + "'" + lname + "'," + chh + "," + cmm + ",'" + cat + "',"
					+ fee + ",'" + ddate + "'," + "'Unpaid" + "')";
			System.out.println(Query);
			rs = statement.executeQuery(Query);
			System.out.println("Citation for " + plate + " successfully assigned!");
			System.out.println("----------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static boolean checkNVV(String lname, String plate) {
		// TODO Auto-generated method stub
		try {
			String Q = "SELECT COUNT(*) FROM EHASV WHERE PLATE = '" + plate + "'";

			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("COUNT(*)");
				if (capa > 0) {

					return true;
				}
			}

			Q = "SELECT COUNT(*) FROM EPERMITS WHERE PLATE = '" + plate + "'";

			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("COUNT(*)");
				if (capa > 0) {
					return true;
				}
			}

			String QV = "SELECT * FROM NEPERMITS WHERE PVNUMBER = '" + plate + "'";

			rs = statement.executeQuery(QV);
			LocalDateTime late = null;
			int hour = 0;
			int minute = 0;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			while (rs.next()) {
				String exp = rs.getString("ENDDATE");
				hour = rs.getInt("EXPHOUR");
				minute = rs.getInt("EXPMINUTE");

				LocalDateTime t = LocalDateTime.parse(exp, formatter);
				if (late != null) {
					if (late.compareTo(t) < 0) {
						late = t;
					}
				} else {
					late = t;
				}
			}
			late = late.plusHours(hour).plusMinutes(minute);
			LocalDateTime current = LocalDateTime.now();

			if (current.compareTo(late) < 0) {
				System.out.println("Expired Permit");
				return false;
			} else
				return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("No Permit");
		return false;
	}

	private static boolean checkVV(String lname, int snumber, String plate) {
		// TODO Auto-generated method stub
		try {
			String Q = "SELECT CATEGORY FROM SPACES WHERE LOT = '" + lname + "' AND sid = " + snumber;
			rs = statement.executeQuery(Q);
			if (rs.next()) {
				String capa = rs.getString("CATEGORY");
				if (!capa.equals("V")) {
					System.out.println("Invalid Permit");
					return false;
				}
			} else {
				System.out.println("Invalid Permit");
				return false;
			}

			String QV = "SELECT * FROM VPERMITS WHERE PVNUMBER = '" + plate + "' AND LNAME ='" + lname + "'";

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
				if (late != null) {
					if (late.compareTo(t) < 0) {
						late = t;
					}
				} else {
					late = t;
				}
			}
			late = late.plusHours(hour).plusMinutes(minute);
			LocalDateTime current = LocalDateTime.now();

			if (current.compareTo(late) < 0) {
				System.out.println("Expired Permit");
				return false;
			} else
				return true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("No Permit");
		return false;
	}

	private static void addVtoE(String vid, String pid) {
		// TODO Auto-generated method stub
		try {
			String Q = "SELECT COUNT(*) FROM EHASV WHERE PID = '" + pid + "'";

			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("COUNT(*)");
				if (capa > 0) {
					System.out.println("Maxium number of Car reached.");
					return;
				}
			}

			String QV = "SELECT COUNT(*) FROM EHASV WHERE PLATE = '" + vid + "'";

			rs = statement.executeQuery(QV);
			if (rs.next()) {
				int capaV = rs.getInt("COUNT(*)");
				if (capaV > 0) {
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
			String Query = "\nINSERT INTO EPERMITS VALUES('" + eid + "'," + "'" + plate + "'," + "'" + zone + "'," + "'"
					+ vtype + "'," + "'" + sdate + "'," + "'" + edate + "',0,0,23,59," + uid + ")";
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
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yy");

			sdate = date.format(myFormatObj);
			String edate = date.plusDays(364).format(myFormatObj);
			String hex = Integer.toHexString(capa);
			String eid = date.getYear() + zone;
			int zs = 8 - eid.length() - hex.length();
			for (int i = 0; i < zs; i++) {
				eid = eid + 0;
			}
			eid = eid + hex;
			String Query = "\nINSERT INTO EPERMITS VALUES('" + eid + "'," + "'" + plate + "'," + "'" + zone + "'," + "'"
					+ vtype + "'," + "'" + sdate + "'," + "'" + edate + "',0,0,23,59," + uid + ")";
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
			String Query = "\nINSERT INTO vehicles VALUES('" + pid + "'," + "'" + mfc + "'," + "'" + mdl + "'," + vy
					+ ",'" + vc + "')\n";
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

	// Function about Visitor % University User
	private static void Paycitation() throws SQLException {
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

	private static void Enterlot() throws SQLException {
		String type;
		System.out.println("\n Please enter Name of Lot:");
		String lname = in.nextLine();
		System.out.println("\n Please enter Space Number:");
		String number = in.nextLine();
		try {
			String Q = "SELECT TSPACE FROM LOTS WHERE LNAME = '" + lname + "'";
			System.out.println(Q);
			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("TSPACE");
				if (capa < Integer.parseInt(number)) {
					System.out.println("Space Number exceeds limit!");
					return;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	String lname = "Premiere Lot";
//    	String number = "200";
		rs = statement.executeQuery("select * from SPACES where LOT='" + lname + "' AND sid =" + number);
		if (rs.next()) {
			type = rs.getString("category");
			int status = rs.getInt("sstatus");
			if(status > 0) {
				System.out.println("The space is occupied by others!");
				return;
			}
			statement.executeUpdate("UPDATE SPACES set sstatus = 1 WHERE LOT='" + lname + "' AND sid = " + number);
		} else {
			System.out.println("\n Please enter Category of Lot:");
			type = in.nextLine();
			statement.executeUpdate(
					"INSERT INTO SPACES values('" + lname + "'," + number + ",'Regular','" + type + "',1)");
		}
		if (type.equals("V"))
			statement.executeUpdate("update LOTS set VSPACE = VSPACE-1 where LNAME='" + lname + "'");
		else
			statement.executeUpdate("update LOTS set TSPACE = TSPACE-1 where LNAME='" + lname + "'");
	}

	private static void Exitlot() throws SQLException {
		String type;
		System.out.println("\n Please enter Name of Lot:");
		String lname = in.nextLine();
		System.out.println("\n Please enter Space Number:");
		String number = in.nextLine();
		try {
			String Q = "SELECT TSPACE FROM LOTS WHERE LNAME = '" + lname + "'";
			System.out.println(Q);
			rs = statement.executeQuery(Q);
			if (rs.next()) {
				int capa = rs.getInt("TSPACE");
				if (capa < Integer.parseInt(number)) {
					System.out.println("Space Number exceeds limit!");
					return;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	String lname = "Premiere Lot";
//    	String number = "200";
		String q = "select category from SPACES where LOT='" + lname + "' AND sid =" + number;
		System.out.println(q);
		rs = statement.executeQuery("select * from SPACES where LOT='" + lname + "' AND sid =" + number);
		
		if (rs.next()) {
			type = rs.getString("category");
			int status = rs.getInt("sstatus");
			if(status < 1) {
				System.out.println("The space is not occupied! Please enter a the correct space!");
				return;
			}
			statement.executeUpdate("UPDATE SPACES set sstatus = 0 WHERE LOT='" + lname + "' AND sid = " + number);
		} else {
			System.out.println("\n Please enter Category of Lot:");
			type = in.nextLine();
			statement.executeUpdate(
					"INSERT INTO SPACES values('" + lname + "'," + number + ",'Regular','" + type + "',0)");
		}
		if (type.equals("V"))
			statement.executeUpdate("update LOTS set VSPACE = VSPACE+1 where LNAME='" + lname + "'");
		else
			statement.executeUpdate("update LOTS set TSPACE = TSPACE+1 where LNAME='" + lname + "'");
	}

	//
	private static void calculateAllRevenue(String startDate, String endDate) throws SQLException {
		try {
			HashMap<Integer, Double> revenues = new HashMap<>();
			// fines involved with a visitor permit's plate number
			rs = statement.executeQuery(
					"SELECT CDATE, SUM(FEE) FROM Citation WHERE CDATE BETWEEN '" + startDate + "' AND '" + endDate
							+ "' AND (VCAT = 'No Permit' OR CARNO IN (SELECT pvnumber FROM vpermits)) GROUP BY CDATE");
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
			rs = statement.executeQuery(
					"SELECT pType, COUNT(*) FROM vpermits WHERE lname = '" + lot + "' AND startDate BETWEEN '"
							+ startDate + "' AND '" + endDate + "' GROUP BY pType ORDER BY pType");
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
			rs = statement.executeQuery(
					"SELECT LNAME, COUNT(*) FROM Citation WHERE CDATE >= '01-JUL-20' AND CDATE <= '30-SEP-20' GROUP BY LNAME ORDER BY LNAME");
			System.out.println("Lot Name | Number of Citations");
			System.out.println("---------------------------------");
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
			System.out.println("\nSELECT * FROM LHASZ\n");
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
			rs = statement.executeQuery(
					"SELECT EP.pid, H.plate, EP.zid, EP.pType, EP.startDate, EP.endDate, EP.startHour, EP.startMinute, EP.expHour, EP.expMinute "
							+ "FROM epermits EP, EhasV H " + "WHERE EP.univid = 1006020 AND EP.pid = H.pid");
			System.out.println("\nSELECT EP.pid, H.plate, EP.zid, EP.pType, EP.startDate, EP.endDate, EP.startHour, EP.startMinute, EP.expHour, EP.expMinute "
					+ "FROM epermits EP, EhasV H " + "WHERE EP.univid = 1006020 AND EP.pid = H.pid\n");
			System.out.println("UniqueID  CarLicense  ZoneID  SpaceType  StartTime  ExpTime");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------");
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
				System.out.println(
						pid + "   " + plate + "   " + zid + "   " + ptype + "   " + startTime + "   " + endTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void vehinfo() throws SQLException {
		try {
			rs = statement.executeQuery("SELECT V.plate, V.carManf, V.carModel, V.carYear, V.carColor "
					+ "FROM vehicles V, nepermits EP " + "WHERE EP.univid = 1006003 AND V.plate = EP.pvnumber");
			System.out.println("\nSELECT V.plate, V.carManf, V.carModel, V.carYear, V.carColor "
					+ "FROM vehicles V, nepermits EP " + "WHERE EP.univid = 1006003 AND V.plate = EP.pvnumber\n");
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
			rs = statement.executeQuery("SELECT sid " + "FROM SPACES "
					+ "WHERE LOT = 'Justice Lot' AND category = 'V' AND stype = 'Electric' AND sstatus = 0");
			System.out.println("\nSELECT sid " + "FROM SPACES "
					+ "WHERE LOT = 'Justice Lot' AND category = 'V' AND stype = 'Electric' AND sstatus = 0\n");
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
					+ "FROM Citation " + "WHERE STATUS = 'Unpaid'");
			System.out.println("\nSELECT CID, CARNO, CMODEL, COLOR, CDATE, LNAME, CHH, CMM, VCAT, FEE, DUE "
					+ "FROM Citation " + "WHERE STATUS = 'Unpaid'\n");
			System.out.println("UniqueCitation#  LicenseNumber  Model  Color  Time  Lot  ViolationCat  Fee  Due");
			System.out.println(
					"-----------------------------------------------------------------------------------------");
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

				DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime dDate = LocalDateTime.parse(due, formatter);

				System.out.println(cid + "   " + cno + "   " + cmod + "   " + co + "   " + cTime + "   " + ln + "   "
						+ vc + "   " + fee + "   " + dDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void empno() throws SQLException {
		try {
			rs = statement.executeQuery("SELECT COUNT(*) AS DNO " + "FROM epermits " + "WHERE zid = 'D'");
			System.out.println("\nSELECT COUNT(*) AS DNO " + "FROM epermits " + "WHERE zid = 'D'\n");
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

		} catch (Throwable err) {
			err.printStackTrace();
		}
	}
}
