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
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Kayttaja;


@WebFilter(urlPatterns={
		"/Kokki"
})
public class KokkiFilter implements Filter {


    public KokkiFilter() {

    }


	public void destroy() {
	
	}


	/*
	 * 
	 * Kokki kohtainen url. Vain kokilla on oikeudet requestaa tätä urlia.
	 * Muuten ohjataan Menuun.
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Kayttaja kayttaja = (Kayttaja) req.getSession(false).getAttribute("kayttaja");
		if(kayttaja == null || !kayttaja.isKokki()){
			res.sendRedirect("Menu");
		}else{
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
