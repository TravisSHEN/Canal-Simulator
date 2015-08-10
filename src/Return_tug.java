/**
 * Author: Litao Shen
 */

// TODO: Auto-generated Javadoc
/**
 * The Class Return_tug moves the vessel from last section back to lock.
 */
public class Return_tug extends Thread {

    /** The last section. */
    private Section lastSection;

    /** The lock. */
    private Lock lock;

    /**
     * Instantiates a new return_tug.
     * 
     * @param lastSection
     *            the last section
     * @param lock
     *            the lock
     */
    public Return_tug(Section lastSection, Lock lock) {
        // TODO Auto-generated constructor stub
        this.lastSection = lastSection;
        this.lock = lock;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {
        while (true) {
            this.returnToLock((Section) lastSection, (Lock) lock);
        }
    }

    /**
     * Return to lock.
     * 
     * @param lastSection
     *            the last section
     * @param lock
     *            the lock
     */
    public void returnToLock(Section lastSection, Lock lock) {

        // System.out.println("RETURN TO LOCK: "
        // + (lock.isDrain() ? " drains " : "fills") + " | Last Section: "
        // + lastSection.getVessel());

        // synchronized object lastSection (lock object lastSection).
        synchronized (lastSection) {
            // when lastSection is not occupied by vessel,
            // let current thread wait until waken up by other thread owns
            // monitor lastSection.
            while (!lastSection.isOccupied()) {
                try {
                    lastSection.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // synchronize object lock (lock object lock)
            synchronized (lock) {
                // wait for water level of lock to be filled
                if (lock.isDrain()) {
                    Thread.yield();
                    return;
                }
                while (lock.isOccupied()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                // vessel leave last section
                Vessel temp = lastSection.leave();
                lastSection.notifyAll();
                // waiting TOWING_TIME to let vessel enters lock.
                try {
                    Thread.sleep(Param.TOWING_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // set vessel as a outbound vessel
                temp.setOutbound(true);
                // let vessel enter lock.
                lock.enter(temp);
                // disable chamber to run automatically
                lock.setChamberEnabled(false);
                lock.notifyAll();
                System.out.println(temp + " returns to lock to go down.");
            }
        }
    }

}
