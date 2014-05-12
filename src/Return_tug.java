// TODO: Auto-generated Javadoc
/**
 * The Class Return_tug.
 */
public class Return_tug extends Thread {

	/** The last section. */
	private Section lastSection;

	/** The lock. */
	private Lock lock;
	private CanalMonitor canalMonitor;
	private volatile boolean signalled;

	/**
	 * Instantiates a new return_tug.
	 * 
	 * @param lastSection
	 *            the last section
	 * @param lock
	 *            the lock
	 */
	public Return_tug(CanalMonitor canalMonitor, Section lastSection, Lock lock) {
		// TODO Auto-generated constructor stub
		this.lastSection = lastSection;
		// this.lock = lock;
		signalled = false;
		this.canalMonitor = canalMonitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while (true) {
			this.returnToLock(lastSection, canalMonitor.getLock());
		}
	}

	public void returnToLock(Section lastSection, Lock lock) {
		synchronized (canalMonitor) {

			// System.out.println("RETURN TO LOCK: "
			// + (lock.isDrain() ? " drains " : "fills") + " | Last Section: "
			// + lastSection.getVessel());
			while (lock.isOccupied()) {
				try {
					canalMonitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (!lock.isDrain() && lastSection.isOccupied()) {

				Vessel temp = this.leaveOut(lastSection);
				canalMonitor.notifyAll();
				try {
					 Thread.sleep(Param.TOWING_TIME);

//					Thread.currentThread().join(Param.TOWING_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.arrive(lock, temp);
				canalMonitor.notifyAll();
			}

		}
	}

	public synchronized Vessel leaveOut(Section lastSection) {
		Vessel temp = lastSection.leave();
		signalled = true;
		this.notify();
		return temp;
	}

	public void arrive(Lock lock, Vessel vessel) {
		synchronized (this) {

			while (!signalled) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			vessel.setOutbound(true);
			lock.enter(vessel);
			signalled = false;

		}
	}

}
