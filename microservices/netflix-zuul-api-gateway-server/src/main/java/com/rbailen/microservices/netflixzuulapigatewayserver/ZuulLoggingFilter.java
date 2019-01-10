package com.rbailen.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/* Logica para la intercepcion (en este caso, los detalles de la request) */
	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

		logger.info("request -> {} request uri -> {}", request,
				request.getRequestURI());

		return null;
	}

	/* Indicamos si se ejecuta el filtro o no para cada request */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/* Si tenemos varios filtros podemos establecer el orden de prioridad */
	@Override
	public int filterOrder() {
		return 1;
	}

	/*
	 * Indica si el filtro debe ser ejecutado antes (pre) o despues (post)
	 * de que la request sea ejecutada. Tambien si solamente queremos
	 * filtrar error requests que han causado una excepcion. En este caso,
	 * queremos interceptar las peticiones antes de que sean ejecutadas
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
