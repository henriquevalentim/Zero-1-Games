package br.edu.fatec.les.web.Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.edu.fatec.les.dao.DashboardDAO;
import br.edu.fatec.les.dao.IDAO;
import br.edu.fatec.les.dominio.Dashboard;
import br.edu.fatec.les.dominio.EntidadeDominio;

@WebFilter("/templates/admin/Principal.jsp")
public class FilterDashboard implements Filter {

	public FilterDashboard() {
		
	}
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		List<EntidadeDominio> dashboardList = null;
		
		if(dashboardList == null) {
			IDAO dashboardDAO = new DashboardDAO();
			Dashboard dashboard = new Dashboard();
			List<EntidadeDominio> entidades = (List<EntidadeDominio>) dashboardDAO.consultar(dashboard);
			
			dashboard = (Dashboard) entidades.get(0);
			
			request.setAttribute("dadosDashboard", entidades);
		}
		
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
