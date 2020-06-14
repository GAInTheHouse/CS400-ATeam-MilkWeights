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

import java.util.ArrayList;
import java.util.zip.DataFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to generate the different types of reports as specified by the Milk
 * Weights Assignment
 * 
 * @author Pragyan Das
 *
 */
public class Report {

	/**
	 * Makes all of the entries that make up the Farm Report.
	 * 
	 * @param farmID the ID of the farm we want to analyze
	 * @param year   the year to look at
	 * @return a list object that can be used with TableView
	 * @throws KeyNotFoundException    - Key not found in the FarmSociety
	 * @throws IllegalNullKeyException - Key is illegal
	 */
	public ObservableList<FarmReport> getFarmReports(String farmID, String year, FarmSociety fm)
			throws IllegalNullKeyException, KeyNotFoundException {
		ObservableList<FarmReport> months = FXCollections.observableArrayList();
		farmID = farmID + year;
		System.out.println(farmID);
		// month names
		String[] mthNames = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "Decemeber" };
		// getting the monthly weights and the annual weight to calculate the weight
		// percent
		int[] monthlyWeights = new int[12];
		// getting the annual data
		int[][] annualData = fm.get(farmID);
		int totalMonthWeight = 0;
		int annualWeight = 0;
		for (int i = 0; i < 12; i++) {
			for (int k = 0; k < 31; k++) {
				if (annualData[i][k] != -1) {
					totalMonthWeight = totalMonthWeight + annualData[i][k];

				}
				annualWeight = annualWeight + totalMonthWeight;
				monthlyWeights[i] = monthlyWeights[i] + totalMonthWeight;
				totalMonthWeight = 0;
			}
		}
		double weightPercent;
		// making the reports
		for (int i = 0; i < 12; i++) {
			weightPercent = Math.round(((double) monthlyWeights[i] / (double) annualWeight) * 100);
			FarmReport fr = new FarmReport(mthNames[i], monthlyWeights[i], weightPercent);
			months.add(fr);

		}

