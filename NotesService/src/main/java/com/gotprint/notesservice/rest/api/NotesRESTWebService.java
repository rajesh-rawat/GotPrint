package com.gotprint.notesservice.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("notes")
public class NotesRESTWebService {
	
	@Path("save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save() {
		return Response.status(200).entity("Success").build();
	}

	@Path("update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update() {
		return Response.status(200).entity("Success").build();
	}

	@Path("read/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read() {
		return Response.status(200).entity("Success").build();
	}

	@Path("delete")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete() {
		return Response.status(200).entity("Success").build();
	}

}
