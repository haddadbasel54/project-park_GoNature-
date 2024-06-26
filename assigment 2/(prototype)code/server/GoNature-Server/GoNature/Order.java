package GoNature;

import java.io.Serializable;

public class Order implements Serializable {
	private String parkName;
	private String orderNumber;
	private String timeofVisit;
	private int numberOfVisitors;
	private String telephoneNumber;
	private String email;
	public Order() {
	}
	public Order(String parkName,String orderNumber,String timeOfVisit,int numberOfVisitors,String telephoneNumber,String email)
	{
		this.parkName=parkName;
		this.orderNumber=orderNumber;
		this.timeofVisit=timeOfVisit;
		this.numberOfVisitors=numberOfVisitors;
		this.telephoneNumber=telephoneNumber;
		this.email=email;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getTimeofVisit() {
		return timeofVisit;
	}
	public void setTimeofVisit(String timeofVisit) {
		this.timeofVisit = timeofVisit;
	}
	public int getNumberOfVisitors() {
		return numberOfVisitors;
	}
	public void setNumberOfVisitors(int numberOfVisitors) {
		this.numberOfVisitors = numberOfVisitors;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Order [parkName=" + parkName + ", orderNumber=" + orderNumber + ", timeofVisit=" + timeofVisit
				+ ", numberOfVisitors=" + numberOfVisitors + ", telephoneNumber=" + telephoneNumber + ", email=" + email
				+ "]";
	}
}
