package com.gotprint.notesservice.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gotprint.notesservice.domain.object.Note;
import com.gotprint.notesservice.service.DaoService;

@Path("notes")
public class NotesRESTWebService {
	
	@Path("save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Note n) {
		try {
			DaoService service = new DaoService();
			service.addNote(n);
		} catch (Exception e) {
			return Response.status(404).entity("Failure").build();
		}
		return Response.status(200).entity("Success").build();
	}

	@Path("update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Note n) {
		try {
			DaoService service = new DaoService();
			service.updateNote(n);
		} catch (Exception e) {
			return Response.status(404).entity("Failure").build();
		}
		return Response.status(200).entity("Success").build();
	}

	@Path("read/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") long id) {
		Note note = null;
		try {
			DaoService service = new DaoService();
			note = service.getNoteById(id);
		} catch (Exception e) {
			return Response.status(404).entity("Failure").build();
		}
		return Response.status(200).entity(note).build();
	}

	@Path("delete")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(Note n) {
		try {
			DaoService service = new DaoService();
			service.deleteNote(n);
		} catch (Exception e) {
			return Response.status(404).entity("Failure").build();
		}
		return Response.status(200).entity("Success").build();
	}

}
