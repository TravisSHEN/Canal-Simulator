/**
 * Author: Litao Shen
 */

// TODO: Auto-generated Javadoc
/**
 * The Class Launch_tug tow away the vessel from lock to first section.
 */
public class Launch_tug extends Thread {

    /** The lock. */
    private Lock lock;

    /** The launch section. */
    private Section launchSection;

    /**
     * Instantiates a new launch_tug.
     * 
     * @param lock
     *            the lock
     * @param launchSection
     *            the launch section
     */
    public Launch_tug(Lock lock, Section launchSection) {
        // TODO Auto-generated constructor stub
        this.lock = lock;
        this.launchSection = launchSection;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {

        while (true) {
            this.launch();
        }

    }

    /**
     * Launch.
     */
    public void launch() {

        Vessel temp = null;
        synchronized (lock) {
            // let current thread wait until lock is occupied by outbound
            // vessel.
            while (lock.getVessel() == null
                    || (lock.getVessel() != null && lock.getVessel()
                            .isOutbound())) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (launchSection) {
                while (launchSection.isOccupied()) {
                    try {
                        launchSection.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                temp = lock.leave();
                // wake up other threads waiting for object lock.
                // allowing chamber to run after vessel leaves.
                lock.setChamberEnabled(true);
                lock.notifyAll();
                System.out.println(temp + " leaves lock.");

                try {
                    Thread.sleep(Param.TOWING_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                launchSection.enter(temp);
                // wake up other threads waiting for object section 0.
                launchSection.notifyAll();
            }
        }
    }
}
