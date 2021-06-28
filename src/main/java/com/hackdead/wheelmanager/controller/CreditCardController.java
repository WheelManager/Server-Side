package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.CreditCard;
import com.hackdead.wheelmanager.service.ICreditCardService;
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
@RequestMapping("/api/creditCards")
@Api(tags = "Credit Card", value = "Service Web RESTful of Credit Card")
public class CreditCardController {
    @Autowired
    private ICreditCardService creditCardService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of Credit Cards", notes = "Method to list all credit cards")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Credit Card found"),
            @ApiResponse(code = 404, message = "Credit Card not found")
    })
    public ResponseEntity<List<CreditCard>> findAll() {
        try {
            List<CreditCard> creditCards = creditCardService.getAll();
            if (creditCards.size() > 0)
                return new ResponseEntity<List<CreditCard>>(creditCards, HttpStatus.OK);
            else
                return new ResponseEntity<List<CreditCard>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<CreditCard>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle Type by Id", notes = "Method to find a vehicle type by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle type found"),
            @ApiResponse(code = 404, message = "Vehicle type not found")
    })
    public ResponseEntity<CreditCard> findById(@PathVariable("id") Long id) {
        try {
            Optional<CreditCard> creditCard = creditCardService.getById(id);
            if (!creditCard.isPresent())
                return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<CreditCard>(creditCard.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CreditCard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByCardNumber/{cardNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Credit Card by name", notes = "Method to find Credit Card by name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Credit Card found"),
            @ApiResponse(code = 404, message = "Credit Card not found")
    })
    public ResponseEntity<List<CreditCard>> findByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        try {
            List<CreditCard> creditCards = creditCardService.findCreditCardsByCardNumber(cardNumber);
            if (creditCards.size() > 0)
                return new ResponseEntity<List<CreditCard>>(creditCards, HttpStatus.OK);
            else
                return new ResponseEntity<List<CreditCard>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<CreditCard>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Credit Card data", notes = "Method to update Credit Card")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Credit Card updated"),
            @ApiResponse(code = 404, message = "Credit Card not updated")
    })
    public ResponseEntity<CreditCard> updateCreditCard(
            @PathVariable("id") Long id, @Valid @RequestBody CreditCard creditCard) {
        try {
            Optional<CreditCard> creditCardUp = creditCardService.getById(id);
            if (!creditCardUp.isPresent())
                return new ResponseEntity<CreditCard>(HttpStatus.NOT_FOUND);
            creditCard.setId(id);
            creditCardService.save(creditCard);
            return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CreditCard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Credit card added", notes = "Method to add a Credit Card in the DB")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Credit Card found"),
            @ApiResponse(code = 404, message = "Credit Card not found")
    })
    public ResponseEntity<CreditCard> insertCreditCard(@Valid @RequestBody CreditCard creditCard) {
        try {
            CreditCard creditCardNew = creditCardService.save(creditCard);
            return ResponseEntity.status(HttpStatus.CREATED).body(creditCardNew);
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
