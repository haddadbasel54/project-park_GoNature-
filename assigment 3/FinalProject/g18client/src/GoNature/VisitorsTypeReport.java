package GoNature;

import java.io.Serializable;

/**
 * The VisitorsTypeReport class represents a report containing the number of
 * visitors by type (individual, family, and group) per hour in the day ovre the
 * last month. This class provides methods to retrieve and modify the data in
 * the report.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Nadine Halabi
 * @author Asaad Sajim
 * @version 1.0
 */
public class VisitorsTypeReport implements Serializable {
	private int[] individualbyhour;
	private int[] familybyhour;
	private int[] groupbyhour;

	/**
	 * Constructs a VisitorsTypeReport object with the specified data.
	 *
	 * @param individualbyhour An array containing the number of individual visitors
	 *                         per hour.
	 * @param familybyhour     An array containing the number of family visitors per
	 *                         hour.
	 * @param groupbyhour      An array containing the number of group visitors per
	 *                         hour.
	 */
	public VisitorsTypeReport(int[] individualbyhour, int[] familybyhour, int[] groupbyhour) {
		this.individualbyhour = individualbyhour;
		this.familybyhour = familybyhour;
		this.groupbyhour = groupbyhour;

	}

	/**
	 * Retrieves the array containing the number of individual visitors per hour.
	 *
	 * @return The array containing the number of individual visitors per hour.
	 */
	public int[] getIndividualbyhour() {
		return individualbyhour;
	}

	/**
	 * Sets the array containing the number of individual visitors per hour.
	 *
	 * @param individualbyhour The array containing the number of individual
	 *                         visitors per hour to set.
	 */
	public void setIndividualbyhour(int[] individualbyhour) {
		this.individualbyhour = individualbyhour;
	}

	/**
	 * Retrieves the array containing the number of family visitors per hour.
	 *
	 * @return The array containing the number of family visitors per hour.
	 */
	public int[] getFamilybyhour() {
		return familybyhour;
	}

	/**
	 * Sets the array containing the number of family visitors per hour.
	 *
	 * @param familybyhour The array containing the number of family visitors per
	 *                     hour to set.
	 */
	public void setFamilybyhour(int[] familybyhour) {
		this.familybyhour = familybyhour;
	}

	/**
	 * Retrieves the array containing the number of group visitors per hour.
	 *
	 * @return The array containing the number of group visitors per hour.
	 */
	public int[] getGroupbyhour() {
		return groupbyhour;
	}

	/**
	 * Sets the array containing the number of group visitors per hour.
	 *
	 * @param groupbyhour The array containing the number of group visitors per hour
	 *                    to set.
	 * 
	 */
	public void setGroupbyhour(int[] groupbyhour) {
		this.groupbyhour = groupbyhour;
	}

}
