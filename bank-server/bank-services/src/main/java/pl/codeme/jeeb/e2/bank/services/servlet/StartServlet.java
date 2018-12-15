package pl.codeme.jeeb.e2.bank.services.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartServlet
 */
@SuppressWarnings("serial")
@WebServlet("/Start")
public class StartServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("<html>");
        response.getWriter().append("<body>");
        response.getWriter().append("<form>");
        response.getWriter().append("<label for=\"email\">email:</label>");
        response.getWriter().append("<input type=\"text\" name=\"email\" />");
        response.getWriter().append("<input type=\"submit\" />");
        response.getWriter().append("</form>");
        response.getWriter().append("</body>");
        response.getWriter().append("</html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
