package com.ibrito.jsf.practice.config.initializer;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
@Order(1)
public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
