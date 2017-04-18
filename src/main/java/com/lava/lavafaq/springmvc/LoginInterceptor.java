package com.lava.lavafaq.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;


public class LoginInterceptor implements HandlerInterceptor {

	private static Logger logger = Logger.getLogger(LoginInterceptor.class); 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		     // 不在验证的范围内
		  String uri = getURI(request);		 		
		   if (exclude(uri)) {		  
			 return true;
		   }
					
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        logger.info("Pedirect to login page session username="+username);
        if (username == null|| username.length()==0 ) {          
            logger.info("Pedirect to login page");
            response.sendRedirect(getLoginUrl(request,"/login/loginHtml"));
            return false;
        }
        return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 获得第三个路径分隔符的位置
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有三(四)个'/'
	 *    http://localhost:8080/lavaFAQ/login/loginHtml       
	 *    getURI=/login/loginHtml uri=/lavaFAQ/login/loginHtml ctxPath=/lavaFAQ
	 */
	private static String getURI(HttpServletRequest request)
			throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);
		int start = 0;
		if (!StringUtils.isBlank(ctxPath)) {
			start = ctxPath.length();
		}
		logger.info("getURI="+uri.substring(start)+" uri="+uri+" ctxPath="+ctxPath);
		return uri.substring(start);
	}
	
	private boolean exclude(String uri) {
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (uri.startsWith(exc)) {
					return true;
				}
			}
		}
		return false;
	}
	//lavaFAQ---request.getContextPath()	
	private String getLoginUrl(HttpServletRequest request,String loginUrl) {
		StringBuilder buff = new StringBuilder();
		if (loginUrl.startsWith("/")) {
			String ctx = request.getContextPath();
			if (!StringUtils.isBlank(ctx)) {
				buff.append(ctx).append(loginUrl);
			}
		}
		logger.info("getLoginUrl="+buff.toString());
		return buff.toString();
	}
	
	private String[] excludeUrls;
	
	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}
