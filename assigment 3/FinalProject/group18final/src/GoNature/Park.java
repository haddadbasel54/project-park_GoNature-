package GoNature;

/**
 * Park represents a park in the system with details such as park name, current
 * number of visitors, maximum capacity of visitors, and time limit for
 * visitors.
 *
 * This class provides methods to retrieve and modify park details.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class Park {
	private String parkName;
	private int currentVisitors;
	private int maxVisitors;
	private int visitorsTimeLimitInHours;

	/**
	 * Constructs a Park object with the specified details.
	 *
	 * @param parkName                 The name of the park.
	 * @param maxVisitors              The maximum capacity of visitors allowed in
	 *                                 the park.
	 * @param visitorsTimeLimitInHours The time limit in hours for visitors' stay in
	 *                                 the park.
	 * @param currentVisitors          The current number of visitors in the park.
	 */
	public Park(String parkName, int maxVisitors, int visitorsTimeLimitInHours, int currentVisitors) {
		this.parkName = parkName;
		this.maxVisitors = maxVisitors;
		this.visitorsTimeLimitInHours = visitorsTimeLimitInHours;
		this.currentVisitors = currentVisitors; // Initially, no visitors
	}

	/**
	 * Retrieves the name of the park.
	 *
	 * @return The name of the park.
	 */
	public String getParkName() {
		return parkName;
	}

	/**
	 * Sets the name of the park.
	 *
	 * @param parkName The name of the park to set.
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	/**
	 * Retrieves the current number of visitors in the park.
	 *
	 * @return The current number of visitors.
	 */
	public int getCurrentVisitors() {
		return currentVisitors;
	}

	/**
	 * Sets the current number of visitors in the park.
	 *
	 * @param currentVisitors The current number of visitors to set.
	 */
	public void setCurrentVisitors(int currentVisitors) {
		this.currentVisitors = currentVisitors;
	}

	/**
	 * Retrieves the maximum capacity of visitors allowed in the park.
	 *
	 * @return The maximum capacity of visitors.
	 */
	public int getMaxVisitors() {
		return maxVisitors;
	}

	/**
	 * Sets the maximum capacity of visitors allowed in the park.
	 *
	 * @param maxVisitors The maximum capacity of visitors to set.
	 */
	public void setMaxVisitors(int maxVisitors) {
		this.maxVisitors = maxVisitors;
	}

	/**
	 * Retrieves the time limit in hours for visitors' stay in the park.
	 *
	 * @return The time limit in hours for visitors' stay.
	 */
	public int getVisitorsTimeLimitInHours() {
		return visitorsTimeLimitInHours;
	}

	/**
	 * Sets the time limit in hours for visitors' stay in the park.
	 *
	 * @param visitorsTimeLimitInHours The time limit in hours to set.
	 */
	public void setVisitorsTimeLimitInHours(int visitorsTimeLimitInHours) {
		this.visitorsTimeLimitInHours = visitorsTimeLimitInHours;
	}
}