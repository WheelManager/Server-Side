package com.hackdead.wheelmanager.controller;

import com.hackdead.wheelmanager.entities.Customer;
import com.hackdead.wheelmanager.service.ICustomerService;
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
@RequestMapping("/api/customers")
@Api(tags = "Customer", value = "Service Web RESTFul of customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List Customers", notes = "Method to list all customers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customers found"),
            @ApiResponse(code = 404, message = "Customers not found")
    })
    public ResponseEntity<List<Customer>> findAll() {
        try {
            List<Customer> customers = customerService.getAll();
            if (customers.size() > 0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Customer by Id", notes = "Method to find an customer by Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer found"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customer = customerService.getById(id);
            if (!customer.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Customer by username", notes = "Method to find Customer by username")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer found"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    public ResponseEntity<List<Customer>> findByUsername(@PathVariable("username") String username) {
        try {
            List<Customer> customers = customerService.findByUsername(username);
            if (customers.size() > 0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Customer by Name", notes = "Method to find Customer by Name")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer found"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    public ResponseEntity<List<Customer>> findByName(@PathVariable("name") String name) {
        try {
            List<Customer> customers = customerService.findByName(name);
            if (customers.size() > 0)
                return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
            else
                return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByDni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Customer by Dni", notes = "Method to find Customer by Dni")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer found"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    public ResponseEntity<Customer> findByDni(@PathVariable("dni") String dni) {
        try {
            Optional<Customer> customer = customerService.findByDni(dni);
            if (!customer.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registration of Customers", notes = "Method to register Customers in the BD")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Customer found"),
            @ApiResponse(code = 404, message = "Customer not found")
    })
    public ResponseEntity<Customer> insertCustomer(@Valid @RequestBody Customer customer) {
        try {
            Customer customerNew = customerService.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerNew);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Customers data", notes = "Method to update Customers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer updated"),
            @ApiResponse(code = 404, message = "Customer not updated")
    })
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable("id") Long id, @Valid @RequestBody Customer customer) {
        try {
            Optional<Customer> customerUp = customerService.getById(id);
            if (!customerUp.isPresent())
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            customer.setId(id);
            customerService.save(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}"/*,produces = MediaType.APPLICATION_JSON_VALUE*/)
    @ApiOperation(value = "Delete Customer", notes = "Method to delete Customer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Customer deleted"),
            @ApiResponse(code = 404, message = "Customer not deleted")
    })
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        try {
            Optional<Customer> customerDelete = customerService.getById(id);
            if (!customerDelete.isPresent()) {
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            }
            customerService.delete(id);
            return new ResponseEntity<Customer>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
