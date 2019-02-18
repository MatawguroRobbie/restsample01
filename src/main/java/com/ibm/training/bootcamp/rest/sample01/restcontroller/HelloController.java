package com.ibm.training.bootcamp.rest.sample01.restcontroller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {

		return "Hello World! This is coming from a REST webservice!!";

	}

	@GET
	@Path("{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHelloName(@PathParam("name") String name) {

		return "Hello " + name + "! Welcome to REST!!";

	}
}
