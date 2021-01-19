package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raul-Andrei Ginj-Groszhart
 */

public class AuthenticationFilter implements Filter {
    
    public void init(FilterConfig fConfig) throws ServletException {
        
        
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // Get HTTP request and response
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        // Store the Login URI and others to ignore
        String loginURI = httpRequest.getContextPath() + "/";
        String loginServletURI = httpRequest.getContextPath() + "/UserServlet.do";
        String registerURI = httpRequest.getContextPath() + "/register.jsp";
        String registerServletURI = httpRequest.getContextPath() + "/RegisterServlet.do";
        
        // Check if the user is already logged in
        boolean loggedIn = (session != null && session.getAttribute("username") != null);
        
        // Check if user is trying to access any of these URIs
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean loginServletRequest = httpRequest.getRequestURI().equals(loginServletURI);
        boolean registerRequest = httpRequest.getRequestURI().equals(registerURI);
        boolean registerServletRequest = httpRequest.getRequestURI().equals(registerServletURI);
  
        // If logged in 
        if(loggedIn){
            // If trying to access login OR register links
            if(loginRequest || loginServletRequest || registerRequest || registerServletRequest){
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/DashboardServlet.do"); // Redirect to dashboard
            } else {
                chain.doFilter(request, response); // Redirect to requested link
            }
            
        } else { // if NOT logged in
            // and trying to access login/register links
            if(loginRequest || loginServletRequest || registerRequest || registerServletRequest){
                chain.doFilter(request, response); // redirect to requested link
            } else {
                httpResponse.sendRedirect(loginServletURI); // otherwise, redirect to login
            }
        }
    }
    

    public void destroy() {
        
        
        
    }
 

    
}
