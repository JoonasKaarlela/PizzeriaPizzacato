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

@WebFilter(urlPatterns={"/taytteidenhallinta", "/PoistaTayte", "/poista", "/nayta", "/MuokkaaTaytetta", "/muokkaa", "/Taytteet", "/lisaaTayte", "lisaa"})
public class AuthenticateFilter implements Filter {


    public AuthenticateFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		Kayttaja kayttaja = (Kayttaja) req.getSession(false).getAttribute("kayttaja");
		if(kayttaja == null || !kayttaja.isOmistaja()){
			res.sendRedirect("Menu");
		}else{
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
