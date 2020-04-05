package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.RoleEntity;
import com.profectusweb.ecommerce.repositories.RolesRepository;
import com.profectusweb.ecommerce.requests.RoleRequestBody;
import com.profectusweb.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController()
@RequestMapping("/roles")
public class RolesController extends BaseController<RoleEntity> {

    private RoleService roleService;

    @Autowired
    RolesController(
            RolesRepository rolesRepository,
            RoleService roleService
    ) {
        super(
                "Role",
                rolesRepository
        );
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleEntity create(

            @Valid @RequestBody RoleRequestBody incommingRequestBody
    ) {
        return this.roleService
                .create(incommingRequestBody);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleEntity update(
            @PathVariable(name = "id") BigInteger id,
            @Valid @RequestBody RoleRequestBody incommingRequestBody
    ) {
        incommingRequestBody.id = id;
        return this.roleService
                .update(incommingRequestBody);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
            @PathVariable(name = "id") BigInteger id
    ) {
        this.roleService
                .remove(id);
    }


}
