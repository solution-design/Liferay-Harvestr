package com.solutiondesign.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@RequestMapping("EDIT")
public class RSSEditController {

	@RenderMapping
	public String edit() {
		return "edit-global";
	}
}
