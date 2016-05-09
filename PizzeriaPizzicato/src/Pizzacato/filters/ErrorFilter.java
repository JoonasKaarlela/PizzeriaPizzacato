package Pizzacato.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns={"/*"})
public class ErrorFilter implements Filter {


    public ErrorFilter() {
        
    }


	public void destroy() {
	
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		req.removeAttribute("error"); // Poista errori request scopesta.
		req.setAttribute("error", (String) req.getSession().getAttribute("error")); // Aseta sessiosta mahdollinen uusi errori request scopeen.
		req.getSession().removeAttribute("error"); // Poista sessiosta errori
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
