package org.example.coffeeshopee.controller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.service.RoleService;
import org.example.coffeeshopee.model.utils.Loggable;

@Path("/role")
@Slf4j
public class RoleApi {
    @Inject
    private RoleService roleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getRoles() {
        log.info("Get roles");
        return roleService.findAll();
    }
//    @POST
//    @PUT
//    @DELETE

}
