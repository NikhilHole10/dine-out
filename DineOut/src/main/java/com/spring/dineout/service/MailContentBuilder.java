package com.spring.dineout.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
	private final TemplateEngine template;
	
	String build(String message) {
		Context context = new Context();
		context.setVariable("meesage",message);
		return template.process("mailTemplate", context);
	}
}
