public class Gold extends State {
    private static final Gold singleton = new Gold();

    private Gold(){}

    public static Gold getInstance(){
        return singleton;
    }
    @Override
    public int getPrice(int p) {
        return (int)(p * 0.95);
    }

    @Override
    public int calculateMile(int m) {
        return (int)(m * 1.5);
    }

    @Override
    public String toString(){
        return "Gold";
    }
}