package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utilities class is to define Reusable Methods for the Automation 
 * 
 */
public class Utilities {

	/**
	 *Method to convert date from IST to GMT format
	 *@param pattern Date pattern in String format (yyyy-MM-dd, dd-MM-yyyy)
	 *@return expectedDate in GMT
	 *@author Anshul Chhajed
	 */
	public static String getGMTDate(String pattern) {
	
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		String expectedDate=format.format(new Date());
		return expectedDate;
		
	}
	public static void main(String[] args) {
		System.out.println(getGMTDate("yyyy-MM-dd"));
	}
}
