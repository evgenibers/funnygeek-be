package hackathon.hardcode.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * CORS filter.
 * 
 * @author Evgeni Bokhanov
 */
@Component
public class CORSFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger();
		
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
    throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (httpServletResponse.getHeader("Access-Control-Allow-Origin") == null) {
			httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        }
        if (httpServletResponse.getHeader("Access-Control-Allow-Methods") == null) {
        	httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        }
        if (httpServletResponse.getHeader("Access-Control-Allow-Headers") == null) {
        	httpServletResponse.addHeader("Access-Control-Allow-Headers",
        		"Origin, X-Requested-With, Content-Type, Accept, Authorization");
        }
        
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.addHeader("Allow", "GET, POST, PUT, DELETE, OPTIONS");
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
        
}