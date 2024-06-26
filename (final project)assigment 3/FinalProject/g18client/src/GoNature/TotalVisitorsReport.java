package GoNature;

import java.io.Serializable;

/**
 * TotalVisitorsReport represents a report containing information about total
 * visitors to a park. It includes the number of visitors categorized by group
 * visits, family visits, and individual visits, as well as the total number of
 * visitors overall. Additionally, it provides arrays to store the number of
 * visits for each day of the week.
 *
 * This class provides methods to retrieve and modify report details.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class TotalVisitorsReport implements Serializable {
	private int groupVisits;
	private int familyVisits;
	private int individualVisits;
	private int totalVisitors;
	private int[] individualvisitsByDay = new int[7];
	private int[] groupvisitsByDay = new int[7];// Assuming 7 days in a week
	private int[] familyvisitsByDay = new int[7];

	/**
	 * Constructs a TotalVisitorsReport object with the specified details.
	 *
	 * @param individualvisitsByDay An array containing the number of individual
	 *                              visits for each day of the week.
	 * @param groupvisitsByDay      An array containing the number of group visits
	 *                              for each day of the week.
	 * @param familyvisitsByDay     An array containing the number of family visits
	 *                              for each day of the week.
	 */
	public TotalVisitorsReport(int[] individualvisitsByDay, int[] groupvisitsByDay, int[] familyvisitsByDay) {
		this.familyvisitsByDay = familyvisitsByDay.clone();
		this.groupvisitsByDay = groupvisitsByDay.clone();
		this.individualvisitsByDay = individualvisitsByDay.clone();
		for (int i = 0; i < 7; i++) {
			this.groupVisits += groupvisitsByDay[i];
			this.individualVisits += individualvisitsByDay[i];
			this.familyVisits += familyvisitsByDay[i];

		}
		this.totalVisitors = groupVisits + familyVisits + individualVisits;// Cloning to avoid reference issues
	}

	/**
	 * Retrieves the number of group visits.
	 *
	 * @return The number of group visits.
	 */
	public int getGroupVisits() {
		return groupVisits;
	}

	/**
	 * Sets the number of group visits.
	 *
	 * @param groupVisits The number of group visits to set.
	 */
	public void setGroupVisits(int groupVisits) {
		this.groupVisits = groupVisits;
	}

	/**
	 * Retrieves the number of family visits.
	 *
	 * @return The number of family visits.
	 */
	public int getFamilyVisits() {
		return familyVisits;
	}

	/**
	 * Sets the number of family visits.
	 *
	 * @param familyVisits The number of family visits to set.
	 */
	public void setFamilyVisits(int familyVisits) {
		this.familyVisits = familyVisits;
	}

	/**
	 * Retrieves the number of individual visits.
	 *
	 * @return The number of individual visits.
	 */
	public int getIndividualVisits() {
		return individualVisits;
	}

	/**
	 * Sets the number of individual visits.
	 *
	 * @param individualVisits The number of individual visits to set.
	 */
	public void setIndividualVisits(int individualVisits) {
		this.individualVisits = individualVisits;
	}

	/**
	 * Retrieves the total number of visitors.
	 *
	 * @return The total number of visitors.
	 */
	public int getTotalVisitors() {
		return totalVisitors;
	}

	/**
	 * Sets the total number of visitors.
	 *
	 * @param totalVisitors The total number of visitors to set.
	 */
	public void setTotalVisitors(int totalVisitors) {
		this.totalVisitors = totalVisitors;
	}

	/**
	 * Retrieves the array containing the number of individual visits for each day
	 * of the week.
	 *
	 * @return An array containing the number of individual visits for each day of
	 *         the week.
	 */
	public int[] getIndividualvisitsByDay() {
		return individualvisitsByDay;
	}

	/**
	 * Sets the array containing the number of individual visits for each day of the
	 * week.
	 *
	 * @param individualvisitsByDay An array containing the number of individual
	 *                              visits for each day of the week to set.
	 */
	public void setIndividualvisitsByDay(int[] individualvisitsByDay) {
		this.individualvisitsByDay = individualvisitsByDay;
	}

	/**
	 * Retrieves the array containing the number of group visits for each day of the
	 * week.
	 *
	 * @return An array containing the number of group visits for each day of the
	 *         week.
	 */
	public int[] getGroupvisitsByDay() {
		return groupvisitsByDay;
	}

	/**
	 * Sets the array containing the number of group visits for each day of the
	 * week.
	 *
	 * @param groupvisitsByDay An array containing the number of group visits for
	 *                         each day of the week to set.
	 */
	public void setGroupvisitsByDay(int[] groupvisitsByDay) {
		this.groupvisitsByDay = groupvisitsByDay;
	}

	/**
	 * Retrieves the array containing the number of family visits for each day of
	 * the week.
	 *
	 * @return An array containing the number of family visits for each day of the
	 *         week.
	 */
	public int[] getFamilyvisitsByDay() {
		return familyvisitsByDay;
	}

	/**
	 * Sets the array containing the number of family visits for each day of the
	 * week.
	 *
	 * @param familyvisitsByDay An array containing the number of family visits for
	 *                          each day of the week to set.
	 */
	public void setFamilyvisitsByDay(int[] familyvisitsByDay) {
		this.familyvisitsByDay = familyvisitsByDay;
	}

}