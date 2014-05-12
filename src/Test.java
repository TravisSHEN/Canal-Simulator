/*
 * 
 */
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Test.
 */
public class Test extends Object implements Cloneable {

	/** The i. */
	int i;

	/**
	 * Instantiates a new test.
	 * 
	 * @param i
	 *            the i
	 */
	public Test(int i) {
		this.i = i;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		while (true) {
			int r = rand.nextInt(2400);

			double p = 1.0 - Math.exp(-(1.0 / 1200.0) * (float)r);
			System.out.println(p + "   " + Math.exp(-(1 / 1200) * r));
		}
	}

}
