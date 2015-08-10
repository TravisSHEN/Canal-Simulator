/**
 * Author: Litao Shen
 */


// TODO: Auto-generated Javadoc
/**
 * The Interface LockInterface.
 */
public interface LockInterface {
    /**
     * Prints the out conditions.
     */
    public void printOutConditions(); 

    /**
     * Operate water level. If there is a vessel in lock, check vessel is
     * outbound or inbound. If it is outbound, lower the water level, otherwise,
     * fills lock, which takes operate time.
     */
    public void operateWaterLevel(); 

    /**
     * Enter: Let vessel enter lock when lock is not occupied.
     * 
     * @param vessel
     *            the vessel
     */
    public void enter(Vessel vessel); 

    /**
     * Leave: let vessel leaves lock.
     *
     * @return the vessel
     */
    public Vessel leave();

    /**
     * Checks if is occupied.
     *
     * @return true, if is occupied
     */
    public boolean isOccupied(); 

    /**
     * Sets the occupied.
     *
     * @param occupied the new occupied
     */
    public void setOccupied(boolean occupied); 

    /**
     * Checks if is drain.
     *
     * @return true, if is drain
     */
    public boolean isDrain();

    /**
     * Sets the drain.
     *
     * @param drain the new drain
     */
    public String setDrain(boolean drain);

    /**
     * Gets the vessel.
     *
     * @return the vessel
     */
    public Vessel getVessel();

    /**
     * Sets the vessel.
     *
     * @param vessel the new vessel
     */
    public void setVessel(Vessel vessel);
    
    /**
     * Gets the count.
     *
     * @return the count
     */
    public int getCount(); 

    /**
     * Increment count.
     */
    public void incrementCount(); 
    
    /**
     * Decrement count.
     */
    public void decrementCount();

    /**
     * Checks if is chamber enabled.
     *
     * @return true, if is chamber enabled
     */
    public boolean isChamberEnabled();

    /**
     * Sets the chamber enabled.
     *
     * @param chamberEnabled the new chamber enabled
     */
    public void setChamberEnabled(boolean chamberEnabled); 
}
