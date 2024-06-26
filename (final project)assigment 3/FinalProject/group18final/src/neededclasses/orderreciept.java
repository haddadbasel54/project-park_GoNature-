package neededclasses;

import GoNature.Order;

/**
 * The OrderReceipt class provides methods to calculate the price for unplanned
 * and planned orders.
 */
public class orderreciept {
	/**
	 * Calculates the price for an unplanned order.
	 *
	 * @param order The order for which the price is to be calculated.
	 * @return The total price for the unplanned order.
	 */
	public static float priceforunplanned(Order order) {
		if (order.getOrderType().equals(Order.OrderType.SINGLE)
				|| order.getOrderType().equals(Order.OrderType.SMALLGROUP)) {
			return 100 * order.getNumberOfVisitors();
		} else
			return 100 * order.getNumberOfVisitors() * (float) 0.9;

	}

	/**
	 * Calculates the price for a planned order.
	 *
	 * @param order The order for which the price is to be calculated.
	 * @return The total price for the planned order.
	 */
	public static float priceforplanned(Order order) {
		if (order.getOrderType().equals(Order.OrderType.SINGLE)
				|| order.getOrderType().equals(Order.OrderType.SMALLGROUP)) {
			return 100 * order.getNumberOfVisitors() * (float) 0.85;
		} else
			return 100 * (order.getNumberOfVisitors() - 1) * (float) 0.75;

	}

}
