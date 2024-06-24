package GoNature;

import java.io.Serializable;

/**
 * Report represents a report submitted by a user regarding a specific park.
 * Each report contains details such as report ID, sender, park name, report type, and date created.
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
public class Report implements Serializable {
	private int id;
	private String parkName;
	private String sender;
	private String reportType;
	private String dateCreated;
	/**
     * Constructs a Report object with the specified details.
     *
     * @param id          The ID of the report.
     * @param sender      The sender of the report.
     * @param parkName    The name of the park associated with the report.
     * @param reportType  The type of the report (e.g., maintenance issue, safety concern).
     * @param dateCreated The date when the report was created.
     */
	public Report(int id, String sender, String parkName, String reportType, String dateCreated) {
		this.id = id;
		this.sender = sender;
		this.reportType = reportType;
		this.dateCreated = dateCreated;
		this.parkName = parkName;
	}
	/**
     * Retrieves the ID of the report.
     *
     * @return The report ID.
     */
	public int getId() {
		return id;
	}
	/**
     * Sets the ID of the report.
     *
     * @param id The report ID to set.
     */
	public void setId(int id) {
		this.id = id;
	}
	/**
     * Retrieves the sender of the report.
     *
     * @return The sender of the report.
     */
	public String getSender() {
		return sender;
	}
	 /**
     * Sets the sender of the report.
     *
     * @param sender The sender of the report to set.
     */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
     * Retrieves the type of the report.
     *
     * @return The report type.
     */
	public String getReportType() {
		return reportType;
	}
	/**
     * Sets the type of the report.
     *
     * @param reportType The report type to set.
     */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	 /**
     * Retrieves the date when the report was created.
     *
     * @return The date when the report was created.
     */
	public String getDateCreated() {
		return dateCreated;
	}
	 /**
     * Sets the date when the report was created.
     *
     * @param dateCreated The date when the report was created.
     */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	 /**
     * Retrieves the name of the park associated with the report.
     *
     * @return The name of the park.
     */
	public String getParkName() {
		return parkName;
	}
	/**
     * Sets the name of the park associated with the report.
     *
     * @param parkName The name of the park to set.
     */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

}