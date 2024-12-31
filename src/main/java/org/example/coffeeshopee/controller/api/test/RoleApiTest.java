package org.example.coffeeshopee.controller.api.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeeshopee.model.entity.Permission;
import org.example.coffeeshopee.model.entity.Role;
import org.example.coffeeshopee.model.service.RoleService;

import java.util.Set;


@Path("/test-role")
@Slf4j
public class RoleApiTest {
    @Inject
    private RoleService roleService;
//    @Inject
//    private PermissionService permissionService;

    @GET
    public String roleApiTest() {
        log.info("roleApiTest");
        Permission permission1 = Permission
                .builder()
                .permissionName("permission_1")
                .build();

        Permission permission2 = Permission
                .builder()
                .permissionName("permission_2")
                .build();
        Permission permission3 = Permission
                .builder()
                .permissionName("permission_3")
                .build();
        Permission permission4 = Permission
                .builder()
                .permissionName("permission_4")
                .build();



        Role role1 = Role
                .builder()
                .roleName("admin")
                .permissionSet(Set.of(permission1, permission2))
                .build();
        roleService.save(role1);

        Role role2 = Role
                .builder()
                .roleName("manager")
                .permissionSet(Set.of(permission3, permission4))
                .build();
        roleService.save(role2);

        return role1.toString() + "," + role2.toString() + "," + permission1;
    }
}
