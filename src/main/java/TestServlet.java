import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TestServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EZStreamCore core = EZStreamCore.getInstance();
        core.plusadin();
        req.setAttribute("data", core.getCount());
        req.getRequestDispatcher("testjsp.jsp").forward(req,resp);
    }
}
