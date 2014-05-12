// TODO: Auto-generated Javadoc
/**
 * The Class Tug.
 */
public class Tug extends Thread {

	private final int id;

	private Section src, dest;
	
	private CanalMonitor canalMonitor;

	private boolean signalled;

	/**
	 * Instantiates a new tug.
	 * 
	 * @param id
	 *            the id
	 * @param src
	 *            the src
	 * @param dest
	 *            the dest
	 */
	public Tug(CanalMonitor canalMonitor, int id, Section src, Section dest) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.src = src;
		this.dest = dest;
		this.signalled = false;
		this.canalMonitor = canalMonitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {

		// if destination is not occupied, move vessel to successor section.
		while (true) {

			// System.out.println("Tug ID: " + this.id);
			// if destination is not occupied, move vessel to successor
			// section.

			this.move(src, dest);

		}

	}
	
	public void move(Section src, Section dest){
		synchronized (this) {
//			while(! src.isOccupied() || dest.isOccupied()){
//				try {
//					canalMonitor.wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			if (src.isOccupied() && !dest.isOccupied()) {
				Vessel temp = this.leaveOut(src);
//				canalMonitor.notifyAll();
				try {
//					Thread.currentThread().join(Param.TOWING_TIME);
					Thread.sleep(Param.TOWING_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.arrive(dest, temp);
//				canalMonitor.notifyAll();
			}
		}
		
	}

	public synchronized Vessel leaveOut(Section src) {

		Vessel temp = src.leave();

		signalled = true;
		this.notify();
		return temp;
	}

	public void arrive(Section dest, Vessel temp) {
		synchronized (this) {
			while (!signalled) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dest.enter(temp);
			signalled = false;
		}

	}

}
