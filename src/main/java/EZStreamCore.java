import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.awt.*;
import java.util.LinkedList;

public class EZStreamCore extends Thread{
    private static EZStreamCore instance;

    private double count=0;
    private LinkedList<EZStreamDbQuery> queryQueue = new LinkedList<EZStreamDbQuery>();
    private EZStreamDbDriver dbOperator;
    public LinkedList<String> exceptionLog = new LinkedList<String>();

    public static synchronized EZStreamCore getInstance() {
        if (instance == null) {
            instance = new EZStreamCore();
            instance.count=Math.random();
            instance.dbOperator = new EZStreamDbDriver();
            instance.dbOperator.start();
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

    public synchronized void addQueue(EZStreamDbQuery query){
        queryQueue.addLast(query);
        dbOperator.interrupt();
    };

    public synchronized EZStreamDbQuery popQueue() {
        EZStreamDbQuery answer = null;
        if (queryQueue.size()!=0) {
            answer = queryQueue.removeFirst();
        } else answer = null;
        return answer;
    };
}
