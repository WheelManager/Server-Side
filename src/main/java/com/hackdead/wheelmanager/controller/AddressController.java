package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Address;
import com.hackdead.wheelmanager.service.IAddressService;
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
@RequestMapping("/api/addresses")
@Api(tags = "Address", value = "Service Web RESTFul of addresses")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Addresses", notes = "Method to list all Addresses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Addresses found"),
            @ApiResponse(code = 404, message = "Addresses not found")
    })
    public ResponseEntity<List<Address>> findAll() {
        try {
            List<Address> address = addressService.getAll();
            if (address.size() > 0)
                return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
            else
                return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Address>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Address by Id", notes = "Method to find an address by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Address found"),
            @ApiResponse(code = 404, message = "Address not found")
    })
    public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
        try {
            Optional<Address> address = addressService.getById(id);
            if (!address.isPresent())
                return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Address>(address.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByLongitudeAndLatitude/{longitude}/{latitude}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Address by Longitude and Latitude", notes = "Method to find address by Longitude and Latitude")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Address found"),
            @ApiResponse(code = 404, message = "Address not found")
    })
    public ResponseEntity<List<Address>> findByLongitudeAndLatitude(@PathVariable("longitude") Double longitude, @PathVariable("latitude") Double latitude) {
        try {
            List<Address> addresses = addressService.findByLongitudeAndLatitude(longitude, latitude);
            if (addresses.size() > 0)
                return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
            else
                return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Address>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Addresses", notes = "Method to register Addresses in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Address found"),
            @ApiResponse(code = 404, message = "Address not found")
    })
    public ResponseEntity<Address> insertAddress(@Valid @RequestBody Address address) {
        try {
            Address addressNew = addressService.save(address);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressNew);
        } catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Address data", notes = "Method to update Address")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Address updated"),
            @ApiResponse(code = 404, message = "Address not updated")
    })
    public ResponseEntity<Address> updateAddress(
            @PathVariable("id") Long id, @Valid @RequestBody Address address) {
        try {
            Optional<Address> addressUp = addressService.getById(id);
            if (!addressUp.isPresent())
                return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
            address.setId(id);
            addressService.save(address);
            return new ResponseEntity<Address>(address, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Address", notes = "Method to delete Address")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Address deleted"),
            @ApiResponse(code = 404, message = "Address not deleted")
    })
    public ResponseEntity<Address> deleteAddress(@PathVariable("id") Long id) {
        try {
            Optional<Address> addressDelete = addressService.getById(id);
            if (!addressDelete.isPresent())
                return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
            addressService.delete(id);
            return new ResponseEntity<Address>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
