package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.User;
import com.hackdead.wheelmanager.service.IUserService;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User", value = "Service Web RESTFul de users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "List Users", notes = "list all users")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users found"),
            @ApiResponse(code = 404, message = "Users not found")

    })
    public ResponseEntity<List<User>>findAll(){
        try {
            List<User> users = userService.getAll();
            if(users.size()>0)
                return new ResponseEntity<List<User>>(users, HttpStatus.OK);
            else
                return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Offer by username", notes = "Method to find User by username")
    @ApiResponses({
            @ApiResponse(code=201, message = "Offers found"),
            @ApiResponse(code=404, message = "Offers not found")
    })

    public ResponseEntity<List<User>> findByUsername(@PathVariable("username") String username){
        try{
            List<User> users = userService.findByUsername(username);
            if(users.size()>0)
                return new ResponseEntity<List<User>>(users, HttpStatus.OK);
            else
                return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Users", notes ="Method to register Users in the BD" )
    @ApiResponses({
            @ApiResponse(code=201, message = "User found"),
            @ApiResponse(code=404, message = "User not found")
    })
    public ResponseEntity<User> insertUser(@Valid @RequestBody User user){
        try{
            User userNew = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Update Users data", notes = "Method to update Users")
    @ApiResponses({
            @ApiResponse(code=200, message = "User updated"),
            @ApiResponse(code=404, message = "User not updated")
    })
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Long id, @Valid @RequestBody User user) {
        try{
            Optional<User> userUp = userService.getById(id);
            if(!userUp.isPresent())
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            user.setId(id);
            userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Delete User", notes = "Method to delete User")
    @ApiResponses({
            @ApiResponse(code=200, message = "User deleted"),
            @ApiResponse(code=404, message = "User not deleted")
    })
    public ResponseEntity<User> deleteUser(@PathVariable("id")Long id){
        try{
            Optional<User> userDelete = userService.getById(id);
            if(!userDelete.isPresent())
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            userService.delete(id);
            return new ResponseEntity<User>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
