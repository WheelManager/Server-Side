package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Vehicle;
import com.hackdead.wheelmanager.service.IVehicleService;
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
@RequestMapping("/api/vehicles")
@Api(tags = "Vehicles", value = "Service Web RESTFul of vehicles")
public class VehicleController {
    @Autowired
    private IVehicleService vehicleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Vehicles", notes = "Method to list all vehicles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vehicles found"),
            @ApiResponse(code = 404, message = "Vehicles not found")
    })
    public ResponseEntity<List<Vehicle>> findAll() {
        try {
            List<Vehicle> vehicles = vehicleService.getAll();
            if (vehicles.size() > 0)
                return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
            else
                return new ResponseEntity<List<Vehicle>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<Vehicle>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle by Id", notes = "Method to find a Vehicle by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle found"),
            @ApiResponse(code = 404, message = "Vehicle not found")
    })
    public ResponseEntity<Vehicle> findById(@PathVariable("id") Long id) {
        try {
            Optional<Vehicle> vehicles = vehicleService.getById(id);
            if (!vehicles.isPresent())
                return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Vehicle>(vehicles.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByVehicleName/{vehicleName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle by name", notes = "Method to find Vehicle by name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle found"),
            @ApiResponse(code = 404, message = "Vehicle not found"),
    })
    public ResponseEntity<List<Vehicle>> findByVehicleName(@PathVariable("vehicleName") String vehicleName) {
        try {
            List<Vehicle> vehicles = vehicleService.findByVehicleName(vehicleName);
            if (vehicles.size() > 0)
                return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
            else
                return new ResponseEntity<List<Vehicle>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Vehicle>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Vehicles", notes = "Method to register Vehicles in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle found"),
            @ApiResponse(code = 404, message = "Vehicle not found")
    })
    public ResponseEntity<Vehicle> insertVehicle(@Valid @RequestBody Vehicle vehicle) {
        try {
            Vehicle vehicleNew = vehicleService.save(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleNew);
        } catch (Exception e) {
            return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Vehicles data", notes = "Method to update Vehicles")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vehicle updated"),
            @ApiResponse(code = 404, message = "Vehicle not updated")
    })
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable("id") Long id, @Valid @RequestBody Vehicle vehicle) {
        try {
            Optional<Vehicle> vehicleUp = vehicleService.getById(id);
            if (!vehicleUp.isPresent())
                return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
            vehicle.setId(id);
            vehicleService.save(vehicle);
            return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Vehicle", notes = "Method to delete Vehicle")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vehicle deleted"),
            @ApiResponse(code = 404, message = "Vehicle not deleted")
    })
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") Long id) {
        try {
            Optional<Vehicle> deleteVehicle = vehicleService.getById(id);
            if (!deleteVehicle.isPresent())
                return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
            vehicleService.delete(id);
            return new ResponseEntity<Vehicle>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Vehicle>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
