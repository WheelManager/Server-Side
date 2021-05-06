package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Brand;
import com.hackdead.wheelmanager.entities.VehicleType;
import com.hackdead.wheelmanager.service.IVehicleTypeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicletypes")
@Api(tags="VehicleTypes", value = "Service Web RESTful of Vehicle Types")
public class VehicleTypeController {
    @Autowired
    private IVehicleTypeService vehicleTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="List Vehicle Types", notes = "Method to list all vehicle types")
    @ApiResponses({
            @ApiResponse(code=200, message = "Vehicle Types found"),
            @ApiResponse(code=404, message = "Vehicle Types not found")
    })
    public ResponseEntity<List<VehicleType>> findAll() {
        try {
            List<VehicleType> vehicleTypes = vehicleTypeService.getAll();
            if(vehicleTypes.size()>0)
                return new ResponseEntity<List<VehicleType>>(vehicleTypes, HttpStatus.OK);
            else
                return new ResponseEntity<List<VehicleType>>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<List<VehicleType>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Seach Vehicle by Id", notes = "Method to find a vehicle by Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "Vehicle found"),
            @ApiResponse(code=404, message = "Vehicle not found")
    })
    public ResponseEntity<VehicleType> findById(@PathVariable("id") Long id){
        try{
            Optional<VehicleType> vehicleType = vehicleTypeService.getById(id);
            if(!vehicleType.isPresent())
                return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<VehicleType>(vehicleType.get(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Vehicle by name", notes = "Method to find Vehicle by name")
    @ApiResponses({
            @ApiResponse(code=201, message = "Vehicle found"),
            @ApiResponse(code=404, message = "Vehicle not found"),
    })
    public ResponseEntity<List<VehicleType>> findByName(@PathVariable("name") String name){
        try{
            List<VehicleType> vehicleTypes = vehicleTypeService.findByName(name);
            if(vehicleTypes.size()>0)
                return new ResponseEntity<List<VehicleType>>(vehicleTypes, HttpStatus.OK);
            else
                return new ResponseEntity<List<VehicleType>>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<List<VehicleType>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value ="Registration of Vehicles", notes = "Method to register Vehicles in the BD")
    @ApiResponses({
            @ApiResponse(code=201, message = "Vehicle found"),
            @ApiResponse(code=404, message = "Vehicle not found")
    })
    public ResponseEntity<VehicleType> insertVehicle(@Valid @RequestBody VehicleType vehicleType){
        try{
            VehicleType vehicleType1 = vehicleTypeService.save(vehicleType);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicleType1);
        }catch (Exception e){
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Update Vehicles data", notes = "Method to update Vehicles")
    @ApiResponses({
            @ApiResponse(code=200, message = "Vehicle updated"),
            @ApiResponse(code=404, message = "Vehicle not updated")
    })
    public ResponseEntity<VehicleType> updateVehicle(
            @PathVariable("id") Long id, @Valid @RequestBody VehicleType vehicleType){
        try {
            Optional<VehicleType> vehicleUp = vehicleTypeService.getById(id);
            if(!vehicleUp.isPresent())
                return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
            vehicleType.setId(id);
            vehicleTypeService.save(vehicleType);
            return new ResponseEntity<VehicleType>(vehicleType, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Delete Vehicle", notes = "Method to delete Vehicle")
    @ApiResponses({
            @ApiResponse(code=200, message = "Vehicle deleted"),
            @ApiResponse(code=404, message = "Vehicle not deleted")
    })
    public ResponseEntity<VehicleType> deleteVehicle(@PathVariable("id")Long id){
        try{
            Optional<VehicleType> deleteVehicle = vehicleTypeService.getById(id);
            if(!deleteVehicle.isPresent())
                return new ResponseEntity<VehicleType>(HttpStatus.NOT_FOUND);
            vehicleTypeService.delete(id);
            return new ResponseEntity<VehicleType>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<VehicleType>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
