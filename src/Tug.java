

// TODO: Auto-generated Javadoc
/**
 * The Class Tug that is tug boat used to move vessel from current section to
 * successor section.
 */
public class Tug extends Thread {

    /** The id. */
    private final int id;

    /** The src. */
    private Section src;
    /** The dest. */
    private Section dest;

    /**
     * Instantiates a new tug.
     * 
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

        while (true) {

            // if source is occupied and destination is not occupied,
            // move the vessel in source(src) to successor section(dest).
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
                    src.notifyAll();

                    try {
                        Thread.sleep(Param.TOWING_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    dest.enter(temp);
                    dest.notifyAll();
                }
            }
        }
    }
}
