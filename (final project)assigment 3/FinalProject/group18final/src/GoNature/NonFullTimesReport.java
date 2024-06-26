package GoNature;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * NonFullTimesReport represents a report containing information about non-full
 * time slots and least full hours in a day. This class stores an ArrayList of
 * NonFullTimes objects representing non-full time slots, and an array of
 * integers representing mostly full days.
 *
 * This class is typically used to generate reports on non-full time slots and
 * identify days with high occupancy.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */

public class NonFullTimesReport implements Serializable {
	private ArrayList<NonFullTimes> NonFullTimes;
	private int[] mostlyfulldays;

	/**
	 * Constructs a NonFullTimesReport object with the specified non-full time slots
	 * and mostly full days.
	 *
	 * @param nonFullTimes  An ArrayList of NonFullTimes objects representing
	 *                      non-full time slots.
	 * @param leastfulldays An array of integers representing mostly full days.
	 */
	public NonFullTimesReport(ArrayList<GoNature.NonFullTimes> nonFullTimes, int[] mostlyfulldays) {
		super();
		NonFullTimes = nonFullTimes;
		this.mostlyfulldays = mostlyfulldays;

	}

	/**
	 * Retrieves the ArrayList of NonFullTimes objects representing non-full time
	 * slots.
	 *
	 * @return The ArrayList of NonFullTimes objects.
	 */
	public ArrayList<NonFullTimes> getNonFullTimes() {
		return NonFullTimes;
	}

	/**
	 * Sets the ArrayList of NonFullTimes objects representing non-full time slots.
	 *
	 * @param nonFullTimes The ArrayList of NonFullTimes objects to set.
	 */

	public void setNonFullTimes(ArrayList<NonFullTimes> nonFullTimes) {
		NonFullTimes = nonFullTimes;
	}

	/**
	 * Retrieves the array of integers representing least full days.
	 *
	 * @return The array of integers representing least full days.
	 */
	public int[] getMostlyfulldays() {
		return mostlyfulldays;
	}

	/**
	 * Sets the array of integers representing least full days.
	 *
	 * @param mostlyfulldays The array of integers to set.
	 */
	public void setMostlyfulldays(int[] mostlyfulldays) {
		this.mostlyfulldays = mostlyfulldays;
	}

}
