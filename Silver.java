public class Silver extends State {
	private static final Silver singleton = new Silver();

	private Silver(){}

	public static Silver getInstance(){
		return singleton;
	}

	@Override
	public int getPrice(int p) {
		return (int)(p * 0.97);
	}

	@Override
	public int calculateMile(int m) {
		return (int)(m * 1.25);
	}

	@Override
	public String toString(){
		return "Silver";
	}
}
