package com.ibm.training.bootcamp.rest.sample01.restcontroller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.ibm.training.bootcamp.rest.sample01.domain.User;
import com.ibm.training.bootcamp.rest.sample01.service.UserService;
import com.ibm.training.bootcamp.rest.sample01.service.UserServiceImpl;

@Path("/users")
public class UsersController {

	private UserService userService;

	public UsersController() {
		this.userService = new UserServiceImpl();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(
			@QueryParam("firstName") String firstName, 
			@QueryParam("lastName") String lastName) {

		try {
			List<User> users;
			
			if (StringUtils.isAllBlank(firstName, lastName)) {
				users = userService.findAll();
			} else {
				users = userService.findByName(firstName, lastName);
			}
						
			return users;
			
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String id) {

		try {
			Long longId = Long.parseLong(id);
			User user = userService.find(longId);
			return user;
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {

		try {
			userService.add(user);
			String result = "User saved : " + user.getFirstName() + " " + user.getLastName();
			return Response.status(201).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {

		try {
			userService.upsert(user);
			String result = "User updated : " + user.getFirstName() + " " + user.getLastName();
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}

	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") String id) {

		try {
			Long longId = Long.parseLong(id);
			userService.delete(longId);
			String result = "User deleted";
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
