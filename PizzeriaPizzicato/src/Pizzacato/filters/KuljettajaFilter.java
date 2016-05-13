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
		"/Kuljettaja"
})
public class KuljettajaFilter implements Filter {


    public KuljettajaFilter() {

    }


	public void destroy() {

	}

	/*
	 * 
	 * Kuljettaja kohtainen url. Vain kuljettajalla on oikeudet requestaa tätä urlia.
	 * Muuten ohjataan Menuun.
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Kayttaja kayttaja = (Kayttaja) req.getSession(false).getAttribute("kayttaja");
		if(kayttaja == null || !kayttaja.isKuljettaja()){
			res.sendRedirect("Menu");
		}else{
			chain.doFilter(request, response);
		}

	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
