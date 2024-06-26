package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import GoNature.Order;

public class Databaseedit {
	public static Connection conn = null;

	public static void ConnectToDB() throws ParseException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception var2) {
			System.out.println("Driver definition failed");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gonature?serverTimezone=IST", "root",
					"lolo1322567");
			System.out.println("SQL connection succeed");
		} catch (SQLException var1) {
			System.out.println("SQLException: " + var1.getMessage());
			System.out.println("SQLState: " + var1.getSQLState());
			System.out.println("VendorError: " + var1.getErrorCode());
		}

	}

	public static ArrayList<Order> ShowOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {

			String Query = "SELECT * FROM `Order`";
			Statement stmt = conn.createStatement();
			ResultSet ordersfromtable = stmt.executeQuery(Query);
			while (ordersfromtable.next()) {
				try {
					String parkName = ordersfromtable.getString("ParkName");
					String orderNumber = ordersfromtable.getString("OrderNumber");
					String timeOfVisit = ordersfromtable.getString("TimeOfVisit");
					int numberofVisitors = ordersfromtable.getInt("NumberOfVisitors");
					String telephoneNumber = ordersfromtable.getString("telephoneNumber");
					String email = ordersfromtable.getString("email");
					orders.add(new Order(parkName, orderNumber, timeOfVisit, numberofVisitors, telephoneNumber, email));
				} catch (SQLException var) {
					var.printStackTrace();
				}
			}
		} catch (SQLException var12) {
			var12.printStackTrace();
		}
		return orders;
	}

	public static String saveNewEditedOrders(ArrayList<Order> newEditedOrders) throws SQLException {
		String sql = "UPDATE `Order` SET `ParkName` = ?, `TimeOfVisit` = ?, `NumberOfVisitors` = ?, `TelephoneNumber` = ?, `Email` = ? WHERE `OrderNumber` = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			for (Order order : newEditedOrders) {
				statement.setString(1, order.getParkName());
				statement.setString(2, order.getTimeofVisit());
				statement.setString(3, String.valueOf(order.getNumberOfVisitors()));
				statement.setString(4, order.getTelephoneNumber());
				statement.setString(5, order.getEmail());
				statement.setString(6, order.getOrderNumber());
				statement.executeUpdate();
			}
			statement.close();
			return("Successfully saved");
		} catch (SQLException var12) {
			return("failed to save");
		}
	}
}
