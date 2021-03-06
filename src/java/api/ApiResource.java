/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.PersonControl;
import entity.User;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import control.UserControl;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author Daniel
 */
@Path("/test")
public class ApiResource {

    @Context
    private UriInfo context;
    PersonControl pc = new PersonControl();

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("hello/name={name},lastname={lastname}")
    public Response getTest(@PathParam("name") String name, @PathParam("lastname") String lastname) {
        String welcome = "Hello " + name + " " + lastname;
        return Response.status(Status.OK).entity(gson.toJson(welcome)).build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
        return Response.status(Status.OK).entity(gson.toJson(pc.getPersons())).build();
    }

    @POST
    @Path("/add")
    public Response addUser(
            @FormParam("username") String username,
            @FormParam("password") String password) {
        new UserControl().addUser(new User(username, password));
        return Response.status(Status.NO_CONTENT).build();
    }

}
