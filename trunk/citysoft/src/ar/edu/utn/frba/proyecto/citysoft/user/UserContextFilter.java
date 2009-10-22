package ar.edu.utn.frba.proyecto.citysoft.user;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.utn.frba.proyecto.citysoft.controller.ConstantesGeneralesDeVentanas;

public class UserContextFilter implements ConstantesGeneralesDeVentanas, Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws java.io.IOException, javax.servlet.ServletException {
		HttpSession session = ((HttpServletRequest) req).getSession(true);
		UserContext userContext = (UserContext) session.getAttribute(SESSION__USER_CONTEXT);
		// Si es la primera vez que pasa por aca, el userContext es nulo. Por
		// tanto, lo creamos
		if (userContext == null) {
			userContext = new UserContext();
			session.setAttribute(SESSION__USER_CONTEXT, userContext);
		}
		// Ahora, ponemos a disponibilidad del thread el userContext posta
		UserContext.setUserContext(userContext);
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
