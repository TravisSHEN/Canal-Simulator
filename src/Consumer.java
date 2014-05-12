// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 */
public class Consumer extends Thread {

	/** The lock. */
	private Lock lock;
	private CanalMonitor canalMonitor;

	/**
	 * Instantiates a new consumer.
	 * 
	 * @param lock
	 *            the lock
	 */
	public Consumer(CanalMonitor canalMonitor, Lock lock) {
		// TODO Auto-generated constructor stub
		 this.lock = lock;
		this.canalMonitor = canalMonitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		while (true) {
			this.depart();

		}

	}

	public void depart() {
		synchronized (canalMonitor) {
//			lock = canalMonitor.getLock();
			// System.out.println("depart:");
			while (!lock.isOccupied()
					|| (lock.isOccupied() && !lock.getVessel().isOutbound())) {
				try {
					canalMonitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println(lock.getVessel()
					+ " departs the system");

			lock.operateWaterLevel();
			lock.leave();
			lock.count.getAndDecrement();
			// System.out.println("WATER LEVEL: " + lock.isDrain());

			canalMonitor.notifyAll();
			try {
				Thread.sleep(Param.departureLapse());
				// Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
