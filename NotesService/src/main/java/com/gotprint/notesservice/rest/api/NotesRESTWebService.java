package com.gotprint.notesservice.rest.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gotprint.notesservice.constants.Constants;
import com.gotprint.notesservice.domain.object.Note;
import com.gotprint.notesservice.domain.object.User;
import com.gotprint.notesservice.generic.exception.BusinessException;
import com.gotprint.notesservice.rest.api.validator.Validator;
import com.gotprint.notesservice.rest.input.json.UserInput;
import com.gotprint.notesservice.rest.output.json.Result;
import com.gotprint.notesservice.service.DaoService;

/**
 * @author Rajesh Rawat
 *
 * REST Webservice for CRUD operations for Notes associated to the user
 * C - Create
 * R - Read
 * U - Update
 * D - Delete
 * Validation is done for business Exceptions
 * JSON is the supported input and output type
 * 
 */
@Path("notes")
public class NotesRESTWebService {

	@Path("save")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(UserInput input, @HeaderParam("authorization") String authString) {
		Result r = new Result();
		try {
			DaoService service = new DaoService();
			Note n = validateAndPrepare(input);

			Validator.vaidateOperationAccess(authString, n);
			service.addNote(n);
		} catch (BusinessException e) {
			r.setStatus(Constants.STATUS_FAILURE);
			r.setMessage(e.getMessage());
			return Response.status(404).entity(r).build();
		}
		r.setStatus(Constants.STATUS_SUCCESS);
		r.setMessage("Note Saved for Email : " + input.getEmail());
		return Response.status(200).entity(r).build();
	}

	@Path("update")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(UserInput input, @HeaderParam("authorization") String authString) {
		Result r = new Result();
		try {
			DaoService service = new DaoService();
			Note n = validateAndPrepare(input);
			Validator.vaidateOperationAccess(authString, n);
			service.updateNote(n);
		} catch (BusinessException e) {
			r.setStatus(Constants.STATUS_FAILURE);
			r.setMessage(e.getMessage());
			return Response.status(404).entity(r).build();
		}
		r.setStatus(Constants.STATUS_SUCCESS);
		r.setMessage("Note Updated for Email : " + input.getEmail());
		return Response.status(200).entity(r).build();
	}

	@Path("read/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") long id) {
		Result r = new Result();
		Note note = null;
		try {
			DaoService service = new DaoService();
			note = service.getNoteById(id);
		} catch (BusinessException e) {
			r.setStatus(Constants.STATUS_FAILURE);
			r.setMessage(e.getMessage());
			return Response.status(404).entity(r).build();
		}
		r.setStatus(Constants.STATUS_SUCCESS);
		r.setMessage(note.getNote());
		return Response.status(200).entity(r).build();
	}

	@Path("delete")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(UserInput input, @HeaderParam("authorization") String authString) {
		Result r = new Result();
		try {
			DaoService service = new DaoService();
			Note n = validateAndPrepare(input);
			Validator.vaidateOperationAccess(authString, n);
			service.deleteNote(n);
		} catch (BusinessException e) {
			r.setStatus(Constants.STATUS_FAILURE);
			r.setMessage(e.getMessage());
			return Response.status(404).entity(r).build();
		}
		r.setStatus(Constants.STATUS_SUCCESS);
		r.setMessage("Note with Title " + input.getTitle() + " deleted.");
		return Response.status(200).entity(r).build();
	}

	private Note validateAndPrepare(UserInput input) throws BusinessException {
		System.out.println(input);
		System.out.println("input.getEmail() : " + input.getEmail());
		System.out.println("input.getNote() : " + input.getNote());
		System.out.println("input.getTitle() : " + input.getTitle());
		Map<String, String> map = new HashMap<String, String>();
		map.put(Constants.KEY_EMAIL, input.getEmail());
		map.put(Constants.KEY_NOTE, input.getNote());
		map.put(Constants.KEY_TITLE, input.getTitle());
		Validator.validate(map);

		Note n = new Note();
		n.setTitle(input.getTitle());
		n.setNote(input.getNote());
		User user = new User();
		user.setEmail(input.getEmail());
		n.setUser(user);
		return n;
	}
}
