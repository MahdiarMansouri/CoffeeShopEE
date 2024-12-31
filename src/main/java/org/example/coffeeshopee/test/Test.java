package org.example.coffeeshopee.test;

import org.example.coffeeshopee.model.service.RoleService;

public class Test  {
    public static void main(String[] args) {
        RoleService roleService = new RoleService();
        roleService.findAll();
    }

}
