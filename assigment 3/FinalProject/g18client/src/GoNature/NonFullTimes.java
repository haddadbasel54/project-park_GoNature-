package GoNature;

import java.io.Serializable;

/**
 * NonFullTimes represents information about non-full time slots for a specific
 * date and time range. This class stores details such as the date and time
 * range for non-full time slots.
 *
 * This class is typically used to represent time slots that are not fully
 * occupied or reserved.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class NonFullTimes implements Serializable {
	private String date;
	private String timeRange;

	/**
	 * Constructs a NonFullTimes object with the specified date and time range.
	 *
	 * @param date      The date for non-full time slots.
	 * @param timeRange The time range for non-full time slots.
	 */
	public NonFullTimes(String date, String timeRange) {
		super();
		this.date = date;
		this.timeRange = timeRange;
	}

	/**
	 * Retrieves the date for non-full time slots.
	 *
	 * @return The date for non-full time slots.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date for non-full time slots.
	 *
	 * @param date The date to set.
	 */

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Retrieves the time range for non-full time slots.
	 *
	 * @return The time range for non-full time slots.
	 */
	public String getTimeRange() {
		return timeRange;
	}

	/**
	 * Sets the time range for non-full time slots.
	 *
	 * @param timeRange The time range to set.
	 */
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

}
