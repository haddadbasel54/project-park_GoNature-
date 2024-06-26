package GoNature;

import java.io.Serializable;

/**
 * Options represents a set of options for communication between client and
 * server. This class contains an enum representing different communication
 * options and an object to store associated data.
 *
 * This class is typically used to specify the type of operation to be performed
 * during client-server communication.
 *
 * Each option corresponds to a specific operation, such as retrieving order
 * lists, adding orders, logging in, etc.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */

public class Options implements Serializable {
	/**
	 * Enum representing different communication options.
	 */
	public enum Option {
		RETRIEVE_ORDER_LIST, ADD_ORDER, DELETE_ORDER, LOGIN, LOGOUT, LOGIN_EMPLOYEE, ADD_USER, UNSUCCESS, WAIT_LIST,
		WAIT_LISTCHECK, WAIT_LISTUPDATE, ALREADYLOGGED, CHECKPARK, CHECKUSERTYPE, UNPLANNEDORDER, ORDER_LIST,
		CHECK_EMAIL, CANCELED_REPORTS, CREATE_TOTALVISITORS, SEND_REQUEST, CREATE_NONFULLTIMESREPORT, SEND_REPORT,
		RETRIEVE_REQUEST_LIST, APPLY_REQUEST, DELETE_REQUEST, GET_REPORTS, SELECTED_REPORT, GENERATE_VISITORSREPORT,
		EXIT_VISIT, DISCONNECT, LOGOUT_EMPLOYEE, GUIDE_REQUESTS, APPROVE_GUIDE, DENY_GUIDE,CHECK_WAITINGLIST;
	}

	private Object data;
	private Option option;

	/**
	 * Constructs an Options object with no initial data and option.
	 */
	public Options() {
	}

	/**
	 * Constructs an Options object with the specified data and option.
	 *
	 * @param data   The data associated with the option.
	 * @param option The option specifying the type of operation.
	 */
	public Options(Object data, Option option) {
		this.data = data;
		this.option = option;
	}

	/**
	 * Retrieves the data associated with the option.
	 *
	 * @return The data associated with the option.
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data associated with the option.
	 *
	 * @param data The data to set.
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Retrieves the option specifying the type of operation.
	 *
	 * @return The option specifying the type of operation.
	 */
	public Option getOption() {
		return option;
	}

	/**
	 * Sets the option specifying the type of operation.
	 *
	 * @param option The option to set.
	 */
	public void setOption(Option option) {
		this.option = option;
	}
}