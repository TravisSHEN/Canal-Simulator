// TODO: Auto-generated Javadoc
/**
 * The Class Operator.
 */
public class Operator extends Thread {

    /** The lock. */
    private Lock lock;

    /**
     * Instantiates a new operator.
     * 
     * @param canalMonitor
     *            the canal monitor
     * @param lock
     *            the lock
     */
    public Operator(Lock lock) {
        this.lock = lock;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {
        while (true) {
            // if lock is not occupied, change the chamber's water
            // level.
            this.operateChamber();
        }

    }

    /**
     * Operate chamber.
     */
    public void operateChamber() {

            if (!lock.isOccupied()) {
                lock.printOutConditions();
                boolean temp = lock.isDrain();
                try {
                    Thread.sleep(Param.operateLapse());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.setDrain(!temp);
                System.out.println("OPERATOR: Chamber "
                        + (lock.isDrain() ? "drains" : "fills"));
            }
    }

}
