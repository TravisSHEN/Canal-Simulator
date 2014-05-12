// TODO: Auto-generated Javadoc
/**
 * The Class Producer.
 */
public class Producer extends Thread {

	/** The lock. */
	private Lock lock;
	private CanalMonitor canalMonitor;

	/**
	 * Instantiates a new producer.
	 * 
	 * @param lock
	 *            the lock
	 */
	public Producer(CanalMonitor canalMonitor, Lock lock) {
		// TODO Auto-generated constructor stub

		this.canalMonitor = canalMonitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while (true) {
			this.produceVessel();

		}
	}

	public void produceVessel() {
		synchronized (canalMonitor) {

			// System.out.println("Produce Vessel: "
			// + (this.occupied ? "occupied " : "not occupied ")
			// + (this.drain ? "drains " : "fills"));

			lock = canalMonitor.getLock();
			while (lock.isOccupied() || lock.count.get() == (Param.SECTIONS)) {
				try {
					canalMonitor.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (lock.isDrain()) {

				Vessel temp = Vessel.getNewVessel();

				try {
					Thread.sleep(Param.arrivalLapse());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				lock.count.getAndIncrement();
				// System.out.println("WATER LEVEL: " + lock.isDrain());
				lock.enter(temp);
				lock.operateWaterLevel();
				System.out.println("Number " + lock.count.get() + " vessel "
						+ lock.getVessel().toString() + " enter Lock to go up");

				canalMonitor.notifyAll();
			}
		}
	}

}
