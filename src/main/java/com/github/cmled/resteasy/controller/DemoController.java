package com.github.cmled.resteasy.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.github.cmled.resteasy.vo.DemoMessage;
import com.github.cmled.resteasy.vo.DemoName;

@Controller
@Path("/hello")
public class DemoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DemoController.class);
	
	@GET
	@Path("/world")
	@Produces("application/json")
	public DemoMessage helloworld() throws Exception {
		return new DemoMessage("Welcome, HelloWorld");
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public DemoName save(DemoName article) {
		return article;
	}
	
	/*
	 * Serve static files
	 */

	@GET
	@Path("/public/html/{fileName}")
	public File getHtml(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/html/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
	
	@GET
	@Path("/public/img/{fileName}")
	public File getImage(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/img/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
	
	@GET
	@Path("/public/js/{fileName}")
	public File getJs(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/js/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
	
	@GET
	@Path("/public/css/{fileName}")
	public File getCss(@PathParam("fileName") String fileName) throws URISyntaxException {
		URL url = this.getClass().getResource("/public/css/" + fileName);
		LOG.debug(url.toURI().toString());
		return new File(url.toURI());
	}
}
