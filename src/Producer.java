// TODO: Auto-generated Javadoc
/**
 * The Class Producer.
 */
public class Producer extends Thread {

	/** The lock. */
	private Lock lock;
	

	/**
	 * Instantiates a new producer.
	 *
	 * @param canalMonitor the canal monitor
	 * @param lock            the lock
	 */
	public Producer(Lock lock) {
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
			this.produceVessel();

		}
	}

	/**
	 * Produce vessel.
	 */
	public void produceVessel() {
		synchronized (lock) {

//			 System.out.println("Produce Vessel: "
//			 + (lock.getVessel() != null ? "occupied " : "not occupied ")
//			 + (lock.isDrain() ? "drains " : "fills"));

			while (lock.isOccupied() || lock.count.get() == (Param.SECTIONS)) {
				try {
					lock.wait();
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
				lock.enter(temp);
				lock.operateWaterLevel();
				System.out.println("Number " + lock.count.get() + " vessel "
						+ lock.getVessel().toString() + " enter Lock to go up");

				lock.notifyAll();
			}
		}
	}

}
