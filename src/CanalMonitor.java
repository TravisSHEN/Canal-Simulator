import java.util.ArrayList;

public class CanalMonitor {

	private volatile Lock lock;
	private volatile ArrayList<Section> secs = new ArrayList<Section>();

	public CanalMonitor(Lock lock, Section[] sections) {

		this.lock = lock;
		if (sections != null) {
			for (int i = 0; i < sections.length; i++) {
				this.secs.add(sections[i]);
			}
		}
	}


	public synchronized Lock getLock() {
		return lock;
	}

}
