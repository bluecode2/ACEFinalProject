package filterlogin;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterLogin implements Filter {
	
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
			// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		if(request.getServletPath().equals("/login.do")){
			   chain.doFilter(request, response);
		}

		else{  
			if(session.getAttribute("currUser") == null){
				if("XMLHttpRequest".equals(
						request.getHeader("X-Requested-With")))
				{
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("window.location.href = \"login.do\";");
					out.println("</script>");
				}
				else
					response.sendRedirect("login.do");
			}
			else
			{
				session.setAttribute("currUser",session.getAttribute("currUser"));
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
	}
}
