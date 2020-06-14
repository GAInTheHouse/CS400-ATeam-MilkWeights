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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * Perfroms several input output functions-
 * 
 * - Reading a csv file based on user input - Inputting only one 1 farm's data -
 * Creating an output text file to print report
 * 
 * @author shreyanssaraogi
 *
 */
public class Input {

	/**
	 * Reads CSV
	 * 
	 * @param name_input
	 * @throws IllegalNullKeyException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static FarmSociety reading(File selectedNewFarm, FarmSociety<String> fm)
			throws NumberFormatException, IllegalNullKeyException, IOException {
		// Scanner file_read = new Scanner(selectedNewFarm);

		BufferedReader fileReader = new BufferedReader(new FileReader(selectedNewFarm));
		String line = "";
		final String delimiter = ",";

		//Code for reading CSV
		fileReader.readLine();
		while ((line = fileReader.readLine()) != null) {
			String[] data = line.split(delimiter);
			String[] date = data[0].split("-");
			if (!data[1].contains("Farm "))
				throw new NumberFormatException();
			int year = Integer.parseInt(date[0]);
			int weight = Integer.parseInt(data[2]);
			String farm = data[1] + date[0];
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			fm.insert(farm, weight, month, day);
		}

		fileReader.close();

		System.out.println(" Input: " + fm.numKeys());
		return fm;
	}

	/**
	 * Adds data to a farm society and returns the object.
	 * 
	 * @param farmId- The farm id
	 * @param farmData- The farm data to be added
	 * @param fm- the farm society to be added into
	 * @throws IllegalNullKeyException
	 * @throws NumberFormatException
	 * @return- the edited farm society
	 */
	public static FarmSociety inputOnlyOne(String farmId, String farmData, FarmSociety fm, String month, String day)
			throws NumberFormatException, IllegalNullKeyException {

		fm.insert(farmId, Integer.parseInt(farmData), Integer.parseInt(month), Integer.parseInt(day));
		return fm;
	}
	
	/**
	 * Code for creating output file
	 * @param report - The observable list
	 * @param outputName - The output report type
	 * @return
	 */
	public static FarmSociety outputTxt(ObservableList<FarmReport> report, String outputName) {

		ArrayList<FarmReport> reportsTxt = new ArrayList<FarmReport>(report);
		System.out.println(reportsTxt.size());
		outputName = outputName + ".txt";

		PrintWriter myWriter = null;
		try {
			myWriter = new PrintWriter(outputName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (outputName.contentEquals("FarmReport.txt")) {
			myWriter.println("Month - Weight - Percentage");
			for (int i = 0; i < reportsTxt.size(); i++) {
				myWriter.println(reportsTxt.get(i).toString1());
			}
		}
		if (outputName.contentEquals("Annual_Report.txt")) {
			myWriter.println("Farm ID - Weight - Percentage");
			for (int i = 0; i < reportsTxt.size(); i++) {
				System.out.println("Hi");
				myWriter.println(reportsTxt.get(i).toString2());
			}
		}
		if (outputName.contentEquals("Monthly_Report.txt")) {
			myWriter.println("Farm ID - Weight - Percentage");
			for (int i = 0; i < reportsTxt.size(); i++) {
				myWriter.println(reportsTxt.get(i).toString2());
			}
		}
		if (outputName.contentEquals("DateRangeReportList.txt")) {
			myWriter.println("Farm ID - Weight - Percentage");
			for (int i = 0; i < reportsTxt.size(); i++) {
				myWriter.println(reportsTxt.get(i).toString2());
			}
		}

		myWriter.close();

		return null;

	}
}
