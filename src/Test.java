
// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test extends Object implements Cloneable{
	
	/** The i. */
	int i;
	
	/**
	 * Instantiates a new test.
	 *
	 * @param i the i
	 */
	public Test(int i){
		this.i = i;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test1 = new Test(1);
		Test test2 = new Test(2);
		Section[] sec = new Section[2];
		sec[0] = new Section(0);
		Section[] secs = new Section[2];
		
		sec[0].setOccupied(true);
		secs = sec;
		System.out.println(secs[0].isOccupied());

	}

}
