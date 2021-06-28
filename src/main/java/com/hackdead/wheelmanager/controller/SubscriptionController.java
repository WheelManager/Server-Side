package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Subscription;
import com.hackdead.wheelmanager.service.ISubscriptionService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RestController
@RequestMapping("/api/subscriptions")
@Api(tags = "Subscriptions", value = "Service Web RESTFul of Subscriptions")
public class SubscriptionController {
    @Autowired
    private ISubscriptionService subscriptionService;

    public static Date ParseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date result = null;
        try {
            result = format.parse(date);
        } catch (Exception ex) {
        }
        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Subscriptions", notes = "Method to list all Subscriptions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Subscriptions found"),
            @ApiResponse(code = 404, message = "Subscriptions not found")
    })
    public ResponseEntity<List<Subscription>> findAll() {
        try {
            List<Subscription> subscriptions = subscriptionService.getAll();
            if (subscriptions.size() > 0)
                return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
            else
                return new ResponseEntity<List<Subscription>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Subscription>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Subscription by Id", notes = "Method to find a Subscription by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Subscription found"),
            @ApiResponse(code = 404, message = "Subscription not found")
    })
    public ResponseEntity<Subscription> findById(@PathVariable("id") Long id) {
        try {
            Optional<Subscription> subscription = subscriptionService.getById(id);
            if (!subscription.isPresent())
                return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Subscription>(subscription.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchSubscriptionByStartDate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search a Subscription by start date", notes = "Method to find Subscription by start date")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Subscriptions found"),
            @ApiResponse(code = 404, message = "Subscriptions not found"),
    })
    public ResponseEntity<List<Subscription>> findBySubscriptionByStartDate(@RequestParam(name = "startDate") String startDate) {
        try {
            Date startDateNew = ParseDate(startDate);
            List<Subscription> subscriptions = subscriptionService.findSubscriptionByStartDate(startDateNew);
            if (subscriptions.size() > 0)
                return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
            else
                return new ResponseEntity<List<Subscription>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Subscription>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Subscription", notes = "Method to register Subscription in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Subscription found"),
            @ApiResponse(code = 404, message = "Subscription not found")
    })
    public ResponseEntity<Subscription> insertSubscription(@Valid @RequestBody Subscription subscription) {
        try {
            Subscription subscriptionNew = subscriptionService.save(subscription);
            return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionNew);
        } catch (Exception e) {
            return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Subscriptions data", notes = "Method to update Subscriptions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Subscriptions updated"),
            @ApiResponse(code = 404, message = "Subscriptions not updated")
    })
    public ResponseEntity<Subscription> updateSubscription(
            @PathVariable("id") Long id, @Valid @RequestBody Subscription subscription) {
        try {
            Optional<Subscription> subscriptionUp = subscriptionService.getById(id);
            if (!subscriptionUp.isPresent())
                return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
            subscription.setId(id);
            subscriptionService.save(subscription);
            return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Subscription", notes = "Method to delete Subscription")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Subscription deleted"),
            @ApiResponse(code = 404, message = "Subscription not deleted")
    })
    public ResponseEntity<Subscription> deleteComment(@PathVariable("id") Long id) {
        try {
            Optional<Subscription> deleteSubscription = subscriptionService.getById(id);
            if (!deleteSubscription.isPresent())
                return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
            subscriptionService.delete(id);
            return new ResponseEntity<Subscription>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Subscription>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
