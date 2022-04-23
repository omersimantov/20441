/**
 * This class represents an airport's schedule on a given calendar day
 *
 * @author Omer Siman Tov
 * @version 20/4/2022
 */

public class Airport {
    // Constants
    public static final int MAX_FLIGHTS=200;


    // Instance variables
    private Flight[] _flightSchedule;
    private int _noOfFlights;
    private String _city;
 

	// Constructor
	/**
	 * Creates a new Airport object
	 * @param city the city in which the airport is located
	 */
    public Airport(String city) {
        _flightSchedule = new Flight[MAX_FLIGHTS];
        _noOfFlights = 0;
        _city = city;
    }

    
    // Methods
    /** 
     * A utility method that gets a flights array as a parameter and returns 
     * a new array which is identical to the parameter only without any "holes"
	 * @param f the flights array to remove potential "holes" from
	 * @return a new flights array which is identical to the parameter only without any "holes"
     */
    private Flight[] getValidFlightsArray(Flight[] f) {
        Flight[] newArr = new Flight[MAX_FLIGHTS];
        int newArrIndex = 0;

        for (int i = 0; i < MAX_FLIGHTS-1; i++) {
            if (f[i] != null) {
                newArr[newArrIndex] = f[i];
                newArrIndex++;
            }
        }

        return newArr;
    }

    /** 
     * Adds a flight to the flight schedule
	 * @param f the flight to be added to the flight schedule
	 * @return true if the flight was successfully added to the flight schedule or false if not
     */
    public boolean addFlight(Flight f) {
        for (int i = 0; i < MAX_FLIGHTS-1; i++) {
            if (_flightSchedule[i] == null && (f.getOrigin().equals(_city) || f.getDestination().equals(_city))) {
                _flightSchedule[i] = f;
                _noOfFlights++;
                return true; 
            }
        }

        return false;
    }

    /** 
     * Removes a flight from the flight schedule
	 * @param f the flight to be removed from the flight schedule
	 * @return true if the flight was successfully removed from the flight schedule or false if not
     */
    public boolean removeFlight(Flight f) {
        boolean didRemove = false;

        for (int i = 0; i < MAX_FLIGHTS-1; i++) {
            if (_flightSchedule[i] == f) {
                _flightSchedule[i] = null;
                _noOfFlights--;
            }
        }

        _flightSchedule = getValidFlightsArray(_flightSchedule);

        return didRemove ? true : false;
    }

    /** 
     * Checks when does the first flight departs from a given city
	 * @param place the city to check when the flight departs from at
	 * @return a Time1 object that represents the time in which the first flight departs from the given city
     */
    public Time1 firstFlightFromOrigin (String place) {
        for (int i = 0; i < _noOfFlights; i++) {
            if (_flightSchedule[i].getOrigin().equals(place))
                return new Time1(_flightSchedule[i].getDeparture());
        }

        return null;
    }

    /** 
     * Checks how many full flights are in the schedule as of currently
	 * @return an integer that represents the amount of flights that are in the schedule as of currently
     */
    public int howManyFullFlights() {
        int counter = 0;

        for (int i = 0; i < _noOfFlights; i++)
          counter += _flightSchedule[i].getIsFull() ? 1 : 0;

        return counter;
    }

    /** 
     * Checks the number of flights in the schedule that come from or go to a given city
	 * @param place the city to check the number of flights in the schedule that come from or go to
	 * @return an integer that represents the number of flights in the schedule that come from or go to a given city
     */
    public int howManyFlightsBetween (String place) {
        int counter = 0;

        for (int i = 0; i < _noOfFlights; i++)
            if ((_flightSchedule[i].getOrigin().equals(_city) && _flightSchedule[i].getDestination().equals(place)) ||
                 _flightSchedule[i].getOrigin().equals(place) && _flightSchedule[i].getDestination().equals(_city)) 
                 counter++;

        return counter;
    }

    /** 
     * Checks which destination in the flight schedule is the most popular one
	 * @return a string that represents the most popular destination
     */
    public String mostPopularDestination() {
        if (_noOfFlights == 0) return null;

        String mostPopular = new String(_flightSchedule[0].getDestination());
        int mostPopularCount = 0;


        for (int i = 0; i < _noOfFlights; i++) {
            String currentDestination = new String(_flightSchedule[i].getDestination());
            int currentDestinationCount = 0;

            for (int j = 0; j < _noOfFlights; j++) {
                if (_flightSchedule[j].getDestination().equals(currentDestination)) 
                    currentDestinationCount++;
            }
                        
            if (currentDestinationCount > mostPopularCount) {
                mostPopular = currentDestination;
                mostPopularCount = currentDestinationCount; 
            } 
        }

        return mostPopular;
    }

    /** 
     * Checks which flight ticket is the most expensive one among the flights in the schedule
	 * @return a flight object that represents the most expensive flight in the schedule by ticket price
     */
    public Flight mostExpensiveTicket() {
        if (_noOfFlights == 0) return null;

        Flight mostExpensive = new Flight(_flightSchedule[0]);
        int mostExpensivePrice = _flightSchedule[0].getPrice();


        for (int i = 1; i < _noOfFlights; i++) {
            if (_flightSchedule[i].getPrice() > mostExpensivePrice)  {
                mostExpensive = _flightSchedule[i];
                mostExpensivePrice = _flightSchedule[i].getPrice();
            }
        }

        return mostExpensive;
    }

    /** 
    * Checks which flight is the longest one by duration among the flights in the schedule
    * @return a flight object that represents the longest flight in the schedule by duration
    */
    public Flight longestFlight() {
        if (_noOfFlights == 0) return null;

        Flight longest = new Flight(_flightSchedule[0]);
        int longestDuration = _flightSchedule[0].getFlightDuration();


        for (int i = 1; i < _noOfFlights; i++) {
            if (_flightSchedule[i].getFlightDuration() > longestDuration)  {
                longest = _flightSchedule[i];
                longestDuration = _flightSchedule[i].getFlightDuration();
            }
        }

        return longest;
    }

    /**
	 * Returns a string that represents this airport's schedule
	 *
	 * @return a string that represents this airport's schedule
	 * in the format of a list of the schedule's flight descriptions, separated by line breaks
	 */
    public String toString() {
        String arrString = new String("");

        for (int i = 0; i < _noOfFlights; i++) {
            arrString += _flightSchedule[i].toString();
            arrString += (i != _noOfFlights-1) ? "\n" : "";
        }

        return arrString;
    }
    // End of methods
}