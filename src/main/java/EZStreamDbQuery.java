import java.sql.ResultSet;

public interface EZStreamDbQuery {
    public String createSQL();
    public void callBack(ResultSet result);
}
