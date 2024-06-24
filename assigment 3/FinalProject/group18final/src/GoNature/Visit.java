package GoNature;

/**
 * The Visit class represents a visit to a park made by travelers. It contains
 * details such as visit ID, park name, order type, number of visitors, entry
 * time, and exit time. This class provides methods to retrieve and modify visit
 * details.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 */
public class Visit {
	private int id;
	private String parkName;
	private String orderType;
	private int numberVisitors;
	private String entryTime;
	private String exitTime;

	/**
	 * Constructs a Visit object with default values.
	 */
	public Visit() {
	}

	/**
	 * Constructs a Visit object with the specified details.
	 *
	 * @param id             The ID of the visit.
	 * @param parkName       The name of the park.
	 * @param orderType      The type of order.
	 * @param numberVisitors The number of visitors in the visit.
	 * @param entryTime      The entry time of the visit.
	 * @param exitTime       The exit time of the visit.
	 */
	public Visit(int id, String parkName, String orderType, int numberVisitors, String entryTime, String exitTime) {
		this.id = id;
		this.orderType = orderType;
		this.numberVisitors = numberVisitors;
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.parkName = parkName;
	}

	/**
	 * Retrieves the ID of the visit.
	 *
	 * @return The ID of the visit.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the visit.
	 *
	 * @param id The ID of the visit to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the order type of the visit.
	 *
	 * @return The order type of the visit.
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * Sets the order type of the visit.
	 *
	 * @param orderType The order type of the visit to set.
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * Retrieves the number of visitors in the visit.
	 *
	 * @return The number of visitors in the visit.
	 */
	public int getNumberVisitors() {
		return numberVisitors;
	}

	/**
	 * Sets the number of visitors in the visit.
	 *
	 * @param numberVisitors The number of visitors in the visit to set.
	 */
	public void setNumberVisitors(int numberVisitors) {
		this.numberVisitors = numberVisitors;
	}

	/**
	 * Retrieves the entry time of the visit.
	 *
	 * @return The entry time of the visit.
	 */
	public String getEntryTime() {
		return entryTime;
	}

	/**
	 * Sets the entry time of the visit.
	 *
	 * @param entryTime The entry time of the visit to set.
	 */
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * Retrieves the exit time of the visit.
	 *
	 * @return The exit time of the visit.
	 */
	public String getExitTime() {
		return exitTime;
	}

	/**
	 * Sets the exit time of the visit.
	 *
	 * @param exitTime The exit time of the visit to set.
	 */
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * Retrieves the name of the park visited.
	 *
	 * @return The name of the park visited.
	 */
	public String getParkName() {
		return parkName;
	}

	/**
	 * Sets the name of the park visited.
	 *
	 * @param parkName The name of the park visited to set.
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	/**
	 * Returns a string representation of the Visit object.
	 *
	 * @return A string representation of the Visit object.
	 */
	@Override
	public String toString() {
		return "Visit{" + "id=" + id + ", orderType='" + orderType + '\'' + ", numberVisitors=" + numberVisitors
				+ ", entryTime=" + entryTime + ", exitTime=" + exitTime + '}';
	}
}