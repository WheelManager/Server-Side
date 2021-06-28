package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.RentalActivity;
import com.hackdead.wheelmanager.service.IRentalActivityService;
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
@RequestMapping("/api/rentalActivities")
@Api(tags = "Rental Activities", value = "Service Web RESTFul of rental activities")
public class RentalActivityController {
    @Autowired
    private IRentalActivityService rentalActivityService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Rental Activities", notes = "Method to list all Rental Activities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Rental Activities found"),
            @ApiResponse(code = 404, message = "Rental Activities not found")
    })
    public ResponseEntity<List<RentalActivity>> findAll() {
        try {
            List<RentalActivity> rentalActivities = rentalActivityService.getAll();
            if (rentalActivities.size() > 0)
                return new ResponseEntity<List<RentalActivity>>(rentalActivities, HttpStatus.OK);
            else
                return new ResponseEntity<List<RentalActivity>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<RentalActivity>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Rental Activity by Id", notes = "Method to find a Rental Activity by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Rental Activity found"),
            @ApiResponse(code = 404, message = "Rental Activity not found")
    })
    public ResponseEntity<RentalActivity> findById(@PathVariable("id") Long id) {
        try {
            Optional<RentalActivity> rentalActivity = rentalActivityService.getById(id);
            if (!rentalActivity.isPresent())
                return new ResponseEntity<RentalActivity>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<RentalActivity>(rentalActivity.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<RentalActivity>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Rental Activity", notes = "Method to register Rental Activity in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Rental Activity found"),
            @ApiResponse(code = 404, message = "Rental Activity not found")
    })
    public ResponseEntity<RentalActivity> insertRentalActivity(@Valid @RequestBody RentalActivity rentalActivity) {
        try {
            RentalActivity rentalActivityNew = rentalActivityService.save(rentalActivity);
            return ResponseEntity.status(HttpStatus.CREATED).body(rentalActivityNew);
        } catch (Exception e) {
            return new ResponseEntity<RentalActivity>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Rental Activities data", notes = "Method to update Rental Activities")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Rental Activity updated"),
            @ApiResponse(code = 404, message = "Rental Activity not updated")
    })
    public ResponseEntity<RentalActivity> updateRentalActivity(
            @PathVariable("id") Long id, @Valid @RequestBody RentalActivity rentalActivity) {
        try {
            Optional<RentalActivity> rentalActivityUp = rentalActivityService.getById(id);
            if (!rentalActivityUp.isPresent())
                return new ResponseEntity<RentalActivity>(HttpStatus.NOT_FOUND);
            rentalActivity.setId(id);
            rentalActivityService.save(rentalActivity);
            return new ResponseEntity<RentalActivity>(rentalActivity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<RentalActivity>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Rental Activity", notes = "Method to delete Rental Activity")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Rental Activity deleted"),
            @ApiResponse(code = 404, message = "Rental Activity not deleted")
    })
    public ResponseEntity<RentalActivity> deleteRentalActivity(@PathVariable("id") Long id) {
        try {
            Optional<RentalActivity> deleteRentalActivity = rentalActivityService.getById(id);
            if (!deleteRentalActivity.isPresent())
                return new ResponseEntity<RentalActivity>(HttpStatus.NOT_FOUND);
            rentalActivityService.delete(id);
            return new ResponseEntity<RentalActivity>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<RentalActivity>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
