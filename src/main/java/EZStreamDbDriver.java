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
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\nuzhdin\\IdeaProjects\\ezstream\\testBase.sqlite3");
        } catch(Exception e){core.exceptionLog.add(e.getMessage());};
    }


    public void run() {
        EZStreamDbQuery query;

        core = EZStreamCore.getInstance();
        try {
            connect();
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
                try {
                    statement = conn.createStatement();
                    try {
                        resultSet = statement.executeQuery(query.createSQL());
                    } catch(Exception ee) {
                        core.exceptionLog.add(ee.getMessage());
                        resultSet = null;
                    }
                    query.callBack(resultSet);

                } catch(Exception e) {
                    core.exceptionLog.add(e.getMessage());
                }
            }
        }
    }
}
