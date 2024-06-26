package GoNature;

import java.io.Serializable;

/**
 * Request represents a request made by a user regarding a specific park. Each
 * request contains details such as sender, request information, request type,
 * and park name. The request type is defined using the RequestType enum.
 *
 * This class provides methods to retrieve and modify request details.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class Request implements Serializable {

	/**
	 * Enum defining different types of requests.
	 */
	public enum RequestType {
		PARK_CAPACITY, VISIT_LENGTH, NUMBER_OF_DIFFERENCES
	}

	private String sender;
	private int requestInfo;
	private RequestType requestType; // Enum field for the type of report
	private String parkName;

	/**
	 * Constructs a Request object with the specified details.
	 *
	 * @param sender      The sender of the request.
	 * @param requestInfo The information related to the request.
	 * @param requestType The type of the request.
	 * @param parkName    The name of the park associated with the request.
	 */
	public Request(String sender, int requestInfo, RequestType requestType, String parkName) {
		this.sender = sender;
		this.requestInfo = requestInfo;
		this.requestType = requestType;
		this.parkName = parkName;
	}

	/**
	 * Default constructor for the Request class.
	 */
	public Request() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets the sender of the request.
	 *
	 * @param sender The sender of the request to set.
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Retrieves the sender of the request.
	 *
	 * @return The sender of the request.
	 */

	public String getSender() {
		return this.sender;
	}

	/**
	 * Sets the information related to the request.
	 *
	 * @param requestInfo The information related to the request to set.
	 */
	public void setRequestInfo(int requestInfo) {
		this.requestInfo = requestInfo;
	}

	/**
	 * Retrieves the information related to the request.
	 *
	 * @return The information related to the request.
	 */
	public int getRequestInfo() {
		return requestInfo;
	}

	/**
	 * Sets the type of the request.
	 *
	 * @param requestType The type of the request to set.
	 */
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	/**
	 * Retrieves the type of the request.
	 *
	 * @return The type of the request.
	 */

	public RequestType getRequestType() {
		return requestType;
	}

	/**
	 * Sets the name of the park associated with the request.
	 *
	 * @param parkName The name of the park to set.
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	/**
	 * Retrieves the name of the park associated with the request.
	 *
	 * @return The name of the park.
	 */

	public String getParkName() {
		return parkName;
	}

	/**
	 * Builds a string representation of the request.
	 *
	 * @param SId          The ID of the request.
	 * @param SRequestInfo The information related to the request.
	 * @param SRequestType The type of the request.
	 * @param SParkName    The name of the park associated with the request.
	 * @return A string representation of the request.
	 */
	public String bulidString(String SId, String SRequestInfo, String SRequestType, String SParkName) {
		return "Request [id=" + SId + ", requestInfo=" + SRequestInfo + ", requestType=" + SRequestType + ", parkName="
				+ SParkName + "]";
	}
}
