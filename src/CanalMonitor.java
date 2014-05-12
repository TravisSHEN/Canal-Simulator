/*
 * 
 */
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class CanalMonitor.
 */
public class CanalMonitor {

	/** The lock. */
	private volatile Lock lock;
	
	/** The sections. */
	private volatile ArrayList<Section> secs = new ArrayList<Section>();

	/**
	 * Instantiates a new canal monitor.
	 *
	 * @param lock the lock
	 * @param sections the sections
	 */
	public CanalMonitor(Lock lock, Section[] sections) {

		this.lock = lock;
		if (sections != null) {
			for (int i = 0; i < sections.length; i++) {
				this.secs.add(sections[i]);
			}
		}
	}


	/**
	 * Gets the lock.
	 *
	 * @return the lock
	 */
	public synchronized Lock getLock() {
		return lock;
	}

}
