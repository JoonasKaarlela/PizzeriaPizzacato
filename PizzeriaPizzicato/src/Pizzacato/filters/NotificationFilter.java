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
public class NotificationFilter implements Filter {


    public NotificationFilter() {

    }


	public void destroy() {
		
	}

	/*
	 *
	 * Notification stack
	 * 1. Poista ilmoitus request scopesta.
	 * 2. Aseta sessiosta mahdollinen uusi ilmoitus request scopeen.
	 * 3. Clearaa sessiosta ilmoitukset.
	 * 
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		req.removeAttribute("notification");
		req.setAttribute("notification", (String) req.getSession().getAttribute("notification"));
		req.getSession().removeAttribute("notification");
		
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
