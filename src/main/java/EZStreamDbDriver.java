import org.sqlite.SQLiteDataSource;
import org.sqlite.SQLiteJDBCLoader;

import java.sql.*;

public class EZStreamDbDriver extends Thread {
    private Connection conn;
    private Statement statement;
    private ResultSet resultSet;
    private EZStreamCore core;

    private void connect() throws ClassNotFoundException, SQLException{
        conn=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:/testBase.sqlite3");
        } catch(Exception e){core.exceptionLog.add(e.getMessage());};
    }

    private void tryConnect(){
        try{
            statement=conn.createStatement();
        } catch(Exception e) {
            core.exceptionLog.add(e.getMessage());
        }
        try {
            resultSet = statement.executeQuery("Select * from tstTable");
            int i =0;
            while (resultSet.next()) {
                i++;
                core.exceptionLog.add("out: "+resultSet.getMetaData().getColumnLabel(i));
            }
        } catch(Exception e){
            core.exceptionLog.add(e.getMessage());
        };
    }

    public void run() {
        EZStreamDbQuery query;

        core = EZStreamCore.getInstance();
        try {
            core.exceptionLog.add("TRY....");
            connect();
            //tryConnect();
        } catch (Exception e) {
            core.exceptionLog.add(e.getMessage());
        }



        while(true) {
            query=core.popQueue();
            if (query==null) {
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e){};
            } else {

            }
        }
    }
}
