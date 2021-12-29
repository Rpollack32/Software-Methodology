package projectOne;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This is the Date class, which is used to create a Date object
 * @author Ryan Pollack, Michael Kang 
 */
public class Date implements Comparable<Date>{
	//min and max constants
	private static final int MIN_YEAR = 1980;
	private static final int MAX_YEAR = 2021;
	private static final int MIN_MONTH = 1;
	private static final int MAX_MONTH = 12;
	private static final int MIN_DAY = 1;
	private static final int MAX_DAY = 31;
	private static final int MAX_DAY_LEAP_YEAR = 29;
	private static final int MAX_DAY_NOT_LEAP_YEAR = 28;
	
	//month constants 
	private static final int JANUARY = 1;
	private static final int FEBRUARY = 2;
	private static final int MARCH = 3;
	private static final int APRIL = 4;
	private static final int MAY = 5;
	private static final int JUNE = 6;
	private static final int JULY = 7;
	private static final int AUGUST = 8;
	private static final int SEPTEMBER = 9;
	private static final int OCTOBER = 10;
	private static final int NOVEMBER  = 11;
	private static final int DECEMBER = 12;
	
	//leap year method constants 
	private static final int QUADRENNIAL = 4;
	private static final int CENTENNIAL = 100;
	private static final int QUATERCENTENNIAL = 400;
	
	//instance variables
	private int year;
	private int month;
	private int day;
	
	/**
	 * This is the Date constructor, which takes in a String as a parameter
	 * @param date
	 */
	public Date(String date) { //take "mm/dd/yy" and create a Date object
		StringTokenizer stk = new StringTokenizer(date);
		
		String month = stk.nextToken("/");
		String day = stk.nextToken("/");
		String year = stk.nextToken("/");
		
		int month1 = Integer.parseInt(month);
		int day1 = Integer.parseInt(day);
		int year1 = Integer.parseInt(year);
		
		Date newDate = new Date();
		newDate.year = year1;
		newDate.month = month1;
		newDate.day = day1;
	}
	
	/**
	 * This is the Date constructor, which does not take any parameters
	 * It creates a Date object with today's date
	 */
	public Date() { //create an object with today's date
        Calendar currentDate = Calendar.getInstance();
        month = currentDate.get(Calendar.MONTH) + 1;
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        year = currentDate.get(Calendar.YEAR);
	}
	
	/**
	 * Gets and returns the year
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Gets and returns the month
	 * @return month
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Gets and returns the day
	 * @return day
	 */
	public int getDay() {
		return day;
	}
	
	/**
	 * This is the checkLeapYear method, which checks if a year is a leap year or not 
	 * @param date
	 * @return true if the date's year is a leap year and false if not
	 */
	public boolean checkLeapYear(int year) {
		boolean isLeapYear;
		if(year % QUADRENNIAL == 0){
			if(year % CENTENNIAL == 0) {
				if(year % QUATERCENTENNIAL == 0) 
					isLeapYear = true;
				else
					isLeapYear = false;
			}
			else
				isLeapYear = true;
		}
		else
			isLeapYear = false;
		return isLeapYear;
	}
	
	/**
	 * This is the isValid method
	 * @param date
	 * @return true if the year is valid and false if the year is not valid
	 */
	public boolean isValid() {
		if(year < MIN_YEAR || year > MAX_YEAR) { //valid year
			return false;
		}
		if(month < MIN_MONTH || month > MAX_MONTH) { //valid month
			return false;
		}
		if(day < MIN_DAY || day > MAX_DAY) { //valid day
			return false;
		}
		
		if(month == JANUARY || month == MARCH || month == MAY || month == JULY || month == OCTOBER || month == DECEMBER) {//31 day months
			if(day < 1 || day > 31) {
				return false;
			}
		}
		
		if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER){//30 day months
			if(day < 1 || day > 30) {
				return false;
			}
		}
		
		if(month == FEBRUARY){ //February
		if((checkLeapYear(year)) == true) {
			if(day < MIN_DAY || day > MAX_DAY_LEAP_YEAR) {
				return false;
			}
		}
		else
			if(day < MIN_DAY || day > MAX_DAY_NOT_LEAP_YEAR) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This is the compareTo method
	 * @param date
	 * @return 1 if true, 0 if false
	 */
	public int compareTo(Date date) {
		if(date.year == year && date.month == month && date.day == day) {
			return 1; //true
		}
		return 0; //false
	}
	
	/*Test Cases
	 * Check for valid month, day, year
	 * Check for valid day based on month
	 * Check for valid day based on leap year (February)
	 */
	
	/**
	 * This is the testbed main method used to test the isValid method
	 * For each test case, it creates a Date object and calls the isValid method
	 * @param args
	 */
	public static void main(String[] args) {
		Date date1 = new Date("04/10/1970");
		if(date1.isValid() == true) {
			System.out.println("date1 is valid");
		}
		else
			System.out.println("date1 is not valid");
		
		Date date2 = new Date("01/05/2030");
		if(date2.isValid() == true) {
			System.out.println("date2 is valid");
		}
		else
			System.out.println("date2 is not valid");
		
		Date date3 = new Date("00/10/2015");
		if(date3.isValid() == true) {
			System.out.println("date3 is valid");
		}
		else
			System.out.println("date3 is not valid");
		
		Date date4 = new Date("14/12/2000");
		if(date4.isValid() == true) {
			System.out.println("date4 is valid");
		}
		else
			System.out.println("date4 is not valid");
		
		Date date5 = new Date("10/00/2010");
		if(date5.isValid() == true) {
			System.out.println("date5 is valid");
		}
		else
			System.out.println("date5 is not valid");
		
		Date date6 = new Date("02/35/2012");
		if(date6.isValid() == true) {
			System.out.println("date6 is valid");
		}
		else
			System.out.println("date6 is not valid");
		
		Date date7 = new Date("02/30/2000");
		if(date7.isValid() == true) {
			System.out.println("date7 is valid");
		}
		System.out.println("date7 is not valid");
		
		Date date8 = new Date("02/29/2019");
		if(date8.isValid() == true) {
			System.out.println("date8 is valid");
		}
		else
			System.out.println("date8 is not valid");
		
		Date date9 = new Date("08/15/2016");
		if(date9.isValid() == true) {
			System.out.println("date9 is valid");
		}
		else
			System.out.println("date9 is not valid");
	}	
}