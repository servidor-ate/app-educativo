package br.com.jsf.primefaces.filtros;

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
import javax.servlet.http.HttpSession;

import br.com.jsf.primefaces.modelo.UsuarioModelo;

@WebFilter("/sistema/*")
public class AutenticacaoFiltro implements Filter {

	public AutenticacaoFiltro() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession = ((HttpServletRequest) request).getSession();

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1) {

			UsuarioModelo usuarioModel = (UsuarioModelo) httpSession
					.getAttribute("usuarioAutenticado");

			if (usuarioModel == null) {

				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/index.xhtml");

			} else {

				chain.doFilter(request, response);
			}
		} else {

			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
