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
		"/MuokkaaTilausta"
})
public class HenkilokuntaFilter implements Filter {


    public HenkilokuntaFilter() {
   
    }


	public void destroy() {
	
	}

	/*
	 * Henkilökunnalla (kuljettaja tai kokki) on oikeus requestaa tätä urlia. 
	 * Muuten ohjataan menuun
	 * 
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Kayttaja kayttaja = (Kayttaja) req.getSession(false).getAttribute("kayttaja");
		if(kayttaja == null || !kayttaja.isKokki() && !kayttaja.isKuljettaja()){
			res.sendRedirect("Menu");
		}else{
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
