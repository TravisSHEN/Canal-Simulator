// TODO: Auto-generated Javadoc
/**
 * The Class Launch_tug.
 */
public class Launch_tug extends Thread {

	/** The lock. */
	private Lock lock;

	/** The canal monitor. */
	private CanalMonitor canalMonitor;

	/** The launch section. */
	private Section launchSection;

	/**
	 * Instantiates a new launch_tug.
	 *
	 * @param canalMonitor the canal monitor
	 * @param lock            the lock
	 * @param launchSection            the launch section
	 */
	public Launch_tug(CanalMonitor canalMonitor, Lock lock,
			Section launchSection) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
		this.launchSection = launchSection;
		this.canalMonitor = canalMonitor;
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

		synchronized (canalMonitor) {

			// Section launchSection = this.secs.get(0);
			while (!lock.isOccupied()
					|| (lock.isOccupied() && lock.getVessel().isOutbound())) {
				try {
					canalMonitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (!launchSection.isOccupied()) {

				Vessel temp = lock.leave();
				System.out.println(temp + " leaves lock.");
				canalMonitor.notifyAll();

				try {
					 Thread.sleep(Param.TOWING_TIME);

//					Thread.currentThread().join(Param.TOWING_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				launchSection.enter(temp);
				canalMonitor.notifyAll();
			}
		}
	}
}
