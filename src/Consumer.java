/**
 * Author: Litao Shen
 */

// TODO: Auto-generated Javadoc
/**
 * The Class Consumer used to let vessel depart canal.
 */
public class Consumer extends Thread {

    /** The lock. */
    private Lock lock;

    /**
     * Instantiates a new consumer.
     * 
     * @param lock
     *            the lock
     */
    public Consumer(Lock lock) {
        // TODO Auto-generated constructor stub
        this.lock = lock;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {

        while (true) {
            this.depart();
        }

    }

    /**
     * Depart.
     */
    public void depart() {
        // lock object lock.
        // let thread wait until lock is occupied by a outbound vessel.
        synchronized (lock) {
            while (lock.getVessel() == null
                    || (lock.getVessel() != null && !lock.getVessel()
                            .isOutbound())) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Let vessel" + lock.getVessel()
                    + " out. Operating chamber: ");
            lock.operateWaterLevel();
            Vessel temp = lock.leave();
            // operate water level to let the vessel go down.
            // decrement the number of vessels in canal.
            lock.decrementCount();
            System.out.println(temp + " departs the system");

            // enable chamber again
            lock.setChamberEnabled(true);
            lock.notifyAll();
            // wait departureLapse until next vessel can get out.
            try {
                Thread.sleep(Param.departureLapse());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