		return months;
	}

	/**
	 * Makes all of the entries that make up the Annual Report.
	 * 
	 * @param year the year to look at
	 * @return a list object that can be used with TableView
	 * @throws KeyNotFoundException    - Key is not found in the FarmSociety
	 * @throws IllegalNullKeyException - Key is illegal
	 */
	public static ObservableList<FarmReport> getAnnualReports(String year, FarmSociety fm)
			throws IllegalNullKeyException, KeyNotFoundException {
		ObservableList<FarmReport> farms = FXCollections.observableArrayList();
		// getting all the keys from the farmsociety to parse through them
		ArrayList<String> keyList = fm.getKey();
		ArrayList<Integer> weightList = new ArrayList<Integer>();
		ArrayList<String> includedKeyList = new ArrayList<String>();
		int[][] annualData;
		int annualWeight = 0;
		int totalAnnualWeight = 0;
		// finding all the keys matching the year and adding them to the includedKeyList
		// and weightList
		for (int i = 0; i < keyList.size(); i++) {
			if (keyList.get(i).substring(keyList.get(i).length() - 4, keyList.get(i).length()).equals(year)) {
				annualData = fm.get(keyList.get(i));
				for (int l = 0; l < 12; l++) {
					for (int k = 0; k < 31; k++) {
						if (annualData[l][k] != -1) {
							annualWeight = annualWeight + annualData[l][k];
						}
					}

				}
				weightList.add(annualWeight);
				includedKeyList.add(keyList.get(i));
				annualWeight = 0;

			}
		}
		int weight;
		double percent;
		int farmID;
		String key;
		// removing duplicate entries from the included lists
		ArrayList<Integer> dupecheckWeight = new ArrayList<Integer>();
		ArrayList<String> dupecheckKey = new ArrayList<String>();
		for (int i = 0; i < includedKeyList.size(); i++) {
			if (!dupecheckKey.contains(includedKeyList.get(i))) {
				dupecheckKey.add(includedKeyList.get(i));
				dupecheckWeight.add(weightList.get(i));

			}
		}
		// calculating the true annual weight(without duplicates)
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			totalAnnualWeight = totalAnnualWeight + dupecheckWeight.get(i);
		}
		// constructing the reports
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			weight = dupecheckWeight.get(i);
			key = dupecheckKey.get(i);
			farmID = Integer.parseInt(key.substring(5, key.length() - 4));
			percent = Math.round(((double) weight / (double) totalAnnualWeight) * 100);
			farms.add(new FarmReport(farmID, weight, percent));
		}
		return farms;

	}

	/**
	 * Makes all of the entries that make up the Date Range Report.
	 * 
	 * @param startDate starting date, inclusive, to analyze
	 * @param endDate   ending date, inclusive, to analyze
	 * @return a list object that can be used with TableView
	 * @throws KeyNotFoundException    - Key is not found in the FarmSociety
	 * @throws IllegalNullKeyException - Key is illegal
	 */
	public ObservableList<FarmReport> getDateRangeReports(String startDate, String endDate, FarmSociety fm)
			throws IllegalNullKeyException, KeyNotFoundException {
		ObservableList<FarmReport> farms = FXCollections.observableArrayList();
		// sentinel array to keep track of month numbers
		String[] mthNames = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "Decemeber" };
		// dividing the input
		int startDay = Integer.parseInt(startDate.substring(0, 2));
		int startMonth = Integer.parseInt(startDate.substring(2, 4));
		int startYear = Integer.parseInt(startDate.substring(4));
		int endDay = Integer.parseInt(endDate.substring(0, 2));
		int endMonth = Integer.parseInt(endDate.substring(2, 4));
		int endYear = Integer.parseInt(endDate.substring(4));
		boolean split = false;
		// deciding on which helper method to call
		if (endYear > startYear) {
			split = true;
		}
		// calling helper methods
		if (split) {
			farms = splitReport(startDay, startMonth, startYear, endDay, endMonth, endYear, fm);
		} else {
			farms = sameYearReport(startDay, startMonth, startYear, endDay, endMonth, fm);
		}
		return farms;
	}

	/**
	 * Helper method to calculate if two dates are on the same year
	 * 
	 * @param startDay   starting day
	 * @param startMonth starting month
	 * @param year       year of the transactions
	 * @param endDay     ending day
	 * @param endMonth   ending month
	 * @return a list object that can be used with TableView
	 * @throws KeyNotFoundException    - Key is not present in the FarmSociety
	 * @throws IllegalNullKeyException - Key is illegal
	 */
	private ObservableList<FarmReport> sameYearReport(int startDay, int startMonth, int year, int endDay, int endMonth,
			FarmSociety fm) throws IllegalNullKeyException, KeyNotFoundException {
		ObservableList<FarmReport> farms = FXCollections.observableArrayList();
		// getting keys from the farmsociety
		ArrayList<String> keyList = fm.getKey();
		ArrayList<Integer> weightList = new ArrayList<Integer>();
		ArrayList<String> includedKeyList = new ArrayList<String>();
		int[][] annualData;
		int rangeWeight = 0;
		int totalRangeWeight = 0;
		String strYear = Integer.toString(year);
		// parsing theough the keylist to find values matching the year
		for (int i = 0; i < keyList.size(); i++) {
			if (keyList.get(i).substring(keyList.get(i).length() - 4, keyList.get(i).length()).equals(strYear)) {
				annualData = fm.get(keyList.get(i));
				for (int l = startMonth - 1; l < endMonth; l++) {
					// loop for start month
					if (l == startMonth - 1) {
						for (int k = startDay - 1; k < 31; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}

						}
					} else
					// loop for last month
					if (l == endMonth - 1) {
						for (int k = 0; k < endDay; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}

						}
					} else {
						// loop for other months
						for (int k = 0; k < 31; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}
						}
					}

				}
				// adding to the appropriate lists
				weightList.add(rangeWeight);
				includedKeyList.add(keyList.get(i));
				rangeWeight = 0;

			}
		}
		int weight;
		double percent;
		int farmID;
		String key;
		// eliminating duplicates
		ArrayList<Integer> dupecheckWeight = new ArrayList<Integer>();
		ArrayList<String> dupecheckKey = new ArrayList<String>();
		for (int i = 0; i < includedKeyList.size(); i++) {
			if (!dupecheckKey.contains(includedKeyList.get(i))) {
				dupecheckKey.add(includedKeyList.get(i));
				dupecheckWeight.add(weightList.get(i));

			}
		}
		// calculating true totalWeight
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			totalRangeWeight = totalRangeWeight + dupecheckWeight.get(i);
		}
		// constructing the report
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			weight = dupecheckWeight.get(i);
			key = dupecheckKey.get(i);
			farmID = Integer.parseInt(key.substring(5, key.length() - 4));
			percent = Math.round(((double) weight / (double) totalRangeWeight) * 100);
			farms.add(new FarmReport(farmID, weight, percent));
		}
		return farms;
	}

	/**
	 * Helper method to calculate if two dates are on different years
	 * 
	 * @param startDay   starting day
	 * @param startMonth starting month
	 * @param startYear  starting year
	 * @param endDay     ending day
	 * @param endMonth   ending month
	 * @param endYear    ending year
	 * @return a list object that can be used with TableView
	 * @throws KeyNotFoundException    - Key is not present in the FarmSociety
	 * @throws IllegalNullKeyException - Key is illegal
	 */
	private ObservableList<FarmReport> splitReport(int startDay, int startMonth, int startYear, int endDay,
			int endMonth, int endYear, FarmSociety<String> fm) throws KeyNotFoundException, IllegalNullKeyException {
		ObservableList<FarmReport> farms = FXCollections.observableArrayList();
		// getting the keylist
		ArrayList<String> keyList = fm.getKey();
		ArrayList<Integer> weightList = new ArrayList<Integer>();
		ArrayList<String> includedKeyList = new ArrayList<String>();
		int[][] annualData;
		int rangeWeight = 0;
		int totalRangeWeight = 0;
		String strYear1 = Integer.toString(startYear);
		String strYear2 = Integer.toString(endYear);
		// loop for first year
		for (int i = 0; i < keyList.size(); i++) {
			if (keyList.get(i).substring(keyList.get(i).length() - 4).equals(strYear1)) {
				annualData = fm.get(keyList.get(i));
				for (int l = startMonth - 1; l < 12; l++) {
					// loop for start month
					if (l == startMonth - 1) {
						for (int k = startDay - 1; k < 31; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}

						}
					} else {
						// loop for other months
						for (int k = 0; k < 31; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}
						}
					}

				}
				// assigning appropriate values
				weightList.add(rangeWeight);
				includedKeyList.add(keyList.get(i));
				rangeWeight = 0;

			}
		}
		// loop for next year
		for (int i = 0; i < keyList.size(); i++) {
			if (keyList.get(i).substring(keyList.get(i).length() - 4, keyList.get(i).length()).equals(strYear2)) {
				annualData = fm.get(keyList.get(i));
				for (int l = 0; l < endMonth; l++) {
					// loop for end month
					if (l == endMonth - 1) {
						for (int k = 0; k < endDay; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}

						}
					} else {
						// loop for other months
						for (int k = 0; k < 31; k++) {
							if (annualData[l][k] != -1) {
								rangeWeight = rangeWeight + annualData[l][k];
							}
						}
					}

				}
				// assigning appropriate values
				weightList.add(rangeWeight);
				includedKeyList.add(keyList.get(i));
				rangeWeight = 0;

			}
		}
		int weight;
		double percent;
		int farmID;
		String key;
		// elminiating duplicates
		ArrayList<Integer> dupecheckWeight = new ArrayList<Integer>();
		ArrayList<String> dupecheckKey = new ArrayList<String>();
		for (int i = 0; i < includedKeyList.size(); i++) {
			if (!dupecheckKey.contains(includedKeyList.get(i))) {
				dupecheckKey.add(includedKeyList.get(i));
				dupecheckWeight.add(weightList.get(i));

			}
		}
		// calculating true weight
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			totalRangeWeight = totalRangeWeight + dupecheckWeight.get(i);
		}
		// constructing the reports
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			weight = dupecheckWeight.get(i);
			key = dupecheckKey.get(i);
			farmID = Integer.parseInt(key.substring(5, key.length() - 4));
			percent = Math.round(((double) weight / (double) totalRangeWeight) * 100);
			farms.add(new FarmReport(farmID, weight, percent));
		}
		return farms;

	}

	/**
	 * Makes all of the entries that make up the Monthly Report. For now, this is
	 * just hard coded data.
	 * 
	 * @param month the month to analyze
	 * @param year  the year to analyze
	 * @return a list object that can be used with TableView
	 * @throws DataFormatException     - Month is invalid
	 * @throws KeyNotFoundException    - Key is not present in the FarmSociety
	 * @throws IllegalNullKeyException - Key is illegal
	 */
	public ObservableList<FarmReport> getMonthlyReports(String month, String year, FarmSociety<String> fm)
			throws DataFormatException, IllegalNullKeyException, KeyNotFoundException {
		ObservableList<FarmReport> farms = FXCollections.observableArrayList();
		// helper array to determine month index
		String[] mthNames = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "Decemeber" };
		int monthIndex = -1;
		for (int i = 0; i < mthNames.length; i++) {
			if (month.equals(mthNames[i])) {
				monthIndex = i;
			}
		}
		// Input wasn't suitable
		if (monthIndex == -1) {
			throw new DataFormatException();
		}
		ArrayList<String> keyList = fm.getKey();
		ArrayList<Integer> weightList = new ArrayList<Integer>();
		ArrayList<String> includedKeyList = new ArrayList<String>();
		int[][] annualData;
		int monthWeight = 0;
		int totalMonthWeight = 0;
		// Adding appropriate elements to the arraylists
		for (int i = 0; i < keyList.size(); i++) {
			if (keyList.get(i).substring(keyList.get(i).length() - 4).equals(year)) {

				annualData = fm.get(keyList.get(i));
				for (int k = 0; k < 31; k++) {
					if (annualData[monthIndex][k] != -1) {
						monthWeight = monthWeight + annualData[monthIndex][k];
					}
				}
				weightList.add(monthWeight);
				includedKeyList.add(keyList.get(i));
				monthWeight = 0;

			}
		}

		int weight;
		double percent;
		int farmID;
		String key;
		// removing duplicates
		ArrayList<Integer> dupecheckWeight = new ArrayList<Integer>();
		ArrayList<String> dupecheckKey = new ArrayList<String>();
		for (int i = 0; i < includedKeyList.size(); i++) {
			if (!dupecheckKey.contains(includedKeyList.get(i))) {
				dupecheckKey.add(includedKeyList.get(i));
				dupecheckWeight.add(weightList.get(i));

			}
		}
		// calculating true weight
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			totalMonthWeight = totalMonthWeight + dupecheckWeight.get(i);
		}
		// constructing the reports
		for (int i = 0; i < dupecheckWeight.size(); i++) {
			weight = dupecheckWeight.get(i);
			key = dupecheckKey.get(i);
			farmID = Integer.parseInt(key.substring(5, key.length() - 4));
			percent = Math.round(((double) weight / (double) totalMonthWeight) * 100);
			farms.add(new FarmReport(farmID, weight, percent));
		}

		return farms;
	}

}