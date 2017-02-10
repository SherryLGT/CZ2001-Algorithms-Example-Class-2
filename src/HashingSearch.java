import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class implements linear probing and double hashing.
 *
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 9/2/2017
 */

public class HashingSearch {
	
	private final static int TABLE_SIZE = 1000;
	
	@SuppressWarnings("resource")
	public static void main(String args[]) {

		Employee[] linearHashTable = new Employee[TABLE_SIZE];
		Employee[] doubleHashTable = new Employee[TABLE_SIZE];
		
		System.out.print("Enter size of data set: ");
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());

		// Generate dataset
		EmployeeController controller = new EmployeeController();
		Employee[] employees = controller.GenerateEmployee('S', size);
		Employee[] invalids = controller.GenerateEmployee('T', size);
		
		int linearInsertionTries = 0;
		int doubleInsertionTries = 0;
		int failedLinear = 0;
		int failedDouble = 0;
		
		// Insert dataset into hash table
		for(Employee e : employees) {
			int linearResult = LinearInsertIntoHashTable(e, linearHashTable);
			int doubleResult = DoubleInsertIntoHashTable(e, doubleHashTable);
			
			if(linearResult == -1)
				failedLinear++;
			else
				linearInsertionTries += linearResult;
			if(doubleResult == -1)
				failedDouble++;
			else
				doubleInsertionTries += doubleResult;
		}
		
		System.out.println("\nLinear Probing Insertion: " + (size - failedLinear) + " items of " + linearInsertionTries + " tries.");
		System.out.println("Double Hashing Insertion: " + (size - failedDouble) + " items of " + doubleInsertionTries + " tries.");
		
		long start = 0;
		long end = 0;
		int result = 0;
		
		// Successful search of Linear Probing
		long totalTime = 0;		
		int totalSearch = 0;
		int totalSearchComparisons = 0;
		int maxSearchComparisons = 0;
		int minSearchComparisons = -1;
		
		System.out.println("\n=== Linear Probing ===\nSearching: " + size + " successful cases.");
		
		for(Employee e : employees) {
			start = System.nanoTime();
			result = LinearSearch(e, linearHashTable);
			end = System.nanoTime();
			totalTime += (end - start);
			totalSearch++;
			totalSearchComparisons += result;
			
			if(maxSearchComparisons < result) {
				maxSearchComparisons = result;
			}
			
			if(minSearchComparisons < 0) {
				minSearchComparisons = result;
			}
			else if(minSearchComparisons > result) {
				minSearchComparisons = result;
			}
		}
		
		System.out.println("Total Successful Cases: " + totalSearch + ", CPU Time: " + totalTime + "ns, Total Comparisons: " + totalSearchComparisons + ", Max Comparisons: " + maxSearchComparisons + ", Min Comparisons: " + minSearchComparisons + ".");
		
		// Unsuccessful search of Linear Probing
		totalTime = 0;
		totalSearch = 0;
		totalSearchComparisons = 0;
		maxSearchComparisons = 0;
		minSearchComparisons = -1;
		
		System.out.println("\nSearching: " + size + " unsuccessful cases.");
		
		for(Employee e : invalids) {
			start = System.nanoTime();
			result = Math.abs(LinearSearch(e, linearHashTable));
			end = System.nanoTime();
			totalTime += (end - start);
			totalSearch++;
			
			if(maxSearchComparisons < result) {
				maxSearchComparisons = result;
			}
			
			if(minSearchComparisons < 0) {
				minSearchComparisons = result;
			}
			else if(minSearchComparisons > result) {
				minSearchComparisons = result;
			}
		}
		
		System.out.println("Total Unsuccessful Cases: " + totalSearch + ", CPU Time: " + totalTime + "ns, Total Comparisons: " + totalSearchComparisons + ", Max Comparisons: " + maxSearchComparisons + ", Min Comparisons: " + minSearchComparisons + ".");
		
		// Successful search of Double Hashing
		totalTime = 0;
		totalSearch = 0;
		totalSearchComparisons = 0;
		maxSearchComparisons = 0;
		minSearchComparisons = -1;
		
		System.out.println("\n=== Double Hashing ===\nSearching: " + size + " successful cases.");
		
		for(Employee e : employees) {
			start = System.nanoTime();
			result = DoubleSearch(e, doubleHashTable);
			end = System.nanoTime();
			totalTime += (end - start);
			totalSearch++;
			totalSearchComparisons += result;
			
			if(maxSearchComparisons < result) {
				maxSearchComparisons = result;
			}
			
			if(minSearchComparisons < 0) {
				minSearchComparisons = result;
			}
			else if(minSearchComparisons > result) {
				minSearchComparisons = result;
			}
		}
		
		System.out.println("Total Successful Cases: " + totalSearch + ", CPU Time: " + totalTime + "ns, Total Comparisons: " + totalSearchComparisons + ", Max Comparisons: " + maxSearchComparisons + ", Min Comparisons: " + minSearchComparisons + ".");
		
		// Unsuccessful search of Double Hashing
		totalTime = 0;
		totalSearch = 0;
		totalSearchComparisons = 0;
		maxSearchComparisons = 0;
		minSearchComparisons = -1;
		
		System.out.println("\nSearching: 10 unsuccessful cases.");
		
