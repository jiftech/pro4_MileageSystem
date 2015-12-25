public class Normal extends State {
	private static final Normal singleton = new Normal();

	private Normal(){}

	public static Normal getInstance(){
		return singleton;
	}

	@Override
	public int getPrice(int p) {
		return p;
	}

	@Override
	public int calculateMile(int m) {
		return m;
	}

	@Override
	public String toString(){
		return "Normal";
	}
}
