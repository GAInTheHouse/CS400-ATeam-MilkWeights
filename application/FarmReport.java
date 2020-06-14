//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Final Project
// Files: HashTableADT.java, HashTable.java, FarmSociety.java, FarmReport.java, 
//        Main.java, Report.java, Input.java, test.java, IllegalNullKeyException.java, 
//        KeyNotFoundException.java, executable files, screenshots, batch file, manifest.txt,
//        README, design.pdf, Annual_Report.txt
// Course: CS400 / Spring 2020
// Lecture Number: 002
// Description: Making a milk community database
//
// Team: Gautam Agarwal,Shreyans Saraogi, Yash Himmatramka, Pragyan Das, Nick Mathew
// Email: gagarwal8@wisc.edu
// Lecturer's Name: Professor Debra Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

/**
 * FarmReport is an object that will be used to hold the pertinent data that is consistent
 * with the entry type that is needed for the report. This object has field that satisfy
 * both types of reports (either entry by farm or by month). Only the field that are 
 * pertinent to the type of report should be considered valid data and all other fields
 * should be considered invalid data. Each instance of this class is an entry in the report.
 * 
 * @author Nicholas Mathew
 *
 */
public class FarmReport {
	//Declare fields, these are all data points that could go on a table/report
	private String month;
	private int monthTotal;
	private int yearTotal;
	private int dateRangeTotal;
	private double percentOfMonthlyTotal;
	private double percentOfYearlyTotal;
	private double percentOfDateRangeTotal;
	private String farmID;

	/**
	 * Constructor for month sorted report formats. This is only used for Farm Reports
	 * 
	 * @param month the month
	 * @param weight the total milk weight for that month for this farm
	 * @param percent the milk weight for this month divided by the total
	 *  milk weight produced by this farm this year
	 */
	public FarmReport(String month, int weight, double percent) {
		this.month = month;
		this.farmID = "";
		this.monthTotal = weight;
		this.yearTotal = weight;
		this.dateRangeTotal = weight;
		this.percentOfMonthlyTotal = percent;
		this.percentOfYearlyTotal = percent;
		this.percentOfDateRangeTotal = percent;
	}

	/**
	 * Constructor for farm ID sorted entries. This is used for all reports except Farm Report
	 * @param farmID the ID of the farm we are looking at
	 * @param weight the milk weight produced by this farm within the dates requested
	 * @param percent weight/the total weight produced by all farms collectively within the same dates
	 */
	public FarmReport(int farmID, int weight, double percent) {
		this.farmID = Integer.toString(farmID);
		this.month = "";
		this.yearTotal = weight;
		this.monthTotal = weight;
		this.dateRangeTotal = weight;
		this.percentOfMonthlyTotal = percent;
		this.percentOfYearlyTotal = percent;
		this.percentOfDateRangeTotal = percent;
	}

	// getters and setters NOTE: DO NOT CHANGE HEADERS - they are following a naming convention used by TableView
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(int monthTotal) {
		this.monthTotal = monthTotal;
	}

	public double getPercentOfMonthlyTotal() {
		return percentOfMonthlyTotal;
	}

	public void setPercentOfMonthlyTotal(int percentOfMonthlyTotal) {
		this.percentOfMonthlyTotal = percentOfMonthlyTotal;
	}

	public double getPercentOfYearlyTotal() {
		return percentOfYearlyTotal;
	}

	public void setPercentOfTotal(int percentOfYearlyTotal) {
		this.percentOfYearlyTotal = percentOfYearlyTotal;
	}

	public String getFarmID() {
		return farmID;
	}

	public void setFarmID(String farmID) {
		this.farmID = farmID;
	}

	public int getDateRangeTotal() {
		return dateRangeTotal;
	}

	public void setDateRangeTotal(int dateRangeTotal) {
		this.dateRangeTotal = dateRangeTotal;
	}

	public double getPercentOfDateRangeTotal() {
		return percentOfDateRangeTotal;
	}

	public void setPercentOfDateRangeTotal(double percentOfDateRangeTotal) {
		this.percentOfDateRangeTotal = percentOfDateRangeTotal;
	}

	public int getYearTotal() {
		return yearTotal;
	}

	public void setYearTotal(int yearTotal) {
		this.yearTotal = yearTotal;
	}
	
	public String toString1() {
		return this.month + " " + Integer.toString(monthTotal) + " " + Double.toString(percentOfMonthlyTotal);
	}
	
	public String toString2() {
		return farmID + " " + Integer.toString(monthTotal) + " " + Double.toString(percentOfMonthlyTotal);
	}
	


}
