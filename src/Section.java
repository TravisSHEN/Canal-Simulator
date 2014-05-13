

// TODO: Auto-generated Javadoc
/**
 * The Class Section.
 */
public class Section {

	/** The section id. */
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
	 * Prints out the conditions of section.
	 */
	public synchronized void printOutConditions() {
        System.out.println("==================== Condition in Section "
                + this.getId() + " ====================");
        System.out.println("Section ID        : " + this.getId());
        System.out.println("Occupied          : " + this.isOccupied());
        System.out.println("Occupied by Vessel: " + vessel);
        System.out.println("==================== END ====================");
	}
	
	/**
	 * Enter: let vessel enter the section.
	 *
	 * @param vessel            the vessel
	 */
	public synchronized void enter(Vessel vessel) {

		this.setOccupied(Param.OCCUPIED);
		this.setVessel(vessel);
		System.out.println(vessel + " enters section " + this.getId());
		this.notifyAll();
	}

	/**
	 * Leave: let vessel leave the section.
	 *
	 * @return the vessel
	 */
	public synchronized Vessel leave() {

		Vessel vessel = (Vessel) this.getVessel().clone();
		this.setOccupied(Param.UNOCCUPIED);
		this.setVessel(null);

		System.out.println(vessel + " leaves section " + this.getId());
		this.notifyAll();

		return vessel;
	}

	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public synchronized boolean isOccupied() {
		return occupied;
	}

	/**
	 * Sets the occupied.
	 *
	 * @param occupied the new occupied
	 */
	public synchronized void setOccupied(boolean occupied) {
		this.occupied = occupied;
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
	 * @param vessel the new vessel
	 */
	public synchronized void setVessel(Vessel vessel) {
		this.vessel = vessel;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
