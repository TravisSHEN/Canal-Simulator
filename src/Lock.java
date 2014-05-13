/**
 * Author: Litao Shen
 */
import java.util.concurrent.atomic.AtomicInteger;

// TODO: Auto-generated Javadoc
/**
 * The Class Lock.
 */
public class Lock implements LockInterface {

    /** The occupied; The drain. */
    private volatile boolean occupied, drain;

    /** The chamber enabled. */
    private volatile boolean chamberEnabled;

    /** The count: used for counting how many vessels in canal. */
    protected volatile AtomicInteger count;

    // occupied by the vessel;
    /** The vessel in lock. */
    private volatile Vessel vessel;

    /**
     * Instantiates a new lock.
     */
    public Lock() {
        this.occupied = false;
        this.drain = true;
        this.count = new AtomicInteger(0);
        this.vessel = null;
        this.chamberEnabled = false;
    }

    /**
     * Prints the out conditions.
     */
    public synchronized void printOutConditions() {
        System.out.println("==================== Condition in Lock"
                + " ====================");
        System.out.println("Occupied            : " + this.isOccupied());
        System.out.println("Occupied by Vessel  : " + vessel);
        System.out.println("Chamber             : "
                + (this.isDrain() ? "drains" : "fills"));
        System.out.println("==================== END ====================");
    }

    /**
     * Operate water level. If there is a vessel in lock, check vessel is
     * outbound or inbound. If it is outbound, lower the water level, otherwise,
     * fills lock, which takes operate time.
     */
    public synchronized void operateWaterLevel() {
        if (this.vessel != null) {
            // takes OPERATE_TIME to operate chamber to raise/lower water level.
            try {
                Thread.sleep(Param.OPERATE_TIME);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (this.vessel.isOutbound()) {
                this.setDrain(Param.DRAINED);
//                this.drain = Param.DRAINED;
            } else {
//                this.drain = Param.UNDRAINED;
                this.setDrain(Param.UNDRAINED);
            }
            
        }
    }

    /**
     * Enter: Let vessel enter lock when lock is not occupied.
     * 
     * @param vessel
     *            the vessel
     */
    public synchronized void enter(Vessel vessel) {
        // when there is a vessel already in lock,
        // next incoming vessel has to wait.
        while (this.isOccupied()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.setOccupied(Param.OCCUPIED);
        this.setVessel(vessel);
        // notify all thread waiting for this object changing.
        this.notifyAll();

    }

    /**
     * Leave: let vessel leaves lock.
     * 
     * @return the vessel
     */
    public synchronized Vessel leave() {
        // when there is no vessel in lock, thread waits until waken up.
        while (!this.isOccupied()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Vessel vessel = (Vessel) this.getVessel().clone();
        this.setOccupied(Param.UNOCCUPIED);
        this.setVessel(null);
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
     * @param occupied
     *            the new occupied
     */
    public synchronized void setOccupied(boolean occupied) {
        this.occupied = occupied;
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
     * @param drain
     *            the new drain
     */
    public synchronized void setDrain(boolean drain) {
        this.drain = drain;
        System.out.println("Chamber " + (this.drain ? "drains" : "fills"));
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

    /**
     * Gets the count.
     * 
     * @return the count
     */
    public synchronized int getCount() {
        return count.get();
    }

    /**
     * Increment count.
     */
    public synchronized void incrementCount() {
        this.count.getAndIncrement();
    }

    /**
     * Decrement count.
     */
    public synchronized void decrementCount() {
        this.count.getAndDecrement();
    }

    /**
     * Checks if is chamber enabled.
     * 
     * @return true, if is chamber enabled
     */
    public synchronized boolean isChamberEnabled() {
        return chamberEnabled;
    }

    /**
     * Sets the chamber enabled.
     * 
     * @param chamberEnabled
     *            the new chamber enabled
     */
    public synchronized void setChamberEnabled(boolean chamberEnabled) {
        this.chamberEnabled = chamberEnabled;
    }

}
