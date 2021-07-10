package com.ibrito.jsf.practice.config.initializer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.ibrito.jsf.practice.utils.ViewScope;

public class TenantBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
		factory.registerScope("view", new ViewScope());
	}
}
