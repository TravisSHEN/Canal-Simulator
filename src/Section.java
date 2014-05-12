// TODO: Auto-generated Javadoc
/**
 * The Class Section.
 */
public class Section {

	/** The id. */
	private int id;

	/** The occupied. */
	private volatile boolean occupied;

	// occupied by ID vessel;
	/** The vessel. */
	private volatile Vessel vessel;

	/**
	 * Instantiates a new section.
	 * 
	 * @param id
	 *            the id
	 */
	public Section(int id) {
		this.id = id;
		this.occupied = false;
		this.vessel = null;
	}

	/**
	 * Checks if is occupied.
	 * 
	 * @return true, if is occupied
	 */
	public synchronized boolean isOccupied() {
		// System.out.println(this.id + " is " + (this.occupied?"occupied":
		// "empty"));
		return occupied;
	}

	/**
	 * Sets the occupied.
	 * 
	 * @param occupied
	 *            the new occupied
	 */
	public synchronized void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public synchronized int getId() {
		return id;
	}

	/**
	 * Gets the vessel.
	 * 
	 * @return the vessel
	 */
	public synchronized Vessel getVessel() {
		return vessel;
	}

	/**
	 * Sets the vessel.
	 * 
	 * @param vessel
	 *            the new vessel
	 */
	public synchronized void setVessel(Vessel vessel) {
		this.vessel = vessel;
	}

	public synchronized void enter(Vessel vessel) {
		this.setOccupied(Param.OCCUPIED);
		this.setVessel(vessel);
		System.out.println(vessel + " enters " + "section " + this.getId());
	}

	public synchronized Vessel leave() {
		Vessel vessel = null;

		try {
			vessel = (Vessel) this.getVessel().clone();
			this.setOccupied(Param.UNOCCUPIED);
			this.setVessel(null);

			System.out.println(vessel + " leaves section "
					+ this.getId());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vessel;
	}

}
