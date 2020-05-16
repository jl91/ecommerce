package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.database.UserEntity;
import com.profectusweb.ecommerce.entities.elasticsearch.UserElasticsearchEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.database.UsersRepository;
import com.profectusweb.ecommerce.requests.UsersRequestBody;
import com.profectusweb.ecommerce.services.amqp.UserAmqpService;
import com.profectusweb.ecommerce.services.database.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController()
@RequestMapping("/users")
public class UsersController {
//    extends BaseController<UserEntity, UserElasticsearchEntity>

    @Autowired
    UsersService usersService;


    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserAmqpService userAmqpService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<UserEntity> all(
            @RequestParam(name = "username", required = false) String username
    ) {

        /**
         * I know that's not the best way to implement filters, we should consider to prepare this endpoint
         * for every incomming queryParam, but to not spend more time i've implemented these if
         * */

        if (username == null) {
            return usersRepository.findAll();
        }

        Iterable<UserEntity> allByUsernameAndDeletedAtIsNull = this.usersRepository
                .findAllByUsernameAndDeletedAtIsNull(username);

        if (!allByUsernameAndDeletedAtIsNull.iterator().hasNext()) {
            throw new ResourceNotFoundException("User", "username", username);
        }
        return allByUsernameAndDeletedAtIsNull;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity byId(@PathVariable(name = "id") BigInteger id) throws ResourceNotFoundException {
        return usersRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity create(
            @Valid @RequestBody UsersRequestBody incommingRequestBody
    ) {
        UserEntity userEntity = this.usersService
                .create(incommingRequestBody);

        userAmqpService.sendUpdateMessage(userEntity.getId());

        return userEntity;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity update(
            @PathVariable(name = "id") BigInteger id,
            @Valid @RequestBody UsersRequestBody incommingRequestBody
    ) {
        incommingRequestBody.id = id;

        UserEntity userEntity = this.usersService
                .update(incommingRequestBody);

        userAmqpService.sendUpdateMessage(userEntity.getId());

        return userEntity;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
            @PathVariable(name = "id") BigInteger id
    ) {
        this.usersService
                .remove(id);
    }

}
