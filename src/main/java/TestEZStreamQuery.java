public class TestEZStreamQuery implements EZStreamDbQuery {
    public String data;
    public String mode;
    public String createSQL() {
        if (mode.equals("read")){
            return "select * from tstTable";
        } else if (mode.equals("read")) {
            return "insert into tstTable (data) values("+data+")";
        }
        return null;
    }

    public void callBack() {

    }

}
