package GoNature;

import java.io.Serializable;

/**
 * CanceledReport represents a report containing information about canceled
 * automatic, manual, and not-come orders, along with their corresponding
 * percentages.
 *
 * This class calculates the percentages of canceled orders based on the
 * provided counts of canceled automatic, canceled manual, and not-come orders.
 *
 * The percentages are calculated as follows: canceledAutomaticP =
 * (canceledAutomatic / totalcanceled) * 100 canceledManualP = (canceledManual /
 * totalcanceled) * 100 notCanceledNotComeP = (notCanceledNotCome /
 * totalcanceled) * 100
 *
 * Note: If the total canceled orders are 0, the percentage values will not be
 * calculated to avoid division by zero.
 *
 * @author Pier Mbariky
 * @author Redan Ganim
 * @author Bassel Haddad
 * @author Joel Hourany
 * @author Asaad Sajim
 * @author Nadine Halabi
 */
public class CanceledReport implements Serializable {
	private int canceledAutomatic;
	private int canceledManual;
	private int notCanceledNotCome;
	private double canceledAutomaticP;
	private double canceledManualP;
	private double notCanceledNotComeP;
	private int totalcanceled;

	/**
	 * Constructs a CanceledReport object with the given counts of canceled
	 * automatic, canceled manual, and not-come orders.
	 *
	 * @param canceledAutomatic  The count of canceled automatic orders.
	 * @param canceledManual     The count of canceled manual orders.
	 * @param notCanceledNotCome The count of orders that were not canceled and not
	 *                           come.
	 */
	public CanceledReport(int canceledAutomatic, int canceledManual, int notCanceledNotCome) {
		this.canceledAutomatic = canceledAutomatic;
		this.canceledManual = canceledManual;
		this.notCanceledNotCome = notCanceledNotCome;
		totalcanceled += canceledAutomatic + canceledManual + notCanceledNotCome;
		if (totalcanceled > 0) {
			this.canceledAutomaticP = ((double) canceledAutomatic / totalcanceled) * 100;
			this.canceledManualP = ((double) canceledManual / totalcanceled) * 100;
			this.notCanceledNotComeP = ((double) notCanceledNotCome / totalcanceled) * 100;
		}
	}

	// Getters and setters for instance variables...
	public int getCanceledAutomatic() {
		return canceledAutomatic;
	}

	public void setCanceledAutomatic(int canceledAutomatic) {
		this.canceledAutomatic = canceledAutomatic;
	}

	public int getCanceledManual() {
		return canceledManual;
	}

	public void setCanceledManual(int canceledManual) {
		this.canceledManual = canceledManual;
	}

	public int getNotCanceledNotCome() {
		return notCanceledNotCome;
	}

	public void setNotCanceledNotCome(int notCanceledNotCome) {
		this.notCanceledNotCome = notCanceledNotCome;
	}

	public double getCanceledAutomaticP() {
		return canceledAutomaticP;
	}

	public void setCanceledAutomaticP(double canceledAutomaticP) {
		this.canceledAutomaticP = canceledAutomaticP;
	}

	public double getCanceledManualP() {
		return canceledManualP;
	}

	public void setCanceledManualP(double canceledManualP) {
		this.canceledManualP = canceledManualP;
	}

	public double getNotCanceledNotComeP() {
		return notCanceledNotComeP;
	}

	public void setNotCanceledNotComeP(double notCanceledNotComeP) {
		this.notCanceledNotComeP = notCanceledNotComeP;
	}

	public int getTotalcanceled() {
		return totalcanceled;
	}

	public void setTotalcanceled(int totalcanceled) {
		this.totalcanceled = totalcanceled;
	}

}