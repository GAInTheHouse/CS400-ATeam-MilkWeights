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

import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class test {

  @Test
  public void test000_collision_scheme()
      throws NumberFormatException, IllegalNullKeyException, IOException {

    File csv = new File("/Users/shreyanssaraogi/Downloads/csv/small/2019-1.csv");

    FarmSociety fm = new FarmSociety();


    fm = Input.reading(csv, fm);

    System.out.println(fm.numKeys());


    assert (fm.numKeys() == 3);
  }
}
