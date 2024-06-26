package GoNature;

import java.io.Serializable;

/**
 * Employee represents an employee working within the system. Each employee has
 * a unique ID and various details such as first name, last name, worker ID,
 * email, role, username, password, park ID, and authority level.
 *
 * This class provides methods to get and set employee details.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */

public class Employee implements Serializable {
	private int ID;
	private String FirstName;
	private String LastName;
	private String WorkerID;
	private String Email;
	private String Role;
	private String UserName;
	private String PassWord;
	private String ParkID;
	private String AuthorityLevel;

	/**
	 * Constructs an Employee object with the specified role.
	 *
	 * @param role The role of the employee.
	 */
	public Employee(String role) {
		this.Role = role;
	}

	/**
	 * Constructs an Employee object with the specified details.
	 *
	 * @param ID             The unique ID of the employee.
	 * @param FirstName      The first name of the employee.
	 * @param LastName       The last name of the employee.
	 * @param WorkerID       The worker ID of the employee.
	 * @param Email          The email address of the employee.
	 * @param Role           The role of the employee.
	 * @param UserName       The username of the employee.
	 * @param PassWord       The password of the employee.
	 * @param ParkID         The park ID associated with the employee.
	 * @param AuthorityLevel The authority level of the employee.
	 */
	public Employee(int iD, String firstName, String lastName, String workerID, String email, String role,
			String userName, String passWord, String parkID, String authorityLevel) {
		super();
		ID = iD;
		FirstName = firstName;
		LastName = lastName;
		WorkerID = workerID;
		Email = email;
		Role = role;
		UserName = userName;
		PassWord = passWord;
		ParkID = parkID;
		AuthorityLevel = authorityLevel;
	}

	/**
	 * Retrieves the unique ID of the employee.
	 *
	 * @return The ID of the employee.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the unique ID of the employee.
	 *
	 * @param ID The ID to set.
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Retrieves the first name of the employee.
	 *
	 * @return The first name of the employee.
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * Sets the first name of the employee.
	 *
	 * @param FirstName The first name to set.
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * Retrieves the last name of the employee.
	 *
	 * @return The last name of the employee.
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * Sets the last name of the employee.
	 *
	 * @param LastName The last name to set.
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * Retrieves the worker ID of the employee.
	 *
	 * @return The worker ID of the employee.
	 */
	public String getWorkerID() {
		return WorkerID;
	}

	/**
	 * Sets the worker ID of the employee.
	 *
	 * @param WorkerID The worker ID to set.
	 */
	public void setWorkerID(String workerID) {
		WorkerID = workerID;
	}

	/**
	 * Retrieves the email address of the employee.
	 *
	 * @return The email address of the employee.
	 */

	public String getEmail() {
		return Email;
	}

	/**
	 * Sets the email address of the employee.
	 *
	 * @param Email The email address to set.
	 */

	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * Retrieves the role of the employee.
	 *
	 * @return The role of the employee.
	 */
	public String getRole() {
		return Role;
	}

	/**
	 * Sets the role of the employee.
	 *
	 * @param Role The role to set.
	 */
	public void setRole(String role) {
		Role = role;
	}

	/**
	 * Retrieves the username of the employee.
	 *
	 * @return The username of the employee.
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * Sets the username of the employee.
	 *
	 * @param UserName The username to set.
	 */

	public void setUserName(String userName) {
		UserName = userName;
	}

	/**
	 * Retrieves the password of the employee.
	 *
	 * @return The password of the employee.
	 */
	public String getPassWord() {
		return PassWord;
	}

	/**
	 * Sets the password of the employee.
	 *
	 * @param PassWord The password to set.
	 */
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	/**
	 * Retrieves the park ID associated with the employee.
	 *
	 * @return The park ID associated with the employee.
	 */
	public String getParkID() {
		return ParkID;
	}

	/**
	 * Sets the park ID associated with the employee.
	 *
	 * @param ParkID The park ID to set.
	 */
	public void setParkID(String parkID) {
		ParkID = parkID;
	}

	/**
	 * Retrieves the authority level of the employee.
	 *
	 * @return The authority level of the employee.
	 */

	public String getAuthorityLevel() {
		return AuthorityLevel;
	}

	/**
	 * Sets the authority level of the employee.
	 *
	 * @param authorityLevel The authority level to set.
	 */
	public void setAuthorityLevel(String authorityLevel) {
		AuthorityLevel = authorityLevel;
	}
}
