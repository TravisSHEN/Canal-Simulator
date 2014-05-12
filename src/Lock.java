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

	public synchronized boolean isDrain() {
		return drain;
	}

	public void setDrain(boolean drain) {
		this.drain = drain;
	}

	public synchronized boolean isOccupied() {
		return occupied;
	}

	public synchronized void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public synchronized Vessel getVessel() {
		return vessel;
	}

	public synchronized void setVessel(Vessel vessel) {
		this.vessel = vessel;
	}

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

	public synchronized void enter(Vessel vessel) {
		this.setOccupied(Param.OCCUPIED);
		this.setVessel(vessel);
		System.out.println(vessel.toString()
				+ " enter Lock to go down");

	}

	public synchronized Vessel leave() {
		Vessel vessel = null;
		try {
			vessel = (Vessel) this.getVessel().clone();
			this.setOccupied(Param.UNOCCUPIED);
			this.setVessel(null);

			System.out.println(vessel.toString() + " leaves lock ");
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vessel;
	}

}
