package GoNature;

import java.io.Serializable;

/**
 * AvailableOrders represents information about available orders for booking at
 * a specific park on a given date and time. This class stores details such as
 * the park name, date, time slot, and exit time.
 *
 * This class is typically used to represent available booking slots that
 * customers can reserve.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class AvailableOrders implements Serializable {
	private String parkName;
	private String date;
	private String time;
	private String exitTime;

	/**
	 * Constructs an AvailableOrders object with the specified details.
	 *
	 * @param parkName The name of the park.
	 * @param date     The date of the available booking slot.
	 * @param time     The start time of the booking slot.
	 * @param exitTime The exit time of the booking slot.
	 */
	public AvailableOrders(String parkName, String date, String time, String exitTime) {
		this.parkName = parkName;
		this.date = date;
		this.time = time;
		this.exitTime = exitTime;
	}

	/**
	 * Retrieves the date of the available booking slot.
	 *
	 * @return The date of the booking slot.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the available booking slot.
	 *
	 * @param date The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Retrieves the start time of the available booking slot.
	 *
	 * @return The start time of the booking slot.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the start time of the available booking slot.
	 *
	 * @param time The start time to set.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Retrieves the exit time of the available booking slot.
	 *
	 * @return The exit time of the booking slot.
	 */
	public String getExitTime() {
		return exitTime;
	}

	/**
	 * Sets the exit time of the available booking slot.
	 *
	 * @param exitTime The exit time to set.
	 */
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * Retrieves the name of the park associated with the available booking slot.
	 *
	 * @return The name of the park.
	 */

	public String getParkName() {
		return parkName;
	}

	/**
	 * Sets the name of the park associated with the available booking slot.
	 *
	 * @param parkName The name of the park to set.
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

}