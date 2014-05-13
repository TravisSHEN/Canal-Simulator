// TODO: Auto-generated Javadoc
/**
 * The Class Tug.
 */
public class Tug extends Thread {

    /** The id. */
    private final int id;

    /** The dest. */
    private Section src, dest;

    /**
     * Instantiates a new tug.
     * 
     * @param canalMonitor
     *            the canal monitor
     * @param id
     *            the id
     * @param src
     *            the src
     * @param dest
     *            the dest
     */
    public Tug(int id, Section src, Section dest) {
        // TODO Auto-generated constructor stub
        this.id = id;
        this.src = src;
        this.dest = dest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {

        // if destination is not occupied, move vessel to successor section.
        while (true) {
            synchronized (src) {
                while (!src.isOccupied()) {
                    try {
                        src.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (dest) {
                    while (dest.isOccupied()) {
                        try {
                            dest.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Vessel temp = src.leave();
//                    src.notifyAll();

                    try {
                        Thread.sleep(Param.TOWING_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dest.enter(temp);
                }
            }
        }
    }
}
