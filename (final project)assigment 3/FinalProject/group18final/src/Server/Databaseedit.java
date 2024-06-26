package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import GoNature.*;
import GoNature.Order.OrderType;
import neededclasses.GmailEmailSender;

/**
 * The Databaseedit class provides methods for connecting to a MySQL database,
 * executing SQL queries, and handling database operations related to the
 * GoNature application.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class Databaseedit {
	/** The connection to the MySQL database. */
	public static Connection conn = null;

	/**
	 * Establishes a connection to the MySQL database.
	 *
	 * @param name     the name of the database
	 * @param username the username for database authentication
	 * @param password the password for database authentication
	 * @return "success" if the connection is successful, "error" otherwise
	 * @throws ParseException if there is an error parsing date and time strings
	 * @author [Author Name]
	 */
	public static String ConnectToDB(String name, String username, String password) throws ParseException {
		String dbconnector = "jdbc:mysql://localhost:3306/" + name + "?serverTimezone=IST";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception var2) {
			System.out.println("Driver definition failed");
		}

		try {
			conn = DriverManager.getConnection(dbconnector, username, password);
			System.out.println("SQL connection succeed");
			return "success";
		} catch (SQLException var1) {
			System.out.println("SQLException: " + var1.getMessage());
			System.out.println("SQLState: " + var1.getSQLState());
			System.out.println("VendorError: " + var1.getErrorCode());
			return "error";
		}

	}

	/**
	 * Executes a SQL query with optional parameters.
	 *
	 * @param query     the SQL query to execute
	 * @param paramters the parameters for the SQL query (can be an Object array or
	 *                  a single Object)
	 * @return a ResultSet containing the results of the query
	 * @throws SQLException if a database access error occurs or the SQL query is
	 *                      invalid
	 */
	private static ResultSet executeQuery(String query, Object paramters) throws SQLException {
		PreparedStatement statement;
		ResultSet results;
		statement = conn.prepareStatement(query);
		if (paramters instanceof Object[]) {
			Object[] para = (Object[]) paramters;
			for (int i = 0; i < para.length; i++) {
				statement.setObject(i + 1, para[i]);
			}
		} else {
			Object para = paramters;
			if (para != null)
				statement.setObject(1, para);
		}
		results = statement.executeQuery();
		return results;
	}

	/**
	 * Executes an SQL update query with optional parameters and returns either the
	 * number of rows affected or the auto-generated key if available.
	 *
	 * @param query     the SQL update query to execute
	 * @param paramters the parameters for the SQL query (can be an Object array or
	 *                  a single Object)
	 * @return the number of rows affected by the update, or the auto-generated key
	 *         if available
	 * @throws SQLException if a database access error occurs or the SQL query is
	 *                      invalid
	 */
	private static int executeUpdate(String query, Object paramters) throws SQLException {
		PreparedStatement statement = null;
		int rowsAffected = 0;

		statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

		if (paramters instanceof Object[]) {
			Object[] para = (Object[]) paramters;
			for (int i = 0; i < para.length; i++) {
				statement.setObject(i + 1, para[i]);
			}
		} else {
			Object para = paramters;
			if (para != null)
				statement.setObject(1, para);
		}

		rowsAffected = statement.executeUpdate();
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys != null && generatedKeys.next()) {
				int generatedId = generatedKeys.getInt(1);
				return generatedId;
			} else {
				if (rowsAffected == 0)
					rowsAffected -= 1;
			}
			return rowsAffected;
		}
	}

	/**
	 * Retrieves a list of orders associated with the provided email.
	 *
	 * @param email the email address of the user whose orders are to be retrieved
	 * @return an ArrayList of Order objects representing the orders associated with
	 *         the provided email
	 * @returns Orders to show for certain client
	 * @throws SQLException if a database access error occurs or the SQL query is
	 *                      invalid
	 */
	public static ArrayList<Order> showOrders(String email) {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			String query = "SELECT * FROM `orders` WHERE `email`=?";
			ResultSet ordersFromTable = executeQuery(query, email);
			while (ordersFromTable.next()) {
				try {
					String parkName = ordersFromTable.getString("park_name");
					String timeOfVisit = ordersFromTable.getString("date_time");
					int numberOfVisitors = ordersFromTable.getInt("number_visitors");
					String telephoneNumber = ordersFromTable.getString("phone_number");
					String userEmail = ordersFromTable.getString("email");
					int id = ordersFromTable.getInt("order_id");
					Order.OrderType orderType = Order.OrderType.valueOf(ordersFromTable.getString("order_Type"));
					orders.add(new Order(id, parkName, numberOfVisitors, timeOfVisit, userEmail, telephoneNumber,
							orderType));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * Searches for a traveler's account based on the provided ID and returns the
	 * corresponding Traveler object. If the traveler is logged in elsewhere,
	 * returns null.
	 * 
	 * @param id The unique identifier of the traveler's account.
	 * @return A Traveler object representing the traveler's account if found and
	 *         not logged in elsewhere, or null if the traveler is logged in
	 *         elsewhere or if the account does not exist.
	 */
	public static Traveler searchForAccount(String id) {
		Traveler traveler = null;
		String sql = "SELECT * FROM users WHERE id = ?";
		ResultSet results = null;
		try {
			results = executeQuery(sql, id);
			results.next();
			if (results.getInt("logged_in") != 0) {
				return null;
			} else {
				String name = results.getString("name");
				String userId = results.getString("id");
				String email = results.getString("Email");
				String phoneNumber = results.getString("phoneNumber");
				traveler = new Traveler(name, userId, email, phoneNumber, results.getString("authority"));
				sql = "UPDATE users SET logged_in = 1 WHERE id = ?";
				executeUpdate(sql, id);
				return traveler;
			}
		} catch (SQLException e) {
			try {
				int approved = checkuserType(id);
				if (approved == 1)
					traveler = new Traveler(id, "Groupleader");
				else
					traveler = new Traveler(id, "regular");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return traveler;
		}
	}

	/**
	 * Searches for an employee's account based on the provided fields (username and
	 * password) and returns the corresponding Employee object.
	 * 
	 * @param fields An array containing the username and password of the employee's
	 *               account.
	 * @return An Employee object representing the employee's account if found and
	 *         not logged in elsewhere, or an Employee object with the message
	 *         "alreadylogged" if the employee is already logged in elsewhere, or
	 *         null if the account does not exist.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static Employee searchForEmpAccount(String[] fields) throws SQLException {
		Employee employee;
		String sql = "SELECT * FROM employees WHERE userName = ? AND password = ?";
		ResultSet results = executeQuery(sql, fields);
		if (results.next()) {
			if (results.getInt("logged_in") != 0) {
				employee = new Employee("alreadylogged");
				return employee;
			} else {
				sql = "UPDATE employees SET logged_in = 1 WHERE userName = ?";
				executeUpdate(sql, fields[0]);
				int empId = results.getInt("ID");
				String FirstName = results.getString("FirstName");
				String LastName = results.getString("LastName");
				String workerID = results.getString("WorkerID");
				String email = results.getString("Email");
				String role = results.getString("Role");
				String username = results.getString("UserName");
				String userPassword = results.getString("PassWord");
				String ParkID = results.getString("ParkID");
				String AuthorityLevel = results.getString("AuthorityLevel");
				return new Employee(empId, FirstName, LastName, workerID, email, role, username, userPassword, ParkID,
						AuthorityLevel);
			}
		} else {
			return null;
		}
	}

	/**
	 * Tries to add an order to the database and returns a status message indicating
	 * the outcome.
	 * 
	 * @param order The Order object representing the order to be added.
	 * @return A status message indicating the outcome of the operation: -
	 *         "alreadyhaveorder" if the user already has an order for the specified
	 *         park within the allowed time frame. - "unsuccess" if the order cannot
	 *         be added due to insufficient capacity or an SQL exception. -
	 *         "success" if the order is successfully added to the database.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static String trytoaddorder(Order order) throws SQLException {
		int time = parkvisitallowtime(order.getParkName());
		String sql = "SELECT COUNT(*) AS count_orders FROM orders WHERE email = ? AND park_name=? AND (date_time >= DATE_SUB(?, INTERVAL ? HOUR) AND date_time <= DATE_ADD(?, INTERVAL ? HOUR))";
		Object[] paramaters = { order.getEmail(), order.getParkName(), order.getTimeofVisit(), time,
				order.getTimeofVisit(), time };
		ResultSet result;
		try {
			result = executeQuery(sql, paramaters);
			result.next();
			if (result.getInt("count_orders") > 0) {
				return "alreadyhaveorder";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int countamount = 0;
		int maxamount = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(order.getTimeofVisit(), formatter);
		System.out.println(dateTime);
		try {
			int maxallowancetime = parkvisitallowtime(order.getParkName());
			String countSql = "SELECT SUM(number_visitors) AS total_visitors FROM orders WHERE park_name = ? AND date_time BETWEEN DATE_SUB(?, INTERVAL ? HOUR) AND DATE_ADD(?, INTERVAL ? HOUR)";
			Object[] countParams = { order.getParkName(), order.getTimeofVisit(), maxallowancetime,
					order.getTimeofVisit(), maxallowancetime };
			ResultSet countResults = executeQuery(countSql, countParams);
			if (countResults.next()) {
				countamount = countResults.getInt("total_visitors");
			}

			// Get available capacity for the park
			String maxSql = "SELECT max_visitors - number_difference AS available_capacity FROM parks WHERE park_name = ?";
			ResultSet maxResults = executeQuery(maxSql, order.getParkName());
			if (maxResults.next()) {
				maxamount = maxResults.getInt("available_capacity");

			}

			// Check if there's enough capacity to add the order
			if (maxamount - countamount < order.getNumberOfVisitors()) {
				return "unsuccess";
			}

			// Insert the order
			String insertSql = "INSERT INTO orders (park_name, date_time, phone_number, number_visitors, email,order_type) VALUES (?, ?, ?, ?, ?,?)";
			Object[] insertParams = { order.getParkName(), order.getTimeofVisit(), order.getPhoneNumber(),
					order.getNumberOfVisitors(), order.getEmail(), order.getOrderType().name() };
			int result1 = executeUpdate(insertSql, insertParams);
			if (result1 > 0) {
				return "success";
			} else {
				return "unsuccess";
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception for debugging
			return "unsuccess"; // Return an appropriate error message
		}
	}

	/**
	 * Adds a new traveler to the database.
	 * 
	 * @param traveler The Traveler object representing the traveler to be added.
	 * @return A status message indicating the outcome of the operation: - "success"
	 *         if the traveler is successfully added to the database. - "unsuccess"
	 *         if the traveler cannot be added due to an SQL exception or if the
	 *         traveler already exists in the database.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static String addUser(Traveler traveler) throws SQLException {
		String sql = "INSERT IGNORE  INTO users (name, id, email, phoneNumber,logged_in,authority) VALUES (?, ?, ?, ?,?,?)";
		Object[] paramters = { traveler.getName(), traveler.getId(), traveler.getEmail(), traveler.getPhoneNumber(),
				traveler.getLogged_in(), traveler.getAuthority() };
		int results = executeUpdate(sql, paramters);
		if (results > 0)
			return "success";
		return "unsuccess";

	}

	/**
	 * Logs out a user by updating the 'logged_in' status in the database.
	 * 
	 * @param id The unique identifier of the user to be logged out.
	 * @return A status message indicating the outcome of the operation: - "success"
	 *         if the user is successfully logged out. - "unsuccess" if the user
	 *         cannot be logged out due to an SQL exception or if the user does not
	 *         exist.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */

	public static String logout(String id) throws SQLException {
		String sql = "UPDATE users SET logged_in = 0 WHERE id = ?";
		int results = executeUpdate(sql, id);
		if (results > 0)
			return "success";
		return "unsuccess";
	}

	/**
	 * Deletes an order from the database and inserts it into the 'canceledorders'
	 * table.
	 * 
	 * @param orderToDelete The Order object representing the order to be deleted.
	 * @return A status message indicating the outcome of the operation: - "success"
	 *         if the order is successfully deleted from the database and inserted
	 *         into the 'canceledorders' table. - "unsuccess" if the order cannot be
	 *         deleted or if an SQL exception occurs.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static String deleteOrder(Order orderToDelete) throws SQLException {
		String sql = "DELETE FROM orders WHERE order_id = ?";
		int results = executeUpdate(sql, orderToDelete.getOrderId());
		if (results > 0) {
			sql = "INSERT INTO canceledorders (park_name, date_time, number_visitors, order_type, cancel_type) VALUES(?,?,?,?,?)";
			Object[] para = { orderToDelete.getParkName(), orderToDelete.getTimeofVisit(),
					orderToDelete.getNumberOfVisitors(), String.valueOf(orderToDelete.getOrderType()), "manual" };
			executeUpdate(sql, para);
			return "success";
		}
		return "unsuccess";

	}

	/**
	 * Adds an order to the waitlist in the database and returns the number of
	 * orders on the waitlist.
	 * 
	 * @param ord The Order object representing the order to be added to the
	 *            waitlist.
	 * @return An array containing two integers: - The first integer represents the
	 *         ID of the added order. - The second integer represents the number of
	 *         orders on the waitlist for the specified park and time.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static Integer[] addtowaitlist(Order ord) throws SQLException {
		Integer[] numorders = new Integer[2];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(ord.getTimeofVisit(), formatter);
		// String sql = "UPDATE users SET logged_in = 0 WHERE id = ?";
		String countSql = "SELECT COUNT(*) AS num_orders FROM waitlist WHERE date_time BETWEEN DATE_SUB(?, INTERVAL 4 HOUR) AND DATE_ADD(?, INTERVAL 4 HOUR) AND park_name=?";
		PreparedStatement stmt = conn.prepareStatement(countSql);
		stmt.setObject(1, dateTime);
		stmt.setObject(2, dateTime);
		stmt.setString(3, ord.getParkName());
		ResultSet resultSet = stmt.executeQuery();
		if (resultSet.next()) {
			numorders[1] = resultSet.getInt("num_orders");
		}
		String insertSql = "INSERT INTO waitlist (orderer_id,park_name, date_time,phone_number,number_visitors,email,order_type) VALUES (?,?,?,?,?,?,?);";
		Object[] para = { ord.getOrderId(), ord.getParkName(), ord.getTimeofVisit(), ord.getPhoneNumber(),
				ord.getNumberOfVisitors(), ord.getEmail(), String.valueOf(ord.getOrderType()) };
		int ordid = executeUpdate(insertSql, para);
		numorders[0] = ordid;
		return numorders;
	}

	/**
	 * Updates the waitlist by moving orders to the confirmed orders list based on
	 * available capacity.
	 * 
	 * @param order The Order object representing the order details.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static void updatewaitlist(Order order) throws SQLException {
		int number = order.getNumberOfVisitors();
		String sql = "SELECT * FROM waitlist WHERE park_name=? AND date_time BETWEEN DATE_SUB(?, INTERVAL ? HOUR) AND DATE_ADD(?, INTERVAL ? HOUR) ORDER BY order_id ASC";
		int timeallowance = parkvisitallowtime(order.getParkName());
		Object[] paramters = { order.getParkName(), order.getTimeofVisit(), timeallowance, order.getTimeofVisit(),
				timeallowance };
		ResultSet result = executeQuery(sql, paramters);

		while (result.next()) {
			if (result.getInt("number_visitors") <= number) {
				number -= result.getInt("number_visitors");
				String parkName = result.getString("park_name");
				String timeOfVisit = result.getString("date_time");
				int numberOfVisitors = result.getInt("number_visitors");
				String telephoneNumber = result.getString("phone_number");
				String userEmail = result.getString("email");
				int id = result.getInt("orderer_id");
				String id1 = String.valueOf(id);
				int approved = checkuserType(id1);
				String authority;
				if (approved == 1)
					authority = "Groupleader";
				else
					authority = "regular";
				Traveler traveler = new Traveler("pier", id1, userEmail, telephoneNumber, authority);
				traveler.setLogged_in(0);
				addUser(traveler);
				sql = "INSERT INTO orders (park_name, date_time, phone_number,number_visitors,email,order_type) VALUES (?, ?, ?, ?,?,?)";
				paramters = new Object[] { parkName, timeOfVisit, telephoneNumber, numberOfVisitors, userEmail,
						result.getString("order_Type") };
				executeUpdate(sql, paramters);
				sql = "DELETE FROM waitlist WHERE date_time = ? AND email = ?";
				paramters = new String[] { timeOfVisit, userEmail };
				executeUpdate(sql, paramters);
				String msgtosend = "Hello, we would like to inform you that the order you were in the wait for has been accepted\n and you've been signed into the system";
				GmailEmailSender.sendEmail(traveler.getEmail(), "update regarding your waitlist gonature", msgtosend);
			} else
				break;
		}
	}

	/**
	 * Checks the availability of a park for visitation based on the current number
	 * of visitors and existing orders.
	 * 
	 * @param parkName The name of the park to check availability for.
	 * @return The available capacity of the park for visitation.
	 * @throws SQLException If an SQL exception occurs during database operations.
	 */
	public static int checkpark(String parkName) throws SQLException {
		int available = 0;
		int maxallowedtime = parkvisitallowtime(parkName);
		Object[] paramters = { parkName, maxallowedtime };
		String sql = "SELECT SUM(number_visitors) AS total_visitors FROM visits WHERE park_name=? AND entry_time <= DATE_SUB(NOW(), INTERVAL ? HOUR)  AND exit_time is NULL;";
		ResultSet results = executeQuery(sql, paramters);
		if (results.next())
			available = results.getInt("total_visitors");
		sql = "UPDATE visits SET exit_time = DATE_ADD(entry_time, INTERVAL ? HOUR) WHERE entry_time < DATE_SUB(entry_time, INTERVAL ? HOUR) AND exit_time IS NULL";
		Integer[] max = { maxallowedtime, maxallowedtime };
		executeUpdate(sql, max);
		sql = "UPDATE parks SET current_visitors = current_visitors - ? WHERE park_Name = ?";
		Object[] update = { available, parkName };
		executeUpdate(sql, update);
		sql = "SELECT max_visitors, current_visitors FROM parks WHERE park_name = ?";
		results = executeQuery(sql, parkName);
		if (results.next()) {
			available = results.getInt("max_visitors") - results.getInt("current_visitors");
		}
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.print(dateTime);
		sql = "SELECT SUM(number_visitors) AS total_visitors FROM orders WHERE park_name=? AND date_time BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL ? HOUR)";
		results = executeQuery(sql, paramters);
		if (results.next()) {
			available -= results.getInt("total_visitors");
		}
		return available;
	}

	/**
	 * Checks the type of user approval for group guide based on the provided ID.
	 * 
	 * @param id The ID of the user to check.
	 * @return An integer representing the approval status. Returns 0 if no approval
	 *         found.
	 * @throws SQLException If a database access error occurs.
	 */
	public static int checkuserType(String id) throws SQLException {
		String sql = "SELECT approved FROM groupleaderrequest WHERE id = ?";
		ResultSet results = executeQuery(sql, id);
		if (results.next()) {
			return results.getInt("approved");
		} else
			return 0;
	}

	/**
	 * Records a visit order and updates the current visitors count for the park.
	 * 
	 * @param ord The order object representing the visit details.
	 * @return The ID of the recorded order.
	 * @throws SQLException If a database access error occurs.
	 */
	public static int visits(Order ord) throws SQLException {
		String sql = "INSERT INTO visits (park_name,order_type,number_visitors,entry_time) VALUES (?, ?,?,?);";
		Object[] paramters = { ord.getParkName(), ord.getOrderType().name(), ord.getNumberOfVisitors(),
				ord.getTimeofVisit() };
		int orderid = executeUpdate(sql, paramters);
		sql = "UPDATE parks SET current_visitors = current_visitors + ? WHERE park_Name = ?";
		paramters = new Object[] { ord.getNumberOfVisitors(), ord.getParkName() };
		executeUpdate(sql, paramters);
		return orderid;

	}

	/**
	 * Deletes an unplanned order based on the provided order ID.
	 * 
	 * @param id The ID of the order to delete.
	 * @throws SQLException If a database access error occurs.
	 */
	public static void deleteunplannedOrder(int id) throws SQLException {
		String sql = "DELETE FROM visits WHERE order_id = ?";
		executeUpdate(sql, id);

	}

	/**
	 * Retrieves details of a pre-ordered visit if the visitor arrived within the
	 * allowed time range.
	 * 
	 * @param orders An array containing the order details.
	 * @return An Order object representing the pre-ordered visit details, or null
	 *         if not found.
	 * @throws SQLException If a database access error occurs.
	 */
	public static Order arrivedpreordered(String[] orders) throws SQLException {
		Order ord;
		int order_id = Integer.parseInt(orders[0]);
		int maxallowedtime = parkvisitallowtime(orders[1]);
		String sql = "SELECT * FROM orders WHERE order_id=? AND date_time BETWEEN DATE_SUB(NOW(), INTERVAL ? HOUR) AND DATE_ADD(NOW(), INTERVAL 1 HOUR)";
		Integer[] paramters = { order_id, maxallowedtime };
		ResultSet result = executeQuery(sql, paramters);
		try {
			if (result.next()) {
				if (!result.getString("park_name").equals(orders[1]))
					return null;
				ord = new Order();
				ord.setParkName(result.getString("park_name"));
				ord.setNumberOfVisitors(result.getInt("number_visitors"));
				ord.setOrderType(Order.OrderType.valueOf(result.getString("order_type")));
				ord.setTimeofVisit(result.getString("date_time"));
				sql = "DELETE FROM orders WHERE order_id=?";
				executeUpdate(sql, order_id);
				return ord;
			}
		} catch (SQLException e) {
			return null;
		}
		return null;
	}

	/**
	 * Retrieves the time limit for park visits based on the provided park name.
	 * 
	 * @param parkName The name of the park to retrieve the visit time limit.
	 * @return The time limit for park visits in hours.
	 * @throws SQLException If a database access error occurs.
	 */
	public static int parkvisitallowtime(String parkName) throws SQLException {
		String sql = "SELECT time_limit_visit FROM parks WHERE park_name=?;";
		ResultSet result = executeQuery(sql, parkName);
		if (result.next())
			return result.getInt("time_limit_visit");
		else
			throw new SQLException();
	}

	/**
	 * Retrieves available visit times for a park based on the provided order
	 * details.
	 * 
	 * @param ord The order object representing the visit details.
	 * @return An ArrayList of AvailableOrders objects representing available visit
	 *         times.
	 * @throws SQLException If a database access error occurs.
	 */
	public static ArrayList<AvailableOrders> availabletimesinpark(Order ord) throws SQLException {
		int count;
		String[] parts;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		int parktimein = parkvisitallowtime(ord.getParkName());
		ArrayList<Order> orders = new ArrayList<Order>();
		String sql = "SELECT max_visitors - number_difference AS maxallowed FROM parks WHERE park_name = ?";
		ResultSet result = executeQuery(sql, ord.getParkName());
		result.next();
		int maxallowed = result.getInt("maxallowed");
		sql = "SELECT * FROM orders WHERE park_name=?";
		result = executeQuery(sql, ord.getParkName());
		while (result.next())
			orders.add(new Order(result.getInt("order_id"), result.getString("park_name"),
					result.getInt("number_visitors"), result.getString("date_time"), result.getString("email"),
					result.getString("phone_number"), OrderType.valueOf(result.getString("order_type"))));
		LocalDateTime localDateTime = LocalDateTime.parse(ord.getTimeofVisit());
		LocalDate datetime = localDateTime.toLocalDate();
		LocalDate endTime = datetime.plusDays(7);
		ArrayList<AvailableOrders> fulltimes = new ArrayList<AvailableOrders>();
		while (datetime.isBefore(endTime) || datetime.isEqual(endTime)) {
			for (int innerTime = 8; innerTime <= 18; innerTime++) {
				count = 0;
				for (int i = 0; i < orders.size(); i++) {
					localDateTime = LocalDateTime.parse(orders.get(i).getTimeofVisit(), formatter);
					LocalDate datetime1 = localDateTime.toLocalDate();
					parts = orders.get(i).getTimeofVisit().split(" ");
					String timePart = parts[1];
					String[] timeParts = timePart.split(":");
					int hour = Integer.parseInt(timeParts[0]);
					if (datetime1.isEqual(datetime) && (innerTime + parktimein > hour && innerTime - parktimein < hour))
						count += orders.get(i).getNumberOfVisitors();

				}

				if (count + ord.getNumberOfVisitors() < maxallowed) {
					LocalDate datetime1 = datetime;
					String dateString = datetime1.toString();
					int allowancetime = innerTime + parktimein;
					String exitTime = allowancetime + ":00";
					String entryTime = innerTime + ":00";
					fulltimes.add(new AvailableOrders(ord.getParkName(), dateString, entryTime, exitTime));
				}
			}
			datetime = datetime.plusDays(1);
		}
		return fulltimes;
	}

	/**
	 * Checks if an email exists in the database.
	 * 
	 * @param email The email address to check.
	 * @return 0 if the email exists, otherwise 1.
	 */
	public static int checkEmail(String email) {
		String sql = "SELECT email FROM users WHERE email=?";
		ResultSet result;
		try {
			result = executeQuery(sql, email);
			if (result.next())
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * Updates orders and sends reminders to check if traveler is coming.
	 * 
	 * @throws SQLException If a database access error occurs.
	 */
	public static void updateIfComing() throws SQLException {
		String sql = "SELECT email FROM orders WHERE DATE(date_time) = DATE(NOW() + INTERVAL 1 DAY)";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(sql);
		String msg = "Regarding your order tomorrow at GoNature park, Please confirm your order , in order to confirm please reply to this email with yes";
		while (results.next()) {
			GmailEmailSender.sendEmail(results.getString("email"), "in regard to your order at Go Nature", msg);
		}
		sql = "SELECT * FROM orders WHERE DATE(date_time) = CURDATE()";
		results = executeQuery(sql, null);
		while (results.next()) {
			String sql1 = "INSERT INTO canceledorders (park_name, date_time, number_visitors, order_type, cancel_type) VALUES(?,?,?,?,?)";
			Object[] para = { results.getString("park_name"), results.getString("date_time"),
					results.getInt("number_visitors"), results.getString("order_type"), "notcancelednotcome" };
			executeUpdate(sql1, para);
		}
		sql = "DELETE FROM orders WHERE DATE(date_time) = CURDATE()";
		executeUpdate(sql, null);
	}

	/**
	 * Retrieves replies to update emails and processes them if coming or not.
	 * 
	 * @throws SQLException If a database access error occurs.
	 */
	public static void repliestoupdate() throws SQLException {
		List<String> emails = GmailEmailSender.fetchReplies();
		String attachtosql = "";
		String sql = "SELECT * FROM orders WHERE DATE(date_time) = DATE(NOW() + INTERVAL 1 DAY)";
		String sql1 = "DELETE FROM orders  WHERE DATE(date_time) = DATE(NOW() + INTERVAL 1 DAY)";
		if (emails.size() != 0) {
			sql += "AND email not IN(";
			sql1 += "AND email not IN(";
			for (int i = 0; i < emails.size() - 1; i++) {
				attachtosql += "'" + (emails.get(i) + "',");
			}
			attachtosql = attachtosql + "'" + emails.get(emails.size() - 1) + "')";
			sql += attachtosql;
			sql1 += attachtosql;
		}
		List<Order> deletedemails = new ArrayList<Order>();
		System.out.println(sql);
		ResultSet result = executeQuery(sql, null);
		while (result.next()) {
			String parkName = result.getString("park_name");
			String timeOfVisit = result.getString("date_time");
			int numberOfVisitors = result.getInt("number_visitors");
			String telephoneNumber = result.getString("phone_number");
			String userEmail = result.getString("email");
			int id = result.getInt("order_id");
			Order.OrderType orderType = Order.OrderType.valueOf(result.getString("order_Type"));
			deletedemails
					.add(new Order(id, parkName, numberOfVisitors, timeOfVisit, userEmail, telephoneNumber, orderType));
		}

		executeUpdate(sql1, null);
		String msg = "We're sorry to inform you that your order has been deleted as you haven't replied to the email";
		sql = "INSERT INTO canceledorders (park_name, date_time, number_visitors, order_type,cancel_type) VALUES (?, ?, ?, ?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < deletedemails.size(); i++) {
			pstmt.setString(1, deletedemails.get(i).getParkName());
			pstmt.setString(2, deletedemails.get(i).getTimeofVisit());
			pstmt.setInt(3, deletedemails.get(i).getNumberOfVisitors());
			pstmt.setString(4, String.valueOf(deletedemails.get(i).getOrderType()));
			pstmt.setString(5, "automatic");
			pstmt.addBatch(); // Add this set of values
			GmailEmailSender.sendEmail(deletedemails.get(i).getEmail(), "regarding your order in go nature", msg);
		}
		if (deletedemails.size() > 0)
			pstmt.executeBatch();

	}

	/**
	 * Retrieves statistics on canceled orders.
	 * 
	 * @return A CanceledReport object containing canceled order statistics.
	 * @throws SQLException If a database access error occurs.
	 */
	public static CanceledReport getcanceled() throws SQLException {
		String sql = "SELECT SUM(CASE WHEN cancel_type = 'automatic' THEN 1 ELSE 0 END) AS canceledAutomatic, SUM(CASE WHEN cancel_type = 'manual' THEN 1 ELSE 0 END) AS canceledManual, SUM(CASE WHEN cancel_type = 'notcancelednotcome' THEN 1 ELSE 0 END) AS notCanceledNotCome FROM canceledorders WHERE date_time >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)AND date_time <= CURDATE()";
		ResultSet result = executeQuery(sql, null);
		if (result.next()) {
			System.out.println(result.getInt("canceledAutomatic"));
			System.out.println(result.getInt("canceledManual"));
			return new CanceledReport(result.getInt("canceledAutomatic"), result.getInt("canceledManual"),
					result.getInt("notCanceledNotCome"));
		}
		return new CanceledReport(0, 0, 0);
	}

	/**
	 * Creates a total visitors report for a park for the last 30 days.
	 * 
	 * @param park The name of the park to generate the report for.
	 * @return A TotalVisitorsReport object containing total visitors statistics.
	 * @throws SQLException If a database access error occurs.
	 */
	public static TotalVisitorsReport createtotalreport(String[] park) throws SQLException {
		LocalDate currentDate = LocalDate.now();
		LocalDate thirtyDaysAgo = currentDate.minusDays(30);
		String sql = "SELECT * FROM visits WHERE park_name=? AND DATE(entry_time) BETWEEN ? AND ?";
		Object[] para = { park[0], thirtyDaysAgo, currentDate };
		int[] individual = new int[7];
		int[] group = new int[7];
		int[] family = new int[7];
		ResultSet result = executeQuery(sql, para);
		while (result.next()) {
			String datetimeString = result.getString("entry_time");
			LocalDateTime dateTime = LocalDateTime.parse(datetimeString,
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			int dayOfWeekValue = dateTime.getDayOfWeek().getValue();
			if (result.getString("order_type").equals("SMALLGROUP"))
				family[dayOfWeekValue - 1] += result.getInt("number_visitors");
			else if (result.getString("order_type").equals("SINGLE"))
				individual[dayOfWeekValue - 1] += result.getInt("number_visitors");
			else
				group[dayOfWeekValue - 1] += result.getInt("number_visitors");

		}
		return new TotalVisitorsReport(individual, group, family);

	}

	/**
	 * Adds a request to the database for the department manager to approve or not
	 * based on the checking he does.
	 * 
	 * @param request The request object to add.
	 * @return True if the request was successfully added, otherwise false.
	 * @throws SQLException If a database access error occurs.
	 */
	public static boolean addRequest(Request request) throws SQLException {
		String sql = "SELECT * FROM requests WHERE park_name=? AND request_type=?";
		String[] param = { request.getParkName(), String.valueOf(request.getRequestType()) };
		if ((executeQuery(sql, param)).next())
			return false;
		String insertSql = "INSERT INTO requests (park_name, sender, request_type, request_info) VALUES (?, ?, ?, ?)";
		Object[] para = { request.getParkName(), request.getSender(), String.valueOf(request.getRequestType()),
				request.getRequestInfo() };
		int result = executeUpdate(insertSql, para);
		if (result > 0)
			return true;
		return false;

	}

	/**
	 * Adds a total visitors report to the database.
	 * 
	 * @param report An array containing the report details.
	 * @return True if the report was successfully added, otherwise false.
	 * @throws SQLException If a database access error occurs.
	 */
	public static boolean addTotalVisitorReport(Object[] report) throws SQLException {
		int x = addReport((String) report[1], "TotalVisitorsReport", (String) report[2]);
		if (x == -1)
			return false;
		TotalVisitorsReport report1 = (TotalVisitorsReport) report[0];
		String sql = "INSERT INTO Totalvisitorsreport (report_id, individualv, groupv, familyv) VALUES (?, ?, ?, ?)";
		int[] individual = report1.getIndividualvisitsByDay();
		int[] family = report1.getFamilyvisitsByDay();
		int[] group = report1.getGroupvisitsByDay();
		String familys = "", groups = "", individuals = "";
		for (int i = 0; i < 6; i++) {
			familys += family[i] + ",";
			individuals += individual[i] + ",";
			groups += group[i] + ",";
		}
		familys += family[6];
		groups += group[6];
		individuals += individual[6];
		Object[] para = { x, individuals, groups, familys };
		int succ = executeUpdate(sql, para);
		if (succ > 0)
			return true;
		return false;
	}

	/**
	 * Adds a non-full times report to the database.
	 * 
	 * @param report An array containing the report details.
	 * @return True if the report was successfully added, otherwise false.
	 * @throws SQLException If a database access error occurs.
	 */
	public static boolean addNonFullReport(Object[] report) throws SQLException {
		int x = addReport((String) report[2], "nonfulltimeslast30days", (String) report[1]);
		if (x == -1)
			return false;
		NonFullTimesReport report1 = (NonFullTimesReport) report[0];
		String sql = "INSERT INTO NonFullTimesReport (report_id, date, info) VALUES (?,?,?)";
		for (int i = 0; i < report1.getNonFullTimes().size() - 1; i++) {
			Object[] para = { x, report1.getNonFullTimes().get(i).getDate(),
					report1.getNonFullTimes().get(i).getTimeRange() };
			executeUpdate(sql, para);

		}
		String leastvisit = "";
		for (int i = 0; i < 10; i++) {
			leastvisit += report1.getMostlyfulldays()[i] + ",";
		}
		leastvisit += report1.getMostlyfulldays()[10];
		sql = "INSERT INTO NonFullTimesReport (report_id, date, info,least_full) VALUES (?,?,?,?)";
		Object[] para = { x, report1.getNonFullTimes().get(report1.getNonFullTimes().size() - 1).getDate(),
				report1.getNonFullTimes().get(report1.getNonFullTimes().size() - 1).getTimeRange(), leastvisit };
		executeUpdate(sql, para);
		return true;

	}

	/**
	 * Generates a report on park full times on the last thirty days.
	 * 
	 * @param parkToCheck The name of the park to generate the report for.
	 * @return A NonFullTimesReport object containing park full times statistics.
	 * @throws SQLException If a database access error occurs.
	 */
	public static NonFullTimesReport parkFullReport(String[] parkToCheck) throws SQLException {
		int maxallowed = 0;
		String sql = "SELECT max_visitors FROM parks WHERE park_name = ?";
		ResultSet results = executeQuery(sql, parkToCheck[0]);
		if (results.next())
			maxallowed = results.getInt("max_visitors");
		LocalDate currentDate = LocalDate.now().minusDays(1);
		LocalDate startDate = currentDate.minusDays(30); // Start 30 days ago
		LocalDate endDate = currentDate;

		sql = "SELECT * FROM visits WHERE park_name=? AND entry_time >= ? AND entry_time <= ?";
		Object[] para = { parkToCheck[0], startDate, endDate };
		ArrayList<Visit> visits = new ArrayList<Visit>();
		ResultSet result = executeQuery(sql, para);
		while (result.next())
			visits.add(new Visit(result.getInt("id"), result.getString("park_name"), result.getString("order_type"),
					result.getInt("number_visitors"), result.getString("entry_time"), result.getString("exit_time")));
		ArrayList<String> nonFullTimes = new ArrayList<>();

		for (LocalDate date = startDate; date.isBefore(currentDate.plusDays(1)); date = date.plusDays(1)) {
			for (int innerTime = 8; innerTime <= 17; innerTime++) {
				int totalVisitorsInHour = 0;
				for (Visit visit : visits) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime entryTime = LocalDateTime.parse(visit.getEntryTime(), formatter);
					LocalDateTime exitTime = LocalDateTime.parse(visit.getExitTime(), formatter);

					if (entryTime.toLocalDate().equals(date) && entryTime.getHour() <= innerTime
							&& exitTime.getHour() >= innerTime) {
						totalVisitorsInHour += visit.getNumberVisitors();
					}
				}

				if (totalVisitorsInHour < maxallowed) { // If the park wasn't full
					nonFullTimes.add(date.toString() + " " + innerTime + ":00"); // Add non-full time with date
				}
			}
		}
		ArrayList<NonFullTimes> notFullRanges = new ArrayList<NonFullTimes>();
		String date = "";
		String startTime = "";
		String endTime = "";
		int[] leastfullhours = new int[11];
		for (int i = 0; i < nonFullTimes.size(); i++) {
			String time = nonFullTimes.get(i);
			String checkDate = time.split(" ")[0];

			if (!checkDate.equals(date)) {
				if (!startTime.isEmpty()) {
					addTimeRange(leastfullhours, notFullRanges, date, startTime, endTime);
				}
				date = checkDate;
				startTime = time.split(" ")[1];
			} else if (startTime.isEmpty()) {
				startTime = time.split(" ")[1];
			}
			if (i + 1 < nonFullTimes.size()) {
				String nextTime = nonFullTimes.get(i + 1);
				String[] part1 = time.split(" ");
				String[] part2 = nextTime.split(" ");
				int currentHour = Integer.parseInt(part1[1].split(":")[0]);
				int nextHour = Integer.parseInt(part2[1].split(":")[0]);
				if (!(part1[0].equals(part2[0]) && nextHour - currentHour == 1)) {
					endTime = time.split(" ")[1];
					addTimeRange(leastfullhours, notFullRanges, date, startTime, endTime);
					startTime = "";
				}
			} else {
				addTimeRange(leastfullhours, notFullRanges, date, startTime, endTime);
			}
		}
		return new NonFullTimesReport(notFullRanges, leastfullhours);
	}

	private static void addTimeRange(int[] leastfullhours, ArrayList<NonFullTimes> notFullRanges, String date,
			String startTime, String endTime) {
		int hours = Integer.parseInt(endTime.split(":")[0]) + 1;
		if (hours >= 10)
			endTime = hours + ":00";
		else
			endTime = "0" + hours + ":00";
		notFullRanges.add(new NonFullTimes(date, " from " + startTime + " to " + endTime + " wasn't full"));
		int starthour = Integer.parseInt(startTime.split(":")[0]);
		int endhour = Integer.parseInt(endTime.split(":")[0]);
		for (int i = starthour; i < endhour; i++) {
			leastfullhours[i - 8]++;
			System.out.println(leastfullhours[2]);
		}
	}

	/**
	 * Adds a report to the database.
	 * 
	 * @param parkName   The name of the park the report is for.
	 * @param reportType The type of the report.
	 * @param sender     The sender of the report.
	 * @return The ID of the added report, or -1 if the report already exists.
	 * @throws SQLException If a database access error occurs.
	 */
	public static int addReport(String parkName, String reportType, String sender) throws SQLException {
		String sql = "SELECT * FROM reports WHERE park_name = ? AND  report_type = ? AND  DATE(date_created)=?";
		LocalDate today = LocalDate.now();
		Object[] para = { parkName, reportType, today };
		ResultSet result = executeQuery(sql, para);
		if (result.next())
			return -1;
		sql = "INSERT INTO reports (park_name, sender, report_type) VALUES (?, ?, ?)";
		para = new Object[] { parkName, sender, reportType };
		int x = executeUpdate(sql, para);
		return x;

	}

	/**
	 * 
	 * Retrieves all requests from the database.
	 * 
	 * @return An ArrayList containing all requests retrieved from the database.
	 * @throws SQLException If a database access error occurs.
	 */
	public static ArrayList<Request> retrieveRequests() throws SQLException {
		ArrayList<Request> requests = new ArrayList<Request>();
		String sql = "SELECT * FROM requests";
		ResultSet result = executeQuery(sql, null);
		while (result.next())
			requests.add(new Request(result.getString("sender"), result.getInt("request_info"),
					Request.RequestType.valueOf(result.getString("request_type")), result.getString("park_name")));
		return requests;
	}

	/**
	 * Applies a request to update park information.
	 * 
	 * @param request The request object containing the update details.
	 * @return True if the request was successfully applied, otherwise false.
	 */
	public static boolean applyRequest(Request request) {
		String sql;
		if (request.getRequestType().equals(Request.RequestType.PARK_CAPACITY)) {
			sql = "UPDATE parks SET max_visitors = ? WHERE park_name = ?";
		} else if (request.getRequestType().equals(Request.RequestType.NUMBER_OF_DIFFERENCES))
			sql = "UPDATE parks SET number_difference = ? WHERE park_name = ?";
		else
			sql = "UPDATE parks SET time_limit_visit = ? WHERE park_name = ?";
		Object[] para = { request.getRequestInfo(), request.getParkName() };
		try {
			executeUpdate(sql, para);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * Deletes a request from the database.
	 * 
	 * @param request The request object to delete.
	 * @return True if the request was successfully deleted, otherwise false.
	 */
	public static boolean deleteRequest(Request request) {
		String sql = "DELETE FROM requests WHERE park_name=? AND request_type=?  ";
		String[] para = { request.getParkName(), String.valueOf(request.getRequestType()) };
		try {
			executeUpdate(sql, para);
		} catch (SQLException e) {
			return false;
			// TODO Auto-generated catch block
		}
		return true;

	}

	/**
	 * Retrieves all reports from the database.
	 * 
	 * @return An ArrayList of Report objects representing all reports.
	 * @throws SQLException If a database access error occurs.
	 */
	public static ArrayList<Report> getReports() throws SQLException {
		ArrayList<Report> reports = new ArrayList<Report>();
		String sql = "SELECT * FROM reports";
		ResultSet results = executeQuery(sql, null);
		while (results.next()) {
			String sender = results.getString("sender");
			String parkName = results.getString("park_name");
			int id = results.getInt("id");
			String date = results.getString("date_created");
			String reportType = results.getString("report_type");
			reports.add(new Report(id, sender, parkName, reportType, date));
		}
		return reports;

	}

	/**
	 * Retrieves a total visitors report from the database.
	 * 
	 * @param id The ID of the report to retrieve.
	 * @return A TotalVisitorsReport object containing total visitors statistics.
	 * @throws SQLException If a database access error occurs.
	 */
	public static TotalVisitorsReport grabTotalReport(int id) throws SQLException {
		String sql = "SELECT * FROM totalvisitorsreport WHERE  report_id=? ";
		ResultSet result = executeQuery(sql, id);
		if (result.next()) {
			String[] individualvs = result.getString("individualv").split(",");
			String[] groupvs = result.getString("groupv").split(",");
			String[] familyvs = result.getString("familyv").split(",");
			;
			int[] individualv = new int[7];
			int[] groupv = new int[7];
			int[] familyv = new int[7];

			for (int i = 0; i < 7; i++) {
				individualv[i] = Integer.parseInt(individualvs[i]);
				groupv[i] = Integer.parseInt(groupvs[i]);
				familyv[i] = Integer.parseInt(familyvs[i]);
			}
			return new TotalVisitorsReport(individualv, groupv, familyv);

		}
		return null;
	}

	/**
	 * Retrieves a non-full times report from the database.
	 * 
	 * @param id The ID of the report to retrieve.
	 * @return A NonFullTimesReport object containing non-full times statistics.
	 * @throws SQLException If a database access error occurs.
	 */
	public static NonFullTimesReport grabNonFullTimesReport(int id) throws SQLException {
		ArrayList<NonFullTimes> nonfulltimes = new ArrayList<NonFullTimes>();
		String sql = "SELECT * FROM nonfulltimesreport WHERE  report_id=? ";
		String least = "";
		int[] leastnum = new int[11];
		ResultSet result = executeQuery(sql, id);

		while (result.next()) {
			nonfulltimes.add(new NonFullTimes(result.getString("date"), result.getString("info")));
			if (result.getString("least_full") != null)
				least = result.getString("least_full");
		}
		String[] leasthours = least.split(",");
		for (int i = 0; i < 11; i++) {
			leastnum[i] = Integer.parseInt(leasthours[i]);
		}
		return new NonFullTimesReport(nonfulltimes, leastnum);
	}

	/**
	 * Generates a visitors type report for the last 30 days.
	 * 
	 * @return A VisitorsTypeReport object containing visitors type statistics.
	 * @throws SQLException If a database access error occurs.
	 */
	public static VisitorsTypeReport generateVisitorsReport() throws SQLException {
		String sql = "SELECT * FROM visits  WHERE entry_time >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND entry_time <= CURDATE() AND exit_time IS NOT null;";
		int[] individualv = new int[11];
		int[] familyv = new int[11];
		int[] groupv = new int[11];
		ResultSet results = executeQuery(sql, null);
		while (results.next()) {
			String entryTimePart = (results.getString("entry_time").split(" ")[1]).split(":")[0];
			String exitTimePart = (results.getString("exit_time").split(" ")[1]).split(":")[0];
			int entryTime = Integer.parseInt(entryTimePart);
			int exitTime = Integer.parseInt(exitTimePart);
			for (int i = entryTime; i <= exitTime; i++) {
				if (results.getString("order_type").equals("SMALLGROUP"))
					familyv[i - 8] += results.getInt("number_visitors");
				else if (results.getString("order_type").equals("SINGLE"))
					individualv[i - 8] += results.getInt("number_visitors");
				else
					groupv[i - 8] += results.getInt("number_visitors");

			}
		}
		return new VisitorsTypeReport(individualv, familyv, groupv);

	}

	/**
	 * Updates the exit time of visitors and adjusts the current number of visitors
	 * in the park.
	 * 
	 * @param id The ID of the visit to be updated.
	 * @return true if the exit time was successfully updated, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public static boolean exitVisitors(int id) throws SQLException {
		String sql = "SELECT * FROM visits WHERE id=? AND exit_time IS NULL";
		String parkName;
		int visitorsamount;
		ResultSet results = executeQuery(sql, id);
		if (results.next()) {
			parkName = results.getString("park_name");
			visitorsamount = results.getInt("number_visitors");
		} else
			return false;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);
		sql = "UPDATE visits SET exit_time = ? WHERE id = ?";
		Object[] para = { formattedDateTime, id };
		int amount = executeUpdate(sql, para);
		if (amount > 0) {
			sql = "UPDATE parks SET current_visitors = current_visitors - ? WHERE park_name = ?";
			para = new Object[] { visitorsamount, parkName };
			executeUpdate(sql, para);
			return true;
		}
		return false;
	}

	/**
	 * Logs out an employee by updating their login status.
	 * 
	 * @param userName The username of the employee to be logged out.
	 * @return true if the employee was successfully logged out, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public static boolean logoutEmployee(String userName) throws Exception {
		String sql = "UPDATE employees SET logged_in = 0 WHERE UserName = ?";
		try {
			executeUpdate(sql, userName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Retrieves IDs of guide requests that have not been approved yet.
	 * 
	 * @return An ArrayList containing the IDs of guide requests.
	 * @throws SQLException If a database access error occurs.
	 */
	public static ArrayList<String> getGuideRequest() throws SQLException {
		ArrayList<String> ids = new ArrayList<String>();
		String sql = "SELECT id FROM groupleaderrequest WHERE approved=0 ";
		ResultSet result = executeQuery(sql, null);
		while (result.next()) {
			ids.add(result.getString("id"));
		}
		return ids;
	}

	/**
	 * Approves a guide request by updating its approval status.
	 * 
	 * @param id The ID of the guide request to be approved.
	 * @return true if the request was successfully approved, false otherwise.
	 */
	public static boolean approveGuide(String id) {
		String sql = "UPDATE groupleaderrequest SET approved=1 WHERE id=? ";
		try {
			executeUpdate(sql, id);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Denies a guide request by removing it from the database.
	 * 
	 * @param id The ID of the guide request to be denied.
	 * @return true if the request was successfully denied, false otherwise.
	 */
	public static boolean denyGuide(String id) {
		String sql = "DELETE FROM groupleaderrequest WHERE ID=?";
		try {
			executeUpdate(sql, id);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public static boolean checkWaitListUser(Order data) throws SQLException {
		String sql = "SELECT * FROM waitlist WHERE orderer_id=? AND date_time BETWEEN DATE_SUB(?, INTERVAL ? HOUR) AND DATE_ADD(?, INTERVAL ? HOUR) AND park_name=?";
		int allowancetime = parkvisitallowtime(data.getParkName());
		String orderer_id = String.valueOf(data.getOrderId());
		Object[] para = { orderer_id, data.getTimeofVisit(), allowancetime, data.getTimeofVisit(), allowancetime,
				data.getParkName() };
		ResultSet result = executeQuery(sql, para);
		if (result.next())
			return false;
		else
			return true;
	}
}
