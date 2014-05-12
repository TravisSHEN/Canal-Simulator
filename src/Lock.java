/*
 * 
 */
import java.util.concurrent.atomic.AtomicInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class Lock.
 */
public class Lock {

	/** The drain. */
	private boolean occupied, drain;

	/** The count. */
	protected AtomicInteger count;

	// occupied by the vessel;
	/** The vessel. */
	private volatile Vessel vessel;

	/**
	 * Instantiates a new lock.
	 */
	public Lock() {
		this.occupied = false;
		this.drain = true;
		this.count = new AtomicInteger(0);
		this.vessel = null;
	}

	/**
	 * Checks if is drain.
	 *
	 * @return true, if is drain
	 */
	public synchronized boolean isDrain() {
		return drain;
	}

	/**
	 * Sets the drain.
	 *
	 * @param drain the new drain
	 */
	public void setDrain(boolean drain) {
		this.drain = drain;
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
	 * Operate water level.
	 */
	public synchronized void operateWaterLevel() {
		if (this.vessel != null) {
			try {
				Thread.sleep(Param.OPERATE_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (this.vessel.isOutbound()) {
				this.drain = Param.DRAINED;
			} else {
				this.drain = Param.UNDRAINED;
			}
		}
	}

	/**
	 * Enter.
	 *
	 * @param vessel the vessel
	 */
	public synchronized void enter(Vessel vessel) {
		this.setOccupied(Param.OCCUPIED);
		this.setVessel(vessel);

	}

	/**
	 * Leave.
	 *
	 * @return the vessel
	 */
	public synchronized Vessel leave() {
		Vessel vessel = null;
		try {
			vessel = (Vessel) this.getVessel().clone();
			this.setOccupied(Param.UNOCCUPIED);
			this.setVessel(null);

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vessel;
	}

}
