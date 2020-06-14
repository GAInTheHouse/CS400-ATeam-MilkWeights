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
 * Data storage structure for farm community
 * 
 * Implements a hashtable with the help of Java's hash code function Computes
 * the index and adds it to an array of linked nodes as a node Element to be
 * added to the same index is added at the index and the previous element is
 * linked to it in a linked list fashion
 * 
 * Collision Resolution Scheme: CHAINED BUCKET: array of linked nodes
 * 
 * @author GAUTAM AGARWAL
 *
 */
public class FarmSociety<K extends Comparable<K>> implements HashTableADT<K> {

	/**
	 * Building block of the Hashtable Stores the annual data for a particular farm
	 * 
	 * @author Gautam
	 *
	 */
	private class FarmAnnual<K> {

		private K key; // Key with which node is identified
						// If a farm with id 1 stores its data for year 2018,
						// key is "Farm 1 2018"
		private int[][] data; // Annual data of milk weights for the farm with the particular ID and
								// year
								// If data for a particular day is not available, stored as -1
		private FarmAnnual<K> next; // Reference to the next node

		/**
		 * Constructor for an empty Node
		 */
		private FarmAnnual() {
			data = new int[12][31];
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 31; j++)
					data[i][j] = -1;
			}
			key = null;
			next = null;
		}

		/**
		 * Getter for data field.
		 * 
		 * @return The annual milk data of this farm
		 */
		private int[][] getData() {
			return data;
		}

		/**
		 * Getter for the key.
		 * 
		 * @return The key of this Node
		 */
		private K getKey() {
			return key;
		}

		/**
		 * Getter for next field.
		 * 
		 * @return The next of this Node
		 */
		private FarmAnnual<K> getNext() {
			return next;
		}

		/**
		 * Setter for data field
		 * 
		 * @param V, the V that the data field will be set to
		 */
		private void setData(int[][] volume) {
			data = volume;
		}

		/**
		 * Setter for data field
		 * 
		 * @param V, the V that the data field will be set to
		 */
		private void setData(int volume, int month, int day) {
			data[month - 1][day - 1] = volume;
		}

		/**
		 * Setter for key field
		 * 
		 * @param K, the key of the node
		 */
		private void setKey(K K) {
			key = K;
		}

		/**
		 * Setter for next field
		 * 
		 * @param node, the next Node
		 */
		private void setNext(FarmAnnual<K> node) {
			next = node;
		}

		/**
		 * Adds this node to the parametric node
		 * 
		 * @param base- Node to which this node is added
		 * @return- The reference to the linked chain of the two nodes
		 */
		private FarmAnnual<K> add(FarmAnnual<K> base) {

			// Adding the node and returning the new reference
			base.setNext(this);
			return base;
		}

	}

	private int capacity; // Current capacity of table
	private double loadFactorThreshold; // load factor that causes a resize and rehash
	private int size; // Current Size of the table (!= no. of farms)
	private FarmAnnual<K>[] hashtable; // Array to store node for Hashtable
	private ArrayList<K> keyList;

	/**
	 * Default constructor calls parametarised constructor for some arbitrary values
	 */
	public FarmSociety() {
		this(100, 0.7);
	}

	/**
	 * Accepts initial capacity and load factor threshold
	 * 
	 * @param initialCapacity     : Initial capacity of the table
	 * @param loadFactorThreshold : Load factor that causes resize and rehash
	 */
	public FarmSociety(int initialCapacity, double loadFactorThreshold) {
		capacity = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		size = 0;
		hashtable = new FarmAnnual[capacity];
		keyList = new ArrayList<K>();

		// Initializing all the array indexes to be null
		for (int i = 0; i < capacity; i++)
			hashtable[i] = null;
	}

	/**
	 * Insert: 1. new farm,and loads one data entry into it and increase the size;
	 * or 2. One new data entry for a pre-existing farm; or 3. Rewrite pre-existing
	 * data entry for a pre-existing farm
	 * 
	 * @param key   Farm to be inserted
	 * @param value Value to be inserted
	 * @param month Month of the data to be inserted
	 * @param day   Day of the data to be inserted
	 * @throws IllegalNullKeyException If key is null
	 */
	@Override
	public void insert(K key, int value, int month, int day) throws IllegalNullKeyException {
		// If key is null
		if (key == null)
			throw new IllegalNullKeyException("Key is null.");

		// Resizing is done if Load Factor Threshold is crossed
		if (getLoadFactor() >= loadFactorThreshold) {
			resize();
		}

		// Create the node to be inserted
		FarmAnnual<K> pair = new FarmAnnual<K>();
		pair.setData(value, month, day);
		pair.setKey(key);

		// Computing the hash index
		int index = Math.abs(key.hashCode() % capacity);

		// Adding a new node if the table index doesn't have a node
		if (hashtable[index] == null) {
			hashtable[index] = pair;
			size++;
		} else {

			FarmAnnual<K> temp = hashtable[index];

			// Checking if the key is already present, & changing its the value
			while (temp != null) {
				if (temp.key.compareTo(key) == 0) {
					temp.setData(value, month, day);
					keyList.add(key);
					return;
				}
				temp = temp.getNext();
			}

			// Adding a new node, if key is not found

			hashtable[index] = hashtable[index].add(pair);
			keyList.add(key);

			size++;
		}
	}

	/**
	 * resize and rehash all nodes in the table if load factor threshold is crossed
	 */
	private void resize() {
		// Getting a new capacity and a new array
		int cap = 2 * capacity + 1;
		FarmAnnual<K>[] copy = new FarmAnnual[cap];

		// Traversing through the original array
		for (int i = 0; i < capacity; i++) {

			FarmAnnual<K> current = hashtable[i];

			// Traversing through every node in the linked node of the array
			while (current != null) {

				// Getting the key & making its new index
				K key = current.getKey();
				int index = Math.abs(key.hashCode() % cap);

				// Making the node to be entered
				FarmAnnual<K> pair = new FarmAnnual<K>();
				pair.setData(current.getData());
				pair.setKey(key);

				// Checking if the position is null- then simply adding a node over there
				if (copy[index] == null) {
					copy[index] = pair;
				} else {
					// Using the add method from the node class to add a new node
					copy[index] = copy[index].add(pair);
				}
				current = current.getNext();
			}
		}

		// Update the capacity and table
		hashtable = copy;
		capacity = cap;

	}

	/**
	 * Removes: 1. A particular data set for a particular farm from the hash table
	 * 2. If month and day are passed -1, removes the entire farm
	 * 
	 * @param       key: key of node to be deleted
	 * 
	 * @param month Month of the data to be removed
	 * @param day   Day of the data to be removed
	 * @return boolean true if deleted successfully, else false
	 * @throws IllegalNullKeyException: If Key is null
	 */
	@Override
	public boolean remove(K key, int month, int day) throws IllegalNullKeyException {

		// If Key passed is null
		if (key == null)
			throw new IllegalNullKeyException("Key is null.");

		// If there are no keys to begin searching with
		if (numKeys() == 0)
			return false;

		// Computes hash index and obtains elements at that index of the table
		int index = Math.abs(key.hashCode() % capacity);
		FarmAnnual<K> current = hashtable[index];

		// Checks if index has nodes
		if (current == null)
			return false;

		// Checks if the key is in the only node in the index
		if (current.getKey().compareTo(key) == 0) {
			if (current.getNext() == null) {
				hashtable[index] = null;
				keyList.remove(key);
				size--;
				return true;
			}
		}
		try {
			hashtable[index] = removeHelp(current, key, month, day);
			return true;
		} catch (KeyNotFoundException e) {
			return false;
		}
	}

	/**
	 * Recursive helper method to delete.
	 * 
	 * @param       current, The node we are currently at.
	 * @param       key, the K to be deleted from the tree
	 * 
	 * @param month Month of the data to be removed
	 * @param day   Day of the data to be removed
	 * @return the root of the modified subtree we deleted from
	 * @throws KeyNotFoundException if the key is not in the tree
	 */
	private FarmAnnual<K> removeHelp(FarmAnnual current, K key, int month, int day) throws KeyNotFoundException {
		if (current != null) {
			if ((current.getNext().getKey()).equals(key)) {

				// Case when you remove the entire farm
				if (month == -1 || day == -1) {
					current.setNext(current.getNext().getNext());
					size--;
					keyList.remove(key);
					return current;
				} else {
					// Case when you remove a particular entry of the farm
					FarmAnnual next = current.getNext();
					next.setData(-1, month, day);
					current.setNext(next);
					return current;
				}
			}
			current.setNext(removeHelp(current.getNext(), key, month, day));
			return current;
		}
		throw new KeyNotFoundException("Key not found");
	}

	/**
	 * Return the entire annual data of the farm
	 * 
	 * @param key of the node to obtain value from
	 * @return the value associated with the specified key.
	 *
	 * @throws IllegalNullKeyException If key is null
	 * @throws KeyNotFoundException()  key is not found
	 */
	@Override
	public int[][] get(K key) throws IllegalNullKeyException, KeyNotFoundException {

		// If key is null
		if (key == null)
			throw new IllegalNullKeyException("Key is null.");

		// If there are no elements in the table to begin searching
		if (numKeys() == 0)
			throw new KeyNotFoundException("Key not found");

		// Computes hash index and obtains elements at that index of the table
		int index = (Math.abs(key.hashCode())) % capacity;
		FarmAnnual<K> current = hashtable[index];

		// Checks if index has nodes
		if (current == null) {
			throw new KeyNotFoundException();
		}

		// Traversing the linked nodes to find the node
		while (current != null) {
			if (current.getKey().compareTo(key) == 0) {
				return current.getData();
			}
			current = current.getNext();
		}

		// Throw exception if key is not found till the end
		throw new KeyNotFoundException("Key Not Found");

	}

	/**
	 * @return a list of keys in the hashtable
	 */
	public ArrayList<K> getKey() {
		return keyList;
	}

	/**
	 * @return number of key,value pairs in the data structure
	 */
	@Override
	public int numKeys() {
		return size;
	}

	/**
	 * @return the current loadFactorThreshold
	 */
	@Override
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	/**
	 * @return the current loadFactor
	 */
	@Override
	public double getLoadFactor() {
		return ((double) size) / ((double) capacity);
	}

	/**
	 * @return capacity of current hash table
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

}