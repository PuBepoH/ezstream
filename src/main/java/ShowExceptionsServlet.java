import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ListIterator;

public class ShowExceptionsServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EZStreamCore core = EZStreamCore.getInstance();
        ServletOutputStream out =  resp.getOutputStream();

        String tmp;
        out.println("<html>");
        out.println("<head>");
        out.println("<title>EXCEPTIONS</title>");
        out.println("</head>");
        out.println("<body>");

        ListIterator<String> iterator = core.exceptionLog.listIterator();
        while (iterator.hasNext()) {
            tmp=iterator.next();
            if (tmp.equals("")){
                out.println("NONE");
            } else {
                out.println(tmp);
            }
            out.println("<br />");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
