import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles Employee objects.
 *
 * @author Sherry Lau Geok Teng
 * @version 1.0
 * @since 8/2/2017
 */

public class EmployeeController {

	private static ArrayList<String> maleFirstNames = new ArrayList<String>();
	private static ArrayList<String> femaleFirstNames = new ArrayList<String>();
	private static ArrayList<String> lastNames = new ArrayList<String>();
	
	private static final String MALE_FILENAME = "Male Names.txt";
	private static final String FEMALE_FILENAME = "Female Names.txt";
	private static final String LASTNAME_FILENAME = "Last Names.txt";
	private static final String GENDER[] = {"Male", "Female"};
	private static final int WEIGHT[] = {2, 7, 6, 5, 4, 3, 2};
//	private static final char[] STATUS = {'S', 'T', 'F', 'G'};
    private static final char[] CHECKSUM = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'Z', 'J'};
    
    // Generate one Employee information
    public Employee GenerateEmployee(char prefix) {
    	
    	Employee e = new Employee();
    	Random rnd = new Random();
    	
    	// Generate NRIC
    	e.setNric(prefix + GenerateNRIC());
    	
    	LoadNames();
    	
    	// Set gender and name
    	if((rnd.nextBoolean() ? 1 : 0) == 0) {
    		e.setName(maleFirstNames.get(rnd.nextInt(maleFirstNames.size())) + " " + lastNames.get(rnd.nextInt(lastNames.size())));
    		e.setGender(GENDER[0]);
    	}
    	else {
    		e.setName(femaleFirstNames.get(rnd.nextInt(femaleFirstNames.size())) + " " + lastNames.get(rnd.nextInt(lastNames.size())));
    		e.setGender(GENDER[1]);
    	}
    	
    	// Generate phone number
		e.setPhone("+65 " + (rnd.nextBoolean() ? 9 : 8) + String.format("%07d", rnd.nextInt(10000000)));
    	
    	return e;
    }
    
    // Generate a list of Employees
    public Employee[] GenerateEmployee(char prefix, int count) {
    	
    	Employee[] list = new Employee[count];
    	
    	for(int i = 0; i < count; i++) {
    		list[i] = GenerateEmployee(prefix);
    	}
    	
		return list;
    }
    
    private String GenerateNRIC() {
    	
    	int sum = 0;
    	String nric = "";
    	Random rnd = new Random();
    	    	
    	nric = Integer.toString(rnd.nextInt(40) + 60) + String.format("%05d", rnd.nextInt(50000)); 
    	for(int i = 0; i < WEIGHT.length; i++) {
    		sum += Character.getNumericValue(nric.charAt(i)) * WEIGHT[i];
    	}
    	sum = 10 - (sum % 11);
    	
    	return nric + CHECKSUM[sum];
    }
    
    private void LoadNames() {
    	
    	BufferedReader br = null;
    	
    	try {
    		br = new BufferedReader(new FileReader(MALE_FILENAME));
    		String line = "";
    		while ((line = br.readLine()) != null) {
    			maleFirstNames.add(line);
    		}
    	} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	try {
    		br = new BufferedReader(new FileReader(FEMALE_FILENAME));
    		String line = "";
    		while ((line = br.readLine()) != null) {
    			femaleFirstNames.add(line);
    		}
    	} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	try {
    		br = new BufferedReader(new FileReader(LASTNAME_FILENAME));
    		String line = "";
    		while ((line = br.readLine()) != null) {
    			lastNames.add(line);
    		}
    	} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}