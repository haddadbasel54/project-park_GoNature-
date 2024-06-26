package GoNature;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Order represents a reservation order made by a visitor to visit a park. Each
 * order contains details such as order ID, park name, time of visit, number of
 * visitors, email, phone number, and order type.
 *
 * This class provides methods to retrieve and modify order details.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class Order implements Serializable {
	/**
	 * Enum representing different types of orders.
	 */
	public enum OrderType {
		SINGLE, SMALLGROUP, GROUP
	}

	private int orderId;
	private String parkName;
	private String timeofVisit;
	private String phoneNumber;
	private int numberOfVisitors;
	private String email;
	private OrderType orderType;

	/**
	 * Constructs an empty Order object.
	 */

	public Order() {
	}

	/**
	 * Constructs an Order object with the specified details.
	 *
	 * @param orderId          The ID of the order.
	 * @param parkName         The name of the park associated with the order.
	 * @param numberOfVisitors The number of visitors included in the order.
	 * @param timeOfVisit      The time of visit for the order.
	 * @param email            The email address associated with the order.
	 * @param phoneNumber      The phone number associated with the order.
	 * @param orderType        The type of the order (e.g., single, small group,
	 *                         group).
	 */
	public Order(int orderId, String parkName, int numberOfVisitors, String timeOfVisit, String email,
			String phoneNumber, OrderType orderType) {
		this.orderId = orderId;
		this.parkName = parkName;
		this.timeofVisit = timeOfVisit;
		this.numberOfVisitors = numberOfVisitors;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.orderType = orderType;
	}

	/**
	 * Retrieves the name of the park associated with the order.
	 *
	 * @return The park name.
	 */
	public String getParkName() {
		return parkName;
	}

	/**
	 * Sets the name of the park associated with the order.
	 *
	 * @param parkName The park name to set.
	 */
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	/**
	 * Retrieves the time of visit for the order.
	 *
	 * @return The time of visit.
	 */
	public String getTimeofVisit() {
		return timeofVisit;
	}

	/**
	 * Sets the time of visit for the order.
	 *
	 * @param timeofVisit The time of visit to set.
	 */
	public void setTimeofVisit(String timeofVisit) {
		this.timeofVisit = timeofVisit;
	}

	/**
	 * Retrieves the number of visitors included in the order.
	 *
	 * @return The number of visitors.
	 */
	public int getNumberOfVisitors() {
		return numberOfVisitors;
	}

	/**
	 * Sets the number of visitors included in the order.
	 *
	 * @param numberOfVisitors The number of visitors to set.
	 */

	public void setNumberOfVisitors(int numberOfVisitors) {
		this.numberOfVisitors = numberOfVisitors;
	}

	/**
	 * Retrieves the email address associated with the order.
	 *
	 * @return The email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address associated with the order.
	 *
	 * @param email The email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the phone number associated with the order.
	 *
	 * @return The phone number.
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number associated with the order.
	 *
	 * @param phoneNumber The phone number to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Retrieves the ID of the order.
	 *
	 * @return The order ID.
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Retrieves the type of the order.
	 *
	 * @return The order type.
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * Sets the type of the order.
	 *
	 * @param orderType The order type to set.
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * Sets the ID of the order.
	 *
	 * @param orderId The order ID to set.
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
