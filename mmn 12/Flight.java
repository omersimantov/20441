/**
 * This class represents a flight
 *
 * @author Omer Siman Tov
 * @version 2/4/2022
 */

public class Flight {
    // Constants
    public static final int MAX_CAPACITY=250;


    // Instance variables
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;


	// Constructors
	/**
	 * Creates a new Flight object
	 * @param origin the origin city of the flight
	 * @param destination the destination city of the flight
     * @param hourOfDeparture the hour of departure
     * @param minuteOfDeparture the minute of departure
     * @param duration the duration of the flight in minutes
     * @param passengers the number of passengers in the flight
     * @param price the price of a ticket for the flight
	 */
    public Flight(String origin, String destination, int hourOfDeparture, int minuteOfDeparture, int duration, int passengers, int price) {
        _origin = origin;
        _destination = destination;
        _departure = new Time1(hourOfDeparture, minuteOfDeparture);

        if (duration < 0) _flightDuration = 0;
        else _flightDuration = duration;

        if (passengers > MAX_CAPACITY) _noOfPassengers = MAX_CAPACITY;
        else if (passengers < 0) _noOfPassengers = 0;
        else _noOfPassengers = passengers;

        _isFull = _noOfPassengers == MAX_CAPACITY;

        if (price < 0) _price = 0;
        else _price = price;
    }
    
	/**
	 * Copy constructor
	 * @param other to be copied
	 */
    public Flight(Flight other) {
        _origin = other._origin;
        _destination = other._destination;
        _departure = other._departure;
        _flightDuration = other._flightDuration;
        _noOfPassengers = other._noOfPassengers;
        _isFull = other._isFull;
        _price = other._price;
    }
    // End of constructors


    // Getters and setters
    /** Gets the origin city
     * @return a string that represents the origin city
     */
    public String getOrigin() {
        return _origin;
    }

    /** Gets the destination city
     * @return a string that represents the destination city
     */
    public String getDestination() {
        return _destination;
    }

    /** Gets the time of departure
     * @return a Time1 object that represents the time of departure
     */
    public Time1 getDeparture() {
        return new Time1(_departure);
    }

    /** Gets the duration
     * @return an integer that represents the duration
     */
    public int getFlightDuration() {
        return _flightDuration;
    }

    /** Gets the number of passengers
     * @return an integer that represents the number of passengers
     */
    public int getNoOfPassengers() {
        return _noOfPassengers;
    }

    /** Gets is the flight full or not
     * @return true if the flight is full or false if not
     */
    public boolean getIsFull() {
        return _isFull;
    }

    /** Gets the price
     * @return an integer that represents the price
     */
    public int getPrice() {
        return _price;
    }

    /** Sets the origin
	 * @param origin the value to be set
	 */
    public void setOrigin(String origin) {
        _origin = origin;
    }

    /** Sets the destination
	 * @param destination the value to be set
	 */
    public void setDestination(String destination) {
        _destination = destination;
    }

    /** Sets the time of departure
	 * @param departure the value to be set
	 */
    public void setDeparture(Time1 departure) {
        _departure = new Time1(departure);
    }

    /** Sets the duration
	 * @param duration the value to be set
	 */
    public void setFlightDuration(int duration) {
        if (duration >= 0) _flightDuration = duration;
    }

    /** Sets the number of passengers
	 * @param passengers the value to be set
	 */
    public void setNoOfPassengers(int passengers) {
        if (passengers <= MAX_CAPACITY && passengers >= 0) {
            _noOfPassengers = passengers;
            _isFull = _noOfPassengers == MAX_CAPACITY;
        }
    }
    
    /** Sets the price
	 * @param price the value to be set
	 */
    public void setPrice(int price) {
        if (price >= 0) _price = price;
    }
    // End of getters and setters


    // Methods
    /** 
     * Checks if 2 flights are the same
	 * @param other the flight to compare this flight to
	 * @return true if the flights are the same or false if not
     */
    public boolean equals(Flight other) {
        if (_origin.equals(other._origin) && _destination.equals(other._destination) && _departure.equals(other._departure)) return true;
        return false;
    }

    /** 
     * Checks the time of arrival
	 * 
	 * @return a Time1 object that represents the time of arrival
	 */
    public Time1 getArrivalTime() {
        return new Time1(_departure.addMinutes(_flightDuration));
    }

    /** 
     * Checks if a certain number of passengers can be added, then adds them if yes
	 * @param num the number of passengers to check if can be added
	 * @return true if the number of passengers passed as a parameter have been added or false if not
	 */
    public boolean addPassengers(int num) {
        if ((_noOfPassengers + num) <= MAX_CAPACITY) {
            setNoOfPassengers(_noOfPassengers + num);
            
            if (_noOfPassengers == MAX_CAPACITY) _isFull = true;

            return true;
        }

        return false;
    }

    /** 
     * Checks if a ticket to this flight is cheaper than a ticket to the flight passed as a parameter
	 * @param other the flight to compare this flight to
	 * @return true if a ticket to this flight is cheaper than a ticket to the flight passed as a parameter or false if not
	 */
    public boolean isCheaper(Flight other) {
        if (_price < other._price) return true;
        return false;
    }

    /** 
     * Checks the total amount of money collected from the passengers for this flight
     * 
     * @return an integer that represents the total amount of money collected from the passengers for this flight
	 */
    public int totalPrice() {
        return _price*_noOfPassengers;
    }
    
    /** 
     * Checks if this flight lands earlier than the flight passed as a parameter
	 * @param other the flight to compare this flight to
	 * @return true if a this flight lands earlier than the flight passed as a parameter or false if not
	 */
    public boolean landsEarlier(Flight other) {
        if (getArrivalTime().before(other.getArrivalTime())) return true;
        return false;
    }

    /**
	 * Returns a string that represents this flight
	 *
	 * @return a string that represents this flight
	 * in the following format:
	 * Flight from _origin to _destination departs at _departure. Flight is full/not full.
	 */
    public String toString() {
        return "Flight from " + _origin + " to " + _destination + " departs at " + _departure.toString() + ". Flight is " + (_isFull ? "full." : "not full.");
    }
    // End of methods
}