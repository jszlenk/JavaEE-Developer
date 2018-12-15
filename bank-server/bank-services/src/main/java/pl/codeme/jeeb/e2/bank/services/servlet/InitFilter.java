package pl.codeme.jeeb.e2.bank.services.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class InitFilter
 */
@WebFilter(filterName = "/InitFilter", urlPatterns = { "/Start" })
public class InitFilter implements Filter {

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        System.out.println("Destroy filter");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest)request;
            if(req.getParameterMap().containsKey("email") && !req.getParameter("email").isEmpty()) {
                req.getSession().setAttribute("email", req.getParameter("email"));
                ((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/infopage");
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("Init filter");
    }

}
