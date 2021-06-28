package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.UserAddress;
import com.hackdead.wheelmanager.service.IUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RestController
@RequestMapping("/api/userAddress")
@Api(tags = "User Addresses", value = "Service Web RESTFul of userAddresses")
public class UserAddressController {
    @Autowired
    private IUserAddressService userAddressService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List UserAddresses", notes = "Method to list all UserAddresses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "UserAddresses found"),
            @ApiResponse(code = 404, message = "UserAddresses not found")
    })
    public ResponseEntity<List<UserAddress>> findAll() {
        try {
            List<UserAddress> userAddresses = userAddressService.getAll();
            if (userAddresses.size() > 0)
                return new ResponseEntity<List<UserAddress>>(userAddresses, HttpStatus.OK);
            else
                return new ResponseEntity<List<UserAddress>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<UserAddress>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search UserAddress by Id", notes = "Method to find a UserAddress by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "UserAddress found"),
            @ApiResponse(code = 404, message = "UserAddress not found")
    })
    public ResponseEntity<UserAddress> findById(@PathVariable("id") Long id) {
        try {
            Optional<UserAddress> userAddress = userAddressService.getById(id);
            if (!userAddress.isPresent())
                return new ResponseEntity<UserAddress>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<UserAddress>(userAddress.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserAddress>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of UserAddress", notes = "Method to register UserAddress in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "UserAddress found"),
            @ApiResponse(code = 404, message = "UserAddress not found")
    })
    public ResponseEntity<UserAddress> insertUserAddress(@Valid @RequestBody UserAddress userAddress) {
        try {
            UserAddress userAddressNew = userAddressService.save(userAddress);
            return ResponseEntity.status(HttpStatus.CREATED).body(userAddressNew);
        } catch (Exception e) {
            return new ResponseEntity<UserAddress>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update UserAddresses data", notes = "Method to update UserAddresses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "UserAddress updated"),
            @ApiResponse(code = 404, message = "UserAddress not updated")
    })
    public ResponseEntity<UserAddress> updateUserAddress(
            @PathVariable("id") Long id, @Valid @RequestBody UserAddress userAddress) {
        try {
            Optional<UserAddress> userAddressUp = userAddressService.getById(id);
            if (!userAddressUp.isPresent())
                return new ResponseEntity<UserAddress>(HttpStatus.NOT_FOUND);
            userAddress.setId(id);
            userAddressService.save(userAddress);
            return new ResponseEntity<UserAddress>(userAddress, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserAddress>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete UserAddress", notes = "Method to delete UserAddress")
    @ApiResponses({
            @ApiResponse(code = 200, message = "UserAddress deleted"),
            @ApiResponse(code = 404, message = "UserAddress not deleted")
    })
    public ResponseEntity<UserAddress> deleteUserAddress(@PathVariable("id") Long id) {
        try {
            Optional<UserAddress> deleteUserAddress = userAddressService.getById(id);
            if (!deleteUserAddress.isPresent())
                return new ResponseEntity<UserAddress>(HttpStatus.NOT_FOUND);
            userAddressService.delete(id);
            return new ResponseEntity<UserAddress>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserAddress>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
