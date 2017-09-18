public class EZStreamCore {
    private static EZStreamCore instance;
    private double count=0;

    public static synchronized EZStreamCore getInstance() {
        if (instance == null) {
            instance = new EZStreamCore();
            instance.count=Math.random();
        }
        return instance;
    }

    public void plusadin() {
        count=count+1;

    };

    public double getCount(){
        return count;
    }
}
