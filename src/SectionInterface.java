
/**
 * Author: Litao Shen
 */
// TODO: Auto-generated Javadoc
/**
 * The Interface SectionInterface.
 */
public interface SectionInterface {
    
    /**
     * Prints the out conditions.
     */
    public void printOutConditions();
    
    /**
     * Enter: let vessel enter the section.
     *
     * @param vessel            the vessel
     */
    public void enter(Vessel vessel); 


    /**
     * Leave: let vessel leave the section.
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
     * Gets the id.
     *
     * @return the id
     */
    public int getId(); 

}
