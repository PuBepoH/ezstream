import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;

public class TestEZStreamQuery implements EZStreamDbQuery {
    public String data;
    public String mode;
    public ResultSet result;
    public CountDownLatch latch = new CountDownLatch(1);
    public String createSQL() {
        if (mode.equals("read")){
            return "select * from tstTable";
        } else if (mode.equals("write")) {
            return "insert into tstTable (data) values ('"+data+"')";
        } else if (mode.equals("sql")) {
            return data;
        }
        return null;
    }

    public void callBack(ResultSet resultIn) {
        result=resultIn;
        latch.countDown();
    }

}
