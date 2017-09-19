import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.awt.*;
import java.util.LinkedList;

public class EZStreamCore extends Thread{
    private static EZStreamCore instance;

    private double count=0;
    private LinkedList<EZStreamDbQuery> queryQueue = new LinkedList<EZStreamDbQuery>();
    private Mutex queryQueueMutex = new Mutex();

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

    public void addQueue(EZStreamDbQuery query){
        try {
            queryQueueMutex.acquire();
            try {
                queryQueue.addLast(query);
            } finally {
                queryQueueMutex.release();
            }
        }  catch (InterruptedException ie) {};
    };

    public EZStreamDbQuery popQueue() {
        EZStreamDbQuery answer = null;
        try {
            queryQueueMutex.acquire();
            try {
                if (queryQueue.size()!=0) {
                    answer = queryQueue.removeFirst();
                } else answer = null;
            } finally {
                queryQueueMutex.release();
            }
        }  catch (InterruptedException ie) {};
        return answer;
    };
}
