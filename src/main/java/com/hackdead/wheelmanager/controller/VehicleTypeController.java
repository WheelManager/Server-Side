package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.VehicleType;
import com.hackdead.wheelmanager.service.IVehicleTypeService;
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
@RequestMapping("/api/vehicletypes")
@Api(tags = "Vehicle Types", value = "Service Web RESTful of Vehicle Types")
public class VehicleTypeController {
    @Autowired
    private IVehicleTypeService vehicleTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Vehicle Types", notes = "Method to list all vehicle types")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vehicle Types found"),
            @ApiResponse(code = 404, message = "Vehicle Types not found")
    })
    public ResponseEntity<List<VehicleType>> findAll() {
        try {
            List<VehicleType> vehicleTypes = vehicleTypeService.getAll();
            if (vehicleTypes.size() > 0)
                return new ResponseEntity<List<VehicleType>>(vehicleTypes, HttpStatus.OK);
            else
                return new ResponseEntity<List<VehicleType>>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<List<VehicleType>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle Type by Id", notes = "Method to find a vehicle type by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle type found"),
            @ApiResponse(code = 404, message = "Vehicle type not found")
    })
    public ResponseEntity<VehicleType> findById(@PathVariable("id") Long id) {
        try {
            Optional<VehicleType> vehicleType = vehicleTypeService.getById(id);
            if (!vehicleType.isPresent())
                return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<VehicleType>(vehicleType.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByTypeName/{typeName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle type by name", notes = "Method to find Vehicle type by name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle type found"),
            @ApiResponse(code = 404, message = "Vehicle type not found"),
    })
    public ResponseEntity<List<VehicleType>> findByTypeName(@PathVariable("typeName") String typeName) {
        try {
            List<VehicleType> vehicleTypes = vehicleTypeService.findByTypeName(typeName);
            if (vehicleTypes.size() > 0)
                return new ResponseEntity<List<VehicleType>>(vehicleTypes, HttpStatus.OK);
            else
                return new ResponseEntity<List<VehicleType>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<VehicleType>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Vehicles", notes = "Method to register Vehicles in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Vehicle type found"),
            @ApiResponse(code = 404, message = "Vehicle type not found")
    })
    public ResponseEntity<VehicleType> insertVehicle(@Valid @RequestBody VehicleType vehicleType) {
        try {
            VehicleType vehicleTypeNew = vehicleTypeService.save(vehicleType);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleTypeNew);
        } catch (Exception e) {
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Vehicle types data", notes = "Method to update Vehicle Types")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vehicle type updated"),
            @ApiResponse(code = 404, message = "Vehicle type not updated")
    })
    public ResponseEntity<VehicleType> updateVehicle(
            @PathVariable("id") Long id, @Valid @RequestBody VehicleType vehicleType) {
        try {
            Optional<VehicleType> vehicleUp = vehicleTypeService.getById(id);
            if (!vehicleUp.isPresent())
                return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
            vehicleType.setId(id);
            vehicleTypeService.save(vehicleType);
            return new ResponseEntity<VehicleType>(vehicleType, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Vehicle Type", notes = "Method to delete Vehicle Type")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Vehicle type deleted"),
            @ApiResponse(code = 404, message = "Vehicle type not deleted")
    })
    public ResponseEntity<VehicleType> deleteVehicle(@PathVariable("id") Long id) {
        try {
            Optional<VehicleType> deleteVehicle = vehicleTypeService.getById(id);
            if (!deleteVehicle.isPresent())
                return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
            vehicleTypeService.delete(id);
            return new ResponseEntity<VehicleType>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
