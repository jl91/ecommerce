package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.database.RoleEntity;
import com.profectusweb.ecommerce.repositories.database.RolesRepository;
import com.profectusweb.ecommerce.requests.RolesRequestBody;
import com.profectusweb.ecommerce.services.database.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController()
@RequestMapping("/roles")
public class RolesController extends BaseController<RoleEntity> {

    private RolesService rolesService;

    @Autowired
    RolesController(
            RolesRepository rolesRepository,
            RolesService rolesService
    ) {
        super(
                "Role",
                rolesRepository
        );
        this.rolesService = rolesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleEntity create(
            @Valid @RequestBody RolesRequestBody incommingRequestBody
    ) {
        return this.rolesService
                .create(incommingRequestBody);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleEntity update(
            @PathVariable(name = "id") BigInteger id,
            @Valid @RequestBody RolesRequestBody incommingRequestBody
    ) {
        incommingRequestBody.id = id;
        return this.rolesService
                .update(incommingRequestBody);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
            @PathVariable(name = "id") BigInteger id
    ) {
        this.rolesService
                .remove(id);
    }


}