		for(Employee e : invalids) {
			start = System.nanoTime();
			result = Math.abs(DoubleSearch(e, doubleHashTable));
			end = System.nanoTime();
			totalTime += (end - start);
			totalSearch++;
			totalSearchComparisons += result;
			
			if(maxSearchComparisons < result) {
				maxSearchComparisons = result;
			}
			
			if(minSearchComparisons < 0) {
				minSearchComparisons = result;
			}
			else if(minSearchComparisons > result) {
				minSearchComparisons = result;
			}
		}
		
		System.out.println("Total Unsuccessful Cases: " + totalSearch + ", CPU Time: " + totalTime + "ns, Total Comparisons: " + totalSearchComparisons + ", Max Comparisons: " + maxSearchComparisons + ", Min Comparisons: " + minSearchComparisons + ".");
		
		System.out.println("\n=== Items in Hash Table ===");
		// Dataset in Linear Probing Hash Table
		System.out.println("Items in Linear Probing Hash Table:");
		for(Employee e : linearHashTable) {
			if(e != null) {
				System.out.print(e.getNric() + " ");
			}
		}
		
		// Dataset in Double Hashing Hash Table
		System.out.println("\n\nItems in Double Hashing Hash Table:");
		for(Employee e : doubleHashTable) {
			if(e != null) {
				System.out.print(e.getNric() + " ");
			}
		}
		
		System.out.println();
		
		// Search
		while(true) {
			System.out.print("\nEnter a NRIC to search: ");
			Employee e = new Employee();
			e.setNric(sc.nextLine());
			
			start = System.nanoTime();
			result = LinearSearch(e, linearHashTable);
			end = System.nanoTime();
			
			if(result <= 0)
				System.out.println("\nLinear Probing Searching Unsuccessful, CPU Time: " + (end - start) + "ns, No. of Comparisons: " + Math.abs(result) + ".");
			else {
                System.out.println("\nLinear Probing Searching Successful, CPU Time: " + (end - start) + "ns, No. of Comparisons: " + result + ".");
                System.out.println("NRIC: " + e.getNric());
                System.out.println("Name: " + e.getName());
                System.out.println("Gender: " + e.getPhone());
			}

			
			start = System.nanoTime();
			result = DoubleSearch(e, doubleHashTable);
			end = System.nanoTime();
			
			if(result <= 0)
				System.out.println("\nDouble Hashing Searching Unsuccessful, CPU Time: " + (end - start) + "ns, No. of Comparisons: " + Math.abs(result) + ".");
			else {
                System.out.println("\nDouble Hashing Searching Successful, CPU Time: " + (end - start) + "ns, No. of Comparisons: " + result + ".");
                System.out.println("NRIC: " + e.getNric());
                System.out.println("Name: " + e.getName());
                System.out.println("Gender: " + e.getPhone());
			}
		}
	}
	
	private static int LinearSearch(Employee e, Employee[] ht) {
		
		int hash = Hash(e.GetNricDigits());
		int loc = hash;
		int comparisons = 0;
		
		while(ht[loc] != null) {
			comparisons++;			
			if(ht[loc].getNric().equals(e.getNric())) {
				e.setName(ht[loc].getName());
				e.setGender(ht[loc].getGender());
				e.setPhone(ht[loc].getPhone());
				return comparisons;
			}
			else {
				loc = RehashLinear(loc);
				if(loc == hash) {
					break;
				}
			}
		}
		
		return 0 - comparisons;
	}
	
	private static int DoubleSearch(Employee e, Employee[] ht) {
		
		int hash = Hash(e.GetNricDigits());
		int loc = hash;
		int dist = HashIncrease(e.GetNricDigits());
		int comparisons = 0;
		
		while(ht[loc] != null) {
			comparisons++;
			if(ht[loc].getNric().equals(e.getNric())) {
				e.setName(ht[loc].getName());
				e.setGender(ht[loc].getGender());
				e.setPhone(ht[loc].getPhone());
				return comparisons;
			}
			else {
				loc = RehashDouble(loc, dist);
				if(loc == hash) {
					return -1;
				}
			}
		}
		
		return 0 - comparisons;
	}
	
	private static int LinearInsertIntoHashTable(Employee e, Employee[] ht) {
		
		int hash = Hash(e.GetNricDigits());
		int loc = hash;
		boolean success = false;
		int tries = 1;
		
		if(ht[loc] == null) {
			ht[loc] = e;
			success = true;
		}
		while(!success) {
			tries++;
			loc = RehashLinear(loc);
			if(ht[loc] == null) {
				ht[loc] = e;
				success = true;
			}
			if(loc == hash) {
				return -1;
			}
		}
		
		return tries;
	}
	
	private static int DoubleInsertIntoHashTable(Employee e, Employee[] ht) {
		
		int hash = Hash(e.GetNricDigits());
		int loc = hash;
		int dist = HashIncrease(e.GetNricDigits());
		boolean success = false;
		int tries = 1;
		
		if(ht[loc] == null) {
			ht[loc] = e;
			success = true;
		}
		while(!success) {
			tries++;
			loc = RehashDouble(loc, dist);
			if(ht[loc] == null) {
				ht[loc] = e;
				success = true;
			}
			if(loc == hash) {
				break;
			}
		}
		
		return tries;
	}
	
	private static int Hash(int key) {
		return key % TABLE_SIZE;
	}
	
	private static int RehashLinear(int hash) {
		return (hash + 1) % TABLE_SIZE;
	}
	
	// Use a prime number for best result
	private static int HashIncrease(int key) {
		return 7 - (key % 7);
	}
	
	private static int RehashDouble(int hash, int dist) {
		return (hash + dist) % TABLE_SIZE;
	}
}
