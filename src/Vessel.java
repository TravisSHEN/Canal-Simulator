
// TODO: Auto-generated Javadoc
/**
 * A class whose instances are vessels, each with its unique Id.
 */

public class Vessel extends Object implements Cloneable {

	// the Id of this vessel
	/** The id. */
	private int id;

	// The status of this vessel
	/** The outbound. */
	private boolean outbound;

	// the next ID to be allocated
	/** The next id. */
	protected static int nextId = 1;

	// create a new vessel with a given Id
	/**
	 * Instantiates a new vessel.
	 * 
	 * @param id
	 *            the id
	 */
	protected Vessel(int id) {
		this.id = id;
		this.outbound = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;

	}

	// get a new Vessel instance with a unique Id
	/**
	 * Gets the new vessel.
	 * 
	 * @return the new vessel
	 */
	public static Vessel getNewVessel() {
		return new Vessel(nextId++);
	}

	// produce the Id of this vessel
	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public synchronized int getId() {
		return id;
	}

	// produce an identifying string for the vessel
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public synchronized String toString() {
		return "[" + id + "] ";
	}

	/**
	 * Checks if is outbound.
	 * 
	 * @return true, if is outbound
	 */
	public synchronized boolean isOutbound() {
		return outbound;
	}

	/**
	 * Sets the outbound.
	 * 
	 * @param outbound
	 *            the new outbound
	 */
	public synchronized void setOutbound(boolean outbound) {
		this.outbound = outbound;
	}

}
