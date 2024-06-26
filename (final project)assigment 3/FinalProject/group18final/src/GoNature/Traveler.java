package GoNature;

import java.io.Serializable;

/**
 * The Traveler class represents a traveler who may have booked visits to parks.
 * It stores information such as name, ID, email, phone number, and authority
 * level. This class provides methods to retrieve and modify traveler details.
 * 
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class Traveler implements Serializable {
	private String name;
	private String id;
	private String email;
	private String phoneNumber;
	private String authority;
	private int logged_in;

	/**
	 * Constructs a Traveler object with the specified ID and authority level.
	 *
	 * @param id        The ID of the traveler.
	 * @param authority The authority level of the traveler.
	 */
	public Traveler(String id, String authority) {
		this.id = id;
		this.authority = authority;
	}

	/**
	 * Constructs a Traveler object with the specified details.
	 *
	 * @param name        The name of the traveler.
	 * @param id          The ID of the traveler.
	 * @param email       The email address of the traveler.
	 * @param phoneNumber The phone number of the traveler.
	 * @param authority   The authority level of the traveler.
	 */
	public Traveler(String name, String id, String email, String phoneNumber, String authority)

	{
		this.name = name;
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.authority = authority;
	}

	/**
	 * Retrieves the name of the traveler.
	 *
	 * @return The name of the traveler.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the traveler.
	 *
	 * @param name The name of the traveler to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the ID of the traveler.
	 *
	 * @return The ID of the traveler.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the ID of the traveler.
	 *
	 * @param id The ID of the traveler to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Retrieves the email address of the traveler.
	 *
	 * @return The email address of the traveler.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the traveler.
	 *
	 * @param email The email address of the traveler to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Retrieves the phone number of the traveler.
	 *
	 * @return The phone number of the traveler.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number of the traveler.
	 *
	 * @param phoneNumber The phone number of the traveler to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Retrieves the authority level of the traveler.
	 *
	 * @return The authority level of the traveler.
	 */
	public String getAuthority() {
		return authority;
	}

	/**
	 * Sets the authority level of the traveler.
	 *
	 * @param authority The authority level of the traveler to set.
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getLogged_in() {
		return logged_in;
	}

	public void setLogged_in(int logged_in) {
		this.logged_in = logged_in;
	}

}
