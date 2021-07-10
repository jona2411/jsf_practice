package com.ibrito.jsf.practice.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named("indexController")
@Scope("view")
public class IndexController {

	private String title = "Controlador Principal";

	@PostConstruct
	public void init() {

		System.out.println("Initializing: Index Controller");

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
