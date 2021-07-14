package lee.common.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Parameter Check Filter
 * - SQL Injection & XSS Prevention - Parameter Validation Check
 *
 * @author infocert1@samsung.com)
 * @date : 2009/12/14
 */
public class ParameterFilter implements Filter {

	private FilterConfig filterConfig;

	public ParameterFilter() {
	}

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
						throws java.io.IOException, ServletException {
		// wrapper class generate
		HttpServletRequestWrapper requestWrapper = new ParameterWrapper((HttpServletRequest)request);
		// forward to next filter chain
		chain.doFilter(requestWrapper, response);
	}

}