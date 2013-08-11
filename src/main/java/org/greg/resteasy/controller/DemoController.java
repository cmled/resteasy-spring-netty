package org.greg.resteasy.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.greg.resteasy.vo.DemoMessage;
import org.greg.resteasy.vo.DemoName;
import org.springframework.stereotype.Controller;

@Controller
@Path("/hello")
public class DemoController {

	@GET
	@Path("/world")
	@Produces("application/json")
	public DemoMessage helloworld() throws Exception {
		return new DemoMessage("Welcome, HelloWorld");
	}

	@GET
	@Path("/auth")
	@Produces("application/json")
	public DemoMessage auth(@Context SecurityContext context) {
		return new DemoMessage(context.getUserPrincipal().getName());
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public DemoName save(DemoName article) {
		return article;
	}


}