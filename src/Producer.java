

// TODO: Auto-generated Javadoc
/**
 * The Class Producer used to generate new vessel and let new vessel get in
 * lock.
 */
public class Producer extends Thread {

    /** The lock. */
    private Lock lock;
    

    /**
     * Instantiates a new producer.
     * 
     * @param lock
     *            the lock
     */
    public Producer(Lock lock) {
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
            this.produceVessel();

        }
    }

    /**
     * Produce vessel.
     */
    public void produceVessel() {
        synchronized (lock) {

            // if lock is occupied or the canal is full, let current thread
            // wait until any vessel gets out of canal.
            while (lock.isOccupied() || lock.getCount() == (Param.SECTIONS)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // only if lock is drain, let new vessel in.
            if (lock.isDrain()) {

                Vessel temp = Vessel.getNewVessel();
                // increment counter.
                lock.incrementCount();
                lock.enter(temp);
                // operate water level to raise the vessel.
                lock.operateWaterLevel();
                System.out.println("Number " + lock.getCount() + " vessel "
                        + lock.getVessel().toString() + " enter Lock to go up");

                try {
                    Thread.sleep(Param.arrivalLapse());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // allowing chamber to run
                lock.setChamberEnabled(true);
                lock.notifyAll();
            }
        }
    }

}
