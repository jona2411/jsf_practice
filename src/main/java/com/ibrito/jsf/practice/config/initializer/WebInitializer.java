package com.ibrito.jsf.practice.config.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ibrito.jsf.practice.config.WebConfig;
import com.ibrito.jsf.practice.config.WebSecurityConfig;
import com.sun.faces.config.ConfigureListener;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static Logger log = Logger.getLogger(WebInitializer.class);
	public static String REAL_PATH;
	public static String REAL_PATH_REPORT;
	public static String FILE_SEPARATOR;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		servletContext.addListener(ConfigureListener.class);

		servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8"));
		servletContext.addListener(new RequestContextListener());
		AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		webCtx.register(WebConfig.class);
		// webCtx.register(WebSecurityConfig.class);

		webCtx.setServletContext(servletContext);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(webCtx);

		// throw NoHandlerFoundException to Controller
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		servlet.addMapping("*.xhtml");

		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(webCtx);

		servletContext.addListener(contextLoaderListener);

	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { WebSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;// new Class<?>[] { ErrorHandleFilter.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}
}
