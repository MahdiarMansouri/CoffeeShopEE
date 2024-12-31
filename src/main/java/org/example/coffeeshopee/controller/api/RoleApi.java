package org.example.coffeeshopee.controller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Role;
import org.example.coffeeshopee.model.service.RoleService;
import org.example.coffeeshopee.controller.interceptor.annotation.Loggable;


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

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getRoleById(@PathParam("id") Long id) {
        log.info("Get role by id ");
        return roleService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getRoleByName(@PathParam("name") String name) {
        log.info("Get role by name ");
        return roleService.findByRoleName(name);
    }

    @GET
    @Path("/username/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getRoleByUsername(@PathParam("username") String username) {
        log.info("Get role by username ");
        return roleService.findByUsername(username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/permission/{permission}")
    @Loggable
    public Object getRoleByPermission(@PathParam("permission") String permission) {
        log.info("Get role by permission ");
        return roleService.findByPermission(permission);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object addRole(Role role) {
        log.info("Add role ");
        roleService.save(role);
        return role;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object updateRole(Role role) {
        log.info("Update role ");
        roleService.edit(role);
        return role;
    }

    @DELETE
    @Path("{id}")
    @Loggable
    public Object deleteRole(@PathParam("id") Long id) {
        log.info("Delete role by id ");
        Role role = roleService.remove(id);
        return role.getId();
    }


}
