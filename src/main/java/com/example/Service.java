package com.example;

import com.example.models.Badge;
import com.example.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/img", description = "Endpoint to Service")
@Path("/img")
public class Service {
    private Server server;

    public Service() {
        this.server = ServerImpl.getInstance();
        List<String> list1 = new ArrayList<>();
        list1.add("moon");
        list1.add("sun");
        this.server.addUser(new User("sgarciac", 0,"http://localhost:8080/img/avatar1.png", "Sergi", "Garcia", list1));
        List<String> list2 = new ArrayList<>();
        list2.add("star");
        list2.add("planet");
        list2.add("moon");
        this.server.addUser(new User("jlopezr", 0,"http://localhost:8080/img/avatar2.png", "Juan", "Lopez", list2));
        this.server.addBadge(new Badge("moon", "http://localhost:8080/img/moon.png"));
        this.server.addBadge(new Badge("sun", "http://localhost:8080/img/sun.png"));
        this.server.addBadge(new Badge("star", "http://localhost:8080/img/star.png"));
        this.server.addBadge(new Badge("planet", "http://localhost:8080/img/planet.png"));
    }

    @GET
    @ApiOperation(value = "get all Badges", notes = "Get badgesList")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Badge.class, responseContainer="List"),
    })
    @Path("/badges")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBadges() {

        List<Badge> badges = this.server.getBadges();

        GenericEntity<List<Badge>> entity = new GenericEntity<List<Badge>>(badges) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get a User", notes = "getUserById")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack(@PathParam("id") Integer id) {
        User u = this.server.getUser(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }
}
