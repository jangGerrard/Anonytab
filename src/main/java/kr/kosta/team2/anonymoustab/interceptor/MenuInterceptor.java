package kr.kosta.team2.anonymoustab.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.kosta.team2.anonymoustab.domain.Menu;
import kr.kosta.team2.anonymoustab.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MenuInterceptor implements HandlerInterceptor {
	
	@Autowired
	private MenuService menuService;
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView mav) throws Exception {
		
		List<Menu> menus = menuService.findMenus();
		request.setAttribute("menus", menus);
		
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
