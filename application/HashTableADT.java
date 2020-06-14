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


/**
 * Interface for farming community
 * 
 * @author GAUTAM AGARWAL
 *
 */
public interface HashTableADT<K extends Comparable<K>> {

  /**
   * Insert: 
   * 1. new farm,and loads one data entry into it and increase the size; or 
   * 2. One new data entry for a pre-existing farm; or 
   * 3. Rewrite pre-existing data entry for a pre-existing farm
   * 
   * @param key   Farm to be inserted
   * @param value Value to be inserted
   * @param month Month of the data to be inserted
   * @param day   Day of the data to be inserted
   * @throws IllegalNullKeyException If key is null
   */
  void insert(K key, int value, int month, int day) throws IllegalNullKeyException;

  /**
   * Removes: 
   * 1. A particular data set for a particular farm from the hash table 
   * 2. If month and day are passed -1, removes the entire farm
   * 
   * @param key:  key of node to be deleted
   * 
   * @param month Month of the data to be removed
   * @param day   Day of the data to be removed
   * @return boolean true if deleted successfully, else false
   * @throws IllegalNullKeyException: If Key is null
   */
  boolean remove(K key, int month, int day) throws IllegalNullKeyException;

  /**
   * Return the entire annual data of the farm
   * 
   * @param key of the node to obtain value from
   * @return the value associated with the specified key.
   *
   * @throws IllegalNullKeyException If key is null
   * @throws KeyNotFoundException()  key is not found
   */
  int[][] get(K key) throws IllegalNullKeyException, KeyNotFoundException;

  // Returns the number of key,value pairs in the data structure
  int numKeys();


  /**
   * @return a list of keys in the hashtable
   */
  public ArrayList<K> getKey();

  // Returns the load factor threshold that was
  // passed into the constructor when creating
  // the instance of the HashTable.
  // When the current load factor is greater than or
  // equal to the specified load factor threshold,
  // the table is resized and elements are rehashed.
  public double getLoadFactorThreshold();

  // Returns the current load factor for this hash table
  // load factor = number of items / current table size
  public double getLoadFactor();

  // Return the current Capacity (table size)
  // of the hash table array.
  //
  // The initial capacity must be a positive integer, 1 or greater
  // and is specified in the constructor.
  //
  // REQUIRED: When the load factor threshold is reached,
  // the capacity must increase to: 2 * capacity + 1
  //
  // Once increased, the capacity never decreases
  public int getCapacity();

}
