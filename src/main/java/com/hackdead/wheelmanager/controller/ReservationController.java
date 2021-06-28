package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Reservation;
import com.hackdead.wheelmanager.service.IReservationService;
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
@RequestMapping("/api/reservations")
@Api(tags = "Reservations", value = "Service Web RESTFul of reservations")
public class ReservationController {
    @Autowired
    private IReservationService reservationService;

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
    @ApiOperation(value = "List Reservations", notes = "Method to list all reservations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Reservations found"),
            @ApiResponse(code = 404, message = "Reservations not found")
    })
    public ResponseEntity<List<Reservation>> findAll() {
        try {
            List<Reservation> reservations = reservationService.getAll();
            if (reservations.size() > 0)
                return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
            else
                return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Reservation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Reservation by Id", notes = "Method to find a Reservation by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Reservation found"),
            @ApiResponse(code = 404, message = "Reservation not found")
    })
    public ResponseEntity<Reservation> findById(@PathVariable("id") Long id) {
        try {
            Optional<Reservation> reservation = reservationService.getById(id);
            if (!reservation.isPresent())
                return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Reservation>(reservation.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchReservationBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search a Reservation between dates", notes = "Method to find Reservations between dates")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Reservations found"),
            @ApiResponse(code = 404, message = "Reservations not found"),
    })
    public ResponseEntity<List<Reservation>> findByReservationBetweenDates(@RequestParam(name = "startDate") String startDate,
                                                                           @RequestParam(name = "endDate") String endDate) {
        try {
            Date startDateNew = ParseDate(startDate);
            Date endDateNew = ParseDate(endDate);
            List<Reservation> reservations = reservationService.findBetweenDate(startDateNew, endDateNew);
            if (reservations.size() > 0)
                return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
            else
                return new ResponseEntity<List<Reservation>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Reservation>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Reservations", notes = "Method to register Reservations in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Reservation found"),
            @ApiResponse(code = 404, message = "Reservation not found")
    })
    public ResponseEntity<Reservation> insertReservation(@Valid @RequestBody Reservation reservation) {
        try {
            Reservation reservationNew = reservationService.save(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservationNew);
        } catch (Exception e) {
            return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Reservations data", notes = "Method to update Reservations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Reservation updated"),
            @ApiResponse(code = 404, message = "Reservation not updated")
    })
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable("id") Long id, @Valid @RequestBody Reservation reservation) {
        try {
            Optional<Reservation> reservationUp = reservationService.getById(id);
            if (!reservationUp.isPresent())
                return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
            reservation.setId(id);
            reservationService.save(reservation);
            return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Reservation", notes = "Method to delete Reservation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Reservation deleted"),
            @ApiResponse(code = 404, message = "Reservation not deleted")
    })
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Long id) {
        try {
            Optional<Reservation> deleteReservation = reservationService.getById(id);
            if (!deleteReservation.isPresent())
                return new ResponseEntity<Reservation>(HttpStatus.NOT_FOUND);
            reservationService.delete(id);
            return new ResponseEntity<Reservation>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Reservation>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
