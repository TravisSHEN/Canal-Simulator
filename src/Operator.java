// TODO: Auto-generated Javadoc
/**
 * The Class Operator.
 */
public class Operator extends Thread {

	/** The lock. */
	private Lock lock;
	private CanalMonitor canalMonitor;

	/**
	 * Instantiates a new operator.
	 * 
	 * @param lock
	 *            the lock
	 */
	public Operator(CanalMonitor canalMonitor, Lock lock) {
//		this.lock = lock;
		this.canalMonitor = canalMonitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		while (true) {
			// if lock is not occupied, change the chamber's water
			// level.
				this.operateChamber();
		}

	}

	public synchronized void operateChamber() {
//		synchronized (canalMonitor) {
			lock = canalMonitor.getLock();

//			while (lock.isOccupied()) {
//				try {
//					canalMonitor.wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}

			boolean temp = lock.isDrain();
			try {
				Thread.sleep(Param.operateLapse());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.setDrain(!temp);
			System.out.println("Chamber "
					+ (lock.isDrain() ? "drains" : "fills"));
			
//			canalMonitor.notifyAll();
//		}
	}

}
