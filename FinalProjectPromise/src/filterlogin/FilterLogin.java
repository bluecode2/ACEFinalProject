package filterlogin;
import java.io.IOException;

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
				response.sendRedirect("login.do");
			}
			else
			{
				session.setAttribute("currUser",session.getAttribute("currUser"));
				chain.doFilter(request, response);
			}
		}
		
		/*if(session.getAttribute("currUser") == null)
		{
			System.out.println("session null");
			response.sendRedirect("login.do");
		}
		else {
			chain.doFilter(req, res);
			//System.out.println(session.getAttribute("currUser"));	
		}*/
			
	}
	
	
	public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
		//System.out.println("check login");
	}
}
