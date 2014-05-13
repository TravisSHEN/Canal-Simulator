// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 */
public class Consumer extends Thread {

	/** The lock. */
	private Lock lock;

	/**
	 * Instantiates a new consumer.
	 * 
	 * @param canalMonitor
	 *            the canal monitor
	 * @param lock
	 *            the lock
	 */
	public Consumer(Lock lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
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

	/**
	 * Depart.
	 */
	public void depart() {
		synchronized (lock) {
			while (lock.getVessel() == null
					|| (lock.getVessel() != null && !lock.getVessel().isOutbound())) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// System.out.println(lock.getVessel()
			// + " departs the system");

			// lock.operateWaterLevel();
			Vessel temp = lock.leave();
			lock.operateWaterLevel();
			lock.count.getAndDecrement();
			System.out.println(temp + " departs the system");

			lock.notifyAll();
			try {
				Thread.sleep(Param.departureLapse());
				// Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
