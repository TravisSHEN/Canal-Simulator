// TODO: Auto-generated Javadoc
/**
 * The Class Launch_tug.
 */
public class Launch_tug extends Thread {

	/** The lock. */
	private Lock lock;

	/** The launch section. */
	private Section launchSection;

	/**
	 * Instantiates a new launch_tug.
	 * 
	 * @param canalMonitor
	 *            the canal monitor
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
			// canalMonitor.launch();
			this.launch();
		}

	}

	/**
	 * Launch.
	 */
	public void launch() {

		Vessel temp = null;
		synchronized (lock) {

			// System.out.println("Launch");
			// synchronized (lock) {
			while (lock.getVessel() == null
					|| (lock.getVessel() != null && lock.getVessel().isOutbound())) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			temp = lock.leave();
			lock.notifyAll();
			System.out.println(temp + " leaves lock.");
		}

		try {
			Thread.sleep(Param.TOWING_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (launchSection) {
			launchSection.enter(temp);
		}
	}
}
