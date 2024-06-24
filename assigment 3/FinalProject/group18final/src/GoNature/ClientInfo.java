package GoNature;

/**
 * ClientInfo represents information about a client, including host, IP address,
 * and status. This class is typically used to store details about a client
 * connected to a server.
 *
 * The host represents the hostname of the client machine, the IP represents the
 * IP address of the client, and the status represents the current status of the
 * client.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */

public class ClientInfo {
	private String host;
	private String ip;
	private String status;

	/**
	 * Constructs a ClientInfo object with the specified details.
	 *
	 * @param host   The hostname of the client machine.
	 * @param ip     The IP address of the client.
	 * @param status The status of the client.
	 */
	public ClientInfo(String host, String ip, String status) {
		this.ip = ip;
		this.host = host;
		this.status = status;
	}
	 /**
     * Retrieves the hostname of the client machine.
     *
     * @return The hostname of the client.
     */
	public String getHost() {
		return this.host;
	}
	/**
     * Sets the hostname of the client machine.
     *
     * @param host The hostname to set.
     */
	public void setHost(String host) {
		this.host = host;
	}
	/**
     * Retrieves the IP address of the client.
     *
     * @return The IP address of the client.
     */
	public String getIp() {
		return this.ip;
	}
	 /**
     * Sets the IP address of the client.
     *
     * @param ip The IP address to set.
     */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
     * Retrieves the status of the client.
     *
     * @return The status of the client.
     */
	public String getStatus() {
		return this.status;
	}
	/**
     * Sets the status of the client.
     *
     * @param status The status to set.
     */
	public void setStatus(String status) {
		this.status = status;
	}
}
