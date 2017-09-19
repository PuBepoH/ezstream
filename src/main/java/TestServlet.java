import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqType;
        String reqData;
        String ans;
        TestEZStreamQuery q = new TestEZStreamQuery();

        EZStreamCore core = EZStreamCore.getInstance();
        reqType = (String) req.getParameter("type");
        reqData = (String) req.getParameter("data");

        if (reqData==null) reqData="";
        if (reqType==null) reqType="";

        if (reqType.equals("write")) {
            if (reqData.equals("")) ans = "ERROR: Nothing to write!!!";
            else {
                q.data = reqData;
                core.addQueue(q);
                ans = "Writed: " + reqData;
            }
        } else {
            q=(TestEZStreamQuery) core.popQueue();
            if (q==null) ans = "ERROR: empty queue!!!";
            else ans = "Readed: " + q.data;
        }
        req.setAttribute("answer", ans);
        req.getRequestDispatcher("/testjsp.jsp").forward(req, resp);
    }
}
