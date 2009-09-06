package ar.edu.utn.frba.proyecto.citysoft.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AuthenticationFilter implements Filter {

	// private static final String PAGE_LOGIN = "login.zul";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		// TODO lograr autenticar...
		// if (!UserContext.getUserContext().isUsuarioAutenticado()) {
		// req.getRequestDispatcher(PAGE_LOGIN).forward(req, res);
		// } else {
		chain.doFilter(req, res);
		// }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
