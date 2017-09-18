public class EZStreamCore extends Thread{
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
        count=count+1000;

    }

    public double getCount(){
        return count;
    }

    public void run(){
        while (true) {
            try{Thread.sleep(1000);} catch (Exception e) {}
            count=count+1;
        }
    }
}
