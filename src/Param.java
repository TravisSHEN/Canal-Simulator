/*
 * 
 */
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * Parameters that influence the behaviour of the system.
 */

public class Param {

    /** The Constant UNOCCUPIED. */
    public final static boolean DRAINED = true, UNDRAINED = false,
            OCCUPIED = true, UNOCCUPIED = false;

    // the number of canal sections
    /** The Constant SECTIONS. */
    public final static int SECTIONS = 6;

    // the time interval at which Main checks threads are alive
    /** The Constant MAIN_INTERVAL. */
    public final static int MAIN_INTERVAL = 50;

    // the time it takes to operate the lock
    /** The Constant OPERATE_TIME. */
    public final static int OPERATE_TIME = 800;

    // the time it takes to tow
    /** The Constant TOWING_TIME. */
    public final static int TOWING_TIME = 1200;

    // the maximum amount of time between vessel arrivals
    /** The Constant MAX_ARRIVE_INTERVAL. */
    public final static int MAX_ARRIVE_INTERVAL = 2400;

    // the maximum amount of time between vessel departures
    /** The Constant MAX_DEPART_INTERVAL. */
    public final static int MAX_DEPART_INTERVAL = 800;

    // the maximum amount of time between operating the lock
    /** The Constant MAX_OPERATE_INTERVAL. */
    public final static int MAX_OPERATE_INTERVAL = 6400;

    /**
     * For simplicity, we assume uniformly distributed time lapses. An
     * exponential distribution might be a fairer assumption.
     * 
     * @return the int
     */

    public static int arrivalLapse() {
        Random random = new Random();
        int r = random.nextInt(MAX_ARRIVE_INTERVAL);
        double prob = 1 - Math.exp(-(1 / 1200.0) * r);
        return (int) (prob * MAX_ARRIVE_INTERVAL);
    }

    /**
     * Departure lapse.
     * 
     * @return the int
     */
    public static int departureLapse() {
        Random random = new Random();
        int r = random.nextInt(MAX_DEPART_INTERVAL);
        double prob = 1 - Math.exp(-(1 / 1200.0) * r);
        return (int) (prob * MAX_DEPART_INTERVAL);
    }

    /**
     * Operate lapse.
     * 
     * @return the int
     */
    public static int operateLapse() {
        Random random = new Random();
        return random.nextInt(MAX_OPERATE_INTERVAL);
    }
}
