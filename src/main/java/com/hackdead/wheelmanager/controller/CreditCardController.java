package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.CreditCard;
import com.hackdead.wheelmanager.service.ICreditCardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/creditcards")
@Api(tags = "CreditCard", value = "Service Web RESTful of Credit Card")
public class CreditCardController {
    @Autowired
    private ICreditCardService creditCardService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of Credit Cards", notes = "Method to list all credit cards")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Credit Card found"),
            @ApiResponse(code = 404, message = "Credit Card not found")
    })

    public ResponseEntity<List<CreditCard>> findAll(){
        try{
            List<CreditCard> creditCards = creditCardService.getAll();
            if (creditCards.size() > 0)
                return new ResponseEntity<List<CreditCard>>(creditCards, HttpStatus.OK);
            else
                return new ResponseEntity<List<CreditCard>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) { return new ResponseEntity<List<CreditCard>>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle Type by Id", notes = "Method to find a vehicle type by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle type found"),
            @ApiResponse(code = 404, message = "Vehicle type not found")
    })
    public ResponseEntity<CreditCard> findById(@PathVariable("id") Long id) {
        try {
            Optional<CreditCard> vehicleType = creditCardService.getById(id);
            if (!vehicleType.isPresent())
                return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<CreditCard>(vehicleType.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CreditCard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Credit card added", notes = "Method to add a Credit Card in the DB")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Credit Card found"),
            @ApiResponse(code = 404, message = "Credit Card not found")
    })
    public ResponseEntity<CreditCard> insertCreditCard(@Valid @RequestBody CreditCard vehicleType) {
        try {
            CreditCard vehicleTypeNew = creditCardService.save(vehicleType);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleTypeNew);
        } catch (Exception e) {
            return new ResponseEntity<CreditCard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Credit Card", notes = "Method to delete Credit Card")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Credit Card deleted"),
            @ApiResponse(code = 404, message = "Credit Card not deleted")
    })
    public ResponseEntity<CreditCard> deleteVehicle(@PathVariable("id") Long id) {
        try {
            Optional<CreditCard> deleteVehicle = creditCardService.getById(id);
            if (!deleteVehicle.isPresent())
                return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
            creditCardService.delete(id);
            return new ResponseEntity<CreditCard>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CreditCard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
