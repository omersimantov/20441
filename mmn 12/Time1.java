/**
 * This class represents a time of the day
 *
 * @author Omer Siman Tov
 * @version 2/4/2022
 */

public class Time1 {
    // Constants
    public static final int MIN_HOUR=0, MAX_HOUR = 23, MIN_MINUTE=0, MAX_MINUTE=59;


    // Instance variables
    private int _hour;
    private int _minute;
 

	// Constructors
	/**
	 * Creates a new Time1 object
	 * @param h the hour in the day (0-23)
	 * @param m the minute of the hour in the day (0-59)
	 */
    public Time1(int h, int m) {
        // Check if the values passed are within the valid range, otherwise initialize instance variables to 0

        if (h < MIN_HOUR || h > MAX_HOUR) _hour = 0;
        else _hour = h;
        
        if (m < MIN_MINUTE || m > MAX_MINUTE) _minute = 0;
        else _minute = m;
    }
    
	/**
	 * Copy constructor
	 * @param other to be copied
	 */
    public Time1(Time1 other) {
        _hour = other._hour;
        _minute = other._minute;
    }
    // End of constructors


    // Getters and setters
    /** Gets the hour
     * @return an integer that represents the hour
     */
    public int getHour() {
        return _hour;
    }
    
    /** Gets the minute
     * @return an integer that represents the minute
     */ 
    public int getMinute() {
        return _minute;
    }
    
    /** Sets the hour
	 * @param num the value to be set
	 */
    public void setHour(int num) {
        if (num >= MIN_HOUR && num <= MAX_HOUR)
            _hour = num;
    }
    
    /** Sets the minute
	 * @param num the value to be set
	 */
    public void setMinute(int num) {
        if (num >= MIN_MINUTE && num <= MAX_MINUTE)
            _minute = num;
    }
    // End of getters and setters


    // Methods
    /**
	 * Returns a string that represents this time
	 *
	 * @return a string that represents this time
	 * in the following format:
	 * hh:mm (e.g: 07:30 or 14:20)
	 */
    public String toString() {
        final int MIN_DOUBLE_DIGIT=10;
        return (_hour < MIN_DOUBLE_DIGIT ? "0" : "") + _hour + ":" + (_minute < MIN_DOUBLE_DIGIT ? "0" : "") + _minute;
    }

    /**
	 * Returns the amount of minutes passed since midnight
	 *
	 * @return an integer that represents the amount of minutes passed since midnight
	 */
    public int minFromMidnight() {
        return _hour*60 + _minute;
    }

    /** 
     * Checks if 2 times are the same
	 * @param other the time to compare this time to
	 * @return true if the times are the same or false if not
     */
    public boolean equals(Time1 other) {
        return _hour == other._hour && _minute == other._minute;
    }

    /** 
     * Checks if this time is earlier than the time sent as a param
	 * @param other the time to compare this time to
	 * @return true if this time is earlier than the time sent as a param or false if not
	 */
    public boolean before(Time1 other) {
        if (_hour < other._hour) return true;

        else if (_hour == other._hour) {
            if (_minute < other._minute) return true;
            return false;
        }

        return false;
    }

    /** 
     * Checks if this time is later than the time sent as a param
	 * @param other the time to compare this time to
	 * @return true if this time is later than the time sent as a param or false if not
	 */
    public boolean after(Time1 other) {
        return other.before(this);
    }

    /** 
     * Checks the time difference in minutes between the time sent as a param and this time
	 * @param other the time to compare this time to
	 * @return an integer that represents the difference in minutes between the time sent as a param and this time
	 */
    public int difference(Time1 other) {
        return (_hour - other._hour)*60 + (_minute - other._minute);
    }


    /** 
     * Copy current object and add requested minutes to new object
	 * @param num the number of minutes to add or subtract
	 * @return a new Time1 object with the added or subtracted minutes
	 */
    public Time1 addMinutes(int num) {
        int hoursToAdd = num/60, minutesToAdd = num%60;

        if (_minute + minutesToAdd > MAX_MINUTE) hoursToAdd++;
        else if (_minute + minutesToAdd < MIN_MINUTE) { 
            hoursToAdd--; minutesToAdd = (60+minutesToAdd)%60; 
        }

        if (_hour + hoursToAdd < MIN_HOUR) hoursToAdd = (24+(hoursToAdd%24));

        return new Time1((_hour + hoursToAdd)%24, (_minute + minutesToAdd)%60);
    }
    // End of methods
}