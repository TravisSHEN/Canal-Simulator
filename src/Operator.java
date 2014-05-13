
// TODO: Auto-generated Javadoc
/**
 * The Class Operator operates water level when lock is not occupied so that
 * next coming vessel can enter lock to go up or go down.
 */
public class Operator extends Thread {

    /** The lock. */
    private Lock lock;

    /**
     * Instantiates a new operator.
     * 
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

        while (lock.isOccupied() || !lock.isChamberEnabled())
            ;

//        if (!lock.isOccupied()) {
            boolean temp = lock.isDrain();
            // takes operateLapse to operate chamber
            try {
                Thread.sleep(Param.operateLapse());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.setDrain(!temp);
            System.out.println("OPERATOR: Chamber "
                    + (lock.isDrain() ? "drains" : "fills"));
//        }
    }

}
