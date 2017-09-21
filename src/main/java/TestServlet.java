import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqType;
        String reqData;
        String ans;
        TestEZStreamQuery query = new TestEZStreamQuery();
        EZStreamCore core = EZStreamCore.getInstance();

        reqType = (String) req.getParameter("type");
        reqData = (String) req.getParameter("data");

        if (reqData==null) reqData="";
        if (reqType==null) reqType="";
        query.mode=reqType;
        query.data=reqData;


        ServletOutputStream out =  resp.getOutputStream();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>IN-OUT</title>");
        out.println("</head>");
        out.println("<body>");

        if (reqType.equals("read")){
            core.addQueue(query);
            try {
                query.latch.await();
                out.println("<h1>READ:</h1>");
                if (query.result!=null) {
                    while (query.result.next()) {
                        out.println("<br />");
                        out.println(query.result.getInt("id") + " --- ");
                        out.println(query.result.getString("data"));
                    }
                } else {
                    out.println("NO DATA");
                }
            } catch(Exception e) {}
        } else if (reqType.equals("write")) {
            core.addQueue(query);
            try {
                query.latch.await();
                out.println("<h1>WRITE:</h1>");
                out.println("<br />");
                out.println(reqData);
            } catch(Exception e) {}
        } else if (reqType.equals("sql")) {
            core.addQueue(query);
            try {
                query.latch.await();
                out.println("<h1>RUN SQL</h1>");
                out.println(query.data);
            } catch (Exception eee) {}
        }
        out.println("<h2>USAGE</h2>");
        out.println("<h3>LOG</h3>");
        out.println("http://localhost:8080/log");
        out.println("<h3>WRITE</h3>");
        out.println("http://localhost:8080/?type=write&data=shit");
        out.println("<h3>READ</h3>");
        out.println("http://localhost:8080/?type=read");
        out.println("<h3>RUN SQL</h3>");
        out.println("http://localhost:8080/?type=sql&data=SQL");
        out.println("</body>");
        out.println("</html>");
    }
}
