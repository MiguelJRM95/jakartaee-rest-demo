package com.restservice.jakarta.resources;

import com.restservice.jakarta.model.Person;
import com.restservice.jakarta.service.PersonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/persons")
public class PersonResource {
	@Inject
	private PersonService ps;

	@GET
	@Produces("application/json")
	public Response getPersons(){
		List<Person> list = ps.listAll();
		return  Response.ok().status(Response.Status.OK).entity(list).build();
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("id") Integer id){
		Person p = ps.findById(id);
		return Response.ok().status(Response.Status.OK).entity(p).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPerson(Person person){
		Person p = ps.save(person);
		return Response.ok().status(Response.Status.CREATED).entity(p).build();
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(@PathParam("id") Integer id, Person person){
		if(ps.findById(id) != null){
			person.setId(id);
			Person p = ps.update(person);
			return Response.ok().status(Response.Status.OK).entity(p).build();
		}else{
			return Response.ok().status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deletePerson(@PathParam("id") Integer id){
		if(ps.findById(id) != null){
			ps.delete(id);
			return Response.ok().status(Response.Status.NO_CONTENT).build();
		}else{
			return Response.ok().status(Response.Status.NOT_FOUND).build();
		}
	}


}
