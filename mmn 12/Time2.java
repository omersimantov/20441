/**
 * This class represents a time of the day
 *
 * @author Omer Siman Tov
 * @version 2/4/2022
 */

public class Time2 {
    // Constants
    public static final int MIN_HOUR=0, MAX_HOUR = 23, MIN_MINUTE=0, MAX_MINUTE=59;


    // Instance variables
    private int _minFromMid;
 

	// Constructors
	/**
	 * Creates a new Time2 object
	 * @param h the hour in the day (0-23)
	 * @param m the minute of the hour in the day (0-59)
	 */
    public Time2(int h, int m) {
        // Check if the values passed are within the valid range, otherwise initialize instance variables to 0

        if (h < MIN_HOUR || h > MAX_HOUR) h = 0;        
        if (m < MIN_MINUTE || m > MAX_MINUTE) m = 0;

        _minFromMid = h*60 + m;
    }
    
	/**
	 * Copy constructor
	 * @param other to be copied
	 */
    public Time2(Time2 other) {
        _minFromMid = other._minFromMid;
    }
    // End of constructors


    // Getters and setters
    /** Gets the hour
     * @return an integer that represents the hour
     */
    public int getHour() {
        return _minFromMid/60;
    }
    
    /** Gets the minute
     * @return an integer that represents the minute
     */ 
    public int getMinute() {
        return _minFromMid%60;
    }
    
    /** Sets the hour
	 * @param num the value to be set
	 */
    public void setHour(int num) {
        if (num >= MIN_HOUR && num <= MAX_HOUR)
            _minFromMid = ((_minFromMid - getMinute())/getHour())*num + getMinute();
    }
    
    /** Sets the minute
	 * @param num the value to be set
	 */
    public void setMinute(int num) {
        if (num >= MIN_MINUTE && num <= MAX_MINUTE)
            _minFromMid = (_minFromMid - getMinute()) + num;
    }
    // End of getters and setters


    // Methods
    /**
	 * Returns the amount of minutes passed since midnight
	 *
	 * @return an integer that represents the amount of minutes passed since midnight
	 */
    public int minFromMidnight() {
        return _minFromMid;
    }

    /** 
     * Checks if 2 times are the same
	 * @param other the time to compare this time to
	 * @return true if the times are the same or false if not
     */
    public boolean equals(Time2 other) {
        return _minFromMid == other._minFromMid;
    }

    /** 
     * Checks if this time is earlier than the time sent as a param
	 * @param other the time to compare this time to
	 * @return true if this time is earlier than the time sent as a param or false if not
	 */
    public boolean before(Time2 other) {
        if (_minFromMid < other._minFromMid) return true;
        return false;
    }

    /** 
     * Checks if this time is later than the time sent as a param
	 * @param other the time to compare this time to
	 * @return true if this time is later than the time sent as a param or false if not
	 */
    public boolean after(Time2 other) {
        return other.before(this);
    }

    /** 
     * Checks the time difference in minutes between the time sent as a param and this time
	 * @param other the time to compare this time to
	 * @return an integer that represents the difference in minutes between the time sent as a param and this time
	 */
    public int difference(Time2 other) {
        return _minFromMid - other._minFromMid;
    }

    /**
	 * Returns a string that represents this time
	 *
	 * @return a string that represents this time
	 * in the following format:
	 * hh:mm (e.g: 07:30 or 14:20)
	 */
    public String toString() {
        final int MIN_DOUBLE_DIGIT=10;
        return (getHour() < MIN_DOUBLE_DIGIT ? "0" : "") + getHour() + ":" + (getMinute() < MIN_DOUBLE_DIGIT ? "0" : "") + getMinute();
    }

    /** 
     * Adds or subtracts a given amount of minutes from a new Time2 object
	 * @param num the number of minutes to add or subtract
	 * @return a new Time2 object with the added or subtracted minutes
	 */
    public Time2 addMinutes(int num) {
        int hoursToAdd = num/60, minutesToAdd = num%60;

        if (getMinute() + minutesToAdd > MAX_MINUTE) hoursToAdd++;
        else if (getMinute() + minutesToAdd < MIN_MINUTE) { 
            hoursToAdd--; minutesToAdd = (60+minutesToAdd)%60; 
        }

        if (getHour() + hoursToAdd < MIN_HOUR) hoursToAdd = (24+(hoursToAdd%24));

        return new Time2((getHour() + hoursToAdd)%24, (getMinute() + minutesToAdd)%60);
    }
    // End of methods
}