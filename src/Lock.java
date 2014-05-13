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
    private volatile boolean occupied, drain;

    /** The count. */
    protected volatile AtomicInteger count;

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

    public synchronized void printOutConditions() {
        // **********************************************//
        System.out.println("==================== Condition in Lock"
                + " ====================");
        System.out.println("Occupied            : " + this.isOccupied());
        System.out.println("Occupied by Vessel  : " + vessel);
        System.out.println("Chamber             : "
                + (this.isDrain() ? "drains" : "fills"));
        System.out.println("==================== END ====================");
        // **********************************************//
    }

    /**
     * Operate water level. If there is a vessel in lock, check vessel is
     * outbound or inbound. If it is outbound, lower the water level, otherwise,
     * fills lock, which takes operate time.
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
                this.setDrain( Param.DRAINED );
            } else {
                this.setDrain( Param.UNDRAINED);
            }
        }
    }

    /**
     * Enter.
     * 
     * @param vessel
     *            the vessel
     */
    public synchronized void enter(Vessel vessel) {
        // when there is a vessel already in lock,
        // next incoming vessel has to wait.
        while (this.isOccupied()) {
            // while(this.vessel != null){
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.setOccupied(Param.OCCUPIED);
        this.setVessel(vessel);
        this.notifyAll();

    }

    /**
     * Leave.
     * 
     * @return the vessel
     */
    public synchronized Vessel leave() {
        while (!this.isOccupied()) {
            // while(this.vessel == null){
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

    public boolean isOccupied() {
        return occupied;
    }

    public synchronized void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isDrain() {
        return drain;
    }

    public synchronized void setDrain(boolean drain) {
        this.drain = drain;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public synchronized void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }

}
