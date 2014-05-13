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

	public synchronized void printOutConditions() {
        System.out.println("==================== Condition in Section "
                + this.getId() + " ====================");
        System.out.println("Section ID        : " + this.getId());
        System.out.println("Occupied          : " + this.isOccupied());
        System.out.println("Occupied by Vessel: " + vessel);
        System.out.println("==================== END ====================");
	}
	/**
	 * Enter.
	 * 
	 * @param vessel
	 *            the vessel
	 */
	public synchronized void enter(Vessel vessel) {

		this.setOccupied(Param.OCCUPIED);
		this.setVessel(vessel);
		System.out.println(vessel + " enters section " + this.getId());
		this.notifyAll();
	}

	/**
	 * Leave.
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

	public boolean isOccupied() {
		return occupied;
	}

	public synchronized void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Vessel getVessel() {
		return vessel;
	}

	public synchronized void setVessel(Vessel vessel) {
		this.vessel = vessel;
	}

	public int getId() {
		return id;
	}

}
